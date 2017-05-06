package decorators;

import javafx.scene.Parent;
import services.CharacterService;
import services.Commande;
import services.EngineService;
import services.RectangleHitboxService;

public class CharacterDecorator  implements CharacterService{
	private final CharacterService delegate;
	

	public CharacterDecorator(CharacterService delegate) {
		this.delegate = delegate;
	}
	
	@Override
	public double getPositionX() {
		return delegate.getPositionX();
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
	public void init(int l, int s, boolean f/*, EngineService e*/) {
		 delegate.init(l, s, f/*, e*/);
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
	public void step(Commande c) {
		delegate.step(c);
		
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
	public boolean isReady() {
		return delegate.isReady();
	}
	
}
