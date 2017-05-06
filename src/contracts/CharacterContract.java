package contracts;

import services.Commande;
import services.EngineService;
import services.FightCharService;
import services.GlobalVariables;
import services.RectangleHitboxService;
import decorators.FightCharDecorator;

public class CharacterContract extends FightCharDecorator {

	public CharacterContract(FightCharService delegate) {
		super(delegate);
	}

	
	public void checkInvariant() {
		//inv : getWidth() = getCharBox().getWidth()
		if (getWidth() != getCharBox().getWidth())
			throw new InvariantError("CharacterContract: getWidth() != getCharBox().getWidth() ");

		//inv : getHeight() = getCharBox().getHeight()
		if (getHeight() != getCharBox().getHeight())
			throw new InvariantError("CharacterContract: getHeight() != getCharBox().getHeight()");
		
		//inv : getPositionX() = getCharBox().getPositionX()
		if (getPositionX() != getCharBox().getPositionX())
			throw new InvariantError("CharacterContract: getPositionX() = getCharBox().getPositionX() ");

		//inv : getPositionY() = getCharBox().getPositionY()
		if (getPositionY() != getCharBox().getPositionY())
			throw new InvariantError("CharacterContract: getPositionY() = getCharBox().getPositionY()");
		
		//inv: getPositionX() >= 0 && getPositionX() <= getEngine().getWidth()
		if (getPositionX() < 0 || getPositionX() > getEngine().getWidth())
			throw new InvariantError("CharacterContract: getPositionX() < 0 || getPositionX() > getEngine().getWidth()");
		
		//inv: getPositionY() >= 0 && getPositionY() < getEngine().getHeight()
		if (getPositionY() < 0 || getPositionY() >= getEngine().getHeight())
			throw new InvariantError("CharacterContract: getPositionY() < 0 && getPositionY() >= getEngine().getHeight()");
		
		//inv: isDead() = !(getLife() > 0)
		if (isDead() == (getLife() > 0))
			throw new InvariantError("isDead() = !(getLife() > 0)");
		
	}
	

	@Override
	public void init(int l, int s, boolean f/*, EngineService e*/) {
		//inv@pre
		checkInvariant();
		
		//pre: l > 0 && s >= 0 
		if (l <= 0 || s < 0)
			throw new PreconditionError("CharacterContract:l > 0 && s >= 0 ");
		
		//run 
		super.init(l, s, f);
		
		//inv@post
		checkInvariant();

		//post: getLife() = l && getSpeed() = s && isRightFace() = f && !isReady()
		if (getLife() != l || getSpeed() != s || isRightFace() != f || isReady() )
			throw new PostconditionError("CharacterContract:getLife() = getLife() != l || getSpeed() != s || isRightFace() != f || isReady()");
		
		//post: \exists h:HitBox {getCharBox() = h}
		if(getCharBox() == null)
			throw new PostconditionError("getCharBox() == null");
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
		 * 			if getPositionX()@pre >= getSpeed()
		 * 				then getPositionX() = getPositionX()@pre - getSpeed()
		 * 			else
		 * 				getPositionX() = 0
		 */
		boolean collision = false;
		for (int i = 0; i <= getEngine().getNbPlayers(); i++){
			if (getEngine().getChar(i) != this && getCharBox().isCollidesWith(getEngine().getChar(i).getCharBox()) ){
				collision = true; 
			}
		}
		
		if (collision){
			if (getPositionX() != getPositionX_atPre)
				throw new PostconditionError("CharacterContract: getPositionX() != getPositionX_atPre");
		}else{
			if (getPositionX_atPre >= getSpeed()){
				if (getPositionX() != getPositionX_atPre - getSpeed()){
					throw new PostconditionError("CharacterContract: getPositionX() != getPositionX_atPre - getSpeed()");
			}else
				if (getPositionX() != 0)
					throw new PostconditionError("CharacterContract: getPositionX() != 0");
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
		 * 			if getPositionX()@pre + getSpeed() <= engine().getWidth()
		 * 				then getPosition() = getPositionX()@pre + getSpeed()
		 * 			else
		 * 				getPositionX() = engine().getWidth()
		 */
		boolean collision = false;
		for (int i = 0; i <= getEngine().getNbPlayers(); i++){
			if (getEngine().getChar(i) != this && getCharBox().isCollidesWith(getEngine().getChar(i).getCharBox()) ){
				collision = true; 
			}
		}
		
		if (collision){
			if (getPositionX() != getPositionX_atPre)
				throw new PostconditionError("CharacterContract: post1: positionX unchanged");
		}else{
			if (getPositionX_atPre + getSpeed() <= getEngine().getWidth()){
				if (getPositionX() != getPositionX_atPre + getSpeed()){
					throw new PostconditionError("CharacterContract: post1: positionX moved");
			}else
				if (getPositionX() != getEngine().getWidth())
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
		//double getPositionX_atPre = getPositionX();
		//CharacterService this_atPre = (CharacterService)this.clone();


		//pre: !isDead() && isReady()
		if (isDead() || !isReady())
			throw new PostconditionError("CharacterContract: !isDead");

		//run 
		super.step(c);
			
		//inv@post
		checkInvariant();
					
	}
	
	@Override
	public void setEngine(EngineService engine) {
		//inv@pre
		checkInvariant();
		
		//pre: !isReady()
		if (isReady())
			throw new PostconditionError("CharacterContract: isReady()");
		
		super.setEngine(engine);
		
		//post: isReady()
		if (!isReady())
			throw new PostconditionError("CharacterContract: isReady()");

	}
		
}
