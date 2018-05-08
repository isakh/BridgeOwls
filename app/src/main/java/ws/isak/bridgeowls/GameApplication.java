package ws.isak.bridgeowls;

import android.app.Application;

import ws.isak.bridgeowls.utils.FontLoader;

public class GameApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		FontLoader.loadFonts(this);

	}
}
