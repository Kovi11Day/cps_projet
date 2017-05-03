package services;

public interface PlayerService {
	
	/* Observators */
	//CharacterService getChar();
	FightCharService getChar();

	String getKeyLeft();
	String getKeyRight();
	String getKeyNeutral();
	
	/* Initializers */
	
	//pre: left != right && right != neutral && left != neutral
	//post: getKeyLeft() = left
	//post: getKeyRight() = right
	//post: getKeyNeutral() = neutral
	//post: getChar() = c
	
	
	
	//public void init(CharacterService c,String left, String right, String neutral );
	public void init(FightCharService c,String left, String right, String neutral, 
			String upLeft, String UpRight, String up, 
			String downLeft, String downRight, String down, 
			String coupPoing, String coupPied, String coupTete);

	
	//TODO: see where to place and add pre/post/inv...
	public Commande getCommande();
	public void handleKey(String key);

	String getKeyUpLeft();

	String getKeyCoupTete();

	String getKeyCoupPied();

	String getKeyCoupPoing();

	String getKeyDown();

	String getKeyDownRight();

	String getKeyDownLeft();

	String getKeyUp();

	String getKeyUpRight();
}	
