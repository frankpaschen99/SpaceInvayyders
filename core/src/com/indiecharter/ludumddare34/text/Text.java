package com.indiecharter.ludumddare34.text;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

public class Text {
	GlyphLayout layout;
	BitmapFont font;
	Align alight;
	
	public Text(String fontFile){
		font = new BitmapFont(Gdx.files.internal(fontFile));
		layout = new GlyphLayout();
		layout.setText(font, "null");
	}
	
	public void setColor(Color color){
		font.setColor(color);
	}
	
	public void setFontSize(float fontSize){
		font.getData().setScale(fontSize);	
	}
	
	public void draw(String text, SpriteBatch batch, float x, float y){
		layout.setText(font, text);
		font.draw(batch, layout, x, y);
	}
	
	public float getStringLength(String text){
		layout.setText(font, text);
		return layout.width;
	}
	
	
}
