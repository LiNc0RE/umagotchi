package de.hawhamburg.se2.umagotchi.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import de.hawhamburg.se2.umagotchi.UmagotchiGame;
import de.hawhamburg.se2.umagotchi.events.RequestStateChangeEvent;

public
class MainMenuState
extends AState {
	
	private
	BitmapFont font;
	
	private
	boolean init;
	
	public
	MainMenuState (UmagotchiGame game) {
		super (game);

		this.font = new BitmapFont ();
		this.init = false;
	}

	@Override
	public
	void onUpdate (float deltaTime) {
		if (! this.init) {
			
			this.init = true;
		}
		
		// process user input
        if (Gdx.input.isTouched ()) {
            Vector3 position = this.getGame ().getCamera ().unproject (
        		new Vector3 (Gdx.input.getX (), Gdx.input.getY (), 0)
    		);
            Gdx.app.debug ("EventManager", 
				"Received touch at " + position
			);
            
            this.getGame ().getEventManager ().raise (
				new RequestStateChangeEvent (BarnState.class, true)
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
        	this.font.draw (batch, "Welcome to Umagotchi!!! ", 100, 150);
        	this.font.draw (batch, "Tap anywhere to begin!", 100, 100);
        batch.end ();
	}
}
