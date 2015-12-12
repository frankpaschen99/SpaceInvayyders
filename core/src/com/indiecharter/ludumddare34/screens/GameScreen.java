package com.indiecharter.ludumddare34.screens;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.indiecharter.ludumddare34.CoreGame;
import com.indiecharter.ludumddare34.entities.BackgroundWallTile;
import com.indiecharter.ludumddare34.entities.FallingObject;
import com.indiecharter.ludumddare34.entities.Player;
import com.indiecharter.ludumddare34.entities.WindowWallTile;
import com.indiecharter.ludumddare34.handler.Handler;
import com.indiecharter.ludumddare34.utilities.Constants;

public class GameScreen implements Screen{
	SpriteBatch batch;
	CoreGame game;
	Handler BackgroundHandler;
	
	Random random;
	
	Player player;
	
	Handler FOHandler;
	Texture heart;
	public GameScreen(CoreGame game){
		heart = new Texture("gui_heart_full.png");
		batch = new SpriteBatch();
		this.game = game;
		System.out.println("Moved to gamescreen");
		player = new Player(100, 2);
		FOHandler = new Handler();
		BackgroundHandler = new Handler();
		random = new Random();
		player.addCollisionsGroup(FOHandler);
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
		BackgroundHandler.render(batch);
		FOHandler.render(batch);
		player.render(batch);
		batch.end();
	}
	int backgroundTimesMoved = 0;
	long lastTime = System.currentTimeMillis();
	public void update(float delta){
		if(lastTime + 1000 < System.currentTimeMillis()){
			FOHandler.addEntity(new FallingObject(random.nextInt(600) + 100, 600, heart));
			System.out.println("Summoned Object");
			lastTime = System.currentTimeMillis();
		}
		
		BackgroundHandler.update(delta);
		BackgroundHandler.translate(0, -Constants.climbSpeed * delta);
		backgroundTimesMoved += (Constants.climbSpeed * delta);
		
		if(backgroundTimesMoved > 18000 / Constants.climbSpeed){
			BackgroundHandler.addEntity(new BackgroundWallTile());
			addWindow();
			backgroundTimesMoved = 0;
		}
		
		FOHandler.update(delta);
		player.update(delta);
		System.out.println(Gdx.graphics.getFramesPerSecond() + " " + FOHandler.getEntities().size());
	}
	
	public void addWindow(){
		for(int i = 0; i < 7; i++){
			if(random.nextInt(7) == 1){
				BackgroundHandler.addEntity(new WindowWallTile((float)(i + 1) *(100) - 50, (float)0));
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
