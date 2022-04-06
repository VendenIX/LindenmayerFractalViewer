package window;

import java.awt.*;
import java.util.*;
import javax.swing.JPanel;
import geometric.*;
import lsystem.*;

/**
 * Cette classe gère le rendu 2D
 */
public class Display2D extends JPanel {
	
	/**
	 * Initialise le JPanel
	 */
	public static ArrayList<Line> map;
	public Graphics g = getGraphics();
	public Display2D(ArrayList<Line> map) {
		Display2D.map = map;
	}

	public static int getMapSize(){
		return map.size();
	}

	@Override
	public void paint(Graphics g) {
		//Parametres par defaut
		Graphics2D g2D = (Graphics2D) g;
		g2D.setColor(Color.red);
		g2D.setRenderingHint(
			        RenderingHints.KEY_TEXT_ANTIALIASING,
			        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2D.setRenderingHint(
		        RenderingHints.KEY_ANTIALIASING,
		        RenderingHints.VALUE_ANTIALIAS_ON);

		g.setColor(Color.BLACK);
		g.fillRect(0,0, getWidth(), getHeight());
		

		//Dessin
		Drawing dessin2 = new Drawing(Display2D.map);
		g.setColor(Color.green);
		dessin2.draw2D(g);
    }
}
