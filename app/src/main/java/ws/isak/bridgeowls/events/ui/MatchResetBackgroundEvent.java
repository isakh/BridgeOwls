package ws.isak.bridgeowls.events.ui;

import android.util.Log;

import ws.isak.bridgeowls.events.AbstractEvent;
import ws.isak.bridgeowls.events.EventObserver;

/**
 * This event is triggered when the background of the matching game needs to be reset.
 *
 * @author isak
 */
public class MatchResetBackgroundEvent extends AbstractEvent {

    private static final String TAG = "MatchResetBackgroundEvent";

	public static final String TYPE = MatchResetBackgroundEvent.class.getName();

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
