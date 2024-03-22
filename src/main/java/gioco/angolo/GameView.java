package gioco.angolo;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class GameView {
    private final GameController gameController;
    private VBox root;
    private Canvas canvas;
    private TextField guessField;

    public GameView(GameController gameController) {
        this.gameController = gameController;
        initializeUI();
    }

    private void initializeUI() {
        root = new VBox(10);
        root.setAlignment(Pos.CENTER); // Assicura che i componenti siano centrati

        canvas = new Canvas(200, 200);
        drawAngle();

        guessField = new TextField();
        guessField.setPromptText("Indovina l'angolo (0-360)");

        Button submitButton = new Button("Invia");
        submitButton.setOnAction(e -> checkGuess());

        root.getChildren().addAll(canvas, guessField, submitButton);
    }

    private void drawAngle() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); // Pulisce il canvas prima di disegnare un nuovo angolo
        gc.setStroke(Color.RED);
        gc.setLineWidth(2);
        // Calcola le coordinate dell'angolo basato sul valore corrente in gameController
        double angle = gameController.getAngle();
        gc.strokeLine(100, 100, 150, 100); // Base
        double endX = 100 + 50 * Math.cos(Math.toRadians(angle));
        double endY = 100 - 50 * Math.sin(Math.toRadians(angle));
        gc.strokeLine(100, 100, endX, endY); // Lato inclinato
    }

    private void checkGuess() {
        try {
            double userGuess = Double.parseDouble(guessField.getText());
            if (gameController.checkGuess(userGuess)) {
                System.out.println("Corretto! L'angolo era: " + gameController.getAngle());
                gameController.generateNewAngle(); // Genera un nuovo angolo
                drawAngle(); // Ridisegna l'angolo
            } else {
                System.out.println("Sbagliato! Riprova.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Per favore, inserisci un numero valido.");
        }
    }

    public Scene getScene() {
        return new Scene(root, 400, 400);
    }
}