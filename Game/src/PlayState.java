import java.awt.Color;
import java.awt.Graphics2D;


public class PlayState extends States {
	
	private Player player;
	
	public PlayState() {
		player = new Player(new Sprites("Res/professor_walk_cycle_no_hat.png", 64 ,64 ), new Vectors(300, 300), 128);
	}
	
	public void update() {
		player.Update();
	}
	public void input(MouseHandler mouse, KeyHandler key) {
		player.input(mouse, key);
	}

	public void render(Graphics2D g) {
		player.render(g);
	}
}
