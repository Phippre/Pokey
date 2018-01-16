package com.phippre.pokey;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Menu extends BasicGameState {
	
	private Image bg;
	
	private float transStart = 0.0f;
	private float transEnd = 1.0f;
	
	public int gradientBarY = 160;
	public int gradientBarX1 = 100;
	public int gradientBarX2 = 185;
	
	private String playText = "Play";
	private String saveText = "Saved"; 
	private String quitText = "Quit";
	
	private Font font = new Font("Courier New", Font.PLAIN, 48);
	TrueTypeFont ttf = new TrueTypeFont(font, true);

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		bg = new Image("res/bg.png");
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(bg, 0, 0);

		g.setColor(Color.transparent);
		
		//Play button
		g.fillRect(50, 100, 115, 60);
		ttf.drawString(50, 100, playText);
		//
		
		//Saved games button
		g.fillRect(50, 200, 145, 60);
		ttf.drawString(50, 200, saveText);
		//
		
		//Quit button
		g.fillRect(50, 300, 115, 60);
		ttf.drawString(50, 300, quitText);
		//
		
		g.drawGradientLine(35, gradientBarY, 255, 255, 255, transStart, gradientBarX1, gradientBarY, 255, 255, 255, transEnd);
		g.drawGradientLine(gradientBarX1, gradientBarY, 255, 255, 255, transEnd, gradientBarX2, gradientBarY, 255, 255, 255, transStart);
		//Saved button gradient bar y new coord is 260.
		//Saved button gradient bar x new coord is 35 to 130, 130 to 215
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		int mX = input.getMouseX();
		int mY = input.getMouseY();
		
		
		
		//MENU SELECT CODE
		if (mouseOver(mX, mY, 50, 100, 115, 60)) {
			gradientBarY = 160;
			gradientBarX1 = 100;
			gradientBarX2 = 185;
			if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				sbg.enterState(1);
			}
		} else if(gradientBarY == 160 && input.isKeyPressed(input.KEY_ENTER)) {
			sbg.enterState(1);
		}
		
		if (mouseOver(mX, mY, 50, 200, 145, 60)) {
			gradientBarY = 260;
			gradientBarX1 = 130;
			gradientBarX2 = 215;
			if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				sbg.enterState(2);
			}
		} else if(gradientBarY == 260 && input.isKeyPressed(input.KEY_ENTER)) {
			sbg.enterState(2);
		}
		
		if (mouseOver(mX, mY, 50, 300, 115, 60)) {
			gradientBarY = 360;
			gradientBarX1 = 100;
			gradientBarX2 = 185;
			if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				System.exit(0);
			}
		} else if(gradientBarY == 360 && input.isKeyPressed(input.KEY_ENTER)) {
			System.exit(0);
		}
		
		if(gradientBarY == 160 && input.isKeyPressed(input.KEY_DOWN)) {
			gradientBarY = 260;
			gradientBarX1 = 130;
			gradientBarX2 = 215;
		}
		
		if (gradientBarY == 260 && input.isKeyPressed(input.KEY_UP)) {
			gradientBarY = 160;
			gradientBarX1 = 100;
			gradientBarX2 = 185;
		} else if (gradientBarY == 260 && input.isKeyPressed(input.KEY_DOWN)) {
			gradientBarY = 360;
			gradientBarX1 = 100;
			gradientBarX2 = 185;
		}
		
		if (gradientBarY == 360 && input.isKeyPressed(input.KEY_UP)) {
			gradientBarY = 260;
			gradientBarX1 = 130;
			gradientBarX2 = 215;
		}
		//END OF MENU SELECT CODE
		
		
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			} else return false;
		} else return false;
	}

	public int getID() {
		return 0;
	}

}
