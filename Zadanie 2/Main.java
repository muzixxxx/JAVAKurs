
import java.io.*;
import java.nio.file.*;
import java.util.Scanner;
public class Main
{
    public static <T> void main(String[] args)
    {

        Scanner scan = new Scanner(System.in);



        Complex a = new Complex<Float>(-1f, 4f,-1f,4f);
        Complex b = new Complex<Float>(4f,3f,4f,3f);

        System.out.println(a.toString());

        System.out.println("+");
        System.out.println(b.toString());
        a.add(b);

        //dodwanie dziala
        System.out.print("wynik: ");
        System.out.println(a);
        System.out.print(" \n");
        System.out.println("typ\n"+a.printTypes());

        System.out.println("odejmowanie\n");

        System.out.println(a.toString()+"\n");

        System.out.println("-");
        System.out.println(b.toString());
        a.subtract(b);

        System.out.print("wynik: "+a.toString()+"\n");

        System.out.print("Mnozenie: \n");
        System.out.println(a.toString()+"\n * \n"+b.toString());
        a.multiply(b);
        System.out.print("wynik : "+a.toString()+"\n");


        System.out.print("Dzielenie: \n");
        System.out.println(a.toString());
        System.out.print(":\n");
        System.out.print(b.toString()+"\n");
        a.divide(b);
        System.out.println("typ\n"+a.printTypes());



        System.out.println("wynik: "+a.toString());

        System.out.println();

       Complex<Integer> x = new Complex<Integer>(1, 2,1,2);
        Complex<Float> y = new Complex<Float>(3.0f, 4.0f,3.0f,4.0f);

        System.out.println( "x -> " + x.printTypes() );
        System.out.println( "y -> " + y.printTypes() );
        x.add(y);
        System.out.println(x.toString());
        System.out.println("x -> " + x.printTypes() );

        //Complex<String> z = new Complex<String>(); //Chyba tego nie chcemy...

        ComplexInteger q = new ComplexInteger(1,2);
        ComplexFloat w = new ComplexFloat(8f, 4f);

        System.out.println();

        System.out.println( "q -> " + q.printTypes() );
        System.out.println( "w -> " + w.printTypes() );
        q.add(w);
        System.out.println(q.toString());
        System.out.println("q -> " + q.printTypes() );


    }
}