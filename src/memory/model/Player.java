package memory.model;

import java.util.ArrayList;

/**
 * Player class wiht attributes.
 * @author cw
 */
public class Player {

    private String name;
    private int score;
    private Boolean turn;

    private ArrayList<Card> chosenCards = new ArrayList<>();

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
        this.turn = false;
    }

    public void incrementPlayerScore() {
        this.score++;
    }

    public void changeTurn() {
        turn = !turn;
    }

    public void addToChosenCards(Card card) {
        this.chosenCards.add(card);
    }

    public void clearChosenCards() {
        this.chosenCards.clear();
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public Boolean getTurn() {
        return turn;
    }

    public ArrayList<Card> getChosenCards() {
        return chosenCards;
    }

    public String toString() {
        return this.name + " (Punkte " + this.score + ") ";
    }

}
