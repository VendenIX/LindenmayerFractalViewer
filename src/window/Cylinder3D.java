package window;
import javafx.scene.shape.Cylinder;
import javafx.geometry.Point3D;
import javafx.scene.paint.PhongMaterial;
import javafx.geometry.*;
import javafx.scene.transform.Translate;
import javafx.scene.transform.Rotate;
import javafx.scene.paint.*;

/**
 * Cette classe permet de créer et gérer un cylindre3D
 */
public class Cylinder3D {
    
    private Point3D startingPoint, endingPoint;
    private double angle;
    private int radius, height_branch, divisions;

    //source: https://netzwerg.ch/blog/2015/03/22/javafx-3d-line/
    /**
     * Créer une ligne entre deux points (Cette ligne est un objet Cylinder)
     * @param startingPoint
     * @param endingPoint
     * @param RADIUS_BRANCH
     * @param div
     * @return Cylinder
     */
    public static Cylinder createConnection(Point3D startingPoint, Point3D endingPoint, int RADIUS_BRANCH , int div) {
        Point3D yAxis = new Point3D(0, 1, 0);
        Point3D diff = endingPoint.subtract(startingPoint);
        double height = diff.magnitude();
    
        Point3D mid = endingPoint.midpoint(startingPoint);
        Translate moveToMidpoint = new Translate(mid.getX(), mid.getY(), mid.getZ());
    
        Point3D axisOfRotation = diff.crossProduct(yAxis);
        double angle = Math.acos(diff.normalize().dotProduct(yAxis));
        Rotate rotateAroundCenter = new Rotate(-Math.toDegrees(angle), axisOfRotation);
    
        Cylinder line = new Cylinder(RADIUS_BRANCH, height);
        Material couleurCylinder = new PhongMaterial(javafx.scene.paint.Color.GREEN);
        line.setMaterial(couleurCylinder);
    
        line.getTransforms().addAll(moveToMidpoint, rotateAroundCenter);
        return line;
    }
    
}
