package bossStage;

public class BossController extends Thread {
	
	private TheGame game;
	private Boss boss;
	private NoteFlow noteFlow;
	private Hero hero;
	private BattleField field;
	int counter = 0;
	private boolean stop = false;
	private int level;
	
	public BossController(int level)
	{
		this.level = level;
	}
	
	public void run()
	{
		while(true){
			
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(stop)
				break;
			
			aiControl(level);
			
		}
	}
	
	public void setGame(TheGame game)
	{
		this.game = game;
	}
	
	public void initResources()
	{
		this.boss = game.getBoss();
		this.hero = game.getHero();
		this.field = game.getBattle();
		this.noteFlow = game.getNoteFlow();
	}
	
	public void requestStop()
	{
		stop = true;
	}
	
	public void aiControl(int level)
	{
		switch(level)
		{
		case 1: 
		{
			counter++;
			if(counter % 15 == 0){
				int random = (int)(Math.random()* 4);
				boss.move(random);
			}
			
			if(counter % 20 == 18)
			{
				game.setWarning(true);
			}
			
			if(counter % 20 == 0)
			{
				game.setWarning(false);
				boss.attack(0,boss.getCombo() * 10/5);
			}
			
			if(counter == 60) counter = 0;
			
			break;
		}
		case 2:
		{
			counter++;
			
			if(counter % 16 == 14)
			{
				game.setWarning(true);
				boss.setWeapon(1);
			}
			
			if(counter % 16 == 14)
			{
				game.setWarning(false);
				boss.attack(1,boss.getCombo() * 20/5);
			}
			
			if(counter % 16 == 8)
			{
				game.setWarning(true);
				boss.setWeapon(0);
			}
			
			if(counter % 16 == 10)
			{
				game.setWarning(false);
				boss.attack(0,boss.getCombo() *16/5);
			}
			
			if(counter == 32) counter = 0;
			
			break;
		}
		case 3:
		{
			counter++;
			if(counter % 12 == 0){
				int random = (int)(Math.random()* 2);
				boss.move(random);
			}
			
			if(counter % 10 == 8)
			{
				game.setWarning(true);
				boss.setWeapon(2);
			}
			
			if(counter % 10 == 0)
			{
				game.setWarning(false);
				boss.attack(2,boss.getCombo() * 20/5);
			}
			
			if(counter == 60) counter = 0;
			
			break;
		}
		case 4:
		{
			counter++;
			if(counter % 8 == 0){
				if(boss.getColumn() == 4)
					boss.move(2);
				else{
					int random = (int)(Math.random()* 2);
					boss.move(random);
				}
			}
			
			if(counter % 8 == 6)
			{
				game.setWarning(true);
			}
			
			if(counter % 8 == 0)
			{
				game.setWarning(false);
				boss.setWeapon(3);
				boss.attack(3,boss.getCombo() * 20/5);
			}
			
			if(counter == 16) counter = 0;
			
			break;
		}
		case 5:
		{
			counter++;
			if(counter % 6 == 0){
				if(boss.getColumn() == 4)
					boss.move(2);
				else{
					int random = (int)(Math.random()* 2);
					boss.move(random);
				}
			}
			
			if(counter % 6 == 4)
			{
				game.setWarning(true);
			}
			
			if(counter % 6 == 0)
			{
				game.setWarning(false);
				boss.setWeapon(4);
				boss.attack(4,boss.getCombo() * 20/5);
			}
			
			if(counter == 12) counter = 0;
			
			break;
		}
		default:
		{
			counter++;
			if(counter % 3 == 0){
					int random = (int)(Math.random()* 4);
					boss.move(random);
				}
			}
			
			if(counter % 6 == 4)
			{
				game.setWarning(true);
				
			}
			
			if(counter % 6 == 0)
			{
				game.setWarning(false);
				if(boss.getColumn() == 3)
				{
					if(hero.getRow() == boss.getRow()){
						boss.setWeapon(4);
						boss.attack(4,boss.getCombo() * 25/5);
					}
					else
					 if(hero.getColumn() == 0 ){
						 if(hero.getRow() == 1)
						 boss.setWeapon(1);
						 else 
						 boss.setWeapon(2);
						 boss.attack(1,boss.getCombo() * 25/5);
					 }
					 else {
						 boss.setWeapon(3);
						 boss.attack(3,boss.getCombo() * 25/5);
					 
					 }
				}
				else{
					if(hero.getRow() == boss.getRow()){
						boss.setWeapon(0);
						boss.attack(0,boss.getCombo() * 25/5);
					}
					else
						if(hero.getRow() == 1){
							boss.setWeapon(2);
							boss.attack(2,boss.getCombo() * 25/5);
						}
						else{
							boss.setWeapon(1);
							boss.attack(1,boss.getCombo() * 25/5);
						}
							
				}
				game.setWarning(false);
			}
			
			if(counter == 12) counter = 0;
			
			break;
		}
		
		}
	}

