package gioco.angolo;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.shape.ArcType;


public class GameView {
    private final GameController gameController;
    private VBox root;
    private Canvas canvas;
    private TextField guessField;
    private TableView<Attempt> attemptsTable;

    public GameView(GameController gameController) {
        this.gameController = gameController;
        initializeUI();
    }

    private void initializeUI() {
        root = new VBox(10);
        root.setAlignment(Pos.CENTER); // Assicura che i componenti siano centrati

        canvas = new Canvas(300, 300);
        drawAngle();

        guessField = new TextField();
        guessField.setPromptText("Indovina l'angolo (0-360)");
        guessField.setMaxWidth(60);
        guessField.getStyleClass().add("guessField");


        Button submitButton = new Button("INVIA");
        submitButton.setOnAction(e -> checkGuess());
        submitButton.getStyleClass().add("button-submitButton"); // Assicurati che il nome della classe corrisponda a quello definito nel CSS


        attemptsTable = new TableView<>();
        initializeAttemptsTable();


        root.getChildren().addAll(canvas, guessField, submitButton, attemptsTable);

    }

    private void initializeAttemptsTable(){
        TableColumn<Attempt, Integer> attemptNoColumn = new TableColumn<>("N.");
        attemptNoColumn.setCellValueFactory(new PropertyValueFactory<>("attemptNo"));
        attemptNoColumn.setPrefWidth(30); // Imposta una larghezza preferita
        attemptNoColumn.setResizable(false);

        TableColumn<Attempt, Double> guessColumn = new TableColumn<>("Guess");
        guessColumn.setCellValueFactory(new PropertyValueFactory<>("guess"));
        guessColumn.setPrefWidth(60); // Imposta una larghezza preferita
        guessColumn.setResizable(false);

        TableColumn<Attempt, String> angleComparisonColumn = new TableColumn<>("Suggerimento");
        angleComparisonColumn.setCellValueFactory(new PropertyValueFactory<>("angleComparison"));
        angleComparisonColumn.setPrefWidth(100); // Imposta una larghezza preferita
        angleComparisonColumn.setResizable(false);



        attemptsTable.setMaxWidth(190);
        attemptsTable.setEditable(false);
        attemptsTable.setPlaceholder(new Label(""));



        attemptsTable.getColumns().add(attemptNoColumn);
        attemptsTable.getColumns().add(guessColumn);
        attemptsTable.getColumns().add(angleComparisonColumn);

        attemptsTable.setPrefHeight(TableView.USE_COMPUTED_SIZE);
        attemptsTable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);


    }
        public static class Attempt {
            private final SimpleIntegerProperty attemptNo;
            private final SimpleDoubleProperty guess;
            private final SimpleStringProperty angleComparison;

            public Attempt(int attemptNo, double guess, String angleComparison){
                this.attemptNo = new SimpleIntegerProperty(attemptNo);
                this.guess = new SimpleDoubleProperty(guess);
                this.angleComparison = new SimpleStringProperty(angleComparison);
            }

            public String getAngleComparison() {
                return angleComparison.get();
            }
            public SimpleStringProperty angleComparisonProperty() {
                return angleComparison;
            }
            public int getAttemptNo() {
                return attemptNo.get();
            }

            public SimpleIntegerProperty attemptNoProperty() {
                return attemptNo;
            }

            public double getGuess() {
                return guess.get();
            }

            public SimpleDoubleProperty guessProperty() {
                return guess;
            }
        }
    private void drawAngle() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); // Pulisce il canvas prima di disegnare un nuovo angolo
        gc.setStroke(Color.RED);
        gc.setLineWidth(3);

        double angle = gameController.getAngle();

        // Calcola le coordinate dell'angolo basato sul valore corrente in gameController
        double anchorX = canvas.getWidth() / 2; // Centra l'angolo orizzontalmente
        double anchorY = canvas.getHeight() / 2; // Posiziona la base dell'angolo al centro verticalmente

        double inclinedLength = canvas.getWidth() * 0.3; // Lunghezza dell'angolo inclinato
        double inclinedEndX = anchorX + inclinedLength * Math.cos(Math.toRadians(angle));
        double inclinedEndY = anchorY - inclinedLength * Math.sin(Math.toRadians(angle));

        gc.strokeLine(anchorX,anchorY,inclinedEndX, inclinedEndY);

        double baseLength = inclinedLength;
        double baseEndX = anchorX + baseLength;

        gc.strokeLine(anchorX, anchorY, baseEndX, anchorY);

        double startAngle = 0;
        double extent = angle;

        double radius = 20;
        AngleDrawingUtility.drawAngleArc(gc, anchorX, anchorY, radius, startAngle, extent);
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

            Attempt attempt = new Attempt(attemptsTable.getItems().size() + 1, userGuess, angleComparison);
            attemptsTable.getItems().add(attempt);

            if (isCorrect) {
                clearAttemptsTable();
                gameController.generateNewAngle();
                drawAngle();
            }
            /*else {
                System.out.println("Sbagliato, riprova" );
            }*/
        } catch (NumberFormatException e) {
            System.out.println("Per favore, inserisci un numero valido.");
        }
        guessField.setText("");
    }

    private void clearAttemptsTable() {
        // Pulisce tutti gli elementi dalla tabella
        attemptsTable.getItems().clear();
    }


    public Scene getScene() {
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/gioco/angolo/GUI/style.css").toExternalForm());
        return scene;
    }
}