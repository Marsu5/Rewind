package hu.tokingame.rewind.LoadingScreen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import hu.tokingame.rewind.Globals.Assets;
import hu.tokingame.rewind.MenuScreen.MenuScreen;
import hu.tokingame.rewind.MenuScreen.MenuStage;
import hu.tokingame.rewind.MyBaseClasses.*;
import hu.tokingame.rewind.MyGdxGame;

/**
 * Created by M on 10/7/2016.
 */

public class LoadingScreen extends MyScreen {

    LoadingStage stage;


    private float elapsedTime = 0;

    public LoadingScreen(MyGdxGame game) {
        super(game);
        setBackGroundColor(0.498f, 0.498f, 0.498f);

    }

    @Override
    public void show() {
        Assets.manager.finishLoading();
        Assets.load();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        //if (elapsedTime > 2.0 && Assets.manager.update()) {
        if (Assets.manager.update() && elapsedTime > 0.5) {
            Assets.afterLoaded();
            game.setScreen(new MenuScreen(game));
        }
        //spriteBatch.begin();
        elapsedTime += delta;
        stage.act(delta);
        spriteBatch.setProjectionMatrix(stage.getCamera().combined);
        stage.draw();
        //Globals.FONT_HOBO_STD.draw(spriteBatch,"Betöltés: " + Assets.manager.getLoadedAssets() + "/" + (Assets.manager.getQueuedAssets()+Assets.manager.getLoadedAssets()) + " (" + ((int)(Assets.manager.getProgress()*100f)) + "%)",0,50);
        //spriteBatch.end();
    }

    @Override
    public void hide() {

    }

    @Override
    public void init() {
        super.init();
        stage = new LoadingStage(new ExtendViewport(1280,720,new OrthographicCamera(1280,720)), spriteBatch, game);
    }

    @Override
    public void dispose() {
        stage.dispose();
        super.dispose();
    }
}
