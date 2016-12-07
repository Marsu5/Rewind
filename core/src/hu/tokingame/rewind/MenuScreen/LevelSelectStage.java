package hu.tokingame.rewind.MenuScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.tokingame.rewind.Global.Globals;
import hu.tokingame.rewind.MyBaseClasses.MyStage;
import hu.tokingame.rewind.MyBaseClasses.OneSpriteAnimatedActor;
import hu.tokingame.rewind.MyGdxGame;

/**
 * Created by davimatyi on 2016. 12. 07..
 */

public class LevelSelectStage extends MyStage {
    public LevelSelectStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.BACK){
            game.setScreenBackByStackPop();
        }
        return false;
    }
    @Override
    public void init() {

        addActor(new OneSpriteAnimatedActor("ButtonAndOther/bg.txt"){
            @Override
            public void init() {
                super.init();
                setSize(Globals.WORLD_WIDTH, Globals.WORLD_HEIGHT);
                setFps(15);
            }
        });



    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
