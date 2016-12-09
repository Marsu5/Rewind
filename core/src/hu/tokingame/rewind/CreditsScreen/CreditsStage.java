package hu.tokingame.rewind.CreditsScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.tokingame.rewind.MenuScreen.MenuScreen;
import hu.tokingame.rewind.MyBaseClasses.MyLabel;
import hu.tokingame.rewind.MyBaseClasses.MyStage;
import hu.tokingame.rewind.MyGdxGame;

/**
 * Created by davimatyi on 2016. 11. 15..
 */

public class CreditsStage extends MyStage {
    String s = "Created by: Tokin Game\n" +
            "Creators:\n" +
            "-Marcell Schuh\n" +
            "-Matyas David\n" +
            "-Zoltan Kovacs\n" +
            "-Daniel Balint\n" +
            "Music used:\n" +
            "Cosmic Sand - 9h00\n" +
            "Stellar Dreams - The Getaway\n" +
            "PowerCut - Friendly Zombie\n" +
            "Cartridge 1987 - The Chase\n" +
            "Lost Years - Converter(V2)\n" +
            "Cartridge 1987 - MFOS\n" +
            "Bourgeoisie - Industrial Sector 3083";
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

        addActor(new MyLabel(s, MyLabel.style1){
            @Override
            public void init() {
                super.init();
                setPosition(0, 0);
            }
        });

    }
}