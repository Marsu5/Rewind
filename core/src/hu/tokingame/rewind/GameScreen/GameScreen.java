package hu.tokingame.rewind.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import hu.tokingame.rewind.Global.Globals;
import hu.tokingame.rewind.MyBaseClasses.MyScreen;
import hu.tokingame.rewind.MyGdxGame;

/**
 * Created by davimatyi on 2016. 11. 20..
 */

public class GameScreen extends MyScreen {

    GameStage stage;
    Box2DDebugRenderer box2DDebugRenderer;

    public GameScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    public void init() {
        super.init();
        stage = new GameStage(new ExtendViewport(16,16,new OrthographicCamera(Globals.WORLD_WIDTH,Globals.WORLD_HEIGHT)),spriteBatch,game);
        //Gdx.input.setInputProcessor(stage);  //A stage adja hozzá. A stage hozza létre a control staget, ezért ott adja hozzá.
        box2DDebugRenderer = new Box2DDebugRenderer();
    }

    @Override
    public void dispose() {
        stage.dispose();
        super.dispose();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        stage.resize(width, height);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        spriteBatch.setProjectionMatrix(stage.getCamera().combined);
        stage.act(delta);
        stage.draw();
        box2DDebugRenderer.render(stage.world, stage.getCamera().combined);
    }
}
