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
		boolean existsIsDead = false;
		
		for(int i=0; i < this.getNbPlayers(); i++){
			if (getChar(i).isDead())
				existsIsDead = true;
		}
		
		//inv: isGameOver() == \exists i:int \in [1,getNbPlayers()] {getChar(i).isDead()}
		if (isGameOver() != existsIsDead)
			throw new InvariantError("EngineContract: inv:isGameOver() != existsIsDead");
		

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
		
		//post: getPlayer(0) = p1
		if (getPlayer(0) != p1){
			throw new PostconditionError("EngineContract:post: getPlayer(0) = p1");
		}

		//post: getPlayer(1) = p2
		if (getPlayer(1) != p2){
			throw new PostconditionError("EngineContract:post: getPlayer(1) = p2");
		}
		
		//post: getCharacter(0).getPositionX() = w//2 - s//2
		//TODO: adding acceptable error

		if (getChar(0).getPositionX() -( w/2 - s/2)>1){
			throw new PostconditionError("EngineContract:post: getCharacter(0).getPositionX() = w//2 - s//2");
		}
		
		//post: getChar(1).getPositionX() = w//2 + s//2
		//TODO: adding acceptable error

		if (getChar(1).getPositionX() - (w/2 + s/2)>1){
			throw new PostconditionError("EngineContract:post: getChar(1).getPositionX() = w//2 + s//2");
		}
		
		//post: getChar(0).getPositionY() = 0
		if (getChar(0).getPositionY() != 0){
			throw new PostconditionError("EngineContract:post: getChar(0).getPositionY() = 0");
		}
		
		//post: getChar(1).getPositionY() = 0
		if (getChar(1).getPositionY() != 0){
			throw new PostconditionError("EngineContract:post: getChar(1).getPositionY() = 0");
		}
		
		//post: getChar(0).isRightFace()
		if (!getChar(0).isRightFace()){
			throw new PostconditionError("EngineContract:post:getChar(0).isRightFace()");
		}
		
		//post: !getChar(1).isRightFace()
		if (getChar(1).isRightFace()){
			throw new PostconditionError("EngineContract:post:!getChar(1).isRightFace()");
		}
		
		//post: getSpace() = s
		if(getSpace() != s)
			throw new PostconditionError("EngineContract:post:getSpace() != s");
		
		//post: getNbPlayers() = 2;
		if(getNbPlayers() != 2)
			throw new PostconditionError("EngineContract:post:getNbPlayers() != 2");

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
		// pre:i \in [1,getNbPlayers()]
		if (i < 0 || i >= getNbPlayers())
			throw new PreconditionError("EngineContract:pre:i in [1,getNbPlayers()]");
		
		return super.getChar(i);
	}


	@Override
	public PlayerService getPlayer(int i) {
		// pre:i \in [1,getNbPlayers()]
				if (i < 0 || i > getNbPlayers() -1)
					throw new PreconditionError("EngineContract:pre:i in [1,getNbPlayers()]");
				
		return super.getPlayer(i);
	}


	@Override
	public boolean isGameOver() {
		
		return super.isGameOver();
	}

	@Override
	public void run() {
		super.run();
	}


	@Override
	public int getNbPlayers() {
		return super.getNbPlayers();
	}


	@Override
	public void updateGame() {
		//inv@pre
		this.checkInvariant();
		
		// pre: !isGameOver() 
		if(isGameOver() )
			throw new PreconditionError("isGameOver()");

		//run
		 super.updateGame();
		 

		//inv@pre
		this.checkInvariant();
		
	}


	@Override
	public void step() {
		//inv@pre
		this.checkInvariant();
		
		// pre: !isGameOver() 
		if(isGameOver() )
			throw new PreconditionError("isGameOver()");

		//run
		 super.step();
		 

		//inv@pre
		this.checkInvariant();
		
	}


	@Override
	public void updateAllVictims() {
		//inv@pre
		this.checkInvariant();
		
		// pre: !isGameOver() 
		if(isGameOver() )
			throw new PreconditionError("isGameOver()");

		//run
		 super.updateAllVictims();
		 

		//inv@pre
		this.checkInvariant();		
	}


	@Override
	public void updateAllAttackers() {
		//inv@pre
		this.checkInvariant();
		
		// pre: !isGameOver() 
		if(isGameOver() )
			throw new PreconditionError("isGameOver()");

		//run
		 super.updateAllAttackers();
		 

		//inv@pre
		this.checkInvariant();		
	}


	@Override
	public void updateAllFrames() {
		//inv@pre
		this.checkInvariant();
		
		// pre: !isGameOver() 
		if(isGameOver() )
			throw new PreconditionError("isGameOver()");

		//run
		 super.updateAllFrames();
		 

		//inv@pre
		this.checkInvariant();		
	}


	@Override
	public void setNewPlayer(PlayerService p) {
		//inv@pre
		this.checkInvariant();
		
		// pre: !isGameOver() 
		if(isGameOver() )
			throw new PreconditionError("isGameOver()");

		//captures
		int getNbPlayers_atPre = getNbPlayers();
		//run
		 super.setNewPlayer(p);
		 
		//post: getNbPlayers() = getNbPlayers()@pre + 1;
		 if (getNbPlayers() != getNbPlayers_atPre + 1)
				throw new PostconditionError("getNbPlayers() != getNbPlayers_atPre + 1");

		//inv@pre
		this.checkInvariant();				
	}

	
	
}
