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

}
