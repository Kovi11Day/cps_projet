package decorators;

import services.HitboxService;
import services.RectangleHitboxService;

public class RectangleHitboxDecorator extends HitboxDecorator 
	implements RectangleHitboxService{

	public RectangleHitboxDecorator(RectangleHitboxService delegate) {
		super(delegate);
		
	}
	
	public RectangleHitboxService getDelegate(){
		return (RectangleHitboxService)super.getDelegate();
	}

	@Override
	public int getHeight() {
		
		return getDelegate().getHeight();
	}

	@Override
	public int getWidth() {
		
		return getDelegate().getWidth();
	}

	@Override
	public void init(double x, double y) {
		getDelegate().init(x, y);
		
	}

	@Override
	public void setWidthHeight(int w, int h) {
		getDelegate().setWidthHeight(w, h);
		
	}

	@Override
	public boolean isBelongsTo(double x, double y) {
		
		return getDelegate().isBelongsTo(x, y);
	}

	@Override
	public boolean isCollidesWith(HitboxService h) {
		
		return getDelegate().isCollidesWith(h);
	}

	@Override
	public boolean isEqualsTo(HitboxService h) {
		
		return getDelegate().isEqualsTo(h);
	}

}
