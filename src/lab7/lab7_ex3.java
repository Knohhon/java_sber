package lab7;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class lab7_ex3 {

    public static class CharStreamExample {
        public static void main(String[] args) {
            // Запись данных в файл
            String fileName = "src/lab7/example3_file.txt";
            String data = "Это данные для записи в файл";

            try (FileWriter writer = new FileWriter(fileName)) {
                writer.write(data);
                System.out.println("Данные записаны в файл: " + fileName);
            } catch (IOException e) {
                System.out.println("Ошибка при записи в файл: " + e.getMessage());
            }

            // Чтение данных из файла
            try (FileReader reader = new FileReader(fileName)) {
                char[] buffer = new char[1024];
                int charRead = reader.read(buffer);
                String readData = new String(buffer, 0, charRead);
                System.out.println("Прочитанные данные: " + readData);
            } catch (IOException e) {
                System.out.println("Ошибка при чтении файла: " + e.getMessage());
            }

            // Удаление файла
            if (new File(fileName).delete()) {
                System.out.println("Файл удален: " + fileName);
            } else {
                System.out.println("Не удалось удалить файл: " + fileName);
            }
        }
    }
}
