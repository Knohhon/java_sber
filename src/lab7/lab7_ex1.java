package lab7;

import java.io.File;
import java.io.IOException;

public class lab7_ex1 {

    public static class FileExample {
        public static void main(String[] args) {
            // Создание папки
            File folder = new File("src/lab7/example1_folder");
            if (!folder.exists()) {
                if (folder.mkdir()) {
                    System.out.println("Папка создана: " + folder.getAbsolutePath());
                } else {
                    System.out.println("Не удалось создать папку: " + folder.getAbsolutePath());
                }
            } else {
                System.out.println("Папка уже существует: " + folder.getAbsolutePath());
            }

            // Создание файла внутри папки
            File file = new File(folder.getAbsolutePath() +
                    File.separator +
                    "example_file.txt");

            try {
                if (file.createNewFile()) {
                    System.out.println("Файл создан: " + file.getAbsolutePath());
                } else {
                    System.out.println("Не удалось создать файл: " + file.getAbsolutePath());
                }
            } catch (IOException e) {
                System.out.println("Ошибка при создании файла: " + e.getMessage());
            }

            // Удаление файла и папки
            if (file.delete()) {
                System.out.println("Файл удален: " + file.getAbsolutePath());
            } else {
                System.out.println("Не удалось удалить файл: " + file.getAbsolutePath());
            }

            if (folder.delete()) {
                System.out.println("Папка удалена: " + folder.getAbsolutePath());
            } else {
                System.out.println("Не удалось удалить папку: " + folder.getAbsolutePath());
            }
        }
    }
}
