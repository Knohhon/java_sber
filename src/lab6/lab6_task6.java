package lab6;

public class lab6_task6 {
    public static class Example6 {

        public static long sumArray(int[] array) throws InterruptedException {
            int threadCount = Runtime.getRuntime().availableProcessors();
            Thread[] threads = new Thread[threadCount];
            long[] localSum = new long[threadCount];

            int chunkSize = (int) Math.ceil((double) array.length / threadCount);

            for (int t = 0; t < threadCount; t++) {
                final int threadIndex = t;
                final int start = threadIndex * chunkSize;
                final int end = Math.min(start + chunkSize, array.length);

                threads[t] = new Thread(() -> {
                    long sum = 0;
                    for (int i = start; i < end; i++) {
                        sum += array[i];
                    }
                    localSum[threadIndex] = sum;
                });

                threads[t].start();
            }

            for (Thread thread : threads) {
                thread.join();
            }

            long totalSum = 0;
            for (long sum : localSum) {
                totalSum += sum;
            }

            return totalSum;
        }

        public static void main(String[] args) throws InterruptedException {
            int[] array = {5, 23, 1, 42, 17, 8, 99, 3, 56, 27, 11, 64};
            long sum = sumArray(array);
            System.out.println("Доступно ядер: " + Runtime.getRuntime().availableProcessors());
            System.out.println("Сумма элементов: " + sum);
        }
    }
}
