package com.phippre.pokey;

import java.awt.Rectangle;
import java.util.LinkedList;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Map {
	
	LinkedList<Rectangle> tiles = new LinkedList<>();
	
	TiledMap map;
	
	public static int TILEWIDTH = 32;
	public static int TILEHEIGHT = 32;
	
	public void init() throws SlickException {
		map = new TiledMap("res/map.tmx");
		assignTiles();
	}
	
	public void render() throws SlickException {
		map.render(0, 0);
	}
	
	public void assignTiles() {
		int objectLayer = map.getLayerIndex("objectLayer");
		for (int x = 0; x < map.getWidth(); x++) {
			for (int y = 0; y < map.getHeight(); y++) {
				if (map.getTileId(x, y, objectLayer) != 0){
					tiles.add(new Rectangle(x * TILEWIDTH, y * TILEHEIGHT, TILEWIDTH, TILEHEIGHT));
				}
			}
		}
	}

}
