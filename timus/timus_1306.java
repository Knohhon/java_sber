import java.util.Scanner;
import java.util.Arrays;

public class timus_1306 {

    public static class Median {
        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            int n = in.nextInt();

            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
            }

            Arrays.sort(a); // Java не имеет встроенного nth_element, поэтому проще отсортировать

            double median;
            if (n % 2 == 1) {
                median = a[(n - 1) / 2];
            } else {
                int k = n / 2;
                median = (a[k - 1] + a[k]) / 2.0;
            }

            System.out.printf("%.1f%n", median);
        }
    }
}
