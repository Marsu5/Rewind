package hu.tokingame.rewind.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.tokingame.rewind.Bodies.Barricade;
import hu.tokingame.rewind.Bodies.Car;
import hu.tokingame.rewind.CreditsScreen.CreditsScreen;
import hu.tokingame.rewind.Global.Assets;
import hu.tokingame.rewind.Global.Globals;
import hu.tokingame.rewind.MapElements.*;
import hu.tokingame.rewind.MenuScreen.MenuScreen;
import hu.tokingame.rewind.MyBaseClasses.MyStage;
import hu.tokingame.rewind.MyBaseClasses.WorldBodyEditorLoader;
import hu.tokingame.rewind.MyGdxGame;
import hu.tokingame.rewind.SettingsScreen.SettingsStage;

import static com.badlogic.gdx.Gdx.input;


/**
 * Created by davimatyi on 2016. 11. 20..
 */

public class GameStage extends MyStage{
    private final int level;
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


    @Override
    public void init() {

    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.BACK || keycode == Input.Keys.ESCAPE){
            pause = true;
        }
        return false;
    }


    public GameStage(Viewport viewport, Batch batch, final MyGdxGame game, int level) {
        super(viewport, batch, game);
        if(level == -1) game.setScreen(new CreditsScreen(game));
        this.level = level;
        System.out.println(level);

        Gdx.input.setCatchBackKey(true);

        world = new World(new Vector2(0,0), false);
        box2DDebugRenderer = new Box2DDebugRenderer();
        mapCreatingStage = new MapCreatingStage(getBatch(), game);
        controlStage = new ControlStage(getBatch(), game);
        bgStage = new BgStage(getBatch(), game);
        pauseStage = new PauseStage(getBatch(),game);

        loader = new WorldBodyEditorLoader(Gdx.files.internal("Jsons/physics.json"));
        //(new Thread(new MapLoader(level,this,world,loader))).start();
        mapLoader = new MapLoader(level, this, world, loader);
        turboOnFor = 0;
        if(level + 1 < Globals.levels.length) newlevel = level + 1;
        else newlevel = -1;



        //https://github.com/libgdx/libgdx/wiki/Threading
        new Thread(new Runnable() {
            boolean loop = true;
            @Override
            public void run() {
                mapLoader.load();
                while (loop)
                {
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
                addActor(car = new Car(world, loader, 1,1));

                car.setRotation(Globals.carRotation);
                car.setPosition(Globals.carPositionX,Globals.carPositionY);

                addActor(finish = new FinishSensor(world, Globals.finishPositionX, Globals.finishPositionY));
                finish.setRotation(Globals.finishRotation);
                startTimer();

            }
        }).start();





        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(this);
        inputMultiplexer.addProcessor(controlStage);
        Gdx.input.setInputProcessor(inputMultiplexer);

        rotationBase = Gdx.input.getAccelerometerY();


        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                if(contact.getFixtureA().getBody().getUserData() instanceof Car){
                    if (contact.getFixtureB().getBody().getUserData() instanceof Road || contact.getFixtureB().getBody().getUserData() instanceof Barricade) {
                        ((Car) contact.getFixtureA().getBody().getUserData()).crash();
                    }
                    if (contact.getFixtureB().getBody().getUserData() instanceof FinishSensor){
                        System.out.println(getTime());
                        Globals.unlockedLevels[newlevel] = true;
                        m.stop();
                        System.out.println("Next Level");
                        game.setScreen(new GameScreen(game,newlevel));
                    }
                } else {
                    if(contact.getFixtureB().getBody().getUserData() instanceof Car){
                        if (contact.getFixtureA().getBody().getUserData() instanceof Road || contact.getFixtureA().getBody().getUserData() instanceof Barricade) {
                            ((Car) contact.getFixtureB().getBody().getUserData()).crash();
                        }
                        if (contact.getFixtureA().getBody().getUserData() instanceof FinishSensor){
                            System.out.println(getTime());
                            Globals.unlockedLevels[newlevel] = true;
                            m.stop();
                            System.out.println("Next Level");
                            game.setScreen(new GameScreen(game,newlevel));
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

        switch(level){
            case 0: m = Assets.manager.get(Assets.MUSIC_TUTORIAL); break;
            case 1: m = Assets.manager.get(Assets.MUSIC_1); break;
            case 2: m = Assets.manager.get(Assets.MUSIC_2); break;
            case 3: m = Assets.manager.get(Assets.MUSIC_3); break;
            case 4: m = Assets.manager.get(Assets.MUSIC_4); break;
            case 5: m = Assets.manager.get(Assets.MUSIC_5); break;
            case 10: m = Assets.manager.get(Assets.SZIPU); break;
            default: m = Assets.manager.get(Assets.MUSIC_TUTORIAL); break;
        }
        m.play();

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
        }else{
            pauseStage.act(delta);
            return;
        }

        /*OrthographicCamera c = (OrthographicCamera)getCamera();
        c.zoom = 0.2f;
        set
        */
        //car.getBody().getMassData().center.set(getWidth()/2,getHeight()/2);
        if(controlStage.zoomOut){
            ExtendViewport vp = (ExtendViewport) getViewport();
            //setCameraMoveToXY( (vp.getWorldWidth() - vp.getMinWorldWidth()),  vp.getWorldWidth() / 2 , 1 + mapLoader.getWidth()>mapLoader.getHeight()?(float)mapLoader.getWidth() / 16.0f * 1.1f : (float)mapLoader.getHeight() / 16.0f * 1.1f, 10, -getCameraRotation());
            setCameraMoveToXY( mapLoader.getWidth()/2,  mapLoader.getHeight()/2+1 , 1 + mapLoader.getWidth()>mapLoader.getHeight()?(float)mapLoader.getWidth() / 16.0f * 1.1f : (float)mapLoader.getHeight() / 16.0f * 1.1f, 10, -getCameraRotation());
        }else {
            if(SettingsStage.cameraRotation){
                setCameraMoveToXY(car.getX(), car.getY(), 0.12f + (0.5f * car.getSpeed()/car.maxSpeed), 10, (float)Math.toDegrees(car.getBody().getAngle()));
                System.out.println(car.getBody().getAngle());
            }else{
                setCameraMoveToXY(car.getX(), car.getY(), 0.12f + (0.5f * car.getSpeed()/car.maxSpeed),10);
            }
        }
/*
        if(input.isKeyPressed(Input.Keys.ESCAPE)){
            game.setScreenBackByStackPop();
        }
*/
        if (controlStage.turboOn){
            controlStage.getTurbo().setTexture(Assets.manager.get(Assets.TURBO_ON));
            turbo = true;
            turboOnFor += delta;
            if(turboOnFor > 4){
                turboOnFor = 0;
                controlStage.turboOn = false;
                turbo = false;
                controlStage.getTurbo().setTexture(Assets.manager.get(Assets.TURBO_UNAVAILABLE));
                car.divSpeed(2f);

            }
        }
        if(input.isKeyPressed(Input.Keys.UP) || controlStage.isGasTouched){
            if (car.isStopped()){
                reverse = false;
            }
            if (reverse){
                car.brake(delta);
            }else {
                if (turbo){
                    car.accelerate(delta *4);
                }else {
                    car.accelerate(delta);
                }

            }
        }
        if(input.isKeyPressed(Input.Keys.DOWN) || controlStage.isBrakeTouched){
            if (car.isStopped()){
                reverse = true;
            }
            if (reverse){
                car.reverse(delta);
            }else{
                car.brake(delta);
            }
        }
        if(input.isKeyPressed(Input.Keys.LEFT)  || controlStage.turnLeft){
            car.turnLeft(60 * delta);
        };
        if(input.isKeyPressed(Input.Keys.RIGHT)  || controlStage.turnRight){
            car.turnRight(60 * delta);
        };
        System.out.println(" X: " + Gdx.input.getAccelerometerX() + " Y: " + Gdx.input.getAccelerometerY() + " Z: " + Gdx.input.getAccelerometerZ());
        if (Gdx.input.getAccelerometerY() < 0) {
            car.turnLeft(Math.abs(Gdx.input.getAccelerometerY()) * 6f * delta);
        }
        if(Gdx.input.getAccelerometerY() > 0){
            car.turnRight(Math.abs(Gdx.input.getAccelerometerY()) * 6f * delta);
        }
        //System.out.println(car.getX()+";"+car.getY()+";"+car.getRotation());
    }

    @Override
    public void draw() {
        if (mapLoader==null){
            return;
        }
        if (mapLoader.addNext() || car == null){
            mapCreatingStage.draw();
            return;
        }
        updateFrustum(1.25f);
        bgStage.draw();
        super.draw();
        if (pause){
            pauseStage.draw();
        }
        else{
            controlStage.draw();
        }
        box2DDebugRenderer.render(world, getCamera().combined);
    }

    @Override
    public void dispose() {

        controlStage.dispose();
        mapCreatingStage.dispose();
        //world.dispose();
        super.dispose();
    }

    @Override
    public void resize(int screenWidth, int screenHeight) {
        super.resize(screenWidth, screenHeight);
        controlStage.resize(screenWidth, screenHeight);
    }
}
