package staticScreen;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import LostSymphony.LostSymphony;
import com.golden.gamedev.*;
//GTGE GUI
import com.golden.gamedev.gui.*;
import com.golden.gamedev.gui.toolkit.*;

/**
 * @author e-liXiR
 *
 */
public class MenuPage extends elxGameObject {

	public MenuPage(GameEngine ge) {
		super(ge);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void initResources() {
		frame = new FrameWork(bsInput, getWidth(), getHeight());

		//#play
		TButton btn_play = new TButton("",12,559, 0, 0){
			public void doAction(){
				father.nextGameID=LostSymphony.GAMEID_PANDA;
				finish();
			}; 
		};
		btn_play.setExternalUI(getImages("scr_resources/play.jpg", 4, 1),true);
		//#quit button
		TButton btn_quit = new TButton("",852,559, 0, 0){
			public void doAction(){
				System.exit(0);
			};
		};
		btn_quit.setExternalUI(getImages("scr_resources/quit.jpg", 4, 1),
						  true);
		
		frame.add(btn_play);
		frame.add(btn_quit);
		//#button area
	}

	public void update(long arg0) {
		// TODO Auto-generated method stub
		frame.update();
	}

	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		BufferedImage bg = getImage("scr_resources/bg.jpg");
		g.drawImage(bg,null,0,0);
		frame.render(g);
	}

}
