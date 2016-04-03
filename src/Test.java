import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import chloroplast.color.LightColor;
import chloroplast.color.filter.CutOffFilter;
import chloroplast.color.fullImage.FileFullImage;
import chloroplast.color.fullImage.FullImage;
import chloroplast.color.fullImage.MemoryFullImage;
import chloroplast.math.Vec;
import chloroplast.raytrace.camera.ScreenCamera;
import chloroplast.raytrace.displayable.ColoredPlain;
import chloroplast.raytrace.displayable.ColoredSphere;
import chloroplast.raytrace.displayable.lightSource.LightSphere;
import chloroplast.scene.Scene;

public class Test {

	public static void main(String[] args) throws Exception{
		ScreenCamera cam = new ScreenCamera(1920, 1080, Math.PI/4);
		
		Scene scene = new Scene();
		
		scene.addDisplayable(new ColoredPlain(new Vec(0, 4, 0),
					new Vec(2, 0, 0),
					new Vec(0,0,-2),
					new LightColor(0.5, 0.5, 0.5), scene));
		
		scene.addDisplayable(new ColoredPlain(new Vec(0, -4, 0),
				   new Vec(1, 0, 0),
				   new Vec(0, 0, 1),
				   new LightColor(1, 0.5, 0), scene));
		
		scene.addDisplayable(new ColoredPlain(new Vec(-4, 0, 0),
				   new Vec(0, 1, 0),
				   new Vec(0, 0, 1),
				   new LightColor(0.5, 0.25, 0), scene));
		
		scene.addDisplayable(new ColoredPlain(new Vec(4, 0, 0),
				   new Vec(0, 1, 0),
				   new Vec(0, 0, 1),
				   new LightColor(0.5, 0.25, 0), scene));
		
		scene.addDisplayable(new ColoredPlain(new Vec(0, 0, -25),
				   new Vec(1, 0, -0.1),
				   new Vec(0, 1, 0),
				   new LightColor(1, 1, 1), scene));
		
		scene.addDisplayable(new ColoredPlain(new Vec(0, 0, 4),
				   new Vec(1, 0, 0),
				   new Vec(0, 1, 0),
				   new LightColor(1, 1, 1), scene));
		
		scene.addDisplayable(new ColoredSphere(new Vec(-0.2, -1.4, -20), 1, new LightColor(0.01, 1, 0.01), scene));
		scene.addDisplayable(new ColoredSphere(new Vec(0, 1, -20.1), 1.1, new LightColor(1, 0.01, 0.01), scene));
		scene.addDisplayable(new ColoredSphere(new Vec(1, 0.5, -19), 0.5, new LightColor(1, 1, 0.01), scene));
		scene.addDisplayable(new ColoredSphere(new Vec(4, 4, -20), 3.5, new LightColor(0.01, 0.01, 1), scene));
		
		scene.addDisplayable(new LightSphere(new Vec(0, 3.9, -20), 0.5, new LightColor(1, 1, 1), 10, scene));
		scene.addDisplayable(new LightSphere(new Vec(-3.8, 0, -19), 0.5, new LightColor(1, 0, 1), 10, scene));
		
		FullImage result = cam.renderScene(scene);
		
		new File("out").mkdirs();
		ImageIO.write(result.finalRender(new CutOffFilter()), "png", new File("out/output"+System.nanoTime()+".png"));
		
	}
}
