package services;

public interface EngineService extends Runnable{

	/* Observators */
	int getHeight();
	int getWidth();
	int getSpace();
	// pre:i  \in [1,2]
	//CharacterService getChar(int i);
	FightCharService getChar(int i);

	// pre:i  \in [1,2]
	PlayerService getPlayer(int i);
	
	boolean isGameOver();
	
	/* Invariants */
	//inv: isGameOver() == \exists i \in [1,2] {getChar(i).isDead()}
	
	
	/* Initializers */
	
	//pre: h > 0 && s > 0 && w > s && p1 != p2
	//pre: {p1.getKeyLeft(), p1.getKeyRight(), p1.getKeyNeutral()} 
	//      \intersection {p2.getKeyLeft(), p2.getKeyRight(), p2.getKeyNeutral()} = \empty
	//post: getHeight() = h
	//post: getWidth() = w
	//post: getPlayer(1) = p1
	//post: getPlayer(2) = p2
	//post: getChar(1).getPositionX() = w//2 - s//2
	//post: getChar(2).getPositionX() = w//2 + s//2
	//post: getChar(1).getPositionY() = 0
	//post: getChar(2).getPositionY() = 0
	//post: getChar(1).isRightFace()
	//post: !getChar(2).isRightFace()
	public void init(int h, int w, int s, PlayerService p1, PlayerService p2);
	
	/* Operators */
	// pre: ! isGameOver()
	// post: getChar(1) = getChar(1)@pre.step(c1)
	// post: getChar(2) = getChar(2)@pre.step(c2)
	public void step(Commande c1, Commande c2);
	public void run();
	
	
}
