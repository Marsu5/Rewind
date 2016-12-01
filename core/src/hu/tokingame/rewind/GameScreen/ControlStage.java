package hu.tokingame.rewind.GameScreen;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import hu.tokingame.rewind.Global.Assets;
import hu.tokingame.rewind.MyBaseClasses.MyStage;
import hu.tokingame.rewind.MyBaseClasses.MyTextButton;
import hu.tokingame.rewind.MyGdxGame;
import hu.tokingame.rewind.SettingsScreen.SettingsStage;

/**
 * Created by tuskeb on 2016. 11. 27..
 */

public class ControlStage extends MyStage {
    public boolean isGasTouched = false, isBrakeTouched = false, turboOn = false;

    MyTextButton Gaspedal, BrakePedal, Turbo;


    public ControlStage(Batch batch, MyGdxGame game) {
        super(new ExtendViewport(16,9), batch, game);
        setDebugAll(true);
    }

    @Override
    public void init() {

        if (SettingsStage.onScreenMode = true){
            //// TODO: 12/1/2016 Csinálj on screen controlt 
        }


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

        addActor(BrakePedal = new MyTextButton(""){
            @Override
            protected void init() {
                super.init();
                setTextureUpDown(Assets.manager.get(Assets.BRAKEPEDAL_UP), Assets.manager.get(Assets.BRAKEPEDAL_DOWN));
                this.setSize(3.5f, 4);
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
                this.setSize(1.5f,1.5f);
                this.setPosition(4, 0);
                this.setTexture(Assets.manager.get(Assets.TURBO_OFF));
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
