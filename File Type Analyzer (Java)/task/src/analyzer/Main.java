package analyzer;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class Main {

/*
    private static int[] pngSignature = {137, 80, 78, 71, 13, 10, 26, 10};
    private static int[] pdfSignature = {37, 80, 68, 70, 45};
    private static int[] docSignature = {208, 207, 17, 224, 161, 177, 26, 225};
*/

    public static void main(String[] args) {
/*
        Character[] pdf = new Character[]{'%', 'P', 'D', 'F', '-'};
        for(char c: pdf){
            System.out.println((int) c);
        }
*/
        try {
            checkMultipleTypePatternsOnMultipleFiles(args[0]);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
/*
        try {
            checkMultipleFilesTypes(args[0], args[1], args[2]);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

 */
/*
        String inputFile = ;
        String pattern = args[1];
        String resultString = args[2];
*/
 /*       long startTime = System.nanoTime();
        System.out.println(checkFileType(args[0], args[1], args[2], args[3]));
        long endTime = System.nanoTime();
        double duration = (double) (endTime - startTime) / 1_000_000_000;
        System.out.println("It took " + duration + " seconds"); */

    }

    private static void checkMultipleTypePatternsOnMultipleFiles(String arg0) throws InterruptedException {
        List<String[]> listOfPatterns = new ArrayList<>();
        URL url = null;
        try {
            url = new URL("https://stepik.org/media/attachments/lesson/210127/patterns.db");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try (Scanner s = new Scanner(url.openStream())) {
            while(s.hasNextLine()){
                listOfPatterns.add(s.nextLine().split(";"));
            }
            //s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // int l = listOfPatterns.size();
                checkMultipleFilesTypes(arg0,listOfPatterns);
    }

/*
    private static List<String[]> createPatterns(File patterndb) throws FileNotFoundException {
        Scanner sc = new Scanner(patterndb);
        List<String[]> listOfPatterns = new ArrayList<>();
        while(sc.hasNextLine()){
            listOfPatterns.add(sc.nextLine().split(";"));
        }
        sc.close();
        return listOfPatterns;
    }
*/

    private static int[] shiftOneStepLeft(int[] currentArraySignatureBytesRead, int current) {
        int l = currentArraySignatureBytesRead.length;
        int[] ans = new int[l];
        for (int i = 0; i < l - 1; i++) {
            ans[i] = currentArraySignatureBytesRead[i + 1];
        }
        ans[l - 1] = current;
        return ans;
    }

    protected static void checkMultipleFilesTypes(String folderPath, List<String[]> priorityPatternsList ) throws InterruptedException {
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();
        int l0 = priorityPatternsList.size();
        int l = listOfFiles.length;
        Worker[][] listOfWorkers = new Worker[l][l0];

        String currentFileName="";
        for(int i=0; i<l; i++) {
            boolean found = false;
           // System.out.println("enters file i="+i);
            for (int j=l0-1; j >=0; j--) {
                //System.out.println(Arrays.asList(priorityPatternsList.get(0)));
                currentFileName = getLastSplitted(listOfFiles[i].getAbsolutePath());
                listOfWorkers[i][j] = new Worker(listOfFiles[i].getAbsolutePath(), priorityPatternsList.get(j)[1].replaceAll("\"",""),
                        priorityPatternsList.get(j)[2].replaceAll("\"",""));
                listOfWorkers[i][j].start();
                listOfWorkers[i][j].join();
                if(listOfWorkers[i][j].result){
                    System.out.println(currentFileName.concat(": ").concat(listOfWorkers[i][j].arg2));
                    found = true;
                    break;
                }
            }
            if(!found){
                System.out.println(currentFileName.concat(": ").concat("Unknown file type"));
            }
        }
    }

    private static String getLastSplitted(String arg0) {
        String[] inputFileArr = arg0.split("\\\\");
        int inputFileArrLength = inputFileArr.length;
        return inputFileArr[inputFileArrLength-1];
    }


    protected static boolean checkFileType(String strategy, String inputFile, String pattern, String res) {

        boolean isOfType = false;
        int current = 0;
        String[] answer = new String[2];
        try (InputStream inputStream = new FileInputStream(inputFile)) {

            if (strategy.equals("--naive")) {
                int[] fileSignature = getSignature(pattern);
                int l = fileSignature.length;
                int[] currentArraySignatureBytesRead = new int[l];
                for (int i = 0; i < l; i++) {
                    current = inputStream.read();
                    currentArraySignatureBytesRead[i] = current;
                    //isPdf = isPdf && (currentArraySignatureBytesRead[i] == pdfSignature[i]);
                    if (current == -1) {
                        break;
                    }
                }
                if (Arrays.equals(currentArraySignatureBytesRead, fileSignature)) {
                    isOfType = true;
                } else {
                    while ((current = inputStream.read()) != -1) {
                        //                   System.out.println(Arrays.toString(currentArraySignatureBytesRead));
                        currentArraySignatureBytesRead = shiftOneStepLeft(currentArraySignatureBytesRead, current);
                        //                   System.out.println(Arrays.toString(currentArraySignatureBytesRead));

                        if (Arrays.equals(currentArraySignatureBytesRead, fileSignature)) {
                            isOfType = true;
                            break;
                        }
                    }
                }

            } else if (strategy.equals("--KMP")) {

                StringBuilder partialStr = new StringBuilder();
                while ((current = inputStream.read()) != -1) {

                    partialStr.append((char) current);

                }
                if (kmpSearchFirstOccurrence(partialStr.toString(), pattern) != -1) {
                    isOfType = true;
                }

            }else if (strategy.equals("--RK")) {

                StringBuilder partialStr = new StringBuilder();
                while ((current = inputStream.read()) != -1) {

                    partialStr.append((char) current);

                }
                if (rabinKarp(partialStr.toString(), pattern)) {
                    isOfType = true;
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
//        String[] inputFileArr = inputFile.split("\\\\");
//        int inputFileArrLength = inputFileArr.length;
        //System.out.print(inputFileArr[inputFileArrLength-1].concat(": "));
        //System.out.println(isOfType? res : "Unknown file type");
        //return isOfType ? res : "Unknown file type";
//        answer[0] = inputFileArr[inputFileArrLength-1].concat(": ");
//        if(isOfType){
//            answer[1]=res;
//        }else{
//            answer[1] = "Unknown file type";
//        }
       return isOfType;
    }

    private static int[] getSignature(String pattern) {
        int patternLength = pattern.length();
        int[] signature = new int[patternLength];
        char[] sgn = pattern.toCharArray();
        for (int i = 0; i < patternLength; i++) {
            signature[i] = (int) sgn[i];
        }
        return signature;
    }

    public static int[] prefixFunction(String str) {
        /* 1 */
        int[] prefixFunc = new int[str.length()];

        /* 2 */
        for (int i = 1; i < str.length(); i++) {
            /* 3 */
            int j = prefixFunc[i - 1];

            while (j > 0 && str.charAt(i) != str.charAt(j)) {
                j = prefixFunc[j - 1];
            }

            /* 4 */
            if (str.charAt(i) == str.charAt(j)) {
                j += 1;
            }

            /* 5 */
            prefixFunc[i] = j;
        }

        /* 6 */
        return prefixFunc;
    }

    public static int kmpSearchFirstOccurrence(String text, String pattern) {
        /* 1 */
        int[] prefixFunc = prefixFunction(pattern);
        //ArrayList<Integer> occurrences = new ArrayList<Integer>();
        int firstOccurrence = -1;
        int j = 0;
        /* 2 */
        for (int i = 0; i < text.length(); i++) {
            /* 3 */
            while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
                j = prefixFunc[j - 1];
            }
            /* 4 */
            if (text.charAt(i) == pattern.charAt(j)) {
                j += 1;
            }
            /* 5 */
            if (j == pattern.length()) {
                firstOccurrence = i - j + 1;
                break;
                // j = prefixFunc[j - 1];
            }
        }
        /* 6 */
        return firstOccurrence;
    }

    public static long charToLong(char ch) {
        return (long) (ch - 'A' + 1);
    }
/*
    public static boolean rabinKarp(String text, String pattern) {
        /* 2
        int a = 613;
        long m = 951_466_000_000_009L;

        /* 3
        long patternHash = 0;
        long currSubstrHash = 0;
        long pow = 1;

        for (int i = 0; i < pattern.length(); i++) {
            patternHash += charToLong(pattern.charAt(i)) * pow;
            patternHash %= m;

            currSubstrHash += charToLong(text.charAt(text.length() - pattern.length() + i)) * pow;
            currSubstrHash %= m;

            if (i != pattern.length() - 1) {
                pow = pow * a % m;
            }
        }

        /* 4
        //ArrayList<Integer> occurrences = new ArrayList<Integer>();
        int countOcc = 0;

        for (int i = text.length(); i >= pattern.length(); i--) {
            if (patternHash == currSubstrHash) {
                boolean patternIsFound = true;

                for (int j = 0; j < pattern.length(); j++) {
                    if (text.charAt(i - pattern.length() + j) != pattern.charAt(j)) {
                        patternIsFound = false;
                        break;
                    }
                }

                if (patternIsFound) {
                    countOcc++;
                    //occurrences.add(i - pattern.length());
                    if(countOcc == 1){
                        return true;
                    }
                }
            }

            if (i > pattern.length()) {
                /* 5
                currSubstrHash = (currSubstrHash - charToLong(text.charAt(i - 1)) * pow % m + m) * a % m;
                currSubstrHash = (currSubstrHash + charToLong(text.charAt(i - pattern.length() - 1))) % m;
            }
        }

        //Collections.reverse(occurrences);
        return false;

    } */

    public static boolean rabinKarp(String text, String pattern) {
        int l = text.length();
        int k = pattern.length();
        long patternHash =  hashFunction(pattern, 239, 51_466_000_000_009L);
        for(int j=0; j<=l-k; j++){
            String current=text.substring(j, j+k);
            long currentHash =  hashFunction(current, 239, 51_466_000_000_009L);
            if(currentHash == patternHash){
                return true;
            }
        }
        return false;
    }
    private static long rollHash(long currentHash, String current, String next, int j, int base, long modulo) {
        long delta = currentHash - charToLong(current.charAt(j))*myPow(base, j, modulo) % modulo;
        delta = delta >=0L ? delta : delta + modulo;
        return (delta * base + charToLong(next.charAt(0))) % modulo;
    }
    private static long myPow(long a, int n, long mod){
        long pow = 1L;
        for(int i=0; i<n; i++){
            pow *= a;
            pow %= mod;
        }
        return pow;
    }
    private static long hashFunction(String current, int a, long m) {
        long currHash = 0L;
        long pow = 1L;
        //int a = 53;
        //long m = 1_000_000_009L;
        for (int i = 0; i < current.length(); i++) {
            currHash += charToLong(current.charAt(i)) * pow;
            currHash %= m;

            if (i != current.length() - 1) {
                pow = pow * a % m;
            }
        }
        return currHash;
    }

}