package decorators;

import javafx.scene.Parent;
import services.Commande;
import services.FightCharService;
import services.PlayerService;

public class PlayerDecorator extends Parent implements PlayerService{
	
	private final PlayerService delegate;

	public PlayerDecorator(PlayerService delegate) {
		this.delegate = delegate;
	}
	
	@Override
	public FightCharService getChar() {
		
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
	public void init(FightCharService c,String left, String right, String neutral, 
			String upLeft, String UpRight, String up, 
			String downLeft, String downRight, String down, 
			String coupPoing, String coupPied, String coupTete){
		delegate.init(c, left, right, neutral, upLeft, UpRight, up, downLeft, downRight, down, coupPoing, coupPied, coupTete);

	}
	@Override
	public Commande getCommande() {
		return delegate.getCommande();
	}

	@Override
	public void handleKey(String key) {
		delegate.handleKey(key);
	}

	@Override
	public String getKeyUpLeft() {
		// TODO Auto-generated method stub
		return delegate.getKeyUpLeft();
	}

	@Override
	public String getKeyCoupTete() {
		// TODO Auto-generated method stub
		return delegate.getKeyCoupTete();
	}

	@Override
	public String getKeyCoupPied() {
		// TODO Auto-generated method stub
		return delegate.getKeyCoupPied();
	}

	@Override
	public String getKeyCoupPoing() {
		// TODO Auto-generated method stub
		return delegate.getKeyCoupPoing();
	}

	@Override
	public String getKeyDown() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getKeyDownRight() {
		// TODO Auto-generated method stub
		return delegate.getKeyDownRight();
	}

	@Override
	public String getKeyDownLeft() {
		// TODO Auto-generated method stub
		return delegate.getKeyDownLeft();
	}

	@Override
	public String getKeyUp() {
		// TODO Auto-generated method stub
		return delegate.getKeyUp();
	}

	@Override
	public String getKeyUpRight() {
		// TODO Auto-generated method stub
		return delegate.getKeyUpRight();
	}

}
