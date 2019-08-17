import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Main {

    private final static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String pattern = sc.nextLine().trim();
        String text = sc.nextLine().trim();
        if( (null == pattern || pattern.isEmpty()) && !text.isEmpty()){
            System.out.println(text.length());
            for(int i=0; i<text.length(); i++){
                System.out.print(i+" ");
            }
        }else{
            List<Integer> results = getAllFirstIndexesOfPattern(text, pattern);// at least one element: -1
            //List<Integer> nonOverlapResults = getNonOverLapping(results, pattern);
            if(results == null || results.isEmpty() || results.get(0) == -1){
                System.out.println(0);
            }else{
                System.out.println(results.size());
                for(int n : results){
                    System.out.print(n+" ");
                }
            }
        }
    }

    private static List<Integer> getAllFirstIndexesOfPattern(String text, String pattern){
        List<Integer> ans = new ArrayList<>();
        int lText = text.length();
        int lPattern = pattern.length();
        char[] charText = text.toCharArray();
        if(lPattern > lText){
            ans.add(-1);
            return ans;
        }
        // char[] charPattern = pattern.toCharArray();
        for(int i=0; i<lText - lPattern + 1; i++){
            if(matchPattern(pattern.toCharArray(), subCharArray(i, i+lPattern, charText))){
                ans.add(i);
            }
        }

        return ans;

    }
    private static boolean matchPattern(char[] str, char[] signature){
        return Arrays.equals(str, signature);
    }

    private static char[] subCharArray(int beg, int end, char[] sentence){
        int l = end - beg;
        char[] sub = new char[l];
        for(int i=0; i<l; i++){
            sub[i] = beg + i < sentence.length ? sentence[beg+i] : sub[i];
        }
        return sub;

    }
    /*
    private static List<Integer> getNonOverLapping(List<Integer> res, String patt){
        List<Integer> nonOL = new ArrayList<>();
        if( res == null || res.isEmpty() || res.get(0) == -1 ){
            return null;
        }else{
            int lPatt = patt.length();
            int lRes = res.size();

            nonOL.add(res.get(0));
            int j=0;
            for(int i = 1; i<lRes; i++){
                if(res.get(i) >= nonOL.get(j) + lPatt){
                    nonOL.add(res.get(i));
                    j++;
                }
            }
        }
        return nonOL;
    } */

}
