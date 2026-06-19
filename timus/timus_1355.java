import java.util.Scanner;

public class timus_1355 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < t; i++) {
            long a = scanner.nextLong();
            long b = scanner.nextLong();

            long ans;
            if (b % a != 0) {
                ans = 0;
            } else {
                long q = b / a;
                int count = 0;
                for (long p = 2; p * p <= q; p++) {
                    while (q % p == 0) {
                        count++;
                        q /= p;
                    }
                }
                if (q > 1) {
                    count++;
                }
                ans = count + 1;
            }

            sb.append(ans).append("\n");
        }

        System.out.print(sb);
    }
}
