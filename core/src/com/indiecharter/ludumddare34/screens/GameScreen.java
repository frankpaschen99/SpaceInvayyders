package com.indiecharter.ludumddare34.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.indiecharter.ludumddare34.CoreGame;

public class GameScreen implements Screen{
	SpriteBatch batch;
	Texture img;
	CoreGame game;
	
	public GameScreen(CoreGame game){
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		this.game = game;
		System.out.println("Moved to gamescreen");
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		update();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
	}
	
	public void update(){
		
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
