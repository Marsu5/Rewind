package hu.tokingame.rewind.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.tokingame.rewind.MyBaseClasses.MyStage;
import hu.tokingame.rewind.MyBaseClasses.MyTextButton;
import hu.tokingame.rewind.MyGdxGame;

/**
 * Created by M on 12/8/2016.
 */

public class PauseStage  extends MyStage{

    public PauseStage(Batch batch, MyGdxGame game) {
        super(new ExtendViewport(512,288,new OrthographicCamera(512,288)), batch, game);
    }




    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.BACK || keycode == Input.Keys.ESCAPE){
            //this.dispose();
        }
        return false;
    }



    @Override
    public void init() {
        Gdx.input.setCatchBackKey(true);

        addActor(new MyTextButton("fidnflksdjf;lskdjflskfjsdlkfjd;lkfajsdfklasdjl"));

    }
}
