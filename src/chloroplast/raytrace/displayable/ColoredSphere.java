package chloroplast.raytrace.displayable;

import chloroplast.color.LightColor;
import chloroplast.math.IntersectionPoint;
import chloroplast.math.Ray;
import chloroplast.math.Vec;
import chloroplast.scene.Scene;

public class ColoredSphere implements Displayable{

	protected Vec center;
	protected double radius;
	protected double radius2;
	protected LightColor color;
	
	Scene scene;
	
	public ColoredSphere(Vec center, double radius, LightColor color, Scene scene){
		this.center = center;
		this.radius2 = radius*radius;
		this.color = color;
		this.radius = radius;
		this.scene = scene;
	}
	
	@Override
	public IntersectionPoint intersect(Ray ray) {
		double a = -ray.fix.substract(center).scalarProduct(ray.direction);
		
		Vec nearest = ray.pointAt(a);
		
		double b = Math.sqrt(radius2 - nearest.substract(center).length2());
		
		double intersection1 = a-b;
		
		if(ray.source != this && intersection1 > 0 && (ray.maxLength == -1 || intersection1 < ray.maxLength)){
			Vec inter = ray.pointAt(intersection1);
			return new IntersectionPoint(inter, intersection1, this, ray, center.getDirection(inter));
		}
		
		double intersection2 = a+b;
		
		
		if(intersection2 > 0 && (ray.maxLength == -1 || intersection2 < ray.maxLength)){
			Vec inter = ray.pointAt(intersection2);
			return new IntersectionPoint(inter, intersection2, this, ray, center.getDirection(inter));
		}
		
		return null;
	}

	@Override
	public LightColor shade(IntersectionPoint point) {
		return scene.getLightFromLightSources(point).multiply(color);
	}

}
