package gioco.angolo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class AngleDrawingUtility {
    public static void drawAngleArc(GraphicsContext gc, double centerX, double centerY, double radius, double startAngle, double extent) {
        gc.setStroke(Color.LIGHTBLUE);

        Color transparentFillColor = new Color(0.79,1, 1, 0.2);
        gc.setFill(transparentFillColor);
        gc.setLineWidth(2);
        // Assicurati che l'angolo di inizio e l'ampiezza siano calcolati correttamente per il tuo caso d'uso
        gc.strokeArc(centerX - radius, centerY - radius, radius * 2, radius * 2, startAngle, extent, ArcType.OPEN);
        gc.fillArc(centerX - radius, centerY - radius, radius * 2, radius * 2, startAngle, extent, ArcType.ROUND);
    }
}
