package ui;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	private static MainStage mainStage = null;
	private static Controller controller = null;

	@Override
	public void start(Stage primaryStage) throws Exception {
		controller = new Controller();
		mainStage = new MainStage();
		mainStage.showStage();
	}

	protected static MainStage getMainStage() {
		return mainStage;
	}

	protected static Controller getController() {
		return controller;
	}
}
