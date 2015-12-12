package com.indiecharter.ludumddare34.entities;

public abstract class Entity {
	
	public boolean isAwake = true;
	public boolean doesUpdate = true;
	
	
	public abstract void update();
	public abstract void render();
}
