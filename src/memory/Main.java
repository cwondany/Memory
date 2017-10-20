package memory;

import memory.control.GameController;
import memory.control.AudioPlayer;
import memory.view.View;
import memory.model.CardManager;

/**
 * Main class starts Model, View, Controller of
 * the MVC designed application.
 * @author cw
 */
public class Main {

    public static void main(String[] args) {

        //create Model and View
        CardManager myModel = new CardManager();
        View myView = new View();

        //create Controller. tell it about Model and View
        GameController myController = new GameController();
        myController.addModel(myModel);
        myController.addView(myView);

        //initialise model
        myController.initModel();
        //tell View about Controller 
        myView.addController(myController); 

        //starts background music:
        String audioFilePath = "src/assets/music.wav";
        AudioPlayer player = new AudioPlayer();
        player.play(audioFilePath);
    }

}
