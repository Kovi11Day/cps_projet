package contracts;

import services.CharacterService;
import services.HitboxService;
import decorators.HitboxDecorator;

public class HitboxContract extends HitboxDecorator{

	public HitboxContract(HitboxService delegate) {
		super(delegate);
	}
	
	@Override
	public int getPositionX() {
		// TODO Auto-generated method stub
		return 	super.getPositionX();
	}

	@Override
	public int getPositionY() {
		// TODO Auto-generated method stub
		return 	super.getPositionY();

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
		super.init(x, y);

	}

	@Override
	public void moveTo(int x, int y) {
		// TODO Auto-generated method stub
		super.moveTo(x, y);
	}
}
