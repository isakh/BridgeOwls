package ws.isak.bridgeowls.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.util.Log;

import ws.isak.bridgeowls.R;
import ws.isak.bridgeowls.common.Shared;
import ws.isak.bridgeowls.model.GameState;

/*
 *  Class PopupManager defines general characteristics for closing a popup and specific methods
 *  for showing each available popup.
 *
 *  //TODO ∆ existing method's to reflect which game they are part of
 *
 * @author isak
 */

public class PopupManager {

    private static final String TAG = "PopupManager";
	
	public static void showMatchPopupSettings() {
        Log.d (TAG, "method showMatchPopupSettings");
		RelativeLayout popupContainer = (RelativeLayout) Shared.activity.findViewById(R.id.popup_container);
		popupContainer.removeAllViews();

		// background
		ImageView imageView = new ImageView(Shared.context);
		imageView.setBackgroundColor(Color.parseColor("#88555555"));        //TODO set in colors.xml
		imageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		imageView.setClickable(true);
		popupContainer.addView(imageView);

		// popup
		MatchPopupSettingsView matchPopupSettingsView = new MatchPopupSettingsView(Shared.context);
		int width = Shared.context.getResources().getDimensionPixelSize(R.dimen.match_popup_settings_width);
		int height = Shared.context.getResources().getDimensionPixelSize(R.dimen.match_popup_settings_height);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
		params.addRule(RelativeLayout.CENTER_IN_PARENT);
		popupContainer.addView(matchPopupSettingsView, params);

		// animate all together
		ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(matchPopupSettingsView, "scaleX", 0f, 1f);
		ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(matchPopupSettingsView, "scaleY", 0f, 1f);
		ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(imageView, "alpha", 0f, 1f);
		AnimatorSet animatorSet = new AnimatorSet();
		animatorSet.playTogether(scaleXAnimator, scaleYAnimator, alphaAnimator);
		animatorSet.setDuration(500);
		animatorSet.setInterpolator(new DecelerateInterpolator(2));
		animatorSet.start();
	}

	public static void showSwapPopupSettings() {
        Log.d (TAG, "method showSwapPopupSettings");
        RelativeLayout popupContainer = (RelativeLayout) Shared.activity.findViewById(R.id.popup_container);
        popupContainer.removeAllViews();

        // background
        ImageView imageView = new ImageView(Shared.context);
        imageView.setBackgroundColor(Color.parseColor("#88555555"));        //TODO set in colors.xml
        imageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        imageView.setClickable(true);
        popupContainer.addView(imageView);

        // popup
        SwapPopupSettingsView swapPopupSettingsView = new SwapPopupSettingsView(Shared.context);
        int width = Shared.context.getResources().getDimensionPixelSize(R.dimen.swap_popup_settings_width);
        int height = Shared.context.getResources().getDimensionPixelSize(R.dimen.swap_popup_settings_height);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        popupContainer.addView(swapPopupSettingsView, params);

        // animate all together
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(swapPopupSettingsView, "scaleX", 0f, 1f);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(swapPopupSettingsView, "scaleY", 0f, 1f);
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(imageView, "alpha", 0f, 1f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleXAnimator, scaleYAnimator, alphaAnimator);
        animatorSet.setDuration(500);
        animatorSet.setInterpolator(new DecelerateInterpolator(2));
        animatorSet.start();
    }    

	public static void showMatchPopupWon(GameState gameState) {
        Log.d (TAG, "method showMatchPopupWon");
		RelativeLayout popupContainer = (RelativeLayout) Shared.activity.findViewById(R.id.popup_container);
		popupContainer.removeAllViews();

		// popup
		MatchPopupWonView matchPopupWonView = new MatchPopupWonView(Shared.context);
		matchPopupWonView.setGameState(gameState);
		int width = Shared.context.getResources().getDimensionPixelSize(R.dimen.popup_won_width);
		int height = Shared.context.getResources().getDimensionPixelSize(R.dimen.popup_won_height);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
		params.addRule(RelativeLayout.CENTER_IN_PARENT);
		popupContainer.addView(matchPopupWonView, params);

		// animate all together
		ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(matchPopupWonView, "scaleX", 0f, 1f);
		ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(matchPopupWonView, "scaleY", 0f, 1f);
		AnimatorSet animatorSet = new AnimatorSet();
		animatorSet.playTogether(scaleXAnimator, scaleYAnimator);
		animatorSet.setDuration(500);
		animatorSet.setInterpolator(new DecelerateInterpolator(2));
		matchPopupWonView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
		animatorSet.start();
	}

    public static void showSwapPopupWon(GameState gameState) {
        Log.d (TAG, "method showSwapPopupWon");
        RelativeLayout popupContainer = (RelativeLayout) Shared.activity.findViewById(R.id.popup_container);
        popupContainer.removeAllViews();

        // popup
        SwapPopupWonView swapPopupWonView = new SwapPopupWonView(Shared.context);
        swapPopupWonView.setGameState(gameState);
        int width = Shared.context.getResources().getDimensionPixelSize(R.dimen.popup_won_width);
        int height = Shared.context.getResources().getDimensionPixelSize(R.dimen.popup_won_height);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        popupContainer.addView(swapPopupWonView, params);

        // animate all together
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(swapPopupWonView, "scaleX", 0f, 1f);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(swapPopupWonView, "scaleY", 0f, 1f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleXAnimator, scaleYAnimator);
        animatorSet.setDuration(500);
        animatorSet.setInterpolator(new DecelerateInterpolator(2));
        swapPopupWonView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        animatorSet.start();
    }

	public static void closePopup() {
		final RelativeLayout popupContainer = (RelativeLayout) Shared.activity.findViewById(R.id.popup_container);
		int childCount = popupContainer.getChildCount();
		if (childCount > 0) {
			View background = null;
			View viewPopup = null;
			if (childCount == 1) {
				viewPopup = popupContainer.getChildAt(0);
			} else {
				background = popupContainer.getChildAt(0);
				viewPopup = popupContainer.getChildAt(1);
			}

			AnimatorSet animatorSet = new AnimatorSet();
			ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(viewPopup, "scaleX", 0f);
			ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(viewPopup, "scaleY", 0f);
			if (childCount > 1) {
				ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(background, "alpha", 0f);
				animatorSet.playTogether(scaleXAnimator, scaleYAnimator, alphaAnimator);
			} else {
				animatorSet.playTogether(scaleXAnimator, scaleYAnimator);
			}
			animatorSet.setDuration(300);
			animatorSet.setInterpolator(new AccelerateInterpolator(2));
			animatorSet.addListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					popupContainer.removeAllViews();
				}
			});
			animatorSet.start();
		}
	}

	public static boolean isShown() {
		RelativeLayout popupContainer = (RelativeLayout) Shared.activity.findViewById(R.id.popup_container);
		return popupContainer.getChildCount() > 0;
	}
}
