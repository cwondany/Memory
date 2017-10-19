package memory;
/**
 * 
 * @author cw
 */
public class Main{

	public static void main(String[] args){

		//create Model and View
		Model myModel 	= new Model();
		View myView 	= new View();
                
		//tell Model about View. 
//		myModel.addObserver(myView);
		//myModel.setValue(start_value);	

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
