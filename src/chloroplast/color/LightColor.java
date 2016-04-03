package chloroplast.color;

import java.awt.Color;

public class LightColor {
	
	public final double r, g, b;
	
	public LightColor(double r, double g, double b){
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public LightColor(Color c, double factor){
		r = c.getRed()*factor/255.0;
		g = c.getGreen()*factor/255.0;
		b = c.getBlue()*factor/255.0;
	}
	
	public LightColor(Color c){
		this(c, 1);
	}
	
	public int getRGB(){
		int r = (int)(this.r * 255);
		int g = (int)(this.g * 255);
		int b = (int)(this.b * 255);
		
		if(r > 255){ r = 255; }
		if(g > 255){ g = 255; }
		if(b > 255){ b = 255; }
		
		return (r<<16) | (g<<8) | b;
	}
	
	public LightColor add(LightColor... args){
		double sumR = 0;
		double sumG = 0;
		double sumB = 0;
		
		for(LightColor arg: args){
			sumR += arg.r;
			sumG += arg.g;
			sumB += arg.b;
		}
		return new LightColor(sumR, sumG, sumB);
		
	}
	
	public LightColor multiply(LightColor color){
		return new LightColor(r*color.r, g*color.g, b*color.b);
	}

}
