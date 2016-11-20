package hu.tokingame.rewind.MenuScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.tokingame.rewind.CreditsScreen.CreditsScreen;
import hu.tokingame.rewind.Game.GameScreen;
import hu.tokingame.rewind.Global.Globals;
import hu.tokingame.rewind.MyBaseClasses.MyStage;
import hu.tokingame.rewind.MyBaseClasses.MyTextButton;
import hu.tokingame.rewind.MyGdxGame;
import hu.tokingame.rewind.SettingsScreen.SettingsScreen;

/**
 * Created by M on 11/14/2016.
 */

public class MenuStage extends MyStage {

    MyTextButton button;

    public MenuStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }
    
    @Override
    public void init() {
        addActor(new MyTextButton("How To Play"){
            @Override
            protected void init() {
                super.init();
                setPosition(Globals.WORLD_WIDTH-this.getWidth(), Globals.WORLD_HEIGHT-this.getHeight());
                addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        game.setScreen(new CreditsScreen(game));
                    }
                });
            }
        });
        addActor(button = new MyTextButton("Settings"){
            @Override
            protected void init() {
                super.init();
                setPosition(0, 0);
                addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        game.setScreen(new SettingsScreen(game));
                    }
                });
            }
        });
        addActor(new MyTextButton("Play"){
            @Override
            protected void init() {
                super.init();
                setPosition(Globals.WORLD_WIDTH/2f - this.getWidth()/2f,Globals.WORLD_HEIGHT/2f - this.getHeight()/2f);
                addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        game.setScreen(new GameScreen(game));
                    }
                });
            }
        });
        addActor(new MyTextButton("Credits"){
            @Override
            protected void init() {
                super.init();
                setPosition(0, button.getHeight());
                addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        game.setScreen(new SettingsScreen(game));
                    }
                });
            }
        });

    }
}

