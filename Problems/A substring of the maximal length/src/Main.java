import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    // private static File test22 = new File("C:\\Users\\lpapi\\IdeaProjects\\File Type Analyzer (Java)\\Problems\\A substring of the maximal length\\src\\5722-22.txt");
    private static Scanner sc = new Scanner(System.in);

 /*   static {
        try {
            sc = new Scanner(test22);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
*/

    public static void main(String[] args){
        String str = sc.nextLine();
        if(str == null || str.isEmpty() || str.length()<2){
            System.out.println(0);
        }else{
            int end = str.length() - 1;
            //end = 200;
            int[] current = new int[2];
            //int k = 0;
            //long t1 = System.nanoTime();
            //while(current[1] > current[0]) {
                current = getNextInterval(str, new int[]{1, end}, end);
                //k++;
                // System.out.println("iteration: "+k+" beg: "+current[0]+"; end: "+current[1] );
            //}
            //long t2 =  System.nanoTime();
            System.out.println(current[0]);

            //System.out.println("it took: "+ (t2-t1)/1_000_000 + " milliseconds" );
        }
    }



    private static long rollHash(long currentHash, String current, String next, int j, int base, long modulo) {
        long delta = currentHash - charToLong(current.charAt(j))*myPow(base, j, modulo) % modulo;
        delta = delta >=0 ? delta : delta + modulo;
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
        long currHash = 0;
        long pow = 1;
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

    /* 1 */
    public static long charToLong(char ch) {
        return (long) (ch - 'A' + 1);
    }

    public static boolean rabinKarp(String text, String pattern) {
        /* 2 */
        int a = 53;
        long m = 1_000_000_000 + 9;

        /* 3 */
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

        /* 4 */
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
                    if(countOcc == 2){
                        return true;
                    }
                }
            }

            if (i > pattern.length()) {
                /* 5 */
                currSubstrHash = (currSubstrHash - charToLong(text.charAt(i - 1)) * pow % m + m) * a % m;
                currSubstrHash = (currSubstrHash + charToLong(text.charAt(i - pattern.length() - 1))) % m;
            }
        }

        //Collections.reverse(occurrences);
        return false;

    }

    private static boolean substringOfLengthKAtLeastTwice(String s,  int k){
        int l = s.length();
        Set<Long> partial = new HashSet<>();
        String current=s.substring(l-k);
        long currentHash=0;

        currentHash = hashFunction(current, 239, 51_466_000_000_009L);
        partial.add(currentHash);
        // System.out.println("should always be one: "+partialSize);
        for(int j=l-k-1; j>=0; j--){
            String next = s.substring(j, j + k);

            currentHash = rollHash(currentHash, current, next, k-1, 239, 51_466_000_000_009L);

            //System.out.println("k=" + k + "; setSize: " + partial.size() + "; currentHash: " + currentHash);
            //System.out.println(next);

            if(partial.contains(currentHash)){
                //System.out.println("found: "+next+" has hash: "+currentHash);
                return true;
            }else{
                partial.add(currentHash);
                current = next;
            }

            //           if(rabinKarp(s, s.substring(j, j+i))){
            //               return i;
            //           }
        }
        //partial.clear();
        return false;
    }

    private static int[] getNextInterval(String s, int[] currentInterval, int maxL){
        int[] nextInterval = new int[2];
        //int k=0;
        int beg = currentInterval[0];
        int end ;
        while(beg <= maxL && substringOfLengthKAtLeastTwice(s, beg)){
            beg *= 2;
            //k++;
        }
        if(beg > maxL){
            end = maxL;
        }else{
            end = beg-1;
        }
        beg /= 2;
        while(beg<end) {
            //System.out.println("iteration: "+k+" beg: "+beg+"; end: "+end);

            if (substringOfLengthKAtLeastTwice(s, end)) {
                beg = end;

            } else if (substringOfLengthKAtLeastTwice(s, (beg + end) / 2)) {
                beg = (beg + end) / 2;
                end = end - 1;
            } else {
                end = (beg + end) / 2 - 1;
            }
            //k++;
        }

        return new int[]{beg, end};
    }
}
