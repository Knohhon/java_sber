import java.util.Arrays;
import java.util.Scanner;

public class timus_1935 {

    public static class Main {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int n = Integer.parseInt(scanner.nextLine().trim());

            int[] a = new int[n];
            String[] parts = scanner.nextLine().trim().split("\\s+");
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(parts[i]);
            }

            // Сортируем по убыванию: самые "требовательные" шкурки кладём по краям,
            // чтобы их требование не суммировалось дважды с соседями
            Integer[] boxed = new Integer[n];
            for (int i = 0; i < n; i++) boxed[i] = a[i];
            Arrays.sort(boxed, (x, y) -> y - x);

            long total = 0;

            // Расстояние от левой обложки до первой (самой требовательной) шкурки
            total += boxed[0];

            // Расстояния между соседними шкурками = max двух соседних значений,
            // а после сортировки по убыванию max(boxed[i], boxed[i+1]) == boxed[i]
            for (int i = 0; i < n - 1; i++) {
                total += boxed[i];
            }

            // Расстояние от последней (наименее требовательной) шкурки до правой обложки
            total += boxed[n - 1];

            System.out.println(total);
        }
    }
}
