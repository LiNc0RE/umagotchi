package de.hawhamburg.se2.umagotchi.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.hawhamburg.se2.umagotchi.UmagotchiGame;
import de.hawhamburg.se2.umagotchi.events.RequestStateChangeEvent;

public
class LaunchState
extends AState {
	
	private
	boolean ready;
	
	private
	BitmapFont font;
	
	private
	String precentage;
	
	public
	LaunchState (UmagotchiGame game) {
		super (game);

		this.ready = false;
		this.precentage = "0";
	}
	
	@Override
	public
	void onUpdate (float deltaTime) {
		if (! this.ready) {
			this.font = new BitmapFont ();
			this.getGame ().getAssetManager ().load ("stall.png", Texture.class);
			this.getGame ().getAssetManager ().load ("horse.png", Texture.class);
			this.getGame ().getAssetManager ().load ("comb2.png", Texture.class);
			this.getGame ().getAssetManager ().load ("hay.png", Texture.class);
			
			this.getGame ().getAssetManager ().load ("neighing-01.mp3", Sound.class);
			this.getGame ().getAssetManager ().load ("neighing-02.mp3", Sound.class);
			this.getGame ().getAssetManager ().load ("chewing.mp3", Sound.class);
			this.getGame ().getAssetManager ().load ("interface-click.mp3", Sound.class);
			this.getGame ().getAssetManager ().load ("bg-music.mp3", Music.class);
			
			this.ready = true;
			
			return;
		}
		
		boolean finished = this.getGame ().getAssetManager ().update (); 
		if (finished) {
			Music bg = this.getGame ().getAssetManager ().get ("bg-music.mp3", Music.class);
			bg.setLooping (true);
			bg.setVolume (0.31415f);
			bg.play ();
			
			this.getGame ().getEventManager ().raise (
				new RequestStateChangeEvent (MainMenuState.class, true)
			);
		}
		else {
			this.precentage = String.valueOf (
				(int)(this.getGame ().getAssetManager ().getProgress () * 100)
			);
		}
	}

	@Override
	public
	void onRender (SpriteBatch batch) {
		Gdx.gl.glClearColor (0, 0, 0.2f, 1);
        Gdx.gl.glClear (GL20.GL_COLOR_BUFFER_BIT);

        this.getGame ().getCamera ().update();
        batch.setProjectionMatrix (this.getGame ().getCamera ().combined);

        batch.begin ();
        	this.font.draw (batch, 
    			"Loading: " + precentage + "%",
    			this.getGame ().getCamera ().viewportWidth / 2,
    			this.getGame ().getCamera ().viewportHeight / 2
			);
        batch.end ();
	}
}
