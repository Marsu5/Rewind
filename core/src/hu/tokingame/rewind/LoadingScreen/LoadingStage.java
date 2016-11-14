package hu.tokingame.rewind.LoadingScreen;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.tokingame.rewind.MyBaseClasses.MyScreen;
import hu.tokingame.rewind.MyBaseClasses.MyStage;
import hu.tokingame.rewind.MyBaseClasses.OneSpriteAnimatedActor;
import hu.tokingame.rewind.MyGdxGame;

/**
 * Created by M on 11/14/2016.
 */

public class LoadingStage extends MyStage {

    private LoadingStage loadingStage;

    public LoadingStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }


    @Override
    public void init() {
        loadingStage = this;
        addActor(new OneSpriteAnimatedActor("loading.txt"){
            @Override
            public void init() {
                super.init();
                setFps(10);
                setSize(loadingStage.getViewport().getWorldWidth(),loadingStage.getViewport().getWorldHeight());
            }
        });
    }
}
