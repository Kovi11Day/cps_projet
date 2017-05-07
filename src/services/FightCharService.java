package services;

public interface FightCharService extends CharacterService{
	
	/* Observators */
	boolean isBlocking();
	boolean isBlockStunned();	
	boolean isHitStunned();
	boolean isTeching();
	
	// pre: isTeching()
	Tech getTech();
	
	// pre: isTeching()
	boolean isTechHasAlreadyHit();
	
	// pre: isTeching()
	boolean isInHitFrame();
	
	boolean isControllable();
	int getTechFrameCounter();
	int getBstunFrameCounter();
	int getHstunFrameCounter();
	FightCharService getOtherFightChar();
	
	// pre: isInHitFrame()
	RectangleHitboxService getTechBox();
	
	int nbTechMastered();
	
	// pre: i \in [1,nbTechMastered()]
	Tech getTechMastered(int i);
	
/* Invariants */
	
	/*inv : isTeching() ==> (!isBlocking() 
	 * 		&& !isBlockStunned() && !isHitStunned() )
	 */
	
	//!(isBlockStunned() && isHitStunned() )
	
	//isBlockStunned() ==> isBlocking()
	
	//isControllable() =! (isTeching() 
	// || isBlockStunned() || isHitStunned()
	
	//getTechFrameCounter() >=0 && 
	// getBStunFrameCounter() >=0 &&  getHStunFrameCounter()>=0
	
	//!isTeching() ==> !isInHitFrame()
	
	//isTeching() == getTechFrameCounter()>0
	
	//isBlockStunned() == getBStunFrameCounter()>0
	
	//isHitStunned() == getHStunFrameCounter() >0
	
	//isTeching() && isInHitFrame() ==> (
	// (getTech().rframe() < getTechFrameCounter()) &&
	// ( getTechFrameCounter() <= (getTech().rframe() + getTech().hframe()) )
	// )
	
	/* Initializers */
	
	//pre: l>0 && s>0
	
	//post: getTechFrameCounter() = 0
	//post: getBStunFrameCounter() =0
	//post: getHStunFrameCounter() =0
	//post: isTechHasAlreadyHit() = false
	//post: isBlocking() = false
	//post: getNbTechMastered = 0
	public void init(int l,int s,boolean f);
	
	/* Operators */
	
	
	//pre:\exist i \in [1,getNbTechMastered()], getTechMastered(i) = t
	//post:getTechBox().equals(t.getHitBox(getPositionX()@pre,getPositionY()@pre, 
				//getWidth()@pre, getHeight()@pre, getFaceRight()@pre)
	//post:getTech() = t
	//post:isTechhasAlreadyHit() = false
	//post:getTechFrameCounter() = t.sframe()+t.hframe()+t.rframe()
	//post:getOtherFightChar() = getOtherFightChar()@pre
	//post:getNbTechMastered() = getNbTechMastered()@pre
	//post: \forall i \in [1,getNbTechMastered()], getTechMastered(i)= getTechMastered(i)@pre
	void startTech(Tech t);
	
	/* pre: !isDead() && isReady()
	 * post: !isControllable()@pre ==> this@pre = this
	 * post: step(Commande.LEFT) = moveLeft()
	 * post: step(Commande.RIGHT) = moveRight()
	 * post: step(Commande.NEUTRAL) = this
	 * post: step(Commande.COUP_DE_POING) = startTech(getTechMastered(1))
	 * post: step(Commande.COUP_DE_PIED) =  startTech(getTechMastered(2))
	 * post: step(Commande.COUP_DE_TETE) = startTech(getTechMastered(3))
	 */
	public void step(Commande c);

	/*
	 * pre: getOtherFightChar() != null
	 * post: (getOtherFightChar().isTeching()@pre && getOtherFightChar().isInHitFrame()@pre
	 * 				&& !getOtherFightChar().isHasAlreadyHit()@pre && getOtherFightChar()@pre.getTechBox().collidesWith(getCharBox()@pre))
	 * 		==>
	 * 		(
	 * 			(
	 * 				isBlocking()@pre ==> 
	 * 					getBstunFrameCounter() == max(getBstunFrameCounter()@pre, getOtherFightChar().tech().bstun()
	 * 					&& getLife() == getLife()@pre
	 * 					&& isBlocking() == isBlocking()@pre	
	 * 			)
	 * 			&&
	 * 			(
	 * 				!isBlocking()@pre && !isTeching()@pre ==>
	 * 					getHstunFrameCounter() == max(getHstunFrameCounter()@pre, getOtherFightChar().tech().hstun())
	 * 					&& getLife() == getLife()@pre - getOtherFightChar().tech().damage()
	 * 					&& isBlocking() == isBlocking()@pre
	 * 			)
	 * 			&&
	 * 			(
	 * 				!isBlocking()@pre && isTeching()@pre ==>
	 * 					getHstunFrameCounter() == max(getHstunFrameCounter()@pre, getOtherFightChar().tech().hstun())
	 * 					&& getTechFrameCounter() == 0
	 * 					&& getLife() == getLife()@pre
	 * 					&& isBlocking() == isBlocking()@pre
	 * 			)
	 * 		)
	 */
	void updateVictim();
	
	
	/*
	 * pre: getOtherFightChar() != null
	 * post: (isTeching()@pre && isInHitFrame()@pre &&!isHasAlreadyHit()@pre && 
	 * 				techBox()@pre.collidesWith(getOtherFightCharacter(C)@pre))
	 * 		==>
	 * 		(
	 * 			isHasAlreadyHit() == true
	 * 			&& isInHitFrame() ==isInHitFrame() @pre
	 * 			&& getLife() ==getLife() @pre
	 * 			&& getHstunFrameCounter() ==getHstunFrameCounter() @pre
	 * 			&& getBstunFrameCounter() ==getBstunFrameCounter() @pre
	 * 		)
	 */
	void updateAttacker();
	
	/*
	 * post: getTechFrameCounter() = max (0, getTechFrameCounter()@pre - 1)
	 * post: getBstunFrameCounter() = max (0, getBstunFrameCounter()@pre - 1)
	 * post: getHstunFrameCounter() = max (0, getHstunFrameCounter()@pre - 1)
	 * post: !isBlockStunned()@pre ==> !isBlocking()
	 * post: !isTeching() ==> !hasAlreadyHit()
	 */
	void updateFrames();
	
	/*
	 * post: getOtherFightChar() != null
	 */
	void setOtherFightChar(FightCharService c);
	
	/*
	 * pre: t.damage() >= 0 && t.hstun >= 0 && t.bstun >= 0 && t.sframe() >= 0 && t.hframe() >= 0 && t.rframe() >= 0
	 * post: getNbTechMastered() == getNbTechMastered()@pre + 1
	 * post: \exists i in [1, getNbTechMastered()] getTechMastered(i) == t
	 */
	void setNewTechMastered(Tech t);
	
}