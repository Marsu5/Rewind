package hu.tokingame.rewind.MenuScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.tokingame.rewind.GameScreen.GameScreen;
import hu.tokingame.rewind.Global.Assets;
import hu.tokingame.rewind.Global.Globals;
import hu.tokingame.rewind.MyBaseClasses.MyStage;
import hu.tokingame.rewind.MyBaseClasses.MyTextButton;
import hu.tokingame.rewind.MyBaseClasses.OneSpriteAnimatedActor;
import hu.tokingame.rewind.MyBaseClasses.OneSpriteStaticActor;
import hu.tokingame.rewind.MyGdxGame;

/**
 * Created by davimatyi on 2016. 12. 07..
 */

public class LevelSelectStage extends MyStage {
    public LevelSelectStage(Viewport viewport, Batch batch, MyGdxGame game) {
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

        addActor(new OneSpriteAnimatedActor("ButtonAndOther/bg.txt"){
            @Override
            public void init() {
                super.init();
                setSize(Globals.WORLD_WIDTH, Globals.WORLD_HEIGHT);
                setFps(15);
            }
        });

        addActor(new MyTextButton("Tutorial"){
            @Override
            protected void init() {
                super.init();
                setTexture(Assets.manager.get(Assets.BUTTON_BG));
                setPosition(100, 500);
                addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        start(0);
                    }
                });
            }
        });
        addActor(new MyTextButton("Level 1"){
            @Override
            protected void init() {
                super.init();
                setTexture(Assets.manager.get(Assets.BUTTON_BG));
                setPosition(350, 500);
                if(Globals.unlockedLevels[1]) {
                    addListener(new ClickListener() {
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            super.clicked(event, x, y);
                            start(1);
                        }
                    });
                }else{
                    setText("Locked.");
                }
            }
        });
        addActor(new MyTextButton("Level 2"){
            @Override
            protected void init() {
                super.init();
                setTexture(Assets.manager.get(Assets.BUTTON_BG));
                setPosition(600, 500);
                if(Globals.unlockedLevels[2]) {
                    addListener(new ClickListener() {
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            super.clicked(event, x, y);
                            start(2);
                        }
                    });
                }else{
                    setText("Locked.");
                }
            }
        });
        addActor(new MyTextButton("Level 3"){
            @Override
            protected void init() {
                super.init();
                setTexture(Assets.manager.get(Assets.BUTTON_BG));
                setPosition(100, 250);
                if(Globals.unlockedLevels[3]) {
                    addListener(new ClickListener() {
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            super.clicked(event, x, y);
                            start(3);
                        }
                    });
                }else{
                    setText("Locked.");
                }
            }
        });
        addActor(new MyTextButton("Level 4"){
            @Override
            protected void init() {
                super.init();
                setTexture(Assets.manager.get(Assets.BUTTON_BG));
                setPosition(350, 250);
                if(Globals.unlockedLevels[4]) {
                    addListener(new ClickListener() {
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            super.clicked(event, x, y);
                            start(4);
                        }
                    });
                }else{
                    setText("Locked.");
                }
            }
        });
        addActor(new MyTextButton("Level 5"){
            @Override
            protected void init() {
                super.init();
                setTexture(Assets.manager.get(Assets.BUTTON_BG));
                setPosition(600, 250);
                if(Globals.unlockedLevels[5]) {
                    addListener(new ClickListener() {
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            super.clicked(event, x, y);
                            start(5);
                        }
                    });
                }else{
                    setText("Locked");
                }
            }
        });
        addActor(new MyTextButton("Back"){
            @Override
            protected void init() {
                super.init();
                setPosition(Globals.WORLD_WIDTH-this.getWidth(), 0);
                addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        game.setScreenBackByStackPop();
                    }
                });
            }
        });





    }

    private void start(int n){
        Assets.manager.get(Assets.MUSIC_MENU).stop();
        game.setScreen(new GameScreen(game, n));
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
