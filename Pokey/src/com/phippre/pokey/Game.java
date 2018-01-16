package com.phippre.pokey;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends BasicGameState {
	
	Map map = new Map();
	Camera cam;
	Player player;

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		map.init();
		cam = new Camera(0, 0);
		player = new Player(50, 50, 22, 32);
		player.init();
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.scale(2.0f, 2.0f);
		g.translate(-cam.getX(), -cam.getY());
			map.render();
			player.render(g);
		g.translate(cam.getX(), cam.getY());
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		cam.tick(player);
		player.update(gc, delta, map);
	}

	public int getID() {
		return 1;
	}

}
