package memory;

import memory.control.Controller;
import memory.view.View;
import memory.model.CardManager;

/**
 *
 * @author cw
 */
public class Main {

    public static void main(String[] args) {
        //create Model and View
        CardManager myModel = new CardManager();
        View myView = new View();

        //create Controller. tell it about Model and View
        Controller myController = new Controller();
        myController.addModel(myModel);
        myController.addView(myView);

        //initialise model
        myController.initModel();

        //tell View about Controller 
        myView.addController(myController);
    }
}
