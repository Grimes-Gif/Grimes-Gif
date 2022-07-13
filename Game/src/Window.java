import javax.swing.JFrame;

public class Window extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public Window() {
		setTitle("Clash");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new Panel(Panel.width, Panel.height));
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		requestFocusInWindow();
	
	}
	
}
