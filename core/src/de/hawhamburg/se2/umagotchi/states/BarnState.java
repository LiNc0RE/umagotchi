package de.hawhamburg.se2.umagotchi.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import de.hawhamburg.se2.umagotchi.UmagotchiGame;
import de.hawhamburg.se2.umagotchi.events.IEvent;
import de.hawhamburg.se2.umagotchi.events.IEventListener;
import de.hawhamburg.se2.umagotchi.events.TouchDownEvent;

public
class BarnState
extends AState {
	
	private
	Texture menubar;
	
	public
	BarnState (UmagotchiGame game) {
		super (game);
		
		Pixmap pixmap;		
        // A Pixmap is basically a raw image in memory as repesented by pixels
        // We create one 256 wide, 128 height using 8 bytes for Red, Green, Blue and Alpha channels
        pixmap = new Pixmap (256, 64, Pixmap.Format.RGBA8888);
        
        //Fill it red
        pixmap.setColor (0.23f, 0.23f, 0.23f, 0.45f);
        pixmap.fill ();
        
        this.menubar = new Texture (pixmap);
	}
	
	@Override
	public
	void onActivate () {
		this.getGame ().getEventManager ().register (this, TouchDownEvent.class);
	}
	
	@Override
	public
	void onDeactivate () {
		this.getGame ().getEventManager ().unregister (this, TouchDownEvent.class);
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

        this.getGame ().getCamera ().update ();
        batch.setProjectionMatrix (this.getGame ().getCamera ().combined);
        
		Vector2 ex = new Vector2 (640, 480);
		Vector2 vp = new Vector2 (
			Gdx.graphics.getWidth (),
			Gdx.graphics.getHeight ()
		);
		Vector2 scale = new Vector2 (
			vp.x / ex.x,
			vp.y / ex.y
		);
        
        batch.begin ();
        
        // background stuff
        batch.draw (this.getGame ().getAssetManager ().get ("stall.png", Texture.class), 
			0, 0, vp.x, vp.y 
		);
		
		Texture horse = this.getGame ().getAssetManager ().get ("horse.png", Texture.class);
		float x = vp.x * 0.15f;
		float y = vp.y * 0.22f;
		batch.draw (horse,
			x, y,
			horse.getWidth () * 0.3f * scale.x,
			horse.getHeight () * 0.3f * scale.y 
		);
		
		// interface and foreground
		batch.draw (
    		this.menubar,
    		0,
    		vp.y - this.menubar.getHeight () * scale.y,
    		vp.x,
    		this.menubar.getHeight () * scale.y
		);
        
		Texture comb = this.getGame ().getAssetManager ().get ("comb2.png", Texture.class);
		float combs = 0.1f;
		batch.draw (comb,
			16 * scale.x,
			vp.y - comb.getHeight () * combs * scale.y,
			comb.getWidth () * combs * scale.x,
			comb.getHeight () * combs * scale.y
		);
		
		Texture hay = this.getGame ().getAssetManager ().get ("hay.png", Texture.class);
		float hays = 0.7f;
		batch.draw (hay,
			128 * scale.x,
			vp.y - hay.getHeight () * hays * scale.y,
			hay.getWidth () * hays * scale.x,
			hay.getHeight () * hays * scale.y
		);
		
        batch.end ();
	}
	
	@Override
	public boolean handle (IEvent event) {
		Class type = event.getClass ();
		
		if (type.equals (TouchDownEvent.class)) {
			TouchDownEvent e = (TouchDownEvent) event;
			Gdx.app.debug ("BarnState", "Position (" + e.screenX + ":" + e.screenY + ")");
		
			Sound click = this.getGame ().getAssetManager ().get ("interface-click.mp3", Sound.class);
			click.play ();
			
			return true;
		}
		
		return false;
	}
}
