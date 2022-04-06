package geometric;
/**
 *  Cette classe permet de créer et gérer des points
 */
public class Point {
	public double x,y,z;
	/**
	 * Construit un objet point
	 * @param x
	 * @param y
	 * @param z
	 */
	public Point(double x, double y,  double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	/**
	 * Donne le X de notre point
	 * @return coordonnée de type double
	 */
	public double getX() {
		return this.x;
	}
	public double getY() {
		return this.y;
	}
	public double getZ() {
		return this.z;
	}
	/**
	 * Donne le Y de notre point
	 * @return coordonnée de type double
	 */
	
	/**
	 * Donne un point, qui est obtenu en faisant une rotation centrée (paramètre center) avec un autre point (paramètre edge) d'angle angle (paramètre angle)
	 * @param center
	 * @param edge
	 * @param angle
	 * @return point
	 */
    public static Point rotateLineClockWise(Point center, Point edge, double angle) {
        double xRot = (int) center.x + Math.cos(Math.toRadians(angle)) * (edge.x - center.x) - Math.sin(Math.toRadians(angle)) * (edge.y - center.y);
        double yRot = (int) center.y + Math.sin(Math.toRadians(angle)) * (edge.x - center.x) + Math.cos(Math.toRadians(angle)) * (edge.y - center.y);
		double zRot = 0;
        return new Point((int) xRot, (int) yRot, zRot);
    }

	public static Point rotateZbyX(Point center, Point edge, double angle) {
        double xRot = (int) center.x + Math.cos(Math.toRadians(angle)) * (edge.x - center.x) - Math.sin(Math.toRadians(angle)) * (edge.y - center.y);
        double yRot = edge.getY();
		double zRot = (int) center.z + Math.sin(Math.toRadians(angle)) * (edge.x - center.x) + Math.cos(Math.toRadians(angle)) * (edge.y - center.y);
        return new Point((int) xRot, (int) yRot, zRot);
    }

    /**
     * Affiche les coordonnées du point
     */
    @Override
    public String toString() {
    	return "[" + this.x + ", " + this.y + ", " + this.z + "]";
    }
    

}
