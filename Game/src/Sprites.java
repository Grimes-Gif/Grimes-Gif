import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Sprites {
	
	private BufferedImage SPRITE_SHEET = null;
	private BufferedImage[][] Sprite_Array;
	private final int TILE_SIZE = 32;
	public int w;
	public int h;
	private int wSprite;
	private int hSprite;
	
	public Sprites(String file) {
		w = TILE_SIZE;
		h = TILE_SIZE;
		
		System.out.println("Lodaing: " + file + " ...");
		SPRITE_SHEET = loadSprite(file);
		
		wSprite = SPRITE_SHEET.getWidth() / w;
		hSprite = SPRITE_SHEET.getHeight() / h;
		loadSpriteArray();
	}
		
	public Sprites(String file, int w, int h) {
		this.w = w;
		this.h = h;
		
		System.out.println("Loading: " + file + " ...");
		SPRITE_SHEET = loadSprite(file);
		
		wSprite = SPRITE_SHEET.getWidth() / w;
		hSprite = SPRITE_SHEET.getHeight() / h;
		loadSpriteArray();
	}
	
	public void setSize(int width, int height) {
		setWidth(width);
		setHeight(height);
	}
	
	public void setWidth(int i) {
		w = i;
		wSprite = SPRITE_SHEET.getWidth() / w;
	}
	
	public void setHeight(int i) {
		h = i;
		hSprite = SPRITE_SHEET.getHeight() / h;
	}
	
	public int getWidth() { return w; }
	public int getHeight() { return h; }
	
	private BufferedImage loadSprite(String file) {
		BufferedImage sprite = null;
		try {
			sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
		} catch(Exception e) {
			System.out.println("ERROR: could not load file" + file);		
		}
		
		
		return sprite;
		
		
	}
	
	public void loadSpriteArray() {
		Sprite_Array = new BufferedImage[hSprite][wSprite];
		
		for(int y = 0; y < hSprite; y++) {
			for(int x = 0; x < wSprite; x++) {
				Sprite_Array[y][x] = getSprite(x, y);
			}
		}
	}
	
	public BufferedImage getSpriteSheet() { return SPRITE_SHEET;}	
	
	public BufferedImage getSprite(int x, int y) {
		return SPRITE_SHEET.getSubimage(x*w, y*h, w, h);
	}
	
	public BufferedImage[] getSpriteArray(int i) {
		return Sprite_Array[i];
	}
	
	public BufferedImage[][] getSpriteArray2(int i) {
		return Sprite_Array;
	}
	
	
	public static void drawArray(Graphics2D g, ArrayList<BufferedImage> img, Vectors pos, int width, int height, int xOffset, int yOffset) {
		float x = pos.x;
		float y = pos.y;
		
		for(int i = 0; i < img.size(); i++) {
			if(img.get(i) != null) {
				g.drawImage(img.get(i), (int) x, (int) y, width, height, null);
			}
			
			x += xOffset;
			y += yOffset;
		}
	}
	
	public static void drawArray(Graphics2D g, Font f, String word, Vectors pos, int width, int height, int xOffset, int yOffset) {
		float x = pos.x;
		float y = pos.y;
		for(int i = 0; i < word.length(); i++) {
			if(word.charAt(i) != 32) {
				
			}
		}
		
		x += xOffset;
		y += yOffset;
	}
	
	
	
	
	
}
