package lab1;

import java.util.Scanner;

public class lab1_sr2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите имя: ");
        String name = sc.nextLine();
        System.out.print("Введите возраст: ");
        int age = sc.nextInt();
        System.out.println("Имя: " + name + ", возраст: " + age);
    }
}
