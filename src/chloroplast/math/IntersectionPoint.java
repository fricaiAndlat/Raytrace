package chloroplast.math;

import chloroplast.raytrace.displayable.Displayable;

public class IntersectionPoint {
	
	public final Vec point;
	public final double length;
	public final Displayable object;
	public final Ray ray;
	
	public IntersectionPoint(Vec point, double length, Displayable object, Ray ray){
		this.point = point;
		this.length = length;
		this.object = object;
		this.ray = ray;
	}
	
}
