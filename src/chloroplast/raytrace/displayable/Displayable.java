package chloroplast.raytrace.displayable;

import chloroplast.color.LightColor;
import chloroplast.math.IntersectionPoint;
import chloroplast.math.Ray;

public interface Displayable {
	
	public IntersectionPoint intersect(Ray ray);
	
	public LightColor shade(IntersectionPoint point);
	

}
