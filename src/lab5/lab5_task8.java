package lab5;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class lab5_task8 {

    public static class Example7 {
        public static void main(String[] args) {
            List<String> strings = Arrays.asList("hi", "hello", "world", "Java", "programming");
            int minLength = 4;

            System.out.println("Исходный список : " + strings);

            List<String> result = filterByMinLength(strings, minLength);

            System.out.println("Список строк длиной больше " + minLength + " : " + result);
        }

        public static List<String> filterByMinLength(List<String> list, int minLength) {
            return list.stream()
                    .filter(s -> s.length() > minLength)
                    .collect(Collectors.toList());
        }
    }
}
