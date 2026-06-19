package lab_4;

public class lab4_ex12 {
    public static class Main {
        public static void m(String str, double chislo) {
            if (str == null) {
                throw new IllegalArgumentException("Строка введена неверно");
            }
            if (chislo > 0.001) {
                throw new IllegalArgumentException("Неверное число");
            }
            System.out.println("Аргументы корректны");
        }

        public static void main(String[] args) {
            try {
                m(null, 0.000001);
            } catch (IllegalArgumentException e) {
                System.out.println("Перехвачено исключение: " + e.getMessage());
            }
        }
    }
}
