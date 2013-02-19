package staticScreen;

import LostSymphony.LostSymphony;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.gui.toolkit.FrameWork;

public abstract class elxGameObject extends GameObject {

	public FrameWork 	frame;	// gui framework
	public LostSymphony father;

	public elxGameObject(GameEngine ge) {
		super(ge);
		father=(LostSymphony)ge;
	}
	

}
