package decorators;

import javafx.scene.Parent;
import services.CharacterService;
import services.Commande;
import services.PlayerService;

public class PlayerDecorator extends Parent implements PlayerService{
	
	private final PlayerService delegate;

	public PlayerDecorator(PlayerService delegate) {
		this.delegate = delegate;
	}
	
	@Override
	public CharacterService getChar() {
		
		return delegate.getChar();
	}

	@Override
	public String getKeyLeft() {
		
		return delegate.getKeyLeft();
	}

	@Override
	public String getKeyRight() {
		
		return delegate.getKeyRight();
	}

	@Override
	public String getKeyNeutral() {
		
		return delegate.getKeyNeutral();
	}

	@Override
	public void init( CharacterService c,String left, String right, String neutral) {
		delegate.init(c,left, right, neutral);
		
	}

	@Override
	public Commande getCommande() {
		return delegate.getCommande();
	}

	@Override
	public void handleKey(String key) {
		delegate.handleKey(key);
	}

}
