package AWT;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Zad1 {
    public List<Person> listaKontaktow = new ArrayList<>();
    public String readFile(String fileName,Person person)
    {
        Path path= Paths.get(fileName);

        if(Files.exists(path)) {
            try {
                File myfile = new File(fileName);
                Scanner myScanner = new Scanner(myfile);
                while (myScanner.hasNextLine())
                {
                    String [] result=(myScanner.nextLine().split(";"));
                    Person p1 =new Person(result[0],result[1],result[2],result[3]);
                    listaKontaktow.add(p1);
                }
                myScanner.close();
                System.out.println("Wczytalem plik");
            }
            catch (FileNotFoundException e) {
                System.out.println("wystapil blad");
                e.printStackTrace();
            }
        }
        else {
            writeFIle(fileName, "");
            System.out.println("Wczytalem plik");
        }

        return "";
    }
    public void writeFIle (String fileName, String line)
    {
        try{
            FileWriter fw;
            fw = new FileWriter(fileName,true);
            fw.write(line);
            fw.close();
        }
        catch (IOException e)
        {
            System.out.println("wystapil blad");
            e.printStackTrace();
        }
    }
    public void display(Person person)
    {

        listaKontaktow.forEach(System.out::println);


    }
    public void search(Person person,String surname){
        for(int i=0;i<listaKontaktow.size();i++)
        {
            if( listaKontaktow.get(i).surname.equals(surname))
            {
                System.out.println(listaKontaktow.get(i));
            }
        }

    }
    public void create(String fileName,Person person)
    {
        listaKontaktow.add(person);
        listaKontaktow.forEach(System.out::println);
    }
    public void save (String fileName,Person person){

            String text;

            text=person.forename;
            text+= ";";
            text+= person.surname;
            text+= ";";
            text+= person.phone;
            text+= ";";
            text+= person.mail;
            text+= "\n";
        System.out.println(text);
            writeFIle(fileName,text);

    }
    public  void menu(String fileName,Person person) {

        String nrOpcji;
        while(true) {
            System.out.println("1 Wyświetlenie wszystkich wizytówek");
            System.out.println("2 Dodanie nowej wizytówki");
            System.out.println("3 Wyświetlenie wizytówki dla osób o określonym nazwisku");
            System.out.println("0 Zakończenie działania programu");
            System.out.println("Podaj nr opcji");
            Scanner scan = new Scanner(System.in);
            nrOpcji = scan.nextLine();


            switch (nrOpcji) {
                case "1":
                    display(person);
                    break;
                case "2":
                    create(fileName,person);
                    break;
                case "3":
                    System.out.println("Podaj Nazwisko");
                    String surname =scan.nextLine();
                    search(person,surname);
                case "4":
                    System.out.println("Zapisz");
                    save(fileName,person);

                    break;
                case "0":
                    System.out.println("Koniec programu");
                    return;

            }
        }


    }
    public  void main(String[] args) throws IOException {

        Zad1 main= new Zad1();
        Person person =new Person();
        /*
        while(true)
        {

            System.out.println("Podaj nazwe pliku do wczytania");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String fileName = reader.readLine();
            main.readFile(fileName,person);
            System.out.println("wczytałem plik "+fileName);
            main.menu(fileName,person);
            return;
        }

         */
    }
}