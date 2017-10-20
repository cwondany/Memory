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
 *
 * @author cw
 */
public class View {

    private final JLabel PLAYER1;
    private final JLabel PLAYER2;
    private final JTextArea GAMEINFO;

    private final JButton[] BUTTONS = new JButton[12];
    private final JButton NEXTTURN;

    private final ImageIcon CARD_BACKSIDE = new ImageIcon("src/assets/backside.png");
    private final ImageIcon BLACKCARD = new ImageIcon("src/assets/blackCard.png");

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
        GridLayout grid = new GridLayout(3, 4, 15, 15);
        panel.setLayout(grid);

        for (int i = 0; i < BUTTONS.length; i++) {
            BUTTONS[i] = new JButton(CARD_BACKSIDE);
            BUTTONS[i].setRolloverEnabled(false);
            BUTTONS[i].setBackground(Color.white);
            panel.add(BUTTONS[i]); //add that same button to the panel
        }
        Panel sidePanel = new Panel();
        BorderLayout b = new BorderLayout();
        sidePanel.setLayout(b);

        GAMEINFO = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(GAMEINFO);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        //scroll automatically to button if textArea is full
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
//        nextTurn.setBackground(Color.black);
        sidePanel.add(NEXTTURN, BorderLayout.SOUTH);

        frame.add("East", sidePanel);
        frame.add(panel);
        frame.addWindowListener(new CloseListener());
        frame.setSize(700, 500);
        frame.setLocation(100, 100);
        frame.setVisible(true);

    }
//tested

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

    public void addController(ActionListener controller) {
        for (int i = 0; i < BUTTONS.length; i++) {
            BUTTONS[i].addActionListener(controller);
        }
        this.NEXTTURN.addActionListener(controller);
    }

    public JButton[] getButtons() {
        return BUTTONS;
    }

    public JTextArea getGameInfo() {
        return GAMEINFO;
    }

    public JButton getNextTurn() {
        return NEXTTURN;
    }

    //tested
    public void setBackside(JButton b) {
        b.setIcon(CARD_BACKSIDE);

    }

    //tested
    public void setBlack(JButton b) {
        b.setIcon(BLACKCARD);
        b.setEnabled(false);
        // b.setContentAreaFilled(false) ;
    }

    public static class CloseListener extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent e) {
            e.getWindow().setVisible(false);
            System.exit(0);
        }
    }

}
