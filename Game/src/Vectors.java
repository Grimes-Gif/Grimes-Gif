
public class Vectors {

	public float x;
	public float y;
	
	public static float ScreenX;
	public static float ScreenY;
	
	public Vectors() {
		x = 0;
		y = 0;
	}
	
	public Vectors(Vectors vec) {
		new Vectors(vec.x, vec.y);
	}
	
	public Vectors (float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void addX(float f) { x += f; }
	public void addY(float f) { y += f; }
	
	public void setX(float f) { x = f; }
	public void setY(float f) { y = f; }
	
	public void setVector(Vectors vec) {
		this.x = vec.x;
		this.y = vec.y;
	}
	
	public void setVector(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public static void setWorldVar(float x, float y) {
		ScreenX = x;
		ScreenY = y;
		
	}
	
	public Vectors getWorldVar() {
		return new Vectors(x - ScreenX, y - ScreenY);
	}
	
	public String toString() {
		return x + "," + y;
	}
}
