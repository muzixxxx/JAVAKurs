package zadanie4v1;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Main extends Thread {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Podaj długość tablicy");
        Scanner scan = new Scanner(System.in);
        int liczba = scan.nextInt();
        System.out.println("Podaj ilosc tablic");
        int iloscTablic = scan.nextInt();
        Random generator = new Random();
        int[] tablica;
        tablica = new int[liczba];
        AtomicInteger suma = new AtomicInteger();


        Runnable r = () -> {

            for (int i = 0; i < liczba; i++) {

                tablica[i] = generator.nextInt(100);;


                suma.addAndGet(tablica[i]);


                System.out.println("suma" + suma);
                System.out.println("Watek nr: " + Thread.currentThread().getName());

            }

        };
        for (int i=0;i<iloscTablic;i++)
        {
            Thread t1 =new Thread(r);
            t1.start();

            if (i==iloscTablic-1)
                Thread.sleep(1000);
                System.out.println("koncowa suma:        "+suma);
        }



    }
}