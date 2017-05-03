package impl;

import services.HitboxService;
import services.RectangleHitboxService;
import services.Tech;


public class CoupDePoing implements Tech{

	@Override
	public int damage() {
		
		return 10;
	}

	@Override
	public int hstun() {
		
		return 3;
	}

	@Override
	public int bstun() {
		
		return 2;
	}

	@Override
	public int sframe() {
		
		return 4;
	}

	@Override
	public int hframe() {
		
		return 10;
	}

	@Override
	public int rframe() {
		
		return 5;
	}

	@Override
	public RectangleHitboxService hitbox(double x, double y, int w , int h, boolean faceRight) {
		RectangleHitboxService techBox = new RectangleHitboxImpl();
		int face = 1;
		if (!faceRight)
			face = -1;
		int coupWidth = w;
		int coupHeight = h;
		techBox.init(x, y);
		techBox.setWidthHeight(coupWidth, coupHeight);
		
		System.out.println();
		/*techBox.init((int)(x + face* (0.5*w + 0.5*coupWidth)) , 
				(int)(y + 0.5*h),
				coupWidth,
				coupHeight);*/
		return techBox;
	}

}
