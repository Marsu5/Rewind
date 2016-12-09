package hu.tokingame.rewind.CreditsScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.tokingame.rewind.Global.Globals;
import hu.tokingame.rewind.MyBaseClasses.MyLabel;
import hu.tokingame.rewind.MyBaseClasses.MyStage;
import hu.tokingame.rewind.MyBaseClasses.MyTextButton;
import hu.tokingame.rewind.MyGdxGame;

/**
 * Created by davimatyi on 2016. 12. 09..
 */

public class HowToPlayStage extends MyStage {
    String s;

    public HowToPlayStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
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
        s = "How to play:\n" +
                " -if you are using on screen controls:\n" +
                "  -Turn the car using the two turn buttons\n" +
                "  -Accelerate using the right pedal, brake using\n the left one.\n" +
                " -If you are using Tilt controls:\n" +
                "  -Turn the car by tilting the phone left and right\n" +
                "  -Accelerate by using the right pedal, brake with\n the left one\n" +
                "\n" +
                " -Use turbo with the turbo button, the reloading\n time is 10 seconds\n" +
                " -You can zoom out to see the whole map by\n" +
                "  pressing the zoom-out button in\n the top left corner\n\n";
        addActor(new MyLabel(s, MyLabel.style2){
            @Override
            public void init() {
                super.init();
                setPosition(0, 0);
            }
        });
        addActor(new MyTextButton("Back"){
            @Override
            protected void init() {
                super.init();
                setPosition(Globals.WORLD_WIDTH - this.getWidth(), 0);
                addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        game.setScreenBackByStackPop();
                    }
                });
            }
        });

    }
}
