package lab2;

import java.util.Random;
import java.util.ArrayList;

public class lab2_1 {
    public static void main(String[] args) {
        int size = 10;
        int[] arr = new int[size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(20); // числа от 0 до 19
        }

        // Выводим массив
        System.out.print("Массив: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println();

        int min = arr[0];
        for (int value : arr) {
            if (value < min) {
                min = value;
            }
        }

        // Собираем все индексы с минимальным значением
        ArrayList<Integer> minIndexes = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == min) {
                minIndexes.add(i);
            }
        }

        System.out.println("Минимальное значение: " + min);
        System.out.print("Индекс(ы) минимального элемента: ");
        for (int i = 0; i < minIndexes.size(); i++) {
            System.out.print(minIndexes.get(i));
            if (i < minIndexes.size() - 1) System.out.print(", ");
        }
        System.out.println();
    }
}
