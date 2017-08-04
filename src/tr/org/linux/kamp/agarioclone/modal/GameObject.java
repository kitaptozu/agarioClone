package tr.org.linux.kamp.agarioclone.modal;

import java.awt.Color;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.*;
import java.awt.geom.Ellipse2D;
/**
 * 
 * @author mustafa
 *
 */
public abstract class GameObject {

	private int x;
	private int y;
	private int radius;
	private Color color;
	/**
	 * 
	 * @param x			nesnenin x koordinat değeri
	 * @param y			nesnenin y koordinat değeri
	 * @param radius	nesnenin yarıçap değeri
	 * @param color		nesnenin renk değeri
	 */
	public GameObject(int x, int y, int radius, Color color) {
		
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.color = color;
	}
	
	
	/**
	 * 
	 * @return x  nesnenin x koordinatını döndürür
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * 
	 * @param x nesnenin x değerinin ayarlar
	 */
	public void setX(int x) {
		
			this.x = x;
		
		
	}
	/**
	 * 
	 * @return y	nesnenin y koordinat değerini döndürür
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * 
	 * @param y nesnenin y değerini ayarlar
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * 
	 * @return radius	nesnenin radius'unu döndürür 
	 */
	public int getRadius() {
		return radius;
	}
	
	/**
	 * 
	 * @param radius	nesnenin redius değerinin ayarlar
	 */
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	/**
	 * 
	 * @return color	Nesnenin rengini döndürür
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * 
	 * @param color		Nesnenin rengini ayarlar
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	/**
	 * 
	 * @param g2d	koordinat değerleri,radius ve rengi göre nesneyi ekrana çizer
	 */
	public void draw(Graphics2D g2d) {
		g2d.setColor(getColor());
		g2d.fillOval(getX(),getY(),getRadius(),getRadius());
		
	}
	/**
	 * 
	 * @return rect		daire olan nesnemizin etrafına bir kare çizer
	 */
	public Rectangle getRectangle() {
		Rectangle rect = new Rectangle(getX(),getY(),getRadius(),getRadius());
		return rect;
	}
	

}
