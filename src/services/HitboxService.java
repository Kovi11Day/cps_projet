package services;

public interface HitboxService {
	/* Observators */
	public int getPositionX();
	public int getPositionY();
	public boolean isBelongsTo(int x,int y);
	public boolean isCollidesWith(HitboxService h);
	public boolean isEqualsTo(HitboxService h);
	
	//TODO:sortout 
	public int getWidth();
	public int getHeight();

	/* Invariants */
	
	//inv :  collidesWith(HitboxService h) == 
		// \exists x,y \in \int {isBelongsTo(x,y) && h.isBelongsTo(x,y)}
	
	//inv :  equalsTo(HitboxService h) == 
			// \forall x,y \in \int {isBelongsTo(x,y) == h.isBelongsTo(x,y)}
	
	
	/* Initializers */
	
	//post: getPostionX() = x
	//post: getPostionY() =  y
	//public void init(int x, int y);
	
	/* Operators */
	// post: getPositionX() = x
	// post: getPositionY() = y
	// post: //forall u, v \in \int {isBelongsTo(u,v) == isBelongsTo(u-(x-getPositionX()@pre), v-(y-getPositionY()@pre)) } 

	public void moveTo(int x, int y);
	void init(int x, int y, int w, int h);
	
	
}
