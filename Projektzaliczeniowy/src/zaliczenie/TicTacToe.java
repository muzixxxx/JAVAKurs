package zaliczenie;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TicTacToe implements Runnable {
    private String ip = "localhost";
    private int port = 22222;
    private Scanner scanner;
    public JFrame frame;

    private final int WIDTH;
    private final int HEIGHT;
    private Thread thread;
    private Painter painter;
    private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;
    private ServerSocket serverSocket;
    private BufferedImage board;
    private BufferedImage redX;
    private BufferedImage blueX;
    private BufferedImage purpleCircle;
    private BufferedImage purpleX;
    private BufferedImage [] colorX={redX,blueX,purpleX};
    private BufferedImage redCircle;
    private BufferedImage blueCircle;
    private int colorXnumber;
    private  int colorOnumber;
    private BufferedImage[] colorCircle={redCircle,blueCircle,purpleCircle};
    private String[] spaces;
    private boolean yourTurn;
    private boolean circle;
    private boolean accepted;
    private boolean unableToCommunicateWithOpponent;
    private boolean won;
    private boolean enemyWon;
    private boolean tie;
    private int lengthOfSpace;
    private int errors;
    private int firstSpot;
    private int secondSpot;
    private Font font;
    private Font smallerFont;
    private Font largerFont;
    private String waitingString;
    private String unableToCommunicateWithOpponentString;
    private String wonString;
    private String enemyWonString;
    private String tieString;
    private int[][] wins;

    public TicTacToe() {

        this.scanner = new Scanner(System.in);
        this.WIDTH = 506;
        this.HEIGHT = 527;
        this.spaces = new String[9];
        this.yourTurn = false;
        this.circle = true;
        this.accepted = false;
        this.unableToCommunicateWithOpponent = false;
        this.won = false;
        this.enemyWon = false;
        this.tie = false;
        this.lengthOfSpace = 160;
        this.errors = 0;
        this.firstSpot = -1;
        this.secondSpot = -1;
        this.font = new Font("Verdana", 1, 32);
        this.smallerFont = new Font("Verdana", 1, 20);
        this.largerFont = new Font("Verdana", 1, 50);
        this.waitingString = "Waiting for another player";
        this.unableToCommunicateWithOpponentString = "Unable to communicate with opponent.";
        this.wonString = "You won!";
        this.enemyWonString = "Opponent won!";
        this.tieString = "Game ended in a tie.";
        this.wins = new int[][]{{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
        System.out.println("Please input the IP: ");
        this.ip = "localhost";
        System.out.println("Please input the port: ");
        this.frame = new JFrame();
        Panel panel= new Panel(this.frame);


        this.frame.setTitle("Tic-Tac-Toe");
        this.frame.setSize(506, 527);
        this.frame.setLocationRelativeTo((Component)null);
        this.frame.setDefaultCloseOperation(3);
        this.frame.setResizable(false);
        this.frame.setVisible(true);
        this.frame.add(panel.panelTic);




    }

     public void start(String userIP, String userPort,int colorXnum,int colorCiNumber){
        this.ip=userIP;
        this.port=Integer.parseInt(userPort);
        this.colorXnumber=colorXnum;
        this.colorOnumber=colorCiNumber;
        System.out.println("liczby color: "+this.colorXnumber+" druga liczba "+ this.colorOnumber);


    this.loadImages();
    this.painter = new Painter();
    this.painter.setPreferredSize(new Dimension(506, 527));
    if (!this.connect()) {
        this.initializeServer();
    }


    this.frame.setContentPane(this.painter);



    this.thread = new Thread(this, "TicTacToe");
    this.thread.start();
}
    public void run() {
        while(true) {
            this.tick();
            this.painter.repaint();
            if (!this.circle && !this.accepted) {
                this.listenForServerRequest();
            }
        }
    }

    private void render(Graphics g) {
        colorX[0]=redX;
        colorX[1]=blueX;
        colorX[2]=purpleX;
        colorCircle[0]=redCircle;
        colorCircle[1]=blueCircle;
        colorCircle[2]=purpleCircle;

        g.drawImage(this.board, 0, 0, (ImageObserver)null);
        int stringWidth;
        Graphics2D g2;
        if (this.unableToCommunicateWithOpponent) {
            g.setColor(Color.RED);
            g.setFont(this.smallerFont);
            g2 = (Graphics2D)g;
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            stringWidth = g2.getFontMetrics().stringWidth(this.unableToCommunicateWithOpponentString);
            g.drawString(this.unableToCommunicateWithOpponentString, 253 - stringWidth / 2, 263);
        } else {
            if (this.accepted) {
                for(int i = 0; i < this.spaces.length; ++i) {
                    if (this.spaces[i] != null) {
                        if (this.spaces[i].equals("X")) {

                                g.drawImage(this.colorX[this.colorXnumber], i % 3 * this.lengthOfSpace + 10 * (i % 3), i / 3 * this.lengthOfSpace + 10 * (i / 3), (ImageObserver)null);

                        } else if (this.spaces[i].equals("O")) {

                                g.drawImage(this.colorCircle[this.colorOnumber], i % 3 * this.lengthOfSpace + 10 * (i % 3), i / 3 * this.lengthOfSpace + 10 * (i / 3), (ImageObserver)null);

                        }
                    }
                }

                if (this.won || this.enemyWon) {
                    g2 = (Graphics2D)g;
                    g2.setStroke(new BasicStroke(10.0F));
                    g.setColor(Color.BLACK);
                    g.drawLine(this.firstSpot % 3 * this.lengthOfSpace + 10 * this.firstSpot % 3 + this.lengthOfSpace / 2, this.firstSpot / 3 * this.lengthOfSpace + 10 * (this.firstSpot / 3) + this.lengthOfSpace / 2, this.secondSpot % 3 * this.lengthOfSpace + 10 * this.secondSpot % 3 + this.lengthOfSpace / 2, this.secondSpot / 3 * this.lengthOfSpace + 10 * (this.secondSpot / 3) + this.lengthOfSpace / 2);
                    g.setColor(Color.GREEN);
                    g.setFont(this.largerFont);
                    if (this.won) {
                        stringWidth = g2.getFontMetrics().stringWidth(this.wonString);
                        g.drawString(this.wonString, 253 - stringWidth / 2, 263);
                    } else if (this.enemyWon) {
                        stringWidth = g2.getFontMetrics().stringWidth(this.enemyWonString);
                        g.drawString(this.enemyWonString, 253 - stringWidth / 2, 263);
                    }
                }

                if (this.tie) {
                    g2 = (Graphics2D)g;
                    g.setColor(Color.BLACK);
                    g.setFont(this.largerFont);
                    stringWidth = g2.getFontMetrics().stringWidth(this.tieString);
                    g.drawString(this.tieString, 253 - stringWidth / 2, 263);
                }
            } else {
                g.setColor(Color.RED);
                g.setFont(this.font);
                g2 = (Graphics2D)g;
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                stringWidth = g2.getFontMetrics().stringWidth(this.waitingString);
                g.drawString(this.waitingString, 253 - stringWidth / 2, 263);
            }

        }
    }

    private void tick() {
        if (this.errors >= 10) {
            this.unableToCommunicateWithOpponent = true;
        }

        if (!this.yourTurn && !this.unableToCommunicateWithOpponent) {
            try {
                int space = this.dis.readInt();
                if (this.circle) {
                    this.spaces[space] = "X";
                } else {
                    this.spaces[space] = "O";
                }

                this.checkForEnemyWin();
                this.checkForTie();
                this.yourTurn = true;
            } catch (IOException var2) {
                var2.printStackTrace();
                ++this.errors;
            }
        }

    }

    private void checkForWin() {
        for(int i = 0; i < this.wins.length; ++i) {
            if (this.circle) {
                if (this.spaces[this.wins[i][0]] == "O" && this.spaces[this.wins[i][1]] == "O" && this.spaces[this.wins[i][2]] == "O") {
                    this.firstSpot = this.wins[i][0];
                    this.secondSpot = this.wins[i][2];
                    this.won = true;
                }
            } else if (this.spaces[this.wins[i][0]] == "X" && this.spaces[this.wins[i][1]] == "X" && this.spaces[this.wins[i][2]] == "X") {
                this.firstSpot = this.wins[i][0];
                this.secondSpot = this.wins[i][2];
                this.won = true;
            }
        }

    }

    private void checkForEnemyWin() {
        for(int i = 0; i < this.wins.length; ++i) {
            if (this.circle) {
                if (this.spaces[this.wins[i][0]] == "X" && this.spaces[this.wins[i][1]] == "X" && this.spaces[this.wins[i][2]] == "X") {
                    this.firstSpot = this.wins[i][0];
                    this.secondSpot = this.wins[i][2];
                    this.enemyWon = true;
                }
            } else if (this.spaces[this.wins[i][0]] == "O" && this.spaces[this.wins[i][1]] == "O" && this.spaces[this.wins[i][2]] == "O") {
                this.firstSpot = this.wins[i][0];
                this.secondSpot = this.wins[i][2];
                this.enemyWon = true;
            }
        }

    }

    private void checkForTie() {
        for(int i = 0; i < this.spaces.length; ++i) {
            if (this.spaces[i] == null) {
                return;
            }
        }

        this.tie = true;
    }

    private void listenForServerRequest() {
        Socket socket = null;

        try {
            socket = this.serverSocket.accept();
            this.dos = new DataOutputStream(socket.getOutputStream());
            this.dis = new DataInputStream(socket.getInputStream());
            this.accepted = true;
            System.out.println("CLIENT HAS REQUESTED TO JOIN, AND WE HAVE ACCEPTED");
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    private boolean connect() {
        try {
            this.socket = new Socket(this.ip, this.port);
            this.dos = new DataOutputStream(this.socket.getOutputStream());
            this.dis = new DataInputStream(this.socket.getInputStream());
            this.accepted = true;
            System.out.println("Connect ip: "+this.ip);
        } catch (IOException var2) {
            System.out.println("Unable to connect to the address: " + this.ip + ":" + this.port + " | Starting a server");
            return false;
        }

        System.out.println("Successfully connected to the server.");
        return true;
    }

    private void initializeServer() {
        try {
            this.serverSocket = new ServerSocket(this.port, 8, InetAddress.getByName(this.ip));
        } catch (Exception var2) {
            var2.printStackTrace();
        }

        this.yourTurn = true;
        this.circle = false;
    }

    private void loadImages() {
        try {

            this.board = ImageIO.read(this.getClass().getResourceAsStream("board.png"));
            this.redX = ImageIO.read(this.getClass().getResourceAsStream("redX.png"));
            this.redCircle = ImageIO.read(this.getClass().getResourceAsStream("redCircle.png"));
            this.blueX = ImageIO.read(this.getClass().getResourceAsStream("blueX.png"));
            this.blueCircle = ImageIO.read(this.getClass().getResourceAsStream("blueCircle.png"));

            this.purpleX = ImageIO.read(this.getClass().getResourceAsStream("purpleX.png"));
            this.purpleCircle = ImageIO.read(this.getClass().getResourceAsStream("purpleCircle.png"));
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }

    public static void main(String[] args) throws FileNotFoundException {


        new TicTacToe();
    }

    private class Painter extends JPanel implements MouseListener {
        private static final long serialVersionUID = 1L;

        public Painter() {
            this.setFocusable(true);
            this.requestFocus();
            this.setBackground(Color.WHITE);
            this.addMouseListener(this);
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            TicTacToe.this.render(g);
        }

        public void mouseClicked(MouseEvent e) {
            if (TicTacToe.this.accepted && TicTacToe.this.yourTurn && !TicTacToe.this.unableToCommunicateWithOpponent && !TicTacToe.this.won && !TicTacToe.this.enemyWon) {
                int x = e.getX() / TicTacToe.this.lengthOfSpace;
                int y = e.getY() / TicTacToe.this.lengthOfSpace;
                y *= 3;
                int position = x + y;
                if (TicTacToe.this.spaces[position] == null) {
                    if (!TicTacToe.this.circle) {
                        TicTacToe.this.spaces[position] = "X";
                    } else {
                        TicTacToe.this.spaces[position] = "O";
                    }

                    TicTacToe.this.yourTurn = false;
                    this.repaint();
                    Toolkit.getDefaultToolkit().sync();

                    try {
                        TicTacToe.this.dos.writeInt(position);
                        TicTacToe.this.dos.flush();
                    } catch (IOException var6) {
                        TicTacToe var10000 = TicTacToe.this;
                        var10000.errors = var10000.errors + 1;
                        var6.printStackTrace();
                    }

                    System.out.println("DATA WAS SENT");
                    TicTacToe.this.checkForWin();
                    TicTacToe.this.checkForTie();
                }
            }

        }

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }
    }
}
