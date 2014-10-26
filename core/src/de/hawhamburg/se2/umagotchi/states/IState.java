package de.hawhamburg.se2.umagotchi.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public
interface IState {
	void onUpdate (float deltaTime);
	
	void onRender (SpriteBatch batch);
}
