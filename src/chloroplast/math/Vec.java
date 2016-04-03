package chloroplast.math;

public class Vec {
	
	public final double x, y, z;
	
	public Vec(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vec(){
		this(0, 0, 0);
	}
	
	public Vec add(Vec vec){
		return new Vec(x+vec.x, y+vec.y, z+vec.z);
	}
	public Vec add(double a, double b, double c){
		return new Vec(x+a, y+b, z+c);
	}
	
	public Vec substract(Vec vec){
		return new Vec(x-vec.x, y-vec.y, z-vec.z);
	}
	public Vec substract(double a, double b, double c){
		return new Vec(x-a, y-b, z-c);
	}
	
	public Vec muliply(Vec vec){
		return new Vec(x*vec.x, y*vec.y, z*vec.z);
	}
	public Vec muliply(double a, double b, double c){
		return new Vec(x*a, y*b, z*c);
	}
	
	public Vec divide(Vec vec){
		return new Vec(x/vec.x, y/vec.y, z/vec.z);
	}
	public Vec divide(double a, double b, double c){
		return new Vec(x/a, y/b, z/c);
	}
	
	public double length(){
		return Math.sqrt(x*x + y*y + z*z);
	}
	
	public double length2(){
		return x*x + y*y + z*z;
	}
	
	public Vec setLength(Vec vec, double newL){
		double l = vec.length();
		
		return new Vec(vec.x*newL/l, vec.y*newL/l, vec.z*newL/l);
	}
	
	public Vec getDirection(Vec v){
		double x = v.x - this.x;
		double y = v.y - this.y;
		double z = v.z - this.z;
		
		double l = Math.sqrt(x*x + y*y + z*z);
		
		return new Vec(x/l, y/l, z/l);
	}
	
	public Vec crossProduct(Vec v){
		return new Vec(y*v.z - z*v.y,
					   z*v.x - x*v.z, 
					   x*v.y - y*v.x);
	}
	
	public double scalarProduct(Vec v){
		return x*v.x + y*v.y + z*v.z;
	}
	
	

}
