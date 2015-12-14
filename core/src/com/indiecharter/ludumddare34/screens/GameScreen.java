package com.indiecharter.ludumddare34.screens;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.indiecharter.ludumddare34.CoreGame;
import com.indiecharter.ludumddare34.ID;
import com.indiecharter.ludumddare34.entities.Background;
import com.indiecharter.ludumddare34.entities.Bullet;
import com.indiecharter.ludumddare34.entities.Enemy;
import com.indiecharter.ludumddare34.entities.Entity;
import com.indiecharter.ludumddare34.entities.FallingObject;
import com.indiecharter.ludumddare34.entities.Player;
import com.indiecharter.ludumddare34.gui.ProgressBar;
import com.indiecharter.ludumddare34.handler.Handler;
import com.indiecharter.ludumddare34.text.Text;
import com.indiecharter.ludumddare34.utilities.Constants;

public class GameScreen implements Screen {
	SpriteBatch batch;
	CoreGame game;

	Random random;

	Player player;

	Handler playerHandler;
	Handler PowerUpHandler;
	Handler playerBullets;

	Handler enemysBullets;
	Handler enemies;

	ProgressBar memeBar;

	Sound andHisNameIsJohnCena;

	Text text;

	ProgressBar pb;

	Background background;

	Texture heart;

	String centerOfScreenText;

	public GameScreen(CoreGame game) {
		text = new Text("fonts/font.fnt");
		text.setFontSize(0.32f);
		text.setColor(Color.GREEN);

		enemysBullets = new Handler();

		andHisNameIsJohnCena = Gdx.audio.newSound(Gdx.files.internal("andHisNameIsJohnCena.mp3"));

		pb = new ProgressBar(false, 10, 2, 200, 200);
		memeBar = new ProgressBar(true, Constants.memePointsNeeded, 0, 10,
				Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 2);
		memeBar.setColor(Color.PURPLE);
		memeBar.setSize(30, 300);

		heart = new Texture("asteroid.png");
		batch = new SpriteBatch();
		this.game = game;
		System.out.println("Moved to gamescreen");

		playerBullets = new Handler();

		enemies = new Handler();
		Sprite sprite = new Sprite(new Texture("ayylmao.png"));
		sprite.setSize(64, 64);
		enemies.addEntity(new Enemy(10, 300, 300, sprite));
		
		player = new Player(100, 2);
		playerHandler = new Handler();
		playerHandler.addEntity(player);
		PowerUpHandler = new Handler();
		
		random = new Random();

		background = new Background();
		centerOfScreenText = "Bitcoins: ";

		backgroundOverlay = new Sprite(new Texture("textures/foreground_blacktint.png"));

		player.x = 100;
		player.y = 2;
	}

	@Override
	public void show() {

	}

	Sprite backgroundOverlay;

	@Override
	public void render(float delta) {
		update(delta);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		background.render(batch);
		PowerUpHandler.render(batch);
		enemysBullets.render(batch);
		playerBullets.render(batch);
		enemies.render(batch);
		playerHandler.render(batch);

		if (paused) {
			backgroundOverlay.draw(batch);
		}
		text.draw(centerOfScreenText + Constants.scores, batch,
				300 - text.getStringLength(centerOfScreenText + Constants.totalScore) / 2, 700);
		memeBar.render(batch);
		batch.end();
	}

	int johnCenaTimes = 1000;
	long lastCenaShot = 0;
	long lastTime = System.currentTimeMillis();
	long firstDed = 0;

	boolean playedSadSong = false;

	long johnCenaWaitTime;

	Sound song = Gdx.audio.newSound(Gdx.files.internal("Sad Violin.mp3"));

	boolean paused = false;

	public void update(float delta) {
		
		if (player.ded) {
			if (playedSadSong == false) {
				song.play(0.4f);
				playedSadSong = true;
				firstDed = System.currentTimeMillis();
				text.setColor(Color.RED);
				text.setFontSize(0.44f);
				this.centerOfScreenText = "You died, top Bitcoins: ";
				this.paused = true;
			}

			if (firstDed + 436000 < System.currentTimeMillis()) {
				Gdx.app.exit();
			}
			return;
		}

		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE) || Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			paused = paused ? false : true;
			return;
		}

		if (paused) {
			return;
		}

		spawnEntities();

		if ((player.specialJohnCena || johnCenaTimes < 32)) {
			if (player.specialJohnCena) {
				johnCenaTimes = 0;
				andHisNameIsJohnCena.play(0.35f);
				System.out.println("JOHNN CENAA");
				johnCenaWaitTime = System.currentTimeMillis();
				player.specialJohnCena = false;
			}

			if (johnCenaWaitTime + 2100 < System.currentTimeMillis()) {

				if (this.lastCenaShot + 500 < System.currentTimeMillis()) {
					this.lastCenaShot = System.currentTimeMillis();
					this.johnCenaTimes++;
					Sprite bulletMesuare = new Sprite(new Texture("lazer.png"));
					bulletMesuare.setSize(bulletMesuare.getWidth() / 2, bulletMesuare.getHeight() / 2);
					for (int i = 0; i < Gdx.graphics.getWidth() / bulletMesuare.getWidth(); i++) {
						Sprite bullet = new Sprite(new Texture("lazer.png"));
						bullet.setSize(bullet.getWidth() / 2, bullet.getHeight() / 2);
						playerBullets.addEntity(new Bullet(i * bullet.getWidth(), 0, 5, 600, bullet, false));
					}
				}

			}

		}

		if (player.shoot) {
			playerShoot();
		}

		for (Entity e : enemies.getEntities()) {
			if (e.id == ID.enemy && e.shoot) {
				e.shoot = false;

				Sprite bullet = new Sprite(new Texture("dankmeme.png"));
				bullet.setSize(bullet.getWidth(), bullet.getHeight());
				Bullet ayy = new Bullet(e.x + (e.sprite.getWidth() / 2) - (bullet.getWidth() / 2),
						e.y + e.sprite.getHeight(), 7, 400, bullet, true);
				ayy.setInvert(true);
				enemysBullets.addEntity(ayy);
			}
		}
		
		
		if (Constants.memePoints >= Constants.memePointsNeeded) {
			if (player.missileLevel < 3) {
				player.missileLevel++;
				Constants.memePoints = 0;
				System.out.println(player.missileLevel);
				Constants.memePointsNeeded *= 2;
			} else {
				if (player.specialTimer + 20000 < System.currentTimeMillis()) {
					player.specialJohnCena = true;
					Constants.memePoints = 0;
					Constants.memePointsNeeded *= 6;
					player.specialTimer = System.currentTimeMillis();
				}
			}

		}

		pb.update();
		if (Constants.memePoints > Constants.memePointsNeeded)
			Constants.memePoints = Constants.memePointsNeeded;
		memeBar.value = Constants.memePoints;
		memeBar.totalValue = Constants.memePointsNeeded;
		memeBar.update();
		playerBullets.update(delta);
		PowerUpHandler.update(delta);
		playerHandler.update(delta);
		enemies.update(delta);
		enemysBullets.update(delta);
		enemysBullets.checkCollision(playerHandler);

		playerBullets.checkCollision(enemies);

	}

	public void playerShoot() {
		Texture texture = new Texture("lazer.png");
		Sprite bullet = new Sprite(texture);
		Sprite bullet1 = new Sprite(texture);
		Sprite bullet2 = new Sprite(texture);
		Sprite bullet3 = new Sprite(texture);
		Sprite bullet4 = new Sprite(texture);
		Sprite bullet5 = new Sprite(texture);
		Sprite bullet6 = new Sprite(texture);

		bullet.setSize(bullet.getWidth() / 2, bullet.getHeight() / 2);
		bullet1.setSize(bullet.getWidth() / 2, bullet.getHeight() / 2);
		bullet2.setSize(bullet.getWidth() / 2, bullet.getHeight() / 2);
		bullet3.setSize(bullet.getWidth() / 2, bullet.getHeight() / 2);
		bullet4.setSize(bullet.getWidth() / 2, bullet.getHeight() / 2);
		bullet5.setSize(bullet.getWidth() / 2, bullet.getHeight() / 2);
		bullet6.setSize(bullet.getWidth() / 2, bullet.getHeight() / 2);
		if (player.missileLevel == 3) {
			playerBullets.addEntity(new Bullet(player.x + (player.sprite.getWidth() / 2) - (bullet.getWidth() / 2),
					player.y + player.sprite.getHeight(), 2, 600, bullet, true));

			// Outer cannons
			playerBullets.addEntity(new Bullet(player.x + 7 - (bullet.getWidth() / 2),
					player.y + player.sprite.getHeight() - 27, Constants.playerDamage, 600, bullet1, true));
			playerBullets.addEntity(new Bullet(player.x + 54 - (bullet.getWidth() / 2),
					player.y + player.sprite.getHeight() - 27, Constants.playerDamage, 600, bullet2, true));

			// Middle Cannons
			playerBullets.addEntity(new Bullet(player.x + 15 - (bullet.getWidth() / 2),
					player.y + player.sprite.getHeight() - 14, Constants.playerDamage, 600, bullet3, true));
			playerBullets.addEntity(new Bullet(player.x + 46 - (bullet.getWidth() / 2),
					player.y + player.sprite.getHeight() - 14, Constants.playerDamage, 600, bullet4, true));

			// Inner Cannons
			playerBullets.addEntity(new Bullet(player.x + 23 - (bullet.getWidth() / 2),
					player.y + player.sprite.getHeight() - 1, Constants.playerDamage, 600, bullet5, true));
			playerBullets.addEntity(new Bullet(player.x + 38 - (bullet.getWidth() / 2),
					player.y + player.sprite.getHeight() - 1, Constants.playerDamage, 600, bullet6, true));

		} else if (player.missileLevel == 2) {
			playerBullets.addEntity(new Bullet(player.x + (player.sprite.getWidth() / 2) - (bullet.getWidth() / 2),
					player.y + player.sprite.getHeight(), 2, 600, bullet, true));
			// Middle Cannons
			playerBullets.addEntity(new Bullet(player.x + 15 - (bullet.getWidth() / 2),
					player.y + player.sprite.getHeight() - 14, Constants.playerDamage, 600, bullet3, true));
			playerBullets.addEntity(new Bullet(player.x + 46 - (bullet.getWidth() / 2),
					player.y + player.sprite.getHeight() - 14, Constants.playerDamage, 600, bullet4, true));

			// Inner Cannons
			playerBullets.addEntity(new Bullet(player.x + 23 - (bullet.getWidth() / 2),
					player.y + player.sprite.getHeight() - 1, Constants.playerDamage, 600, bullet5, true));
			playerBullets.addEntity(new Bullet(player.x + 38 - (bullet.getWidth() / 2),
					player.y + player.sprite.getHeight() - 1, Constants.playerDamage, 600, bullet6, true));

		} else if (player.missileLevel == 1) {
			playerBullets.addEntity(new Bullet(player.x + (player.sprite.getWidth() / 2) - (bullet.getWidth() / 2),
					player.y + player.sprite.getHeight(), 2, 600, bullet, true));
			// Inner Cannons
			playerBullets.addEntity(new Bullet(player.x + 23 - (bullet.getWidth() / 2),
					player.y + player.sprite.getHeight() - 1, Constants.playerDamage, 600, bullet5, true));
			playerBullets.addEntity(new Bullet(player.x + 38 - (bullet.getWidth() / 2),
					player.y + player.sprite.getHeight() - 1, Constants.playerDamage, 600, bullet6, true));

		} else if (player.missileLevel == 0) {
			playerBullets.addEntity(new Bullet(player.x + (player.sprite.getWidth() / 2) - (bullet.getWidth() / 2),
					player.y + player.sprite.getHeight(), 2, 600, bullet, true));
		}
		player.shoot = false;
	}

	long lastSpawnTime = 0;

	public void spawnEntities() {
		if (lastTime + 1000 < System.currentTimeMillis()) {
			PowerUpHandler.addEntity(new FallingObject(random.nextInt(Gdx.graphics.getWidth() - 200) + 100,
					Gdx.graphics.getHeight(), heart));
			System.out.println("Summoned Object");
			lastTime = System.currentTimeMillis();

			System.out.println(Gdx.graphics.getFramesPerSecond());

		}

		if (enemies.getEntities().size() == 0) {
			int ranI = random.nextInt(1) + 1 + Constants.totalScore / 5;
			for (int i = 0; i < ranI; i++) {
				Sprite sprite;

				if (Constants.totalScore > 5) {
					if (random.nextInt(2) == 1) {
						sprite = new Sprite(new Texture("ayylmao.png"));
					} else {
						sprite = new Sprite(new Texture("UFO.png"));
					}
				} else {
					sprite = new Sprite(new Texture("ayylmao.png"));
				}
				int x = random.nextInt(30 + Constants.totalScore);
				int y = random.nextInt(30 + Constants.totalScore);
				sprite.setColor(random.nextFloat(), random.nextFloat(), random.nextFloat(), 1);
				sprite.setSize(32 + x, 32 + y);

				enemies.addEntity(new Enemy((float) (random.nextInt(10) + Constants.totalScore + ((x + y) / 8)),
						random.nextInt(400) + 100, random.nextInt(500) + 200, sprite));
			}

		}
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}

}
