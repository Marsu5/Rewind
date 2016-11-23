package hu.tokingame.rewind.LoadingScreen;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.tokingame.rewind.GameScreen.GameScreen;
import hu.tokingame.rewind.MyBaseClasses.MyLabel;
import hu.tokingame.rewind.MyBaseClasses.MyScreen;
import hu.tokingame.rewind.MyBaseClasses.MyStage;
import hu.tokingame.rewind.MyGdxGame;

/**
 * Created by davimatyi on 2016. 11. 23..
 */

public class GameLoadingStage extends MyStage {
    public GameLoadingStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }

    @Override
    public void init() {



        game.setScreen(new GameScreen(game));
    }
}
