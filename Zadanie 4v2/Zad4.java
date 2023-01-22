import java.util.Date;
import java.util.Scanner;

public class Zad4 {

    public static void main(String[] args) {

        //Date start = new Date();
        Scanner scan= new Scanner(System.in);
        // ogarnac klase statyczna generator pousuwac


        System.out.println("Podaj ilosc wierszy macierzy A: ");
        int aR = scan.nextInt();
        System.out.println("Podaj ilosc kolumn macierzy A: ");
        int aC = scan.nextInt();
        System.out.println("Podaj ilosc wierszy macierzy B: ");
        int bR = scan.nextInt();
        System.out.println("Podaj ilosc kolumn macierzy B: ");
        int bC = scan.nextInt();

        if (aC!=bR)
        {
            System.out.println("Niewłaściwe wymiary macierzy, nie można wykonać mnożenia");
        }

        System.out.println("Podaj macierz A \n");
        int[][] m1 = new int[aR][aC];

        for (int i = 0; i < aR; i++) {
            for (int j = 0; j < aC; j++) {

                m1[i][j] = scan.nextInt();
            }
        }

        System.out.println("\n Podaj macierz B \n");
        int[][] m2 = new int[bR][bC];

        for (int i = 0; i < bR; i++) {
            for (int j = 0; j < bC; j++) {

                m2[i][j] = scan.nextInt();
            }
        }


        int[][] result= new int [aR][bC];
        ParallelThreadsCreator.multiply(m1,m2,result);


        System.out.println("Matrix 1 : ");
        MatrixGeneratorUtil.print(m1);

        System.out.println("\nMatrix 2 : ");
        MatrixGeneratorUtil.print(m2);

        System.out.println("\nOutput Matrix : ");
        MatrixGeneratorUtil.print(result);

    }


}