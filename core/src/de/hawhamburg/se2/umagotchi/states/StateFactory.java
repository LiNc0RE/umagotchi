package de.hawhamburg.se2.umagotchi.states;

import de.hawhamburg.se2.umagotchi.UmagotchiGame;

public final
class StateFactory {
	public static
	IState createFromType (UmagotchiGame game, Class<? extends IState> type) {
		if (type.equals (BarnState.class)) {
			return new BarnState (game);
		}
		if (type.equals (ContinueState.class)) {
			return new ContinueState (game);
		}
		if (type.equals (GrasslandState.class)) {
			return new GrasslandState (game);
		}
		if (type.equals (LaunchState.class)) {
			return new LaunchState (game);
		}
		if (type.equals (MainMenuState.class)) {
			return new MainMenuState (game);
		}
		if (type.equals (NewPlayerState.class)) {
			return new NewPlayerState (game);
		}
		if (type.equals (OptionsState.class)) {
			return new OptionsState (game);
		}
		if (type.equals (QuitState.class)) {
			return new QuitState (game);
		}
		if (type.equals (StatusState.class)) {
			return new StatusState (game);
		}
		
		return null;
	}
}
