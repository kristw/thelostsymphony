package bossStage;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import LostSymphony.LostSymphony;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.Timer;


public class TheGame extends GameObject{

/////-------------------------- Local Variable

	boolean start = false;
	boolean warning = false;
	private int gameStatus = 0;
	private int level;
	private String levelColor;
/////--------------------------- Hero Object
	Hero hero;
	Sprite heroSprite;
	
/////--------------------------- Boss Object and Controller
	Boss boss;
	BossController bossController;
	
/////--------------------------- Timer

	Timer startTimer,stopTimer;

/////--------------------------- NoteFlow -> create note beat
	
	NoteFlow noteFlow;
	LinkedList[] noteQueue;

/////--------------------------- Create battle field	
	BattleField battle;

/////--------------------------- image layout
	
	BufferedImage background,
	backgroundRed,
	mask,
	leftGauge,rightGauge,
	centerField,
	bamboo,
	barRed,barPurple;
	Sprite hpHeroSprite,hpBossSprite,comboHeroSprite,comboBossSprite;
	
	BufferedImage[] weaponHeroImages;
	BufferedImage[] patternHeroImages;
	BufferedImage[] weaponBossImages;
	BufferedImage[] patternBossImages;
	BufferedImage weaponHeroImageF;
	BufferedImage[] weaponBossImagesF;
	Sprite weaponHero,weaponBoss;
	Sprite patternHero,patternBoss;
	
/////--------------------------- image for adding to new Note
	BufferedImage note,noteHit;

////---------------------------- weapon
	BufferedImage weaponTest;
	
////---------------------------- HIT/MISS WORD
	WordAnimate wordAnimateHero, wordAnimateBoss;
	
////---------------------------- MUSIC PLAYER
	MusicPlayer music;

////---------------------------- TEMP FIELD
	int maxMana,score;



	
	public TheGame(GameEngine parent) {
		super(parent);
		this.level = ((LostSymphony) parent).getLevel() - 1;
//		this.level = 6;
		((LostSymphony)parent).ready=true;
	}
	
	public void initResources() 
	{
		startTimer = new Timer(3000);
		
////	---------------------------- Level Color
		
		switch (level)
		{
			case 1: levelColor = "purple"; break;
			case 2: levelColor = "grey"; break;
			case 3: levelColor = "red"; break;
			case 4: levelColor = "blue"; break;
			case 5: levelColor = "green"; break;
			default: levelColor = "yellow"; break;
		}
		
		background = getImage("resources2/bg_black.png");
		backgroundRed = getImage("resources2/bg_red.png");
		mask = getImage("resources2/mask.png");
		leftGauge = getImage("resources2/gauge_left.png");
		rightGauge = getImage("resources2/gauge_right.png");
		centerField = getImage("resources2/9x9_" + levelColor + ".jpg");
		bamboo = getImage("resources2/bamboo.png");
		barRed  = getImage("resources2/bar_red.jpg");
		barPurple = getImage("resources2/bar_purple.jpg");

		
		weaponHeroImages = new BufferedImage[5];
		weaponHeroImages[0] = getImage("resources2/note_item.png");
		weaponHeroImages[1] = getImage("resources2/Violin_item.png");
		weaponHeroImages[2] = getImage("resources2/zither_item.png");
		weaponHeroImages[3] = getImage("resources2/drum_item.png");
		weaponHeroImages[4] = getImage("resources2/flute_item.png");;
		weaponHero = new Sprite(weaponHeroImages[0],33,492.5);
		weaponHeroImageF = getImage("resources2/note_itemF.png");
		
		patternHeroImages = new BufferedImage[5];
		patternHeroImages[0] = getImage("resources2/note_pattern.png");
		patternHeroImages[1] = getImage("resources2/Violin_pattern.png");
		patternHeroImages[2] = getImage("resources2/zither_pattern.png");
		patternHeroImages[3] = getImage("resources2/drum_pattern.png");
		patternHeroImages[4] = getImage("resources2/flute_pattern.png");;
		patternHero = new Sprite(patternHeroImages[0],29.5,643.5);
		
		weaponBossImages = new BufferedImage[5];
		weaponBossImages[0] = getImage("resources2/boss1_item.png");
		weaponBossImages[1] = getImage("resources2/boss2_item.png");
		weaponBossImages[2] = getImage("resources2/boss3_item.png");
		weaponBossImages[3] = getImage("resources2/boss4_item.png");
		weaponBossImages[4] = getImage("resources2/boss5_item.png");;
		weaponBoss = new Sprite(weaponBossImages[0],878,492.5);	
		
		weaponBossImagesF = new BufferedImage[5];
		weaponBossImagesF[0] = getImage("resources2/boss1_itemF.png");
		weaponBossImagesF[1] = getImage("resources2/boss2_itemF.png");
		weaponBossImagesF[2] = getImage("resources2/boss3_itemF.png");
		weaponBossImagesF[3] = getImage("resources2/boss4_itemF.png");
		weaponBossImagesF[4] = getImage("resources2/boss5_itemF.png");;	
		
		patternBossImages = new BufferedImage[5];
		patternBossImages[0] = getImage("resources2/boss1_pattern.png");
		patternBossImages[1] = getImage("resources2/boss2_pattern.png");
		patternBossImages[2] = getImage("resources2/boss3_pattern.png");
		patternBossImages[3] = getImage("resources2/boss4_pattern.png");
		patternBossImages[4] = getImage("resources2/boss5_pattern.png");;
		patternBoss = new Sprite(patternBossImages[0],874.5,643.5);
		
		hpHeroSprite = new Sprite(barRed,33,104.5);
		comboHeroSprite = new Sprite(barPurple,106,104.5);
		hpBossSprite = new Sprite(barRed,878,104.5);
		comboBossSprite = new Sprite(barPurple,951,104.5);
		
		
////	---------------------------- Note Flow add Note Queue
		noteFlow = new NoteFlow();
		noteFlow.setGame(this);
		noteFlow.generateQueue();
		
		noteQueue = new LinkedList[4];
		for (int i = 0; i< 4 ; i++)
		{
			noteQueue[i] = new LinkedList();
		}
		
////	---------------------------- Init Note and Note Hit Image	

		note = getImage("resources2/beat_" + levelColor + ".jpg");
		noteHit = getImage("resources2/pad_" + levelColor + ".jpg");
		
		
////	---------------------------- Init Hero

		BufferedImage[] heroImages = initHeroImages();
		
		hero = new Hero(heroImages);
		hero.setGame(this);
		hero.setWeaponLimit(level);
		hero.setHealth(((LostSymphony) parent).getHealth());
		hero.setMaxHealth(((LostSymphony) parent).getMaxHealth());
		maxMana = ((LostSymphony)parent).getMaxMana(); 
		score = ((LostSymphony)parent).getScore();
////	---------------------------- Init Boss and Boss Controller
		
		BufferedImage[] bossImages = initBossImages(level);		
		
		boss = new Boss(bossImages);
		boss.setGame(this);
		
		switch(level){
			case 1: boss.setHealth(1000); boss.setMaxHealth(1000); break;
			case 2: boss.setHealth(1100); boss.setMaxHealth(1100);break;
			case 3: boss.setHealth(1200); boss.setMaxHealth(1200);break;
			case 4: boss.setHealth(1300); boss.setMaxHealth(1300);break;
			case 5: boss.setHealth(1400); boss.setMaxHealth(1400);break;
			default: boss.setHealth(1500);boss.setMaxHealth(1500); break;
		}
		
		bossController = new BossController(level);
		bossController.setGame(this);
		bossController.initResources();
		

////	---------------------------- Init Word for Hero and Boss	
		
		BufferedImage[] wordImages = initWordImages();
		wordAnimateHero = new WordAnimate(wordImages,100, 500);
		wordAnimateBoss = new WordAnimate(wordImages,800,500);		

////	---------------------------- Init Battle Field

		battle = new BattleField();
		battle.setGame(this);

////	---------------------------- Init Music Player
		
		music = new MusicPlayer("resources2/"+level+"beat.mp3");
		music.setGame(this);
		
		setFPS(100);
	}
	
	public void update(long elapsedTime) {
	
		
		wordAnimateHero.update(elapsedTime);
		wordAnimateBoss.update(elapsedTime);
		hero.update(elapsedTime);
		if(boss.getFrame() == 7)
			boss.setActive(false);
		boss.update(elapsedTime);
		
		weaponHero.update(elapsedTime);
		patternHero.update(elapsedTime);
		
		hpHeroSprite.setY(454.5 - (hero.getFactor() * 350));
		comboHeroSprite.setY(454.5 - (hero.getComboFactor() * 350));
		hpBossSprite.setY(454.5 - (boss.getFactor() * 350));
		comboBossSprite.setY(454.5 - (boss.getComboFactor() * 350));
		
		hpHeroSprite.update(elapsedTime);
		comboHeroSprite.update(elapsedTime);
		hpBossSprite.update(elapsedTime);
		comboBossSprite.update(elapsedTime);
		
////	---------------------------- Key Checker
		
		if(gameStatus == 0)
			recognizeKey();
		
////	---------------------------- Move all the Notes
		
		for (int i = 0; i< 4;i++)
		{
			for (int j = 0; j<noteQueue[i].size();j++)
			{
				((Note)noteQueue[i].get(j)).update(elapsedTime);
				((Note)noteQueue[i].get(j)).move(0,0.2*elapsedTime);
				
////			---------------------------- Boss hit here
				
				if( ((Note)noteQueue[i].get(j)).getY() > 500){
////				---------------------------- boss random hit					
					boolean randomProb;
					switch(level){
					case 1: randomProb = Math.random() > 0.1; break;
					case 2: randomProb = Math.random() > 0.08; break;
					case 3: randomProb = Math.random() > 0.06; break;
					case 4: randomProb = Math.random() > 0.04; break;
					case 5: randomProb = Math.random() > 0.02; break;
					default : randomProb = Math.random() > 0; break;
					}
					if(randomProb)
						hit2(i);

////				---------------------------- boss random miss
					else if(((Note)noteQueue[i].get(j)).getRightStatus() == 0 )
						((Note)noteQueue[i].get(j)).setRightStatus(2);
				}
				
////			---------------------------- Check if hero miss
				
				if(((Note)noteQueue[i].get(j)).getY() > 520)
				{
					
////				---------------------------- hero miss					
					if(((Note)noteQueue[i].get(j)).getLeftStatus() == 0){
						
						hero.clearCombo();
						wordAnimateHero.displayMiss();
					
					}
					
////				---------------------------- boss miss					
					if(((Note)noteQueue[i].get(j)).getRightStatus() == 0 ||
							((Note)noteQueue[i].get(j)).getRightStatus() == 2){
						
						boss.clearCombo();						
						wordAnimateBoss.displayMiss();
					}
					
////				---------------------------- remove both left and right
					noteQueue[i].remove(j);
				}
			
			}
			
		}
		
////	---------------------------- Start Music		
		if(startTimer.action(elapsedTime))
		{
			music.start();
			music.setPriority(3);
			bossController.start();
			startTimer.setActive(false);
		}
		
////
		if(stopTimer != null && stopTimer.action(elapsedTime))
		{
			System.out.println("end");
			setGameStatus(2);
			stopTimer.setActive(false);
		}
		
////	---------------------------- HERO WIN
		if(gameStatus == 1)
		{
			bossController.requestStop();
			if(keyPressed(KeyEvent.VK_SPACE)){
				music.requestStop();
				parent.nextGameID = 1;
				((LostSymphony)parent).setMaxHealth(hero.getMaxHealth());
				((LostSymphony)parent).setMaxMana(maxMana);
				((LostSymphony)parent).setScore(score);
				finish();
			}
		}
		
////	---------------------------- HERO LOSE
		if(gameStatus == 2)
		{
			bossController.requestStop();
			if(keyPressed(KeyEvent.VK_SPACE)){
				music.requestStop();
				((LostSymphony) parent).setHealth(hero.getMaxHealth());
				parent.nextGameID = 2;
				finish();
				/*if(((LostSymphony)parent).getCont()>0) {
					((LostSymphony)parent).setCont(((LostSymphony)parent).getCont()-1);
					((LostSymphony) parent).setHealth(hero.getMaxHealth());
					parent.nextGameID = 2;
				}
				else { parent.nextGameID = 0; }
					System.out.println("nextGameID " + parent.nextGameID );
					finish();*/
			}
		}
	}

	public void render(Graphics2D g) {
		
		if(gameStatus == 0)
		{
			g.setColor(Color.WHITE);
	        g.fillRect(0, 0, getWidth(), getHeight());   
			
	        hpHeroSprite.render(g);
			comboHeroSprite.render(g);
			hpBossSprite.render(g);
			comboBossSprite.render(g);
			
			weaponHero.render(g);
			patternHero.render(g);
			weaponBoss.render(g);
			patternBoss.render(g);
			drawLayout(g);
	        
	        //g.setColor(Color.BLACK);
			//g.fillRect(0,450,getWidth(),30);
			
			drawField(g);	
			
			hero.render(g);
			boss.render(g);
			
			for(int i = 0 ; i< 4 ; i++)
			{
				for (int j = 0; j<noteQueue[i].size();j++)
				{
					((Note) noteQueue[i].get(j)).render(g);
				}
			}
		}
		
		if(gameStatus == 1)
		{
			boss.render(g);
			g.setColor(new Color(255,0,162));
			//g.setColor(Color.PINK);
           // g.setFont(new Font("Comic Sans MS",1,100));
			//g.drawString("YOU WIN!!!",250,400);
			g.drawImage(getImage("scr_resources/youWin.gif"),null,324,320);
			//g.setFont(new Font("Comic Sans MS",1,40));
			//g.drawString("Please press spacebar to continue...",180,550);
		}
		
		else if(gameStatus == 2)
		{
			g.setColor(new Color(255,0,162));
           // g.setFont(new Font("Comic Sans MS",1,100));
			//g.drawString("YOU LOSE!!!",250,400);
			g.drawImage(getImage("scr_resources/youLose.gif"),null,324,320);
			//g.setFont(new Font("Comic Sans MS",1,40));
			//g.drawString("Please press spacebar to continue...",180,550);
			/*if(((LostSymphony)parent).getCont()>0)g.drawString("Continue : "+((LostSymphony)parent).getCont(),180,610);
               else g.drawString("GAME OVER",180,610);*/
		}

		
	}

	private BufferedImage[] initHeroImages() {
		BufferedImage[] heroImages = new BufferedImage[9];
		heroImages[0] = getImage("resources2/aPanda.png");
		heroImages[1] = getImage("resources2/aPanda1.png");
		heroImages[2] = getImage("resources2/aPanda2.png");
		heroImages[3] = getImage("resources2/aPanda3.png");
		heroImages[4] = getImage("resources2/aPanda4.png");
		heroImages[5] = getImage("resources2/aPanda5.png");
		heroImages[6] = getImage("resources2/aPanda6.png");
		heroImages[7] = getImage("resources2/aPanda7.png");
		heroImages[8] = getImage("resources2/aPanda8.png");
		return heroImages;
	}

	private BufferedImage[] initBossImages(int level) {
		
		BufferedImage[] bossImages = new BufferedImage[8];
		
		String bossAddress = "resources2/boss" + level + "-";
		
		for(int i = 0;i<8;i++)
			bossImages[i] = getImage(bossAddress+i+".png");
		
		return bossImages;
	}

	private void recognizeKey() {
		
		if(keyPressed(KeyEvent.VK_Q)){
			boss.damage(1000);
		}
		
		if(keyPressed(KeyEvent.VK_UP)){
			hero.move(0);
		}
		else if(keyPressed(KeyEvent.VK_DOWN)){
			hero.move(1);
		}
		else if(keyPressed(KeyEvent.VK_LEFT)){
			hero.move(2);
		}
		else if(keyPressed(KeyEvent.VK_RIGHT)){
			hero.move(3);
		}
		else 
		{
			if(keyPressed(KeyEvent.VK_A))
			{
				hit(0);
			}
			if(keyPressed(KeyEvent.VK_E))
			{
				hit(1);
			}
			if(keyPressed(KeyEvent.VK_R))
			{
				hit(2);
			}
			if(keyPressed(KeyEvent.VK_G))
			{
				hit(3);
			}
			if(keyPressed(KeyEvent.VK_W))
			{
				hit(0);hit(1);hit(2);hit(3);
			}
			if(keyPressed(KeyEvent.VK_SHIFT)){
				hero.attack();
			}
			if(keyPressed(KeyEvent.VK_CONTROL)){
				hero.changeWeapon();
			}
			if(keyPressed(KeyEvent.VK_ESCAPE)){
				parent.nextGameID = 1;
				finish();
			}
		}
	}

	private BufferedImage[] initWordImages() {
		
		BufferedImage[] wordImages = new BufferedImage[11];
		
		for (int i = 0; i <= 9 ; i ++)
			wordImages[i] = getImage("resources2/hit000" + i + ".png");
		
		wordImages[10] = getImage("resources2/hit0010.png");
		
		return wordImages;
		
	}

	private void hit(int i) {
		
		if(noteQueue[i].size() > 0 && ((Note)noteQueue[i].get(0)).getY() < 520 && 
				  ((Note)noteQueue[i].get(0)).getY() > 480)
				  {
					  ((Note)noteQueue[i].get(0)).setLeftStatus(1);
					  hero.addCombo();
					  wordAnimateHero.displayHit();
				  }
	}

	public void hit2(int i) {
		
		if(noteQueue[i].size() > 0 && ((Note)noteQueue[i].get(0)).getY() < 520 && 
				  ((Note)noteQueue[i].get(0)).getY() > 480)
				  {
					  Note tempNote = ((Note)noteQueue[i].get(0));
					  if(tempNote.getRightStatus() == 0)
					  {
						  ((Note)noteQueue[i].get(0)).setRightStatus(1);
						  boss.addCombo();
						  wordAnimateBoss.displayHit();
					  }
				  }
	}
	
	public void addQueue(int i)
	{
		noteQueue[i].add(new Note(note,noteHit,178+i*80,0));
	}
	
	public void drawField(Graphics2D g)
	{
		
		for (int i = 0; i< 3; i++)
			for(int j=0; j<6;j++)
			{
				Bullet tempBullet = battle.getField(i*6+j);
				if(tempBullet != null ){
					//BOSS Bullet
					if(tempBullet.getType() > 4){
						g.setColor(Color.RED);
						g.drawImage(weaponBossImagesF[tempBullet.getType()-5],null,195+116*j,500+i*72);
					}
					//HERO Bullet
					else{ 
						g.drawImage(weaponHeroImageF,null,195+116*j,500+i*72);
					}
					}
			}
	}
	
	public void drawLayout(Graphics2D g)
	{

			//g.drawImage(leftGauge,null,32,104);
			//g.drawImage(rightGauge,null,0,0);
			
			g.drawImage(background,null,179,0);
			
			if(warning)
				g.drawImage(backgroundRed,null,525,0);
			
			g.drawImage(mask,null,0,0);
			g.drawImage(centerField,null,179,549);
			g.drawImage(bamboo,null,499,0);
			
			g.drawImage(noteHit,null,179,500);
			g.drawImage(noteHit,null,259,500);
			g.drawImage(noteHit,null,339,500);
			g.drawImage(noteHit,null,419,500);
			g.drawImage(noteHit,null,525,500);
			g.drawImage(noteHit,null,605,500);
			g.drawImage(noteHit,null,685,500);
			g.drawImage(noteHit,null,765,500);
			
//        	
//			/*g.setColor(Color.BLACK);
//
////      LEFT        
//             g.fillRect(0,0,177,550);
//             g.drawRect(0,550,162,218);
//             
//             g.drawRect(162,550,350,218);
//             
//             g.drawLine(162+116,550,162+116,768);
//             g.drawLine(162+116+116,550,162+116+116,768);
//             
//             g.drawLine(162,550+72,512,550+72);
//             g.drawLine(162,550+72+72,512,550+72+72);
//             
//             //HIT FIELD
//             g.drawRect(177,0,320,550);
//             g.fillRect(497,0,15,550);
//             g.drawLine(257,0,257,550);
//             g.drawLine(337,0,337,550);
//             g.drawLine(417,0,417,550);
//             g.drawLine(497,0,497,550);
//             
//             g.setColor(Color.WHITE);
//             g.drawRect(40,30,30,400);
//             g.fillRect(40,430 - ((int)(hero.getFactor()*400)) ,30 ,((int)(hero.getFactor()*400)));
//             g.setColor(Color.BLACK);
//
//             g.setColor(Color.WHITE);
//             g.drawRect(90,30,30,400);
//             g.setColor(Color.RED);
//             g.fillRect(90,430 - ((int)(hero.getComboFactor()* 400)) ,30 ,((int)(hero.getComboFactor()* 400)));
//             g.setColor(Color.BLACK);*/
//             //g.fillArc(10,580,140,140,0,((int)(hero.getFactor()*360)));
//             //g.drawArc(10,580,140,140,0,360);
//             
//             
//             // RIGHT 
//             g.fillRect(1024-177,0,177,550);
//             g.drawRect(1024-162,550,162,218);
//             
//             g.drawRect(1024-162-350,550,350,218);
//             
//             g.drawLine(1024-(162+116),550,1024-(162+116),768);
//             g.drawLine(1024-(162+116+116),550,1024-(162+116+116),768);
//             g.drawLine(1024-162,550+72,1024-512,550+72);
//             g.drawLine(1024-162,550+72+72,1024-512,550+72+72);
//             
//             //HIT FIELD
//             g.drawRect(1024-100-397,0,397,550);
//             g.fillRect(1024-497-15,0,15,550);
//
//             g.drawLine(1024-180,0,1024-180,550);
//             g.drawLine(1024-260,0,1024-260,550);
//             g.drawLine(1024-340,0,1024-340,550);
//             g.drawLine(1024-417,0,1024-417,550);
//             
//             //g.fillArc(1024-10-140,580,140,140,0,((int)(boss.getFactor()*360)));
//             g.setColor(Color.WHITE);
//             
//             g.setColor(Color.WHITE);
//             g.drawRect(1024-70,30,30,400);
//             g.fillRect(1024-70,430 - ((int)(boss.getFactor()*400)) ,30 ,((int)(boss.getFactor()*400)));
//             g.setColor(Color.BLACK);
//
//             g.setColor(Color.WHITE);
//             g.drawRect(1024-120,30,30,400);
//             g.setColor(Color.RED);
//             g.fillRect(1024-120,430 - ((int)(boss.getComboFactor()* 400)) ,30 ,((int)(boss.getComboFactor()* 400)));
//             g.setColor(Color.BLACK);
//             
//             g.setColor(Color.BLUE);
//             g.setFont(new Font("Comic San MS",1,40));
//             g.drawString("" + hero.getCombo(),10,520);
//          
//             g.setFont(new Font("Comic San MS",1,40));
//             g.drawString("" + boss.getCombo(),900,520);
             
               weaponHero.setImage(weaponHeroImages[hero.getWeapon()]);
               patternHero.setImage(patternHeroImages[hero.getWeapon()]);
               weaponBoss.setImage(weaponBossImages[boss.getWeapon()]);
               patternBoss.setImage(patternBossImages[boss.getWeapon()]);
//            	 g.setColor(Color.BLACK);
//             else if(hero.getWeapon() == 1)
//            	 g.setColor(Color.GREEN);
//             else if(hero.getWeapon() == 2)
//            	 g.setColor(Color.RED);
//             g.fillOval(40,600,80,80);
//             g.setColor(Color.BLUE);
                       
             wordAnimateHero.render(g);
             wordAnimateBoss.render(g);

	}
	
	public void finish(){
		music.requestStop();
		super.finish();
	}	
	
//----------------- GETTERS --------------- BEGIN
	public Boss getBoss()
	{
		return this.boss;
	}
	
	public NoteFlow getNoteFlow()
	{
		return this.noteFlow;
	}

	public Hero getHero() {
		return hero;
	}
	
	public BattleField getBattle()
	{
		return this.battle;
	}

	
	public int getGameStatus() {
		return gameStatus;
	}

	
	public void setGameStatus(int gameStatus) {
		if(this.gameStatus != 1 && this.gameStatus != 2)
			this.gameStatus = gameStatus;	
	}

	public int getLevel() {
		return level;
	}
	
	public void setWarning(boolean warning){
		this.warning = warning;
	}
	
	public void timeOut(){
		System.out.println("timeOut");
		stopTimer = new Timer(3000);
	}
//	================= GETTERS ================ END
}
