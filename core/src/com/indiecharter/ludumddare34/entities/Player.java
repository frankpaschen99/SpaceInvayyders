package com.indiecharter.ludumddare34.entities;

import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.indiecharter.ludumddare34.handler.Handler;

public class Player extends Entity{
	
	boolean isFlipped = false;
	LinkedList<Handler> Collisions = new LinkedList<Handler>();
	
	public int score;
	
	public Player(float x, float y){
		this.x = x;
		this.y = y;
		Texture playerTexture = new Texture("sprite_hero.png");
		sprite = new Sprite(playerTexture);
		sprite.setPosition(x, y);
		sprite.setOrigin(0, 0);
		sprite.setSize(sprite.getWidth() * 2, sprite.getHeight() * 2);
	}
	
	public void addCollisionsGroup(Handler handler){
		Collisions.add(handler);
	}
	
	@Override
	public void update(float delta) {
		sprite.setPosition(x, y);
		float speed = 500 * delta;
		if(Gdx.input.isKeyPressed(Keys.A)){
			x -= speed;
			if(!isFlipped){
				sprite.setFlip(true, false);
				this.isFlipped = true;
			}
		}
		if(Gdx.input.isKeyPressed(Keys.D)){
			x += speed;
			if(isFlipped){
				sprite.setFlip(false, false);
				this.isFlipped = false;
			}
		}
		
		checkCollision();
	}
	
	public void checkCollision(){
		for(Handler h: Collisions){
			for(Entity e: h.getEntities()){
				if(e.isTrash) continue;
				
				Rectangle playerRect = new Rectangle();
				playerRect.set(this.x, this.y, this.sprite.getWidth(), this.sprite.getHeight());
				
				
				Rectangle eRect = new Rectangle();
				eRect.set(e.x, e.y, e.sprite.getWidth(), e.sprite.getHeight());

				System.out.println("Score: " + score);
				
				if(playerRect.overlaps(eRect)){
					e.isTrash = true;
					score++;
					System.out.println("Score: " + score);
				}
			}
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		sprite.draw(batch);
		
	}
	

	@Override
	public void dipose() {
		
	}
}
