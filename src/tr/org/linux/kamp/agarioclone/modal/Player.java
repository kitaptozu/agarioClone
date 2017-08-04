package tr.org.linux.kamp.agarioclone.modal;

import java.awt.Color;
import java.awt.Graphics2D;
/**
 * 
 * @author mustafa
 *
 */
public class Player extends GameObject {

	private int speed;
	private String name;
	/**
	 * 
	 * @param x			player nesnesinin x koordinat değeri
	 * @param y			player nesnesinin y koordinat değeri
	 * @param radius	player nesnesinin yarıçap değeri
	 * @param speed		player nesnesinin hız değeri
	 * @param color		player nesnesinin renk değeri
	 * @param name		player nesnesinin adı
	 */
	public Player(int x, int y, int radius, int speed, Color color, String name) {
		super(x, y, radius, color);
		// TODO Auto-generated constructor stub
		this.speed = speed;
		this.name = name;

	}
	
	
	
	/**
	 * Şarta göre nesnenin yarıçapının ayarlanması
	 */
	@Override
	public void setRadius(int radius) {
		// TODO Auto-generated method stub
		super.setRadius(radius);
		if(getRadius()<5){
			setRadius(5);
		}
		else if(getRadius()>250) {
			setRadius(250);
		}
	}
	/**
	 * 
	 * @return speed	nesnenin hızını döndürür
	 */
	public int getSpeed() {
		return speed;
	}
	/**
	 * 
	 * @param speed		nesnenin hızını ayarlar
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	/**
	 * 
	 * @return nesnenin adını döndürür
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @param name nesnenin adını set eder
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * nesneniyi ekrana çizer
	 */
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		super.draw(g2d);
		g2d.setColor(Color.WHITE);
		g2d.drawString(this.name, getX()+getRadius()/2-name.length()/2*8, getY() + getRadius() / 2);
	}

}
