package gioco.angolo.table;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Attempt {
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