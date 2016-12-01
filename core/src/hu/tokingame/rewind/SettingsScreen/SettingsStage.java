package hu.tokingame.rewind.SettingsScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import java.lang.String;

import hu.tokingame.rewind.Global.Globals;
import hu.tokingame.rewind.MyBaseClasses.MyLabel;
import hu.tokingame.rewind.MyBaseClasses.MyStage;
import hu.tokingame.rewind.MyBaseClasses.MyTextButton;
import hu.tokingame.rewind.MyGdxGame;

/**
 * Created by davimatyi on 2016. 11. 15..
 */



public class SettingsStage extends MyStage {

    public static boolean cameraRoatation = true;
    final private static String camText = "Camera rotation: ";

    public static boolean onScreenMode = false;
    final private static String onScrenn = "Controls: ";

    public SettingsStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }

    @Override
    public void init() {

        addActor(new MyTextButton("Back"){
            @Override
            protected void init() {
                super.init();
                this.setPosition(getViewport().getWorldWidth()-this.getWidth(),0);
                addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        game.setScreenBackByStackPop();
                    }
                });
            }
        });

        addActor(new MyTextButton(camText){
            @Override
            protected void init() {
                this.setPosition(120,0);
                super.init();
                final MyTextButton button = this;
                if(cameraRoatation)
                    this.setText(camText + "enabled");
                else
                    this.setText(camText + "disabled");
                addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        cameraRoatation = !cameraRoatation;
                        if(cameraRoatation)
                            button.setText(camText + "enabled");
                        else
                            button.setText(camText + "disabled");
                    }
                });
            }
        });

        if(Globals.AccelerometerAvailable){
            addActor(new MyTextButton(onScrenn){
                @Override
                protected void init() {
                    this.setPosition(getViewport().getWorldWidth()-this.getWidth()-150,getViewport().getWorldHeight()-this.getHeight());
                    super.init();
                    final MyTextButton button = this;
                    if(onScreenMode)
                        this.setText(onScrenn + "on screen");
                    else
                        this.setText(onScrenn + "tilt");
                    addListener(new ClickListener(){
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            super.clicked(event, x, y);
                            onScreenMode = !onScreenMode;
                            if(onScreenMode)
                                button.setText(onScrenn + "on screen");
                            else
                                button.setText(onScrenn + "tilt");
                        }
                    });
                }
            });
        }else {
            addActor(new MyLabel("Accelerometer is not available! \nOn screen controls will be enabled.",MyLabel.style1){
                @Override
                public void init() {
                    super.init();
                    this.setPosition(getViewport().getWorldWidth()-this.getWidth(),getViewport().getWorldHeight()-this.getHeight());
                }
            });
        }

    }
}