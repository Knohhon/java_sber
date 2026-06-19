import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class timus_1726 {

    public static class AverageDistance {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(br.readLine().trim());

            long[] x = new long[n];
            long[] y = new long[n];

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                x[i] = Long.parseLong(st.nextToken());
                y[i] = Long.parseLong(st.nextToken());
            }

            Arrays.sort(x);
            Arrays.sort(y);

            long totalSum = sumOfPairwiseDiffs(x) + sumOfPairwiseDiffs(y);
            long numPairs = (long) n * (n - 1) / 2;

            long average = totalSum / numPairs;

            System.out.println(average);
        }

        // Сумма попарных |arr[i] - arr[j]| по всем i < j для отсортированного массива
        private static long sumOfPairwiseDiffs(long[] arr) {
            long total = 0;
            long prefixSum = 0;

            for (int i = 0; i < arr.length; i++) {
                total += arr[i] * i - prefixSum;
                prefixSum += arr[i];
            }

            return total;
        }
    }
}
