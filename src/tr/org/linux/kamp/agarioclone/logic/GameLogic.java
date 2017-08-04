package tr.org.linux.kamp.agarioclone.logic;

import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.lang.annotation.Target;
import java.nio.channels.GatheringByteChannel;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.SwingConstants;

import tr.org.linux.kamp.agarioclone.modal.Chip;
import tr.org.linux.kamp.agarioclone.modal.Diffculty;
import tr.org.linux.kamp.agarioclone.modal.Enemy;
import tr.org.linux.kamp.agarioclone.modal.GameObject;
import tr.org.linux.kamp.agarioclone.modal.Mine;
import tr.org.linux.kamp.agarioclone.modal.Player;
import tr.org.linux.kamp.agarioclone.view.GameFrame;
import tr.org.linux.kamp.agarioclone.view.GamePanel;
/**
 * 
 * @author Mustafa
 * @version 1.0
 *
 */
public class GameLogic {

	private Player player;
	private ArrayList<GameObject> gameObject;

	private ArrayList<GameObject> cipsToRemove;
	private ArrayList<GameObject> minesToRemove;
	private ArrayList<GameObject> enemysToRemove;
	private GameFrame gameFrame;
	private GamePanel gamePanel;
	private Enemy enemy;
	private Random random;
	private boolean isGameRunning;
	private int xTarget;
	private int yTarget;
	/**
	 * 
	 * @param playerName Oyuncu adnı
	 * @param color		 Oyuncu rengi
	 * @param diffculty	 Oyun zorluk derecesi
	 */
	public GameLogic(String playerName,Color color,Diffculty diffculty) {
		player = new Player(10, 10, 20,2,color , playerName);

		gameObject = new ArrayList<GameObject>();
		cipsToRemove = new ArrayList<GameObject>();
		minesToRemove = new ArrayList<GameObject>();
		enemysToRemove = new ArrayList<GameObject>();

		gameObject.add(player);

		gameFrame = new GameFrame();
		gamePanel = new GamePanel(gameObject);
		random = new Random();
	
		switch (diffculty) {
	
		case EASY:
			fillChips(15);
			fillMine(7);
			fillEnemies(5);
			break;
		case NORMAL:
			fillChips(25);
			fillMine(10);
			fillEnemies(9);
			break;
		case HARD:
			fillChips(40);
			fillMine(20);
			fillEnemies(15);
			break;
			default:
				break;
			
		}
		
		
		
		addMouseListener();

	}
	/**
	 * ilk olarak dizi içerisindeki nesnelerin türünü kontrol eder.
	 * Daha sonra nesnelerin oyun içerisinde kesişip kesişmediğinin kontrolü yapılır.
	 */
	private synchronized void checkCollisions() {

		for (GameObject gameObject2 : gameObject) {

			if (player.getRectangle().intersects(gameObject2.getRectangle())) {

				if (gameObject2 instanceof Chip) {
					player.setRadius(player.getRadius() + gameObject2.getRadius() / 2);
					cipsToRemove.add(gameObject2);
				}

				if (gameObject2 instanceof Enemy) {
					if (player.getRadius() > gameObject2.getRadius()) {
						player.setRadius(player.getRadius() + gameObject2.getRadius());
						enemysToRemove.add(gameObject2);
					} else if (player.getRadius() < gameObject2.getRadius()) {
						gameObject2.setRadius(gameObject2.getRadius() + player.getRadius());
						// Game Over
						isGameRunning = false;
						

					}

				}

				if (gameObject2 instanceof Mine) {
					player.setRadius((int) player.getRadius() / 2);
					minesToRemove.add(gameObject2);

				}

			}

			if (gameObject2 instanceof Enemy) {
				Enemy enemy = (Enemy) gameObject2;
				for (GameObject gameObject3 : gameObject) {
					if (enemy.getRectangle().intersects(gameObject3.getRectangle())) {

						if (gameObject3 instanceof Chip) {
							enemy.setRadius(enemy.getRadius() + gameObject3.getRadius() / 2);
							cipsToRemove.add(gameObject3);
						} else if (gameObject3 instanceof Mine) {
							enemy.setRadius(gameObject3.getRadius() / 2);
							cipsToRemove.add(gameObject3);

						} else if (gameObject3 instanceof Enemy) {

						}

					}
				}

			}

		}

		gameObject.removeAll(cipsToRemove);
		gameObject.removeAll(minesToRemove);
		gameObject.removeAll(enemysToRemove);

	}
	
	/*
	 * Oyun oynanırken ömrünü tamamlamış nesneler çıkarıldıkları için bu nesneleri en baştan projeye dahil ediyor. 
	 */
	private synchronized void addNewObjects() {
		fillChips(cipsToRemove.size());
		fillMine(minesToRemove.size());
		fillEnemies(enemysToRemove.size());

		cipsToRemove.clear();// eğer burda temizlemezsek sürekli random chip üretir.
		minesToRemove.clear();
		enemysToRemove.clear();
	}
	
	/*
	 * Player nesnesinin fareyi takip etmesi iöin yazılan metod
	 */
	private synchronized void movePlayer() {
		if (xTarget > player.getX()) {
			player.setX(player.getX() + player.getSpeed());

		} else if (xTarget < player.getX()) {
			player.setX(player.getX() - player.getSpeed());

		}

		if (yTarget > player.getY()) {
			player.setY(player.getY() + player.getSpeed());

		} else if (yTarget < player.getY()) {
			player.setY(player.getY() - player.getSpeed());

		}

	}
	/**
	 * Düşmanın player nesnesinen konumu ve boyutuna bağlı olarak hareket etmesini sağlayan metod
	 */
	private synchronized void moveEnemies() {
		for (GameObject gameoblect2 : gameObject) {
			if (gameoblect2 instanceof Enemy) {
				Enemy enemy = (Enemy) gameoblect2;

				if (enemy.getRadius() < player.getRadius()) {
					// Kaçacak

					int distance = (int) Point.distance(player.getX(), player.getY(), enemy.getX(), enemy.getY());

					int newX = enemy.getX() + enemy.getSpeed();
					int newY = enemy.getY() + enemy.getSpeed();
					if (calculateNewDistanceToEnemy(enemy, distance, newX, newY)) {

						continue;
					}

					newX = enemy.getX() - enemy.getSpeed();
					newY = enemy.getY() + enemy.getSpeed();
					if (calculateNewDistanceToEnemy(enemy, distance, newX, newY)) {

						continue;
					}

					newX = enemy.getX() + enemy.getSpeed();
					newY = enemy.getY() - enemy.getSpeed();
					if (calculateNewDistanceToEnemy(enemy, distance, newX, newY)) {

						continue;
					}

					newX = enemy.getX() - enemy.getSpeed();
					newY = enemy.getY() - enemy.getSpeed();
					if (calculateNewDistanceToEnemy(enemy, distance, newX, newY)) {

						continue;
					}

				} else {

					if (player.getX() > enemy.getX()) {
						enemy.setX(enemy.getX() + enemy.getSpeed());

					} else if (player.getX() < enemy.getX()) {
						enemy.setX(enemy.getX() - enemy.getSpeed());

					}

					if (player.getY() > enemy.getY()) {
						enemy.setY(enemy.getY() + enemy.getSpeed());

					} else if (player.getY() < enemy.getY()) {
						enemy.setY(enemy.getY() - enemy.getSpeed());

					}

				}

			}
		}
	}
	/**
	 * 
	 * @param enemy     düşman nesnesi
	 * @param distance	mesafe
	 * @param x			Koordinattaki x değeri
	 * @param y			Koordinattaki y değeri
	 * @return			şarta göre true yada false değerinin döndürülmesi
	 * 
	 * player ve düşman arasındaki mesafeyi hesaplayan metod
	 */
	private boolean calculateNewDistanceToEnemy(Enemy enemy, int distance, int x, int y) {
		int newDistance = (int) Point.distance(player.getX(), player.getY(), x, y);
		if (newDistance > distance) {
			enemy.setX(x);
			enemy.setY(y);
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param j    j değişkenin değeri kadar yem üretir
	 */
	private synchronized void fillChips(int j) {
		for (int i = 0; i < j; i++) {
			gameObject.add(new Chip(random.nextInt(gameFrame.getWidth()), random.nextInt(gameFrame.getHeight()), 10, Color.ORANGE));

		}
	}
	
	/**
	 * 
	 * @param j  j değişkeninin değeri kadar mayın üretir
	 */
	
	private synchronized void fillMine(int j) {
		for (int i = 0; i < j; i++) {
			Mine mine = new Mine(random.nextInt(gameFrame.getWidth()), random.nextInt(gameFrame.getHeight()), 20, Color.GREEN);

			while (player.getRectangle().intersects(mine.getRectangle())
					&& enemy.getRectangle().intersects(mine.getRectangle())) {
				mine.setX(random.nextInt(1000));
				mine.setY(random.nextInt(1000));
			}
			gameObject.add(mine);
		}

	}
	

	/**
	 * 
	 * @param n   n değişkeninin değeri kadar düşman üretir
	 */
	private synchronized void fillEnemies(int n) {
		for (int i = 0; i < n; i++) {
			Enemy enemy = new Enemy(random.nextInt(gameFrame.getWidth()), random.nextInt(gameFrame.getHeight()), (random.nextInt(10) + 15), 1, Color.RED,
					"Bot");
			while (player.getRectangle().intersects(enemy.getRectangle())) {
				enemy.setX(random.nextInt(1000));
				enemy.setY(random.nextInt(1000));
			}
			gameObject.add(enemy);
		}
	}
	
	
	/**
	 * Oyunun başlatan metod
	 * yeni bir iş parçacığı(Thread) oluşturarak bu iş parçacığı üzerinden oyunun koşturur
	 */
	public synchronized void startGame() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				while (isGameRunning) {
					movePlayer();
					checkCollisions();
					addNewObjects();
					moveEnemies();
					gamePanel.repaint();

					try {
						Thread.sleep(15);
					} catch (InterruptedException e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			}
		}).start();

	}
	
	/**
	 * startGame metodundaki while döngüsünü aktif eden metod
	 */
	public void startApplication() {
		gameFrame.setContentPane(gamePanel);
		isGameRunning = true;
		gameFrame.setVisible(true);
		startGame();
	}
	/**
	 * Farenin hareketlerini dinleyen metod
	 */
	private void addMouseListener() {
		gameFrame.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) { // Mouse'e basılı tutulduğunda çalışan kod
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) { // mouse pencerenin dışına çıktığı zaman çalışan kod
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {// mouse pencerenin içine girdiğini gösteriyor.
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		gameFrame.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) { // Mouse pencere üzerinde hareket ettiğinde çalışıcak
				// TODO Auto-generated method stub

				xTarget = e.getX();
				yTarget = e.getY();

			}

			@Override
			public void mouseDragged(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

}
