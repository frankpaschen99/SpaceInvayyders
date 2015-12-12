package com.indiecharter.ludumddare34.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WindowWallTile extends Entity{
	public WindowWallTile(float x, float y){
		this.y = 600;
		this.x = x;
		sprite = new Sprite(new Texture("Window.png"));
		sprite.setPosition(x, y);
		sprite.setOrigin(0, 0);
	}
	
	@Override
	public void update(float delta) {
		sprite.setPosition(x, y);
		if(y < 0 - sprite.getHeight()){
			System.out.println("WALL TRASHED");
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
