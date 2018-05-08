package ws.isak.bridgeowls.events.ui;

import android.util.Log;

import ws.isak.bridgeowls.events.AbstractEvent;
import ws.isak.bridgeowls.events.EventObserver;

/*
 * This event triggers when the difficultyLevel of the swapping game is selected
 *
 * @author isak
 */
public class ComposeDifficultySelectEvent extends AbstractEvent {

    private static final String TAG = "ComposeDiffSelectEvent";

    public static final String TYPE = ComposeDifficultySelectEvent.class.getName();

    public final int difficulty;

    public ComposeDifficultySelectEvent(int difficulty) {
        Log.d (TAG, "constructor");
        this.difficulty = difficulty;
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
