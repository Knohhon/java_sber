package lab_4;

import java.util.Scanner;
import java.util.InputMismatchException;

public class lab4_task1 {
    public static class Task1 {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.print("Введите размер массива: ");
                int n = scanner.nextInt(); // здесь возможна InputMismatchException

                int[] array = new int[n];
                System.out.println("Введите " + n + " целых чисел:");
                for (int i = 0; i < n; i++) {
                    System.out.print("Элемент[" + i + "] = ");
                    array[i] = scanner.nextInt(); // здесь тоже возможна InputMismatchException
                }

                int sum = 0;
                int count = 0;
                for (int value : array) {
                    if (value > 0) {
                        sum += value;
                        count++;
                    }
                }

                // Если положительных элементов нет, count == 0,
                // и целочисленное деление бросит ArithmeticException ("/ by zero")
                int average = sum / count;
                System.out.println("Среднее значение положительных элементов: " + average);

            } catch (InputMismatchException e) {
                System.out.println("Ошибка ввода: введите, пожалуйста, целое число.");
            } catch (ArithmeticException e) {
                System.out.println("Ошибка вычисления: в массиве отсутствуют положительные " +
                        "элементы, среднее значение вычислить невозможно.");
            } catch (NegativeArraySizeException e) {
                System.out.println("Ошибка: размер массива не может быть отрицательным числом.");
            } finally {
                System.out.println("Работа с массивом завершена (секция finally).");
                scanner.close();
            }
        }
    }
}
