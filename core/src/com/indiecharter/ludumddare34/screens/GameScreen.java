package com.indiecharter.ludumddare34.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.indiecharter.ludumddare34.CoreGame;
import com.indiecharter.ludumddare34.entities.Player;
import com.indiecharter.ludumddare34.handler.Handler;

public class GameScreen implements Screen{
	SpriteBatch batch;
	CoreGame game;
	Handler handler;
	
	Player player;
	
	
	public GameScreen(CoreGame game){
		batch = new SpriteBatch();
		this.game = game;
		System.out.println("Moved to gamescreen");
		player = new Player(100, 2);
	}
	
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		update();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		player.render(batch);
		batch.end();
	}
	
	public void update(){
		player.update();
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
