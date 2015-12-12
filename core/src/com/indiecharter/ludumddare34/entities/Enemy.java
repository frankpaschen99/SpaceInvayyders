package com.indiecharter.ludumddare34.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.indiecharter.ludumddare34.Directions;
import com.indiecharter.ludumddare34.ID;

public class Enemy extends Entity{
	
	float HP;	
	
	Directions direction;
	
	float speed = 200;
	
	public Enemy(float HP, float x, float y, Sprite sprite){
		this.HP = HP;
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		direction = Directions.left;
		this.id = ID.enemy;
	}
	
	@Override
	public void update(float delta) {
		if(this.direction == Directions.left){
			if(this.x < 0){
				this.direction = Directions.right;
			}else{
				x -= 50 * delta;
			}
		}else{
			if(this.x > Gdx.graphics.getWidth() - sprite.getWidth()){
				this.direction = Directions.left;
			}else{
				x += 50 * delta;
			}
		}
		sprite.setPosition(x, y);  
		
		if(this.HP <= 0){
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
		if(e.id == ID.bullet){
			HP -= e.attackDamage;
		}
	}

}
