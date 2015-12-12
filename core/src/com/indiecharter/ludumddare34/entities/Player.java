package com.indiecharter.ludumddare34.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends Entity{
	Sprite player;
	public Player(float x, float y){
		this.x = x;
		this.y = y;
		Texture playerTexture = new Texture("sprite_hero.png");
		player = new Sprite(playerTexture);
		player.setPosition(x, y);
		player.setOrigin(0, 0);
	}
	
	@Override
	public void update(float delta) {
		player.setPosition(x, y);
		float speed = 500 * delta;
		System.out.println(speed);
		if(Gdx.input.isKeyPressed(Keys.A)) x -= speed;
		if(Gdx.input.isKeyPressed(Keys.D)) x += speed;
	}

	@Override
	public void render(SpriteBatch batch) {
		player.draw(batch);
	}

}
