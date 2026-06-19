package lab6;

public class lab6_task5 {
    public static class Example5 {

        public static int findMax(int[] array) throws InterruptedException {
            int threadCount = Runtime.getRuntime().availableProcessors();
            Thread[] threads = new Thread[threadCount];
            int[] localMax = new int[threadCount];

            int chunkSize = (int) Math.ceil((double) array.length / threadCount);

            for (int t = 0; t < threadCount; t++) {
                final int threadIndex = t;
                final int start = threadIndex * chunkSize;
                final int end = Math.min(start + chunkSize, array.length);

                threads[t] = new Thread(() -> {
                    if (start >= end) {
                        localMax[threadIndex] = Integer.MIN_VALUE;
                        return;
                    }
                    int max = array[start];
                    for (int i = start + 1; i < end; i++) {
                        if (array[i] > max) {
                            max = array[i];
                        }
                    }
                    localMax[threadIndex] = max;
                });

                threads[t].start();
            }

            for (Thread thread : threads) {
                thread.join();
            }

            int result = Integer.MIN_VALUE;
            for (int max : localMax) {
                if (max > result) {
                    result = max;
                }
            }

            return result;
        }

        public static void main(String[] args) throws InterruptedException {
            int[] array = {5, 23, 1, 42, 17, 8, 99, 3, 56, 27, 11, 64};
            int max = findMax(array);
            System.out.println("Доступно ядер: " + Runtime.getRuntime().availableProcessors());
            System.out.println("Максимальный элемент: " + max);
        }
    }
}
