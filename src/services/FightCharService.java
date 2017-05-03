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
	HitboxService getTechBox();
	int nbTechMastered();
	// pre: i \in [1,nbTechMastered()]
	Tech getTechMastered(int i);

	//TODO:inv + pre + post for each operator
	void startTech(Tech t);
	void updateStatus();
	void updateFrames();
	void setOtherFightChar(FightCharService c);
	void setNewTechMastered(Tech t);
	
}
