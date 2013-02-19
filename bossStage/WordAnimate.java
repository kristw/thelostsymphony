package bossStage;

import java.awt.image.BufferedImage;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.sprite.AdvanceSprite;


public class WordAnimate extends AdvanceSprite {

	public WordAnimate(BufferedImage[] wordImages,double x,double y){
		
		super(wordImages,x,y);
		
		getAnimationTimer().setDelay(50);
		
		// set animation frame starting from the first image to the third image
		setAnimationFrame(0,0);
	    
		// animate the sprite, and perform continous animation
		setAnimate(true);
		
	}
	
	public void displayHit()
	{
		setAnimationFrame(5,9);
		setAnimate(true);
	}
	
	public void displayMiss()
	{
		setAnimationFrame(0,5);
		setAnimate(true);
	}
	
}
