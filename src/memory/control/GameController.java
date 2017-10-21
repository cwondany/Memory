package memory.control;

import java.awt.event.ActionListener;
import memory.view.View;
import memory.model.Player;
import memory.model.CardManager;
import java.util.ArrayList;
import javax.swing.JButton;

/**
 * Controller Class regulates the game cycle. It´s is wired with view and model
 * classes (MVC pattern).
 * 
 * @author cw
 */
public class GameController implements ActionListener {

    //Controller has Model and View hardwired in
    private CardManager model;
    private View view;
    /**
     * List of players.
     */
    private ArrayList<Player> players = new ArrayList<>();
    /**
     * List of Buttons which are clicked by current player, is cleared after
     * every turn.
     */
    private ArrayList<Integer> amountOfClickedButtons = new ArrayList<>();

    /**
     * Controller constructor creates two Player and decides which player
     * begins. Possible new feature: create Players in a for loop by given int,
     * diceFunction which decides who may begin.
     */
    public GameController() {
        Player player1 = new Player("Player 1", 0);
        Player player2 = new Player("Player 2", 0);

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

    /**
     * Gets card which is clicked and adds it to the chosenCard-List of current
     * player.
     * @param indexOfChosenCard index of the button which is clicked, is used in
     * order to determine which cards have to be turned to backside
     */
    public void addChosenCardsToPlayer(int indexOfChosenCard) {
        if (this.getPlayerWhoseTurnItIs().getChosenCards().size() < 2) {
            if (!amountOfClickedButtons.contains(indexOfChosenCard)) {
                this.getPlayerWhoseTurnItIs().getChosenCards().add(model.getCards().get(indexOfChosenCard));
                amountOfClickedButtons.add(indexOfChosenCard);
            }

        } else {
            view.setBackside(view.getButtons()[indexOfChosenCard]);
            view.getGameInfo().append("\nKlicke auf\nZug beenden.\n");
            System.out.println("Es wurden schon zwei Karten aufgedeckt! Klicke auf Zug beenden.");
        }
    }

    /**
     * Is called after two cards are clicked and evaluates the result: - Player
     * score is increased after a card pair is uncovered. - plays sound effect
     * dependent on the result - resets all buttons and chosencard lists -
     * provide changing turns
     */
    public void evaluateAndChangeTurns() {

        if (this.amountOfClickedButtons.size() == 2) {
            if (this.getPlayerWhoseTurnItIs().getChosenCards().get(0).equals(this.getPlayerWhoseTurnItIs().getChosenCards().get(1))) {

                Runnable soundPlay = new Runnable() {
                    @Override
                    public void run() {
                        AudioPlayer player = new AudioPlayer();
                        player.play("src/assets/success.wav");
                    }
                };
                soundPlay.run();
                this.getPlayerWhoseTurnItIs().incrementPlayerScore();
                view.getGameInfo().append(this.getPlayerWhoseTurnItIs().getName() + " bekommt\neinen Punkt.\n");
                System.out.println(this.getPlayerWhoseTurnItIs().getName() + " bekommt 1 Punkt");

                //disable card and make its black
                for (int i = 0; i < amountOfClickedButtons.size(); i++) {
                    view.setBlack(view.getButtons()[this.amountOfClickedButtons.get(i)]);
                }
                view.updatePlayerScoreInView(this.getPlayerWhoseTurnItIs(), this.indexOfPlayerWhoseTurnItIs());
            } else {
                Runnable soundPlay = new Runnable() {
                    @Override
                    public void run() {
                        AudioPlayer player = new AudioPlayer();
                        player.play("src/assets/fail.wav");
                    }
                };
                soundPlay.run();
                //turn cards to backside
                for (int i = 0; i < amountOfClickedButtons.size(); i++) {
                    view.setBackside(view.getButtons()[this.amountOfClickedButtons.get(i)]);
                }
            }
            // clear List of chosen cards/clickedBtns and change turn
            this.getPlayerWhoseTurnItIs().clearChosenCards();
            this.amountOfClickedButtons.clear();
            System.out.println("Playerwechsel");
            this.changeTurnOfPlayers();
            view.getGameInfo().append("\n - Playerwechsel - \n"
                    + this.getPlayerWhoseTurnItIs().getName() + " ist am Zug.     \n");
        }
        isGameOver();
    }

    /**
     * Adds int value to the list amountOfClickedButtons.
     *
     * @param value int
     */
    public void addToBtns(int value) {
        this.amountOfClickedButtons.add(value);
    }

    /**
     * Get the player whose turn it is. - loops through players list and gets
     * the player which turn is true.
     *
     * @return player
     */
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

    /**
     * Gets index of the player whose turn it is.
     *
     * @return int index of the player whose turn it is.
     */
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

    /**
     * Change turn of players.
     */
    public void changeTurnOfPlayers() {
        // if next player is out of bounds get first player of the list
        if (indexOfPlayerWhoseTurnItIs() + 1 >= this.players.size()) {
            this.players.get(indexOfPlayerWhoseTurnItIs()).changeTurn();
            this.players.get(0).changeTurn();
            System.out.println(getPlayerWhoseTurnItIs().getName() + " ist dran.");
            return;
        }
        // gets the next player of the players_list
        if (indexOfPlayerWhoseTurnItIs() + 1 < this.players.size()) {
            this.players.get(indexOfPlayerWhoseTurnItIs() + 1).changeTurn();
            this.players.get(indexOfPlayerWhoseTurnItIs()).changeTurn();
        }
        System.out.println(getPlayerWhoseTurnItIs().getName() + " ist dran.");

    }

    /**
     * Determine which player have the highest score.
     *
     * @param players list of all player
     * @return Player player
     */
    public Player getWinner(ArrayList<Player> players) {
        Player winner = new Player("", -1);
        for (Player player : players) {
            if (winner.getScore() < player.getScore()) {
                winner = player;
            }
        }
        return winner;
    }

    /**
     * Set ImageIcon of given Button to the BACKSIDE-ImageIcon in view.
     *
     * @param button
     */
    public void setCardsToBackside(JButton button) {
        view.setBackside(button);
    }

    //adds model
    public void addModel(CardManager m) {
        this.model = m;
    }

    //adds view
    public void addView(View v) {
        this.view = v;
    }

    /**
     * Init: Create cards in CardManager and links them to the Buttons[] of
     * view.
     */
    public void initModel() {
        model.createCards(view.getButtons());
        view.getGameInfo().append(this.getPlayerWhoseTurnItIs().getName() + " ist am Zug.\n     ");
    }

    /**
     * Is called when the game is over. - display winner in textAre_GameInfo
     */
    private void isGameOver() {
        int highestScoreOfAllPlayers = 0;
        for (Player player : this.players) {
            highestScoreOfAllPlayers += player.getScore();
        }
        if (highestScoreOfAllPlayers == ((view.getButtons().length) / 2)) {
            view.getGameInfo().append("\n\nSpielende\n");
            view.getGameInfo().append("***\nThe winner is...\n"
                    + this.getWinner(players).getName() + "\n***");

        }
    }

}
