package chloroplast.color.filter;

import chloroplast.color.LightColor;

public class CutOffFilter implements Filter{

	@Override
	public int shade(int x, int y, double red, double green, double blue, double depth) {
		LightColor color = new LightColor(red, green, blue);
		
		return color.getRGB();
	}

}
