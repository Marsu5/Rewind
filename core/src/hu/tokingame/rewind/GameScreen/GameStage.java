package hu.tokingame.rewind.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.tokingame.rewind.Bodies.Barricade;
import hu.tokingame.rewind.Bodies.Car;
import hu.tokingame.rewind.CreditsScreen.CreditsScreen;
import hu.tokingame.rewind.Global.Assets;
import hu.tokingame.rewind.Global.Globals;
import hu.tokingame.rewind.MapElements.*;
import hu.tokingame.rewind.MenuScreen.LevelSelectScreen;
import hu.tokingame.rewind.MenuScreen.MenuScreen;
import hu.tokingame.rewind.MyBaseClasses.MyStage;
import hu.tokingame.rewind.MyBaseClasses.WorldBodyEditorLoader;
import hu.tokingame.rewind.MyGdxGame;
import hu.tokingame.rewind.ScoreScreen.ScoreScreen;
import hu.tokingame.rewind.SettingsScreen.SettingsStage;

import static com.badlogic.gdx.Gdx.input;


/**
 * Created by davimatyi on 2016. 11. 20..
 */

public class GameStage extends MyStage {
    public static int level;
    World world;
    WorldBodyEditorLoader loader;
    Car car;
    ControlStage controlStage;
    MapCreatingStage mapCreatingStage;
    BgStage bgStage;
    MapLoader mapLoader;
    Box2DDebugRenderer box2DDebugRenderer;
    FinishSensor finish;
    PauseStage pauseStage;
    float turboOnFor;
    boolean turbo = false;
    float rotationBase;
    int newlevel;
    boolean pause = false;
    Music m;
    float leltime =0;

    boolean setNextLevel = false;

    @Override
    public void init() {

    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.BACK || keycode == Input.Keys.ESCAPE) {
            pause = true;
        }
        return false;
    }

    private Stage me;

    public GameStage(Viewport viewport, Batch batch, final MyGdxGame game, int level) {
        super(viewport, batch, game);
        me = this;
        if (level == -1) game.setScreen(new CreditsScreen(game));
            this.level = level;
            System.out.println(level);

            Gdx.input.setCatchBackKey(true);

            world = new World(new Vector2(0, 0), false);
            box2DDebugRenderer = new Box2DDebugRenderer();
            mapCreatingStage = new MapCreatingStage(new SpriteBatch(), game);
            controlStage = new ControlStage(new SpriteBatch(), game);
            bgStage = new BgStage(new SpriteBatch(), game);
            pauseStage = new PauseStage(new SpriteBatch(), game, this);

            loader = new WorldBodyEditorLoader(Gdx.files.internal("Jsons/physics.json"));
            //(new Thread(new MapLoader(level,this,world,loader))).start();
            mapLoader = new MapLoader(level, this, world, loader);
            turboOnFor = 0;
            if (level + 1 < Globals.levels.length) newlevel = level + 1;
            else newlevel = -1;



            //https://github.com/libgdx/libgdx/wiki/Threading
            new Thread(new Runnable() {
                boolean loop = true;

                @Override
                public void run() {
                    mapLoader.load();
                    while (loop) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Gdx.app.postRunnable(new Runnable() {
                            @Override
                            public void run() {
                                loop = mapLoader.addNext();
                            }
                        });
                    }
                    addActor(car = new Car(world, loader, 1, 1));

                    car.setRotation(Globals.carRotation);
                    car.setPosition(Globals.carPositionX, Globals.carPositionY);

                /*addActor(finish = new FinishSensor(world, Globals.finishPositionX, Globals.finishPositionY));
                finish.setRotation(Globals.finishRotation);*/
                    startTimer();
                    for (int i = 0; i < me.getActors().size; i++){
                        Actor a = me.getActors().get(i);
                        if (a instanceof Barricade) {
                            a.setZIndex(Integer.MAX_VALUE);
                        }
                        if (a instanceof Road) {
                            a.setZIndex(0);
                        }

                    }
                }
            }).start();


            InputMultiplexer inputMultiplexer = new InputMultiplexer();
            inputMultiplexer.addProcessor(this);
            inputMultiplexer.addProcessor(controlStage);
            inputMultiplexer.addProcessor(pauseStage);
            Gdx.input.setInputProcessor(inputMultiplexer);

            rotationBase = Gdx.input.getAccelerometerY();


        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                if(contact.getFixtureA().getBody().getUserData() instanceof Car){
                    if (contact.getFixtureB().getBody().getUserData() instanceof FinishSensor){
                        System.out.println("Time"+getTime());
                        setNextLevel = true;
                        m.stop();
                        System.out.println("Next Level");
                        //game.setScreen(new GameScreen(game,newlevel), false);
                    }
                } else {
                    if(contact.getFixtureB().getBody().getUserData() instanceof Car){
                        if (contact.getFixtureA().getBody().getUserData() instanceof FinishSensor){
                            System.out.println(getTime());
                            m.stop();
                            System.out.println("Next Level");
                            //game.setScreen(new GameScreen(game,newlevel));
                        }
                    }

                    }


                }

                @Override
                public void endContact(Contact contact) {

                }

                @Override
                public void preSolve(Contact contact, Manifold oldManifold) {

                }

                @Override
                public void postSolve(Contact contact, ContactImpulse impulse) {

                }
            });

            switch (level) {
                case 0:
                    m = Assets.manager.get(Assets.MUSIC_TUTORIAL);
                    break;
                case 1:
                    m = Assets.manager.get(Assets.MUSIC_1);
                    break;
                case 2:
                    m = Assets.manager.get(Assets.MUSIC_2);
                    break;
                case 3:
                    m = Assets.manager.get(Assets.MUSIC_3);
                    break;
                case 4:
                    m = Assets.manager.get(Assets.MUSIC_4);
                    break;
                case 5:
                    m = Assets.manager.get(Assets.MUSIC_5);
                    break;
                case 10:
                    m = Assets.manager.get(Assets.SZIPU);
                    break;
                default:
                    m = Assets.manager.get(Assets.MUSIC_TUTORIAL);
                    break;
            }
            if (Globals.music) m.play();

        }


        private boolean reverse = false;

    @Override
    public void act(float delta) {
        if (mapLoader.addNext() || car == null){
            mapCreatingStage.setPercent(mapLoader.getPercent());
            mapCreatingStage.act(delta);
            return;
        }
        if (!pause) {
            world.step(delta, 10, 10);
            super.act(delta);
            controlStage.act(delta);
            if (setNextLevel){
                if(newlevel == -1){
                    game.setScreen(new CreditsScreen(game));
                }else {
                    Globals.unlockedLevels[newlevel] = true;
                    Globals.currenLevel = newlevel;
                    System.out.println("dklfasjdklf time "+getTime());
                    game.setScreen(new ScoreScreen(game,getTime()),false);
                }

            }
        }else{
            pauseStage.act(delta);
            return;
        }

        /*OrthographicCamera c = (OrthographicCamera)getCamera();
        c.zoom = 0.2f;
        set
        */
            //car.getBody().getMassData().center.set(getWidth()/2,getHeight()/2);
            if (controlStage.zoomOut) {
                ExtendViewport vp = (ExtendViewport) getViewport();
                //setCameraMoveToXY( (vp.getWorldWidth() - vp.getMinWorldWidth()),  vp.getWorldWidth() / 2 , 1 + mapLoader.getWidth()>mapLoader.getHeight()?(float)mapLoader.getWidth() / 16.0f * 1.1f : (float)mapLoader.getHeight() / 16.0f * 1.1f, 10, -getCameraRotation());
                setCameraMoveToXY(mapLoader.getWidth() / 2, mapLoader.getHeight() / 2 + 1, 1 + mapLoader.getWidth() > mapLoader.getHeight() ? (float) mapLoader.getWidth() / 16.0f * 1.1f : (float) mapLoader.getHeight() / 16.0f * 1.1f, 10, -getCameraRotation());
            } else {
                if (SettingsStage.cameraRotation) {
                    setCameraMoveToXY(car.getX(), car.getY(), 0.12f + (0.1f * car.getSpeed() / car.maxSpeed), 10, (float) Math.toDegrees(car.getBody().getAngle()));
                } else {
                    setCameraMoveToXY(car.getX(), car.getY(), 0.12f + (0.1f * car.getSpeed() / car.maxSpeed), 10);
                }
            }
/*
        if(input.isKeyPressed(Input.Keys.ESCAPE)){
            game.setScreenBackByStackPop();
        }
*/
            if (controlStage.turboOn) {
                controlStage.getTurbo().setTexture(Assets.manager.get(Assets.TURBO_ON));
                turbo = true;
                turboOnFor += delta;
                if (turboOnFor > 4) {
                    turboOnFor = 0;
                    controlStage.turboOn = false;
                    turbo = false;
                    controlStage.getTurbo().setTexture(Assets.manager.get(Assets.TURBO_UNAVAILABLE));
                    car.divSpeed(2f);

                }
            }
            if (input.isKeyPressed(Input.Keys.UP) || controlStage.isGasTouched) {
                if (car.isStopped()) {
                    reverse = false;
                }
                if (reverse) {
                    car.brake(delta);
                } else {
                    if (turbo) {
                        car.accelerate(delta * 4);
                    } else {
                        car.accelerate(delta);
                    }

                }
            }
            if (input.isKeyPressed(Input.Keys.DOWN) || controlStage.isBrakeTouched) {
                if (car.isStopped()) {
                    reverse = true;
                }
                if (reverse) {
                    car.reverse(delta);
                } else {
                    car.brake(delta);
                }
            }
            if (input.isKeyPressed(Input.Keys.LEFT) || controlStage.turnLeft) {
                car.turnLeft(60 * delta);
            }
            ;
            if (input.isKeyPressed(Input.Keys.RIGHT) || controlStage.turnRight) {
                car.turnRight(60 * delta);
            }
            ;
            //System.out.println(" X: " + Gdx.input.getAccelerometerX() + " Y: " + Gdx.input.getAccelerometerY() + " Z: " + Gdx.input.getAccelerometerZ());
            if (Gdx.input.getAccelerometerY() < 0) {
                car.turnLeft(Math.abs(Gdx.input.getAccelerometerY()) * 6f * delta);
            }
            if (Gdx.input.getAccelerometerY() > 0) {
                car.turnRight(Math.abs(Gdx.input.getAccelerometerY()) * 6f * delta);
            }



            controlStage.setSpeed(car.getSpeed());



        if(car.health < 1){
            car.maxSpeed = 0;
            car.actor.setTexture(Assets.manager.get(Assets.CAR_GREEN_WRECKED));
            leltime += delta;

            if(leltime > 0.5f){
                car.maxSpeed= 200;
                m.stop();
                game.setScreen(new LevelSelectScreen(game), false);
            }

        }

        }

        @Override
        public void draw () {
            if (mapLoader == null) {
                return;
            }
            if (mapLoader.addNext() || car == null) {
                mapCreatingStage.draw();
                return;
            }
            updateFrustum(1.25f);
            bgStage.draw();
            super.draw();
            if (pause) {
                pauseStage.draw();
            } else {
                controlStage.draw();
            }
            //box2DDebugRenderer.render(world, getCamera().combined);
            //box2DDebugRenderer.render(world, getCamera().combined);
        }

        @Override
        public void dispose () {

        super.dispose();
        //world.dispose();

        controlStage.dispose();
        mapCreatingStage.dispose();
        pauseStage.dispose();
        bgStage.dispose();
    }

        @Override
        public void resize ( int screenWidth, int screenHeight){
            super.resize(screenWidth, screenHeight);
            controlStage.resize(screenWidth, screenHeight);
        }

    public float getCarVelocity() {
        return car.getSpeed();
    }
}
