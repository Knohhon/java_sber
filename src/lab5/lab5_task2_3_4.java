package lab5;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class lab5_task2_3_4 {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        Random random = new Random();

        // --- Задание 1: чётные числа ---
        System.out.println("Введите размер массива для задания 1");
        int size1 = in.nextInt();

        int[] arr = new int[size1];
        for (int i = 0; i < size1; i++) {
            arr[i] = random.nextInt();
        }

        System.out.println("\nЗадание 1");
        System.out.println("Массив arr : ");
        System.out.println(Arrays.toString(arr));

        int[] arrResult = filterEvenNumbers(arr);

        System.out.println("Массив arrResult (чётные) :");
        System.out.println(Arrays.toString(arrResult));

        // --- Задание 2: общие элементы двух массивов ---
        System.out.println("\nВведите размер массивов для задания 2");
        int size2 = in.nextInt();

        int[] arr1 = new int[size2];
        int[] arr2 = new int[size2];
        for (int i = 0; i < size2; i++) {
            arr1[i] = random.nextInt(200);
            arr2[i] = random.nextInt(200);
        }

        System.out.println("\nЗадание 2");
        System.out.println("Массив arr1 : ");
        System.out.println(Arrays.toString(arr1));
        System.out.println("Массив arr2 : ");
        System.out.println(Arrays.toString(arr2));

        int[] commonResult = findCommonElements(arr1, arr2);

        System.out.println("Массив arrResult (общие элементы) :");
        System.out.println(Arrays.toString(commonResult));

        // --- Задание 3: строки с заглавной буквы ---
        String text = "Напишите функцию, которая принимает на вход список " +
                "строк и возвращает новый список, содержащий только те строки, " +
                "которые начинаются с большой буквы.";

        List<String> strings = List.of(text.split(" "));

        System.out.println("\nЗадание 3");
        System.out.println("Список строк после сплитования : ");
        System.out.println(strings);

        List<String> stringsAfter = filterCapitalizedStrings(strings);

        System.out.println("Список строк, начинающихся с большой буквы :");
        System.out.println(stringsAfter);
    }

    public static int[] filterEvenNumbers(int[] arr) {
        return Arrays.stream(arr)
                .filter(x -> x % 2 == 0)
                .toArray();
    }

    public static int[] findCommonElements(int[] arr1, int[] arr2) {
        return Arrays.stream(arr1)
                .filter(x -> Arrays.stream(arr2).anyMatch(y -> y == x))
                .toArray();
    }

    public static List<String> filterCapitalizedStrings(List<String> list) {
        return list.stream()
                .filter(s -> !s.isEmpty() && Character.isUpperCase(s.charAt(0)))
                .collect(Collectors.toList());
    }


}
