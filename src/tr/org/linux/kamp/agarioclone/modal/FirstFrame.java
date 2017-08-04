package tr.org.linux.kamp.agarioclone.modal;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
/**
 * 
 * @author mustafa
 *
 */
public class FirstFrame extends JFrame {

	private JPanel contentPane;
	/**
	 * Ekranda görünecek pencerenin görünümü ve diğer ayarlar
	 */
	public FirstFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
	/**
	 * 
	 * @param args
	 * 
	 * Oyun Başlama ekranının main metodunda çağırılması
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstFrame frame = new FirstFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	

}
