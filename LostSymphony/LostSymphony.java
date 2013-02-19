package LostSymphony;
import java.awt.Dimension;

import normalStage.Panda;
import staticScreen.*;
import bossStage.TheGame;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.GameObject;

public class LostSymphony extends GameEngine {
	
	int health = 200;
	int mana = 200;
	int maxHealth = 200;
	int maxMana = 200;
	int score = 0;
	public int level = 1;
	int cont =9;
	public static final int GAMEID_MENUPAGE = 4;
	public static final int GAMEID_PANDA=1;
	public static final int GAMEID_BEAT=2;
	public static final int GAMEID_TRANSITION=3;
	public static final int GAMEID_INTRO=0;
	public static final int GAMEID_END = 5;
	public static final int GAMEID_CREDITS=6;
	
	public boolean ready=false;
	public boolean isTransit=false;
	public boolean isBoss=false;
	
	public GameObject nextGameObject;

	public GameObject getGame(int gameID) {
		//System.out.println("getGame called. >>"+isTransit+":"+ready);
		if(isTransit){
			isTransit=false;
			return nextGameObject;
		}
		ready=false;
		if(level==1){
			score = 0;
			cont =9;
		}
		//end mode
		if(gameID==GAMEID_PANDA&&level==7)return new EndPage(this);
		
		//else
		switch(gameID){
			case GAMEID_MENUPAGE :return new MenuPage(this);
			case GAMEID_INTRO :return new IntroPage(this);
			case GAMEID_PANDA:nextGameObject = new Panda(this);isBoss=false;break;
			case GAMEID_BEAT:nextGameObject= new TheGame(this);isBoss=true;break;
			case GAMEID_END:return new EndPage(this);
			case GAMEID_CREDITS:return new CreditsPage(this);
			//case GAMEID_TRANSITION: return new TransitionPage(this);
			default: return new IntroPage(this);
		}
		isTransit=true;
		return new TransitionPage(this);
	}
	
    public static void main(String[] args) {
       // GameEngine class creation is equal with Game class creation
       GameLoader game = new GameLoader();
       game.setup(new LostSymphony(), new Dimension(1024,768),true);
       game.start();
       
    }

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	
	/**
	 * @return Returns the mana.
	 */
	public int getMana() {
		return mana;
	}
	/**
	 * @param mana The mana to set.
	 */
	public void setMana(int mana) {
		this.mana = mana;
	}
	/**
	 * @return Returns the maxHealth.
	 */
	public int getMaxHealth() {
		return maxHealth;
	}
	/**
	 * @param maxHealth The maxHealth to set.
	 */
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	/**
	 * @return Returns the maxMana.
	 */
	public int getMaxMana() {
		return maxMana;
	}
	/**
	 * @param maxMana The maxMana to set.
	 */
	public void setMaxMana(int maxMana) {
		this.maxMana = maxMana;
	}
	/**
	 * @return Returns the score.
	 */
	public int getScore() {
		return score;
	}
	/**
	 * @param score The score to set.
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	/**
	 * @return Returns the level.
	 */
	public int getLevel() {
		return level;
	}
	/**
	 * @param level The level to set.
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	/**
	 * @return Returns the cont.
	 */
	public int getCont() {
		return cont;
	}
	/**
	 * @param cont The cont to set.
	 */
	public void setCont(int cont) {
		this.cont = cont;
	}
}
