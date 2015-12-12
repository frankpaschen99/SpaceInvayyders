package com.indiecharter.ludumddare34.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BackgroundWallTile extends Entity{
	
	public BackgroundWallTile(){
		this.y = 600;
		this.x = 0;
		sprite = new Sprite(new Texture("Building_Layer.png"));
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
