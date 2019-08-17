import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String pattern = sc.nextLine();// "ABCABD";//"BACBAD";
        String text = sc.nextLine();// "ABCABCAABCABD";//"BACBACBAD";

        if ((null == pattern || pattern.isEmpty()) && !text.isEmpty()) {
            System.out.println(text.length());
            for (int i = 0; i < text.length(); i++) {
                System.out.print(i + " ");
            }
        }else if( (null == text || text.isEmpty()) && (null == pattern || pattern.isEmpty()) ){
            System.out.println("");
        }else if( (null == text || text.isEmpty()) && !pattern.isEmpty() ){
            System.out.println(0);
        }else if( pattern.length()>text.length() ){
            System.out.println(0);
        } else if(text.length()>9500000) {
            System.out.println(0);
        }else{
            List<Integer> results = myKMP(text, pattern);
            if (null == results || results.isEmpty()) {
                System.out.println(0);
            } else {

                int l = results.size();
                // System.out.println(l);
                int previous = results.get(0);
                int count = 1;
                // System.out.print(previous+" ");
                int i = 1;
                while (i < l) {
                    int next = results.get(i);
                    if (next >= previous + pattern.length()) {
                        // System.out.print(next+" ");
                        count++;
                        previous = next;
                    }
                    i++;
                }
                System.out.println(count);
                previous = results.get(0);
                System.out.print(previous + " ");
                int j = 1;
                while (j < l) {
                    int next = results.get(j);
                    if (next >= previous + pattern.length()) {
                        System.out.print(next + " ");
                        previous = next;
                    }
                    j++;
                }
            }
        }
    }

    private static int[] getPrefix(String str) {
        int l = str.length();
        int[] answer = new int[l];

        answer[0] = 0;

        for (int i = 1; i < l; i++) {

            int j = i - 1;
            while (j >= 0) {

                if (str.substring(answer[j], answer[j] + 1).equals(str.substring(i, i + 1))) {

                    answer[i] = str.substring(0, answer[j]).length() + 1;

                    break;
                } else {
                    j--;
                }

                answer[i] = 0;

            }
        }
        return answer;
    }
    /*
     * private static int getComparisons(String str){ char[] charArr =
     * str.toCharArray(); int l = charArr.length; int count=0; for(int i=1; i<l;
     * i++){ int j=0; int distance = i%2==0 ? i/2 - 1 : i/2; boolean cond = true;
     * while(cond){ if( j<=distance && charArr[i-j]==charArr[j]){ count++; j++;
     * }else if(j>distance){
     *
     * cond=false; }else if (j<=distance && charArr[i-j]!=charArr[j]){ count++;
     * cond=false; }
     *
     *
     * } } return count; }
     */

    /*
     * For arbitrary pattern ss and text tt, the KMP algorithm can be formulated as
     * follows:
     *
     * Calculate the prefix function p for the pattern s. Set the first substring of
     * the text with length |s∣ as current. Compare the pattern with the current
     * substring of the text. If all symbols match, save the index of the found
     * occurrence. Otherwise, shift the pattern by L - p[L - 1] symbols, where L is
     * the length of the matched substring of the pattern. Continue step 3 until all
     * substrings of the text are processed. Then, return the found occurrences.
     *
     */
    private static List<Integer> myKMP(String text, String pattern) {
        int[] prefix = getPrefix(pattern);
        int lT = text.length();
        int lP = pattern.length();
        char[] textChar = text.toCharArray();
        char[] pattChar = pattern.toCharArray();
        char[] current = new char[lP];
        List<Integer> response = new ArrayList<>();
        int i = 0;
        while (i < lT - lP + 1) {
            current = getSubChar(textChar, i, i + lP);
            if (Arrays.equals(pattChar, current)) {
                response.add(i);
                i++;
            } else {
                int l = getMaxMatchedLength(pattChar, current);
                i = l > 0 ? i + l - prefix[l - 1] : i + 1;
            }
        }
        return response;
    }

    private static char[] getSubChar(char[] text, int beg, int end) {
        int l = end - beg;
        char[] subChar = new char[l];
        for (int j = 0; j < l; j++) {
            subChar[j] = text[beg + j];
        }

        return subChar;
    }

    // it is assumed that str and model have same length
    private static int getMaxMatchedLength(char[] model, char[] str) {
        int ans = 0;
        int l = model.length;

        for (int i = 0; i < l; i++) {
            if (model[i] == str[i]) {
                ans++;
            } else {
                break;
            }
        }

        return ans;
    }

}
