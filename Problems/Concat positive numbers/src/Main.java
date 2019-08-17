import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class ConcatPositiveNumbersProblem {

    public static List<Integer> concatPositiveNumbers(List<Integer> l1, List<Integer> l2) {
        List<Integer> result = new ArrayList<>();
        for(Integer n1 : l1){
            if(n1 > 0){
                result.add(n1);
            }
        }
        for(Integer n2 : l2){
            if(n2 > 0){
                result.add(n2);
            }
        }

        return result; // write your code here
    }

    /* Do not modify this method */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> list1 = readArrayList(scanner);
        List<Integer> list2 = readArrayList(scanner);

        List<Integer> result = concatPositiveNumbers(list1, list2);

        result.forEach((n) -> System.out.print(n + " "));
    }

    /* Do not modify this method */
    private static List<Integer> readArrayList(Scanner scanner) {
        return Arrays
                .stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}