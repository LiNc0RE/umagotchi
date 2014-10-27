package de.hawhamburg.se2.umagotchi;

import com.badlogic.gdx.InputProcessor;

import de.hawhamburg.se2.umagotchi.events.EventManager;
import de.hawhamburg.se2.umagotchi.events.KeyDownEvent;
import de.hawhamburg.se2.umagotchi.events.KeyTypedEvent;
import de.hawhamburg.se2.umagotchi.events.KeyUpEvent;
import de.hawhamburg.se2.umagotchi.events.MouseMovedEvent;
import de.hawhamburg.se2.umagotchi.events.ScrollEvent;
import de.hawhamburg.se2.umagotchi.events.TouchDownEvent;
import de.hawhamburg.se2.umagotchi.events.TouchDraggedEvent;
import de.hawhamburg.se2.umagotchi.events.TouchUpEvent;

public
class InputManager
implements InputProcessor {
	
	private
	EventManager eventManager;
	
	public
	InputManager (EventManager eventManager) {
		this.eventManager = eventManager;
	}

	@Override
	public
	boolean keyDown (int keycode) {
		this.eventManager.raise (new KeyDownEvent (keycode));		
		return false;
	}

	@Override
	public
	boolean keyUp (int keycode) {
		this.eventManager.raise (new KeyUpEvent (keycode));
		return false;
	}

	@Override
	public
	boolean keyTyped (char character) {
		this.eventManager.raise (new KeyTypedEvent (character));
		return false;
	}

	@Override
	public
	boolean touchDown (int screenX, int screenY, int pointer, int button) {
		this.eventManager.raise (
			new TouchDownEvent (screenX, screenY, pointer, button)
		);
		return false;
	}

	@Override
	public
	boolean touchUp (int screenX, int screenY, int pointer, int button) {
		this.eventManager.raise (
			new TouchUpEvent (screenX, screenY, pointer, button)
		);
		return false;
	}

	@Override
	public
	boolean touchDragged (int screenX, int screenY, int pointer) {
		this.eventManager.raise (
			new TouchDraggedEvent (screenX, screenY, pointer)
		);
		return false;
	}

	@Override
	public
	boolean mouseMoved (int screenX, int screenY) {
		this.eventManager.raise (
			new MouseMovedEvent (screenX, screenY)
		);
		return false;
	}

	@Override
	public
	boolean scrolled (int amount) {
		this.eventManager.raise (
			new ScrollEvent (amount)
		);
		return false;
	}

}
