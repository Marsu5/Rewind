package hu.tokingame.rewind;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by M on 10/28/2016.
 */

public class MenuStage extends MyStage {

    public MenuStage(Viewport viewport, Batch batch, Game game) {
        super(viewport, batch, game);
        Gdx.input.setCatchBackKey(true);
        init();
    }


    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.BACK){
            System.out.println("vissza");
        }
        return false;
    }

    @Override
    protected void init() {


    }

    protected void refresh(){

    }





}
