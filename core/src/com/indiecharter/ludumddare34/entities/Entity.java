package com.indiecharter.ludumddare34.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Entity {
	
	public float x, y;
	
	public boolean isAwake = true;
	public boolean doesUpdate = true;
	
	
	public abstract void update(float delta);
	public abstract void render(SpriteBatch batch);
}
