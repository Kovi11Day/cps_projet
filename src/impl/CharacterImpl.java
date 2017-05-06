package impl;

import services.CharacterService;
import services.Commande;
import services.EngineService;
import services.GlobalVariables;
import services.HitboxService;
import services.RectangleHitboxService;

public class CharacterImpl implements CharacterService{
	protected EngineService engine;
	protected RectangleHitboxService charBox;
	protected int life;
	protected int maxLife;
	protected int speed;
	protected boolean rightFace;
	protected boolean ready;

	@Override
	public void init(int l, int s, boolean f) {
		this.life = l;
		this.speed = s;
		this.rightFace = f;
		this.maxLife = l; 
		this.ready = false;
		//TODO: UTILISATION de RectangleHitboxContract
		RectangleHitboxService hb = new RectangleHitboxImpl();//a renommer en hbImplem
		//RectangleHitboxContract hb = new  RectangleHitboxContract(hbImplem);
		hb.init(0, 0);
		this.charBox = hb;
	}

	@Override
	public double getPositionX() {
		return this.charBox.getPositionX();
	}

	@Override
	public double getPositionY() {
		return this.charBox.getPositionY();
	}
	
	public int getHeight() {
		return this.getCharBox().getHeight();
	}

	public int getWidth() {
		return this.getCharBox().getWidth();
	}
	
	@Override
	public EngineService getEngine() {
		
		return this.engine;
	}

	@Override
	public RectangleHitboxService getCharBox() {
		
		return this.charBox;
	}

	@Override
	public int getLife() {
		
		return this.life;
	}

	@Override
	public int getSpeed() {
		
		return this.speed;
	}

	@Override
	public boolean isRightFace() {
		
		return this.rightFace;
	}

	@Override
	public boolean isDead() {
		
		return this.life <= 0;
	}
	
	@Override
	public boolean isReady() {
		return this.ready;
	}
	
	//pas un operateur par rapport a spec, seulement utile pour l'implem
	public void updateLife(int l){
		this.life = l;
	}
	
	//pas un operateur par rapport a spec, seulement utile pour l'implem
	public void moveTo(double x, double y){
		this.getCharBox().moveTo(x, y);
	}

	@Override
	public void moveLeft() {		

		boolean collision = false;
		
		//detecter future collision
		moveTo(getPositionX()-getSpeed(), getPositionY());
		
		for (int i = 0; i < engine.getNbPlayers(); i++){
			if (getEngine().getChar(i) != this && getCharBox().isCollidesWith(getEngine().getChar(i).getCharBox()) ){
				collision = true; 
			}
		}
		//retourner a l'ancien position
		moveTo(getPositionX()+getSpeed(), getPositionY());
		
		if (!collision){
			if (getPositionX() >= getSpeed())
				moveTo(getPositionX()-getSpeed(), getPositionY());
			else
				moveTo(0, getPositionY());
		}
	}

	@Override
	public void moveRight() {

		boolean collision = false;
		
		//detecter future collision
		moveTo(getPositionX()+getSpeed(), getPositionY());
		
		for (int i = 0; i < engine.getNbPlayers(); i++){
			if (getEngine().getChar(i) != this && getCharBox().isCollidesWith(getEngine().getChar(i).getCharBox()) ){
				collision = true; 
			}
		}
		
		//retouner a l'ancien position
		moveTo(getPositionX()-getSpeed(), getPositionY());

		if (!collision){
			if (getPositionX() + getSpeed() <= this.engine.getWidth())
				moveTo(getPositionX()+getSpeed(), getPositionY());
			else
				moveTo(this.engine.getWidth(),getPositionY());
		}
		
	}

	@Override
	public void switchSide() {
		this.rightFace = !this.isRightFace();
	}

	@Override
	public void step(Commande c) {
		switch(c){
		case LEFT: this.moveLeft(); break;
		case RIGHT: this.moveRight(); break;
		}
		
	}

	@Override
	public void setEngine(EngineService engine) {
		//TODO: add life bar of new character
		this.engine = engine;
		this.ready = true;
		
		if (this.rightFace){
			this.charBox.moveTo(engine.getWidth()/2-engine.getSpace()/2, 0 );
		}
		else{
			this.charBox.moveTo(engine.getWidth()/2+engine.getSpace()/2, 0);
		}
		 
	}



}
