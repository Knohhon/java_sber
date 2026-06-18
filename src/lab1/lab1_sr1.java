package lab1;

import java.util.Scanner;

public class lab1_sr1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите фамилию: ");
        String surname = sc.nextLine();
        System.out.print("Введите имя: ");
        String name = sc.nextLine();
        System.out.print("Введите отчество: ");
        String patronymic = sc.nextLine();
        System.out.println("Hello " + surname + ", " + name + ", " + patronymic);
    }
}
