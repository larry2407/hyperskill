import java.util.Scanner;

class Main {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int l = sc.nextInt();
        int[] arr = new int[l];
        for(int i=0; i<l; i++){
            arr[i] = sc.nextInt();
        }

        System.out.println(insertionSortShiftCount(arr));
    }
    public static int insertionSortShiftCount(int[] array) {
            int shiftCounter = 0;
        /* iterating over elements in the unsorted part */
        for (int i = 1; i < array.length; i++) {
            int elem = array[i]; // take the next element
            int j = i - 1;

            /* find a suitable position to insert and shift elements to the right */
            if( array[j] < elem){
                shiftCounter++;
            }
            while (j >= 0 && array[j] < elem) {
                array[j + 1] = array[j]; // shifting
                j--;
            }
            array[j + 1] = elem; // insert the element in the found position in the sorted part
        }

        return shiftCounter;
    }
}