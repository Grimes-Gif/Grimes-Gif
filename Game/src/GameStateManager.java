import java.awt.Graphics2D;
import java.util.ArrayList;

public class GameStateManager {
	
	private static ArrayList<States> GameState;
	
	public static Vectors map;
	
	public static final int PLAY = 0;
	public static final int MENU = 1;
	public static final int PAUSE = 2;
	public static final int GAMEOVER = 3;
	
	public GameStateManager(Graphics2D g) {
		map = new Vectors(Panel.width, Panel.height);
		Vectors.setWorldVar(map.x, map.y); 
		
		GameState = new ArrayList<States>();
		
		GameState.add(new PlayState());
		
	}
	
	public void addAndpop(int state) {
		GameState.remove(0);
		add(state);
	}
	
	public void pop(int state) {
		GameState.remove(state);
	}
	
	public void add(int state) {
		if(state == PLAY) {
			GameState.add(new PlayState());
		}
		if(state == MENU) {
			GameState.add(new MenuState());
		}
		if(state == PAUSE) {
			GameState.add(new PauseState());
		}
		if(state == GAMEOVER) {
			GameState.add(new GameOverState());
		}
	}
	
	public void update() {
		Vectors.setWorldVar(map.x, map.y);
		for(int i = 0; i < GameState.size(); i++) {
			GameState.get(i).update();
		}
	}
	
	public void render(Graphics2D g) {
		for(int i = 0; i < GameState.size(); i++) {
			GameState.get(i).render(g);
		}
	}
	
	public void input(MouseHandler mouse, KeyHandler key) {
		for(int i = 0; i < GameState.size(); i++) {
			GameState.get(i).input(mouse, key);
		}
		
	}
	
	
}
