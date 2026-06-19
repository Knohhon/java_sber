import java.util.Scanner;

public class timus_1335 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long c = n * n;
        long b = n * n + n;
        long a = n * n + 2 * n;
        System.out.println(a + " " + b + " " + c);
    }
}
