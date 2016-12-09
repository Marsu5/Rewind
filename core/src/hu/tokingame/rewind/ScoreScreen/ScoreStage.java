package hu.tokingame.rewind.ScoreScreen;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.tokingame.rewind.GameScreen.GameScreen;
import hu.tokingame.rewind.Global.Globals;
import hu.tokingame.rewind.MyBaseClasses.MyLabel;
import hu.tokingame.rewind.MyBaseClasses.MyStage;
import hu.tokingame.rewind.MyBaseClasses.MyTextButton;
import hu.tokingame.rewind.MyGdxGame;

/**
 * Created by M on 12/9/2016.
 */

public class ScoreStage extends MyStage{
    private float time;
    public boolean next = false;

    public ScoreStage(Viewport viewport, Batch batch, MyGdxGame game, float time) {
        super(viewport, batch, game);
        this.time = time;
    }

    @Override
    public void init() {
        addActor(new MyLabel("You completed the level in : " + time + " seconds.", MyLabel.style1){
            @Override
            public void init() {
                super.init();
                this.setPosition(Globals.WORLD_WIDTH/2f - this.getWidth(),Globals.WORLD_HEIGHT/2f - this.getHeight());
            }
        });
        addActor(new MyTextButton("Continue"){
            @Override
            protected void init() {
                super.init();
                this.setPosition(Globals.WORLD_WIDTH-this.getWidth(), 0);
                addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        next = true;
                    }
                });
            }
        });

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(next){
            game.setScreen(new GameScreen(game,Globals.currenLevel));
        }
    }

    @Override
    public void draw() {
        super.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
    }


}
