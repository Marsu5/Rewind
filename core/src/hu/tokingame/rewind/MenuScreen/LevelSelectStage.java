package hu.tokingame.rewind.MenuScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.tokingame.rewind.Global.Globals;
import hu.tokingame.rewind.MyBaseClasses.MyStage;
import hu.tokingame.rewind.MyBaseClasses.OneSpriteAnimatedActor;
import hu.tokingame.rewind.MyBaseClasses.OneSpriteStaticActor;
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

        addActor(new OneSpriteStaticActor(""){ //tootoral
            @Override
            public void init() {
                super.init();
                this.setSize(200, 100);
                this.setPosition(100, 500);
            }
        });
        addActor(new OneSpriteStaticActor(""){ //level1
            @Override
            public void init() {
                super.init();
                this.setSize(200, 100);
                this.setPosition(100, 500);
            }
        });
        addActor(new OneSpriteStaticActor(""){ //level2
            @Override
            public void init() {
                super.init();
                this.setSize(200, 100);
                this.setPosition(100, 500);
            }
        });
        addActor(new OneSpriteStaticActor(""){ //level3
            @Override
            public void init() {
                super.init();
                this.setSize(200, 100);
                this.setPosition(100, 500);
            }
        });
        addActor(new OneSpriteStaticActor(""){ //level4
            @Override
            public void init() {
                super.init();
                this.setSize(200, 100);
                this.setPosition(100, 500);
            }
        });
        addActor(new OneSpriteStaticActor(""){ //level5
            @Override
            public void init() {
                super.init();
                this.setSize(200, 100);
                this.setPosition(100, 500);
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