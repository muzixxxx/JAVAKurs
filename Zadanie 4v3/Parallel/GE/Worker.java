package Parallel.GE;
public class Worker extends Thread {

    private double[][] matrix;
    private int k;
    private int e;

    public Worker(double[][] matrix, int k, int e) {
        this.matrix = matrix;
        this.k = k;
        this.e = e;
    }
    // kroje na czesci;

    public void run() {

        int n = matrix.length;

        for(;k < e; k++){
            int index = k;
            double value = matrix[index][k];

            for (int x = k + 1; x < n; x++) {
                if (Math.abs(matrix[x][k]) > value) {
                    value = matrix[x][k];
                    index = x;
                }
            }

            if (k != index) {
                matrix = Main.swapRow(matrix, k, index);
            }

            // elimanacja do gornej trojkatnej
            for (int x = k + 1; x < n; x++) {
                double f = matrix[x][k] / matrix[k][k];
                for (int y = k + 1; y <= n; y++)
                    matrix[x][y] -= matrix[k][y] * f;
                // zerowanie
                matrix[x][k] = 0;
            }
        }
    }
}