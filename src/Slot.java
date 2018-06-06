import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Slot extends JButton implements ActionListener {
    int index;

    public Slot() {
    }

    public Slot(Icon icon) {
        super(icon);
    }

    public Slot(String text) {
        super(text);
    }

    public Slot(Action a) {
        super(a);
    }

    public Slot(String text, Icon icon) {
        super(text, icon);
    }

    public int getIndex() {
        return index;
    }

    public void increaseNum() {
        setBackground(Color.blue);
        setText(Integer.toString(24));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ie) {

        }

        setBackground(Color.yellow);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MancalaPlayer.pieceToMove = index;
        MancalaPlayer.movePieces = true;

    }
}
