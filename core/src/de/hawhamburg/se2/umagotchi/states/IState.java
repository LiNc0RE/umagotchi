package de.hawhamburg.se2.umagotchi.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.hawhamburg.se2.umagotchi.UmagotchiGame;

public
interface IState {
	
	void onActivate ();
	
	void onDeactivate ();
	
	void onUpdate (float deltaTime);
	
	void onRender (SpriteBatch batch);
	
}
