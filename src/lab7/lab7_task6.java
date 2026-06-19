package lab7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class lab7_task6 {

    public static class WordSearcher {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите имя файла: ");
            String fileName = scanner.nextLine();
            System.out.print("Введите слово для поиска: ");
            String word = scanner.nextLine();

            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                int lineNumber = 0;
                boolean found = false;

                while ((line = reader.readLine()) != null) {
                    lineNumber++;
                    if (line.contains(word)) {
                        System.out.println("Строка " + lineNumber + ": " + line);
                        found = true;
                    }
                }

                if (!found) {
                    System.out.println("Слово \"" + word + "\" не найдено в файле.");
                }
            } catch (IOException e) {
                System.out.println("Ошибка при чтении файла: " + e.getMessage());
            }
        }
    }
}
