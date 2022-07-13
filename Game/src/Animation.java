import java.awt.image.BufferedImage;

public class Animation {
	
	private BufferedImage[] frames;
	private int CurrentFrame;
	private int NumFrames;
	
	private int count;
	private int delay;
	
	private int timesPlayed;
	
	public Animation(BufferedImage[] frames) {
		timesPlayed = 0;
		setFrames(frames);
	}
	
	public Animation() {
		timesPlayed = 0;
	}
	
	public void setFrames(BufferedImage[] frames) {
		this.frames = frames;
		CurrentFrame = 0;
		count = 0;
		timesPlayed = 0;
		delay = 2;
		NumFrames = frames.length;
		
	}

	public void setDelay(int i) { delay = i; }
	public void setFrame(int i) { CurrentFrame = i; }
	public void setNumFrames(int i) { NumFrames = i; }
	
	public void update() {
		if(delay == -1) return;
		
		count++;
		
		if(count == delay) {
			CurrentFrame++;
			count = 0;
		}
		if(CurrentFrame == NumFrames) {
			CurrentFrame = 0;
			timesPlayed++;
		}
	}
	
	public int getDelay() {
		return delay;
	}
	public int getFrame() {
		return CurrentFrame;
	}
	public int getCount() {
		return count;
	}
	public BufferedImage getImage() {
		return frames[CurrentFrame];
	}
	public boolean hasPlayedOnce() {
		return timesPlayed > 0;
	}
	public boolean hasPlayed(int i) {
		return timesPlayed == i;
	}
}	

