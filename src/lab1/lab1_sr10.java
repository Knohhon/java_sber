package lab1;

import java.util.Scanner;

public class lab1_sr10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите первое число: ");
        int a = sc.nextInt();
        System.out.print("Введите второе число: ");
        int b = sc.nextInt();

        int sum = a + b;
        int diff = a - b;

        System.out.println("Сумма: " + sum);
        System.out.println("Разность: " + diff);
    }
}
