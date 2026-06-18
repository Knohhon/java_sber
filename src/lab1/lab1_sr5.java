package lab1;

import java.util.Scanner;

public class lab1_sr5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите год рождения: ");
        int birthYear = sc.nextInt();
        int currentYear = 2026;
        int age = currentYear - birthYear;
        System.out.println("Ваш возраст: " + age);
    }
}
