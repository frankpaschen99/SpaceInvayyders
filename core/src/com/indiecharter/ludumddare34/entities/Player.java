package com.indiecharter.ludumddare34.entities;

import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.indiecharter.ludumddare34.handler.Handler;
import com.indiecharter.ludumddare34.utilities.Constants;

public class Player extends Entity{
	
	boolean isFlipped = false;
	LinkedList<Handler> Collisions = new LinkedList<Handler>();
	
	public int score;
	
	float originalSpriteWidth, originalSpriteHeight;
	
	public Player(float x, float y){
		this.x = x;
		this.y = y;
		Texture playerTexture = new Texture("space_ship.png");
		sprite = new Sprite(playerTexture);
		sprite.setPosition(x, y);
		sprite.setOrigin(0, 0);
		originalSpriteWidth = sprite.getWidth();
		originalSpriteHeight = sprite.getHeight();
		sprite.setSize(sprite.getWidth(), sprite.getHeight());
	}
	
	
	int lastWarp = 0;
	
	@Override
	public void update(float delta) {
		sprite.setSize(originalSpriteWidth + (score * 2 * originalSpriteWidth / 100), originalSpriteHeight + (score * 2 * originalSpriteHeight / 100));
		sprite.setPosition(x, y);
		float speed = 500 * delta;
		if(Gdx.input.isKeyPressed(Keys.A)){

			if(Gdx.input.isKeyJustPressed(Keys.SPACE) && lastWarp + 1000 < System.currentTimeMillis()){
				x -= speed * 20;
			}else{
				x -= speed;
				if(!isFlipped){
					sprite.setFlip(true, false);
					this.isFlipped = true;
				}
			}
			
		}
		if(Gdx.input.isKeyPressed(Keys.D)){
			if(Gdx.input.isKeyJustPressed(Keys.SPACE)  && lastWarp + 1000 < System.currentTimeMillis()){
				x += speed * 20;
			}else{
				x += speed;
				if(isFlipped){
					sprite.setFlip(false, false);
					this.isFlipped = false;
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

	@Override
	public void collidedWith(Entity e) {
		// TODO Auto-generated method stub
		
	}
}
