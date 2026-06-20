import java.util.Scanner;

public class timus_1964 {

    public static class Main {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            long n = scanner.nextLong();
            int k = scanner.nextInt();

            long sum = 0;
            for (int i = 0; i < k; i++) {
                long a = scanner.nextLong();
                sum += a;
            }

            long answer = sum - n * (k - 1);

            if (answer < 0) {
                answer = 0;
            }

            System.out.println(answer);
        }
    }
}
