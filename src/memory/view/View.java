package memory.view;

import memory.model.Player;
import java.awt.BorderLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
import javax.swing.border.Border;
import javax.swing.text.DefaultCaret;
import static javax.swing.text.DefaultCaret.ALWAYS_UPDATE;

/**
 * UI with all its components.
 *
 * @author cw
 */
public class View {

    private final int AMOUNT_OF_CARDS = 40;
    private final int SIZEX = 1200;
    private final int SIZEY = 500;

    private final JLabel PLAYER1;
    private final JLabel PLAYER2;
    private final JTextArea GAMEINFO;
    /**
     * Array of Buttons that are displayed in the game.
     */
    private final JButton[] BUTTONS = new JButton[AMOUNT_OF_CARDS];
    private final JButton NEXTTURN;

    private final ImageIcon CARD_BACKSIDE = new ImageIcon("src/assets/backside.png");
    private final ImageIcon BLACKCARD = new ImageIcon("src/assets/blackCard.png");

    /**
     * View constructor initilizes whole UI.
     */
    public View() {
        Frame frame = new Frame("Memory");
        frame.setBackground(Color.black);

        PLAYER1 = new JLabel("Player 1: "
                + "                                                            "
                + 0);

        PLAYER1.setBackground(Color.LIGHT_GRAY);
        PLAYER1.setOpaque(true);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 5);
        PLAYER1.setBorder(border);
        PLAYER2 = new JLabel("Player 2:"
                + "                                                            "
                + 0);

        PLAYER2.setBackground(Color.LIGHT_GRAY);
        PLAYER2.setOpaque(true);
        PLAYER2.setBorder(border);
        PLAYER1.setForeground(Color.black);
        PLAYER2.setForeground(Color.black);

        frame.add("North", PLAYER1);
        frame.add("South", PLAYER2);
        Panel panel = new Panel();
        GridLayout grid = new GridLayout(4, 5, 8, 8);
        grid.preferredLayoutSize(panel);
        panel.setLayout(grid);

        for (int i = 0; i < BUTTONS.length; i++) {
            BUTTONS[i] = new JButton(CARD_BACKSIDE);
            BUTTONS[i].setRolloverEnabled(false);
            BUTTONS[i].setBackground(Color.white);
            panel.add(BUTTONS[i]); //add button to the panel
        }
        Panel sidePanel = new Panel();
        BorderLayout b = new BorderLayout();
        sidePanel.setLayout(b);
        sidePanel.setSize(500, 500);

        GAMEINFO = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(GAMEINFO);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        DefaultCaret caret = (DefaultCaret) GAMEINFO.getCaret();
        caret.setUpdatePolicy(ALWAYS_UPDATE);

        Border borderSideLabel = BorderFactory.createLineBorder(Color.lightGray, 5);
        GAMEINFO.setBackground(Color.black);
        GAMEINFO.setOpaque(true);
        GAMEINFO.setForeground(Color.white);
        GAMEINFO.setBorder(borderSideLabel);
        sidePanel.add(scrollPane, BorderLayout.CENTER);
        NEXTTURN = new JButton("Zug beenden");
        NEXTTURN.setForeground(Color.black);
        NEXTTURN.setBorder(borderSideLabel);

        sidePanel.add(NEXTTURN, BorderLayout.SOUTH);

        frame.add("East", sidePanel);
        frame.add(panel);
        frame.addWindowListener(new CloseListener());
        frame.setSize(SIZEX, SIZEY);
        frame.setLocation(0, 0);
        frame.setVisible(true);

    }

    /**
     * Updates the label which held the player name and their scores.
     *
     * @param player Player
     * @param index int
     */
    public void updatePlayerScoreInView(Player player, int index) {
        //update: scroll bar to bottom
        DefaultCaret caret = (DefaultCaret) GAMEINFO.getCaret();
        caret.setUpdatePolicy(ALWAYS_UPDATE);

        switch (index) {
            case 0:
                PLAYER1.setText(player.getName()
                        + "                                                            "
                        + player.getScore());
                break;
            case 1:
                PLAYER2.setText(player.getName()
                        + "                                                            "
                        + player.getScore());
                break;
        }
    }

    /**
     * Adds to all existing button an ActionController (called in GameController
     * class).
     *
     * @param controller GameController
     */
    public void addController(ActionListener controller) {
        for (int i = 0; i < BUTTONS.length; i++) {
            BUTTONS[i].addActionListener(controller);
        }
        this.NEXTTURN.addActionListener(controller);
    }

    // Array of all buttons (those consits of ImageIcon/cards)
    public JButton[] getButtons() {
        return BUTTONS;
    }

    public JTextArea getGameInfo() {
        return GAMEINFO;
    }

    public JButton getNextTurn() {
        return NEXTTURN;
    }

    /**
     * Set Button Icon to a backside picture of for the cards.
     *
     * @param b Button
     */
    public void setBackside(JButton b) {
        b.setIcon(CARD_BACKSIDE);
    }

    /**
     * Set Button Icon to a black picture.
     *
     * @param b Button
     */
    public void setBlack(JButton b) {
        b.setIcon(BLACKCARD);
        b.setEnabled(false);
    }

    public static class CloseListener extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent e) {
            e.getWindow().setVisible(false);
            System.exit(0);
        }
    }

}
