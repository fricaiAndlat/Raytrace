package chloroplast.color.fullImage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import chloroplast.color.Filter;
import chloroplast.color.LightColor;

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
		raf.setLength(4 * 8 * width * height);
		
		
		
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
	public void setColor(int x, int y, LightColor color) {
		try {
			raf.seek(LENGTH_ENTRY * (width * y + x));
		
			raf.writeDouble(color.r);
			raf.writeDouble(color.g);
			raf.writeDouble(color.b);

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BufferedImage finalRender(Filter filter) {
		// TODO Auto-generated method stub
		return null;
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
