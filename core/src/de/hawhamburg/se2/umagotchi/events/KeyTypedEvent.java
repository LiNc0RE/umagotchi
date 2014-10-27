package de.hawhamburg.se2.umagotchi.events;

public
class KeyTypedEvent
implements IEvent {
	
	public final
	char character;
	
	public
	KeyTypedEvent (char character) {
		this.character = character;
	}
	
}
