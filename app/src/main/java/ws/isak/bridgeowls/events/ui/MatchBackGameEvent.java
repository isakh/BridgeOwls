package ws.isak.bridgeowls.events.ui;

import android.util.Log;

import ws.isak.bridgeowls.events.AbstractEvent;
import ws.isak.bridgeowls.events.EventObserver;

/*
 * This event is triggered when the player wants to replay a matching game
 * TODO check that this is called when difficultyLevel and matchTheme are kept the same?
 *
 * @author isak
 */
public class MatchBackGameEvent extends AbstractEvent {

    private static final String TAG = "MatchBackGameEvent";

	public static final String TYPE = MatchBackGameEvent.class.getName();

	@Override
	protected void fire(EventObserver eventObserver) {
        Log.d (TAG, "overriding method fire");
        eventObserver.onEvent(this);
	}

	@Override
	public String getType() {
        //Log.d (TAG, "");
        return TYPE;
	}

}
