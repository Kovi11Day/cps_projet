package contracts;

import services.Commande;
import services.EngineService;
import services.FightCharService;
import services.RectangleHitboxService;
import services.Tech;
import decorators.FightCharDecorator;

public class FightCharContract extends CharacterContract implements FightCharService{
	public FightCharContract(FightCharService delegate) {
		super(delegate);
	}
	
	public void checkInvariant() {
	
		//inv : isTeching() == !(isBlocking() || isBlockStunned() || isHitStunned() )
		if (isTeching()) 
			if (!(isBlocking() && isBlockStunned()&&isHitStunned()))
				throw new InvariantError("isTeching() == (isBlocking() || isBlockStunned()||isHitStunned()");
				
		//inv: !(isBlockStunned() && isHitStunned() )
		if (isBlockStunned() && isHitStunned())
			throw new InvariantError("isBlockStunned() && isHitStunned()");
		
		//inv: isBlockStunned() ==> isBlocking()
		if (isBlockStunned() && !isBlocking())
			throw new InvariantError("isBlockStunned() ==> isBlocking()");
		
		//inv: isControllable() = !(isTeching() || isBlockStunned() || isHitStunned())
		if (isControllable() == (isTeching() || isBlockStunned() || isHitStunned()))
			throw new InvariantError("isControllable() == (isTeching() || isBlockStunned() || isHitStunned()");
		
		//inv:getTechFrameCounter() >=0 && getBStunFrameCounter() >=0 &&  getHStunFrameCounter()>=0
		if (getTechFrameCounter() <0 || getBstunFrameCounter() <0 ||  getHstunFrameCounter() < 0)
			throw new InvariantError("getTechFrameCounter() >=0 && getBStunFrameCounter() >=0 &&  getHStunFrameCounter()>=0");
		
		//inv:!isTeching() ==> !isInHitFrame()
		if (!isTeching() && isInHitFrame())
			throw new InvariantError("!isTeching() ==> !isInHitFrame()");
		
		//inv:isTeching() == getTechFrameCounter()>0
		if (isTeching() != getTechFrameCounter()>0)
			throw new InvariantError("isTeching() == getTechFrameCounter()>0");
		
		//inv: isBlockStunned() == getBStunFrameCounter()>0
		if (isBlockStunned() != getBstunFrameCounter()>0)
			throw new InvariantError("isBlockStunned() == getBStunFrameCounter()>0");
		
		//inv:isHitStunned() == getHStunFrameCounter() >0
		if (isHitStunned() != getHstunFrameCounter() >0)
			throw new InvariantError("isHitStunned() == getHstunFrameCounter() >0");
		
		//inv:isTeching() && isInHitFrame() ==> (
		// (getTech().getRFrame() < getTechFrameCounter()) &&
		// ( getTechFrameCounter() >= (getTech().getRFrame() + getTech().getHFrame()) )
		// )
		if (isTeching() && isInHitFrame()){
			if (!(getTech().rframe() < getTechFrameCounter() && getTechFrameCounter() >= getTech().rframe()  +getTech().hframe())){
				throw new InvariantError("isTeching() && isInHitFrame() ==> IN APPROPRIATE RANGE");
			}
		}
	}
		
		//initializers
		//TODO:init
		@Override
		public double getPositionX() {
			
			return super.getPositionX() ;
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
		public void init(int l, int s, boolean f) {
			super.init(l, s, f);
			
		}

		@Override
		public void moveLeft() {
			super.moveLeft();
			
		}

		@Override
		public void moveRight() {
			
			super.moveRight();
		}

		@Override
		public void switchSide() {
			super.switchSide();
			
		}

		@Override
		public void setEngine(EngineService engine) {
			super.setEngine(engine);
			
		}


		@Override
		public int getWidth() {
			
			return super.getWidth();
		}

		@Override
		public int getHeight() {
			
			return super.getHeight();
		}

		@Override

		public boolean isBlocking() {
			
			return super.isBlocking();
		}

		@Override
		public boolean isBlockStunned() {
			
			return super.isBlockStunned();
		}

		@Override
		public boolean isHitStunned() {
			
			return super.isHitStunned();
		}

		@Override
		public boolean isTeching() {
			
			return super.isTeching();
		}

		@Override
		public Tech getTech() {
			
			return super.getTech();
		}

		@Override
		public boolean isTechHasAlreadyHit() {
			
			return super.isTechHasAlreadyHit();
		}

		@Override
		public boolean isInHitFrame() {
			
			return super.isInHitFrame();
		}

		@Override
		public boolean isControllable() {
			
			return super.isControllable();
		}

		@Override
		public int getTechFrameCounter() {
			
			return super.getTechFrameCounter();
		}

		@Override
		public int getBstunFrameCounter() {
			
			return super.getBstunFrameCounter();
		}

		@Override
		public int getHstunFrameCounter() {
			
			return super.getHstunFrameCounter();
		}

		@Override
		public FightCharService getOtherFightChar() {
			
			return super.getOtherFightChar();
		}

		@Override
		public RectangleHitboxService getTechBox() {
			
			return super.getTechBox();
		}

		@Override
		public int nbTechMastered() {
			
			return super.nbTechMastered();
		}

		@Override
		public Tech getTechMastered(int i) {
			
			return super.getTechMastered(i);
		}

		@Override
		public void init(int l, int s, boolean f, EngineService e) {
			
			super.init(l, s, f, e);
		}

		@Override
		public void startTech(Tech t) {
			
			super.startTech(t);
		}

		@Override
		public void step(Commande c) {
			
			super.step(c);
		}

		@Override
		public void updateVictim() {
			
			super.updateVictim();
		}

		@Override
		public void updateAttacker() {
			super.updateAttacker();
			
		}

		@Override
		public void updateFrames() {
			
			super.updateFrames();
		}

		@Override
		public void setOtherFightChar(FightCharService c) {
			super.setOtherFightChar(c);
			
		}

		@Override
		public void setNewTechMastered(Tech t) {
			
			super.setNewTechMastered(t);
		}
		
	
	
}
	