package chloroplast.color.fullImage;

import java.awt.image.BufferedImage;

import chloroplast.color.LightColor;
import chloroplast.color.filter.Filter;

public class MemoryFullImage implements FullImage{
	
	private final static int R = 0;
	private final static int G = 1;
	private final static int B = 2;
	private final static int D = 3;
	
	private double[][][] data; //[rgbd][x][y]
	private int width, height;
	
	public MemoryFullImage(int width, int height){
		data = new double [4][width][height];
		this.width = width;
		this.height = height;
	}

	@Override
	public LightColor getColor(int x, int y) {
		return new LightColor(data[R][x][y], data[G][x][y], data[B][x][y]);
	}

	@Override
	public void setColor(int x, int y, LightColor color) {
		data[R][x][y] = color.r;
		data[G][x][y] = color.g;
		data[B][x][y] = color.b;
	}

	@Override
	public double getDepth(int x, int y) {
		return data[D][x][y];
	}

	@Override
	public void setDepth(int x, int y, double d) {
		data[D][x][y] = d;
	}

	@Override
	public Stats createStats() {
		double nearest = -1;
		double widest = 0;
		double r = 0;
		double g = 0;
		double b = 0;
		
		
		for(int x = 0; x < width; ++x){
			for(int y = 0; y < height; ++y){
				if(data[R][x][y] > r){
					r = data[R][x][y];
				}
				if(data[G][x][y] > g){
					g = data[G][x][y];
				}
				if(data[B][x][y] > b){
					b = data[B][x][y];
				}
				if(data[D][x][y] > widest){
					widest = data[D][x][y];
				}else if(nearest!=-1 && nearest>data[D][x][y]){
					nearest = data[D][x][y];
				}
			}
		}
		
		return new FullImage.Stats(nearest, widest, new LightColor(r, g, b));
	}
 
	@Override
	public BufferedImage finalRender(Filter filter) {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for(int x = 0; x < width; ++x){
			for(int y = 0; y < height; ++y){
				image.setRGB(x, y, filter.shade(x, y, data[R][x][y], data[G][x][y], data[B][x][y], data[D][x][y]));
			}
			
		}
		return image;
	}

	@Override
	public void remove() {
	}
}
