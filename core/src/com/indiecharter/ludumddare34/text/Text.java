package com.indiecharter.ludumddare34.text;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text {
	
	public static void Draw(String text, SpriteBatch batch, int posX, int posY, int fontSize, String fontFile) {
		BitmapFont font = new BitmapFont(Gdx.files.internal(fontFile));
		font.getData().setScale(fontSize);
		font.draw(batch, text, posX, posY);
	}
}
