import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

public class Notepad {
    private JPanel Notepad;
    private JMenu Plik;
    private JMenuItem Save;
    private JMenuItem Open;
    private JTextArea textArea1;
    private JMenuItem close;
    private JMenu format;
    private JMenu size;
    private JMenuItem size24;
    private JMenuItem size8;
    private JMenuItem size16;
    private JMenu font;
    private JMenuItem consolas;
    private JMenuItem arial;
    private JPanel Exit;
    private JFrame frame = new JFrame("Notepad");
    private JFileChooser fileChooser = new JFileChooser();
    private File file;


    public String readFile(String fileName)
    {
        Path path= Paths.get(fileName);

        if(Files.exists(path)) {
            try {
                File myfile = new File(fileName);
                Scanner myScanner = new Scanner(myfile);
                while (myScanner.hasNextLine())
                {
                    textArea1.setText(myScanner.nextLine());

                }
                myScanner.close();
            } catch (FileNotFoundException e) {
                System.out.println("wystapil blad");
                e.printStackTrace();
            }
        }
        else
            writeFIle(fileName,"");
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
    public void Size(int fontSize)
    {
        Font setSize=new Font(textArea1.getFont().getFontName(), Font.PLAIN,fontSize);
        textArea1.setFont(setSize);
    }

    public void Font(String name)
    {
        Font setSize=new Font(name, Font.PLAIN,textArea1.getFont().getSize());
        textArea1.setFont(setSize);
    }
    public Notepad() {
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] possibilities = {"Zapisz", "Anuluj", "Wyjdź bez zapisywania"};
                Icon icon=null;
                String s = (String)JOptionPane.showInputDialog(
                        frame,
                        "Chcesz Opuśćić notatnik?\n"
                                + "Nie zapomnij zapisać swoich postępów",
                        "Wyjście z programu",
                        JOptionPane.PLAIN_MESSAGE,
                        icon,
                        possibilities, "Zapisz");
                System.out.println(s);
                if(s=="Wyjdź bez zapisywania")
                    System.exit(0);
                if(s=="Zapisz") {
                    Save.doClick();
                    System.exit(0);
                }

            }

        });


        Save.addActionListener(new ActionListener()  {
            @Override
            public void actionPerformed(ActionEvent e) {

                Component modalToComponent = null;
                if (fileChooser.showSaveDialog(modalToComponent) == JFileChooser.APPROVE_OPTION) {
                   file = fileChooser.getSelectedFile();
                    writeFIle(String.valueOf(file),textArea1.getText());
                }

            }
        });




        Open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {JFileChooser fileChooser = new JFileChooser();
                Component modalToComponent = null;
                if (fileChooser.showOpenDialog(modalToComponent) == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    readFile(String.valueOf(file));
                }

            }
        });



        size8.addActionListener(new ActionListener()  {
            @Override
            public void actionPerformed(ActionEvent e) {
                Size(8);
                }
        });
        size16.addActionListener(new ActionListener()  {
            @Override
            public void actionPerformed(ActionEvent e) {
                Size(16);
            }
        });
        size24.addActionListener(new ActionListener()  {
            @Override
            public void actionPerformed(ActionEvent e) {
                Size(24);

            }
        });

        arial.addActionListener(new ActionListener()  {
            @Override
            public void actionPerformed(ActionEvent e) {
                Font("Arial");

            }
        });
        consolas.addActionListener(new ActionListener()  {
            @Override
            public void actionPerformed(ActionEvent e) {
                Font("Consolas");

            }
        });


    }

    public static void main(String[] args) {
        Notepad notepad = new Notepad();
        notepad.frame.setContentPane(new Notepad().Notepad);
        notepad.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        notepad.frame.pack();

        notepad.frame.setVisible(true);


    }

}
