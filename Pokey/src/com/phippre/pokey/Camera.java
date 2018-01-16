package com.phippre.pokey;

public class Camera {
	
	float x, y;
	
	public Camera(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void tick(Player player) {
		x = player.getX() - Main.WIDTH / 4 + 16;
		y = player.getY() - Main.HEIGHT / 4 + 16;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}

}
