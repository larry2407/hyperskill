import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        String str = sc.nextLine();
        int l = str.length();
        if (l == 1) {
            System.out.println(2);
        } else {
            int count = 2;
            for (int i = l - 2; i >= 0; i--) {
                String currentChar = str.substring(i, i + 1);
                String previousSubStr = str.substring(i + 1, l);

                if (!previousSubStr.contains(currentChar)) {
                    count += l - i;
                } else {
                    int j = 0;
                    while (kmpSearch(previousSubStr, str.substring(i, l - j)).isEmpty()) {
                        count++;
                        j++;
                    }
                }
            }

            System.out.println(count);

        }
    }

    public static int[] getPrefix(String str) {
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

    public static List<Integer> kmpSearch(String text, String pattern) {
        /* 1 */
        int[] prefixFunc = getPrefix(pattern);
        ArrayList<Integer> occurrences = new ArrayList<Integer>();
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
                occurrences.add(i - j + 1);
                j = prefixFunc[j - 1];
            }
        } /* 6 */
        return occurrences;
    }

}
