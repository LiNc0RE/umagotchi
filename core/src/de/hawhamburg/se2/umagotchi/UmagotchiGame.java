package de.hawhamburg.se2.umagotchi;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.hawhamburg.se2.umagotchi.events.EventManager;
import de.hawhamburg.se2.umagotchi.events.IEvent;
import de.hawhamburg.se2.umagotchi.events.IEventListener;
import de.hawhamburg.se2.umagotchi.events.RequestStateChangeEvent;
import de.hawhamburg.se2.umagotchi.states.IState;
import de.hawhamburg.se2.umagotchi.states.LaunchState;
import de.hawhamburg.se2.umagotchi.states.StateFactory;
import de.hawhamburg.se2.umagotchi.states.StateManager;

public class UmagotchiGame
implements
	ApplicationListener,
	IEventListener {
	
	private
	AssetManager assetManager;
	private
	EventManager eventManager;
	private
	InputManager inputManager;
	private
	StateManager stateManager;
	
	private
	SpriteBatch batch;
	private
    OrthographicCamera camera;
	private
	StateChangeListener stateChangeListener;
	
	private
	Map<String, Horse> horseMap;
	private
	Horse selectedHorse;
    
	@Override
	public
	void create () {
		Gdx.app.setLogLevel (Application.LOG_DEBUG);
		
		this.assetManager = new AssetManager ();
		this.eventManager = new EventManager ();
		this.inputManager = new InputManager (this.eventManager);
		this.stateManager = new StateManager (this);
		
		//
		this.horseMap = new HashMap<String, Horse> ();
		this.selectedHorse = null;
		
		// Register the input manager to receive input events
		Gdx.input.setInputProcessor (this.inputManager);

		// This informs android to use the asset manager
		// enables us to show a loading screen on resume
		Texture.setAssetManager (this.assetManager);
		
		this.batch = new SpriteBatch ();
		this.camera = new OrthographicCamera ();

		this.stateChangeListener = new StateChangeListener (this);
		this.eventManager.register (this.stateChangeListener, RequestStateChangeEvent.class);
		
		this.eventManager.raise (
			new RequestStateChangeEvent (LaunchState.class, false)
		);
	}

	@Override
	public
	void render () {
		// process all queued events
		this.eventManager.process ();
		
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
		Gdx.app.debug ("UmagotchiGame", "Resizing to (" + width + ":" + height + ")");
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
	void selectHorse (String horseName) {
		if (! this.horseMap.containsKey (horseName)) {
			throw new RuntimeException ("No horse named " + horseName);
		}
		this.selectedHorse = this.horseMap.get (horseName);
	}
	
	public
	Horse getSelectedHorse () {
		return this.selectedHorse;
	}
	
	public
	List<String> getHorseNames () {
		return new LinkedList<String> (this.horseMap.keySet ());
	}
	
	public
	Horse getHorseByName (String horseName) {
		if (! this.horseMap.containsKey (horseName)) {
			throw new RuntimeException ("No horse named " + horseName);
		}
		
		return this.horseMap.get (horseName);
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
	
	private static
	class StateChangeListener
	implements IEventListener {
		private
		UmagotchiGame game;
		
		public
		StateChangeListener (UmagotchiGame game) {
			this.game = game;
		}
		
		@Override
		public boolean handle (IEvent event) {
			boolean consumed = false;
			RequestStateChangeEvent re = (RequestStateChangeEvent) event;
			
			if (re.removeTop) {
				this.game.stateManager.pop ();
			}
			
			IState newstate;
			try {
				//newstate = re.stateType.newInstance ();
				newstate = StateFactory.createFromType (this.game, re.stateType);
				if (null == newstate) {
					throw new InstantiationException ("Type has been " + re.stateType);
				}
				
				this.game.stateManager.push (newstate);
			}
			catch (InstantiationException e) {
				Gdx.app.error ("UmagotchiGame", "Something didn't add up.");
				Gdx.app.error ("UmagotchiGame", e.getMessage ());
				e.printStackTrace();
			}
			finally {
				consumed = true;
			}
			
			return consumed;
		}
		
	}

	@Override
	public
	boolean handle (IEvent event) {
		boolean consumend = false;
		
		return consumend;
	}

}
