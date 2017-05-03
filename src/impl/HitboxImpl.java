package impl;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import services.HitboxService;

public abstract class HitboxImpl implements HitboxService{
	private double x;
	private double y;
	//Rectangle box;//
	//Gui gui;
	public HitboxImpl(){
		//this.gui = gui;
		//this.box = new Rectangle();
		//this.getChildren().add(box);

		//box.setWidth(40);
		//box.setHeight(125);
		//Circle face = new Circle(10);
		//face.setFill(Color.RED);
		
	}

	@Override
	public double getPositionX() {
		
		return x;
		//return (int) (x + (box.getWidth()/2));
	}

	@Override
	public double getPositionY() {
		
		return y;
		//return (int) (y-box.getHeight());
	}

	@Override
	public boolean isBelongsTo(double x, double y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public abstract boolean isCollidesWith(HitboxService h);

	@Override
	public abstract boolean isEqualsTo(HitboxService h);

	@Override
	public void init(double x, double y) {
		this.x=x;
		this.y=y;
		
		
	}

	@Override
	public void moveTo(double x, double y) {
		// TODO Auto-generated method stub
		System.out.println("moveto callon: " + x + "," + y);
		this.x = x; 
		this.y = y;

	//	box.setX(x); 
		//box.setY(y);
		/*this.x = (int) (x-(box.getWidth()/2));
		this.y = (int) (y+box.getHeight());
		box.setX(x-(box.getWidth()/2));
		box.setY(y+box.getHeight());*/
	}
}
