package impl;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import services.EngineService;
import services.GlobalVariables;

public class Gui extends Parent{
	private Pane arene;
	private ArrayList<StructPlayer> players;
	private EngineService engine;
	
	private int recWidth = 40;
	private int recHeight = 125;
	
	private Color[] colorPlayer = {Color.RED, Color.BLUE};	

	public Gui() {
		super();
		this.arene = new Pane();
		this.players = new ArrayList<>();
		colorPlayer = new Color[2];

	}
	
	class StructPlayer extends Parent{
		Rectangle figure;
		Rectangle techOutline;
		ProgressBar lifeBar;
		int maxLife;
		
		StructPlayer(Rectangle p,Rectangle t,ProgressBar l, int m){
			figure = p; 
			lifeBar = l;
			maxLife = m;
			techOutline = t;
			this.getChildren().add(p);
			this.getChildren().add(l);
			this.getChildren().add(t);

		}
	}
	public void init(EngineService engine){
		this.engine = engine;
		
		//this.arene = new Pane();
		arene.setMinWidth(engine.getWidth()); //add size of character width so that character stays within sight
		arene.setMinHeight(engine.getHeight());
		
		
		this.getChildren().add(arene);
		
		for (int i = 0 ; i < GlobalVariables.nbPlayersMax; i++){
			Rectangle rect = new Rectangle(recWidth, recHeight);
			Rectangle techRect = new Rectangle(engine.getChar(i).getTechBox().getWidth(), engine.getChar(i).getTechBox().getHeight());

			//rect.setFill(colorPlayer[i]);
			if (i == 0){
				rect.setFill(Color.RED);
				techRect.setFill(Color.RED);
			}
			else if (i == 1){
				rect.setFill(Color.BLUE);
				techRect.setFill(Color.BLUE);
			}

			//this.getChildren().add(rect);
			
			ProgressBar pb = new ProgressBar();
			pb.setMaxSize(100, 40);
			pb.setProgress(((float)1));
			//this.getChildren().add(pb);

			//int maxLife = engine.getPlayer(i).getChar().getLife();
			StructPlayer sp = new StructPlayer(rect, techRect, pb, 0);
			this.getChildren().add(sp);

			players.add(sp);
			
			//this.engine = engine;System.out.println("carbox before:set engine"  +
					//getPositionX(i) + ","  +getPositionY(i)); 
			//original position of players
			updatePlayerI(i);
			//this.engine = engine;System.out.println("carbox after engine"  +
					//getPositionX(i) + ","  +getPositionY(i));
		}
	

	}
	
	public void updateProgressBarI(int i, float progress){
		
	}
	
	/*public int getPositionX(int i){
		return engine.getChar(i).getPositionX()-(engine.getWidth()/2);
	}
	public int getPositionY(int i){
		return engine.getChar(i).getPositionY()+engine.getHeight();
	}*/
	public int transOriginX(int x, int width){
		return (int) (x - 0.5*width);
	}
	public int transOriginY(int y, int height){
		return (y +height) + engine.getHeight();
	}
	public void updatePlayerI(int i){
		//update player positions
		players.get(i).figure.setX(transOriginX(engine.getChar(i).getPositionX(),recWidth));
		players.get(i).figure.setY(transOriginY(engine.getChar(i).getPositionY(),recHeight));
		//update techBox (if necessary)
		if (engine.getChar(i).isInHitFrame()){
			System.out.println("isInHitFrame=" + engine.getChar(i).isInHitFrame());
			//update player
			players.get(i).techOutline.setWidth(engine.getChar(i).getTechBox().getWidth());
			players.get(i).techOutline.setHeight(engine.getChar(i).getTechBox().getHeight());
			players.get(i).techOutline.setX(transOriginX(engine.getChar(i).getTechBox().getPositionX(), engine.getChar(i).getTechBox().getWidth()));
			players.get(i).techOutline.setY(transOriginY(engine.getChar(i).getTechBox().getPositionY(), engine.getChar(i).getTechBox().getHeight()));
			players.get(i).techOutline.setVisible(true);		

		}else{
			/*players.get(i).techOutline.setWidth(engine.getChar(i).getTechBox().getWidth());
			players.get(i).techOutline.setHeight(engine.getChar(i).getTechBox().getHeight());
			players.get(i).techOutline.setX(transOriginX(engine.getChar(i).getTechBox().getPositionX(), engine.getChar(i).getTechBox().getWidth()));
			players.get(i).techOutline.setY(transOriginY(engine.getChar(i).getTechBox().getPositionY(), engine.getChar(i).getTechBox().getHeight()));
			players.get(i).techOutline.setVisible(true);*/	
			players.get(i).techOutline.setVisible(false);		

		}
		//TODO:update life bar
		
	}

}
