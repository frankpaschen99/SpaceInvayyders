package com.indiecharter.ludumddare34.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.indiecharter.ludumddare34.ID;

public abstract class Entity {
	
	public ID id;
	
	public float attackDamage;
	
	public float x, y;
	
	public Sprite sprite;
	
	public boolean isAwake = true;
	public boolean doesUpdate = true;
	public boolean isTrash = false;
	
	
	public abstract void update(float delta);
	public abstract void render(SpriteBatch batch);
	
	public abstract void dipose();
	
	public abstract void collidedWith(Entity e);
}
