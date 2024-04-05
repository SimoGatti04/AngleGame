package gioco.angolo;


import gioco.angolo.angle.DrawAngle;
import gioco.angolo.difficolta.DialogoDifficolta;
import gioco.angolo.table.Attempt;
import gioco.angolo.table.AttemptTableManager;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
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

        initializeGameUI();
    }

    private void initializeGameUI() {

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


        Button submitButton = new Button("✔️");
        submitButton.getStylesheets().add(getClass().getResource("/gioco/angolo/GUI/styles/sendButtonStyle.css").toExternalForm());
        submitButton.setOnAction(e -> checkGuess());
        submitButton.getStyleClass().add("button-submitButton"); // Assicurati che il nome della classe corrisponda a quello definito nel CSS

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(20);
        gridPane.setMaxWidth(250);

        ColumnConstraints guessCol = new ColumnConstraints();
        guessCol.setPrefWidth(120);
        guessCol.setHalignment(HPos.RIGHT);


        ColumnConstraints buttonCol = new ColumnConstraints();
        submitButton.setPrefWidth(60);
        buttonCol.setHalignment(HPos.LEFT);

        gridPane.getColumnConstraints().addAll(guessCol, buttonCol);
        gridPane.add(guessField, 0, 0);
        gridPane.add(submitButton, 1, 0);


        root.getChildren().addAll(canvas, gridPane, attemptTableManager.getAttemptsTable());

    }

    private void checkGuess() {
        try {
            double userGuess = Double.parseDouble(guessField.getText());
            if (userGuess < 0 || userGuess > 360) {
                throw new NumberFormatException("L'angolo deve essere compreso tra 0 e 360 gradi");
            }
            boolean isCorrect = gameController.checkGuess(userGuess);

            String angleComparison;
            if (userGuess < gameController.getAngle()) {
                angleComparison = ("higher");
            }
            else if (userGuess > gameController.getAngle()) {
                angleComparison = ("lower");
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
            System.out.println(e.getMessage());
            guessField.setText("");
            return;
        }
        if (attemptTableManager.primoTentativo()){
            difficoltaScelta.getComboBox().setDisable(true);
            difficoltaScelta.getComboBox().setVisible(false);
        }
        guessField.setText("");
    }


    public Scene getScene() {

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/gioco/angolo/GUI/styles/style.css").toExternalForm());

        return scene;
    }
}