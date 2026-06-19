package lab6;

public class lab6_task4 {
    public static class Example4 {

        public static void main(String[] args) {
            for (int i = 1; i <= 10; i++) {
                final int threadNumber = i;
                Thread t = new Thread(() -> {
                    System.out.println("Поток номер: " + threadNumber);
                });
                t.start();
            }
        }
    }
}
