package hu.tokingame.rewind.MenuScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import hu.tokingame.rewind.Global.Globals;
import hu.tokingame.rewind.MyBaseClasses.MyScreen;
import hu.tokingame.rewind.MyGdxGame;

/**
 * Created by davimatyi on 2016. 12. 07..
 */

public class LevelSelectScreen extends MyScreen {
    LevelSelectStage stage;

    public LevelSelectScreen(MyGdxGame game) {
        super(game);
        stage = new LevelSelectStage(new ExtendViewport(Globals.WORLD_WIDTH, Globals.WORLD_HEIGHT), spriteBatch, game);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        spriteBatch.setProjectionMatrix(stage.getCamera().combined);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        stage.resize(width, height);
    }

    @Override
    public void dispose() {
        stage.dispose();
        super.dispose();
    }
}
