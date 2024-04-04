package gioco.angolo;


import gioco.angolo.angle.DrawAngle;
import gioco.angolo.difficolta.DialogoDifficolta;
import gioco.angolo.table.Attempt;
import gioco.angolo.table.AttemptTableManager;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class GameView {

    private final GameController gameController;
    private VBox root;
    private Canvas canvas;
    private TextField guessField;
    private AttemptTableManager attemptTableManager;
    private DialogoDifficolta difficoltaScelta;
    private DrawAngle drawAngle;

    public GameView(GameController gameController) {

        this.gameController = gameController;
        this.attemptTableManager = new AttemptTableManager();
        this.drawAngle = new DrawAngle();
        this.difficoltaScelta = new DialogoDifficolta();

        initializeUI();
    }

    private void initializeUI() {

        Font.loadFont(getClass().getResourceAsStream("/gioco/angolo/GUI/Font_ComboBox.ttf"), 14);

        root = new VBox(10);
        root.setAlignment(Pos.CENTER); // Assicura che i componenti siano centrati

        root.getChildren().add(difficoltaScelta.getComboBox());
        difficoltaScelta.getComboBox().getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal)->{
            gameController.setTolleranza(newVal.getTolleranza());
        });

        canvas = new Canvas(300, 300);
        double angle = gameController.getAngle();
        drawAngle.drawAngle(canvas.getGraphicsContext2D(), angle);


        guessField = new TextField();
        guessField.setPromptText("???");
        guessField.setMaxWidth(60);
        guessField.getStyleClass().add("guessField");


        Button submitButton = new Button("GUESS");
        submitButton.setOnAction(e -> checkGuess());
        submitButton.getStyleClass().add("button-submitButton"); // Assicurati che il nome della classe corrisponda a quello definito nel CSS


        root.getChildren().addAll(canvas, guessField, submitButton, attemptTableManager.getAttemptsTable());

    }

    private void checkGuess() {
        if (attemptTableManager.primoTentativo()){
            difficoltaScelta.getComboBox().setDisable(true);
            difficoltaScelta.getComboBox().setVisible(false);
        }
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