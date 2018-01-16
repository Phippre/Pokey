package com.phippre.pokey;

import java.io.File;

import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame {
	
	public static int WIDTH = 1366;
	public static int HEIGHT = 768;

	public Main(String name) {
		super(name);
	}

	public static void main(String[] args) throws SlickException{
		System.setProperty("org.lwjgl.librarypath", new File(new File(System.getProperty("user.dir"), "native"), LWJGLUtil.getPlatformName()).getAbsolutePath());
		
		AppGameContainer app = new AppGameContainer(new Main("Pokey"));
		app.setDisplayMode(WIDTH, HEIGHT, false);
		//app.setTargetFrameRate(60);
		app.setAlwaysRender(true);
		app.start();
	}

	public void initStatesList(GameContainer gc) throws SlickException {
		addState(new Menu());
		addState(new Game());
		addState(new Saved());
	}

}
