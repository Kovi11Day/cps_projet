package contracts;


import java.util.ArrayList;

import services.CharacterService;
import services.Commande;
import services.EngineService;
import services.FightCharService;
import services.GlobalVariables;
import services.PlayerService;
import decorators.EngineDecorator;

public class EngineContract extends EngineDecorator{

	public EngineContract(EngineService delegate) {
		super(delegate);
	}
	
	public void checkInvariant() {
		//inv: isGameOver() == \exists i \in [1,2] {getChar(i).isDead()}
		boolean existsIsDead = false;
		
		for(int i=0; i < GlobalVariables.nbPlayersMax; i++){
			if (getChar(i).isDead())
				existsIsDead = true;
		}
		
		if (isGameOver() != existsIsDead)
			throw new InvariantError("EngineContract: isGameOver() ");

	}
	
	@Override
	public void init(int h, int w, int s, PlayerService p1, PlayerService p2){
		//inv@pre
		checkInvariant();
		
		//pre: h > 0 && s > 0 && w > s && p1 != p2
		if (h<= 0 || s<= 0 || w<=s || p1==p2){
			throw new PreconditionError("EngineContract: pre: h > 0 && s > 0 && w > s && p1 != p2");
		}
		
		//pre: {p1.getKeyLeft(), p1.getKeyRight(), p1.getKeyNeutral()} 
		//      \intersection {p2.getKeyLeft(), p2.getKeyRight(), p2.getKeyNeutral()} = \empty
		ArrayList<String> p1Keys = new ArrayList<String>();
		ArrayList<String> p2Keys = new ArrayList<String>();
		p1Keys.add(p1.getKeyLeft()); p1Keys.add(p1.getKeyRight());p1Keys.add(p1.getKeyNeutral());
		p2Keys.add(p2.getKeyLeft()); p2Keys.add(p2.getKeyRight());p2Keys.add(p2.getKeyNeutral());
		
		for(String s1: p1Keys){
			for(String s2: p2Keys){
				if (s1.equals(s2))
					throw new PreconditionError("EngineContract: pre: no equal keys");
			}	
		}
		
		//run
		super.init(h, w, s, p1, p2);
		
		//inv@post
		checkInvariant();
		
		//post: getHeight() = h
		if (getHeight() != h){
			throw new PostconditionError("EngineContract: post: getHeight() = h");
		}
		
		//post: getWidth() = w
		if (getWidth() != w){
			throw new PostconditionError("EngineContract:post: getWidth() = w");
		}
		
		//post: getPlayer(1) = p1
		if (getPlayer(1) != p1){
			throw new PostconditionError("EngineContract:post: getPlayer(1) = p1");
		}
		
		//post: getPlayer(2) = p2
		if (getPlayer(2) != p2){
			throw new PostconditionError("EngineContract:post: getPlayer(2) = p2");
		}
		
		//post: getCharacter(1).getPositionX() = w//2 - s//2
		if (getChar(1).getPositionX() != w/2 - s/2){
			throw new PostconditionError("EngineContract:post: getCharacter(1).getPositionX() = w//2 - s//2");
		}
		
		//post: getChar(2).getPositionX() = w//2 + s//2
		if (getChar(2).getPositionX() != w/2 + s/2){
			throw new PostconditionError("EngineContract:post: getChar(2).getPositionX() = w//2 + s//2");
		}
		
		//post: getChar(1).getPositionY() = 0
		if (getChar(1).getPositionY() != 0){
			throw new PostconditionError("EngineContract:post: getChar(1).getPositionY() = 0");
		}
		
		//post: getChar(2).getPositionY() = 0
		if (getChar(2).getPositionY() != 0){
			throw new PostconditionError("EngineContract:post: getChar(2).getPositionY() = 0");
		}
		
		//post: getChar(1).isRightFace()
		if (!getChar(1).isRightFace()){
			throw new PostconditionError("EngineContract:post:getChar(1).isRightFace()");
		}
		
		//post: !getChar(2).isRightFace()
		if (getChar(1).isRightFace()){
			throw new PostconditionError("EngineContract:post:!getChar(2).isRightFace()");
		}
		
	}
	
	
	 
	@Override
	public int getHeight() {
		return super.getHeight();
	
	}


	@Override
	public int getWidth() {
		
		return super.getWidth();
	}


	@Override
	public FightCharService getChar(int i) {
		// pre:i  \in [1,2]
		if (i < 1 || i > GlobalVariables.nbPlayersMax)
			throw new PreconditionError("EngineContract: pre:i  in [1,2]");
		
		return super.getChar(i);
	}


	@Override
	public PlayerService getPlayer(int i) {
		// pre:i  \in [1,2]
				if (i < 1 || i > GlobalVariables.nbPlayersMax)
					throw new PreconditionError("EngineContract: pre:i  in [1,2]");
		return super.getPlayer(i);
	}


	@Override
	public boolean isGameOver() {
		
		return super.isGameOver();
	}


	@Override
	public void step(Commande c1, Commande c2) {
		//inv@pre
		checkInvariant();
				
		// pre: !isGameOver()
		if (isGameOver())
			throw new PreconditionError("EngineContract: pre:!isGameOver()");

		//captures
		CharacterService getChar1_atPre = (CharacterService)getChar(1).clone();
		CharacterService getChar2_atPre = (CharacterService)getChar(2).clone();
		getChar1_atPre.step(c1);
		getChar2_atPre.step(c2);

		//run
		super.step(c1, c2);
				
		//inv@post
		checkInvariant();
		
		// post: getChar(1) = getChar(1)@pre.step(c1)
		if(!getChar(1).equals( getChar1_atPre))
			throw new PostconditionError("EngineContract: getChar(1) = getChar(1)@pre.step(c1)");

		// post: getChar(2) = getChar(2)@pre.step(c2)
				if(!getChar(2).equals( getChar2_atPre))
					throw new PostconditionError("EngineContract: getChar(2) = getChar(2)@pre.step(c2)");
	}
	
}
