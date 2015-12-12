package com.indiecharter.ludumddare34.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.indiecharter.ludumddare34.ID;

public class Bullet extends Entity{
	float speed;
	
	public Bullet(float x, float y, float hitDamage, float speed, Sprite sprite){
		this.sprite = sprite;
		sprite.setPosition(x, y);
		this.x = x;
		this.y = y;
		this.attackDamage = hitDamage;
		this.speed = speed;
	}
	
	@Override
	public void update(float delta) {
		this.y += speed * delta;
		sprite.setPosition(x, y);
		if(this.y > Gdx.graphics.getHeight()){
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

	@Override
	public void collidedWith(Entity e) {
		if(e.id == ID.enemy)
			this.isTrash = true;
	}

}
