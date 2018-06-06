import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class MancalaPlayer extends JFrame implements Runnable {
    private Dimension screenSize = Toolkit.getDefaultToolkit()
                                          .getScreenSize();
    private Thread    thread;

    private boolean running = false;

    private JPanel center = new JPanel();
    private JPanel bottom = new JPanel();

    private JButton exit  = new JButton("Exit");
    private JButton reset = new JButton("Reset");

    private              BorderLayout bl         = new BorderLayout();
    private              FlowLayout   bottomFL   = new FlowLayout();
    private static final int          PLAYER_ONE = 0;
    private static final int          PLAYER_TWO = 1;
    private              int          turn       = PLAYER_ONE;
    private              int          winner;

    private JTextField jTextField = new JTextField();

    private LinkedList<Integer> slots = new LinkedList<>();

    private int boardSize = Integer.parseInt(JOptionPane.showInputDialog(null, "Type in board size"));
    private int numPieces = Integer.parseInt(JOptionPane.showInputDialog(null, "Type in number of game pieces. Must be a multiple of " + (boardSize - 2)));

    private JButton pit1 = new JButton("0");
    private JButton pit2 = new JButton("0");

    private Slot[] gameSlots = new Slot[boardSize - 2];

    private       int     slotClicked = 0;
    private       int     leftToMove  = 0;
    public static boolean movePieces  = false;
    public static int     pieceToMove;

    Timer timer = new Timer(1, action -> update());

    public MancalaPlayer() {
        configureFrame();
        configureBottom();
    }

    private void configureFrame() {

        System.out.println(jTextField.getText());

        center.setLayout(new GridLayout(2, (boardSize - 2) / 2));
        for (int i = 0; i < boardSize - 2; i++) {
            gameSlots[i] = new Slot("0");
            gameSlots[i].setBackground(Color.yellow);
            gameSlots[i].index = i;
            gameSlots[i].addActionListener(gameSlots[i]);
            center.add(gameSlots[i]);
        }


        for (int i = 0; i < boardSize; i++) {
            if (i == 0 || i == boardSize / 2) {
                slots.add(0);
            } else {
                slots.add(numPieces / (boardSize - 2));
            }
        }

        update();
        setLayout(bl);

        pit1.setBackground(Color.yellow);
        add(pit1, BorderLayout.LINE_START);
        pit2.setBackground(Color.yellow);
        add(pit2, BorderLayout.LINE_END);

        add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.PAGE_END);
        setTitle("Mancala");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(screenSize.width / 2, screenSize.height * 2 / 3);
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
    }

    private void configureBottom() {
        bottom.setLayout(bottomFL);
        bottom.add(exit);
        bottom.add(reset);
        exit.addActionListener(action -> stop());
//        reset.addActionListener(action -> reset());
    }

    private void reset() {
        System.out.println("resetting..");
    }


    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        JOptionPane.showMessageDialog(new JOptionPane(),
                                      "On my honor as a member of the Woodson HS Community,\n" +
                                      "I, Dennis Nguyen, certify that I have neither given \n" +
                                      "nor received unauthorized aid on this assignment, \n" +
                                      "that I have cited my sources for authorized aid, and \n" +
                                      "that this project was started on or after April 18, 2018.", "Honor Code",
                                      JOptionPane.INFORMATION_MESSAGE);

        System.exit(0);
    }

    public void movePieces() {
        int numToDrop  = slots.get(pieceToMove + 1);
        int slotToDrop = pieceToMove + 1;
        if (turn == PLAYER_ONE) {
            gameSlots[pieceToMove].setBackground(Color.blue);
            slots.set(pieceToMove + 1, 0);

            while (numToDrop > 0) {
                if (slots.get(slotToDrop + 1) == 0) {
                } else {
                    turn = PLAYER_TWO;
                }

                if (slotToDrop + 1 == slots.size() / 2)
                    continue;

                slots.set(slotToDrop + 1, slots.get(slotToDrop + 1) + 1);
                numToDrop--;
                slotToDrop++;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {}
        } else if (turn == PLAYER_TWO) {
            gameSlots[pieceToMove].setBackground(Color.blue);
            slots.set(pieceToMove + 1, 0);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
            }

            while (numToDrop > 0) {
                if (slots.get(slotToDrop + 2) == 0) {
                } else {
                    turn = PLAYER_ONE;
                }

                if (slotToDrop + 2 > slots.size()) {

                }
            }

        }


        movePieces = false;
    }

    public void update() {
        if (IntStream.range(1, boardSize / 2)
                     .allMatch(num -> slots.get(num) == 0)) {
            gameOver();
            winner = PLAYER_ONE;
        } else if (IntStream.range((boardSize / 2) + 1, slots.size() - 1)
                            .allMatch(num -> slots.get(num) == 0)) {
            gameOver();
            winner = PLAYER_TWO;
        }

        if (movePieces) {
            movePieces();
        }

        pit2.setText(Integer.toString(slots.get(0)));
        pit1.setText(Integer.toString(slots.get(boardSize / 2)));

        for (int i = 1; i < boardSize / 2; i++) {
            gameSlots[i - 1].setText(Integer.toString(slots.get(i)));
            gameSlots[i - 1].setBackground(Color.yellow);
        }

        for (int i = (boardSize / 2) + 1; i < boardSize; i++) {
            gameSlots[i - 2].setText(Integer.toString(slots.get(i)));
            gameSlots[i - 2].setBackground(Color.yellow);
        }
    }


    public void gameOver() {
        stop();
    }

    @Override
    public void run() {
        timer.start();
    }

    public boolean isRunning() {
        return running;
    }
}
