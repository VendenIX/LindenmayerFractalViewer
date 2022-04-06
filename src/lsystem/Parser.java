package lsystem;

import java.awt.Dimension;
import java.util.ArrayList;

import geometric.*;
/**
 * Classe qui sert à parser une string
 * Parser = analyser 
 * La classe possede une methode d'anaylse isValid() qui dit si la string est assez cohérente pour etre desinee
 * La classe possede une methode d'analyse parse() qui converti la string si elle est coherente en array de lines
 */
public class Parser {

    public String chaine;
    private double LONGUEUR;
    private double ANGLE;
    private String charAutorise = "+-[]012FX";
    
    /* '+' l'angle est positif  */
    /* '-' l'angle est negatif */
    /* 'F' && 'X' ligne */
    /* '[' debut de branche*/
    /* ']' fin de branche*/
    /* '0' couleur marron */
    /* '1' couleur vert fonce */
    /* '2' couleur vert clair */
    
    /**
     * Constructeur du parser qui prend une chaine en entrée
     * @param chaine
     */
    public Parser(String chaine, double LONGUEUR, double ANGLE) {
        this.chaine = chaine;
        this.LONGUEUR = LONGUEUR;
        this.ANGLE = ANGLE;
    }

    /**
     * Dit si la string est valide
     * @return
     */
    public boolean isValid() {
        return (isLexical() && isSyntaxic());   
    }

    /**
     * Regarde si la string est correcte au niveau lexical (que les caractères soient tous autorisés)
     * @return boolean
     */
    public boolean isLexical() {

    	int cpt = 0;
        for(int i=2; i<chaine.length(); i++) {
        	String c = "";
        	c = c + (chaine.charAt(i));
            if(!charAutorise.contains(c)) {
            		System.out.println("Le caractère '" + c + "' n'est pas autorise");
            		return false;
            }
            c = "";
            
            if (chaine.charAt(i) == '[') {
                cpt++;
            }
            if (chaine.charAt(i) == ']') {
                cpt--;
            }
        }
        if(cpt != 0) {
            System.out.println("Il manque un crochet" + chaine);
            return false;     
        }
        return true;
    }

    /**
     * Regarde si la string comporte des mots cohérents
     * @return
     */
    public boolean isSyntaxic() {

        for(int i=0; i<chaine.length(); i++) {
            if(chaine.charAt(i) == '[') {
                //Si on a [] je return false
                if(i+1 <= chaine.length()-1) {
                    if(chaine.charAt(i+1) == ']') {
                        System.out.println(" '[]' interdit");
                        return false;
                    }
                }
                //Si on a [[A]] je return false
                //Si je trouve encore un  crochet apres mon crochet actuel
                if(i+1 <= chaine.length()-1) {
                    if(chaine.charAt(i+1) == '[') {
                        //Je parcours le reste
                        for(int j=i+2;j<chaine.length();j++) {
                            if(chaine.charAt(j) == ']') {
                                //System.out.println("autre");
                                if(j+1 <= chaine.length()-1) {
                                    if(chaine.charAt(j+1) == ']') {
                                        System.out.println(" double crochets inutiles");
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                }
                //Si on n'a plus de fermente je return false
                int compteurCrochetFerment = 0;
                for(int k=i; k<chaine.length(); k++) {
                    if(chaine.charAt(k) == ']') {
                        compteurCrochetFerment = compteurCrochetFerment + 1;
                    }
                }
                if(compteurCrochetFerment == 0) {
                    System.out.println("Mauvaise encapsulation des branches : " + compteurCrochetFerment);
                    return false;
                }
            }
            // Regarde si un C est suivi de 0 ou 1 ou 2
            if(chaine.charAt(i) == 'C') {
                if(chaine.charAt(i+1) == '0' || chaine.charAt(i+1) == '1' || chaine.charAt(i+1) == '2') {
                } 
                else {
                    System.out.println("Mauvaise couleur");
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Redefini l'angle par l'angle pris en paramètre
     * @param angle
     */
    public void setAngle(double angle) {
        this.ANGLE = angle;
    }

    /**
     * Donne l'angle actuel
     * @return
     */
    public double getAngle() {
        return this.ANGLE;
    }

    /**
     * PARSE String -> Lines
     * Converti la chaine de caractere en tableau de lignes
     * @return res
     */
    public ArrayList<Line> parseStringtoLines() {
        //pour recupérer les dimensions de l'ecran
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
        double currentAngle = 90;
        int currentId = 0;
        ArrayList<Line> res = new ArrayList<Line>();
        Point currentEndingPoint = new Point(0,0,0);
        ArrayList<Point> pointsEncrages = new ArrayList<>();
        ArrayList<Double> pointsEncragesAngles = new ArrayList<>();
        ArrayList<Integer> pointsEncragesId = new ArrayList<>();
        pointsEncrages.add(new Point(width/2,height-120,0));

        for (int i = 0; i < chaine.length(); i++) {

            if (chaine.charAt(i) == '1') {
                currentId = 1;
            }

            if (chaine.charAt(i) == '2') {
                currentId = 2;
            }

            if (chaine.charAt(i) == '+') {
                currentAngle += ANGLE;
            }

            if (chaine.charAt(i) == '-') {
                currentAngle -= ANGLE;
            }

            if (chaine.charAt(i) == '[') {
                pointsEncrages.add(currentEndingPoint);
                pointsEncragesAngles.add(currentAngle);
                pointsEncragesId.add(currentId);
            }

            if (chaine.charAt(i) == ']') {
                currentEndingPoint = pointsEncrages.get(pointsEncrages.size()-1);
                currentAngle = pointsEncragesAngles.get(pointsEncragesAngles.size()-1);
                pointsEncrages.remove(pointsEncrages.size()-1);
                pointsEncragesAngles.remove(pointsEncragesAngles.size()-1);
                pointsEncragesId.remove(pointsEncragesId.size()-1);
            }

            if ((chaine.charAt(i) == 'F' || chaine.charAt(i) == 'X')) { //Si c'est une ligne
                //si je suis la premiere ligne
                if (currentEndingPoint.getX() == 0 && currentEndingPoint.getY() == 0 && currentEndingPoint.getZ() == 0) {
                    Line F = new Line(currentId,pointsEncrages.get(0),LONGUEUR,90);
                    currentEndingPoint = F.endingPoint;
                    res.add(F);
                } else {
                    // si je suis pas la premiere ligne
                    Line F = new Line(currentId,currentEndingPoint,LONGUEUR,currentAngle);
                    res.add(F);
                    currentEndingPoint = F.endingPoint;
                    currentAngle = F.getAngle();
                }
            }
        }
        return res;
    }

    /**
     * Compte le nombre de F dans la string
     * @param axiome
     * @return nombre
     */
    public int getNbAxiomes() {
        int cpt = 0;
        for (int i = 0; i < chaine.length(); i++) {
            if (chaine.charAt(i) == 'F') {
                cpt++;
            }
        }
        return cpt;
    }
}