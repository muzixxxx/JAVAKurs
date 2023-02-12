package Parallel.GE;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final boolean randomTests = true;



    private static final int VALUE_RANGE = 10;


    public static double[][] swapRow(double[][] matrix, int x, int y) {
        for (int k = 0; k <= matrix.length; k++) {
            double temp = matrix[x][k];
            matrix[x][k] = matrix[y][k];
            matrix[y][k] = temp;
        }
        return matrix;
    }


    public static double[][] gaussElim(double[][] matrix){
        int n = matrix.length;
        for (int k = 0; k < n; k++){
            int index = k;
            double value = matrix[index][k];

            // find row for pivot
            for (int x = k+1; x < n; x++) {
                if (Math.abs(matrix[x][k]) > value) {
                    value = matrix[x][k];
                    index = x;
                }
            }
            matrix = swapRow(matrix, k, index);

            // eliminate elements to form triangular matrix
            for (int x = k + 1; x < n; x++){
                double f = matrix[x][k] / matrix[k][k];
                for (int y = k + 1; y <= n; y++)
                    matrix[x][y] -= matrix[k][y]*f;
                matrix[x][k] = 0;
            }
        }
        return matrix;
    }

    public static double[] backSub(double matrix[][]) {

        int n = matrix.length;
        double sol[] = new double[n];

        // work up solutions from the bottom row with substitution
        for (int x = n - 1; x >= 0; x--){
            sol[x] = matrix[x][n];
            for (int y=x+1; y<n; y++){
                sol[x] = sol[x] - matrix[x][y] * sol[y];
            }
            sol[x] = sol[x] / matrix[x][x];
        }
        return sol;
    }

    public static void printMatrix(double[][] m){
        for(int x = 0; x < m.length; x++ ){
            for(int y = 0; y < m[0].length; y++){
                System.out.print(m[x][y] + " ");
            }
            System.out.println();
        }
    }


     // Tworzenie random matrix

    public static double[][] randMatrix(int rows, int cols){
        double[][] matrix = new double[rows][cols];
        for(int x = 0; x < rows; x++){
            for(int y = 0; y < cols; y++){
                matrix[x][y] = (int)(Math.random() * VALUE_RANGE);
            }
        }
        return matrix;
    }

     // mozemy przetestowac solucje i zapetlic
    public static boolean checkSolution(double[][] matrix, double[] sol) {
        int n = matrix[0].length;
        for(int x = 0; x < matrix.length; x++){
            double sum = 0;
            for(int y = 0; y < n - 1; y++){
                sum += matrix[x][y]*sol[y];
            }
            if(sum - matrix[x][n - 1] <= -0.1 || sum - matrix[x][n - 1] >= 0.1){
                return false;
            }
        }
        return true;
    }

    public static void normalGauss(double[][] matrix, int n){
        printMatrix(matrix);

        final double startTime = System.nanoTime();
        double[][] M = gaussElim(matrix);
        double[] solution = backSub(matrix);

        final double duration = (System.nanoTime() - startTime) / 1000000;


            System.out.println("Triangular matrix:\n");
            printMatrix(matrix);
            System.out.println("\nSolutions:");
            for(int x = 0; x < solution.length; x++){
                System.out.println(solution[x]);
            }

        System.out.println("\n" + duration + "ms passed normal Gauss");
    }

    public static void parallelGauss(double[][] matrix, int n){

         printMatrix(matrix);

        final double startTime = System.nanoTime();
        int p = Runtime.getRuntime().availableProcessors();
        int div = n / p;
        int remainder = n % p;

        ArrayList<Worker> threads = new ArrayList<>();
        for(int k = 0; k < p; k++){
            if(k == p - 1)
                threads.add(new Worker(matrix, k * div, (k+1) * div + remainder));
            else
                threads.add(new Worker(matrix, k * div, (k+1) * div));
            threads.get(k).run();
        }
        double[] solution = backSub(matrix);
        final double duration = (System.nanoTime() - startTime) / 1000000; // converts to ms


            System.out.println("Triangular matrix:\n");
            printMatrix(matrix);
            System.out.println("\nSolutions:");
            for(int x = 0; x < solution.length; x++){
                System.out.println(solution[x]);
            }

        System.out.println("\n" + duration + "ms passed parallel");
        if(checkSolution(matrix, solution)) System.out.println("solution is correct\n");

    }

    public static void main(String[] args) {



            System.out.println("Write size matrix: ");

        Scanner sc = new Scanner(System.in);
        int n;
        n=sc.nextInt();
        System.out.println("`Matrix` "+n+" x "+n);

                double[][] matrix = randMatrix(n, n + 1);
                parallelGauss(matrix, n);



//            }

    }
}