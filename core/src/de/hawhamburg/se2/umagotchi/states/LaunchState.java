package de.hawhamburg.se2.umagotchi.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import de.hawhamburg.se2.umagotchi.UmagotchiGame;

public
class LaunchState
	implements IState {
	
	private
	UmagotchiGame game;
	
	private
	boolean ready;
	
	private
	BitmapFont font;
	
	private
	String precentage;
	
	public
	LaunchState (UmagotchiGame game) {
		this.game = game;
		this.ready = false;
		this.precentage = "0";
	}
	
	@Override
	public
	void onUpdate (float deltaTime) {
		if (! this.ready) {
			this.font = new BitmapFont ();		
			this.game.getAssetManager ().load ("stall.png", Texture.class);
			this.game.getAssetManager ().load ("pferd.png", Texture.class);
			
			this.ready = true;
			
			return;
		}
		
		boolean finished = this.game.getAssetManager ().update (); 
		if (finished) {
			this.game.getStateManager ().pop ();
			this.game.getStateManager ().push (new MainMenuState (this.game));
		}
		else {
			this.precentage = String.valueOf (
				this.game.getAssetManager ().getProgress ()
			);
		}
	}

	@Override
	public
	void onRender (SpriteBatch batch) {
		Gdx.gl.glClearColor (0, 0, 0.2f, 1);
        Gdx.gl.glClear (GL20.GL_COLOR_BUFFER_BIT);

        this.game.getCamera ().update();
        batch.setProjectionMatrix (this.game.getCamera ().combined);

        batch.begin ();
        	this.font.draw (batch, 
    			"Loading: " + precentage + "%",
    			this.game.getCamera ().viewportWidth / 2,
    			this.game.getCamera ().viewportHeight / 2
			);
        batch.end ();
	}
}
