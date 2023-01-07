package AWT;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.AttributedCharacterIterator;

public class ControlsSample
{
    public static void main(String[] args)
    {
        Zad1 zad1= new Zad1();
        Person person =new Person();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Frame f = new Frame("Kontrolki AWT");
        f.setSize(800, 600);
        f.setLayout(null);
        f.setResizable(false);
        f.setVisible(true);
        f.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                f.dispose();
            }
        });



        // Label
        Label label = new Label("Wprowadź dane i wcisnij enter");
        label.setBounds(20, 50, 200, 20);
        label.setBackground(Color.orange);
        label.setForeground(Color.red);
        f.add(label);

        // TextField
        TextField textField = new TextField();

        TextField textField5= new TextField();
        textField5.setBounds(100, 80, 400, 30);

        textField5.setText("Nazwa pliku do wczytania");
        f.add(textField5);

        Button buttonWczytaj = new Button("Wczytaj");
        buttonWczytaj.setBounds(300,110 , 150, 60);
        f.add(buttonWczytaj);

        buttonWczytaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zad1.readFile(textField5.getText(),person);

            }
        });

        textField5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println(textField5.getText());

            }
        });



        textField.setBounds(100, 200, 400, 30);
        textField.setText(person.forename);
        f.add(textField);

        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println(textField.getText());

            }
        });


        TextField textField2 = new TextField();
        textField2.setBounds(100, 230, 400, 30);
        textField2.setText(person.surname);
        f.add(textField2);

        textField2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println(textField2.getText());

            }
        });

        TextField textField3= new TextField();
        textField3.setBounds(100, 260, 400, 30);
        textField3.setText(person.phone);
        f.add(textField3);

        textField3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println(textField3.getText());

            }
        });

        TextField textField4= new TextField();

        textField4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println(textField4.getText());

            }
        });

        textField4.setBounds(100, 290, 400, 30);
        textField4.setText(person.mail);
        f.add(textField4);
        // Button
        int width = 200;
        int height = 50;
        int x = (800 - width)/2;
        int y = 400;



        // MenuBar, Menu & MenuItem
        MenuBar menubar = new MenuBar();
        Menu menu1 = new Menu("Plik");




        Menu Show = new Menu("Wyświetl");

        MenuItem wczytaj = new MenuItem("Wczytaj");
        MenuItem zapisz = new MenuItem("Zapisz");

        MenuItem showall = new MenuItem("Pokaż Wszystkie Osoby");


        menu1.add(wczytaj);menu1.add(zapisz);
        menubar.add(menu1);
        menubar.add(Show);
        Show.add(showall);

        f.setMenuBar(menubar);

        wczytaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zad1.readFile(textField5.getText(),person);



            }
        });
        zapisz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Person person = new Person(textField.getText(),textField2.getText(),textField3.getText(),textField4.getText());
                zad1.save(textField5.getText(),person);



            }
        });

        showall.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zad1.display(person);
            }
        });


        Button button = new Button("Dodaj Osobe");
        button.setBounds(x, y, width, height);
        f.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text;
                Person person = new Person(textField.getText(),textField2.getText(),textField3.getText(),textField4.getText());
                zad1.create(textField5.getText(),person);
                person.listaKontaktow.forEach(System.out::println);




            }
        });


        // PopUp Menu




        // Dialog
        Dialog dialog = new Dialog(f, "Okno dialogowe", true);
        dialog.setSize(250, 250);
        dialog.setResizable(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        dialog.setLocation((int)(screenSize.getWidth()/2 - 125), (int)(screenSize.getHeight()/2 - 125));
        dialog.setVisible(false);







    }



}