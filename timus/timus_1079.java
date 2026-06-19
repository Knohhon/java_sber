import java.util.Scanner;

public class timus_1079 {

    public static class Solution {
        public static void main(String[] args) {
            final int MAX_N = 99999;

            // Предвычисляем последовательность a[0..MAX_N]
            int[] a = new int[MAX_N + 1];
            a[0] = 0;
            a[1] = 1;
            for (int k = 2; k <= MAX_N; k++) {
                if (k % 2 == 0) {
                    a[k] = a[k / 2];
                } else {
                    int i = (k - 1) / 2;
                    a[k] = a[i] + a[i + 1];
                }
            }

            // Предвычисляем массив максимумов на префиксе для O(1) ответа на запрос
            int[] maxPrefix = new int[MAX_N + 1];
            maxPrefix[0] = a[0];
            for (int k = 1; k <= MAX_N; k++) {
                maxPrefix[k] = Math.max(maxPrefix[k - 1], a[k]);
            }

            Scanner in = new Scanner(System.in);
            StringBuilder sb = new StringBuilder();

            while (in.hasNextInt()) {
                int n = in.nextInt();
                if (n == 0) {
                    break;
                }
                sb.append(maxPrefix[n]).append("\n");
            }

            System.out.print(sb);
        }
    }
}
