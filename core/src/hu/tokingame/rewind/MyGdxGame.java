package hu.tokingame.rewind;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import java.util.Stack;
import java.lang.reflect.InvocationTargetException;


/*
Help (hogyan játszd) képernyő
zene
credits (készítők)

 */

public class MyGdxGame extends Game {

	public final Stack<Class> backButtonStack = new Stack();

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
	public void setScreen(Screen screen){
		setScreen(screen,true);
	}

	public void setScreenBackByStackPop() {
		if (backButtonStack.size() > 1) {
			try {
				setScreen((MyScreen) backButtonStack.pop().getConstructor(MyGdxGame.class).newInstance(this), false);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		} else {
			Gdx.app.exit();
		}
	}

	public void setScreen(Screen screen, boolean pushToStack) {
		Screen prevScreen = getScreen();
		if (prevScreen != null) {
			if (pushToStack) {
				backButtonStack.push(prevScreen.getClass());
			}
			prevScreen.dispose();
		}
		super.setScreen(screen);
	}

}
