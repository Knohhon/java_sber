package lab5;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class lab5_task6 {

    public static class Example5 {
        public static void main(String[] args) {
            List<String> strings = Arrays.asList("apple", "banana", "grape", "pineapple", "kiwi");
            String substring = "apple";

            System.out.println("Исходный список : " + strings);

            List<String> result = filterContainingSubstring(strings, substring);

            System.out.println("Список, содержащий подстроку \"" + substring + "\" : " + result);
        }

        public static List<String> filterContainingSubstring(List<String> list, String substring) {
            return list.stream()
                    .filter(s -> s.contains(substring))
                    .collect(Collectors.toList());
        }
    }
}
