package hu.tokingame.rewind;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by M on 10/7/2016.
 */

public class LoadingScreen extends MyScreen {

    Stage stage;

    private float elapsedTime = 0;

    public LoadingScreen(Game game) {
        super(game);
        setBackGroundColor(0f, 0f, 0f);
        stage = new Stage(viewport, spriteBatch);
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
        if (Assets.manager.update()) {
            Assets.afterLoaded();
            game.setScreen(new MenuScreen(game));
        }
        //spriteBatch.begin();
        elapsedTime += delta;
        stage.act(delta);
        stage.draw();
        //Globals.FONT_HOBO_STD.draw(spriteBatch,"Betöltés: " + Assets.manager.getLoadedAssets() + "/" + (Assets.manager.getQueuedAssets()+Assets.manager.getLoadedAssets()) + " (" + ((int)(Assets.manager.getProgress()*100f)) + "%)",0,50);
        //spriteBatch.end();
    }

    @Override
    public void hide() {

    }
}
