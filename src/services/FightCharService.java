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
	
	//post: 
	//getTechFrameCounter() = 0
	//getBStunFrameCounter() =0
	//getHStunFrameCounter() =0
	//isTechHasAlreadyHit() = false
	//isBlocking() = false
	//getNbTechMastered = 0
	public void init(int l,int s,boolean f);
	
	/* Operators */
	
	
	//pre:\exist i \in [1,getNbTechMastered()], getTechMastered(i) = t
	//post:getTechBox().equals(t.getHitBox(getPositionX()@pre,getPositionY()@pre)
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
	 * post: (getOtherFightChar().isTeching() && getOtherFightChar().isInHitFrame 
	 * 				&& !getOtherFightChar().isHasAlreadyHit() && getOtherFightChar().getTechBox().collidesWith(getCharBox()))
	 * 		==>
	 * 		(
	 * 			(
	 * 				isBlocking()@pre ==> 
	 * 					getBstunFrameCounter() == max(getBstunFrameCounter()@pre, getOtherFightChar().tech().bstun()
	 * 					&& life() == life()@pre
	 * 					&& isBlocking() == isBlocking()@pre	
	 * 			)
	 * 			&&
	 * 			(
	 * 				!isBlocking()@pre && !isTeching()@pre ==>
	 * 					getHstunFrameCounter() == max(getHstunFrameCounter()@pre, getOtherFightChar().tech().hstun())
	 * 					&& life() == life()@pre - getOtherFightChar().tech().damage()
	 * 					&& isBlocking() == isBlocking()@pre
	 * 			)
	 * 			&&
	 * 			(
	 * 				!isBlocking()@pre && isTeching()@pre ==>
	 * 					getHstunFrameCounter() == max(getHstunFrameCounter()@pre, getOtherFightChar().tech().hstun())
	 * 					&& getTechFrameCounter() == 0
	 * 					&& life() == life()@pre
	 * 					&& isBlocking() == isBlocking()@pre
	 * 			)
	 * 		)
	 */
	void updateVictim();
	
	/*
	 * pre: getOtherFightCharacter() != null
	 */
	void updateAttacker();
	void updateFrames();
	void setOtherFightChar(FightCharService c);
	void setNewTechMastered(Tech t);
	
}