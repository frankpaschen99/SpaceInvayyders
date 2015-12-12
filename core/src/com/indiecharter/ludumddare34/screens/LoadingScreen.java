package com.indiecharter.ludumddare34.screens;

import com.badlogic.gdx.Screen;
import com.indiecharter.ludumddare34.CoreGame;

public class LoadingScreen implements Screen{
	CoreGame game;
	public LoadingScreen(CoreGame game){
		this.game = game;
	}
	
	@Override
	public void show() {
		
	}
	
	@Override
	public void render(float delta) {
		update();
	}
	
	public void update(){
		System.out.println("LOADING");
		game.setScreen(new MenuScreen(game));
		
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
