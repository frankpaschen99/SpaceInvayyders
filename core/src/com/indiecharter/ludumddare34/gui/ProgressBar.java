package com.indiecharter.ludumddare34.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class ProgressBar extends Gui {
	// TODO: Created progress bar that can be use vertical or horizontal
	Texture barOutline;
	public Sprite sprite;

	public float totalValue;
	public float value;

	ShapeRenderer sr;

	Color color;

	boolean rotate;

	public ProgressBar(boolean rotate, float totalValue, float currentValue, float x, float y) {
		barOutline = new Texture("loading_bar.png");
		sprite = new Sprite(barOutline);
		this.rotate = rotate;
		this.totalValue = totalValue;
		this.value = currentValue;
		this.x = x;
		this.y = y;
		color = Color.RED;
		sr = new ShapeRenderer();
		sprite.setPosition(x, y);
		if (rotate) {
			sprite.setSize(10, 100);
		} else {
			sprite.setSize(100, 10);
		}
	}

	public void setSize(float x, float y) {
		sprite.setSize(x, y);
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void update() {
		if(this.value > this.totalValue){
			this.value = this.totalValue;
		}
		sprite.setPosition(x, y);
	}

	public void render(SpriteBatch batch) {
		if (this.rotate) {
			batch.end();
			sr.begin(ShapeType.Filled);
			sr.setColor(color);
			sr.rect(this.x, this.y, this.sprite.getWidth(),
					this.sprite.getHeight() - (this.sprite.getHeight() * (1 - value / totalValue)));
			sr.end();

			sr.begin(ShapeType.Line);
			sr.setColor(Color.WHITE);
			sr.rect(this.x, this.y, this.sprite.getWidth(), this.sprite.getHeight());
			sr.end();
			batch.begin();
		} else {
			batch.end();
			sr.begin(ShapeType.Filled);
			sr.setColor(color);
			sr.rect(this.x, this.y, this.sprite.getWidth() - (this.sprite.getWidth() * (1 - value / totalValue)),
					this.sprite.getHeight());
			sr.end();

			sr.begin(ShapeType.Line);
			sr.setColor(Color.WHITE);
			sr.rect(this.x, this.y, this.sprite.getWidth(), this.sprite.getHeight());
			sr.end();
			batch.begin();
		}
	}
}
