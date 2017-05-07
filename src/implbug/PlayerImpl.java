package implbug;

import java.util.ArrayList;

import services.CharacterService;
import services.Commande;
import services.FightCharService;
import services.PlayerService;

public class PlayerImpl implements PlayerService {
	//private CharacterService character;
	private FightCharService character;

	private  String keyLeft = "";
	private  String keyRight= "";
	private  String keyNeutral= "";
	private String upLeft = "";
	private String UpRight = "";
	private String up = "";
	private String downLeft = "";
	private String downRight = "";
	private String down = "";
	private String coupPoing = "";
	private String coupPied = "";
	private String coupTete = "";
	private ArrayList<String> sequenceKeys;
	private boolean pressActivated;
	private String keyPressed;
	
	//PlayerImpl otherPlayer;
	public PlayerImpl(){
		//otherPlayer = null;
		this.sequenceKeys = new ArrayList<>();

		/*this.setOnFXCommEvent(new EventHandler<FXCommEvent>(){
			public void handle(FXCommEvent me){
				handleKey(me);
			}
		});
	}
	
	 public final void setOnFXCommEvent(
		        EventHandler<? super FXCommEvent> value) {
		    this.addEventHandler(FXCommEvent.COMM, value);
		}
	 
	public void handleKey(FXCommEvent event){
		if (event.getKey().equals(keyLeft)
				|| event.getKey().equals(keyNeutral)
				|| event.getKey().equals(keyRight)){
		     this.sequenceKeys.add(event.getKey());
		}
		     
		event.consume();*/
	}
	
	public void handleKey(String key){
		System.out.println(key);
		this.sequenceKeys.add(key);
	}
	
	public Commande getCommande(){
		Commande result = Commande.NEUTRAL;
		synchronized(sequenceKeys){

		try{
			if (sequenceKeys.size() > 0){
			if (sequenceKeys.get(0).equals(keyLeft))
				result = Commande.LEFT;
			else if (sequenceKeys.get(0).equals(keyRight))
				result = Commande.RIGHT;
			else if (sequenceKeys.get(0).equals(this.up))
				result = Commande.UP;
			else if (sequenceKeys.get(0).equals(this.down))
				result = Commande.DOWN;
			else if (sequenceKeys.get(0).equals(this.downLeft))
				result = Commande.DOWNLEFT;
			else if (sequenceKeys.get(0).equals(this.downRight))
				result = Commande.DOWNRIGHT;
			else if (sequenceKeys.get(0).equals(this.UpRight))
				result = Commande.UPRIGHT;
			else if (sequenceKeys.get(0).equals(this.downRight))
				result = Commande.DOWNRIGHT;
			else if (sequenceKeys.get(0).equals(this.coupPied))
				result = Commande.COUP_DE_PIED;
			else if (sequenceKeys.get(0).equals(this.coupPoing))
				result = Commande.COUP_DE_POING;
			else if (sequenceKeys.get(0).equals(this.coupTete))
				result = Commande.COUP_DE_TETE;
			else if (sequenceKeys.get(0).equals(this.keyNeutral))
				result = Commande.BLOCK;
			}
		}catch(NullPointerException e){}
		
		//else treat sequence of keys
		
		for (int i = 0 ; i < sequenceKeys.size(); i++)
			sequenceKeys.remove(i);
		}
		if (result!=Commande.NEUTRAL)
		System.out.println(result);
		return result;
	}

	@Override
	public FightCharService getChar() {
		
		return this.character;
	}
	/*
	public void setOtherPlayer(PlayerImpl p){
		this.otherPlayer = p;
	}
	
	public PlayerImpl getOtherPlayer(){
		return this.otherPlayer;
	}*/
	@Override
	public String getKeyLeft() {
		
		return this.keyLeft;
	}

	@Override
	public String getKeyRight() {
		
		return this.keyRight;
	}

	@Override
	public String getKeyUpLeft() {
		
		return this.upLeft;
	}

	public String getKeyUpRight() {
		
		return this.UpRight;
	}
	public String getKeyUp() {
		
		return this.up;
	}
	public String getKeyDownLeft() {
		
		return this.downLeft;
	}
	public String getKeyDownRight() {
		
		return this.downRight;
	}
	@Override
	public String getKeyDown() {
		
		return this.down;
	}
	@Override
	public String getKeyCoupPoing() {
		
		return this.coupPoing;
	}
	@Override
	public String getKeyCoupPied() {
		
		return this.coupPied;
	}
	@Override
	public String getKeyCoupTete() {
		
		return this.coupTete;
	}
	@Override
	public String getKeyNeutral() {
		
		return this.keyNeutral;
	}

	@Override
	public void init(FightCharService c, String left, String right,
			String neutral, String upLeft, String UpRight, String up,
			String downLeft, String downRight, String down, String coupPoing,
			String coupPied, String coupTete) {
		this.keyLeft = left;
		this.keyRight = right;
		this.keyNeutral = neutral;
		this.upLeft = upLeft;
		this.UpRight = UpRight;
		this.up = up;
		this.downLeft = downLeft;
		this.downRight = downRight;
		this.down = down;
		this.coupPoing = coupPoing;
		this.coupPied = coupPied;
		this.coupTete = coupTete;

		this.character = c;		
	}



	
}
