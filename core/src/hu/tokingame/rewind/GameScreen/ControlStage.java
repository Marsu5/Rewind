package hu.tokingame.rewind.GameScreen;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.tokingame.rewind.Global.Assets;
import hu.tokingame.rewind.MyBaseClasses.MyStage;
import hu.tokingame.rewind.MyBaseClasses.MyTextButton;
import hu.tokingame.rewind.MyGdxGame;

/**
 * Created by tuskeb on 2016. 11. 27..
 */

public class ControlStage extends MyStage {
    public boolean isGasTouched = false, isBrakeTouched = false, turboOn = false;

    MyTextButton Gaspedal, BreakPedal, Turbo;


    public ControlStage(Batch batch, MyGdxGame game) {
        super(new ExtendViewport(16,9), batch, game);
        setDebugAll(true);
    }

    @Override
    public void init() {
        addActor(Gaspedal = new MyTextButton(""){

            @Override
            protected void init() {
                super.init();
                this.setSize(3.5f, 4);
                this.setPosition(12.5f, 0);
                setTextureUpDown(Assets.manager.get(Assets.GASPEDAL_UP), Assets.manager.get(Assets.GASPEDAL_DOWN));
                addListener(new InputListener(){
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        isGasTouched = true;
                        return super.touchDown(event, x, y, pointer, button);
                    }

                    @Override
                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                        isGasTouched = false;
                        super.touchUp(event, x, y, pointer, button);
                    }

                    @Override
                    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                        super.exit(event, x, y, pointer, toActor);
                        isGasTouched = false;
                    }
                });
            }
        });

        addActor(BreakPedal = new MyTextButton(""){
            @Override
            protected void init() {
                super.init();
                this.setSize(4, 4);
                this.setPosition(0, 0);
                addListener(new InputListener(){
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        isBrakeTouched = true;
                        return super.touchDown(event, x, y, pointer, button);
                    }

                    @Override
                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                        isBrakeTouched = false;
                        super.touchUp(event, x, y, pointer, button);
                    }
                    @Override
                    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                        super.exit(event, x, y, pointer, toActor);
                        isBrakeTouched = false;
                    }
                });
            }
        });

        addActor(Turbo = new MyTextButton(""){
            @Override
            protected void init() {
                super.init();
                this.setSize(4,4);
                this.setPosition(0,BreakPedal.getHeight());
                this.setTexture(Assets.manager.get(Assets.CAR_RED));
                addListener(new InputListener(){
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        turboOn = true;
                        return super.touchDown(event, x, y, pointer, button);
                    }

                    @Override
                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                        super.touchUp(event, x, y, pointer, button);
                    }

                    @Override
                    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                        super.exit(event, x, y, pointer, toActor);
                    }
                });
            }
        });
    }

    public MyTextButton getTurbo(){
        return Turbo;
    }
}
