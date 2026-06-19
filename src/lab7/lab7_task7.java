package lab7;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class lab7_task7 {

    public static class FileWriterCounter {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите имя файла: ");
            String fileName = scanner.nextLine();
            System.out.print("Введите текст для записи: ");
            String text = scanner.nextLine();

            try (FileWriter writer = new FileWriter(fileName)) {
                writer.write(text);
                System.out.println("Количество записанных символов: " + text.length());
            } catch (IOException e) {
                System.out.println("Ошибка при записи в файл: " + e.getMessage());
            }
        }
    }
}
