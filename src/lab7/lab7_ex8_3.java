package lab7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class lab7_ex8_3 {

    public static class FileLineCounter {
        public static void main(String[] args) throws IOException {
            BufferedReader reader =
                    new BufferedReader(new FileReader("src/lab7/input6.txt"));

            int lineCount = 0;
            while (reader.readLine() != null) {
                lineCount++;
            }
            reader.close();

            System.out.println("Количество строк в файле: " + lineCount);
        }
    }
}
