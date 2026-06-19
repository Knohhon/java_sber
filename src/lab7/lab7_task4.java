package lab7;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class lab7_task4 {

    public static class FileCopier {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите имя исходного файла: ");
            String sourceFile = scanner.nextLine();
            System.out.print("Введите имя файла назначения: ");
            String targetFile = scanner.nextLine();

            try (FileReader reader = new FileReader(sourceFile);
                 FileWriter writer = new FileWriter(targetFile)) {

                int c;
                while ((c = reader.read()) != -1) {
                    writer.write(c);
                }
                System.out.println("Файл скопирован успешно!");
            } catch (IOException e) {
                System.out.println("Ошибка при копировании файла: " + e.getMessage());
            }
        }
    }
}
