package lab7;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class lab7_task8 {

    public static class SerializationDemo {
        public static void main(String[] args) {
            String fileName = "student.dat";

            // Создание и запись объекта в файл
            lab7_task8_student.Student student = new lab7_task8_student.Student("Иван Иванов", 20, "Информатика", 4.7);

            try (ObjectOutputStream out =
                         new ObjectOutputStream(new FileOutputStream(fileName))) {
                out.writeObject(student);
                System.out.println("Объект сохранен в файл: " + fileName);
            } catch (IOException e) {
                System.out.println("Ошибка при записи объекта: " + e.getMessage());
            }

            // Восстановление объекта из файла
            try (ObjectInputStream in =
                         new ObjectInputStream(new FileInputStream(fileName))) {
                lab7_task8_student.Student restoredStudent = (lab7_task8_student.Student) in.readObject();

                System.out.println("Объект восстановлен из файла:");
                System.out.println("Имя: " + restoredStudent.getName());
                System.out.println("Возраст: " + restoredStudent.getAge());
                System.out.println("Факультет: " + restoredStudent.getFaculty());
                System.out.println("Средний балл: " + restoredStudent.getAverageGrade());
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Ошибка при восстановлении объекта: " + e.getMessage());
            }
        }
    }
}
