package chloroplast.raytrace.camera;

import java.io.IOException;

import chloroplast.color.fullImage.FileFullImage;
import chloroplast.color.fullImage.FullImage;
import chloroplast.color.fullImage.MemoryFullImage;
import chloroplast.math.IntersectionPoint;
import chloroplast.math.Ray;
import chloroplast.math.Vec;
import chloroplast.scene.Scene;

public class ScreenCamera implements Camera{
	Vec focusPoint;
	int width, height;
	double realHeight; //realWidth is always 1
	
	public ScreenCamera(int width, int height, double fov){
		this.width = width;
		this.height = height;
		double depth = 0.5 / Math.tan(fov / 2);
		focusPoint = new Vec(0, 0, depth);
		realHeight = height / (double)width;
	}

	@Override
	public FullImage renderScene(Scene scene) {
		FullImage result = null;
		try{
			result = new MemoryFullImage(width, height);
		}catch(OutOfMemoryError e){
			System.out.println("not enough RAM, swithing to buffer image in cache");
			try {
				result = new FileFullImage(width, height);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		double dx = 1.0/width;
		
		for(int j = 0; j < height; ++j){
			for(int i = 0; i < width; ++i){
				Vec pixel = new Vec(-0.5 + i*dx, -realHeight/2 + j*dx, 0);
				IntersectionPoint intersection = scene.intersect(new Ray(null, pixel, focusPoint.getDirection(pixel), -1));
				if(intersection == null){
					result.setColor(i, j, scene.getBackgroundColor());
					result.setDepth(i, j, -1);
				}else{
					result.setColor(i, j, scene.shade(intersection));
					result.setDepth(i, j, intersection.length);					
				}
			}
		}
		
		
		return result;
	}
	

}
