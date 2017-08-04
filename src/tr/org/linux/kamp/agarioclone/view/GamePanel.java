package tr.org.linux.kamp.agarioclone.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import tr.org.linux.kamp.agarioclone.modal.GameObject;
/**
 * 
 * @author mustafa
 *
 */
public class GamePanel extends JPanel{
	
	private ArrayList<GameObject> gameObject;
	/**
	 * 
	 * @param gameObject nesnelerimizi içinde barındaran dizi
	 */
	public GamePanel(ArrayList<GameObject> gameObject) {
		// TODO Auto-generated constructor stub
		this.gameObject=gameObject;
	}
	
	
	/**
	 * komponentleri ekrana çizer
	 */
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		Graphics2D g2d=(Graphics2D) g;
		
		for (GameObject gameObject2 : gameObject) {
			gameObject2.draw(g2d);
		}
	}
	
}
