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
import chloroplast.raytrace.camera.ScreenCamera;
import chloroplast.scene.Scene;

public class Test {

	public static void main(String[] args) throws Exception{
		ScreenCamera cam = new ScreenCamera(1920*4, 1080*4, Math.PI/2);
		
		FullImage result = cam.renderScene(new Scene());
		
		ImageIO.write(result.finalRender(new CutOffFilter()), "png", new File("output.png"));
		
	}
}
