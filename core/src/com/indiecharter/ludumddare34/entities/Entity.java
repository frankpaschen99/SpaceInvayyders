package com.indiecharter.ludumddare34.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Entity {
	
	public int x, y;
	
	public boolean isAwake = true;
	public boolean doesUpdate = true;
	
	
	public abstract void update();
	public abstract void render(SpriteBatch batch);
}
