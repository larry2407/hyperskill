import java.util.*;

public class Main {

    private static Scanner sc =new Scanner(System.in);

    public static void main(String[] args) {
        List<Integer> inputList = new ArrayList<>();
        while(sc.hasNextInt()){
            inputList.add(sc.nextInt());
        }
        int l = inputList.size();
        int n = inputList.remove(l-1);

        int least = Integer.MAX_VALUE;
        for(Integer x : inputList){
            least = Math.abs(n - x) < least ?  Math.abs(n - x) : least ;
        }

        for(Integer x : inputList){
           if( n - x == least){
               System.out.print(x+" ");
           }
        }
        if(least != 0) {
            for (Integer x : inputList) {
                if (n - x == -least) {
                    System.out.print(x + " ");
                }
            }
        }
    }
}
/* REFERENCE SOLUTION
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] items = scanner.nextLine().split("\\s+");
        List numbers = new ArrayList<>();

        for (String item : items) {
            numbers.add(Integer.parseInt(item));
        }

        int n = scanner.nextInt();
        ArrayList result = new ArrayList<>();

        int delta = Integer.MAX_VALUE;
        for (int i : numbers) {
            if (Math.abs(i - n) < delta) {
                delta = Math.abs(i - n);
                result.clear();
                result.add(i);
            } else if (Math.abs(i - n) == delta) {
                result.add(i);
            }
        }

        Collections.sort(result);

        for (int item : result) {
            System.out.print(item + " ");
        }
    }
}


 */