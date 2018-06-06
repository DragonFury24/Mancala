import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MancalaPlayer extends JFrame implements Runnable, MouseListener {
    private Dimension screenSize = Toolkit.getDefaultToolkit()
                                          .getScreenSize();
    private Thread    thread;

    private boolean running = false;

    private JPanel center = new JPanel();
    private JPanel bottom = new JPanel();

    private JButton exit  = new JButton("Exit");
    private JButton reset = new JButton("Reset");

    private BorderLayout bl = new BorderLayout();
    private FlowLayout bottomFL = new FlowLayout();
    private static final int PLAYER_ONE = 0;
    private static final int PLAYER_TWO = 1;
    private              int turn       = PLAYER_ONE;

    public MancalaPlayer() {
        configureFrame();
        configureBottom();
        createUI();
    }

    private void configureFrame() {
        center.setLayout(new GridLayout(2, 6));
        for (int i = 0; i < 12; i++) {
            center.add(new JButton(Integer.toString(i)));
        }

        setLayout(bl);
        add(new JButton("Pit1"), BorderLayout.LINE_START);
        add(new JButton("Pit2"), BorderLayout.LINE_END);

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
        reset.addActionListener(action -> reset());
    }

    private void reset() {
        System.out.println("reset");
    }

    private void createUI() {
        setVisible(true);
        setVisible(false);
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

    @Override
    public void run() {
        System.out.println("testing");
    }

    public boolean isRunning() {
        return running;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
