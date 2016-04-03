package chloroplast.color.fullImage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import chloroplast.color.LightColor;
import chloroplast.color.filter.Filter;

public class FileFullImage implements FullImage{
	
	private File tempFile;
	private RandomAccessFile raf;
	private int width;
	private int height;
	
	private long LENGTH_ENTRY = 32L;
	private long LENGTH_DOUBLE = 8L;
	
	/*
	 * One Entry equals 8 * 4 = 32bytes
	 * 
	 */
	
	
	
	public FileFullImage(int width, int height) throws IOException{
		this.width = width;
		this.height = height;
		
		tempFile = File.createTempFile("raytrace_full_image_", "tmp");
		raf = new RandomAccessFile(tempFile, "rw");
		
		tempFile.deleteOnExit();
		raf.setLength(4L * 8 * width * height);
		
		
		
	}

	@Override
	public LightColor getColor(int x, int y) {
		try {
			raf.seek(LENGTH_ENTRY * (width * y + x));
		
			double r = raf.readDouble();
			double g = raf.readDouble();
			double b = raf.readDouble();
			return new LightColor(r, g, b);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void setColor(int x, int y, LightColor lightColor) {
		try {
			raf.seek(LENGTH_ENTRY * (width * y + x));
		
			raf.writeDouble(lightColor.r);
			raf.writeDouble(lightColor.g);
			raf.writeDouble(lightColor.b);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public double getDepth(int x, int y) {
		
		try {
			raf.seek(LENGTH_ENTRY * (width * y + x) + LENGTH_DOUBLE * 3);
		
			return raf.readDouble();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return -1;
	}

	@Override
	public void setDepth(int x, int y, double d) {
		try {
			raf.seek(LENGTH_ENTRY * (width * y + x) + LENGTH_DOUBLE * 3);
		
			raf.writeDouble(d);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Stats createStats() {
		double nearest = -1;
		double widest = 0;
		double r = 0;
		double g = 0;
		double b = 0;
		
		try {
			raf.seek(0);
		
			for(int i = 0; i < width*height; ++i){
				double value = raf.readDouble();
				
				if(value > r){
					r = value;
				}
				
				value = raf.readDouble();
				
				if(value > g){
					g = value;
				}
				
				value = raf.readDouble();
				
				if(value > b){
					b = value;
				}
				
				value = raf.readDouble();
				
				if(value > widest){
					widest = value;
				}else if(nearest!=-1 && nearest>value){
					nearest = value;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new FullImage.Stats(nearest, widest, new LightColor(r, g, b));
	}

	@Override
	public BufferedImage finalRender(Filter filter) {
		double r, g, b;
		double depth;
		int x, y;
		
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		try{
		
			raf.seek(0);
			
			for(int i = 0; i < width*height; ++i){
				r = raf.readDouble();
				g = raf.readDouble();
				b = raf.readDouble();
				depth = raf.readDouble();
				
				x = i % width;
				y = i / width;
				
				image.setRGB(x, y, filter.shade(x, y, r, g, b, depth));
			}
		
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return image;
	}

	@Override
	public void remove() {
		try {
			raf.close();
			tempFile.delete();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	

}
