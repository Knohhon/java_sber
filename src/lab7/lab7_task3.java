package lab7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class lab7_task3 {

    public static class FileLineCounter {
        public static void main(String[] args) throws IOException {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите имя файла: ");
            String fileName = scanner.nextLine();

            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                int lineCount = 0;
                while (reader.readLine() != null) {
                    lineCount++;
                }
                System.out.println("Количество строк в файле: " + lineCount);
            } catch (IOException e) {
                System.out.println("Ошибка при чтении файла: " + e.getMessage());
            }
        }
    }
}
