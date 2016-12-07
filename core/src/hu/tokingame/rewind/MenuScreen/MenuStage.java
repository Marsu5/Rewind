package hu.tokingame.rewind.MenuScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.tokingame.rewind.CreditsScreen.CreditsScreen;
import hu.tokingame.rewind.GameScreen.GameScreen;
import hu.tokingame.rewind.Global.Assets;
import hu.tokingame.rewind.Global.Globals;
import hu.tokingame.rewind.MyBaseClasses.MyLabel;
import hu.tokingame.rewind.MyBaseClasses.MyStage;
import hu.tokingame.rewind.MyBaseClasses.MyTextButton;
import hu.tokingame.rewind.MyGdxGame;
import hu.tokingame.rewind.SettingsScreen.SettingsScreen;
import hu.tokingame.rewind.SettingsScreen.SettingsStage;

import static hu.tokingame.rewind.Global.Assets.MUSIC_MENU;
import static hu.tokingame.rewind.Global.Assets.manager;

/**
 * Created by M on 11/14/2016.
 */

public class MenuStage extends MyStage {

    MyTextButton button;

    public static boolean musicToggle = true;

    public MenuStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.BACK){
            game.setScreen(new ExitScreen(game));
        }
        return false;
    }

    @Override
    public void init() {

        if(!Globals.AccelerometerAvailable) addActor(new MyLabel("No Accelerometer Found.", MyLabel.style1){
            @Override
            public void init() {
                super.init();
                SettingsStage.onScreenMode = true;
                setPosition(600, 600);
            }
        });


        addActor(new MyTextButton("How To Play"){
            @Override
            protected void init() {
                super.init();
                setPosition(10, 100);
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
                setPosition(10, 200);
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
                this.setFont(Assets.manager.get(Assets.VERMIN_FONT_BIG));
                setPosition(10, 400);
                addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        game.setScreen(new GameScreen(game, 3));
                        manager.get(MUSIC_MENU).stop();
                    }
                });
            }

        });
        addActor(new MyTextButton("Credits"){
            @Override
            protected void init() {
                super.init();
                setPosition(10, 10);
                addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        game.setScreen(new SettingsScreen(game));
                    }
                });
            }
        });

        addActor(new MyTextButton("Music: "){
            @Override
            protected void init() {
                super.init();
                final String text = this.getText().toString();
                final MyTextButton button = this;
                if(musicToggle){
                    button.setText(text + "on");
                    Assets.manager.get(Assets.MUSIC_MENU).play();
                }
                else{
                    button.setText(text + "off");
                    Assets.manager.get(Assets.MUSIC_MENU).pause();
                }
                this.setPosition(Globals.WORLD_WIDTH-this.getWidth()-50,0);
                addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        musicToggle = !musicToggle;
                        if(musicToggle){
                            button.setText(text + "on");
                            Assets.manager.get(Assets.MUSIC_MENU).play();
                        }
                        else{
                            button.setText(text + "off");
                            Assets.manager.get(Assets.MUSIC_MENU).pause();
                        }
                    }
                });
            }
        });

    }
}

