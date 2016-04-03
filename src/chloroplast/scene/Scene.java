package chloroplast.scene;

import java.util.ArrayList;

import chloroplast.color.LightColor;
import chloroplast.math.IntersectionPoint;
import chloroplast.math.Ray;
import chloroplast.math.Vec;
import chloroplast.raytrace.displayable.Displayable;
import chloroplast.raytrace.displayable.lightSource.LightSource;

public class Scene {
	
	public static double EPSILON = 1e-5;
	
	LightColor backgroundColor;
	
	ArrayList<Displayable> displayables = new ArrayList<Displayable>();
	ArrayList<Vec> lights = new ArrayList<Vec>();
	
	Settings settings;
	
	public Scene(){ 
		backgroundColor = new LightColor(0.0352941176470588, 0, 0.4588235294117647);
	}
	
	public LightColor getBackgroundColor(){
		return backgroundColor;
	}
	
	public IntersectionPoint intersect(Ray ray){
		IntersectionPoint nearest = null;
		
		for(Displayable disp: displayables){
			IntersectionPoint current = disp.intersect(ray);
			if(current!= null && (nearest == null || (current.length >= EPSILON && current.length < nearest.length))){
				nearest = current;
			}
		}
		
		return nearest;
		
	}
	public LightColor shade(IntersectionPoint point){
		return point.object.shade(point);
	}
	
	public void addDisplayable(Displayable disp){
		displayables.add(disp);
		
		if(disp instanceof LightSource){
			LightSource source = (LightSource)disp;
			lights.addAll(source.getPositions());
		}
	}
	
	public LightColor getLightFromLightSources(IntersectionPoint intersection){
		double r = 0.1;
		double g = 0.1;
		double b = 0.1;
		
		for(Vec light: lights){
			Ray shadowRay = new Ray(intersection.object, intersection.point, intersection.point.getDirection(light), -1);
			IntersectionPoint intersect = this.intersect(shadowRay);
			if(intersect != null){
				if(intersect.object instanceof LightSource){
					LightColor color = shade(intersect);
					double factor = intersection.normal.scalarProduct(shadowRay.direction);
					r += color.r*factor;///intersect.length;
					g += color.g*factor;///intersect.length;
					b += color.b*factor;///intersect.length;
				}
			}
		}
		
		return new LightColor(r, g, b);
	}

}
