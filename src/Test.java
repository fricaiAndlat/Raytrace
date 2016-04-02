import java.io.IOException;

import chloroplast.color.fullImage.FileFullImage;

public class Test {

	public static void main(String[] args) throws Exception{
		FileFullImage image = new FileFullImage(1920*4, 1080*4);
		image.remove();
	}
}
