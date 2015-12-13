package com.indiecharter.ludumddare34.entities;

import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.indiecharter.ludumddare34.ID;
import com.indiecharter.ludumddare34.PlayerUpgrades;
import com.indiecharter.ludumddare34.gui.ProgressBar;
import com.indiecharter.ludumddare34.handler.Handler;

public class Player extends Entity {

	boolean isFlipped = false;
	LinkedList<Handler> Collisions = new LinkedList<Handler>();

	public float HP = 100;

	public int missileLevel;

	public int score;

	LinkedList<PlayerUpgrades> currentUpgrades;

	ProgressBar pb;

	Sprite missileLevel1, missileLevel2, missileLevel3;

	float originalSpriteWidth, originalSpriteHeight;

	public Player(float x, float y) {
		this.id = ID.player;
		this.x = x;
		this.y = y;
		Texture playerTexture = new Texture("space_ship.png");
		sprite = new Sprite(playerTexture);
		sprite.setPosition(x, y);
		sprite.setOrigin(0, 0);
		originalSpriteWidth = sprite.getWidth();
		originalSpriteHeight = sprite.getHeight();
		sprite.setSize(sprite.getWidth(), sprite.getHeight());
		pb = new ProgressBar(false, HP, HP, this.x + this.sprite.getWidth() / 2 - 50,
				this.y + 12 + this.sprite.getHeight());
		pb.setColor(Color.GREEN);
		
		missileLevel = 0;
		
		missileLevel1 = new Sprite(new Texture("upgrades/missilesLevel1.png"));
		missileLevel2 = new Sprite(new Texture("upgrades/missilesLevel2.png"));
		missileLevel3 = new Sprite(new Texture("upgrades/missilesLevel3.png"));

		currentUpgrades = new LinkedList<PlayerUpgrades>();
	}

	int lastWarp = 0;

	public boolean specialJohnCena = false;
	public long specialTimer;
	public boolean ded = false;

	@Override

	public void update(float delta) {
		pb.value = this.HP;

		pb.setPosition(this.x + this.sprite.getWidth() / 2 - pb.sprite.getWidth() / 2,
				this.y + pb.sprite.getHeight() + 2 + this.sprite.getHeight());
		sprite.setSize(originalSpriteWidth + (score * 2 * originalSpriteWidth / 100),
				originalSpriteHeight + (score * 2 * originalSpriteHeight / 100));
		sprite.setPosition(x, y);
		float speed = 500 * delta;
		if (Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT)) {

			if (Gdx.input.isKeyJustPressed(Keys.SPACE) && lastWarp + 1000 < System.currentTimeMillis()) {
				// x -= speed * 20;
			} else {
				if (x < -sprite.getWidth() / 2) {

				} else {
					x -= speed;
				}
				if (!isFlipped) {
					sprite.setFlip(true, false);
					this.isFlipped = true;
				}
			}

		}

		if (Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT)) {
			if (Gdx.input.isKeyJustPressed(Keys.SPACE) && lastWarp + 1000 < System.currentTimeMillis()) {
				// x += speed * 20;
			} else {
				if (x > Gdx.graphics.getWidth() - sprite.getWidth() / 2) {

				} else {
					x += speed;
				}
				if (isFlipped) {
					sprite.setFlip(false, false);
					this.isFlipped = false;
				}
			}
		}

		if (currentUpgrades.contains(PlayerUpgrades.MissilesLevel3)) {

			missileLevel = 3;
		} else if (currentUpgrades.contains(PlayerUpgrades.MissilesLevel2)) {

			missileLevel = 2;
		} else if (currentUpgrades.contains(PlayerUpgrades.MissilesLevel1)) {

			missileLevel = 1;
		}

		if (Gdx.input.isKeyJustPressed(Keys.J) && this.specialTimer + 10000 < System.currentTimeMillis()) {
			this.specialJohnCena = true;
			this.specialTimer = System.currentTimeMillis();
		}
		if (Gdx.input.isKeyJustPressed(Keys.SPACE) || Gdx.input.isKeyJustPressed(Keys.UP)) {
			shoot = true;
		}

		if (this.HP <= 0) {
			ded = true;
		}

	}

	@Override
	public void render(SpriteBatch batch) {
		if (currentUpgrades.contains(PlayerUpgrades.MissilesLevel3)) {
			this.missileLevel3.draw(batch);
			this.missileLevel2.draw(batch);
			this.missileLevel1.draw(batch);
			missileLevel = 3;
		} else if (currentUpgrades.contains(PlayerUpgrades.MissilesLevel2)) {
			this.missileLevel2.draw(batch);
			this.missileLevel1.draw(batch);
			missileLevel = 2;
		} else if (currentUpgrades.contains(PlayerUpgrades.MissilesLevel1)) {
			this.missileLevel1.draw(batch);
			missileLevel = 1;
		}
		sprite.draw(batch);
		pb.render(batch);

	}

	@Override
	public void dipose() {

	}

	@Override
	public void collidedWith(Entity e) {
		if (e.id == ID.bullet) {
			System.out.println("HPP: " + this.HP);
			this.HP -= e.attackDamage;
		}

	}

	public void addUpgrade(PlayerUpgrades upgradeTo) {
		currentUpgrades.add(upgradeTo);
	}
}
