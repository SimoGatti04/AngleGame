package gioco.angolo;

import gioco.angolo.difficolta.Difficolta;

import java.util.Random;

public class GameController {

    private double angle;
    private int tolleranza = 5;
    private Difficolta difficolta;

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
        return Math.abs(guess - angle) <= tolleranza; // Tolleranza di 0 gradi
    }

    public void setTolleranza(int tolleranza) {
        this.tolleranza = tolleranza;
    }
}
