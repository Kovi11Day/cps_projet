package impl;

import java.util.ArrayList;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import services.Commande;
import services.EngineService;
import services.FightCharService;
import services.GlobalVariables;
import services.PlayerService;

public class EngineImpl extends Parent implements EngineService, Runnable{
	
	private int height;
	private int width;
	private int space;
	private ArrayList<PlayerService> players;
	private boolean gameOver;
	
	//
	Pane pane;
	Gui gui;
	public EngineImpl(Gui gui){
		super();
		this.gui = gui;
		//init(h,w,s,p1,p2);
		pane = new Pane();
		//pane.setMinWidth(w); //add size of character width so that character stays within sight
		//pane.setMinHeight(h);
		  this.getChildren().add(pane);
		  
		  	
	}
	
	@Override
	public void init(int h, int w, int s, PlayerService p1, PlayerService p2) {
		this.height = h;
		this.width = w;
		this.space = s;
		this.players = new ArrayList<>();
		this.players.add(p1);
		this.players.add(p2);
		
		//---p1.getChar().getCharBox().moveTo(w/2-s/2, 0);
		//---p2.getChar().getCharBox().moveTo(w/2+s/2, 0);
		
		
		pane.setMinWidth(w); 
		pane.setMinHeight(h);
		
		//HitboxImpl h1 = (HitboxImpl) p1.getChar().getCharBox();
		// HitboxImpl h2 = (HitboxImpl) p2.getChar().getCharBox();
		  //this.getChildren().add(h1);
		  //	this.getChildren().add(h2);
		/*
		this.getChildren().add((HitboxContract)p1);
		  	this.getChildren().add(((CharacterImpl)p1.getChar()).getProgressBar());
		  	ProgressBar pb2 = ((CharacterImpl)p2.getChar()).getProgressBar();
		  	pb2.setLayoutX(w - pb2.getMaxWidth());
		  	this.getChildren().add(((CharacterImpl)p2.getChar()).getProgressBar());*/
	}
	
	@Override
	public void run() {
		while(!gameOver){
			try {
				Thread.sleep(GlobalVariables.frameTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			step(players.get(0).getCommande(), players.get(1).getCommande());
		}
		System.out.println("GAME OVER");
	}
	@Override
	public int getHeight() {
		
		return this.height;
	}

	//@Override
	public int getWidth() {
		
		return this.width;
	}
	//@Override
		public int getSpace() {
			
			return this.space;
		}
	@Override
	public FightCharService getChar(int i) {
		
		return this.players.get(i).getChar();
	}

	@Override
	public PlayerService getPlayer(int i) {
		
		return this.players.get(i);
	}

	@Override
	public boolean isGameOver() {
		return this.gameOver;
	}

	

	@Override
	public void step(Commande c1, Commande c2) {
		players.get(0).getChar().step(c1);
		players.get(1).getChar().step(c2);
		//players.get(0).getChar().updateStatus();
		//players.get(1).getChar().updateStatus();
		players.get(0).getChar().updateVictim();
		players.get(1).getChar().updateVictim();
		players.get(0).getChar().updateAttacker();
		players.get(1).getChar().updateAttacker();

		gui.updatePlayerI(0);
		gui.updatePlayerI(1);
		players.get(0).getChar().updateFrames();
		players.get(1).getChar().updateFrames();


		}

	

}
