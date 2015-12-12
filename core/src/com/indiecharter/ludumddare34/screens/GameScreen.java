package com.indiecharter.ludumddare34.screens;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.indiecharter.ludumddare34.CoreGame;
import com.indiecharter.ludumddare34.entities.Background;
import com.indiecharter.ludumddare34.entities.Bullet;
import com.indiecharter.ludumddare34.entities.FallingObject;
import com.indiecharter.ludumddare34.entities.Player;
import com.indiecharter.ludumddare34.handler.Handler;

public class GameScreen implements Screen{
	SpriteBatch batch;
	CoreGame game;
	
	Random random;
	
	Player player;
	
	Handler playerHandler;
	Handler PowerUpHandler;
	Handler playerBullets;
	
	Background background;
	
	Texture heart;
	public GameScreen(CoreGame game){
		heart = new Texture("gui_heart_full.png");
		batch = new SpriteBatch();
		this.game = game;
		System.out.println("Moved to gamescreen");
		
		playerBullets = new Handler();
		
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
		PowerUpHandler.render(batch);
		playerHandler.render(batch);
		batch.end();
	}
	
	long lastTime = System.currentTimeMillis();
	public void update(float delta){
		if(lastTime + 1000 < System.currentTimeMillis()){
			PowerUpHandler.addEntity(new FallingObject(random.nextInt(Gdx.graphics.getWidth()- 200) + 100, Gdx.graphics.getHeight(), heart));
			Sprite bullet = new Sprite(new Texture("lazer.png"));
			bullet.setSize(bullet.getWidth() / 2, bullet.getHeight() /2);
			playerBullets.addEntity(new Bullet(player.x + (player.sprite.getWidth() / 2) - (bullet.getWidth() / 2), player.y + player.sprite.getHeight(), 2, 600, bullet ));
			System.out.println("Summoned Object");
			lastTime = System.currentTimeMillis();
			
			
		}
		
		playerBullets.update(delta);
		PowerUpHandler.update(delta);
		playerHandler.update(delta);
		
		System.out.println(Gdx.graphics.getFramesPerSecond() + " " + PowerUpHandler.getEntities().size());
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
