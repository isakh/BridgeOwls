package ws.isak.bridgeowls.events.engine;

import android.util.Log;

import ws.isak.bridgeowls.events.AbstractEvent;
import ws.isak.bridgeowls.events.EventObserver;

/**
 * This method is called when the cards in the match game need to be flipped down
 *
 * @author isak
 */
public class MatchFlipDownCardsEvent extends AbstractEvent {

    private static final String TAG = "MatchFlipDownCardsEvent";

	public static final String TYPE = MatchFlipDownCardsEvent.class.getName();

	public MatchFlipDownCardsEvent() {
        Log.d (TAG, "constructor - empty");
	}
	
	@Override
	protected void fire(EventObserver eventObserver) {
        //Log.d (TAG, "");
		eventObserver.onEvent(this);
	}

	@Override
	public String getType() {
        //Log.d (TAG, "");
		return TYPE;
	}
}
