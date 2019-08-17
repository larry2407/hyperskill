import java.util.Scanner;

class Finder {

    private FindingStrategy strategy;

    public Finder(FindingStrategy strategy) {
       this.strategy = strategy;
    }

    /**
     * It performs the search algorithm according to the given strategy
     */
    public int find(int[] numbers) {
        int l = numbers.length;
        int chosen = strategy.getDefaultValue();
        for(int i=0; i<l; i++){
            chosen = i > 0 ? strategy.takeOne(numbers[i], chosen) : numbers[i];
           /* if(i==0){
                chosen = numbers[i];
            }else{
                chosen = strategy.takeOne(numbers[i],chosen);
            }*/
        }
        return chosen;
    }
}

interface FindingStrategy {

    /**
     * Returns one of two values
     */
    int takeOne(int elem1, int elem2);

    /**
     * Returns the default value of a concrete implementation
     */
    int getDefaultValue();
}

class MaxFindingStrategy implements FindingStrategy {

    public int takeOne(int elem1, int elem2) {
        return Math.max(elem1, elem2);
    }

    public int getDefaultValue() {
        return Integer.MIN_VALUE;
    }
}

class MinFindingStrategy implements FindingStrategy {

    public int takeOne(int elem1, int elem2) {
        return Math.min(elem1, elem2);
    }

    public int getDefaultValue() {
       return Integer.MAX_VALUE;
    }
}

/* Do not change code below */
public class Main {

    public static void main(String args[]) {

        final Scanner scanner = new Scanner(System.in);

        final String[] elements = scanner.nextLine().split("\\s+");
        int[] numbers = null;

        if (!elements[0].equals("EMPTY")) {
            numbers = new int[elements.length];
            for (int i = 0; i < elements.length; i++) {
                numbers[i] = Integer.parseInt(elements[i]);
            }
        } else {
            numbers = new int[0];
        }

        final String type = scanner.nextLine();

        Finder finder = null;

        switch (type) {
            case "MIN":
                finder = new Finder(new MinFindingStrategy());
                break;
            case "MAX":
                finder = new Finder(new MaxFindingStrategy());
                break;
            default:
                break;
        }

        if (finder == null) {
            throw new RuntimeException(
                    "Unknown strategy type passed. Please, write to the author of the problem.");
        }

        System.out.println(finder.find(numbers));
    }
}