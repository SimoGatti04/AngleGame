package gioco.angolo;

import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu {
    private VBox layout;
    private Scene scene;
    private GameView gameView;

    private GameController gameController;

    public MainMenu(Stage primaryStage) {
        Button startButton = new Button("Start");
        Button scoreButton = new Button("Scoreboard");
        Button leaderboardButton = new Button("Leaderboard");

        scoreButton.setVisible(false);
        leaderboardButton.setVisible(false);

        gameController = new GameController();
        gameView = new GameView(gameController);

        startButton.setOnAction(e -> primaryStage.setScene(gameView.getScene()));
        layout = new VBox (10);
        layout.getChildren().addAll(startButton, scoreButton, leaderboardButton);


        scene = new Scene(layout, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/gioco/angolo/GUI/styles/mainMenuStyle.css").toExternalForm());
    }

    public Scene getScene(){
        return scene;
    }
}


