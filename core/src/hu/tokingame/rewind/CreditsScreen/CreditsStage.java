package hu.tokingame.rewind.CreditsScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.tokingame.rewind.GameScreen.GameScreen;
import hu.tokingame.rewind.Global.Assets;
import hu.tokingame.rewind.Global.Globals;
import hu.tokingame.rewind.MenuScreen.MenuScreen;
import hu.tokingame.rewind.MyBaseClasses.MyLabel;
import hu.tokingame.rewind.MyBaseClasses.MyStage;
import hu.tokingame.rewind.MyBaseClasses.MyTextButton;
import hu.tokingame.rewind.MyGdxGame;

/**
 * Created by davimatyi on 2016. 11. 15..
 */

public class CreditsStage extends MyStage {
    String s;
    public CreditsStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
        Gdx.input.setCatchBackKey(true);
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
        s = "Created by: Tokin Game\n" +
                "Creators:\n" +
                "  Marcell Schuh\n" +
                "  Matyas David\n" +
                "  Zoltan Kovacs\n" +
                "  Daniel Balint\n" +
                "Music used:\n" +
                "  Cosmic Sand - 9h00\n" +
                "  Stellar Dreams - The Getaway\n" +
                "  PowerCut - Friendly Zombie\n" +
                "  Cartridge 1987 - The Chase\n" +
                "  Lost Years - Converter(V2)\n" +
                "  Cartridge 1987 - MFOS\n" +
                "  Bourgeoisie - Industrial Sector 3083\n\n\n";
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
        addActor(new MyTextButton("clikc"){
            @Override
            protected void init() {
                super.init();
                setPosition(Globals.WORLD_WIDTH-this.getWidth(), Globals.WORLD_HEIGHT-this.getHeight());
                addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        game.setScreen(new GameScreen(game, 10));
                        Assets.manager.get(Assets.MUSIC_MENU).stop();
                    }
                });
            }
        });

    }
}