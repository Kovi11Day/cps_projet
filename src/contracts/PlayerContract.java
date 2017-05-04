package contracts;

import services.FightCharService;
import services.PlayerService;
import decorators.PlayerDecorator;

public class PlayerContract extends PlayerDecorator{
	
	public PlayerContract(PlayerService delegate) {
		super(delegate);
	}
	public void checkInvariant() {
		
	}
	
	
	@Override
	public FightCharService getChar() {
		
		return super.getChar();
	}

	@Override
	public String getKeyLeft() {
		
		return super.getKeyLeft();
	}

	@Override
	public String getKeyRight() {
		
		return super.getKeyRight();
	}

	@Override
	public String getKeyNeutral() {
		
		return super.getKeyNeutral();
	}
	@Override
	public void init(FightCharService c,String left, String right, String neutral, 
			String upLeft, String UpRight, String up, 
			String downLeft, String downRight, String down, 
			String coupPoing, String coupPied, String coupTete){
		//inv@pre
				checkInvariant();
				
				//pre: left != right && right != neutral && left != neutral
				if (left == right || right == neutral || left == neutral)
					throw new PreconditionError("PlayerContract: left != right && right != neutral && left != neutral");

				//run
				super.init(c, left, right, neutral, upLeft, UpRight, up, downLeft, downRight, down, coupPoing, coupPied, coupTete);
				
				//inv@post
				checkInvariant();	
				
				//post: getKeyLeft() = left
				if (getKeyLeft() != left)
					throw new PostconditionError("PlayerContract: getKeyLeft() = left");
				
				//post: getKeyRight() = right
				if (getKeyRight() != right)
					throw new PostconditionError("PlayerContract: getKeyRight() = right");
				
				//post: getKeyNeutral() = neutral
				if (getKeyNeutral() != neutral)
					throw new PostconditionError("PlayerContract: getKeyNeutral() = neutral");
				
				//post: getChar() = c
						if (getChar() != c)
							throw new PostconditionError("PlayerContract: getChar() = c");

	}

	
	//TODO:
	//handeKey()
	//getCommande()

}
