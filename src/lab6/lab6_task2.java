package lab6;

public class lab6_task2 {
    public static class Example2 {

        public static void main(String[] args) throws InterruptedException {
            Thread t = new Thread(() -> {
                for (int i = 1; i <= 10; i++) {
                    System.out.println(i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            t.start();
            t.join();
        }
    }
}
