package com.indiecharter.ludumddare34.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.indiecharter.ludumddare34.ID;

public class Enemy extends Entity{
	float HP;
	public Enemy(float HP, float x, float y, Sprite sprite){
		this.HP = HP;
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	@Override
	public void update(float delta) {
		
	}

	@Override
	public void render(SpriteBatch batch) {
		
	}

	@Override
	public void dipose() {
		
	}

	@Override
	public void collidedWith(Entity e) {
		if(e.id == ID.bullet){
			HP -= e.attackDamage;
		}
	}

}
