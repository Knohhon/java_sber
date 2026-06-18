package lab1;

import java.util.Scanner;

public class lab1_sr9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите число: ");
        int n = sc.nextInt();

        int first = n - 1;
        int second = n;
        int third = n + 1;
        int fourth = (first + second + third) * (first + second + third);

        System.out.println(first + ", " + second + ", " + third + ", " + fourth);
    }
}
