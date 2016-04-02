package chloroplast.color.fullImage;

import java.awt.image.BufferedImage;

import chloroplast.color.Filter;
import chloroplast.color.LightColor;

public interface FullImage {
	
	public LightColor getColor(int x, int y);
	public void       setColor(int x, int y, LightColor color);
	
	public double getDepth(int x, int y);
	public void   setDepth(int x, int y, double d);
	
	public Stats createStats();
	
	public BufferedImage finalRender(Filter filter);
	
	public void remove();
	
	public class Stats{
		public final double nearest;
		public final double widest;
		public final LightColor brightest;
		
		public Stats(double nearest, double widest, LightColor brightest){
			this.nearest = nearest;
			this.widest = widest;
			this.brightest = brightest;
		}
	}
	

}
