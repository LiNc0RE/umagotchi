package de.hawhamburg.se2.umagotchi.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.hawhamburg.se2.umagotchi.UmagotchiGame;

public
class BarnState 
	implements IState {
	
	private
	UmagotchiGame game;
	
	public
	BarnState () {
	}
	
	@Override
	public
	void attachTo (UmagotchiGame game) {
		this.game = game;
	}
	
	@Override
	public
	void onUpdate (float deltaTime) {	
	}

	@Override
	public
	void onRender (SpriteBatch batch) {
		Gdx.gl.glClearColor (0, 1, 0.2f, 1);
        Gdx.gl.glClear (GL20.GL_COLOR_BUFFER_BIT);

        this.game.getCamera ().update ();
        batch.setProjectionMatrix (this.game.getCamera ().combined);
        
        batch.begin ();
        this.renderScene (batch);
        this.renderToolbar (batch);
        batch.end ();
	}
	
	private
	void renderToolbar (SpriteBatch batch) {
		Texture comb = this.game.getAssetManager ().get ("comb2.png", Texture.class);
		batch.draw (comb,
			120, 120
		);
	}
	
	private
	void renderScene (SpriteBatch batch) {
		batch.draw (this.game.getAssetManager ().get ("stall.png", Texture.class),
			120, 120
		);
		batch.draw (this.game.getAssetManager ().get ("pferd.png", Texture.class),
			220, 280
		);
	}
}
