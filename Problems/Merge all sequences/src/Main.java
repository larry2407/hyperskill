import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        List<int[]> inputList = createListOfSequences(sc);
        int[] currentArray = inputList.get(0);
        for(int i=1; i<inputList.size(); i++){
            currentArray= concatenation(currentArray, inputList.get(i));
            mergeSort(currentArray, 0, currentArray.length);
        }
      for(int n : currentArray){
          System.out.print(n+" ");
      }
    }

    private static int[] concatenation(int[] currentArray, int[] ints) {
        int currentArrayLength = currentArray.length;
        int concatenatedLength =currentArrayLength + ints.length;
        int[] concatenation = new int[concatenatedLength];
        for(int k=0; k<concatenatedLength; k++){
            if(k<currentArrayLength ) {
                concatenation[k] = currentArray[k];
            }else{
                concatenation[k] = ints[k-currentArrayLength];
            }
        }
        return concatenation;
    }

    private static List<int[]> createListOfSequences(Scanner sc) {
        List<int[]> result = new ArrayList<>();
        int listLength = sc.nextInt();
        for(int i=0; i<listLength; i++){
            result.add(createSequence(sc));
        }
        return result;
    }

    private static int[] createSequence(Scanner sc) {
        int seqLength = sc.nextInt();
        int[] seq = new int[seqLength];
        for(int j=0; j<seqLength; j++){
            seq[j] = sc.nextInt();
        }
        return seq;
    }

    public static void mergeSort(int[] array, int leftIncl, int rightExcl) {

        // the base case: if subarray contains <= 1 items, stop dividing because it's sorted
        if (rightExcl <= leftIncl + 1) {
            return;
        }

        /* divide: calculate the index of the middle element */
        int middle = leftIncl + (rightExcl - leftIncl) / 2;

        mergeSort(array, leftIncl, middle);  // conquer: sort the left subarray
        mergeSort(array, middle, rightExcl); // conquer: sort the right subarray

        /* combine: merge both sorted subarrays into sorted one */
        merge(array, leftIncl, middle, rightExcl);
    }

    private static void merge(int[] array, int left, int middle, int right) {

        int i = left;   // index for the left subarray
        int j = middle; // index for the right subarray
        int k = 0;      // index for the temp subarray

        int[] temp = new int[right - left]; // temporary array for merging

    /* get the next lesser element from one of two subarrays
       and then insert it in the array until one of the subarrays is empty */
        while (i < middle && j < right) {
            if (array[i] >= array[j]) {
                temp[k] = array[i];
                i++;
            } else {
                temp[k] = array[j];
                j++;
            }
            k++;
        }

        /* insert all the remaining elements of the left subarray in the array */
        for (;i < middle; i++, k++) {
            temp[k] = array[i];
        }

        /* insert all the remaining elements of the right subarray in the array */
        for (;j < right; j++, k++) {
            temp[k] = array[j];
        }

        /* effective copying elements from temp to array */
        System.arraycopy(temp, 0, array, left, temp.length);
    }

}