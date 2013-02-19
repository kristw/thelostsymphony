package bossStage;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import sun.security.acl.GroupImpl;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public class Note{
	
	private int leftStatus = 0,rightStatus = 0;
	private Sprite left,right;
	BufferedImage note,noteHit;
	
	public Note(BufferedImage note,BufferedImage noteHit,double x,double y)
	{
		this.note = note;
		this.noteHit = noteHit;
		left = new Sprite(note,x,y);
		right = new Sprite(note,x+350,y);		
	}

	public int getLeftStatus() {
		return leftStatus;
	}
	
	public void setLeftStatus(int leftStatus) {
		this.leftStatus = leftStatus;
		if(leftStatus == 1) left.setImage(noteHit);
	}
	
	public int getRightStatus() {
		return rightStatus;
	}
	
	public void setRightStatus(int rightStatus) {
		this.rightStatus = rightStatus;
		if(rightStatus == 1) right.setImage(noteHit);
	}
	
	public void move(double x,double y)
	{
		left.move(x,y);
		right.move(x,y);
	}
	
	public void render(Graphics2D g){
		left.render(g);
		right.render(g);
	}

	public void update(long elapsedTime) {
		left.update(elapsedTime);
		right.update(elapsedTime);
		
	}

	public double getY() {
		return left.getY();
	}
	
}
