package AWT;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Timer implements Runnable{


    JTextField h;
    JTextField m;
    JTextField s;
    JLabel t;
    JButton stopButton;
    JButton uruchomPonownieButton;
    int hTemp;
    int mTemp;
    int sTemp;
    Boolean stop=false;



    public Timer(JLabel time,JTextField godziny,JTextField minuty,JTextField sekundy,JButton stopButton,JButton uruchomPonownieButton,int hTemp,int mTemp,int sTemp) {
        this.t=time;
        this.h=godziny;
        this.m=minuty;
        this.s= sekundy;
        this.stopButton=stopButton;
        this.uruchomPonownieButton= uruchomPonownieButton;
        this.hTemp=hTemp;
        this.mTemp=mTemp;
        this.sTemp=sTemp;

    }

    @Override
    public void run() {
        stop=false;

        while(Integer.parseInt(s.getText())>-1){
            stopButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    stop=true;
                }
            });


            uruchomPonownieButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    h.setText(String.valueOf(hTemp));
                    m.setText(String.valueOf(mTemp));
                    s.setText(String.valueOf(sTemp));
                    t.setText("0");
                    stop=true;
                }
            });


            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
            int current = Integer.parseInt(t.getText());
            int minuseS =Integer.parseInt(s.getText());
            int minuseM =Integer.parseInt(m.getText());
            int minuseH=Integer.parseInt(h.getText());
            if(stop==true) break;
            t.setText((current+1)+"");
            if(minuseS==0)
            {
                if(minuseH>0|| minuseM>0){
                    minuseS=60;
                    if(minuseM==0)
                    {
                        minuseM=60;

                    }
                    if (minuseM>0)
                        minuseM=minuseM-1;
                    if (minuseH>0)
                        minuseH=minuseH-1;

                    m.setText((minuseM)+"");
                    h.setText((minuseH)+"");

                }
                else{
                    JOptionPane.showMessageDialog(null, "Czas sie skonczyl", "Message", JOptionPane.INFORMATION_MESSAGE);
                }


            }
            if(minuseS>0)
                s.setText((minuseS-1)+"");
            else break;

                /*if(current%10==0 && current!=0)
                    JOptionPane.showMessageDialog(null, "Minelo kolejne 10 sekund", "Message", JOptionPane.INFORMATION_MESSAGE);
                    */

        }

    }

}
