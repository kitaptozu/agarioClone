package tr.org.linux.kamp.agarioclone.modal;

import java.awt.Color;
import java.awt.Graphics2D;
/**
 * 
 * @author mustafa
 *
 */
public class Enemy extends GameObject {
	
	private int speed;
	private String name;
	/**
	 * 
	 * @param x			Düşmanın x koordinatı
	 * @param y			Düşmanın y koordinatı
	 * @param radius	Düşmanın yaçapı 
	 * @param speed		Düşmanın hızı
	 * @param color		Düşmanın rengi
	 * @param name		Düşmanın adı
	 */
	public Enemy(int x, int y, int radius,int speed, Color color,String name) {
		super(x, y, radius, color);
		// TODO Auto-generated constructor stub
		this.speed=speed;
		this.name=name;
	}
	
	/**
	 * Belirtilen şarta göre düşmanın yarıçapının ayarlanması metodu
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
	 * @return speed düşmanın hızını döndürür
	 */
	public int getSpeed() {
		return speed;
	}
	/**
	 * 
	 * @param speed Düşamının hızını ayarlar
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	/**
	 * Düşmanın ekrandaki görünümünü çizer
	 */
	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		super.draw(g2d);
		g2d.setColor(Color.WHITE);
		g2d.drawString(this.name, getX()+getRadius()/2-name.length()/2*8, getY() + getRadius() / 2);
		
	}
	
}
