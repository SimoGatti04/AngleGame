package gioco.angolo.difficolta;

import javafx.scene.control.ComboBox;

public class DialogoDifficolta {

    private ComboBox<Difficolta> comboBoxDifficolta;

    public DialogoDifficolta() {
        comboBoxDifficolta = new ComboBox<>();
        comboBoxDifficolta.getItems().addAll(Difficolta.values());
        comboBoxDifficolta.setValue(Difficolta.EASY);
    }

    public ComboBox<Difficolta> getComboBox(){
        return comboBoxDifficolta;
    }
}

