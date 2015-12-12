package com.indiecharter.ludumddare34;

import com.badlogic.gdx.Game;
import com.indiecharter.ludumddare34.screens.LoadingScreen;

public class CoreGame extends Game {
	
	@Override
	public void create () {
		System.out.println("LOADING");
		this.setScreen(new LoadingScreen(this));
	}

	@Override
	public void render () {
	}
}
