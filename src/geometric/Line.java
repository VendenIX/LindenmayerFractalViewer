/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometric;

import javax.swing.JPanel;
import java.awt.Graphics;
/**
 * Cette classe permet de créer et gérer des lignes
 */
public class Line extends JPanel {

    public Point startingPoint;
    public Point endingPoint;
    public double length;
    public double angle;
    public int id;
    
    /**
     * Créer un objet line avec un point et un angle 
     * @param point
     * @param length
     * @param angle
     */
    public Line( int id, Point point, double length, double angle) {
    	this.startingPoint = point;
    	this.endingPoint = Point.rotateLineClockWise(point, new Point(point.getX()-length,point.getY(),point.getZ()), angle);
    	this.angle = angle;
    	this.length = length;
        this.id = id;
    }

    /**
     * Créer un objet line avec deux points
     * @param id
     * @param startingPoint
     * @param endingPoint
     */
    public Line(int id, Point startingPoint, Point endingPoint) {
        this.id = id;
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
        // faut que je calcul l'angle 
        // faut que je calcul la longueur
    }
    

    
    /**
     * Donne l'angle actuel de la ligne
     * @return un angle sous forme de double
     */
    public double getAngle() {
    	return this.angle;
    }
    
    /**
     * Donne la longueur de la ligne
     * @return un longueur sous forme de longueur
     */
    public double getLength() {
        //RESULTAT FAUX
    	return this.length;
    }
    
    /**
     * Redéfinit l'angle par le paramètre qui est donc un double
     * @param angle
     */
    public void setAngle(double angle) {
    	this.angle = angle;
        //je dois bouger la ligne
        Point p = Point.rotateLineClockWise(this.startingPoint, this.endingPoint, angle - 90);
        setEndingPoint(p);
    }

    public void setLength(double length) {
        this.length = length;
    }
    
    /**
     * Affiche la ligne en console
     */
    @Override
    public String toString() {
    	return "Line{id = " + this.id + ", Depart = (" + startingPoint.getX() + "," + startingPoint.getY() + "," + startingPoint.getZ() + ")" + " , Arrive = (" + endingPoint.getX() + "," + endingPoint.getY() + "), Longueur = " + this.length + " , Angle = " + this.angle + "}";
    }
    
    /**
     * Dessine la ligne avec le graphic pris en paramètre
     * @param g
     */
    public void drawLine(Graphics g) {
        g.drawLine((int)startingPoint.getX(),(int) startingPoint.getY(),(int) endingPoint.getX(),(int) endingPoint.getY());       
    }
    
    /**
     * Redéfinit le startingPoint de notre ligne par le point p pris en paramètre
     * ATTENTION cette fonction modifie le endingPoint !
     * @param p
     */
    public void setStartingPoint(Point p) {
    	
    	double differenceX = p.getX() - this.startingPoint.getX();
    	double differenceY = p.getY() - this.startingPoint.getY();
        double differenceZ = p.getZ() - this.startingPoint.getZ();
    	this.endingPoint = new Point(this.endingPoint.getX() + differenceX, this.endingPoint.getY() + differenceY, this.endingPoint.getZ() + differenceZ);
    	this.startingPoint = p;
    }
    
    /**
     * Redéfinit le endingPoint de notre ligne par le point p pris en paramètre
     * @param p
     */
    public void setEndingPoint(Point p) {
    	this.endingPoint = p;
    }
    
    /**
     * Donne le starting point de notre ligne 
     * @return startingPoint
     */
    public Point getStartingPoint() {
    	return this.startingPoint;
    }
    
    /**
     * Donne le endingPoint de notre ligne
     * @return endingPoint
     */
    public Point getEndingPoint() {
    	return this.endingPoint;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

}
