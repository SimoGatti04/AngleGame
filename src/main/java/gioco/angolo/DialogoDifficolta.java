package gioco.angolo;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import java.util.Optional;

public class DialogoDifficolta {

    public static Optional<Difficolta> mostraDialogoDifficolta() {
        ChoiceDialog<Difficolta> dialog = new ChoiceDialog<>(Difficolta.FACILE, Difficolta.values());
        dialog.setTitle("Selezione Difficoltà");
        dialog.setHeaderText("Scegli la difficoltà del gioco");
        dialog.setContentText("Difficoltà:");

        // Mostra il dialogo e attende la risposta dell'utente
        Optional<Difficolta> risultato = dialog.showAndWait();
        return risultato;
    }
}
