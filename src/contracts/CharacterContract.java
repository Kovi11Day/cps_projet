package contracts;

import services.CharacterService;
import services.Commande;
import services.EngineService;
import services.GlobalVariables;
import services.RectangleHitboxService;
import decorators.CharacterDecorator;

public class CharacterContract extends CharacterDecorator {

	public CharacterContract(CharacterService delegate) {
		super(delegate);
	}

	
	public void checkInvariant() {
		//inv : getPositionX() = getCharBox().getPositionX()
		if (getPositionX() != getCharBox().getPositionX())
			throw new InvariantError("CharacterContract: getPositionX() = getCharBox().getPositionX() ");

		//inv : getPositionY() = getCharBox().getPositionY()
		if (getPositionY() != getCharBox().getPositionY())
			throw new InvariantError("CharacterContract: getPositionY() = getCharBox().getPositionY()");
		
		//inv: getPositionX() > 0 && getPositionX() < getEngine().getWidth()
		if (getPositionX() <= 0 || getPositionX() >= getEngine().getWidth())
			throw new InvariantError("CharacterContract: getPositionX() > 0 && getPositionX() < getEngine().getWidth()");
		
		//inv: getPositionY() >= 0 && getPositionY() < getEngine().getHeight()
		if (getPositionY() < 0 || getPositionY() >= getEngine().getHeight())
			throw new InvariantError("CharacterContract: getPositionY() > 0 && getPositionY() < getEngine().getHeight()");
		
		//inv: isDead() = !(getLife() > 0)
		if (isDead() == (getLife() > 0))
			throw new InvariantError("isDead() = !(getLife() > 0)");
		
	}
	

	@Override
	public void init(int l, int s, boolean f/*, EngineService e*/) {
		//inv@pre
		checkInvariant();
		
		//pre: l > 0 && s:int \in [1,4]  
		if (l <= 0 || s < 1 || s > 4)
			throw new PreconditionError("CharacterContract:l > 0 && s:int in [1,4] ");
		
		//run 
		super.init(l, s, f/*, e*/);
		
		//inv@post
		checkInvariant();

		//post: getLife() = l && getSpeed() = s && isRightFace() = f && getEngine() = e 
		/*-----if (getLife() != l || getSpeed() != s || isRightFace() != f || getEngine() != e )
			throw new PostconditionError("CharacterContract:getLife() = l && getSpeed() = s && isRightFace() = f && getEngine() = e ");*/
		
		//post: \exists h:HitBox {getCharBox() = h}
		boolean ok = false;
		for (int i = 1; i <= 2 ; i++)
			if (getCharBox() == getEngine().getChar(i))
				ok = true;
		if (!ok)
			throw new PostconditionError("CharacterContract:exists h:HitBox {getCharBox() = h");

	}
	
	@Override
	public double getPositionX() {
		return super.getPositionX();
	}
	
	@Override
	public double getPositionY() {
		return super.getPositionY();
	}
	
	@Override
	public EngineService getEngine() {
		
		return super.getEngine();
	}
	
	@Override
	public RectangleHitboxService getCharBox() {
		
		return super.getCharBox();
	}
	

	@Override
	public int getLife() {
		
		return super.getLife();
	}

	@Override
	public int getSpeed() {
		
		return super.getSpeed();
	}

	@Override
	public boolean isRightFace() {
		
		return super.isRightFace();
	}

	@Override
	public boolean isDead() {
		
		return super.isDead();
	}

	@Override
	public void moveLeft() {
		//inv@pre
		checkInvariant();
		
		//captures
		double getPositionX_atPre = getPositionX();
		boolean isRightFace_atPre = isRightFace();
		int getLife_atPre = getLife();
		double getPositionY_atPre = getPositionY();
		//run
		super.moveLeft();
		
		//inv@post
		checkInvariant();
		
		/*post: if \exists getEngine().getChar(i) != this && getHitBox().isCollidesWith(getEngine().getChar(i).getHitBox())
		 * 			then getPositionX() = getPositionX()@pre
		 * 		else 
		 * 			if getPositionX()@pre + getSpeed() <= maxX
		 * 				then getPosition() = getPositionX()@pre + getSpeed()
		 * 			else
		 * 				getPositionX() = maxX*/
		boolean collision = false;
		for (int i = 1; i <= GlobalVariables.nbPlayersMax; i++){
			if (getEngine().getChar(i) != this && getCharBox().isCollidesWith(getEngine().getChar(i).getCharBox()) ){
				collision = true; 
			}
		}
		
		if (collision){
			if (getPositionX() != getPositionX_atPre)
				throw new PostconditionError("CharacterContract: post1: positionX unchanged");
		}else{
			if (getPositionX_atPre >= getSpeed()){
				if (getPositionX() != getPositionX_atPre - getSpeed()){
					throw new PostconditionError("CharacterContract: post1: positionX movedLeft");
			}else
				if (getPositionX() != 0)
					throw new PostconditionError("CharacterContract: post1: positionX extreme left");
		}
		}
		
		//post: isRightFace() = isRightFace()@pre && getLife() = getLife()@pre
		if (isRightFace() != isRightFace_atPre || getLife_atPre != getLife())
			throw new PostconditionError("CharacterContract: post2: positionX 0");
		
		//post: getPositionY() = getPositionY()@pre
		if (getPositionY() != getPositionY_atPre)
			throw new PostconditionError("CharacterContract: post2: positionX 0");
	}
		
	@Override
	public void moveRight() {
		//inv@pre
		checkInvariant();
		
		//captures
		double getPositionX_atPre = getPositionX();
		boolean isRightFace_atPre = isRightFace();
		int getLife_atPre = getLife();
		double getPositionY_atPre = getPositionY();
		//run
		super.moveRight();
		
		//inv@post
		checkInvariant();
		
		/*post: if \exists getEngine().getChar(i) != this && getHitBox().isCollidesWith(getEngine().getChar(i).getHitBox())
		 * 			then getPositionX() = getPositionX()@pre
		 * 		else 
		 * 			if getPositionX()@pre + getSpeed() <= maxX
		 * 				then getPosition() = getPositionX()@pre + getSpeed()
		 * 			else
		 * 				getPositionX() = maxX*/
		boolean collision = false;
		for (int i = 1; i <= GlobalVariables.nbPlayersMax; i++){
			if (getEngine().getChar(i) != this && getCharBox().isCollidesWith(getEngine().getChar(i).getCharBox()) ){
				collision = true; 
			}
		}
		
		if (collision){
			if (getPositionX() != getPositionX_atPre)
				throw new PostconditionError("CharacterContract: post1: positionX unchanged");
		}else{
			if (getPositionX_atPre + getSpeed() <= GlobalVariables.width){
				if (getPositionX() != getPositionX_atPre + getSpeed()){
					throw new PostconditionError("CharacterContract: post1: positionX moved");
			}else
				if (getPositionX() != GlobalVariables.width)
					throw new PostconditionError("CharacterContract: post1: positionX extreme right");
		}
		}
		
		//post: isRightFace() = isRightFace()@pre && getLife() = getLife()@pre
		if (isRightFace() != isRightFace_atPre || getLife_atPre != getLife())
			throw new PostconditionError("CharacterContract: post2: positionX 0");
		
		//post: getPositionY() = getPositionY()@pre
		if (getPositionY() != getPositionY_atPre)
			throw new PostconditionError("CharacterContract: post2: positionX 0");
	}
	

	@Override
	public void switchSide() {
		//inv@pre
		checkInvariant();
				
		//captures
		boolean isRightFace_atPre = isRightFace();
		double getPositionX_atPre = getPositionX();
		
		//run 
		super.switchSide();
		
		//inv@post
				checkInvariant();
				
		//post: isRightFace() != isRightFace()@pre
		if (isRightFace() == isRightFace_atPre)
			throw new PostconditionError("CharacterContract: isRightFace() != isRightFace()@pre");
		
		//post: getPositionX() = getPositionX()@pre	
		if (getPositionX() != getPositionX_atPre)
			throw new PostconditionError("CharacterContract: getPositionX() = getPositionX()@pre");
	}

	@Override
	public void step(Commande c) {
		//inv@pre
		checkInvariant();
		
		//captures 
		double getPositionX_atPre = getPositionX();
		CharacterService this_atPre = (CharacterService)this.clone();


//pre
if (isDead())
		throw new PostconditionError("CharacterContract: !isDead");

//run 
super.step(c);
			
//inv@post
checkInvariant();
					
//post: step(Commande.LEFT) = moveLeft()
if (c == Commande.LEFT){
		this_atPre.moveLeft();
		if (getPositionX() != this_atPre.getPositionX())
			throw new PostconditionError("CharacterContract: step(Commande.LEFT) = moveLeft()");
}
//post:step(Commande.RIGHT) = moveRight()
if (c == Commande.RIGHT){
		this_atPre.moveRight();
		if (getPositionX() != this_atPre.getPositionX())
			throw new PostconditionError("CharacterContract: step(Commande.RIGHT) = moveRight()");
}

//post:step(Commande.NEUTRAL) = this
if(c == Commande.NEUTRAL){
		if (getPositionX() != this_atPre.getPositionX())
			throw new PostconditionError("CharacterContract: step(Commande.NEUTRAL) = this");
}
	}
		
}
