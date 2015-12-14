package com.indiecharter.ludumddare34.entities;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.indiecharter.ludumddare34.Directions;
import com.indiecharter.ludumddare34.ID;
import com.indiecharter.ludumddare34.gui.ProgressBar;
import com.indiecharter.ludumddare34.utilities.Constants;

public class Enemy extends Entity{
	
	float HP;	
	
	Directions direction;
	
	float speed = 200;
	
	ProgressBar pb;
	
	Random random;
	
	public Enemy(float HP, float x, float y, Sprite sprite){
		this.HP = HP;
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		direction = Directions.left;
		this.id = ID.enemy;
		pb = new ProgressBar(false, HP, HP, this.x + this.sprite.getWidth()/2 - (100 /2), this.y );
		random = new Random();
	}
	
	public void setColor(float color){
		sprite.setColor(color);
	}
	
	@Override
	public void update(float delta) {
		
		if(random.nextInt(400) <= Constants.scores / 10 + 1){
			shoot = true;
		}
		
		if(this.direction == Directions.left){
			if(this.x < 0){
				this.direction = Directions.right;
			}else{
				x -= 100 * delta;
			}
		}else{
			if(this.x > Gdx.graphics.getWidth() - sprite.getWidth()){
				this.direction = Directions.left;
			}else{
				x += 100 * delta;
			}
		}
		
		sprite.setPosition(x, y);  
		
		pb.setPosition(this.x + this.sprite.getWidth()/2 - (pb.sprite.getWidth() /2), this.y - pb.sprite.getHeight() - 2);
		
		if(this.HP <= 0){
			Sound sound = Gdx.audio.newSound(Gdx.files.internal("Explosion.wav"));
			sound.play(0.24f);
			Constants.scores++;
			Constants.totalScore++;
			this.isTrash = true;
			Constants.memePoints += random.nextInt(9);
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		sprite.draw(batch);
		pb.render(batch);
	}

	@Override
	public void dipose() {
		
	}

	@Override
	public void collidedWith(Entity e) {
		if(e.id == ID.bullet){
			HP -= e.attackDamage;
			pb.value = HP;
		}
	}

}
