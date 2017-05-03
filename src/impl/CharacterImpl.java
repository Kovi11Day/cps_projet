package impl;

import services.CharacterService;
import services.Commande;
import services.EngineService;
import services.GlobalVariables;
import services.HitboxService;
import services.RectangleHitboxService;

public class CharacterImpl implements CharacterService{
	//private int positionX;
	//private int positionY;
	protected EngineService engine;
	//protected HitboxService charBox;
	protected RectangleHitboxService charBox;

	protected int life = 0;
	protected int maxLife = 0;//
	protected int speed;
	protected boolean rightFace;
	protected boolean dead;
//	private ProgressBar pb; //
	public CharacterImpl(/*int l, int s, boolean f, /*EngineService e*/) {
		//this.maxLife = l;
	//	this.pb = new ProgressBar();
		//pb.setMaxSize(100, 40);
		//this.pb.setProgress(((float)1));
	//	this.getChildren().add(pb);
		//init(l,s,f);
	}

	@Override
	public void init(int l, int s, boolean f/*, EngineService e*/) {
		this.life = l;
		this.speed = s;
		this.rightFace = f;
		this.maxLife = l; //????
		
		//***UTILISATION DE HITBOX CONTRACT***//
		RectangleHitboxService hb = new RectangleHitboxImpl();
		//hb.init(0, 0, 20, 20);
		hb.init(0, 0);
		//this.charBox = new HitboxContract(hb);
		this.charBox = hb;
	}
	
	@Override
	public double getPositionX() {
		return this.charBox.getPositionX();
	}
	/*ProgressBar getProgressBar(){ //---//
		return this.pb;
	}*/
	@Override
	public double getPositionY() {
		//return this.charBox.getPositionY() - this.getEngine().getHeight();
		return this.charBox.getPositionY();
	}
	//@Override
	public int getHeight() {
		
		return this.getCharBox().getHeight();
	}

	//@Override
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
		
		return this.dead;
	}
	
	public void updateLife(int l){
		this.life = l;
		//this.pb.setProgress(((float)l/(float)life));

	}
	public void moveTo(double d, double e){
		//this.getCharBox().moveTo(x, y + this.getEngine().getHeight());
		this.getCharBox().moveTo(d, e );

	}

	@Override
	public void moveLeft() {		
		System.out.println(this.rightFace + ": moved left");

		boolean collision = false;
		for (int i = 0; i < GlobalVariables.nbPlayersMax; i++){
			System.out.println("CHECK INSTANCE: "+ getEngine().getChar(i) + " and this: " + this);

			if (getEngine().getChar(i) != this && getCharBox().isCollidesWith(getEngine().getChar(i).getCharBox()) ){
				collision = true; 
			}
		}
		if (!collision){
			if (getPositionX() >= getSpeed())
				/*this.charBox.*/moveTo(getPositionX()-getSpeed(), getPositionY());
				//this.positionX -= getSpeed();
			else
				/*this.charBox.*/moveTo(0, getPositionY());
				//this.positionX = 0;
		}
	}

	@Override
	public void moveRight() {
		System.out.println(this.rightFace + ": moved right");

		boolean collision = false;
		for (int i = 0; i < GlobalVariables.nbPlayersMax; i++){
			if (getEngine().getChar(i) != this && getCharBox().isCollidesWith(getEngine().getChar(i).getCharBox()) ){
				collision = true; 
			}
		}
		if (!collision){
			if (getPositionX() + getSpeed() <= this.engine.getWidth())
				/*this.charBox.*/moveTo(getPositionX()+getSpeed(), getPositionY());
				//this.positionX += getSpeed();
			else
				//this.positionX = GlobalVariables.width;
				/*this.charBox.*/moveTo(this.engine.getWidth(),getPositionY());
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
	public CharacterService clone() {
		//CharacterService clone = (CharacterService) super.clone();
		
		CharacterService clone = new CharacterImpl();
		clone.init(life, speed,rightFace/*, engine*/);
		//clone.charBox = charBox.clone();
		//clone.dead =dead;
		//clone.positionX = positionX;
		//clone.positionY = positionY;
		return clone;
	}

	@Override
	public boolean equals(CharacterService c) {
		if (this.dead != c.isDead() || this.rightFace != c.isRightFace() || !this.charBox.equals(c.getCharBox())
				|| this.engine!=c.getEngine() || this.life!=c.getLife()/* ||this.positionX!=c.getPositionX() ||this.positionY!=c.getPositionY()*/
				||this.speed != c.getSpeed())
			return false;
		return true;
	}
	
	@Override
	//TODO:initial positions of rectangles
	public void setEngine(EngineService engine) {
		this.engine = engine;System.out.println("carbox before:set engine"  +this.charBox.getPositionX() + ","  +this.charBox.getPositionY());
		if (this.rightFace){
			this.charBox.moveTo(engine.getWidth()/2-engine.getSpace()/2, 0 );
			
		}
		else{
			this.charBox.moveTo(engine.getWidth()/2+engine.getSpace()/2, 0);
		  	//pb.setLayoutX(engine.getWidth() - pb.getMaxWidth());

		}
		//---p1.getChar().getCharBox().moveTo(w/2-s/2, 0);
		//---p2.getChar().getCharBox().moveTo(w/2+s/2, 0);
	this.engine = engine;System.out.println("carbox after engine"  +this.charBox.getPositionX() + ","  +this.charBox.getPositionY());

	}

}
