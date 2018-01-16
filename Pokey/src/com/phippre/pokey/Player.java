package com.phippre.pokey;

import java.awt.Rectangle;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Player {

	int target = GL11.GL_TEXTURE_2D;
	//Moving right.
	private SpriteSheet MoveRight; // initate a SprtieSheet
    private Animation MoveRightAni; // initate a Animation
    //Moving left.
    private SpriteSheet MoveLeft; // initate a SprtieSheet
    private Animation MoveLeftAni; // initate a Animation
    //Moving up.
    private SpriteSheet MoveUp; // initate a SprtieSheet
    private Animation MoveUpAni; // initate a Animation
    //Moving down.
    private SpriteSheet MoveDown; // initate a SprtieSheet
    private Animation MoveDownAni; // initate a Animation
    //Facing down, not moving.
    private SpriteSheet playerStill;
    private Animation playerStillAni;
    //Facing up, not moving.
    private SpriteSheet playerUpStill;
    private Animation playerUpStillAni;
    //Facing left, not moving.
    private SpriteSheet playerLeftStill;
    private Animation playerLeftStillAni;
    //Facing right, not moving.
    private SpriteSheet playerRightStill;
    private Animation playerRightStillAni;

    private Animation currentImage;
    
    private boolean playerMoving = false;
	
	float playerX;
	float playerY;
	int width;
	int height;
	float dx, dy;
	double velX = 0.1;
	double velY = 0.1;

	public Player(float x, float y, int width, int height) {
		this.playerX = x;
		this.playerY = y;
		this.width = width;
		this.height = height;
	}
	
	public void init() throws SlickException {
		
		MoveUp = new SpriteSheet("res/spriteSheetUp.png", width, height);
		MoveUpAni = new Animation(MoveUp, 400);
		
		MoveDown = new SpriteSheet("res/spriteSheetDown.png", width, height);
		MoveDownAni = new Animation(MoveDown, 400);
		
		MoveLeft = new SpriteSheet("res/spriteSheetLeft.png", width, height);
		MoveLeftAni = new Animation(MoveLeft, 300);
		
		MoveRight = new SpriteSheet("res/spriteSheetRight.png", width, height);
		MoveRightAni = new Animation(MoveRight, 300);
		
		playerStill = new SpriteSheet("res/player.png", width, height);
		playerStillAni = new Animation(playerStill, 300);
		
		playerUpStill = new SpriteSheet("res/playerUp.png", width, height);
		playerUpStillAni = new Animation(playerUpStill, 300);
		
		playerLeftStill = new SpriteSheet("res/playerLeft.png", width, height);
		playerLeftStillAni = new Animation(playerLeftStill, 300);
		
		playerRightStill = new SpriteSheet("res/playerRight.png", width, height);
		playerRightStillAni = new Animation(playerRightStill, 300);
		
		currentImage = playerStillAni;
		
	}
	
	public void update(GameContainer gc, int delta, Map map) {
		Input input = gc.getInput();
		
		//PLAYER MOVEMENT START.
		MoveUpAni.update(delta);
		MoveDownAni.update(delta);
		playerStillAni.update(delta);
		playerUpStillAni.update(delta);
		
		playerMoving = false;

		move(input, delta, map, dx, dy, velX, velY);
		
		if (playerMoving == false) {

			if (currentImage == MoveUpAni) {
				currentImage = playerUpStillAni;
			}
			if (currentImage == MoveDownAni) {
				currentImage = playerStillAni;
			}
			if (currentImage == MoveLeftAni) {
				currentImage = playerLeftStillAni;
			}
			if (currentImage == MoveRightAni) {
				currentImage = playerRightStillAni;
			}
			
			currentImage.stop();
		}
		//PLAYER MOVEMENT END.
        
        
	}
	
	public void render(Graphics g) {
		currentImage.draw(playerX, playerY);
	}
	
	public void move(Input input, int delta, Map map, float dx, float dy, double velX, double velY) {

		dx = 0;
		dy = 0;
			
		currentImage.start();

		if (input.isKeyDown(Input.KEY_D)) {
			playerMoving = true;
			currentImage = MoveRightAni;
			dx += velX * delta;
		}

		if (input.isKeyDown(Input.KEY_A)) {
			playerMoving = true;
			currentImage = MoveLeftAni;
			dx -= velX * delta;
		}

		if (input.isKeyDown(Input.KEY_W)) {
			playerMoving = true;
			currentImage = MoveUpAni;
			dy -= velY * delta;
		}

		if (input.isKeyDown(Input.KEY_S)) {
			playerMoving = true;
			currentImage = MoveDownAni;
			dy += velY * delta;
		}
		
		if (dx != 0 && dy != 0) {
			move(input, delta, map, dx, dy, velX, 0);
			move(input, delta, map, dx, dy, 0, velY);
			return;
		}
		
		if (!collision(map, dx, dy)) {
			playerX += dx;
			playerY += dy;
		}
		
		
	}
	
	public boolean collision(Map map, float dx, float dy) {
		boolean collision = false;
		Rectangle player = new Rectangle((int) playerX, (int) playerY, width, height);
		if (dx != 0 || dy != 0) {
			
			if (dx > 0) {
				//Right
				player.x = (int) (playerX + dx);
			}
			
			if (dx < 0) {
				//Left
				player.x = (int) (playerX + dx);
			}
			
			if (dy < 0) {
				//Up
				player.y = (int) (playerY + dy);
			}
			
			if (dy > 0) {
				//Down
				player.y = (int) (playerY + dy + 1);
			}
			
			for (int i = 0; i < map.tiles.size(); i++) {
				if (player.intersects(map.tiles.get(i))) {
					collision = true;
				}
			}
		}
		return collision;
	}
	
	public float getX() {
		return playerX;
	}
	
	public float getY() {
		return playerY;
	}

}
