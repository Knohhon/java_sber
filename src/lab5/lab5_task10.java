package lab5;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class lab5_task10 {

    public static class Example10 {
        public static void main(String[] args) {
            List<String> strings = Arrays.asList("hello", "world123", "Java!", "test", "abc_def");

            System.out.println("Исходный список : " + strings);

            List<String> result = filterOnlyLetters(strings);

            System.out.println("Список строк только из букв : " + result);
        }

        public static List<String> filterOnlyLetters(List<String> list) {
            return list.stream()
                    .filter(s -> s.chars().allMatch(Character::isLetter))
                    .collect(Collectors.toList());
        }
    }
}
