package com.indiecharter.ludumddare34.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background extends Entity{
	Texture backgroundTexture;
	
	public Background(){
		backgroundTexture = new Texture("Space_Background.png");
		sprite = new Sprite(backgroundTexture);
		
	}
	@Override
	public void update(float delta) {
		
	}

	@Override
	public void render(SpriteBatch batch) {
		sprite.draw(batch);
		
	}

	@Override
	public void dipose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collidedWith(Entity e) {
		// TODO Auto-generated method stub
		
	}

}
