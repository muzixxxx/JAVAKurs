package AWT;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AWT extends JFrame {




    public static void main(String[] args) {


        AWT m = new AWT();
        m.setDefaultCloseOperation(EXIT_ON_CLOSE);
        m.setSize(new Dimension(170, 200));

        JPanel main = new JPanel();

        JTextField godziny= new JTextField("godziny");


        JTextField minuty= new JTextField("minuty");
        JTextField sekundy= new JTextField("sekundy");


        main.add(godziny);main.add(minuty);main.add(sekundy);

        m.add(main);
        JLabel time = new JLabel("0");


        JButton startButton= new JButton("Start");
        main.add(startButton);
        JButton stopButton= new JButton("Stop");
        main.add(stopButton);
        JButton uruchomPonownieButton= new JButton("Uruchom Ponownie");
        main.add(uruchomPonownieButton);
        main.add(time);
        time.setBounds(new Rectangle(40,80,30,30));
        m.setVisible(true);


        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int hTemp=Integer.parseInt(godziny.getText());
                int mTemp=Integer.parseInt(minuty.getText());
                int sTemp=Integer.parseInt(sekundy.getText());

                Timer t = new Timer(time,godziny,minuty,sekundy,stopButton,uruchomPonownieButton,hTemp,mTemp,sTemp);
                Thread timer = new Thread(t);
                timer.start();
            }
        });











    }



}