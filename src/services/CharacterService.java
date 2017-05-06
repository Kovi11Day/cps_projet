package services;

public interface CharacterService {
	/* Observators */
	double getPositionX();
	double getPositionY();
	int getWidth();
	int getHeight();
	EngineService getEngine();
	RectangleHitboxService getCharBox ();
	int getLife();
	int getSpeed();
	boolean isRightFace();
	boolean isDead();
	boolean isReady();

	/* Invariants */
	//inv : getWidth() = getCharBox().getWidth()
	//inv : getHeight() = getCharBox().getHeight()
	//inv : getPositionX() = getCharBox().getPositionX()
	//inv : getPositionY() = getCharBox().getPositionY()
	//inv: getPositionX() >= 0 && getPositionX() <= getEngine().getWidth()
	//inv: getPositionY() >= 0 && getPositionY() < getEngine().getHeight()
	//inv: isDead() = !(getLife() > 0)
	
	/* Initializers */
	//pre: l > 0 && s>= 0  
	//post: getLife() = l && getSpeed() = s && isRightFace() = f && !isReady()
	//post: \exists h:HitBox {getCharBox() = h}
	public void init(int l, int s, boolean f);
	
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
	 * 			if getPositionX()@pre + getSpeed() <= engine().getWidth()
	 * 				then getPosition() = getPositionX()@pre + getSpeed()
	 * 			else
	 * 				getPositionX() = engine().getWidth()
	 * 
	 * post: isRightFace() = isRightFace()@pre && getLife() = getLife()@pre
	 * post: getPositionY() = getPositionY()@pre
	 */
	public void moveRight();
	
	/*post: isRightFace() != isRightFace()@pre
	 * 		getPositionX() = getPositionX()@pre	
	 */
	public void switchSide();
	
	/* pre: !isDead() && isReady()
	 * post: step(Commande.LEFT) = moveLeft()
	 * post: step(Commande.RIGHT) = moveRight()
	 * post: step(Commande.NEUTRAL) = this
	 */
	public void step(Commande c);
	
	//pre: !isReady()
	//post: isReady()
	public void setEngine(EngineService engine);

}
