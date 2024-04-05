package gioco.angolo.table;

import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;

public class AttemptTableManager {
    private TableView<Attempt> attemptsTable;
    public AttemptTableManager() {
        attemptsTable = new TableView<>();
        initializeAttemptsTable();
    }

    public TableView<Attempt> getAttemptsTable() {
        return attemptsTable;
    }
    public void initializeAttemptsTable(){
        TableColumn<Attempt, Integer> attemptNoColumn = new TableColumn<>("N.");
        attemptNoColumn.setCellValueFactory(new PropertyValueFactory<>("attemptNo"));
        attemptNoColumn.setPrefWidth(30); // Imposta una larghezza preferita
        attemptNoColumn.setResizable(false);

        TableColumn<Attempt, Double> guessColumn = new TableColumn<>("Guess");
        guessColumn.setCellValueFactory(new PropertyValueFactory<>("guess"));
        guessColumn.setPrefWidth(50); // Imposta una larghezza preferita
        guessColumn.setResizable(false);

        TableColumn<Attempt, String> angleComparisonColumn = new TableColumn<>("Hint");
        angleComparisonColumn.setCellValueFactory(new PropertyValueFactory<>("angleComparison"));
        angleComparisonColumn.setPrefWidth(50); // Imposta una larghezza preferita
        angleComparisonColumn.setResizable(false);



        attemptsTable.setMaxWidth(135);
        attemptsTable.setEditable(false);
        attemptsTable.setPlaceholder(new Label(""));



        attemptsTable.getColumns().add(attemptNoColumn);
        attemptsTable.getColumns().add(guessColumn);
        attemptsTable.getColumns().add(angleComparisonColumn);

        attemptsTable.setPrefHeight(TableView.USE_COMPUTED_SIZE);
        attemptsTable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

        attemptsTable.getStylesheets().add(getClass().getResource("/gioco/angolo/GUI/styles/resultTableStyle.css").toExternalForm());

    }

    public void clearAttemptsTable() {
        // Pulisce tutti gli elementi dalla tabella
        attemptsTable.getItems().clear();
    }

    public boolean primoTentativo(){
        return attemptsTable.getItems().isEmpty();
    }
}