package de.hawhamburg.se2.umagotchi.events;

import de.hawhamburg.se2.umagotchi.states.IState;

public
class RequestStateChangeEvent
implements IEvent {
	
	public final
	Class<? extends IState> stateType;
	public final
	boolean removeTop;
	
	public
	RequestStateChangeEvent (Class<? extends IState> stateType, boolean removeTop) {
		this.stateType = stateType;
		this.removeTop = removeTop;
	}
	
}
