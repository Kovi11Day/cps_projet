package decorators;

import javafx.scene.Parent;
import services.CharacterService;
import services.Commande;
import services.EngineService;
import services.HitboxService;

public class CharacterDecorator extends Parent implements CharacterService{
	private final CharacterService delegate;
	

	public CharacterDecorator(CharacterService delegate) {
		this.delegate = delegate;
	}
	
	@Override
	public int getPositionX() {
		return delegate.getPositionX();
	}

	@Override
	public int getPositionY() {
		return delegate.getPositionY();
	}

	@Override
	public EngineService getEngine() {
		
		return delegate.getEngine();
	}

	@Override
	public HitboxService getCharBox() {
		
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
	public CharacterService clone() {
		return delegate.clone();
	}

	@Override
	public boolean equals(CharacterService c) {
		return delegate.equals(c);
	}

	@Override
	public void setEngine(EngineService engine) {
		delegate.setEngine(engine);
	}
	
}
