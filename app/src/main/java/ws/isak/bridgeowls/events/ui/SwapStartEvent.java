package ws.isak.bridgeowls.events.ui;

import android.util.Log;

import ws.isak.bridgeowls.events.AbstractEvent;
import ws.isak.bridgeowls.events.EventObserver;

/*
 * This event is triggered when a new swap game is started.
  *
  * @author isak
 */
public class SwapStartEvent extends AbstractEvent {

    private static final String TAG = "SwapStartEvent";

    public static final String TYPE = SwapStartEvent.class.getName();

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
