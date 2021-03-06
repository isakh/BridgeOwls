package ws.isak.bridgeowls.fragments;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import ws.isak.bridgeowls.R;
import ws.isak.bridgeowls.common.Shared;
import ws.isak.bridgeowls.engine.ScreenController;
import ws.isak.bridgeowls.engine.ScreenController.Screen;
import ws.isak.bridgeowls.utils.FontLoader;

/*
 * Class GameSelectFragment provides the view for the fragment where the user decides which of
 * the games to play.  This view contains clickable buttons for each of the games with onClick
 * listeners that animate the click action and open the appropriate subsequent screen.
 *
 *  @author isak
 */

public class GameSelectFragment extends Fragment implements  View.OnClickListener{

    public static final String TAG="GameSelectFragment";

    private TextView fragmentTitle;
    private TextView matchTitle;
    private TextView swapTitle;
    private TextView composeTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "overriding method onCreateView");
        View view = LayoutInflater.from(Shared.context).inflate(R.layout.game_select_fragment, container, false);

        //creating views for matchGameLaunch and swapGameLaunch
        Log.d (TAG, "       : creating views for matchGameLaunch and swapGameLaunch");
        View matchGameLaunch = view.findViewById(R.id.game_match_container);
        View swapGameLaunch = view.findViewById(R.id.game_swap_container);
        View composeGameLaunch = view.findViewById(R.id.game_compose_container);

        fragmentTitle = (TextView) view.findViewById(R.id.game_select_fragment_title);
        matchTitle = (TextView) view.findViewById(R.id.game_match_title);
        swapTitle = (TextView) view.findViewById(R.id.game_swap_title);
        composeTitle = (TextView) view.findViewById(R.id.game_compose_title);

        FontLoader.setTypeface(Shared.context, new TextView[] {
                fragmentTitle, matchTitle, swapTitle, composeTitle
        }, FontLoader.Font.ANGRYBIRDS);
        //setting click listeners for game launch views
        Log.d (TAG, "setting click listeners for game launch views");
        matchGameLaunch.setOnClickListener(this);
        swapGameLaunch.setOnClickListener(this);
        composeGameLaunch.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick (View view) {
        Log.d (TAG, "overriding method onClick: game launch buttons animate and move to appropriate subsequent screen");
        switch (view.getId()) {
            case R.id.game_match_container:
                Log.d(TAG, "overridden method onClick: case game_match: switch to MENU_MATCH screen");
                animateShow(view);
                ScreenController.getInstance().openScreen(Screen.MENU_MATCH);
                break;
            case R.id.game_swap_container:
                Log.d(TAG, "overridden method onClick: case game_swap: switch to MENU_SWAP screen");
                animateShow(view);
                ScreenController.getInstance().openScreen(Screen.MENU_SWAP);
                break;
            case R.id.game_compose_container:
                Log.d(TAG, "overridden method onClick: case game_compose: switch to MENU_COMPOSE screen"); //TODO do we want a menu screen?
                animateShow(view);
                ScreenController.getInstance().openScreen(Screen.DIFFICULTY_COMPOSE); //FIXME for now this goes to difficulty select
                break;
        }
    }

    private void animateShow(View view) {
        ObjectAnimator animatorScaleX = ObjectAnimator.ofFloat(view, "scaleX", 0.5f, 1f);
        ObjectAnimator animatorScaleY = ObjectAnimator.ofFloat(view, "scaleY", 0.5f, 1f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(300);
        animatorSet.playTogether(animatorScaleX, animatorScaleY);
        animatorSet.setInterpolator(new DecelerateInterpolator(2));
        view.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        animatorSet.start();
    }
}
