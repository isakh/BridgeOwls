package ws.isak.bridgeowls.fragments;

import android.support.v4.app.Fragment;
import android.util.Log;

import ws.isak.bridgeowls.events.EventObserver;

import ws.isak.bridgeowls.events.engine.MatchFlipDownCardsEvent;
import ws.isak.bridgeowls.events.engine.MatchGameWonEvent;
import ws.isak.bridgeowls.events.engine.MatchHidePairCardsEvent;
import ws.isak.bridgeowls.events.engine.SwapPlayRowAudioEvent;
import ws.isak.bridgeowls.events.engine.SwapGameWonEvent;
import ws.isak.bridgeowls.events.engine.PlayCardAudioEvent;

import ws.isak.bridgeowls.events.ui.ComposeDifficultySelectEvent;
import ws.isak.bridgeowls.events.ui.MatchBackGameEvent;
import ws.isak.bridgeowls.events.ui.MatchFlipCardEvent;
import ws.isak.bridgeowls.events.ui.MatchDifficultySelectedEvent;
import ws.isak.bridgeowls.events.ui.SwapDifficultySelectedEvent;
import ws.isak.bridgeowls.events.ui.MatchNextGameEvent;
import ws.isak.bridgeowls.events.ui.MatchResetBackgroundEvent;
import ws.isak.bridgeowls.events.ui.MatchThemeSelectedEvent;
import ws.isak.bridgeowls.events.ui.MatchStartEvent;
import ws.isak.bridgeowls.events.ui.SwapStartEvent;
import ws.isak.bridgeowls.events.ui.SwapBackGameEvent;
import ws.isak.bridgeowls.events.ui.SwapNextGameEvent;
import ws.isak.bridgeowls.events.ui.SwapSelectedCardsEvent;
import ws.isak.bridgeowls.events.ui.SwapUnselectCardsEvent;

/*
 * Class BaseFragment defines the core of each fragment behavior when an event occurs
 *
 * @author isak
 */

public class BaseFragment extends Fragment implements EventObserver {

    private static final String TAG = "BaseFragment";

    //Match Game Events

	@Override
	public void onEvent(MatchFlipCardEvent event) {
        Log.d (TAG, "method onEvent: MatchFlipCardEvent");
        throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(MatchDifficultySelectedEvent event) {
        //Log.d (TAG, "");
        throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(MatchHidePairCardsEvent event) {
        //Log.d (TAG, "");
        throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(MatchFlipDownCardsEvent event) {
        //Log.d (TAG, "");
        throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(MatchStartEvent event) {
        //Log.d (TAG, "");
        throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(MatchThemeSelectedEvent event) {
        //Log.d (TAG, "");
        throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(MatchGameWonEvent event) {
        //Log.d (TAG, "");
        throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(MatchBackGameEvent event) {
        //Log.d (TAG, "");
        throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(MatchNextGameEvent event) {
        //Log.d (TAG, "");
        throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(MatchResetBackgroundEvent event) {
        //Log.d (TAG, "");
        throw new UnsupportedOperationException();
	}

	//Swap Game Events

    public void onEvent(SwapSelectedCardsEvent event) {
        Log.d (TAG, "method onEvent: MatchFlipCardEvent");
        throw new UnsupportedOperationException();
    }

    public void onEvent(SwapUnselectCardsEvent event) {
        Log.d (TAG, "onEvent(SwapUnselectCardsEvent event)");
        throw new UnsupportedOperationException();
    }

    @Override
    public void onEvent(SwapGameWonEvent event) {
        //Log.d (TAG, "");
        throw new UnsupportedOperationException();
    }

    @Override
    public void onEvent(SwapStartEvent event) {
        //Log.d (TAG, "");
        throw new UnsupportedOperationException();
    }

    @Override
    public void onEvent(SwapDifficultySelectedEvent event) {
        //Log.d (TAG, "");
        throw new UnsupportedOperationException();
    }

    @Override
    public void onEvent(SwapNextGameEvent event) {
        //Log.d (TAG, "");
        throw new UnsupportedOperationException();
    }

    @Override
    public void onEvent(SwapBackGameEvent event) {
        //Log.d (TAG, "");
        throw new UnsupportedOperationException();
    }

    //Compose Game Events

    @Override
    public void onEvent(ComposeDifficultySelectEvent event) {
        //Log.d (TAG, "");
        throw new UnsupportedOperationException();
    }

    //Audio Playback Events

    @Override
    public void onEvent(SwapPlayRowAudioEvent event) {
        //Log.d (TAG, "");
        throw new UnsupportedOperationException();
    }

    @Override
	public void onEvent(PlayCardAudioEvent event) {
        //Log.d (TAG, "");
        throw new UnsupportedOperationException();
	}
}
