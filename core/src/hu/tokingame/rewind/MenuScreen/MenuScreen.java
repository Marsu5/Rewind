package hu.tokingame.rewind.MenuScreen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import hu.tokingame.rewind.Globals.Globals;
import hu.tokingame.rewind.MyBaseClasses.MyScreen;
import hu.tokingame.rewind.MyGdxGame;

/**
 * Created by M on 11/14/2016.
 */

public class MenuScreen extends MyScreen {
    private MenuStage stage;
    public MenuScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    public void init() {
        super.init();
        stage = new MenuStage(new ExtendViewport(Globals.WORLD_WIDTH,Globals.WORLD_HEIGHT,new OrthographicCamera(Globals.WORLD_WIDTH,Globals.WORLD_HEIGHT)),spriteBatch,game);
    }
}
