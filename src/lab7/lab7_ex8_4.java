package lab7;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class lab7_ex8_4 {

    public static class FileCopier {
        public static void main(String[] args) throws IOException {
            FileReader reader = new FileReader("src/lab7/input6.txt");
            FileWriter writer = new FileWriter("src/lab7/output8.txt");

            int c;
            while ((c = reader.read()) != -1) {
                writer.write(c);
            }

            reader.close();
            writer.close();

            System.out.println("Файл скопирован успешно!");
        }
    }
}
