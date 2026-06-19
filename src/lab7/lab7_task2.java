package lab7;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class lab7_task2 {

    public static class FileCopier {
        public static void main(String[] args) throws IOException {
            BufferedReader consoleReader =
                    new BufferedReader(new InputStreamReader(System.in));
            FileWriter writer = new FileWriter("src/lab7/outputTask2.txt");

            System.out.println("Введите текст построчно. Для завершения ввода введите пустую строку:");

            String line;
            while (!(line = consoleReader.readLine()).isEmpty()) {
                writer.write(line);
                writer.write(System.lineSeparator());
            }

            writer.close();

            System.out.println("Файл сохранен успешно: src/lab7/outputTask2.txt");
        }
    }
}
