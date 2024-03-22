package gioco.angolo;

import java.util.Random;

public class GameController {
    private double angle;

    public GameController() {
        generateNewAngle();
    }

    public void generateNewAngle() {
        this.angle = new Random().nextInt(361); // Genera un nuovo angolo
    }

    public double getAngle() {
        return angle;
    }

    public boolean checkGuess(double guess) {
        return Math.abs(guess - angle) < 1; // Tolleranza di 0 gradi
    }
}
