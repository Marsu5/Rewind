package hu.tokingame.rewind.ScoreScreen;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.tokingame.rewind.GameScreen.GameScreen;
import hu.tokingame.rewind.Global.Assets;
import hu.tokingame.rewind.Global.Globals;
import hu.tokingame.rewind.Global.WriteUnlocked;
import hu.tokingame.rewind.MyBaseClasses.MyLabel;
import hu.tokingame.rewind.MyBaseClasses.MyStage;
import hu.tokingame.rewind.MyBaseClasses.MyTextButton;
import hu.tokingame.rewind.MyBaseClasses.OneSpriteStaticActor;
import hu.tokingame.rewind.MyGdxGame;

/**
 * Created by M on 12/9/2016.
 */

public class ScoreStage extends MyStage{
    private float time;
    public boolean next = false;

    public ScoreStage(Viewport viewport, Batch batch, MyGdxGame game, float time) {
        super(viewport, batch, game);
        this.time = Math.round(time * 100) / 100f;
        System.out.println("Stage Time" + this.time);
        addActor(new MyLabel("You completed the level in : " + this.time + " seconds.", MyLabel.style1){
            @Override
            public void init() {
                super.init();
                this.setPosition(Globals.WORLD_WIDTH/2f - this.getWidth()/2f,Globals.WORLD_HEIGHT/2f - this.getHeight()/2f);
            }
        });
    }

    @Override
    public void init() {
        WriteUnlocked.WriteUnlocked();
        addActor(new OneSpriteStaticActor(Assets.manager.get(Assets.POINTS_BACKGROUND)){
            @Override
            public void init() {
                super.init();
                this.setPosition(0,0);
                this.setSize(Globals.WORLD_WIDTH,Globals.WORLD_HEIGHT);
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
                        game.setScreen(new GameScreen(game,Globals.currenLevel));
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
