import java.util.Scanner;

class Time {

    int hour;
    int minute;
    int second;

    public static Time noon() {
        Time returnedInstance = new Time();
        returnedInstance.hour = 12;
        returnedInstance.minute = 0;
        returnedInstance.second = 0;

        return returnedInstance;
    }

    public static Time midnight() {
        Time returnedInstance = new Time();
        returnedInstance.hour = 0;
        returnedInstance.minute = 0;
        returnedInstance.second = 0;

        return returnedInstance;

    }

    public static Time ofSeconds(long seconds) {
        Time returnedInstance = new Time();
        int h = (int) (seconds / 3600);
        int m = (int) ((seconds % 3600)/60);
        int s = (int) (seconds - 3600 * h - 60 * m);
        returnedInstance.hour = h%24;
        returnedInstance.minute = m;
        returnedInstance.second = s;

        return returnedInstance;

    }

    public static Time of(int hour, int minute, int second) {
        Time returnedInstance = new Time();
        boolean isCorrectTime = hour>=0 && hour<=23 && minute>=0 && minute<=59 && second>=0 && second<=59;
        if(isCorrectTime){
            returnedInstance.hour = hour;
            returnedInstance.minute = minute;
            returnedInstance.second = second;

            return returnedInstance;

        }else{
            return null;
        }

    }
}

/* Do not change code below */
public class Main {

    public static void main(String args[]) {
        final Scanner scanner = new Scanner(System.in);

        final String type = scanner.next();
        Time time = null;

        switch (type) {
            case "noon":
                time = Time.noon();
                break;
            case "midnight":
                time = Time.midnight();
                break;
            case "hms":
                int h = scanner.nextInt();
                int m = scanner.nextInt();
                int s = scanner.nextInt();
                time = Time.of(h, m, s);
                break;
            case "seconds":
                time = Time.ofSeconds(scanner.nextInt());
                break;
            default:
                time = null;
                break;
        }

        if (time != null) {
            System.out.println(String.format("%s %s %s", time.hour, time.minute, time.second));
        } else {
            System.out.println(time);
        }
    }
}