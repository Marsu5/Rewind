package hu.tokingame.rewind.GameScreen;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import hu.tokingame.rewind.Global.Assets;
import hu.tokingame.rewind.MyBaseClasses.MyStage;
import hu.tokingame.rewind.MyBaseClasses.UpDownButton;
import hu.tokingame.rewind.MyGdxGame;
import hu.tokingame.rewind.SettingsScreen.SettingsStage;

/**
 * Created by tuskeb on 2016. 11. 27..
 */

public class ControlStage extends MyStage {
    public boolean isGasTouched = false, isBrakeTouched = false, turboOn = false, turnLeft = false, turnRight = false, zoomOut = false;

    UpDownButton Gaspedal, BrakePedal, Turbo, LeftButton, RightButton;
    private ControlStage controlStage;



    public ControlStage(Batch batch, MyGdxGame game) {
        super(new ExtendViewport(16,9), batch, game);
        setDebugAll(true);
    }

    @Override
    public void init() {

        controlStage = this;

        if (SettingsStage.onScreenMode){
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

            addActor(BrakePedal = new UpDownButton(Assets.manager.get(Assets.BRAKEPEDAL_UP), Assets.manager.get(Assets.BRAKEPEDAL_DOWN)){
                @Override
                public void init() {
                    super.init();
                    this.setSize(3.5f, 4);
                    this.setPosition(9, 0);
                    addListener(new InputListener(){
                        @Override
                        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                            isBrakeTouched = true;
                            return super.touchDown(event, x, y, pointer, button);
                        }

                        @Override
                        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                            super.touchUp(event, x, y, pointer, button);
                            isBrakeTouched = false;
                        }
                        @Override
                        public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                            super.exit(event, x, y, pointer, toActor);
                            isBrakeTouched = false;
                        }
                    });
                }
            });

            addActor(Turbo = new UpDownButton(Assets.manager.get(Assets.TURBO_OFF), Assets.manager.get(Assets.TURBO_ON)){
                @Override
                public void init() {
                    //super.init();
                    this.setSize(1.5f,1.5f);
                    this.setPosition(14.5f, 4);
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

            addActor(LeftButton = new UpDownButton(Assets.manager.get(Assets.STEERLEFT_DOWN),Assets.manager.get(Assets.STEERLEFT_UP)){
                @Override
                public void init() {
                    super.init();
                    this.setSize(3.5f, 4);
                    this.setPosition(0, 0);
                    addListener(new InputListener(){
                        @Override
                        public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                            super.exit(event, x, y, pointer, toActor);
                            turnLeft = false;
                        }

                        @Override
                        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                            turnLeft = true;
                            return super.touchDown(event, x, y, pointer, button);
                        }

                        @Override
                        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                            turnLeft = false;
                            super.touchUp(event, x, y, pointer, button);
                        }
                    });
                }
            });

            addActor(RightButton = new UpDownButton(Assets.manager.get(Assets.STEERRIGHT_DOWN),Assets.manager.get(Assets.STEERRIGHT_UP)){
                @Override
                public void init() {
                    super.init();
                    this.setSize(3.5f, 4);
                    this.setPosition(4, 0);
                    addListener(new InputListener(){
                        @Override
                        public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                            super.exit(event, x, y, pointer, toActor);
                            turnRight = false;
                        }

                        @Override
                        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                            turnRight = true;
                            return super.touchDown(event, x, y, pointer, button);
                        }

                        @Override
                        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                            super.touchUp(event, x, y, pointer, button);
                            turnRight = false;
                        }
                    });
                }
            });

            addActor(new UpDownButton(Assets.manager.get(Assets.ZOOMOUT), Assets.manager.get(Assets.ZOOMOUT)){
                @Override
                public void init() {
                    super.init();
                    setSize(1,1);
                    setPosition(10, 5);
                    addListener(new InputListener(){
                        @Override
                        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                            zoomOut = true;
                            return super.touchDown(event, x, y, pointer, button);
                        }

                        @Override
                        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                            super.touchUp(event, x, y, pointer, button);
                            zoomOut = false;
                        }

                        @Override
                        public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                            super.exit(event, x, y, pointer, toActor);
                            zoomOut = false;
                        }
                    });

                }
            });

        }else {
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

            addActor(new UpDownButton(Assets.manager.get(Assets.CAR_RED), Assets.manager.get(Assets.CAR_BLUE)){
                @Override
                public void init() {
                    super.init();
                    setSize(1,1);
                    setPosition(10, 5);
                    addListener(new InputListener(){
                        @Override
                        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                            zoomOut = true;
                            return super.touchDown(event, x, y, pointer, button);
                        }

                        @Override
                        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                            super.touchUp(event, x, y, pointer, button);
                            zoomOut = false;
                        }

                        @Override
                        public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                            super.exit(event, x, y, pointer, toActor);
                            zoomOut = false;
                        }
                    });

                }
            });
        }





    }

    public UpDownButton getTurbo(){
        return Turbo;
    }
}
