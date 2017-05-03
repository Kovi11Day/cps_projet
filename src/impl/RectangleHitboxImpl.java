package impl;

import services.GlobalVariables;
import services.HitboxService;
import services.RectangleHitboxService;

public class RectangleHitboxImpl extends HitboxImpl implements RectangleHitboxService{

	private int width;
	private int height;
	public RectangleHitboxImpl() {
		
		
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
		width =GlobalVariables.boxWidth;
		height=GlobalVariables.boxHeight;
		
		
	}
	@Override
	public void setWidthHeight(int w, int h) {
		this.height = h;
		this.width = w;
		
	}
	@Override
	public boolean isCollidesWith(HitboxService h) {
		if(h instanceof RectangleHitboxImpl){
			RectangleHitboxImpl rh = (RectangleHitboxImpl) h;
			double xmax1 = getPositionX() + (width/2.0);
			double xmin1 = getPositionX() - (width/2.0);
			double xmax2 = h.getPositionX() + (rh.getWidth()/2.0);
			double xmin2 = h.getPositionX() - (rh.getWidth()/2.0);
			//System.out.println(getPositionX()+" "+xmax1);
			//System.out.println(rh.getPositionX()+" "+xmax2);
			//System.out.println(xmin1+"  "+xmin2);
			/*if(xmax1>= xmin2 && xmax1<xmax2){
				
				return true;
			}
			if(xmin2 >= xmin1 && xmin2 < xmax1){

				return true;
			}*/
			//formule collision:|distX|<=0.5*(this.width + h.width) ||
			//|distY|<=0.5*(this.height + h.height)
			//formule translationcentre: (xcentre,ycentre) = (x,y+0.5*rect.height)
			
			//les 2 points egaux
			if(rh.getPositionX()==this.getPositionX()
					&& rh.getPositionY()+(rh.height/2.0)==this.getPositionY()+(this.height/2.0)){
				System.out.println("COLLISION eq");

				return true;
			}
			//les 2 points differents
			if ((rh.getPositionX()!=this.getPositionX())
					&& (rh.getPositionY()+(rh.height/2.0)!=this.getPositionY()+(this.height/2.0))
					&& ((Math.abs(rh.getPositionX() - this.getPositionX()) <= (this.getWidth()+rh.getWidth())/2.0)
					||(Math.abs((rh.getPositionY()+(rh.height/2.0)) - (this.getPositionY()+(this.height/2.0))) 
								<= ( this.getHeight()+rh.getHeight())/2.0))){
				System.out.println("NOT COLLINEAR");
				return true;	
			}
			//les 2 points en horizontal
			if ( rh.getPositionY()+(rh.height/2.0)==this.getPositionY()+(this.height/2.0)
					&& Math.abs(rh.getPositionX() - this.getPositionX()) <= (this.getWidth()+rh.getWidth())/2.0){
				System.out.println("COLLISION X: "+(this.getWidth()+rh.getWidth())/2.0);
				return true;
			}
			//les 2 points en vertical
			if (rh.getPositionX()==this.getPositionX() &&Math.abs((rh.getPositionY()+(rh.height/2.0)) - (this.getPositionY()+(this.height/2.0))) 
					<= ( this.getHeight()+rh.getHeight())/2.0){
				System.out.println("COLLISION Y");
				return true;
			}
			
			return false;
		}
		
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

	

}
