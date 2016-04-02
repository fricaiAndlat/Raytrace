package chloroplast.raytrace.camera;

import chloroplast.color.fullImage.FullImage;
import chloroplast.scene.Scene;

public interface Camera {
	
	public FullImage renderScene(Scene scene);
	
}
