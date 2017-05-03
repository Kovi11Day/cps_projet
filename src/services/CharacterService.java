package services;

public interface CharacterService {
	/* Observators */
	double getPositionX();
	double getPositionY();
	EngineService getEngine();
	//HitboxService getCharBox ();
	RectangleHitboxService getCharBox ();

	int getLife();
	int getSpeed();
	boolean isRightFace();
	boolean isDead();
	
	/* Invariants */
	//inv : getPositionX() = getCharBox().getPositionX()
	//inv : getPositionY() = getCharBox().getPositionY()
	//inv: getPositionX() > 0 && getPositionX() < getEngine().getWidth()
	//inv: getPositionY() > 0 && getPositionY() < getEngine().getHeight()
	//inv: isDead() = !(getLife() > 0)
	
	/* Initializers */
	//pre: l > 0 && s:int \in [1,4]  
	//post: getLife() = l && getSpeed() = s && isRightFace() = f && getEngine() = e 
	//post: \exists h:HitBox {getCharBox() = h}
	public void init(int l, int s, boolean f/*, EngineService e*/);
	
	/* Operators */
	
	/*post: if \exists getEngine().getChar(i) != this && getHitBox().isCollidesWith(getEngine().getChar(i).getHitBox())
	 * 			then getPositionX() = getPositionX()@pre
	 * 		else 
	 * 			if getPositionX()@pre >= getSpeed()
	 * 				then getPositionX() = getPositionX()@pre - getSpeed()
	 * 			else
	 * 				getPositionX() = 0
	 * 
	 * post: isRightFace() = isRightFace()@pre && getLife() = getLife()@pre
	 * post: getPositionY() = getPositionY()@pre
	 */
	public void moveLeft();
	
	/*post: if \exists getEngine().getChar(i) != this && getHitBox().isCollidesWith(getEngine().getChar(i).getHitBox())
	 * 			then getPositionX() = getPositionX()@pre
	 * 		else 
	 * 			if getPositionX()@pre + getSpeed() <= maxX
	 * 				then getPosition() = getPositionX()@pre + getSpeed()
	 * 			else
	 * 				getPositionX() = maxX
	 * 
	 * post: isRightFace() = isRightFace()@pre && getLife() = getLife()@pre
	 * post: getPositionY() = getPositionY()@pre
	 */
	public void moveRight();
	
	/*post: isRightFace() != isRightFace()@pre
	 * 		getPositionX() = getPositionX()@pre	
	 */
	public void switchSide();
	
	/* pre: !isDead()
	 * post: step(Commande.LEFT) = moveLeft()
	 * 		step(Commande.RIGHT) = moveRight()
	 * 		step(Commande.NEUTRAL) = this
	 */
	public void step(Commande c);
	
	public void setEngine(EngineService engine);
	public CharacterService clone();
	public boolean equals(CharacterService c);
	public int getWidth();
	public int getHeight();
}
