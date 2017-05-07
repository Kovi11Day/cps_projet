package implbug;

import java.util.ArrayList;

import services.Commande;
import services.EngineService;
import services.FightCharService;
import services.HitboxService;
import services.RectangleHitboxService;
import services.Tech;

public class FightCharImpl  extends CharacterImpl implements FightCharService{
	
	private boolean isBlocking;
	private Tech tech;
	private boolean techHasAlreadyHit;
	private int techFrameCounter;
	private int bstunFrameCounter;
	private int hstunFrameCounter;
	private FightCharService otherFightChar;
	private RectangleHitboxService techBox;
	private ArrayList<Tech> techMastered;

	public 	FightCharImpl(){
		super();
		//TODO: brancher contrant RectangleHitbox ici
		techBox = new RectangleHitboxImpl();
		//techBox.init(0, 0, 0, 0); 
		techBox.init(0, 0); 
		
	}

	@Override
	public void init(int l, int s, boolean f) {
		super.init(l, s, f);
		isBlocking = false;
		techHasAlreadyHit = false;
		techFrameCounter = 0;
		bstunFrameCounter = 0;
		hstunFrameCounter = 0;
		techMastered = new ArrayList<Tech>();
	}
	@Override
	public boolean isBlocking() {
		return isBlocking;
	}

	@Override
	public boolean isBlockStunned() {
		
		return bstunFrameCounter > 0;
	}

	@Override
	public boolean isHitStunned() {
		
		return hstunFrameCounter > 0;
	}

	@Override
	public boolean isTeching() {
		
		return techFrameCounter > 0;
	}

	@Override
	public Tech getTech() {
		
		return tech;
	}

	@Override
	public boolean isTechHasAlreadyHit() {
		
		return this.techHasAlreadyHit;
	}

	@Override
	public boolean isInHitFrame() {
		if(this.isTeching()	)
			return this.tech.rframe() < this.techFrameCounter 
					&& techFrameCounter <= tech.rframe()+ tech.hframe() ;
		else 
			return false;
	}

	@Override
	public boolean isControllable() {
		
		return !(isTeching()||this.isBlockStunned()||this.isHitStunned());
	}

	@Override
	public int getTechFrameCounter() {
		
		return this.techFrameCounter;
	}

	@Override
	public int getBstunFrameCounter() {
		
		return this.bstunFrameCounter;
	}

	@Override
	public int getHstunFrameCounter() {
		
		return this.hstunFrameCounter;
	}

	@Override
	public FightCharService getOtherFightChar() {
		
		return this.otherFightChar;
	}

	@Override
	public RectangleHitboxService getTechBox() {
		
		return this.techBox;
	}

	@Override
	public int nbTechMastered() {
		
		return this.techMastered.size();
	}

	@Override
	public Tech getTechMastered(int i) {
		return this.techMastered.get(i);
	}
	
	@Override
	public void step(Commande c) {
		if (this.isControllable()){
		switch(c){
		case LEFT: this.moveLeft(); break;
		case RIGHT: this.moveRight(); break;
		case COUP_DE_POING: 
				this.startTech(this.getTechMastered(0)); 
			break;
		case COUP_DE_PIED: 
			this.startTech(this.getTechMastered(1)); 
		break;
		case COUP_DE_TETE: 
			this.startTech(this.getTechMastered(2)); 
		break;
		case BLOCK: 
			this.isBlocking = true;
		break;
		}
		
		}
		
	}
	@Override
	public void startTech(Tech t) {
		this.tech = t;
		this.techHasAlreadyHit = false;
		this.techFrameCounter = t.sframe() + t.hframe() + t.rframe();
		this.techBox = t.hitbox(this.getPositionX(), this.getPositionY(), this.getWidth(), this.getHeight(), this.rightFace);
	}
	@Override
	public void updateVictim() {
		/*System.out.println("FRAME BEGIN");
		System.out.println("update status: otherCharTeching? " +this.otherFightChar.isTeching() );
		System.out.println("update status: isInHitFrame? " +this.otherFightChar.isInHitFrame() );
		System.out.println("update status: isTechHasAlreadyHit? " +!this.otherFightChar.isTechHasAlreadyHit() );
		System.out.println("update status: isCollidesWith? " +this.getCharBox().isCollidesWith(this.otherFightChar.getTechBox()) );
		System.out.println("FRAME END");*/

		if(this.otherFightChar.isTeching() && this.otherFightChar.isInHitFrame() && !this.otherFightChar.isTechHasAlreadyHit()
				&& this.getCharBox().isCollidesWith(this.otherFightChar.getTechBox())){

			if(this.isBlocking){
				this.bstunFrameCounter = Math.max(this.bstunFrameCounter, this.otherFightChar.getTech().bstun());
			}
			if(!this.isBlocking && !this.isTeching()){
				this.hstunFrameCounter = Math.max(this.hstunFrameCounter, this.otherFightChar.getTech().hstun());
				this.life = Math.max(0, this.life - this.otherFightChar.getTech().damage());

			}
			if(!this.isBlocking && this.isTeching()){
				this.hstunFrameCounter = Math.max(this.hstunFrameCounter, this.otherFightChar.getTech().hstun());
				this.techFrameCounter = 0;
			}
		}
	}
	@Override
	public void updateAttacker() {	
		if(this.isTeching() && this.isInHitFrame() && !this.isTechHasAlreadyHit() 
				&& this.techBox.isCollidesWith(this.otherFightChar.getTechBox())){
			this.techHasAlreadyHit = true;
			
		}
	}


	@Override
	public void updateFrames() {
		this.techFrameCounter = Math.max(0,this.techFrameCounter - 1);
		this.bstunFrameCounter = Math.max(0,this.bstunFrameCounter - 1);
		this.hstunFrameCounter = Math.max(0,this.hstunFrameCounter - 1);
	}

	@Override
	public void setOtherFightChar(FightCharService c) {
		this.otherFightChar = c;
	}

	@Override
	public void setNewTechMastered(Tech t) {
		this.techMastered.add(t);
	}


}
