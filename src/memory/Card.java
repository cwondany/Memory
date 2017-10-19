package memory;

import java.util.Objects;
import javax.swing.ImageIcon;

/**
 *
 * @author cw
 */
public class Card {

    private ImageIcon cardFrontside;

    public Card(ImageIcon cardFrontside) {

        this.cardFrontside = cardFrontside;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Card other = (Card) obj;
        if (!Objects.equals(this.cardFrontside, other.cardFrontside)) {
            return false;
        }
        return true;
    }



    public ImageIcon getCardFrontside() {
        return cardFrontside;
    }
    

}
