package com.indiecharter.ludumddare34.screens;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.indiecharter.ludumddare34.CoreGame;
import com.indiecharter.ludumddare34.entities.Background;
import com.indiecharter.ludumddare34.entities.Bullet;
import com.indiecharter.ludumddare34.entities.Enemy;
import com.indiecharter.ludumddare34.entities.FallingObject;
import com.indiecharter.ludumddare34.entities.Player;
import com.indiecharter.ludumddare34.gui.ProgressBar;
import com.indiecharter.ludumddare34.handler.Handler;
import com.indiecharter.ludumddare34.text.Text;

public class GameScreen implements Screen{
	SpriteBatch batch;
	CoreGame game;
	
	Random random;
	
	Player player;
	
	Handler playerHandler;
	Handler PowerUpHandler;
	Handler playerBullets;
	
	Handler enemies;
	
	Text text;
	
	ProgressBar pb;
	
	Background background;
	
	Texture heart;
	public GameScreen(CoreGame game){
		text = new Text("fonts/font.fnt");
		text.setFontSize(0.32f);
		text.setColor(Color.GREEN);
		
		pb = new ProgressBar(false, 10, 2, 200, 200);
		
		heart = new Texture("gui_heart_full.png");
		batch = new SpriteBatch();
		this.game = game;
		System.out.println("Moved to gamescreen");
		
		playerBullets = new Handler();
		
		enemies = new Handler();
		Sprite sprite = new Sprite(new Texture("space_disk.png"));
		sprite.setSize(64, 64);
		enemies.addEntity(new Enemy(10, 300, 300, sprite));
		
		player = new Player(100, 2);
		playerHandler = new Handler();
		playerHandler.addEntity(player);
		
		PowerUpHandler = new Handler();
		
		random = new Random();
		
		background = new Background();
	}
	
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		update(delta);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		background.render(batch);
		playerBullets.render(batch);
		enemies.render(batch);
		PowerUpHandler.render(batch);
		playerHandler.render(batch);
		pb.render(batch);
		text.draw("Ayy lmao", batch, 300 - text.getStringLength("Ayy lmao")/2, 400);
		batch.end();
	}
	
	long lastTime = System.currentTimeMillis();
	public void update(float delta){
		if(lastTime + 1000 < System.currentTimeMillis()){
			PowerUpHandler.addEntity(new FallingObject(random.nextInt(Gdx.graphics.getWidth()- 200) + 100, Gdx.graphics.getHeight(), heart));
			System.out.println("Summoned Object");
			lastTime = System.currentTimeMillis();
			
			System.out.println(Gdx.graphics.getFramesPerSecond());
			
		}
		
		if(player.shoot){

			Sprite bullet = new Sprite(new Texture("lazer.png"));
			bullet.setSize(bullet.getWidth() / 2, bullet.getHeight() /2);
			playerBullets.addEntity(new Bullet(player.x + (player.sprite.getWidth() / 2) - (bullet.getWidth() / 2), player.y + player.sprite.getHeight(), 2, 600, bullet ));
			
			player.shoot = false;
		}
		
		pb.update();
		playerBullets.update(delta);
		PowerUpHandler.update(delta);
		playerHandler.update(delta);
		enemies.update(delta);
		
		
		
		playerBullets.checkCollision(enemies);
		
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
