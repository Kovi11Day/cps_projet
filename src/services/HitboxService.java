package services;

public interface HitboxService {
	/* Observators */
	public double getPositionX();
	public double getPositionY();
	
	/* Initializers */
	
	//post: getPostionX() = x
	//post: getPostionY() =  y
	public void init(double x, double y);
	
	/* Operators */
	// post: getPositionX() = x
	// post: getPositionY() = y

	public void moveTo(double x, double y);
	
	
}
