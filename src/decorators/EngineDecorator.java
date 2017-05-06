package decorators;

import javafx.scene.Parent;
import services.CharacterService;
import services.Commande;
import services.EngineService;
import services.FightCharService;
import services.PlayerService;

public class EngineDecorator extends Parent implements EngineService,Runnable{

private final EngineService delegate;
	

	public EngineDecorator(EngineService delegate) {
		this.delegate = delegate;
	}


	@Override
	public int getHeight() {
		return delegate.getHeight();
	}


	@Override
	public int getWidth() {
		
		return delegate.getWidth();
	}
	
	@Override
	public int getSpace() {
		
		return delegate.getSpace();
	}


	@Override
	public FightCharService getChar(int i) {
		
		return delegate.getChar(i);
	}


	@Override
	public PlayerService getPlayer(int i) {
		
		return delegate.getPlayer(i);
	}


	@Override
	public boolean isGameOver() {
		
		return delegate.isGameOver();
	}


	@Override
	public void init(int h, int w, int s, PlayerService p1, PlayerService p2) {
		
		delegate.init(h, w, s, p1, p2);
	}


	@Override
	public void run() {
		delegate.run();
	}


	@Override
	public int getNbPlayers() {
		return delegate.getNbPlayers();
	}


	@Override
	public void updateGame() {
		 delegate.updateGame();
		
	}


	@Override
	public void step() {
		 delegate.step();
		
	}


	@Override
	public void updateAllVictims() {
		 delegate.updateAllVictims();
		
	}


	@Override
	public void updateAllAttackers() {
		 delegate.updateAllAttackers();
		
	}


	@Override
	public void updateAllFrames() {
		 delegate.updateAllFrames();
		
	}


	@Override
	public void setNewPlayer(PlayerService p) {
		 delegate.setNewPlayer(p);
		
	}
}
