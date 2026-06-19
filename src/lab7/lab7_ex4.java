package lab7;

import java.io.*;

public class lab7_ex4 {

    public static class BufferedStreamExample {
        public static void main(String[] args) {
            // Запись данных в файл
            String fileName = "example_file.txt";
            String data = "Это данные для записи в файл";

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                writer.write(data);
                System.out.println("Данные записаны в файл: " + fileName);
            } catch (IOException e) {
                System.out.println("Ошибка при записи в файл: " + e.getMessage());
            }

            // Чтение данных из файла
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
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
