package com.indiecharter.ludumddare34.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FallingObject extends Entity{
	
	public FallingObject(float x, float y, Texture texture){
		this.x = x;
		this.y = y;
		
		sprite = new Sprite(texture);
		sprite.setPosition(x, y);
		sprite.setOrigin(0, 0);
		sprite.setSize(sprite.getWidth() * 2, sprite.getHeight() * 2);
	}
	
	@Override
	public void update(float delta) {
		sprite.setPosition(x, y);
		float speed = delta * 250;
		this.y -= speed;
		System.out.println("Yo");
		if(this.y < 0 - sprite.getHeight()){
			System.out.println("TOUCHED GROUND");
			this.isTrash = true;
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
