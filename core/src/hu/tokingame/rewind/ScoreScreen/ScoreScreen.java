package hu.tokingame.rewind.ScoreScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import hu.tokingame.rewind.Global.Globals;
import hu.tokingame.rewind.MyBaseClasses.MyScreen;
import hu.tokingame.rewind.MyGdxGame;
import hu.tokingame.rewind.SettingsScreen.SettingsStage;

/**
 * Created by M on 12/9/2016.
 */

public class ScoreScreen extends MyScreen {

    private ScoreStage stage;
    private float time;

    public ScoreScreen(MyGdxGame game, float time) {
        super(game);
        this.time = time;
        System.out.println(this.time);
        init();
    }

    @Override
    public void init() {
        super.init();
        stage = new ScoreStage(new ExtendViewport(Globals.WORLD_WIDTH, Globals.WORLD_HEIGHT,new OrthographicCamera(Globals.WORLD_WIDTH,Globals.WORLD_HEIGHT)),spriteBatch,game, time);
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
