package gioco.angolo;

public enum Difficolta {
    FACILE(5, 5),
    MEDIO(4, 3),
    DIFFICILE(4, 1),
    ESTREMO(3,0);

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
