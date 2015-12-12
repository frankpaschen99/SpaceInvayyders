package com.indiecharter.ludumddare34.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		update();
		Gdx.gl.glClearColor(0, 0, 0, 1);
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
