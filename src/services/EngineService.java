package services;

public interface EngineService extends Runnable{

	/* Observators */
	int getHeight();
	int getWidth();
	int getSpace();
	int getNbPlayers();
	
	// pre:i \in [1,getNbPlayers()]
	FightCharService getChar(int i);

	// pre:i \in [1,getNbPlayers()]
	PlayerService getPlayer(int i);
	
	boolean isGameOver();
	
	/* Invariants */
	//inv: isGameOver() == \exists i:int \in [1,getNbPlayers()] {getChar(i).isDead()}
	
	/* Initializers */
	
	//pre: h > 0 && s > 0 && w > s && p1 != p2
	//pre: {p1.getKeyLeft(), p1.getKeyRight(), p1.getKeyNeutral()} 
	//      \intersection {p2.getKeyLeft(), p2.getKeyRight(), p2.getKeyNeutral()} = \empty
	//post: getHeight() = h
	//post: getWidth() = w
	//post: getSpace() = s
	//post: getPlayer(1) = p1
	//post: getPlayer(2) = p2
	//post: getNbPlayers() = 2;
	//post: getChar(1).getPositionX() = w//2 - s//2
	//post: getChar(2).getPositionX() = w//2 + s//2
	//post: getChar(1).getPositionY() = 0
	//post: getChar(2).getPositionY() = 0
	//post: getChar(1).isRightFace()
	//post: !getChar(2).isRightFace()
	public void init(int h, int w, int s, PlayerService p1, PlayerService p2);
	
	/* Operators */
	// pre: !isGameOver() 
	// post: updateGame() = step().updateAllVictims().updateAllAttackers().updateAllFrames()
	public void updateGame();
	
	// pre: !isGameOver()
	/* post: \forall i \in [1,getNbPlayers()] {getChar(i) = getChar(i)@pre.step(getPlayer(i)@pre.getCommande())*/
	public void step();
	
	// pre: !isGameOver()
	/* post: \forall i \in [1,getNbPlayers()] {getChar(i) = getChar(i)@pre.updateVictim()*/
	public void updateAllVictims();

	// pre: !isGameOver()
	/* post: \forall i \in [1,getNbPlayers()] {getChar(i) = getChar(i)@pre.updateAttacker()*/
	public void updateAllAttackers();

	// pre: !isGameOver()
	/* post: \forall i \in [1,getNbPlayers()] {getChar(i) = getChar(i)@pre.updateFrames()*/
	public void updateAllFrames();
	
	//pre:!isGameOver()
	//post: getNbPlayers() = getNbPlayers()@pre + 1;
	public void setNewPlayer(PlayerService p);
	
	public void run();
	
	
}
