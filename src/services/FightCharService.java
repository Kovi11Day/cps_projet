package services;

public interface FightCharService extends CharacterService{
	
	/* Observators */
	boolean isBlocking();
	boolean isBlockStunned();
	boolean isHitSunned();
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
	
	//inv : isTeching() == !(isBlocking() 
	//|| isBlockStunned() || isHitStunned() )
	
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
	// (getTech().getRFrame() < getTechFrameCounter()) &&
	// ( getTechFrameCounter() >= (getTech().getRFrame() + getTech().getHFrame()) )
	// )
	
	/* Initializers */
	
	//pre: l>0 && s>0
	
	//post: 
	//isBlockStunned() = false
	//isHitStunned() = false
	//getBStunFrameCounter() =0
	//getHStunFrameCounter() =0
	//geNbTechMastered()>0
	// \forAll i \in [1,getNbTechMastered()] 
	// \exist t:Tech ,getTechMastered()= t
	
	public void init(int l,int s,boolean f,EngineService e);
	
	/* Operators */
	//pre:\exist i \in [1,getNbTechMastered()], getTechMastered(i) = t
	//post:
	//isBlocking() =false
	//getTechBox().equals(t.getHitBox(getPositionX(),getPositionY())
	//getTech() = t
	//isTechhasAlreadyHit() = false
	//getTechFrameCounter() = t.getSFrame()+t.getHFrame()+t.getRFrame()
	//getBStunFrameCounter()= getBStunFrameCounter()@pre
	//getHStunFrameCounter() = getHStunFrameCounter()@pre
	//getOtherFightChar() = getOtherFightChar()@pre
	//getNbTechMastered() = getNbTechMastered()@pre
	//\exist i \in [1,getNbTechMastered()], getTechMastered(i)= getTechMastered(i)@pre
	


	//TODO:inv + pre + post for each operator
	void startTech(Tech t);
	
	//pre:!dead() && isControllable()
	//post: iBlocking() == c.equals(bloque)
	//
	public void step(Commande c);

	//void updateStatus();
	void updateVictim();
	void updateAttacker();
	void updateFrames();
	void setOtherFightChar(FightCharService c);
	void setNewTechMastered(Tech t);
	
}