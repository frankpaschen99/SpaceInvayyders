package com.indiecharter.ludumddare34.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends Entity{
	Sprite player;
	public Player(int x, int y){
		this.x = x;
		this.y = y;
		Texture playerTexture = new Texture("sprite_hero.png");
		player = new Sprite(playerTexture);
		player.setPosition(x, y);
		player.setOrigin(0, 0);
	}
	
	@Override
	public void update() {
		player.setPosition(x, y);
		
		if(Gdx.input.isKeyPressed(Keys.A)) x--;
		if(Gdx.input.isKeyPressed(Keys.D)) x++;
	}

	@Override
	public void render(SpriteBatch batch) {
		player.draw(batch);
	}

}
