package lab5;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class lab5_task5 {

    public static class Example4 {
        public static void main(String[] args) {
            List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, -6, 7);

            System.out.println("Исходный список : " + numbers);

            List<Integer> squares = squareNumbers(numbers);

            System.out.println("Список квадратов : " + squares);
        }

        public static List<Integer> squareNumbers(List<Integer> list) {
            return list.stream()
                    .map(x -> x * x)
                    .collect(Collectors.toList());
        }
    }
}
