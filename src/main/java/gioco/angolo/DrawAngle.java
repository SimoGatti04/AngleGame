package gioco.angolo;

import gioco.angolo.angle.AngleDrawingUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawAngle{
    public void drawAngle(GraphicsContext gc, double angle) {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight()); // Pulisce il canvas prima di disegnare un nuovo angolo
        gc.setStroke(Color.RED);
        gc.setLineWidth(3);

        // Calcola le coordinate dell'angolo basato sul valore corrente in gameController
        double anchorX = gc.getCanvas().getWidth() / 2; // Centra l'angolo orizzontalmente
        double anchorY = gc.getCanvas().getHeight() / 2; // Posiziona la base dell'angolo al centro verticalmente

        double inclinedLength = gc.getCanvas().getWidth() * 0.3; // Lunghezza dell'angolo inclinato
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
}