package lab1;

import java.util.Scanner;

public class lab1_sr4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите название месяца: ");
        String month = sc.nextLine();
        System.out.print("Введите количество дней: ");
        int days = sc.nextInt();
        System.out.println(month + " содержит " + days + " дней");
    }
}
