package capers;
import java.util.List;
import java.util.Arrays;
import java.util.Map;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class Test {
    public static void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
    }

    public  static void swap2(int[] A) {
        int[] B = A;
        for (int i = 0; i < B.length; i++) {
            B[i] = B[i] * 2;
        }
    }
    public static void main(String[] args) {
        int x1 = 6;
        int x2 = 9;
        System.out.println("Before Swapping");
        System.out.println("x1" +  "=" + x1);
        System.out.println("x2" +  "=" + x2);
        swap(x1, x2);
        System.out.println("After Swapping");
        System.out.println("x1" +  "=" + x1);
        System.out.println("x2" +  "=" + x2);

        int temp = x1;
        x1 = x2;
        x2 = temp;
        System.out.println("true Swapping");
        System.out.println("x1" +  "=" + x1);
        System.out.println("x2" +  "=" + x2);

        int[] x3 = new int[] {1,2,3,4};
        System.out.println("x3" +  "=" + Arrays.toString(x3));
        swap2(x3);
        System.out.println("After Swapping");
        System.out.println("x3" +  "=" + Arrays.toString(x3));
    }
}
