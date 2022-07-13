
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Font {
	
	private BufferedImage FONT_SHEET = null;
	private BufferedImage[][] Sprite_Array;
	private final int TILE_SIZE = 32;
	public int w;
	public int h;
	private int wLetter;
	private int hletter;
	
	public Font(String file) {
		w = TILE_SIZE;
		h = TILE_SIZE;
		
		System.out.println("Lodaing: " + file + " ...");
		FONT_SHEET = loadFont(file);
		
		wLetter = FONT_SHEET.getWidth() / w;
		hletter = FONT_SHEET.getHeight() / h;
		loadSpriteArray();
	}
		
	public Font(String file, int w, int h) {
		this.w = w;
		this.h = h;
		
		System.out.println("Loading: " + file + " ...");
		FONT_SHEET = loadFont(file);
		
		wLetter = FONT_SHEET.getWidth() / w;
		hletter = FONT_SHEET.getHeight() / h;
		loadSpriteArray();
	}
	
	public void setSize(int width, int height) {
		setWidth(width);
		setHeight(height);
	}
	
	public void setWidth(int i) {
		w = i;
		wLetter = FONT_SHEET.getWidth() / w;
	}
	
	public void setHeight(int i) {
		h = i;
		hletter = FONT_SHEET.getHeight() / h;
	}
	
	public int getWidth() { return w; }
	public int getHeight() { return h; }
	
	private BufferedImage loadFont(String file) {
		BufferedImage sprite = null;
		try {
			sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
		} catch(Exception e) {
			System.out.println("ERROR: could not load file" + file);		
		}
		
		
		return sprite;
		
		
	}
	
	public void loadSpriteArray() {
		Sprite_Array = new BufferedImage[wLetter][hletter];
		
		for(int x = 0; x < wLetter; x++) {
			for(int y = 0; y < hletter; y++) {
				Sprite_Array[x][y] = getLetter(x, y);
			}
		}
	}
	
	public BufferedImage getFontSheet() { return FONT_SHEET;}	
	
	public BufferedImage getLetter(int x, int y) {
		return FONT_SHEET.getSubimage(x*w, y*h, w, h);
	}
	
	public BufferedImage getFont(char letter) {
		int value = letter;
		
		int x = value % wLetter;
		int y = value / hletter;
		
		return FONT_SHEET.getSubimage(x, y, w, h);
	}
	
}
