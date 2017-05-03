package decorators;

import javafx.scene.Parent;
import services.CharacterService;
import services.Commande;
import services.EngineService;
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
	public CharacterService getChar(int i) {
		
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
	public void step(Commande c1, Commande c2) {
		delegate.step(c1, c2);
	}


	@Override
	public void run() {
		delegate.run();
	}
}
