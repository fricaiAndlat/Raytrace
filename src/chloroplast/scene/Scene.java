package chloroplast.scene;

import java.util.ArrayList;

import chloroplast.color.LightColor;
import chloroplast.math.IntersectionPoint;
import chloroplast.math.Ray;
import chloroplast.raytrace.displayable.Displayable;

public class Scene {
	
	LightColor backgroundColor;
	ArrayList<Displayable> displayables = new ArrayList<Displayable>();
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
			if(nearest != null || current.length < nearest.length){
				nearest = current;
			}
		}
		
		return nearest;
		
	}
	public LightColor shade(IntersectionPoint point){
		return point.object.shade(point);
	}

}
