package memory;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author cw
 */
public class Controller implements java.awt.event.ActionListener {

//Controller has Model and View hardwired in
    private Model model;
    private View view;
    private int counterOfCards = 0;

    private ArrayList<ImageIcon> chosenCards = new ArrayList<>();
    private ArrayList<JButton> chosenBtn = new ArrayList<>();
    private ArrayList<Integer> idsOfChosenBtns = new ArrayList<Integer>();

    Controller() {

    }

    //invoked when a button is pressed
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        Object source = e.getSource();

        if (counterOfCards == 2) {

            if (chosenCards.size() == 2) {
                if (chosenCards.get(0) == chosenCards.get(1)) {
                    if (view.isTurn()) {
                        System.out.println("Player 1 bekommt einen Punkt.");
                        view.incrementPlayer1Score();
                    }
                    if (!view.isTurn()) {
                        System.out.println("Player 2 bekommt einen Punkt.");
                        view.incrementPlayer2Score();
                    }
                    view.update();
                    for (JButton button : view.getButtons()) {
                        if (button == chosenBtn.get(0)) {
                            view.setBlack(chosenBtn.get(0));
                        }
                        if (button == chosenBtn.get(1)) {
                            view.setBlack(chosenBtn.get(1));
                        }
                    }
                }
                chosenCards.clear();
                chosenBtn.clear();
                view.changeTurn();
                System.out.println("Playerwechsel");

            }

            setCardsToBackside(idsOfChosenBtns.get(0));
            setCardsToBackside(idsOfChosenBtns.get(1));
            idsOfChosenBtns.clear();
            counterOfCards = 0;
        }

        for (int i = 0; i < view.getButtons().length; i++) {
            if (source == view.getButtons()[i]) {
                view.getButtons()[i].setIcon(model.getCardImage(i));
                idsOfChosenBtns.add(i);
                counterOfCards++;
                tempCards(i);
            }
        }
    }

    public void tempCards(int i) {
        view.update();
        if (view.isTurn()) {
            System.out.println("Player 1 ist dran");
        } else {
            System.out.println("Player 2 ist dran");
        }

        if (chosenCards.size() == 2) {
            if (chosenCards.get(0) == chosenCards.get(1)) {
                if (view.isTurn()) {
                    System.out.println("Player 1 bekommt einen Punkt.");
                    view.incrementPlayer1Score();
                }
                if (!view.isTurn()) {
                    System.out.println("Player 2 bekommt einen Punkt.");
                    view.incrementPlayer2Score();
                }
                view.update();
                for (JButton button : view.getButtons()) {
                    if (button == chosenBtn.get(0)) {
                        view.setBlack(chosenBtn.get(0));
                    }
                    if (button == chosenBtn.get(1)) {
                        view.setBlack(chosenBtn.get(1));
                    }
                }
            }
            chosenCards.clear();
            chosenBtn.clear();
            view.changeTurn();
            System.out.println("Playerwechsel");

        }
        chosenCards.add(model.getCardImage(i));
        chosenBtn.add(view.getButtons()[i]);
    }

    public void setCardsToBackside(int i) {
        view.setBackside(i);
    }

    public void addModel(Model m) {
        this.model = m;
    }

    public void addView(View v) {
        this.view = v;
    }

    public void initModel() {
        model.setCards();
    }

}
