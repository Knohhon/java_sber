package lab7;

import java.io.File;
import java.util.Scanner;

public class lab7_task5 {

    public static class FileSizeChecker {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите имя файла: ");
            String fileName = scanner.nextLine();

            File file = new File(fileName);
            if (file.exists()) {
                long sizeInBytes = file.length();
                System.out.println("Размер файла: " + sizeInBytes + " байт");
            } else {
                System.out.println("Файл не найден: " + fileName);
            }
        }
    }
}
