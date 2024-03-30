package gioco.angolo;
import gioco.angolo.table.Attempt;
import gioco.angolo.table.AttemptTableManager;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class GameView {
    private final GameController gameController;
    private VBox root;
    private Canvas canvas;
    private TextField guessField;
    private AttemptTableManager attemptTableManager;
    private DrawAngle drawAngle;

    public GameView(GameController gameController) {
        this.gameController = gameController;
        this.attemptTableManager = new AttemptTableManager();
        this.drawAngle = new DrawAngle();
        initializeUI();
    }

    private void initializeUI() {
        root = new VBox(10);
        root.setAlignment(Pos.CENTER); // Assicura che i componenti siano centrati

        canvas = new Canvas(300, 300);
        double angle = gameController.getAngle();
        drawAngle.drawAngle(canvas.getGraphicsContext2D(), angle);

        guessField = new TextField();
        guessField.setPromptText("Indovina l'angolo (0-360)");
        guessField.setMaxWidth(60);
        guessField.getStyleClass().add("guessField");


        Button submitButton = new Button("INVIA");
        submitButton.setOnAction(e -> checkGuess());
        submitButton.getStyleClass().add("button-submitButton"); // Assicurati che il nome della classe corrisponda a quello definito nel CSS


        root.getChildren().addAll(canvas, guessField, submitButton, attemptTableManager.getAttemptsTable());

    }

    private void checkGuess() {
        try {
            double userGuess = Double.parseDouble(guessField.getText());
            boolean isCorrect = gameController.checkGuess(userGuess);

            String angleComparison;
            if (userGuess < gameController.getAngle()) {
                angleComparison = ("Più alto");
            }
            else if (userGuess > gameController.getAngle()) {
                angleComparison = ("Più basso");
            }
            else   {
                angleComparison = ("Corretto");
            }

            Attempt attempt = new Attempt(attemptTableManager.getAttemptsTable().getItems().size() + 1, userGuess, angleComparison);
            attemptTableManager.getAttemptsTable().getItems().add(attempt);

            if (isCorrect) {
                attemptTableManager.clearAttemptsTable();
                gameController.generateNewAngle();
                drawAngle.drawAngle(canvas.getGraphicsContext2D(), gameController.getAngle());
            }
        } catch (NumberFormatException e) {
            System.out.println("Per favore, inserisci un numero valido.");
        }
        guessField.setText("");
    }


    public Scene getScene() {
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/gioco/angolo/GUI/style.css").toExternalForm());
        return scene;
    }
}