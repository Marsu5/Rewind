package hu.tokingame.rewind.SettingsScreen;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import java.lang.String;

import hu.tokingame.rewind.Global.Globals;
import hu.tokingame.rewind.MyBaseClasses.MyStage;
import hu.tokingame.rewind.MyBaseClasses.MyTextButton;
import hu.tokingame.rewind.MyGdxGame;

/**
 * Created by davimatyi on 2016. 11. 15..
 */



public class SettingsStage extends MyStage {

    public static boolean cameraRoatation = true;
    final private static String camText = "Camera rotation: ";

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

    }
}