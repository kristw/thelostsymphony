package bossStage;


import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class BulletPattern extends Thread{
	
	private TheGame game;
	
	private int row,column;
	
	int speed;
	
	int position;
	int type;
	int damage;
	
	boolean stop = false;
	
	LinkedList[] patternList;
	BattleField battle;
	Bullet bullet;

	private int target;
	
	public BulletPattern(int row,int column,int damage,int type,int target)
	{
		this.position = position;
		this.damage = damage;
		this.type = type;
		this.row = row;
		this.column = column;
		this.target = target;
		//System.out.println(position + " " + row + " test " + column);
		setBullet(type);
	}
	
	public void run()
	{
		int index = 0;
		int tempColumn = 0;
		int tempRow = 0;
		while(true)
		{

			try {
				sleep(450);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(stop) break;
			
			
			if(patternList.length > 0){
				
				if(index < patternList.length)
				for(int i = 0; i< patternList[index].size(); i+=2)
				{
					tempColumn = ((Integer)patternList[index].get(i)).intValue();
					tempRow = ((Integer)patternList[index].get(i+1)).intValue();
					
					if(tempRow >= 0 && tempRow <= 2 && tempColumn <= 5 && tempColumn >= 0)
						game.getBattle().setField(tempRow*6 + tempColumn,bullet);
					
				}			
				
				if(index > 0)
					for(int i = 0; i< patternList[index-1].size(); i+=2)
					{
						tempColumn = ((Integer)patternList[index-1].get(i)).intValue();
						tempRow = ((Integer)patternList[index-1].get(i+1)).intValue();
						if(tempRow >= 0 && tempRow <= 2 && tempColumn <= 5 && tempColumn >= 0)
							game.getBattle().clearField(tempRow*6 + tempColumn);
						
					}
				if(index++ == patternList.length){
					break;
				}
			}
			

		}
		
	}
	
	public void setBullet(int type)
	{
		switch(type)
		{
		case 0:
			// DIRECT ATTACK (HERO)
			patternList = new LinkedList[5];
			for(int i = 0 ; i< 5 ; i++)
			{	
				patternList[i] = new LinkedList();
				patternList[i].add(new Integer(column+i+1));
				patternList[i].add(new Integer(row));
			}
			break;
		case 1:
			// DOUBLE LINE HERO
			patternList = new LinkedList[5];
			for(int i = 0 ; i< 5 ; i++)
			{	
				patternList[i] = new LinkedList();
				patternList[i].add(new Integer(column+i+1));
				patternList[i].add(new Integer(0));
				patternList[i].add(new Integer(column+i+1));
				patternList[i].add(new Integer(2));
			}
			break;
		case 2:
			// create LinkedList of size 5 (maybe more)
			//ZIG ZAG HERO
			patternList = new LinkedList[5];
			for(int i = 0 ; i< 5 ; i++)
			{

				if(i%2 == 0){
					patternList[i] = new LinkedList();
					patternList[i].add(new Integer(column+i+1));
					patternList[i].add(new Integer(row-1));
					patternList[i].add(new Integer(column+i+1));
					patternList[i].add(new Integer(row+1));
				}
				else
				{
					patternList[i] = new LinkedList();
					patternList[i].add(new Integer(column+i+1));
					patternList[i].add(new Integer(row-2));
					patternList[i].add(new Integer(column+i+1));
					patternList[i].add(new Integer(row));
					patternList[i].add(new Integer(column+i+1));
					patternList[i].add(new Integer(row+2));
				}
			}
			break;
		case 3:
			// MELEE HERO
			patternList = new LinkedList[3];
			for(int i = 0 ; i< 3 ; i++)
			{	
				
				patternList[i] = new LinkedList();
				patternList[i].add(new Integer(column+i+1));
				patternList[i].add(new Integer(row));
				
				if(i < 2){
				patternList[i].add(new Integer(column+i+1));
				patternList[i].add(new Integer(row+1));
				patternList[i].add(new Integer(column+i+1));
				patternList[i].add(new Integer(row-1));
				}
				
				if(i < 1){
				patternList[i].add(new Integer(column+i+1));
				patternList[i].add(new Integer(row+2));
				patternList[i].add(new Integer(column+i+1));
				patternList[i].add(new Integer(row-2));
				}
			}
			break;
		case 4:
			// ZAP HERO
			patternList = new LinkedList[1];
				patternList[0] = new LinkedList();
				patternList[0].add(new Integer(column+1));
				patternList[0].add(new Integer(row));
				patternList[0].add(new Integer(column+2));
				patternList[0].add(new Integer(row));
				patternList[0].add(new Integer(column+3));
				patternList[0].add(new Integer(row));
			break;
		case 5:
			// DIRECT BOSS
			patternList = new LinkedList[5];
			for(int i = 0 ; i< 5 ; i++)
			{	
				patternList[i] = new LinkedList();
				patternList[i].add(new Integer(column-i-1));
				patternList[i].add(new Integer(row));
			}
			break;
		case 6:
			// DOUBLE LINE HERO
			patternList = new LinkedList[5];
			for(int i = 0 ; i< 5 ; i++)
			{	
				patternList[i] = new LinkedList();
				patternList[i].add(new Integer(column-i-1));
				patternList[i].add(new Integer(0));
				patternList[i].add(new Integer(column-i-1));
				patternList[i].add(new Integer(2));
			}
			break;
		case 7:	
			// ZIGZAG BOSS
			patternList = new LinkedList[5];
			for(int i = 0 ; i< 5 ; i++)
			{

				if(i%2 == 0){
					patternList[i] = new LinkedList();
					patternList[i].add(new Integer(column-i-1));
					patternList[i].add(new Integer(row-1));
					patternList[i].add(new Integer(column-i-1));
					patternList[i].add(new Integer(row+1));
				}
				else
				{
					patternList[i] = new LinkedList();
					patternList[i].add(new Integer(column-i-1));
					patternList[i].add(new Integer(row-2));
					patternList[i].add(new Integer(column-i-1));
					patternList[i].add(new Integer(row));
					patternList[i].add(new Integer(column-i-1));
					patternList[i].add(new Integer(row+2));
				}
			}
			break;
		case 8:
			// MELEE BOSS
			patternList = new LinkedList[3];
			for(int i = 0 ; i< 3 ; i++)
			{	
				patternList[i] = new LinkedList();
				patternList[i].add(new Integer(column-i-1));
				patternList[i].add(new Integer(row));
				if(i < 2){
					patternList[i].add(new Integer(column-i-1));
					patternList[i].add(new Integer(row+1));
					patternList[i].add(new Integer(column-i-1));
					patternList[i].add(new Integer(row-1));
					}
					
					if(i < 1){
					patternList[i].add(new Integer(column-i-1));
					patternList[i].add(new Integer(row+2));
					patternList[i].add(new Integer(column-i-1));
					patternList[i].add(new Integer(row-2));
					}
				
			}
			break;
		case 9:
			// ZAP BOSS
			patternList = new LinkedList[1];
				patternList[0] = new LinkedList();
				patternList[0].add(new Integer(column-1));
				patternList[0].add(new Integer(row));
				patternList[0].add(new Integer(column-2));
				patternList[0].add(new Integer(row));
				patternList[0].add(new Integer(column-3));
				patternList[0].add(new Integer(row));
			break;
		}
	}

	
	public void setGame(TheGame game)
	{
		this.game = game;
		this.bullet = new Bullet(game.getImage("resources2/ml019.png"),this.damage,this.type,this.target);
		
	}
	
	public void requestStop()
	{
		this.stop = true;
	}
	
}
