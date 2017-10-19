package memory;

import java.awt.Color;
import java.awt.Panel;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.Border;

/**
 *
 * @author cw
 */
public class View {

    private JLabel player1;
    private JLabel player2;
    private int player1Score = 0;
    private int player2Score = 0;

    private final JButton[] BUTTONS = new JButton[12];

    private final ImageIcon CARD_BACKSIDE = new ImageIcon("src/assets/backside.png");
    private final ImageIcon BLACKCARD = new ImageIcon("src/assets/blackCard.png");

    private boolean player1Turn = true;

    public View() {
        Frame frame = new Frame("Memory");
        frame.setBackground(Color.black);

        player1 = new JLabel("Player 1: "
                + "                                                            "
                + player1Score);
        player1.setBackground(Color.LIGHT_GRAY);
        player1.setOpaque(true);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 8);
        player1.setBorder(border);
        player2 = new JLabel("Player 2:"
                + "                                                            "
                + player2Score);
        player2.setBackground(Color.LIGHT_GRAY);
        player2.setOpaque(true);
        player2.setBorder(border);
        player1.setForeground(Color.black);
        player2.setForeground(Color.black);

        frame.add("North", player1);
        frame.add("South", player2);

        Panel panel = new Panel();
        GridLayout grid = new GridLayout(3, 4, 15, 15);
        panel.setLayout(grid);

        for (int i = 0; i < BUTTONS.length; i++) {
            BUTTONS[i] = new JButton(CARD_BACKSIDE);
            BUTTONS[i].setRolloverEnabled(false);
            panel.add(BUTTONS[i]); //add that same button to the panel

        }
        frame.add(panel);

        frame.addWindowListener(new CloseListener());
        frame.setSize(500, 500);
        frame.setLocation(100, 100);
        frame.setVisible(true);

    }

    public void update() {
        if (player1Turn) {
            player1.setText("Player 1:"
                    + "                                                            "
                    + player1Score);
        }
        if (!player1Turn) {
            player2.setText("Player 2:"
                    + "                                                            "
                    + player2Score);
        }

    }
    //to initialise TextField

    public void setValue(int v) {
        this.player1.setText("" + v);
        this.player2.setText("" + v);
    }

    public void addController(ActionListener controller) {
        for (int i = 0; i < BUTTONS.length; i++) {
            BUTTONS[i].addActionListener(controller);
        }
    }

    public JButton[] getButtons() {
        return BUTTONS;
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

    public void setBackside(int id) {
        for (int i = 0; i < BUTTONS.length; i++) {
            if (i == id) {
                BUTTONS[i].setIcon(CARD_BACKSIDE);
            }
        }
    }

    public void incrementPlayer1Score() {
         player1Score++;
    }
    public void incrementPlayer2Score() {
         player2Score++;
    }

    public void setBlack(JButton btn) {

        btn.setDisabledIcon(BLACKCARD);
        btn.setEnabled(false);
    }

    public void changeTurn() {
        player1Turn = !player1Turn;
    }

    public boolean isTurn() {
        return player1Turn;
    }

    public static class CloseListener extends WindowAdapter {

        public void windowClosing(WindowEvent e) {
            e.getWindow().setVisible(false);
            System.exit(0);
        }
    }

}
