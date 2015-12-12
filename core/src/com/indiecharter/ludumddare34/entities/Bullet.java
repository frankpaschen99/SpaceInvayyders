package com.indiecharter.ludumddare34.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.indiecharter.ludumddare34.ID;

public class Bullet extends Entity{
	
	float speed;
	
	boolean invert;
	
	public Bullet(float x, float y, float hitDamage, float speed, Sprite sprite){
		this.sprite = sprite;
		sprite.setPosition(x, y);
		this.x = x;
		this.y = y;
		this.attackDamage = hitDamage;
		this.speed = speed;
		
		this.id = ID.bullet;
	}
	
	public void setInvert(boolean invert){
		this.invert = invert;
	}
	
	@Override
	public void update(float delta) {
		if(this.invert){
			this.y -= speed * delta;
			if(this.y < 0 - this.sprite.getHeight()){
				this.isTrash = true;
			}
		}else{
			this.y += speed * delta;
			if(this.y > Gdx.graphics.getHeight()){
				this.isTrash = true;
			}
		}
		sprite.setPosition(x, y);
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
		if(e.id == ID.enemy || e.id == ID.player)
			this.isTrash = true;
	}

}
