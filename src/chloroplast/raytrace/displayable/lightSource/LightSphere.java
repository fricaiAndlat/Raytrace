package chloroplast.raytrace.displayable.lightSource;

import java.util.ArrayList;
import java.util.Random;

import chloroplast.color.LightColor;
import chloroplast.math.IntersectionPoint;
import chloroplast.math.Vec;
import chloroplast.raytrace.displayable.ColoredSphere;
import chloroplast.scene.Scene;

public class LightSphere extends ColoredSphere implements LightSource{

	Random rand;
	int quality;
	
	public LightSphere(Vec center, double radius, LightColor color, Random rand, int quality, Scene scene){
		super(center, radius, color, scene);
		this.rand = rand;
		this.quality = quality;
	}
	
	public LightSphere(Vec center, double radius, LightColor color, int quality, Scene scene) {
		this(center, radius, color, new Random(0), quality, scene);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Vec> getPositions() {
		ArrayList<Vec> positionList = new ArrayList<Vec>();
		
		for(int i = 0; i < quality; ++i){
			positionList.add(this.center.add(
					Math.sin(rand.nextDouble())*this.radius,
					Math.sin(rand.nextDouble())*this.radius,
					Math.sin(rand.nextDouble())*this.radius));
		}
		
		return positionList;
	}
	
	@Override
	public LightColor shade(IntersectionPoint intersect){
		return this.color;
	}

}
