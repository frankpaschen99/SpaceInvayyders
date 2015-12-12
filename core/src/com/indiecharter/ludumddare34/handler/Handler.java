package com.indiecharter.ludumddare34.handler;

import java.util.LinkedList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.indiecharter.ludumddare34.entities.Entity;

public class Handler {
	LinkedList<Entity> entitiesU = new LinkedList<Entity>();
	LinkedList<Entity> entitiesR = new LinkedList<Entity>();
	LinkedList<Entity> bufferedEntities = new LinkedList<Entity>();
	LinkedList<Entity> bufferedEntitiesU = new LinkedList<Entity>();
	LinkedList<Entity> debufferedEntities = new LinkedList<Entity>();
	
	public void update(){
		for(Entity e: bufferedEntitiesU){
			entitiesU.add(e);
		}
		
		for(Entity e: debufferedEntities){
			entitiesU.remove(e);
			entitiesR.remove(e);
		}
		
		for(Entity e: entitiesU){
			if(e.isAwake) e.update();;
		}
	}
	
	public void render(SpriteBatch batch){
		for(Entity e: bufferedEntities){
			entitiesR.add(e);
		}
		
		for(Entity e: entitiesR){
			e.render(batch);
		}
	}
	
	public void addEntity(Entity e){
		bufferedEntities.add(e);
		if(e.doesUpdate){
			bufferedEntitiesU.add(e);
		}
	}
	
	public void removeEntity(Entity e){
		debufferedEntities.add(e);
	}
}
