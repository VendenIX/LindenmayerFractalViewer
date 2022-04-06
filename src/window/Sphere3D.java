package window;
import javafx.scene.shape.Sphere;
import javafx.scene.paint.PhongMaterial;
import javafx.geometry.Point3D;
import javafx.geometry.*;
import javafx.scene.paint.*;
/**
 *  Cette classe permet de gérer des sphères qui fonctionnent comme des joints entre les différentes branches dans la version 3D
 */
public class Sphere3D {
    
    private int radius_sphere, nb_div_sphere;
    private Point3D center;

    public static Sphere creatreSphere3D(int radius_sphere, int nb_div_sphere, Point3D center) {
        Sphere sphere = new Sphere(radius_sphere, nb_div_sphere);
        sphere.setTranslateX(center.getX());
        sphere.setTranslateY(center.getY());
        sphere.setTranslateZ(center.getZ());
        Material couleurSphere = new PhongMaterial(javafx.scene.paint.Color.GREEN);
        sphere.setMaterial(couleurSphere);
        return sphere;
    }

    @Override
    public String toString() {
        return "Sphere3D[radius = " + this.radius_sphere + ", nb_div = " + this.nb_div_sphere + ", center : " + center.toString() + "]";
    }

    public int getNbDiv() {
        return this.nb_div_sphere;
    }
    
}
