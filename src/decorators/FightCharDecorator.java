package decorators;

import services.CharacterService;
import services.Commande;
import services.EngineService;
import services.FightCharService;
import services.RectangleHitboxService;
import services.Tech;

public class FightCharDecorator implements FightCharService{
	private final FightCharService delegate;
	

	public FightCharDecorator(FightCharService delegate) {
		this.delegate = delegate;
	}
	@Override
	public double getPositionX() {
		
		return delegate.getPositionX() ;
	}

	@Override
	public double getPositionY() {
		
		return delegate.getPositionY();
	}

	@Override
	public EngineService getEngine() {
		
		return delegate.getEngine();
	}

	@Override
	public RectangleHitboxService getCharBox() {
		
		return delegate.getCharBox();
	}

	@Override
	public int getLife() {
		
		return delegate.getLife();
	}

	@Override
	public int getSpeed() {
		
		return delegate.getSpeed();
	}

	@Override
	public boolean isRightFace() {
		
		return delegate.isRightFace();
	}

	@Override
	public boolean isDead() {
		
		return delegate.isDead();
	}

	@Override
	public void init(int l, int s, boolean f) {
		delegate.init(l, s, f);
		
	}

	@Override
	public void moveLeft() {
		delegate.moveLeft();
		
	}

	@Override
	public void moveRight() {
		
		delegate.moveRight();
	}

	@Override
	public void switchSide() {
		delegate.switchSide();
		
	}

	@Override
	public void setEngine(EngineService engine) {
		delegate.setEngine(engine);
		
	}


	@Override
	public int getWidth() {
		
		return delegate.getWidth();
	}

	@Override
	public int getHeight() {
		
		return delegate.getHeight();
	}

	@Override
	public boolean isBlocking() {
		
		return delegate.isBlocking();
	}

	@Override
	public boolean isBlockStunned() {
		
		return delegate.isBlockStunned();
	}

	@Override
	public boolean isHitStunned() {
		
		return delegate.isHitStunned();
	}

	@Override
	public boolean isTeching() {
		
		return delegate.isTeching();
	}

	@Override
	public Tech getTech() {
		
		return delegate.getTech();
	}

	@Override
	public boolean isTechHasAlreadyHit() {
		
		return delegate.isTechHasAlreadyHit();
	}

	@Override
	public boolean isInHitFrame() {
		
		return delegate.isInHitFrame();
	}

	@Override
	public boolean isControllable() {
		
		return delegate.isControllable();
	}

	@Override
	public int getTechFrameCounter() {
		
		return delegate.getTechFrameCounter();
	}

	@Override
	public int getBstunFrameCounter() {
		
		return delegate.getBstunFrameCounter();
	}

	@Override
	public int getHstunFrameCounter() {
		
		return delegate.getHstunFrameCounter();
	}

	@Override
	public FightCharService getOtherFightChar() {
		
		return delegate.getOtherFightChar();
	}

	@Override
	public RectangleHitboxService getTechBox() {
		
		return delegate.getTechBox();
	}

	@Override
	public int nbTechMastered() {
		
		return delegate.nbTechMastered();
	}

	@Override
	public Tech getTechMastered(int i) {
		
		return delegate.getTechMastered(i);
	}


	@Override
	public void startTech(Tech t) {
		
		delegate.startTech(t);
	}

	@Override
	public void step(Commande c) {
		
		delegate.step(c);
	}

	@Override
	public void updateVictim() {
		
		delegate.updateVictim();
	}

	@Override
	public void updateAttacker() {
		delegate.updateAttacker();
		
	}

	@Override
	public void updateFrames() {
		
		delegate.updateFrames();
	}

	@Override
	public void setOtherFightChar(FightCharService c) {
		delegate.setOtherFightChar(c);
		
	}

	@Override
	public void setNewTechMastered(Tech t) {
		
		delegate.setNewTechMastered(t);
	}
	@Override
	public boolean isReady() {
		return delegate.isReady();
	}

}
