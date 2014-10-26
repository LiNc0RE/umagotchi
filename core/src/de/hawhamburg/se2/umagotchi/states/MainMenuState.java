package de.hawhamburg.se2.umagotchi.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import de.hawhamburg.se2.umagotchi.UmagotchiGame;

public
class MainMenuState
	implements IState {
	
	private
	UmagotchiGame game;
	
	private
	BitmapFont font;
	
	public
	MainMenuState (UmagotchiGame game) {
		this.game = game;
		this.font = new BitmapFont ();
	}

	@Override
	public
	void onUpdate (float deltaTime) {	
		// process user input
        if (Gdx.input.isTouched ()) {
            Vector3 position = this.game.getCamera ().unproject (
        		new Vector3 (Gdx.input.getX (), Gdx.input.getY (), 0)
    		);
            Gdx.app.debug ("EventManager", 
				"Received touch at " + position
			);
            this.game.getStateManager ().pop ();
            this.game.getStateManager ().push (new BarnState (this.game));
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
        	this.font.draw (batch, "Welcome to Umagotchi!!! ", 100, 150);
        	this.font.draw (batch, "Tap anywhere to begin!", 100, 100);
        batch.end ();
	}
}
