import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Konwerter {
    private JTextField kursTextField;
    private JButton przeliczButton;
    private JTextField kwota1TextField;
    private JTextField kwota2TextField;
    private JPanel Main;
    private JTextField NazwaW;
    double kwota1;
    double kwota2;
    double kurs;


    public Konwerter() {
        przeliczButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                kwota1= Double.parseDouble(String.valueOf((Double.parseDouble(kwota1TextField.getText()))))*1000000000;
                kwota2TextField.setText(String.valueOf(kwota1*Double.parseDouble(kursTextField.getText())/1000000000));

                kwota2TextField.setText(kwota2TextField.getText()+" "+NazwaW.getText());

                JOptionPane.showMessageDialog(null,("Kwota po przeliczeniu z "+ kwota1TextField.getText()+" PLN to \n"+ kwota2TextField.getText()));
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Konwerter");
        frame.setContentPane(new Konwerter().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
