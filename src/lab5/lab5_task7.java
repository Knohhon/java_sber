package lab5;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class lab5_task7 {

    public static class Example6 {
        public static void main(String[] args) {
            List<Integer> numbers = Arrays.asList(10, 15, 20, 23, 30, 7, 9);
            int divisor = 5;

            System.out.println("Исходный список : " + numbers);

            List<Integer> result = filterDivisibleBy(numbers, divisor);

            System.out.println("Список чисел, делящихся на " + divisor + " : " + result);
        }

        public static List<Integer> filterDivisibleBy(List<Integer> list, int divisor) {
            return list.stream()
                    .filter(x -> x % divisor == 0)
                    .collect(Collectors.toList());
        }
    }
}
