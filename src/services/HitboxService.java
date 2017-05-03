package services;

public interface HitboxService {
	/* Observators */
	public double getPositionX();
	public double getPositionY();
	public boolean isBelongsTo(double x,double y);
	public boolean isCollidesWith(HitboxService h);
	public boolean isEqualsTo(HitboxService h);
	
	/* Invariants */
	
	//inv :  collidesWith(HitboxService h) == 
		// \exists x,y:double {isBelongsTo(x,y) && h.isBelongsTo(x,y)}
	
	//inv :  equalsTo(HitboxService h) == 
			// \forall x,y:double \int {isBelongsTo(x,y) == h.isBelongsTo(x,y)}
	
	
	/* Initializers */
	
	//post: getPostionX() = x
	//post: getPostionY() =  y
	public void init(double x, double y);
	
	/* Operators */
	// post: getPositionX() = x
	// post: getPositionY() = y
	// post: //forall u, v \in \int {
	// isBelongsTo(u,v) == isBelongsTo(u-(x-getPositionX()@pre), v-(y-getPositionY()@pre)) 
	// } 

	public void moveTo(double x, double y);
	
	
}
