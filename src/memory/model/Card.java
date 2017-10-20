package memory.model;

import java.util.Objects;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author cw
 */
public class Card {

    private ImageIcon cardFrontside;
    private JButton btn;

    public Card(ImageIcon cardFrontside, JButton btn) {

        this.cardFrontside = cardFrontside;
        this.btn = btn;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.cardFrontside);
        hash = 61 * hash + Objects.hashCode(this.btn);
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
        if (!Objects.equals(this.btn, other.btn)) {
            return false;
        }
        return true;
    }

    public ImageIcon getCardFrontside() {
        return cardFrontside;
    }

    public JButton getBtn() {
        return btn;
    }

}
