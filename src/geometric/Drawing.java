package geometric;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Cette classe permet de faire un objet Dessin qui est une dictionnaire de lignes et de le gérer 
 */
public class Drawing {

	private ArrayList<Line> lines;

	/* Initialise un dessin sans lignes donc un ArrayList vide
	 */
	public Drawing() {
		this.lines = new ArrayList<Line>();
	}
	
	/**
	 * Initialise un ArrayList de lignes (pris en paramètres) donc un dessin
	 * @param lines
	 */
	public Drawing(ArrayList<Line> lines) {
		this.lines = lines;
	}

	/**
	 * Donne le ArrayList de lignes du dessin
	 * @return ArrayList<Line>
	 */
	public ArrayList<Line> getLines() {
		return lines;
	}
	
	/**
	 * Affiche chaque ligne de notre ArrayList de lignes en console
	 */
	@Override
	public String toString() {
		String res = "";
		for(Line l :this.lines)
		{
			res = res + l.toString() + "\n";
		}
		return res;
	}
	
	
	/**
	 * Desine chaque ligne de notre ArrayList de lignes
	 * @param g
	 */
	public void draw2D(Graphics g) {
		//System.out.println("Attention, je lance le dessin !");
		float cpt = 0;
		for(Line l : this.lines)
		{
			if (l.id == 0) {
				g.setColor(new Color(51,25,0));
			}

			if (l.id == 1) {
				g.setColor(new Color(0,153,0));
			}

			if (l.id == 2) {
				g.setColor(new Color(51,102,0));
			}
			l.drawLine(g);
			cpt++;
		}
	}
	
	/**
	 * Desine chaque ligne d'un ArrayList de lignes
	 * @param g
	 */
	public void draw2D(ArrayList<Line> lignes, Graphics g) {
		for(Line l : lignes)
		{
			l.drawLine(g);
		}
	}
	
	/**
	 * Fonction qui déplace le dessin, et donc réactualise toutes les positions des lignes
	 * @param Le nouveau point de départ de première ligne de mon dessin
	 */
	public void moveDrawing(Point newStart) {
		if(this.lines.size() >= 2) 
		{
			double differenceX = newStart.getX() - this.lines.get(0).startingPoint.getX();
			double differenceY = newStart.getY() - this.lines.get(0).startingPoint.getY();
			double differenceZ = newStart.getY() - this.lines.get(0).startingPoint.getZ();
			for(int i=0; i< this.lines.size();i++)
			{			
				Point newStartingPoint = new Point(this.lines.get(i).getStartingPoint().getX() + differenceX,this.lines.get(i).getStartingPoint().getY() + differenceY, this.lines.get(i).getStartingPoint().getZ() + differenceZ);
				Point newEndingPoint = new Point(this.lines.get(i).getEndingPoint().getX() + differenceX,this.lines.get(i).getEndingPoint().getY() + differenceY, this.lines.get(i).getEndingPoint().getZ() + differenceZ);
				this.lines.get(i).setStartingPoint(newStartingPoint);
				this.lines.get(i).setEndingPoint(newEndingPoint);
			}
		} 

	}

}
