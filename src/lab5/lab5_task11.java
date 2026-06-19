package lab5;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class lab5_task11 {

    public static class Example11 {
        public static void main(String[] args) {
            List<Integer> numbers = Arrays.asList(3, 8, 15, 1, 22, 9, 30);
            int threshold = 10;

            System.out.println("Исходный список : " + numbers);

            List<Integer> result = filterLessThan(numbers, threshold);

            System.out.println("Список чисел меньше " + threshold + " : " + result);
        }

        public static List<Integer> filterLessThan(List<Integer> list, int threshold) {
            return list.stream()
                    .filter(x -> x < threshold)
                    .collect(Collectors.toList());
        }
    }
}
