package lab4;

import java.util.InputMismatchException;
import java.util.Scanner;

public class lab4_task2 {
    public static class Task2 {
        public static void main(String[] args) {
            int[][] matrix = {
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12}
            };

            Scanner scanner = new Scanner(System.in);
            try {
                System.out.println("Матрица:");
                for (int[] row : matrix) {
                    for (int value : row) {
                        System.out.print(value + "\t");
                    }
                    System.out.println();
                }

                System.out.print("Введите номер столбца для вывода: ");
                int col = scanner.nextInt(); // здесь возможна InputMismatchException

                System.out.println("Столбец № " + col + ":");
                for (int[] row : matrix) {
                    System.out.println(row[col]); // здесь возможна ArrayIndexOutOfBoundsException
                }

            } catch (InputMismatchException e) {
                System.out.println("Ошибка ввода: номер столбца должен быть целым числом.");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Ошибка: столбца с таким номером не существует. " +
                        "Допустимые номера столбцов: от 0 до " + (matrix[0].length - 1) + ".");
            } finally {
                System.out.println("Вывод столбца завершён (секция finally).");
                scanner.close();
            }
        }
    }
}
