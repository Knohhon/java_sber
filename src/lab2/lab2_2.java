package lab2;

public class lab2_2 {
    public static void main(String[] args) {
        int rows = 6;
        int cols = 6;
        int[][] matrix = new int[rows][cols];

        int num = 1;

        int top = 0, bottom = rows - 1;
        int left = 0, right = cols - 1;

        while (num <= rows * cols) {
            if (top <= bottom) {
                for (int col = left; col <= right; col++) {
                    matrix[top][col] = num++;
                }
                top++;
            }

            if (left <= right) {
                for (int row = top; row <= bottom; row++) {
                    matrix[row][right] = num++;
                }
                right--;
            }

            if (top <= bottom) {
                for (int col = right; col >= left; col--) {
                    matrix[bottom][col] = num++;
                }
                bottom--;
            }

            if (left <= right) {
                for (int row = bottom; row >= top; row--) {
                    matrix[row][left] = num++;
                }
                left++;
            }
        }

        System.out.println("Матрица, заполненная змейкой:");
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.printf("%4d", val);
            }
            System.out.println();
        }
    }
}
