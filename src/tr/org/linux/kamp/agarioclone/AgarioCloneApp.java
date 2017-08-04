package tr.org.linux.kamp.agarioclone;

import java.awt.Color;

import tr.org.linux.kamp.agarioclone.logic.GameLogic;
import tr.org.linux.kamp.agarioclone.modal.Diffculty;
import tr.org.linux.kamp.agarioclone.view.GameFrame;
/**
 * 
 * @author Mustafa
 *
 */
public class AgarioCloneApp {

	public static void main(String[] args) { 
		GameLogic gameLogic = new GameLogic("Ali",Color.RED,Diffculty.EASY);
		gameLogic.startApplication();
		
	}
}
