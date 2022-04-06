package window;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

import geometric.*;
import geometric.Point;
import lsystem.*;

import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.geometry.Point3D;
import javafx.application.*;
import javafx.beans.value.*;
import javafx.scene.PerspectiveCamera;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.web.*;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.Sphere;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.net.MalformedURLException;
import java.net.URL;


/**
* Cette classe gère le rendu 3D dans son ensemble
*/
public class Display3D extends JFXPanel {

    private int RADIUS_BRANCH = 1;
    private int HEIGHT_BRANCH = 500;
    private int NB_DIV_BRANCH = 50;
    private int RADIUS_SPHERE = 100;
    private int NB_DIV_SPHERE = 30;

    public static ArrayList<Line> map;
    public Group root = new Group();
    public Scene scene;
    public PerspectiveCamera camera1 = new PerspectiveCamera(true);

    /**
     * Constructeur qui initialise la scène avec l'arbre généré dans la classe LSystem
     * @param map
     */
    public Display3D(ArrayList<Line> map) {
        super();
        this.scene = new Scene(root,400,400, true);
        Display3D.map = map;
        createScene();
        initCamera1();
        setScene(this.scene);
    }

    /**
     * Fonction qui initialise la caméra
     */
    public void initCamera1() {
         // true positionne la camera en x y z = 0 0 0
        int camSpeed = 100;
        camera1.setTranslateZ(-3000); // elle est a 2000 pixel devant mon ecran, donc utile pour avoir un point de vue spectateur realiste
        camera1.setTranslateY(-1000);
        camera1.setRotationAxis(Rotate.X_AXIS);
        camera1.setNearClip(0.1);
        camera1.setFarClip(10000);
        camera1.setRotate(-23);
        scene.setOnScroll(event -> {
            camera1.setTranslateZ(camera1.getTranslateZ() + event.getDeltaY());
        });
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                switch (event.getCode()) {
                    case RIGHT:
                        scene.getCamera().setTranslateX(camera1.getTranslateX() + camSpeed);
                        break;

                    case LEFT:
                        scene.getCamera().setTranslateX(camera1.getTranslateX() - camSpeed);
                        break;

                    case UP:
                        scene.getCamera().setTranslateY(camera1.getTranslateY() - camSpeed);
                        break;
                        
                    case DOWN:
                        scene.getCamera().setTranslateY(camera1.getTranslateY() + camSpeed);
                        break;

                    case NUMPAD8:
                        scene.getCamera().setRotationAxis(new Point3D(1, 0, 0));
                        scene.getCamera().setRotate(scene.getCamera().getRotate() + 5);
                        break;

                    case NUMPAD2:
                        scene.getCamera().setRotationAxis(new Point3D(1, 0, 0));
                        scene.getCamera().setRotate(scene.getCamera().getRotate() - 5);
                        break;

                    case SPACE:
                        rotateCam(new Rotate(55, Rotate.Y_AXIS));
                        break;
                }
            }
        });
        scene.setCamera(camera1);
        root.getChildren().add(camera1);
    }

    /**
     * Fonction qui crée la scène et donc par extension l'arbre
     */
    private void createScene() {

        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                //Initialisation du terrain (Donc là faudra enlever les figures de tests et faire du gazon, un sol je veux dire)
                for (int i = 0; i < map.size(); i++) {
                    if(i == 4) {
                        Point p1 = Point.rotateZbyX(map.get(i).getStartingPoint(), map.get(i).getEndingPoint(), 0);
                        Point3D res = new Point3D(p1.getX(),p1.getY(),p1.getZ());
                        root.getChildren().add(Cylinder3D.createConnection(new Point3D(map.get(i).getStartingPoint().getX(),map.get(i).getStartingPoint().getY(),0), res,RADIUS_BRANCH , 50));
                        root.getChildren().add(Sphere3D.creatreSphere3D(RADIUS_BRANCH, 50, res));
                    } else {
                        root.getChildren().add(Cylinder3D.createConnection(new Point3D(map.get(i).getStartingPoint().getX(),map.get(i).getStartingPoint().getY(),0), new Point3D(map.get(i).getEndingPoint().getX(), map.get(i).getEndingPoint().getY(), 0),RADIUS_BRANCH , 50));
                        root.getChildren().add(Sphere3D.creatreSphere3D(RADIUS_BRANCH, 50, new Point3D(map.get(i).getEndingPoint().getX(),map.get(i).getEndingPoint().getY(),0)));
                    }
                }

                //Colorimétrie du ciel
                scene.setFill(new RadialGradient(
                0, 0, 0, 0, 1, true, //sizing
                CycleMethod.NO_CYCLE, //cycling
                new Stop(1, Color.web("#000000")))
                );

            }
        });
    }

    private void rotateCam(Rotate rotation) {
        Transform currTransform;
        currTransform = new Rotate();
        currTransform = currTransform.createConcatenation(rotation);
        camera1.getTransforms().clear();
        camera1.getTransforms().addAll(currTransform);
    }

}

