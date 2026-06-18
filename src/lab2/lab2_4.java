package lab2;

public class lab2_4 {

    public static class Person {

        private String name;
        private int age;
        private String gender;


        public Person(String name, int age, String gender) {
            this.name = name;
            this.age = age;
            this.gender = gender;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public String getGender() {
            return gender;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(int age) {
            if (age >= 0) {
                this.age = age;
            } else {
                System.out.println("Возраст не может быть отрицательным!");
            }
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        @Override
        public String toString() {
            return "Person { имя='" + name + "', возраст=" + age + ", пол='" + gender + "' }";
        }

        public static void main(String[] args) {

            Person person1 = new Person("Алексей", 25, "Мужской");
            Person person2 = new Person("Мария", 30, "Женский");

            System.out.println(person1);
            System.out.println(person2);

            System.out.println();

            System.out.println("Имя: "    + person1.getName());
            System.out.println("Возраст: " + person1.getAge());
            System.out.println("Пол: "    + person1.getGender());

            System.out.println();

            person1.setName("Александр");
            person1.setAge(26);
            System.out.println("После изменения: " + person1);

            System.out.println();

            person2.setAge(-5);
        }
    }

}
