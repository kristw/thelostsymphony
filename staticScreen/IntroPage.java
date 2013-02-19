package staticScreen;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import LostSymphony.LostSymphony;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.gui.toolkit.FrameWork;

public class IntroPage extends elxGameObject {

	private BufferedImage topBorder,bigPic,upperBottomBorder,text,lowerBottomBorder;
	private int textCount=1;
	private int picCount=1;
	private int counter;
	private String root_dir;
	private boolean isEnd=false;
	
	public IntroPage(GameEngine ge){
		super(ge);
		root_dir = "scr_resources/intro/";
		topBorder = getImage(root_dir+"intro_01.jpg");
		bigPic = getImage(root_dir+"images/bigPic1_02.jpg");
		upperBottomBorder=getImage(root_dir+"intro_03.jpg");
		text = getImage(root_dir+"images/txt1_04.jpg");
		lowerBottomBorder=getImage(root_dir+"intro_05.jpg");
	}
	
	public void initResources() {
		frame = new FrameWork(bsInput, getWidth(), getHeight());
	}

	public void update(long arg0) {
		if(textCount>=15)picCount=4;
		else if(textCount>=11)picCount=3;
		else if(textCount>=5)picCount=2;
		else picCount=1;
		bigPic = getImage(root_dir+"images/bigPic"+picCount+"_02.jpg");
		text = getImage(root_dir+"images/txt"+textCount+"_04.jpg");
		if(keyPressed(KeyEvent.VK_ESCAPE)){
			father.nextGameID=LostSymphony.GAMEID_MENUPAGE;
			finish();
		}
		else if(keyPressed(KeyEvent.VK_SPACE)){
			if(textCount<18)textCount++;
		}
		else if(keyPressed(KeyEvent.VK_BACK_SPACE)){
			if(textCount>1)textCount--;
		}
		frame.update();
	}

	public void render(Graphics2D g) {
		g.drawImage(topBorder,null,0,0);
		g.drawImage(bigPic,null,0,197);
		g.drawImage(upperBottomBorder,null,0,608);
		g.drawImage(text,null,0,639);
		g.drawImage(lowerBottomBorder,null,0,694);
		frame.render(g);
	}

}
