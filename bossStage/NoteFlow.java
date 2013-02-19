package bossStage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class NoteFlow {
	
	
	ArrayList one,two,three,four;
	
	TheGame game;
	boolean stop = false;
	// have to choose what is better
	// if wanna go faster use array
	// if wanna use less momory use queue or list
	
	private int index;
	
	public NoteFlow()
	{
		one = new ArrayList();
		two = new ArrayList();
		three = new ArrayList();
		four = new ArrayList();

	}

	public void generateQueue()
	{
		
		BufferedReader in;
		try {
			
			in = new BufferedReader(new FileReader("resources2/note" + game.getLevel() + ".note"));

// Just read line by line and check line by line			
			String s = new String();
			while((s = in.readLine())!= null)
				switch(s.charAt(0))
				{
				case '1' : one.add(new Integer(s.substring(1))); break;
				case '2' : two.add(new Integer(s.substring(1))); break;
				case '3' : three.add(new Integer(s.substring(1))); break;
				case '4' : four.add(new Integer(s.substring(1))); break;
				}
			in.close();
//
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
	
	public void tick()
	{
		index++;
		if(one.size() > 0 && index == ((Integer)one.get(0)).intValue())
		{
			game.addQueue(0);
			one.remove(0);
		}
		if(two.size() > 0 && index == ((Integer)two.get(0)).intValue())
		{
			game.addQueue(1);
			two.remove(0);
		}
		if(three.size() > 0 && index == ((Integer)three.get(0)).intValue())
		{
			game.addQueue(2);
			three.remove(0);
		}
		if(four.size() > 0 && index == ((Integer)four.get(0)).intValue())
		{
			game.addQueue(3);
			four.remove(0);
		}
		if(one.size() == 0 && two.size() == 0 && three.size() == 0&& four.size() == 0 && !stop){
			stop = true;
			game.timeOut();
		}
			
	}
	
	public void setGame(TheGame game)
	{
		this.game = game;
	}
	
}
