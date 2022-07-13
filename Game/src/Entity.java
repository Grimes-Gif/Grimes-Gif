import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class Entity {

	private final int UP = 0;
	private final int DOWN = 2;
	private final int RIGHT = 3;
	private final int LEFT = 1;
	
	protected Animation ani;
	protected Sprites sprite;
	protected Vectors pos;
	protected int size;
	protected int CurrentAnimation;
	protected boolean up;
	protected boolean down;
	protected boolean right;
	protected boolean left;
	protected boolean attack;
	protected int attackSpeed;
	protected int attackDuration;
	protected float dx;
	protected float dy;
	
	protected float maxSpeed = 3f;
	protected float accel = 1.10f;
	protected float deaccel = 0.09f;
	
	protected AABB hitBounds;
	protected AABB bounds;
	
	
	public Entity(Sprites sprite, Vectors orgin, int size) {
		this.sprite = sprite;
		pos = orgin;
		this.size = size;
		
		bounds = new AABB(orgin, size, size);
		hitBounds = new AABB(new Vectors(orgin.x + (size / 2), orgin.y), size, size);
		
		ani = new Animation();
		setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 10);
	}
	
	
	public void setSprite(Sprites sprite) {
		this.sprite = sprite;
		
	}
	
	public void setSize(int i) {
		size = i;
	}
	public void setMaxSpeed(float f) {
		maxSpeed = f;
	}
	
	public void setAccel(float f) {
		accel = f;
	}
	
	public void setDeaccel(float f) {
		deaccel = f;
	}
	public AABB getBounds() {
		return bounds;
	}
	
	public int getSize() {
		return size;
	}
	public Animation getAnimation() {
		return ani;
	}
	
	public void setAnimation(int i, BufferedImage[] frames, int delay) {
		CurrentAnimation = i;
		ani.setFrames(frames);
		ani.setDelay(delay);
	}
	
	public void animate() {
		if(up) {
			if(CurrentAnimation != UP || ani.getDelay() == -1) {
				setAnimation(UP, sprite.getSpriteArray(UP), 5);
			}
		}
		else if(down) {
			if(CurrentAnimation != DOWN || ani.getDelay() == -1) {
				setAnimation(DOWN, sprite.getSpriteArray(DOWN), 5);
			}
		}
		else if(left) {
			if(CurrentAnimation != LEFT || ani.getDelay() == -1) {
				setAnimation(LEFT, sprite.getSpriteArray(LEFT), 5);
			}
		}
		else if(right) {
			if(CurrentAnimation != RIGHT || ani.getDelay() == -1) {
				setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 5);
			}
		} else {
				setAnimation(CurrentAnimation, sprite.getSpriteArray(CurrentAnimation), -1);
			}
		}
	
	public void setHitBoxDirection() {
		if(up) {
			hitBounds.setYOffset(-size / 2);
			hitBounds.setXOffSet(-size / 2);
		}
		else if(down) {
			hitBounds.setYOffset(size / 2);
			hitBounds.setXOffSet(-size / 2);
			
		}
		else if(left) {
			hitBounds.setXOffSet(-size);
			hitBounds.setYOffset(0);
		}
		else if(right) {
			hitBounds.setXOffSet(0);
			hitBounds.setYOffset(0);
		}
	}
	
	
	
	
	public void update() {
		animate();
		setHitBoxDirection();
		ani.update();
	}
	
	public abstract void render(Graphics2D g);
	public void input(KeyHandler key, MouseHandler mouse) {
		
	}

}
