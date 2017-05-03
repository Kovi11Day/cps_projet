package impl;

import services.HitboxService;
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
		
		return 20;
	}

	@Override
	public int rframe() {
		
		return 3;
	}

	@Override
	public HitboxService hitbox(int x, int y, int w , int h, boolean faceRight) {
		HitboxService techBox = new HitboxImpl();
		int face = 1;
		if (!faceRight)
			face = -1;
		int coupWidth = w;
		int coupHeight = h;
		techBox.init(x , 
				y,
				coupWidth,
				coupHeight);
		System.out.println();
		/*techBox.init((int)(x + face* (0.5*w + 0.5*coupWidth)) , 
				(int)(y + 0.5*h),
				coupWidth,
				coupHeight);*/
		return techBox;
	}

}
