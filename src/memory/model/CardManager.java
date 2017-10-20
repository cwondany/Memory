package memory.model;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author cw
 */
public class CardManager {

    private ArrayList<Card> cards;

    public CardManager() {
        cards = new ArrayList();
    }

    public void setCards(JButton[] buttons) {
        for (int i = 1; i < 7; i++) {
            Card card = new Card(new ImageIcon("src/assets/card" + i + ".png"), buttons[i]);
            cards.add(card);
            cards.add(card);
        }
        Collections.shuffle(cards);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public ImageIcon getCardImage(int id) {
        for (int i = 0; i < cards.size(); i++) {
            if (i == id) {
                ImageIcon icon = cards.get(i).getCardFrontside();
                return icon;
            }
        }
        return null;
    }

}
