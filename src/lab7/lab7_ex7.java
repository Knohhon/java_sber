package lab7;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class lab7_ex7 {

    public static class SerializationExample {
        public static void main(String[] args) {
            lab7_ex7_person.Person person = new lab7_ex7_person.Person("Ivan Ivanov", 30);

            try {
                FileOutputStream fileOut =
                        new FileOutputStream("src/lab7/person7.json");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(person);
                out.close();
                fileOut.close();
                System.out.println("Serialized data is saved in person.ser");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
