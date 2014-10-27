package de.hawhamburg.se2.umagotchi.states;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.hawhamburg.se2.umagotchi.UmagotchiGame;

public
class StateManager
	implements IState {
	
	private
	UmagotchiGame game;
	
	private
	List<IState> states;
	
	public
	StateManager (UmagotchiGame game) {
		this.game = game;
		this.states = new LinkedList<IState> ();
	}	
	
	public
	void push (IState state) {
		if (this.hasStateOfType (state.getClass ())) {
			throw new RuntimeException ("Trying to push " + states.getClass ());
		}
		
		this.states.add (state);
	}
	
	public
	void pop () {
		if (this.states.isEmpty ()) {
			return;
		}
		
		this.states.remove (this.states.size () - 1);
	}
	
	@Override
	public
	void attachTo (UmagotchiGame game) {
		// do nothing
	}
	
	@Override
	public
	void onUpdate (float deltaTime) {
		List<IState> copy = new LinkedList<IState> (this.states);
		Collections.reverse (copy);
		for (IState state : copy) {
			state.onUpdate (deltaTime);
		}
	}

	@Override
	public
	void onRender (SpriteBatch batch) {
		for (IState state : this.states) {
			state.onRender (batch);
		}
	}
	
	private
	boolean hasStateOfType (Class <? extends IState> type) {
		for (IState state : this.states) {
			if (state.getClass ().equals (type)) {
				return true;
			}
		}
		
		return false;
	}

}
