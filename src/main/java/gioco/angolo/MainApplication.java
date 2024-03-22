package gioco.angolo;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        GameController gameController = new GameController();
        GameView gameView = new GameView(gameController);
        primaryStage.setScene(gameView.getScene());
        primaryStage.setTitle("Indovina l'Angolo");
        primaryStage.show();
    }
}
