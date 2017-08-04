package tr.org.linux.kamp.agarioclone;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import tr.org.linux.kamp.agarioclone.logic.GameLogic;
import tr.org.linux.kamp.agarioclone.modal.Diffculty;
import tr.org.linux.kamp.agarioclone.modal.Player;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
/**
 * 
 * @author Mustafa
 *
 */
public class FirstPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public FirstPanel() {
		setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][grow]"));

		JLabel lblUsername = new JLabel("UserName:");
		add(lblUsername, "cell 0 0,alignx left");

		textField = new JTextField();
		add(textField, "cell 1 0,growx,aligny center");
		textField.setColumns(10);

		JLabel lblPassword = new JLabel("Password:");
		add(lblPassword, "cell 0 1");

		textField_1 = new JTextField();
		add(textField_1, "cell 1 1,growx,aligny center");
		textField_1.setColumns(10);

		JLabel lblNewLabel = new JLabel("Select Color:");
		add(lblNewLabel, "cell 0 2,alignx trailing");

		JComboBox comboBox = new JComboBox();
		add(comboBox, "cell 1 2,growx,aligny center");
		comboBox.addItem("Red");
		comboBox.addItem("Blue");
		comboBox.addItem("Orange");
		comboBox.addItem("Yellow");
		comboBox.addItem("Black");
		comboBox.addItem("Pink");
		comboBox.addItem("Cyan");
		comboBox.addItem("Magenta");
		comboBox.addItem("Green");

		JLabel lblDifficulty = new JLabel("Difficulty:");
		add(lblDifficulty, "cell 0 3,alignx left");

		ButtonGroup birAradaTut = new ButtonGroup();

		JRadioButton rdbtnEasy = new JRadioButton("Easy");
		add(rdbtnEasy, "cell 1 3");
		birAradaTut.add(rdbtnEasy);

		JRadioButton rdbtnNormal = new JRadioButton("Normal");
		rdbtnNormal.setSelected(true);
		add(rdbtnNormal, "cell 1 4");
		birAradaTut.add(rdbtnNormal);

		JRadioButton rdbtnHard = new JRadioButton("Hard");
		add(rdbtnHard, "cell 1 5");
		birAradaTut.add(rdbtnHard);

		JButton btnAbout = new JButton("About");
		btnAbout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JOptionPane.showMessageDialog(FirstPanel.this, "Bu Program LYK 2017 Java Sınıfı Tarafından Kodlanmıştır!","Hakkında",JOptionPane.DEFAULT_OPTION);
			}
		});
		add(btnAbout, "flowx,cell 1 6,alignx right");

		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (textField.getText().equals("admin") && textField_1.getText().equals("123")) {
					Color color = null;
					switch (comboBox.getSelectedItem().toString()) {
					case "Red":
						color = color.RED;
						break;
					case "Blue":
						color = color.BLUE;
						break;
					case "Orange":
						color = color.ORANGE;
						break;
					case "Yellow":
						color = color.YELLOW;
						break;
					case "Black":
						color = color.BLACK;
						break;
					case "Pink":
						color = color.PINK;
						break;
					case "Cyan":
						color = color.CYAN;
						break;
					case "Magenta":
						color = color.MAGENTA;
						break;
					case "Green":
						color = color.GREEN;
						break;
					default:
						break;
					}

					Diffculty diffculty = null;

					if (rdbtnEasy.isSelected()) {
						// Easy
						diffculty = Diffculty.EASY;
					} else if (rdbtnNormal.isSelected()) {
						// Normal
						diffculty = Diffculty.NORMAL;
					} else {
						// Hard
						diffculty = Diffculty.HARD;
					}

					GameLogic gameLogic = new GameLogic(textField.getText(), color,diffculty);
					gameLogic.startApplication();

				} else {
					JOptionPane.showMessageDialog(null, "Kullanıcı adı veya parola hatalı!!!");

				}

			}
		});
		add(btnStart, "cell 1 6,alignx right");
		
		table = new JTable();
		add(table, "cell 1 8");

	}

}
