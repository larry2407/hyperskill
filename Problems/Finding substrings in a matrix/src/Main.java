import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int patternRows = sc.nextInt();
        int patternCols = sc.nextInt();

        char[][] matrixPattern = new char[patternRows][patternCols];

        for (int i = 0; i < patternRows; i++) {

            matrixPattern[i] = sc.next().toCharArray();
        }
        int textRows = sc.nextInt();
        int textCols = sc.nextInt();

        char[][] matrixText = new char[textRows][textCols];

        for (int i = 0; i < textRows; i++) {

            matrixText[i] = sc.next().toCharArray();
        }
        int count = countOccurrences(matrixText, matrixPattern, textRows, textCols, patternRows, patternCols);

        System.out.println(count);
    }

    private static int countOccurrences(char[][] matrixText, char[][] matrixPattern, int textRows, int textCols, int patternRows,
                                        int patternCols) {

        if (patternRows > textRows || patternCols > textCols) {
            return 0;
        }


        int count = 0;

        List<Set<Integer>> results = new ArrayList<>();
        for (int i = 0; i <= textRows - patternRows; i++) {

            for (int j = i; j < i + patternRows; j++) {
                String currentText = new String(matrixText[j]);
                String currentPatt = new String(matrixPattern[j - i]);
                String previousText = j - 1 >= 0 ? new String(matrixText[j - 1]) : "";
                String previousPatt = j - i - 1 >= 0 ? new String(matrixPattern[j - i - 1]) : "";

                if (!previousPatt.isEmpty() && !previousText.isEmpty() && previousText.equals(currentText) && previousPatt.equals(currentPatt)) {
                    continue;
                }
                results.add(kmpSearch(currentText, currentPatt));
            }
            count += countPatterns(results);
            results.clear();
        }


        return count;
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

    public static Set<Integer> kmpSearch(String text, String pattern) {
        /* 1 */
        int[] prefixFunc = prefixFunction(pattern);
        Set<Integer> occurrences = new HashSet<Integer>();
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
        }
        /* 6 */
        return occurrences;
    }

    private static int countPatterns(List<Set<Integer>> res) {
        int l = res.size();
        for (int i = 1; i < l; i++) {
            res.get(0).retainAll(res.get(i));
        }
        return res.get(0).size();
    }

}
