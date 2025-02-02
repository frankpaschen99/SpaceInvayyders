package com.indiecharter.ludumddare34.handler;

import java.util.LinkedList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.indiecharter.ludumddare34.entities.Entity;

public class Handler {
	LinkedList<Entity> entitiesU = new LinkedList<Entity>();
	LinkedList<Entity> entitiesR = new LinkedList<Entity>();
	LinkedList<Entity> bufferedEntities = new LinkedList<Entity>();
	LinkedList<Entity> bufferedEntitiesU = new LinkedList<Entity>();
	LinkedList<Entity> debufferedEntities = new LinkedList<Entity>();
	
	public void update(float delta){
		
		for(Entity e: bufferedEntitiesU){
			entitiesU.add(e);
		}
		
		for(Entity e: debufferedEntities){
			e.dipose();
			entitiesU.remove(e);
			entitiesR.remove(e);
		}
		bufferedEntitiesU.clear();
		debufferedEntities.clear();
		
		for(Entity e: entitiesU){
			if(e.isTrash) {
				debufferedEntities.add(e);
				continue;
			}
			if(e.isAwake) e.update(delta);
		}
	}
	
	public void render(SpriteBatch batch){
		for(Entity e: bufferedEntities){
			entitiesR.add(e);
		}
		
		bufferedEntities.clear();
		
		for(Entity e: entitiesR){
			if(e.isTrash) {
				debufferedEntities.add(e);
				continue;
			}
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
	
	public LinkedList<Entity> getEntities(){
		return this.entitiesR;
	}
	
	public void translate(float x, float y){
		for(Entity e: entitiesU){
			System.out.println(e.y + y);
			e.x += x;
			e.y += y;
		}
	}
	
	public void checkCollision(Handler handler){
		for(Entity e: this.entitiesR){
			for(Entity n: handler.entitiesR){
				Rectangle eRect = new Rectangle(e.x, e.y, e.sprite.getWidth(), e.sprite.getHeight());
				Rectangle nRect = new Rectangle(n.x, n.y, n.sprite.getWidth(), n.sprite.getHeight());
				if(eRect.overlaps(nRect)){
					e.collidedWith(n);
					n.collidedWith(e);
				}
			}
		}
	}
}
