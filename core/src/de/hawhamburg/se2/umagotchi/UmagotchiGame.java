package de.hawhamburg.se2.umagotchi;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.hawhamburg.se2.umagotchi.states.LaunchState;
import de.hawhamburg.se2.umagotchi.states.StateManager;

public class UmagotchiGame
	implements ApplicationListener {
	
	private
	AssetManager assetManager;
	private
	EventManager eventManager;
	private
	StateManager stateManager;
	
	private
	SpriteBatch batch;
	private
    OrthographicCamera camera;
    
	@Override
	public
	void create () {
		this.assetManager = new AssetManager ();
		this.eventManager = new EventManager ();
		this.stateManager = new StateManager (this);

		// this informs android to use the asset manager
		// enables us to show a loading screen on resume
		Texture.setAssetManager (this.assetManager);
		
		this.batch = new SpriteBatch ();
		this.camera = new OrthographicCamera ();
		
		this.stateManager.push (new LaunchState (this));
	}

	@Override
	public
	void render () {
		this.stateManager.onUpdate (Gdx.graphics.getDeltaTime ());
		this.stateManager.onRender (this.batch);
	}
	
	@Override public
	void dispose () {		
		this.assetManager.dispose ();
		this.batch.dispose ();
	}
	
	public
	void clearScreen () {
		Gdx.gl.glClearColor (1, 0, 0, 1);
		Gdx.graphics.getGL20().glClear (
			GL20.GL_COLOR_BUFFER_BIT |
			GL20.GL_DEPTH_BUFFER_BIT
		);
	}

	@Override
	public
	void resize (int width, int height) {
		this.camera.setToOrtho (false, width, height);
	}

	@Override
	public
	void pause () {
	}

	@Override
	public
	void resume () {
	}
	
	public
	AssetManager getAssetManager () {
		return this.assetManager;
	}
	
	public
	EventManager getEventManager () {
		return this.eventManager;
	}
	
	public
	StateManager getStateManager () {
		return this.stateManager;
	}
	
	public
	OrthographicCamera getCamera () {
		return this.camera;
	}

}
