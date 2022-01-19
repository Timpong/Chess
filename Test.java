import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Test {

    public static void main(String[] args) {
        // int[][] li = new int[][]{{0,1},{2,3}};
        // System.out.println(Arrays.toString(li[0]));
        ArrayList<int[]> li = new ArrayList<>();
        System.out.println(li.size());
        li.add(new int[]{0,0});
        System.out.println(li.size());
    }
}
