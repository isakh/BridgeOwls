package ws.isak.bridgeowls.events.ui;

import android.util.Log;

import ws.isak.bridgeowls.events.AbstractEvent;
import ws.isak.bridgeowls.events.EventObserver;

/*
 * This event is triggered when the SwapNextGameEvent is called on a swaping game
 *
 * @author isak
 */
public class SwapNextGameEvent extends AbstractEvent {

    private static final String TAG = "SwapNextGameEvent";

    public static final String TYPE = SwapNextGameEvent.class.getName();

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
