package com.indiecharter.ludumddare34.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.indiecharter.ludumddare34.CoreGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 800;
		config.height = 600;
		config.resizable = false;
		config.vSyncEnabled = true;
		config.title = "";
		new LwjglApplication(new CoreGame(), config);
	}
}
