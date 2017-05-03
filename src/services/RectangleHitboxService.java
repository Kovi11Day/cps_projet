package services;

public interface RectangleHitboxService extends /*refine*/ HitboxService{
	
	/* Observators */
	public int getHeight();
	public int getWidth();
	
	/* Invariants */
	
	//inv : belongsTo(int a,int b) == a \in {getPositionX()-getWidth()/2,
	// getPositionX()+getWidth/2 } && b \in {getPositionY()-getHeight()/2,
	// getPositionY()+getHeight/2}
	
	/* Initializers */
	
	
	//post: getHeight() == h
	//post: getWidth() == w
	public void init(double x,double y);
	
	/* Operators */
	//pre : w>0 && h>0
	//post: getPositionX() == getPositionX()@pre
	//post: getPositionY() == getPositionY()@pre
	//post: getWidth() == w
	//post: getHeight() == h
	
	public void setWidthHeight(int w, int h);

}
