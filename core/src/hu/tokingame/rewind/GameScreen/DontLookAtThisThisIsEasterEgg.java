package hu.tokingame.rewind.GameScreen;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.tokingame.rewind.Global.Assets;
import hu.tokingame.rewind.Global.Globals;
import hu.tokingame.rewind.MyBaseClasses.MyStage;
import hu.tokingame.rewind.MyBaseClasses.MyTextButton;
import hu.tokingame.rewind.MyBaseClasses.OneSpriteStaticActor;
import hu.tokingame.rewind.MyGdxGame;

/**
 * Created by davimatyi on 2016. 12. 09..
 */

public class DontLookAtThisThisIsEasterEgg extends MyStage {
    public DontLookAtThisThisIsEasterEgg(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.BACK) {
            game.setScreenBackByStackPop();
        }
        return false;
    }

    @Override
    public void init() {
        addActor (new OneSpriteStaticActor(Assets.manager.get(Assets.POINTS_BACKGROUND)){
            @Override
            public void init() {
                super.init();
                this.setSize(Globals.WORLD_WIDTH, Globals.WORLD_HEIGHT);
                setPosition(0, 0);
            }
        });
        addActor(new MyTextButton("ey_b0ss"){
            @Override
            protected void init() {
                super.init();
                setPosition(Globals.WORLD_WIDTH / 2 - this.getWidth() / 2, Globals.WORLD_HEIGHT / 2 - this.getHeight() / 2);
                addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        Assets.manager.get(Assets.MUSIC_MENU).stop();
                        game.setScreen(new GameScreen(game, 10));
                    }
                });

            }
        });
    }
}

