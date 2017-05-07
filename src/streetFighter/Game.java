package streetFighter;

import impl.CoupDePoing;
import impl.EngineImpl;
import impl.FightCharImpl;
import impl.Gui;
import impl.PlayerImpl;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
//import services.CharacterService;
import services.EngineService;
import services.FightCharService;
import services.PlayerService;
//import contracts.CharacterContract;
import contracts.EngineContract;
import contracts.FightCharContract;
import contracts.InvariantError;
import contracts.PostconditionError;
import contracts.PreconditionError;
//import contracts.PlayerContract;

public class Game extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		try{

		Gui gui = new Gui();

		//***UTILISATION DE CHARACTER CONTRACT***//
		//CharacterService char1= new CharacterImpl();
		//CharacterService char2= new CharacterImpl();
		FightCharService charImplem1= new FightCharImpl();
		FightCharService charImplem2= new FightCharImpl();
		FightCharService char1= new FightCharContract(charImplem1);
		FightCharService char2= new FightCharContract(charImplem2);

		char1.init(100,30,true);//life, speed, faceRight
		char2.init(100,10,false);//life, speed, faceRight
	
		char1.setNewTechMastered(new CoupDePoing());
		char2.setNewTechMastered(new CoupDePoing());
		char1.setOtherFightChar(char2);
		char2.setOtherFightChar(char1);

		//CharacterService contractChar1 = new CharacterContract(char1);
		//CharacterService contractChar2 = new CharacterContract(char2);

		//***UTILISATION DE PLAYER CONTRACT***//
		PlayerService player1 = new PlayerImpl();
		PlayerService player2 = new PlayerImpl();
		player1.init(char1,"q","s","d","a","z","e","w","x","c","&","é","\"");
		player2.init(char2,"k","l","m","i","o","p",",",";",":","!","ç","à");

		//PlayerService contractPlayer1 = new PlayerContract(player1);
		//PlayerService contractPlayer2 = new PlayerContract(player2);

		//***UTILISATION DE ENGINE CONTRACT***//
		EngineService engine = new EngineImpl(gui);
		EngineService contractEngine = new EngineContract(engine);
		contractEngine.init(450,700,70,player1,player2);//width height space
		//char1.setEngine(engine);
		//char2.setEngine(engine);
		char1.setEngine(contractEngine);
		char2.setEngine(contractEngine);
		gui.init(contractEngine);


		Group root = new Group();

		root.getChildren().add(gui);
		
		Text t = new Text();		 
		  t.requestFocus();
		  t.setFocusTraversable(true);
		  t.setOnKeyTyped(new EventHandler<KeyEvent>()
					{
				 @Override
		            public void handle( KeyEvent keyEvent )
		            {
					 	handleKey(keyEvent, player1, player2);
		            }
					});
		  root.getChildren().add(t);
			//Thread th = new Thread(contractEngine);
			Thread th = new Thread(contractEngine);
			th.start();
		  Scene s = new Scene(root);	

		  stage.setScene(s);
		  stage.show();
		
		}catch(PreconditionError exp){
			System.out.println("PRECONDITION");
				exp.printStackTrace();
			}catch(PostconditionError exp){
				System.out.println("POSTCONDITION");
				exp.printStackTrace();
			}catch(InvariantError exp){
				System.out.println("INVARIANT");
				exp.printStackTrace();
			}
	}
	

	public void handleKey(KeyEvent keyEvent, PlayerService p1, PlayerService p2){
		String key = String.valueOf(keyEvent.getCharacter());
		if (key.equals(p1.getKeyLeft()) || key.equals(p1.getKeyRight()) || key.equals(p1.getKeyNeutral())
				|| key.equals(p1.getKeyCoupPoing()) ||  key.equals(p1.getKeyCoupPied()) ||  key.equals(p1.getKeyCoupTete())
				|| key.equals(p1.getKeyDown()) ||  key.equals(p1.getKeyDownLeft()) ||  key.equals(p1.getKeyDownRight())
				|| key.equals(p1.getKeyUp()) ||  key.equals(p1.getKeyUpLeft()) ||  key.equals(p1.getKeyUpRight()))
					
			p1.handleKey(key);
		else if (key.equals(p2.getKeyLeft()) || key.equals(p2.getKeyRight()) || key.equals(p2.getKeyNeutral())
				|| key.equals(p2.getKeyCoupPoing()) ||  key.equals(p2.getKeyCoupPied()) ||  key.equals(p2.getKeyCoupTete())
				|| key.equals(p2.getKeyDown()) ||  key.equals(p2.getKeyDownLeft()) ||  key.equals(p2.getKeyDownRight())
				|| key.equals(p2.getKeyUp()) ||  key.equals(p2.getKeyUpLeft()) ||  key.equals(p2.getKeyUpRight()))
			p2.handleKey(key);

	
	}
	public static void main (String args[]){
		Application.launch(Game.class,args);
		
	}
	
}
