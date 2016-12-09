package hu.tokingame.rewind.GameScreen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.tokingame.rewind.Global.Assets;
import hu.tokingame.rewind.MyBaseClasses.MyStage;
import hu.tokingame.rewind.MyBaseClasses.OneSpriteStaticActor;
import hu.tokingame.rewind.MyGdxGame;

/**
 * Created by tuskeb on 2016. 12. 08..
 */

public class BgStage extends MyStage {
    public BgStage(Batch batch, MyGdxGame game) {
        super(new FitViewport(16,9, new OrthographicCamera(16,9)), batch, game);
    }

    @Override
    public void init() {
        addActor(new OneSpriteStaticActor(Assets.manager.get(Assets.MAP_BG)){
            @Override
            public void init() {
                super.init();
                setSize(16,9);
            }
        });
    }
}
