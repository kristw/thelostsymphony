package normalStage;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.StringTokenizer;

import LostSymphony.LostSymphony;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.AdvanceSpriteGroup;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.background.ImageBackground;
import com.golden.gamedev.object.background.ParallaxBackground;
import com.golden.gamedev.util.FileUtil;

/*
 * Created on 1 Á.¤. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author Nut
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Panda extends GameObject{
	
	public Panda(GameEngine parent) {
		super(parent);
		// TODO Auto-generated constructor stub
		((LostSymphony)parent).ready=true;
	}

	//Controller
	int [] controller = new int[] {
			KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT,	// left-right
			KeyEvent.VK_UP, KeyEvent.VK_DOWN,		// up-down
			KeyEvent.VK_SPACE,KeyEvent.VK_CONTROL};	// fire-changeWeapon
	
	/***************************** PLAY FIELD ***********************************/
	PlayField 	playfield;

	Background	backgr,backgr2,backgr3;

	SpriteGroup PLAYER, BULLET,WALKENEMY,SPIKE,TILE,BRIDGE,ENEMYBULLET,ENEMY,STONE,
				TRANTURNTILE,TURNTILE,ITEM,WARP,MOVEBLOCK,TURNBLOCK,BLINKBLOCK;//,GOAL;

	Player 		player;	
	GameFont    font1;
	//Image
	BufferedImage   image1,bonusImg,image3,bullImg,enemyImg,
					head,leftHand,rightHand,green,gold,goldTemp,empty,full;
	String levelTile;
	//Player
	int health;
	int remainingHealth;
	int remainingMana;
	int cont ;
	//Font
	Font font;
	int level = 1;
	Timer blink;
	MusicPlayer music;
	private static final int type1=1;
	
//	public static void main(String[] args) {
//		 
//		 GameLoader game = new GameLoader();
//	     game.setup(new Panda(), new Dimension(1024,768), false);
//	     game.start();
//	     
//	}

	public void initResources(){
		level = ((LostSymphony)parent).getLevel();
		if(level<=6) 	initLevel();
		head = this.getImage("resources/panda/gauge_head.png");
		leftHand = this.getImage("resources/panda/left_hand.png");
		rightHand = this.getImage("resources/panda/right_hand.png");
		green = this.getImage("resources/panda/green_bb.png");
		gold = this.getImage("resources/panda/golden_bb.png");
		goldTemp = this.getImage("resources/panda/golden_bb.png");
		empty = this.getImage("resources/panda/milk_empty.png");
		full = this.getImage("resources/panda/milk_full.png");
		cont =((LostSymphony)parent).getCont();	
		font1 = fontManager.getFont(getImages("resources/font.png", 20, 3),
                " !            .,0123" +
                "456789:   -? ABCDEFG" +
                "HIJKLMNOPQRSTUVWXYZ ");
	}
	
	public void initLevel() {
		//playMusic("resources/1music.mid");
		music = new MusicPlayer(level);
		music.start();
		//Set Player
		player = new Player(this,this.controller,0,0,type1);
		blink = new Timer(5000);
		
//		 get level file content
		// BEGIN LEVEL READING/PARSING
		String levelFile = "resources/level"+level+"/"+level+"Level.txt";
		String[] content = FileUtil.fileRead(bsIO.getStream(levelFile));


		//////////// reading level title ////////////
		StringTokenizer token = new StringTokenizer(content[0], "+");
		String[] desc = new String[token.countTokens()];
		for (int i=0;i < desc.length;i++) {
			desc[i] = token.nextToken();
		}
////////////reading level dimension ////////////
		token = new StringTokenizer(content[1], " x ");
		int w = Integer.parseInt(token.nextToken()), 	// level width
			h = Integer.parseInt(token.nextToken());	// level height
		
//		 create the playfield
		playfield 	= new PlayField();
		
		image1 = getImage("resources/level"+level+"/"+level+"background.jpg",false);

		backgr		= new ParallaxBackground(new Background[] {
				new Background(w*64,h*64),
				new ImageBackground(image1, // the image background can not larger than w*32, h*32
									(image1.getWidth() > w*64) ? w*64 : image1.getWidth(),
									(image1.getHeight() > h*64) ? h*64 : image1.getHeight())
			} );
		
		playfield.setBackground(backgr);
		playfield.getBackground().setToCenter(player);
//		create the sprite groups
		TILE  		= playfield.addGroup(new AdvanceSpriteGroup("Tile", 700));
		BRIDGE		= playfield.addGroup(new SpriteGroup("Bridge"));
		TRANTURNTILE = playfield.addGroup(new SpriteGroup("TranTurnTile"));
		TURNTILE	= playfield.addGroup(new SpriteGroup("TurnTile"));
		ENEMY 		= playfield.addGroup(new AdvanceSpriteGroup("Enemy",50));
		PLAYER		= playfield.addGroup(new SpriteGroup("Player"));
		BULLET  	= playfield.addGroup(new SpriteGroup("Bullet"));
		ENEMYBULLET = playfield.addGroup(new SpriteGroup("EnemyBullet"));
		ITEM		= playfield.addGroup(new AdvanceSpriteGroup("Item",50));
		SPIKE		= playfield.addGroup(new AdvanceSpriteGroup("Spike",100));
		WARP		= playfield.addGroup(new SpriteGroup("Warp"));
		MOVEBLOCK	= playfield.addGroup(new AdvanceSpriteGroup("MoveBlock"));
		TURNBLOCK	= playfield.addGroup(new AdvanceSpriteGroup("TurnBlock"));
		BLINKBLOCK  = playfield.addGroup(new AdvanceSpriteGroup("BlinkBlock"));
		STONE		= playfield.addGroup(new AdvanceSpriteGroup("Stone"));
////////////reading level tiles ////////////
		// building levels
		for (int y=0;y < h;y++) {
		for (int x=0;x < w;x++) {
	
			int tile = 0;

			try {
				// get tile from file content
				char c = content[y + 2].charAt(x);
				switch (c) {
					case '*': tile = 10;break;  // this is player position
					case 'B': tile = 100;break;  // walking
					case 'C': tile = 101;break;  // flying
					case 'D': tile = 102;break; // shooting
					case 'H': tile = 500;break; //MaxHealth
					case 'M': tile = 501;break;//MaxMana
					case '#': tile = 700;break;//door
					case 'W': tile = 1000;break; // this is spike
					case 'X': tile = 1001; break; 
					case 'Y': tile = 1002;break;
					case 'Z': tile = 1003;break;
					
					default : tile = Character.getNumericValue(c); break;
				}
			} catch (Exception e) { }

			switch (tile) {
				
				case 1: // standard tile
					levelTile="resources/level"+level+"/"+level+"tile.gif";
					Sprite groundTile = new Sprite(getImage(levelTile),x*64, y*64);
					TILE.add(groundTile);
				break;
				case 2: // turn tile
					Sprite turnTile = new Sprite(getImage("resources/transparent.gif"),x*64, y*64);
					TRANTURNTILE.add(turnTile);
				break;
				case 3:
					levelTile="resources/level"+level+"/"+level+"tile.gif";
					Sprite turnTile2 = new Sprite(getImage(levelTile),x*64, y*64);
					TURNTILE.add(turnTile2);
				break;
				case 4: 
					levelTile="resources/level"+level+"/"+level+"tile.gif";
					Sprite bridge = new Sprite(getImage(levelTile),x*64, y*64);
					BRIDGE.add(bridge);
					BRIDGE.setActive(false);
				break;
				case 5:
					levelTile ="resources/level"+level+"/"+level+"tile.gif";
					Sprite moveBlock = new Sprite(getImage(levelTile),x*64,y*64);
					moveBlock.setHorizontalSpeed(-0.2);
					MOVEBLOCK.add(moveBlock);					
				break;
				case 6: // turn block
					Sprite turnblock = new Sprite(getImage("resources/transparent.gif"),x*64, y*64);
					TURNBLOCK.add(turnblock);
				break;
				case 7 ://blink block
					Sprite winkWink = new Cage(this,x*64,y*64,level);
					BLINKBLOCK.add(winkWink);
				break;
				case 8 ://Stone
					Sprite stone = new Stone(this,x*64,y*64);
					STONE.add(stone);					
				break;
				case 10: // our position
					player.setLocation(x*64, y*64);
				break;
				case 100: //Enemy type1
					ENEMY.add(new Walk_Enemy(this,x*64,y*64,level));
				break;
				case 101:
					ENEMY.add(new Fly_Enemy(this,x*64,y*64,level));
				break;
				case 102:
					ENEMY.add(new Stand_Enemy(this,x*64,y*64,level));
				break;
				case 500:
					ITEM.add(new Item(this,x*64,y*64,5));
				break;
				case 501:
					ITEM.add(new Item(this,x*64,y*64,6));
				break;
				case 700:
					WARP.add(new Warp(this,x*64,y*64));
				break;
				case 1000:
					SPIKE.add(new Spike(this,x*64,y*64,4));
				break;
				case 1001: // objective!
					SPIKE.add(new Spike(this,x*64,y*64,1));
				break;
				case 1002:
					SPIKE.add(new Spike(this,x*64,y*64,3));
				break;
				case 1003:
					SPIKE.add(new Spike(this,x*64,y*64,2));
				break;
				default:
				break;
				
			}
		}
	    }
//		Insert Player
		PLAYER.add(player);
		//backgr.setToCenter(player);
		//Reset player state
		player.resetState();
		
		playfield.update(0);
		
		//setMaskColor(new Color(255, 255, 255));
		
		///////////// setting collision check /////////////
		//between player and tile
	    playfield.addCollisionGroup(PLAYER, TILE,new PlayerTileCollision());
	    playfield.addCollisionGroup(PLAYER, TURNTILE,new PlayerTileCollision());
	    playfield.addCollisionGroup(PLAYER, BLINKBLOCK,new PlayerBlinkblockCollision());
	    playfield.addCollisionGroup(PLAYER, STONE,new PlayerTileCollision());
		//MoveBlock
	    playfield.addCollisionGroup(PLAYER, MOVEBLOCK, new PlayerMoveblockCollision());
	    playfield.addCollisionGroup(MOVEBLOCK , TURNTILE,new MoveblockTurntileCollision());
	    playfield.addCollisionGroup(MOVEBLOCK , TURNBLOCK,new MoveblockTurntileCollision());
	    //between walkenemy and tile
	    playfield.addCollisionGroup(ENEMY,TILE,new EnemyTileCollision());
	    //between bullet and walkenemy
	    playfield.addCollisionGroup(BULLET,ENEMY,new BulletEnemyCollision(this));
	    playfield.addCollisionGroup(BULLET,STONE,new BulletStoneCollision(this));
	    //between player and enemy
	    playfield.addCollisionGroup(PLAYER,ENEMY,new playerEnemyCollision(this));
	    //between enemyBullet and player
	    playfield.addCollisionGroup(ENEMYBULLET,PLAYER,new EnemybulletPlayerCollision());
	    //between player and item
	    playfield.addCollisionGroup(PLAYER,ITEM,new PlayerItemCollision());
	    //between spike and tile
	    playfield.addCollisionGroup(SPIKE,TILE,new EnemyTileCollision());
	    //between player and bridge
	    playfield.addCollisionGroup(PLAYER, BRIDGE,new PlayerTileCollision());
	    //between player and spike
	    playfield.addCollisionGroup(PLAYER, SPIKE,new PlayerSpikeCollision());
	    //between bullet and tile
	    playfield.addCollisionGroup(BULLET, TILE,new BulletTileCollision());
	    playfield.addCollisionGroup(BULLET, SPIKE,new BulletTileCollision());
	    playfield.addCollisionGroup(BULLET, BLINKBLOCK,new BulletTileCollision());
	    playfield.addCollisionGroup(BULLET, TURNTILE,new BulletTileCollision());
	    playfield.addCollisionGroup(ENEMYBULLET, TILE,new BulletTileCollision());
	    //between enemy and turntile
	    playfield.addCollisionGroup(ENEMY,TURNTILE,new EnemyTurntileCollision());
	    playfield.addCollisionGroup(ENEMY,TRANTURNTILE,new EnemyTurntileCollision());
	    playfield.addCollisionGroup(ENEMY,SPIKE,new EnemyTurntileCollision());
	
	    //Change game
	    playfield.addCollisionGroup(PLAYER,WARP,new PlayerWarpCollision(this));
	    //Set Framerate
		System.out.println("MAX HP : "+player.getMaxHealth()+" Level :"+level);
		System.out.println("MAX MP : "+player.getMaxMana()+" Level :"+level);
	    
	    setFPS(100);
		}
	

	public void update(long elapsedTime) {
		//Set our player as the center
		
		
		if (blink.action(elapsedTime)){
			if(BLINKBLOCK.isActive()) BLINKBLOCK.setActive(false);
			else BLINKBLOCK.setActive(true);
			}
		if(ENEMY.getActiveSprite()==null)BRIDGE.setActive(true);
		backgr.setToCenter(player);
		//System.out.println(ENEMY.getActiveSprite());
		playfield.update(elapsedTime);
	
		if ((player.getX() > backgr.getWidth() ||
				player.getY() > backgr.getHeight() ||
				player.getX()+player.getWidth() < 0 ||
				player.getY()+player.getHeight() < 0)) {
				// if panda out of background dimension
				// die die die !!!
				if(player.deadFlag==false) player.dead();
			}
		
		// this is where the game change mode
		// set parent nextGameID = 1
		// set all attributes that are important by Setters
		// this is example
////////////////EXAMPLE
		if(keyPressed(KeyEvent.VK_ESCAPE))
		{
			parent.nextGameID = LostSymphony.GAMEID_BEAT;
			((LostSymphony)parent).level++;
			
			//bsMusic.stop(0);
			music.requestStop();
			
			
			((LostSymphony) parent).setHealth(player.getHealth());
			((LostSymphony) parent).setMana(player.getMana());
			((LostSymphony) parent).setMaxHealth(player.getMaxHealth());
			((LostSymphony) parent).setMaxMana(player.getMaxMana());
			((LostSymphony) parent).setScore(player.getScore());
			((LostSymphony) parent).setCont(this.getCont());
			finish();
		}
		if(keyPressed(KeyEvent.VK_Q))
		{
			parent.nextGameID = LostSymphony.GAMEID_MENUPAGE;
			music.requestStop();
			finish();
		}
////////////////DEAD
		if(player.deadFlag==true){

			music.requestStop();
			if(keyPressed(KeyEvent.VK_SPACE)){
				if(this.getCont()>0){
					((LostSymphony)parent).setScore(player.getScore());
					((LostSymphony)parent).setCont(((LostSymphony)parent).getCont()-1);
					this.initResources();
				}
				else {
					parent.nextGameID=0;
					finish();
				}
			}
		}
		
	}

	public void render(Graphics2D g) {
		//setMaskColor(Color.WHITE);
        playfield.render(g);
		g.setFont(font);
	  
	 
	    g.drawImage(head,null,34,10);

		g.drawImage(green,null,24,102);
		remainingHealth = 95-(player.getHealth()*94)/player.getMaxHealth();
		if(player.deadFlag||remainingHealth>=95)remainingHealth=94;
		g.drawImage(gold.getSubimage(gold.getMinX(),gold.getMinY(),88,remainingHealth),null,24,102);
	   
	    g.drawImage(full,null,102,106);
	    remainingMana = 119-(player.getMana()*118)/player.getMaxMana();
	    if(player.getMana()==0||remainingMana>=119)remainingMana=118;
	    g.drawImage(empty.getSubimage(empty.getMinX(),empty.getMinY(),40,remainingMana),null,102,106);
	    
	  
	    g.drawImage(leftHand,null,16,137);
	    g.drawImage(rightHand,null,117,137);
	
	    font1.drawString(g,"SCORE : "+ player.getScore(),160,30);
	    font1.drawString(g,"CONTINUE: " + this.getCont(),160,60);
	    if(player.deadFlag==true){
	    	if(this.getCont()>0){
	    	//	g.setColor(new Color(255,0,162));
	    	//	g.setFont(new Font("Comic Sans MS",1,40));
	    	//	g.drawString("Please press spacebar to continue...",180,550);
	    		g.drawImage(getImage("scr_resources/youreDead.gif"),null,324,320);
	    	}
	    	else {
	    		//.setColor(new Color(255,0,162));
	    		//g.setFont(new Font("Comic Sans MS",1,40));
	    		//g.drawString("GAME OVER",180,500);
	    		g.drawImage(getImage("scr_resources/gameOver.gif"),null,324,320);
	    		//g.drawString("Press spacebar",180,550);
	    	}
	    }
	   
	}

	public Player getPlayer() {
		return player;
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