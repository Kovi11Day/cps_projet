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
	
	//
	Pane pane;
	Gui gui;
	public EngineImpl(Gui gui){
		super();
		this.gui = gui;
		pane = new Pane();
		this.getChildren().add(pane);
		this.players = new ArrayList<>();
	}
	
	@Override
	public void init(int h, int w, int s, PlayerService p1, PlayerService p2) {
		this.height = h;
		this.width = w;
		this.space = s;
		this.players.add(p1);
		this.players.add(p2);
		//gui
		pane.setMinWidth(w); 
		pane.setMinHeight(h);
	}
	
	@Override
	public void run() {
		while(!isGameOver()){
			try {
				Thread.sleep(GlobalVariables.frameTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			step();
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
		for(int i = 0; i < players.size(); i++){
			if(players.get(i).getChar().isDead()){
				return true;
			}
		}
		return false;
	}

	@Override
	public int getNbPlayers() {
		return this.players.size();
	}


	@Override
	public void setNewPlayer(PlayerService p) {
		this.players.add(p);
	}

	@Override
	public void updateGame() {
		step();
		updateAllVictims();
		updateAllAttackers();
		updateAllFrames();
	}

	@Override
	public void step() {
		for(int i = 0; i < players.size(); i++){
			getChar(i).step(getPlayer(i).getCommande());
		}		
	}

	@Override
	public void updateAllVictims() {
		for(int i = 0; i < players.size(); i++){
			getChar(i).updateVictim();;
		}				
	}

	@Override
	public void updateAllAttackers() {
		for(int i = 0; i < players.size(); i++){
			getChar(i).updateAttacker();;
		}				
	}

	@Override
	public void updateAllFrames() {
		for(int i = 0; i < players.size(); i++){
			getChar(i).updateFrames();
		}		
		
	}

	

}
