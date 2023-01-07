import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
    private JPanel Calculator;
    private JTextField txDisplay;
    private JButton button1;
    private JButton button2;
    private JButton xButton;
    private JButton button4;
    private JButton ACButton;
    private JButton delButton;
    private JButton a7Button;
    private JButton a4Button;
    private JButton a1Button;
    private JButton a00Button;
    private JButton button11;
    private JButton a8Button;
    private JButton a5Button;
    private JButton a2Button;
    private JButton a0Button;
    private JButton button16;
    private JButton a9Button;
    private JButton a6Button;
    private JButton a3Button;
    private JButton button20;
    double x;
    double y;
    double result;
    String op;

    public Calculator() {
        ACButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txDisplay.setText(null);
            }
        });
        a7Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txDisplay.setText(txDisplay.getText()+"7");
            }
        });
        a6Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txDisplay.setText(txDisplay.getText()+"6");
            }
        });
        a1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txDisplay.setText(txDisplay.getText()+"1");
            }
        });
        a2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txDisplay.setText(txDisplay.getText()+"2");
            }
        });
        a3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txDisplay.setText(txDisplay.getText()+"3");
            }
        });
        a4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txDisplay.setText(txDisplay.getText()+"4");
            }
        });
        a5Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txDisplay.setText(txDisplay.getText()+"5");
            }
        });
        a8Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txDisplay.setText(txDisplay.getText()+"8");
            }
        });
        a9Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txDisplay.setText(txDisplay.getText()+"9");
            }
        });
        a0Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txDisplay.setText(txDisplay.getText()+"0");
            }
        });
        a00Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txDisplay.setText(txDisplay.getText()+"00");
            }
        });
        button16.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(txDisplay.getText().contains("."))
                {
                    double pm=Double.parseDouble(txDisplay.getText());
                    pm=pm*-1;
                    txDisplay.setText(String.valueOf(pm));
                }
                else
                {
                    long PM= Long.parseLong(txDisplay.getText());
                    PM= PM*-1;
                    txDisplay.setText(String.valueOf(PM));
                }
            }
        });
        button20.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!txDisplay.getText().contains("."))
                {
                    txDisplay.setText(txDisplay.getText()+".");
                }
            }
        });

        button11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                x = Double.parseDouble(txDisplay.getText());
                op = "+";
                txDisplay.setText("");

            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                x = Double.parseDouble(txDisplay.getText());
                op = "-";
                txDisplay.setText("");
            }
        });
        xButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                x = Double.parseDouble(txDisplay.getText());
                op = "x";
                txDisplay.setText("");
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                x = Double.parseDouble(txDisplay.getText());
                op = "/";
                txDisplay.setText("");

            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                y = Double.parseDouble(txDisplay.getText());
                if (op == "+")
                {
                    y=x+y;
                }
                if (op == "-")
                {
                    y=x-y;
                }
                if (op == "/")
                {
                    y=x/y;
                }
                if (op == "x")
                {
                    y=x*y;
                }
                txDisplay.setText(String.valueOf(y));
            }
        });

        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String del;
                if (txDisplay.getText().length()>0)
                {
                    StringBuilder strA = new StringBuilder(txDisplay.getText());
                    strA.deleteCharAt(txDisplay.getText().length()-1);
                    del=String.valueOf(strA);
                    txDisplay.setText(del);
                }

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(new Calculator().Calculator);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
