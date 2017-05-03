package decorators;

import javafx.scene.Parent;
import services.HitboxService;

public class HitboxDecorator extends Parent implements HitboxService{
	private final HitboxService delegate;
	public HitboxDecorator(HitboxService delegate) {
		this.delegate = delegate;
	}
	@Override
	public int getPositionX() {
		// TODO Auto-generated method stub
		return 	delegate.getPositionX();
	}

	@Override
	public int getPositionY() {
		// TODO Auto-generated method stub
		return 	delegate.getPositionY();

	}

	@Override
	public boolean isBelongsTo(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCollidesWith(HitboxService h) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEqualsTo(HitboxService h) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void init(int x, int y) {
		// TODO Auto-generated method stub
		delegate.init(x, y);

	}

	@Override
	public void moveTo(int x, int y) {
		// TODO Auto-generated method stub
		delegate.moveTo(x, y);
	}

}
