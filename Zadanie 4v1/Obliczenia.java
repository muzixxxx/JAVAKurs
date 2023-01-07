import java.util.Random;

public class Obliczenia extends Thread {

    public static int suma;
    public int liczba;
    Random generator = new Random();
    int[] tablica;
    public  Obliczenia (int liczba,int suma)
    {
        this.liczba=liczba;
        this.suma=suma;
    }

   public void run() {

        tablica = new int[liczba];
        for (int i = 0; i < liczba; i++) {

            tablica[i] = generator.nextInt(100);
            suma+=tablica[i];

            System.out.println("suma"+suma);
            System.out.println("Watek nr: "+ Thread.currentThread().getName());
        }
    }
}
