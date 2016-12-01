package hu.tokingame.rewind.GameScreen;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import hu.tokingame.rewind.Global.Assets;
import hu.tokingame.rewind.MyBaseClasses.MyStage;
import hu.tokingame.rewind.MyBaseClasses.MyTextButton;
import hu.tokingame.rewind.MyBaseClasses.UpDownButton;
import hu.tokingame.rewind.MyGdxGame;
import hu.tokingame.rewind.SettingsScreen.SettingsStage;

/**
 * Created by tuskeb on 2016. 11. 27..
 */

public class ControlStage extends MyStage {
    public boolean isGasTouched = false, isBrakeTouched = false, turboOn = false;

    UpDownButton Gaspedal, BrakePedal, Turbo;



    public ControlStage(Batch batch, MyGdxGame game) {
        super(new ExtendViewport(16,9), batch, game);
        setDebugAll(true);
    }

    @Override
    public void init() {

        if (SettingsStage.onScreenMode = true){
            //// TODO: 12/1/2016 Csinálj on screen controlt 
        }


        addActor(Gaspedal = new UpDownButton(Assets.manager.get(Assets.GASPEDAL_UP), Assets.manager.get(Assets.GASPEDAL_DOWN)){
            @Override
            public void init() {
                super.init();
                this.setSize(3.5f, 4);
                this.setPosition(12.5f, 0);
                addListener(new InputListener(){
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        isGasTouched = true;
                        pressed();
                        return super.touchDown(event, x, y, pointer, button);
                    }

                    @Override
                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                        isGasTouched = false;
                        realesed();
                        super.touchUp(event, x, y, pointer, button);
                    }

                    @Override
                    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                        super.exit(event, x, y, pointer, toActor);
                        realesed();
                        isGasTouched = false;
                    }
                });
            }
        });

        addActor(BrakePedal = new UpDownButton(Assets.manager.get(Assets.BRAKEPEDAL_UP), Assets.manager.get(Assets.BRAKEPEDAL_DOWN)){
            @Override
            public void init() {
                super.init();
                this.setSize(3.5f, 4);
                this.setPosition(0, 0);
                addListener(new InputListener(){
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        isBrakeTouched = true;
                        pressed();
                        return super.touchDown(event, x, y, pointer, button);
                    }

                    @Override
                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                        super.touchUp(event, x, y, pointer, button);
                        isBrakeTouched = false;
                        realesed();
                    }
                    @Override
                    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                        super.exit(event, x, y, pointer, toActor);
                        realesed();
                        isBrakeTouched = false;
                    }
                });
            }
        });

        addActor(Turbo = new UpDownButton(Assets.manager.get(Assets.TURBO_OFF), Assets.manager.get(Assets.TURBO_ON)){
            @Override
            public void init() {
                super.init();
                this.setSize(1.5f,1.5f);
                this.setPosition(4, 0);
                addListener(new InputListener(){
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        turboOn = true;
                        pressed();
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

    public UpDownButton getTurbo(){
        return Turbo;
    }
}
