package hu.tokingame.rewind;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

/*
Help (hogyan játszd) képernyő
zene
credits (készítők)

 */

public class MyGdxGame extends Game {
	@Override
	public void create () {
		Assets.prepare();
		setScreen(new LoadingScreen(this));
	}

	@Override
	public void resume() {
		super.resume();
		Assets.manager.update();
	}
	
	@Override
	public void dispose () {
		super.dispose();
		Assets.unload();
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void setScreen(Screen screen){super.setScreen(screen);}
}
