package gioco.angolo.difficolta;

public enum Difficolta {
    EASY(5, 5),
    MEDIUM(4, 3),
    HARD(4, 1),
    EXTREME(3,0);

    private final int tentativi;
    private final int tolleranza;

    Difficolta(int tentativi, int tolleranza){
        this.tentativi = tentativi;
        this.tolleranza = tolleranza;
    }

    public int getTentativi() {
        return tentativi;
    }

    public int getTolleranza() {
        return tolleranza;
    }
}
