package memory.model;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * CardManager creates cards which exist in the game and add them to cards list.
 * @author cw
 */
public class CardManager {

    /**
     * Determines who many cards are created.
    *
     */
    private final int amountOfCards = 9;
    /**
     * Helds all cards of the game.
     */
    private ArrayList<Card> cards;

    // CardManager initializes cards list.
    public CardManager() {
        cards = new ArrayList();
    }

    /**
     * Creates all cards and adds them to the list.
     *
     * @param buttons
     */
    public void createCards(JButton[] buttons) {
        for (int i = 1; i <= amountOfCards; i++) {
            Card card = new Card(new ImageIcon("src/assets/card" + i + ".png"), buttons[i]);
            cards.add(card);
            cards.add(card);
        }
        Collections.shuffle(cards);
    }

    /**
     * Sets correct image to the card which index was given.
     * @param indexOfCard int
     * @return ImageIcon
     */
    public ImageIcon getCardImage(int indexOfCard) {
        for (int i = 0; i < cards.size(); i++) {
            if (i == indexOfCard) {
                ImageIcon icon = cards.get(i).getCardFrontside();
                return icon;
            }
        }
        return null;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
}
