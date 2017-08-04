package tr.org.linux.kamp.agarioclone.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
/**
 * 
 * @author mustafa
 *
 */
public class GameFrame extends JFrame{
	/**
	 * penceremizi oluşturuyoruz
	 */
	public GameFrame() {
		setTitle("Agario Clone");
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(640,480);
		setLocationRelativeTo(null);
		
		


		
	}
}
