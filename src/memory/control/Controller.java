package memory.control;

import java.awt.event.ActionListener;
import memory.view.View;
import memory.model.Player;
import memory.model.CardManager;
import java.util.ArrayList;
import javax.swing.JButton;

/**
 *
 * @author cw
 */
public class Controller implements ActionListener {

//Controller has Model and View hardwired in
    private CardManager model;
    private View view;

    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Integer> clickedBtns = new ArrayList<>();

    public Controller() {
        Player player1 = new Player("Chiara", 0);
        Player player2 = new Player("Thomas", 0);

        players.add(player1);
        players.add(player2);

        // at the moments player1 begins always; later: whoBeginsDice()-method
        player1.changeTurn();
    }

    //invoked when a button is pressed
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        Object source = e.getSource();

        if (source == view.getNextTurn()) {
            evaluateAndChangeTurns();
        }
        for (int i = 0; i < view.getButtons().length; i++) {
            if (source == view.getButtons()[i]) {
                view.getButtons()[i].setIcon(model.getCardImage(i));

                addChosenCardsToPlayer(i);
                break;
            }
        }
    }

    public void addChosenCardsToPlayer(int indexOfChosenCard) {
            if (this.getPlayerWhoseTurnItIs().getChosenCards().size() < 2) {
                if (!clickedBtns.contains(indexOfChosenCard)) {
                    this.getPlayerWhoseTurnItIs().getChosenCards().add(model.getCards().get(indexOfChosenCard));
                    clickedBtns.add(indexOfChosenCard);
                }

            } else {
                view.setBackside(view.getButtons()[indexOfChosenCard]);
                view.getGameInfo().append("\nKlicke auf\nZug beenden.\n");
                System.out.println("Es wurden schon zwei Karten aufgedeckt! Klicke auf Zug beenden.");
            }
        
    }

    //
    public void evaluateAndChangeTurns() {
        if (this.clickedBtns.size() == 2) {

            if (this.getPlayerWhoseTurnItIs().getChosenCards().get(0).equals(this.getPlayerWhoseTurnItIs().getChosenCards().get(1))) {

                this.getPlayerWhoseTurnItIs().incrementPlayerScore();
                view.getGameInfo().append(this.getPlayerWhoseTurnItIs().getName() + " bekommt\neinen Punkt.\n");
                System.out.println(this.getPlayerWhoseTurnItIs().getName() + "bekommt 1 Punkt");

                //disable card and make its black
                for (int i = 0; i < clickedBtns.size(); i++) {
                    view.setBlack(view.getButtons()[this.clickedBtns.get(i)]);
                }
                view.updatePlayerScoreInView(this.getPlayerWhoseTurnItIs(), this.indexOfPlayerWhoseTurnItIs());
            } else {
                //turn cards to backside
                for (int i = 0; i < clickedBtns.size(); i++) {
                    view.setBackside(view.getButtons()[this.clickedBtns.get(i)]);
                }
            }
            // clear List of chosen cards/clickedBtns and change turn
            this.getPlayerWhoseTurnItIs().clearChosenCards();
            this.clickedBtns.clear();
            System.out.println("Playerwechsel");
            this.changeTurnOfPlayers();
            view.getGameInfo().append("\n< Playerwechsel >\n"
                    + this.getPlayerWhoseTurnItIs().getName() + " ist am Zug.     \n");
        }
    }

    public void addToBtns(int value) {
        this.clickedBtns.add(value);
    }

    //tested
    public Player getPlayerWhoseTurnItIs() {
        for (int i = 0; i < this.players.size(); i++) {
            if (players.get(i).getTurn()) {
                return players.get(i);
            }
        }
        System.out.println("getPlayerWhoseTurnItIs: Error: turn of players are false."
                + "ONE has to be true.");
        return null;
    }

    //tested
    public int indexOfPlayerWhoseTurnItIs() {
        for (int i = 0; i < this.players.size(); i++) {
            if (players.get(i).getTurn()) {
                return i;
            }
        }
        System.out.println("IndexOfPlayers: Error: turn of players are false."
                + "ONE has to be true.");
        return -1;
    }

    // tested
    public void changeTurnOfPlayers() {

        if (indexOfPlayerWhoseTurnItIs() + 1 >= this.players.size()) {
            this.players.get(indexOfPlayerWhoseTurnItIs()).changeTurn();
            this.players.get(0).changeTurn();
            System.out.println(getPlayerWhoseTurnItIs().getName() + " ist dran.");
            return;
        }

        if (indexOfPlayerWhoseTurnItIs() + 1 < this.players.size()) {
            this.players.get(indexOfPlayerWhoseTurnItIs() + 1).changeTurn();
            this.players.get(indexOfPlayerWhoseTurnItIs()).changeTurn();
        }
        System.out.println(getPlayerWhoseTurnItIs().getName() + " ist dran.");

    }

    public void setCardsToBackside(JButton b) {
        view.setBackside(b);
    }

    public void addModel(CardManager m) {
        this.model = m;
    }

    public void addView(View v) {
        this.view = v;
    }

    public void initModel() {
        model.setCards(view.getButtons());
        view.getGameInfo().append(this.getPlayerWhoseTurnItIs().getName() + " ist am Zug.\n     ");
    }
}
