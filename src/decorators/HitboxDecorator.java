package decorators;

import javafx.scene.Parent;
import services.HitboxService;

public abstract class HitboxDecorator extends Parent implements HitboxService{
	
	private final HitboxService delegate;
	
	public HitboxDecorator(HitboxService delegate) {
		this.delegate = delegate;
	}
	
	public HitboxService getDelegate(){
		return delegate;
	}
	@Override
	public double getPositionX() {
		
		return 	delegate.getPositionX();
	}

	@Override
	public double getPositionY() {
		
		return 	delegate.getPositionY();

	}


	@Override
	public void init(double x, double y) {
		
		delegate.init(x, y);

	}

	@Override
	public void moveTo(double x, double y) {
		
		delegate.moveTo(x, y);
	}

}
