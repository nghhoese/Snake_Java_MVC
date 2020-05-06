package Controller;


import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
	private Controller controller;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
		
	}
	@Override
	public void start(Stage stage) {
		controller = new Controller(stage);
		
	}

}
