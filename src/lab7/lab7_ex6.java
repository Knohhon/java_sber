package lab7;

import java.io.*;

public class lab7_ex6 {

    public static class PrintWriterExample {
        public static void main(String[] args) {
            String inputFileName = "src/lab7/input6.txt";
            String outputFileName = "src/lab7/output6.txt";

            try (BufferedReader bufferedReader =
                         new BufferedReader(new FileReader(inputFileName));
                 PrintWriter printWriter =
                         new PrintWriter(outputFileName, "UTF-8")) {

                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    printWriter.println(line.toUpperCase());
                }

                System.out.println("Данные записаны в файл: " + outputFileName);
            } catch (IOException e) {
                System.out.println("Ошибка при чтении/записи файла: " + e.getMessage());
            }
        }
    }
}
