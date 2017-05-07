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


	public Gui() {
		super();
		this.arene = new Pane();
		this.players = new ArrayList<>();
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
		
		arene.setMinWidth(engine.getWidth()); 
		arene.setMinHeight(engine.getHeight());
		
		this.getChildren().add(arene);
		
		for (int i = 0 ; i < engine.getNbPlayers(); i++){
			//visual character hitbox
			Rectangle rect = new Rectangle(engine.getChar(i).getWidth(), engine.getChar(i).getHeight());
			//visual tech hitbox
			Rectangle techRect = new Rectangle(engine.getChar(i).getTechBox().getWidth(), engine.getChar(i).getTechBox().getHeight());
			
			
			ProgressBar pb = new ProgressBar();
			pb.setMaxSize(100, 40);
			pb.setProgress(((float)1));

			if (i == 0){
				rect.setFill(Color.RED);
				techRect.setFill(Color.RED);
			}
			else if (i == 1){
				rect.setFill(Color.BLUE);
				techRect.setFill(Color.BLUE);
				pb.setLayoutX(engine.getWidth()-pb.getMaxWidth());
			}
			
			StructPlayer sp = new StructPlayer(rect, techRect, pb, engine.getChar(i).getLife());
			players.add(sp);

			this.getChildren().add(sp);

			
			updatePlayerI(i);
		}
	

	}

	//form game character origin to javafx origin
	public double transOriginX(double x, int width){
		return (x - (width/2));
	}
	public double transOriginY(double y, int height){
		return y + (engine.getHeight() - height);
	}
	
	public void updatePlayerI(int i){
		//update player positions
		players.get(i).figure.setX(transOriginX(engine.getChar(i).getPositionX(),engine.getChar(i).getWidth()));
		players.get(i).figure.setY(transOriginY(engine.getChar(i).getPositionY(),engine.getChar(i).getHeight()));
		//players.get(i).figure.setX(transOriginX(engine.getChar(i).getPositionX());
		//players.get(i).figure.setY(0);

		players.get(i).lifeBar.setProgress((engine.getChar(i).getLife()*1.0)/(players.get(i).maxLife*1.0));
		if (engine.getChar(i).isBlocking()){
			if(i == 0)
					players.get(i).techOutline.setFill(Color.CRIMSON);
			else
				players.get(i).techOutline.setFill(Color.BLUEVIOLET);
		}else{
			if(i == 0)
				players.get(i).techOutline.setFill(Color.RED);
			else
				players.get(i).techOutline.setFill(Color.BLUE);
		}
		//update techBox (if necessary)
		if (engine.getChar(i).isInHitFrame()){
			//update player
			players.get(i).techOutline.setWidth(engine.getChar(i).getTechBox().getWidth());
			players.get(i).techOutline.setHeight(engine.getChar(i).getTechBox().getHeight());
			players.get(i).techOutline.
			setX(transOriginX(engine.getChar(i).getTechBox().getPositionX(), engine.getChar(i).getTechBox().getWidth()));
			players.get(i).techOutline.
			setY(transOriginY(engine.getChar(i).getTechBox().getPositionY(), engine.getChar(i).getTechBox().getHeight()));
			players.get(i).techOutline.setVisible(true);		

		}else{

			players.get(i).techOutline.setVisible(false);		

		}

	}

}
