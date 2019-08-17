import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String pat = sc.nextLine().trim();
        String text = sc.nextLine().trim();

        System.out.println(indexOf(Pattern.compile(pat), text));
    }

    public static int indexOf(Pattern pattern, String str) {
        Matcher matcher = pattern.matcher(str);
        return matcher.find() ? matcher.start() : -1;
    }
}