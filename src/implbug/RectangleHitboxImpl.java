package implbug;

import services.GlobalVariables;
import services.HitboxService;
import services.RectangleHitboxService;

public class RectangleHitboxImpl extends HitboxImpl implements RectangleHitboxService{

	private int width;
	private int height;
	public RectangleHitboxImpl() {
		super();
		
	}
	@Override
	public int getHeight() {
		
		return height;
	}
	@Override
	public int getWidth() {
		return width;
	}
	@Override
	public void init(double x, double y) {
		super.init(x, y);
		//width =GlobalVariables.boxWidth;
		//height=GlobalVariables.boxHeight;
		
		
	}
	@Override
	public void setWidthHeight(int w, int h) {
		this.height = h;
		this.width = w;
		
	}
	@Override
	public boolean isCollidesWith(HitboxService h) {
		/*if(h instanceof RectangleHitboxImpl){
			RectangleHitboxImpl h2 = (RectangleHitboxImpl) h;
			if(h2.getPositionX()-(h2.getWidth()/2.0) >= 
					getPositionX()-(getWidth()/2.0)+getWidth() ||
					h2.getPositionX()-(h2.getWidth()/2.0) + h2.getWidth() <=
					getPositionX()-(getWidth()/2.0) ||
					h2.getPositionY() >=
					getPositionY() +getHeight() ||
					h2.getPositionY()+ h2.getHeight() <= 
					getPositionY()){
						return false;

			}
			System.out.println("isCollidesWith");
			return true;
		}*/
		return false;

	}
	
	@Override
	public boolean isEqualsTo(HitboxService h) {
		if(h instanceof RectangleHitboxImpl){
			RectangleHitboxImpl rh = (RectangleHitboxImpl)h;
			if(rh.getHeight()!= getHeight() || rh.getWidth()!=getWidth()){
				return false;
			}
			if(rh.getPositionX()!= getPositionX() || rh.getPositionY()!=getPositionY()){
				return false;
			}
			return true;
		}
		return false;
	}
	@Override
	public boolean isBelongsTo(double x, double y) {
		if(x>= getPositionX()-(getWidth()/2.0) && 
				x<= getPositionX()+(getWidth()/2.0) && 
				y>= getPositionY() &&
				y<= getPositionY()+getHeight()){
			return true;
			
		}
		return false;
	}

	

}
