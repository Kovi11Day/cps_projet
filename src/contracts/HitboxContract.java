package contracts;

import impl.HitboxImpl;
import services.CharacterService;
import services.GlobalVariables;
import services.HitboxService;
import decorators.HitboxDecorator;

public abstract class  HitboxContract extends HitboxDecorator{

	public HitboxContract(HitboxService delegate) {
		super(delegate);
	}
	

	
	//inv :  collidesWith(HitboxService h) == 
		// \exists x,y \in \int {isBelongsTo(x,y) && h.isBelongsTo(x,y)}
	
	//inv :  equalsTo(HitboxService h) == 
			// \forall x,y \in \int {isBelongsTo(x,y) == h.isBelongsTo(x,y)}
	
	abstract void checkInvariant();
	
	@Override
	public void init(double x, double y) {
		// PRE : pas de précondition
		
		// Traitement
		super.init(x, y);
		
		// invariant
		//pas d'invariant
		
		//post: getPostionX() = x
		if(getPositionX()!= x){
			throw new PostconditionError("getPostionX() = x");
		}
		
		//post: getPostionY() =  y
		if(getPositionY()!= y){
			throw new PostconditionError("getPostionY() =  y");
		}

	}
	

	@Override
	public void moveTo(double x, double y) {
		// PRE : pas de précondition
		
		// pre-invariant
		checkInvariant();
		
		// Captures
		double getPositionX_at_pre = getPositionX();
		double getPositionY_at_pre = getPositionY();
		/* capture du centre*/
		boolean belongsTo_centre_at_pre = isBelongsTo(getPositionX(), getPositionY());
		/* capture du centre +100 */
		boolean belongsTo_centre_100_at_pre = isBelongsTo(getPositionX()+100,
				getPositionY()+100);
		/* capture d'un point absolue*/
		boolean belongsTo_abs_at_pre = isBelongsTo(300, 0);
		
		// TRAITEMENT
		super.moveTo(x, y);
		
		// post-invariant
		checkInvariant();
		
		// post: getPositionX() = x
		if(getPositionX() != x){
			throw new PostconditionError("getPositionX() = x");
		}
		// post: getPositionY() = y
		if(getPositionY() != y){
			throw new PostconditionError("getPositionY() = y");
		}
		
		// post: //forall u, v \in \int {
		// isBelongsTo(u,v) == isBelongsTo(u-(x-getPositionX()@pre), v-(y-getPositionY()@pre)) 
		// } 
		
		/*test du centre*/
		if(! isBelongsTo(getPositionX(), getPositionY()) == belongsTo_centre_at_pre){
			throw new PostconditionError("! isBelongsTo(getPositionX(), getPositionY())"
					+ " == belongsTo_centre_at_pre");
		}
		
		/* teste du centre +100*/
		if(! isBelongsTo(getPositionX()+100, getPositionY()+100) == belongsTo_centre_100_at_pre){
			throw new PostconditionError("! isBelongsTo(getPositionX()+100, "
					+ "getPositionY()+100) == belongsTo_centre_100_at_pre");
		}
		
		/* teste d'un point absolue */
		if(! isBelongsTo(300+(x-getPositionX_at_pre),
				0+(y- getPositionY_at_pre)) == belongsTo_abs_at_pre ){
			throw new PostconditionError("! isBelongsTo(300+(x-getPositionX_at_pre),"
				+"0+(y- getPositionY_at_pre)) == belongsTo_abs_at_pre ");
			
		}
		
	}
}
