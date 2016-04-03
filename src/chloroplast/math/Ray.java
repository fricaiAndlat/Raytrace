package chloroplast.math;

public class Ray {
	
	public final Object source;
	public final Vec fix;
	public final Vec direction;
	public final double maxLength;
	
	public Ray(Object source, Vec fix, Vec direction, double maxLength){
		
		this.source 	= source;
		this.fix 		= fix;
		this.direction 	= direction;
		this.maxLength 	= maxLength;
		
	}
	
	public Vec pointAt(double scalar){
		return new Vec(fix.x + scalar*direction.x, 
					   fix.y + scalar*direction.y,
					   fix.z + scalar*direction.z);
	}

}
