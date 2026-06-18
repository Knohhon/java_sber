package lab1;

import java.util.Scanner;

public class lab1_sr7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите возраст: ");
        int age = sc.nextInt();
        int currentYear = 2026;
        int birthYear = currentYear - age;
        System.out.println("Год рождения: " + birthYear);
    }
}
