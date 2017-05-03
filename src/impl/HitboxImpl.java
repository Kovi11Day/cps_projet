package impl;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import services.HitboxService;

public class HitboxImpl implements HitboxService{
	int x; int y; int height; int width;
	//Rectangle box;//
	//Gui gui;
	public HitboxImpl(){
		//this.gui = gui;
		this.x = 0; 
		this.y = 0;
		//this.box = new Rectangle();
		//this.getChildren().add(box);

		//box.setWidth(40);
		//box.setHeight(125);
		//Circle face = new Circle(10);
		//face.setFill(Color.RED);
	}
	public int getWidth() {
		return this.width;
	}
	public int getHeight() {
		return this.height;
	}
	@Override
	public int getPositionX() {
		// TODO Auto-generated method stub
		return x;
		//return (int) (x + (box.getWidth()/2));
	}

	@Override
	public int getPositionY() {
		// TODO Auto-generated method stub
		return y;
		//return (int) (y-box.getHeight());
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
	public void init(int x, int y, int w, int h) {
		this.x=x;
		this.y=y;
		this.width= w;
		this.height = h;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveTo(int x, int y) {
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
