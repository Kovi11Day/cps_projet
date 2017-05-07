package contracts;

import services.Commande;
import services.EngineService;
import services.FightCharService;
import services.RectangleHitboxService;
import services.Tech;
import decorators.FightCharDecorator;

public class FightCharContract extends CharacterContract implements FightCharService{
	public FightCharContract(FightCharService delegate) {
		super(delegate);
	}
	
	public void checkInvariant() {
		
		super.checkInvariant();
		
		/*inv : isTeching() ==> (!isBlocking() 
		 * 		&& !isBlockStunned() && !isHitStunned() )
		 */		
		if (isTeching()) 
			if (isBlocking() || isBlockStunned() || isHitStunned())
				throw new InvariantError("isTeching() ==> isBlocking() || isBlockStunned() || isHitStunned()");
				
		//inv: !(isBlockStunned() && isHitStunned() )
		if (isBlockStunned() && isHitStunned())
			throw new InvariantError("isBlockStunned() && isHitStunned()");
		
		//inv: isBlockStunned() ==> isBlocking()
		if (isBlockStunned() && !isBlocking())
			throw new InvariantError("isBlockStunned() ==> isBlocking()");
		
		//inv: isControllable() = !(isTeching() || isBlockStunned() || isHitStunned())
		if (isControllable() == (isTeching() || isBlockStunned() || isHitStunned()))
			throw new InvariantError("isControllable() == (isTeching() || isBlockStunned() || isHitStunned()");
		
		//inv:getTechFrameCounter() >=0 && getBStunFrameCounter() >=0 &&  getHStunFrameCounter()>=0
		if (getTechFrameCounter() <0 || getBstunFrameCounter() <0 ||  getHstunFrameCounter() < 0)
			throw new InvariantError("getTechFrameCounter() >=0 && getBStunFrameCounter() >=0 &&  getHStunFrameCounter()>=0");
		
		//inv:!isTeching() ==> !isInHitFrame()
		if (!isTeching() && isInHitFrame())
			throw new InvariantError("!isTeching() ==> !isInHitFrame()");
		
		//inv:isTeching() == getTechFrameCounter()>0
		if (isTeching() != getTechFrameCounter()>0)
			throw new InvariantError("isTeching() == getTechFrameCounter()>0");
		
		//inv: isBlockStunned() == getBStunFrameCounter()>0
		if (isBlockStunned() != getBstunFrameCounter()>0)
			throw new InvariantError("isBlockStunned() == getBStunFrameCounter()>0");
		
		//inv:isHitStunned() == getHStunFrameCounter() >0
		if (isHitStunned() != getHstunFrameCounter() >0)
			throw new InvariantError("isHitStunned() == getHstunFrameCounter() >0");
		
		//inv:isTeching() && isInHitFrame() ==> (
		// (getTech().getRFrame() < getTechFrameCounter()) &&
		// ( getTechFrameCounter() >= (getTech().getRFrame() + getTech().getHFrame()) )
		// )
		if (isTeching() && isInHitFrame()){
			if (!(getTech().rframe() < getTechFrameCounter() && getTechFrameCounter() <= getTech().rframe()  +getTech().hframe())){
				throw new InvariantError("isTeching() && isInHitFrame() ==> IN APPROPRIATE RANGE");
			}
		}
	}
		
		//initializers
	
		@Override
		public double getPositionX() {
			
			return super.getPositionX() ;
		}

		@Override
		public double getPositionY() {
			
			return super.getPositionY();
		}

		@Override
		public EngineService getEngine() {
			
			return super.getEngine();
		}

		@Override
		public RectangleHitboxService getCharBox() {
			
			return super.getCharBox();
		}

		@Override
		public int getLife() {
			
			return super.getLife();
		}

		@Override
		public int getSpeed() {
			
			return super.getSpeed();
		}

		@Override
		public boolean isRightFace() {
			
			return super.isRightFace();
		}

		@Override
		public boolean isDead() {
			
			return super.isDead();
		}

		@Override
		public void init(int l, int s, boolean f) {
			//inv@pre
			//verifier par super 
			
			//pre:verifier par super 
			
			super.init(l, s, f);
			
			//inv@post
			//verifier par super 

			//post: getTechFrameCounter() = 0
			if (getTechFrameCounter() != 0)
				throw new PostconditionError("getTechFrameCounter() != 0");
			
			//post: getBStunFrameCounter() =0
			if (getBstunFrameCounter() != 0)
				throw new PostconditionError("getBStunFrameCounter() != 0");
			
			//post: getHStunFrameCounter() =0
			if (getHstunFrameCounter() != 0)
				throw new PostconditionError("getHStunFrameCounter() != 0");
			
			//post: isTechHasAlreadyHit() = false
			if (isTechHasAlreadyHit() != false)
				throw new PostconditionError("isTechHasAlreadyHit() != false");
			
			//post: isBlocking() = false
			if (isBlocking() != false)
				throw new PostconditionError("isBlocking() != false");
			
			//post: getNbTechMastered = 0
			if (nbTechMastered() != 0)
				throw new PostconditionError("getNbTechMastered() != 0");
			
		}

		@Override
		public void moveLeft() {
			super.moveLeft();
			
		}

		@Override
		public void moveRight() {
			
			super.moveRight();
		}

		@Override
		public void switchSide() {
			super.switchSide();
			
		}

		@Override
		public void setEngine(EngineService engine) {
			super.setEngine(engine);
			
		}


		@Override
		public int getWidth() {
			
			return super.getWidth();
		}

		@Override
		public int getHeight() {
			
			return super.getHeight();
		}

		@Override

		public boolean isBlocking() {
			
			return super.isBlocking();
		}

		@Override
		public boolean isBlockStunned() {
			
			return super.isBlockStunned();
		}

		@Override
		public boolean isHitStunned() {
			
			return super.isHitStunned();
		}

		@Override
		public boolean isTeching() {
			
			return super.isTeching();
		}

		@Override
		public Tech getTech() {
			
			return super.getTech();
		}

		@Override
		public boolean isTechHasAlreadyHit() {
			
			return super.isTechHasAlreadyHit();
		}

		@Override
		public boolean isInHitFrame() {
			
			return super.isInHitFrame();
		}

		@Override
		public boolean isControllable() {
			
			return super.isControllable();
		}

		@Override
		public int getTechFrameCounter() {
			
			return super.getTechFrameCounter();
		}

		@Override
		public int getBstunFrameCounter() {
			
			return super.getBstunFrameCounter();
		}

		@Override
		public int getHstunFrameCounter() {
			
			return super.getHstunFrameCounter();
		}

		@Override
		public FightCharService getOtherFightChar() {
			
			return super.getOtherFightChar();
		}

		@Override
		public RectangleHitboxService getTechBox() {
			
			return super.getTechBox();
		}

		@Override
		public int nbTechMastered() {
			
			return super.nbTechMastered();
		}

		@Override
		public Tech getTechMastered(int i) {
			
			return super.getTechMastered(i);
		}

		

		@Override
		public void startTech(Tech t) {
			
			//inv@pre
			checkInvariant();
			
			//pre:\exist i \in [1,nbTechMastered()()], getTechMastered(i) = t
			boolean exists = false;
			for(int i = 0 ; i<nbTechMastered(); i++ ){
				if (getTechMastered(i) == t)
					exists = true;
			}
			if (!exists)
				throw new PreconditionError("tech not mastered");
			
			//captures
			double getPositionX_atPre = getPositionX();
			double getPositionY_atPre = getPositionY();
			int getWidth_atPre = this.getWidth();
			int getHeight_atPre = this.getHeight();
			boolean isRightFace_atPre = this.isRightFace();
			FightCharService getOtherFightChar_atPre = this.getOtherFightChar();
			int nbTechMastered_atPre = this.nbTechMastered();
			
			//run
			super.startTech(t);
			
			//inv@post
			checkInvariant();
			
			//post:getTechBox().equals(t.getHitBox(getPositionX()@pre,getPositionY()@pre)
			if (! this.getTechBox().equals(t.hitbox(getPositionX_atPre,getPositionY_atPre,
					getWidth_atPre, getHeight_atPre, isRightFace_atPre)))
				throw new PostconditionError("");
				
			//post:getTech() = t
			if (getTech() != t)
				throw new PostconditionError("");

			//post:isTechhasAlreadyHit() = false
			if (isTechHasAlreadyHit() != false)
				throw new PostconditionError("");

			//post:getTechFrameCounter() = t.sframe()+t.hframe()+t.rframe()
			if (getTechFrameCounter() != t.sframe()+t.hframe()+t.rframe())
				throw new PostconditionError("");

			//post:getOtherFightChar() = getOtherFightChar()@pre
			if (getOtherFightChar() != getOtherFightChar_atPre)
				throw new PostconditionError("");

			//post:getNbTechMastered() = getNbTechMastered()@pre
			if (nbTechMastered() != nbTechMastered_atPre)
				throw new PostconditionError("");

			//post: \forall i \in [1,getNbTechMastered()], getTechMastered(i)= getTechMastered(i)@pre
			//omis car trop de stockage de valeures de l'etat @pre et n'est pas critique pour ce cas
		}

		@Override
		public void step(Commande c) {
			//inv@pre
			checkInvariant();
			
			//pre: meme que super
			
			//captures
			boolean isControllable_atPre = this.isControllable();
			boolean isBlocking_atPre = this.isBlocking();
			int getTechFrameCounter_atPre = getTechFrameCounter();
			int getBstunFrameCounter_atPre = getBstunFrameCounter();
			int getHstunFrameCounter_atPre = getHstunFrameCounter();
			
			super.step(c);
			
			//inv@post
			checkInvariant();
			
			//post: !isControllable()@pre ==> this@pre = this
			if (!isControllable_atPre){
				if ( isBlocking_atPre != this.isBlocking() ||
			 getTechFrameCounter_atPre != getTechFrameCounter() ||
			 getBstunFrameCounter_atPre != getBstunFrameCounter() ||
			 getHstunFrameCounter_atPre != getHstunFrameCounter())
					throw new PostconditionError("");
			}

		}

		@Override
		public void updateVictim() {
			//inv@pre
			checkInvariant();

			//pre: getOtherFightChar() != null
			if (getOtherFightChar() == null)
				throw new PreconditionError("");
			//captures
			boolean getOtherFightChar_isTeching_atPre = getOtherFightChar().isTeching();
			boolean getOtherFightChar_isInHitFrame_atPre = getOtherFightChar().isInHitFrame();
			boolean getOtherFightChar_isHasTechAlreadyHit_atPre = getOtherFightChar().isTechHasAlreadyHit(); 
			boolean collision_atPre = false;
			if (getOtherFightChar_isInHitFrame_atPre)
				collision_atPre = getOtherFightChar().getTechBox().isCollidesWith(getCharBox());
			boolean isBlocking_atPre = isBlocking();
			boolean isTeching_atPre = isTeching();
			int getBstunFrameCounter_atPre = getBstunFrameCounter();
			int getHstunFrameCounter_atPre = getHstunFrameCounter();
			int getLife_atPre = getLife();
			super.updateVictim();
			
			//inv@post
			checkInvariant();
			
			/*
			 * post: (getOtherFightChar().isTeching()@pre && getOtherFightChar().isInHitFrame()@pre
			 * 				&& !getOtherFightChar().isTechHasAlreadyHit()@pre && getOtherFightChar()@pre.getTechBox().collidesWith(getCharBox()@pre))
			 * 		==>
			 * 		(
			 * 			(
			 * 				isBlocking()@pre ==> 
			 * 					getBstunFrameCounter() == max(getBstunFrameCounter()@pre, getOtherFightChar().tech().bstun()
			 * 					&& getLife() == getLife()@pre
			 * 					&& isBlocking() == isBlocking()@pre	
			 * 			)
			 * 			&&
			 * 			(
			 * 				!isBlocking()@pre && !isTeching()@pre ==>
			 * 					getHstunFrameCounter() == max(getHstunFrameCounter()@pre, getOtherFightChar().tech().hstun())
			 * 					&& getLife() == getLife()@pre - getOtherFightChar().tech().damage()
			 * 					&& isBlocking() == isBlocking()@pre
			 * 			)
			 * 			&&
			 * 			(
			 * 				!isBlocking()@pre && isTeching()@pre ==>
			 * 					getHstunFrameCounter() == max(getHstunFrameCounter()@pre, getOtherFightChar().tech().hstun())
			 * 					&& getTechFrameCounter() == 0
			 * 					&& getLife() == getLife()@pre
			 * 					&& isBlocking() == isBlocking()@pre
			 * 			)
			 * 		)
			 */
			if (getOtherFightChar_isTeching_atPre && getOtherFightChar_isInHitFrame_atPre 
						&& !getOtherFightChar_isHasTechAlreadyHit_atPre &&collision_atPre){
				if (isBlocking_atPre){
					if (getBstunFrameCounter() != Math.max(getBstunFrameCounter_atPre, getOtherFightChar().getTech().bstun())
							|| isBlocking()!=isBlocking_atPre
							|| getLife() != getLife_atPre)
								
							throw new PostconditionError("");
				}
				
				if (!isBlocking_atPre && !isTeching_atPre){
					if (getHstunFrameCounter() != Math.max(getHstunFrameCounter_atPre, getOtherFightChar().getTech().hstun())
							|| isBlocking()!=isBlocking_atPre
							|| getLife() != getLife_atPre - getOtherFightChar().getTech().damage())
							throw new PostconditionError("");
				}
				
				
				if (!isBlocking_atPre && isTeching_atPre){
					if (getHstunFrameCounter() != Math.max(getHstunFrameCounter_atPre, getOtherFightChar().getTech().hstun())
							|| isBlocking()!=isBlocking_atPre
							|| getLife() != getLife_atPre
							|| getTechFrameCounter() != 0)
							throw new PostconditionError("");
				}
			}
			
		}
		

		@Override
		public void updateAttacker() {
			//inv@pre
			checkInvariant();
			
			//captures
			boolean isInHitFrame_atPre = isInHitFrame();
			boolean isHasTechAlreadyHit_atPre = isTechHasAlreadyHit(); 
			boolean collision_atPre = false;
			if (isInHitFrame_atPre)
				collision_atPre = getTechBox().isCollidesWith(getOtherFightChar().getCharBox());
			boolean isTeching_atPre = isTeching();
			int getBstunFrameCounter_atPre = getBstunFrameCounter();
			int getHstunFrameCounter_atPre = getHstunFrameCounter();
			int getLife_atPre = getLife();
			
			//pre: getOtherFightChar() != null
			if ( getOtherFightChar() == null)
				throw new PreconditionError("");
			
			super.updateAttacker();
			
			//inv@post
			checkInvariant();
			

			/*
			 * post: (isTeching()@pre && isInHitFrame()@pre &&!isHasAlreadyHit()@pre && 
			 * 				techBox()@pre.collidesWith(getOtherFightCharacter(C).charBox()@pre))
			 * 		==>
			 * 		(
			 * 			isHasAlreadyHit() == true
			 * 			&& isInHitFrame() ==isInHitFrame() @pre
			 * 			&& getLife() ==getLife() @pre
			 * 			&& getHstunFrameCounter() ==getHstunFrameCounter() @pre
			 * 			&& getBstunFrameCounter() ==getBstunFrameCounter() @pre
			 * 		)
			 */
			if (isTeching_atPre && isInHitFrame_atPre 
					&& !isHasTechAlreadyHit_atPre && collision_atPre){
				if (isTechHasAlreadyHit() != true  
						|| isInHitFrame() != isInHitFrame_atPre
						|| getLife() != getLife_atPre
						|| getHstunFrameCounter() != getHstunFrameCounter_atPre
						|| getBstunFrameCounter() != getBstunFrameCounter_atPre){
					System.out.println("debug:" + isTechHasAlreadyHit());
					throw new PostconditionError("");
				}
			}
		}

		@Override
		public void updateFrames() {
			
			//inv@pre
			checkInvariant();
			
			//captures
			int getTechFrameCounter_atPre = getTechFrameCounter();
			int getBstunFrameCounter_atPre = getBstunFrameCounter();
			int getHstunFrameCounter_atPre = getHstunFrameCounter();
			boolean isBlockStunned_atPre = isBlockStunned();
			super.updateFrames();
			
			//inv@post
			checkInvariant();
			
			//post: getTechFrameCounter() = max (0, getTechFrameCounter()@pre - 1)
			if (getTechFrameCounter() != Math.max (0, getTechFrameCounter_atPre - 1))
				throw new PostconditionError("");
			
			// post: getBstunFrameCounter() = max (0, getBstunFrameCounter()@pre - 1)
			if (getBstunFrameCounter() != Math.max (0, getBstunFrameCounter_atPre - 1))
				throw new PostconditionError("");
			
			// post: getHstunFrameCounter() = max (0, getHstunFrameCounter()@pre - 1)
			if (getHstunFrameCounter() != Math.max (0, getHstunFrameCounter_atPre - 1))
				throw new PostconditionError("");
			
			// post: !isBlockStunned()@pre ==> !isBlocking()
			 if (!isBlockStunned_atPre){
				 if (isBlocking())
					throw new PostconditionError("");

			 }
			 
			 //post: !isTeching() ==> !hasTechAlreadyHit()
			 if (!isTeching())
				 if (isTechHasAlreadyHit())
						throw new PostconditionError("");

		}

		@Override
		public void setOtherFightChar(FightCharService c) {
			//inv@pre
			checkInvariant();
			
			super.setOtherFightChar(c);
			
			//inv@post
			checkInvariant();
			
			//post: getOtherFightChar() != null
			if(getOtherFightChar() == null)
				throw new PostconditionError("");
			
		}

		@Override
		public void setNewTechMastered(Tech t) {
			
			//inv@pre
			checkInvariant();
			
			 // pre: t.damage() >= 0 && t.hstun >= 0 && t.bstun >= 0 
					//&& t.sframe() >= 0 && t.hframe() >= 0 && t.rframe() >= 0
			
			if (t.damage() < 0 || t.hstun() < 0 || t.bstun() < 0 
					|| t.sframe() < 0 || t.hframe() < 0 || t.rframe() < 0){
				throw new PreconditionError("");

			}
			
			//captures
			int nbTechMastered_atPre = nbTechMastered();
			
			super.setNewTechMastered(t);
			
			//inv@post
			checkInvariant();
			
			// post: getNbTechMastered() == getNbTechMastered()@pre + 1
			if (nbTechMastered() != nbTechMastered_atPre + 1)
				throw new PostconditionError("");

			//post:  \exists i in [1, getNbTechMastered()] getTechMastered(i) == t
			 	boolean exists = false;
			 	for (int i = 0 ; i< nbTechMastered(); i++)
			 		if (getTechMastered(i) == t)
			 			exists = true;
			 	
			 	if (!exists)
					throw new PostconditionError("");

		}
		
	
	
}
	