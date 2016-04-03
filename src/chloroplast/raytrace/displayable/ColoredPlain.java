package chloroplast.raytrace.displayable;

import chloroplast.color.LightColor;
import chloroplast.math.IntersectionPoint;
import chloroplast.math.Ray;
import chloroplast.math.Vec;
import chloroplast.scene.Scene;

public class ColoredPlain implements Displayable{

	Vec normal;
	Vec sup1, sup2;
	Vec fix;
	
	LightColor color;
	
	Scene scene;
	
	public ColoredPlain(Vec fix, Vec sup1, Vec sup2, LightColor color, Scene scene){
		this.fix = fix;
		this.sup1 = sup1;
		this.sup2 = sup2;
		
		this.color = color;
		
		this.normal = sup1.crossProduct(sup2);
		
		this.scene = scene;
	}
	
	
	@Override
	public IntersectionPoint intersect(Ray ray) {
		if(ray.source == this){
			return null;
		}
		
		double a = -ray.fix.substract(fix).scalarProduct(normal) / ray.direction.scalarProduct(normal);
		
		if(a < 0){
			return null;
		}
		
		return new IntersectionPoint(ray.pointAt(a), a, this, ray);
	}

	@Override
	public LightColor shade(IntersectionPoint point) {
		return scene.getLightFromLightSources(point).multiply(color);
	}

}
