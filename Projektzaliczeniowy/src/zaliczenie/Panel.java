package zaliczenie;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panel  {
    public Panel(JFrame frame){


        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                TicTacToe tic = new TicTacToe();



                tic.start(podajIpTextField.getText(),podajPortTextField.getText(),colorBox.getSelectedIndex(),colorBoxEnemy.getSelectedIndex());


                frame.setVisible(false);





            }
        });
        defaultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                podajIpTextField.setText("localhost");
                podajPortTextField.setText("22222");
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                System.exit(0);
            }
        });
    }

    public JButton startButton;


    public JTextField podajIpTextField;
    public JTextField podajPortTextField;
    public JButton closeButton;
    public JPanel panelTic;
    private JButton defaultButton;
    private JComboBox colorBox;
    private JComboBox colorBoxEnemy;
    private JTextPane colorXTextPane;
    private JTextPane colorCircleTextPane;


}
