package com.indiecharter.ludumddare34.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.indiecharter.ludumddare34.CoreGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 600;
		config.height = 800;
		config.resizable = false;
		config.vSyncEnabled = true;
		config.title = "Space Invayyders";
		new LwjglApplication(new CoreGame(), config);
	}
}
