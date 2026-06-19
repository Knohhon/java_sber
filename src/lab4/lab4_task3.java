package lab4;

import java.util.InputMismatchException;
import java.util.Scanner;

public class lab4_task3 {
    public static class Task3 {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.print("Введите размер массива: ");
                int n = scanner.nextInt(); // здесь возможна InputMismatchException

                byte[] array = new byte[n];
                System.out.println("Введите " + n + " чисел типа byte (от -128 до 127):");
                for (int i = 0; i < n; i++) {
                    System.out.print("Элемент[" + i + "] = ");
                    array[i] = scanner.nextByte();
                    // здесь возможна InputMismatchException —
                    // и при вводе строки, и при вводе числа вне диапазона byte
                }

                int correctSum = 0; // контрольная сумма без потери точности (тип int)
                byte byteSum = 0;   // сумма в типе byte — может незаметно переполниться

                for (byte value : array) {
                    correctSum += value;
                    byteSum += value; // здесь возможно переполнение диапазона типа byte
                }

                // Экспериментально установлено: переполнение byte не бросает
                // исключение само по себе, поэтому проверяем результат вручную
                if (byteSum != correctSum) {
                    throw new ArithmeticException(
                            "Переполнение диапазона типа byte при вычислении суммы " +
                                    "(результат в byte = " + byteSum +
                                    ", фактическая сумма = " + correctSum + ")");
                }

                System.out.println("Сумма элементов массива: " + byteSum);

            } catch (InputMismatchException e) {
                System.out.println("Ошибка ввода: введите целое число в диапазоне " +
                        "типа byte (от -128 до 127).");
            } catch (ArithmeticException e) {
                System.out.println("Ошибка вычисления: " + e.getMessage());
            } catch (NegativeArraySizeException e) {
                System.out.println("Ошибка: размер массива не может быть отрицательным числом.");
            } finally {
                System.out.println("Вычисление суммы завершено (секция finally).");
                scanner.close();
            }
        }
    }
}
