package gioco.angolo;

import javafx.application.Application;
import javafx.stage.Stage;

public class AppInitializer extends Application {
    @Override
    public void start(Stage primaryStage) {
        MainMenu mainMenu = new MainMenu(primaryStage);
        primaryStage.setScene(mainMenu.getScene());
        primaryStage.setTitle("Angle Game");
        primaryStage.show();
    }
}
