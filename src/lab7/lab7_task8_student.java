package lab7;

import java.io.Serializable;

public class lab7_task8_student {
    public static class Student implements Serializable {
        private static final long serialVersionUID = 1L;

        private String name;
        private int age;
        private String faculty;
        private double averageGrade;

        public Student(String name, int age, String faculty, double averageGrade) {
            this.name = name;
            this.age = age;
            this.faculty = faculty;
            this.averageGrade = averageGrade;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public String getFaculty() {
            return faculty;
        }

        public double getAverageGrade() {
            return averageGrade;
        }
    }
}
