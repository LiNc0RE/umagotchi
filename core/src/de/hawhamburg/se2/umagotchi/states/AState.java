package de.hawhamburg.se2.umagotchi.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.hawhamburg.se2.umagotchi.UmagotchiGame;
import de.hawhamburg.se2.umagotchi.events.IEvent;
import de.hawhamburg.se2.umagotchi.events.IEventListener;

public abstract
class AState
implements IState, IEventListener {

	private
	UmagotchiGame game;
	
	public
	AState (UmagotchiGame game) {
		this.game = game;
	}

	@Override
	public
	void onActivate () {
	}

	@Override
	public
	void onDeactivate () {
	}

	@Override
	public
	void onUpdate (float deltaTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public
	void onRender (SpriteBatch batch) {
		// TODO Auto-generated method stub

	}

	@Override
	public
	boolean handle (IEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
	
	protected
	UmagotchiGame getGame () {
		return this.game;
	}
	
}
