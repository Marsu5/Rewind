package hu.tokingame.rewind.GameScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.tokingame.rewind.MyBaseClasses.MyStage;
import hu.tokingame.rewind.MyBaseClasses.MyTextButton;
import hu.tokingame.rewind.MyGdxGame;

/**
 * Created by M on 12/8/2016.
 */

public class PauseStage  extends MyStage{

    private GameStage stage;
    private PauseStage thisStag;
    private float time=0;
    private boolean back = false;

    public PauseStage(Batch batch, MyGdxGame game, GameStage stage) {
        super(new ExtendViewport(512,288,new OrthographicCamera(512,288)), batch, game);
        this.stage = stage;
        thisStag = this;
    }



    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.BACK || keycode == Input.Keys.ESCAPE){
           if(time > 0.4){
                stage.pause = false;
                time = 0;
            }

        }
        return false;
    }



    @Override
    public void init() {
        Gdx.input.setCatchBackKey(true);

        addActor(new MyTextButton("Continue"){
            @Override
            protected void init() {
                super.init();
                this.setPosition(0,0);
                addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        stage.pause = false;
                    }
                });
            }
        });


        addActor(new MyTextButton("Exit"){
            @Override
            protected void init() {
                super.init();
                setPosition(512-this.getWidth(),0);
                addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        thisStag.addActor(new MyTextButton("Press to confirm"){
                            @Override
                            protected void init() {
                                super.init();
                                setPosition((512/2f)-this.getWidth()/2f,288/2f);
                                addListener(new ClickListener(){
                                    @Override
                                    public void clicked(InputEvent event, float x, float y) {
                                        super.clicked(event, x, y);
                                        Gdx.app.exit();
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(back){
            game.setScreenBackByStackPop();
        }
        time += delta;
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
