package hu.tokingame.rewind.GameScreen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.tokingame.rewind.Global.Globals;
import hu.tokingame.rewind.MyBaseClasses.MyStage;
import hu.tokingame.rewind.MyBaseClasses.MyTextButton;
import hu.tokingame.rewind.MyGdxGame;

import static hu.tokingame.rewind.Global.Assets.MUSIC_MENU;
import static hu.tokingame.rewind.Global.Assets.manager;

/**
 * Created by tuskeb on 2016. 11. 27..
 */

public class MapCreatingStage extends MyStage {

    private MyTextButton percentActor;

    public MapCreatingStage(Batch batch, MyGdxGame game) {
        super(new ExtendViewport(Globals.WORLD_WIDTH,Globals.WORLD_HEIGHT,new OrthographicCamera(Globals.WORLD_WIDTH,Globals.WORLD_HEIGHT)), batch, game);
    }

    @Override
    public void init() {
        addActor(new MyTextButton("Building world..."){
            @Override
            protected void init() {
                super.init();
                setPosition(Globals.WORLD_WIDTH/2f - this.getWidth()/2f,Globals.WORLD_HEIGHT/2f - this.getHeight()/2f);
            }
        });
        addActor(percentActor = new MyTextButton("0 %"){
            @Override
            protected void init() {
                super.init();
                setPosition(Globals.WORLD_WIDTH/2f - this.getWidth()/2f,Globals.WORLD_HEIGHT/2f - this.getHeight()*2);
            }
        });
    }

    public void setPercent(int percent){
        percentActor.setText(percent + " %");
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
