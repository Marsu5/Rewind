package hu.tokingame.rewind.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.Rectangle;

import hu.tokingame.rewind.Bodies.Barricade;
import hu.tokingame.rewind.Bodies.Car;
import hu.tokingame.rewind.Global.Assets;
import hu.tokingame.rewind.MapElements.*;
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
    Barricade barricade;
    ControlStage controlStage;
    MapCreatingStage mapCreatingStage;
    MapLoader mapLoader;
    Box2DDebugRenderer box2DDebugRenderer;
    float turboOnFor;
    boolean turbo = false;
    float rotationBase;


    @Override
    public void init() {

    }

    public GameStage(Viewport viewport, Batch batch, final MyGdxGame game, int level) {
        super(viewport, batch, game);
        this.level = level;
        System.out.println(level);

        world = new World(new Vector2(0,0), false);
        box2DDebugRenderer = new Box2DDebugRenderer();
        mapCreatingStage = new MapCreatingStage(getBatch(), game);
        controlStage = new ControlStage(getBatch(), game);

        loader = new WorldBodyEditorLoader(Gdx.files.internal("Jsons/physics.json"));
        //(new Thread(new MapLoader(level,this,world,loader))).start();
        mapLoader = new MapLoader(level, this, world, loader);
        turboOnFor = 0;




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
                car.setPosition(3.5f,5.5f);
                addActor(barricade = new Barricade(world, loader, 1, 1));
                barricade.setPosition(3f, 6.5f);
                addActor(new FinishSensor(world, 3.5f, 6.5f));
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
                    if (contact.getFixtureB().getBody().getUserData() instanceof Road) {
                        ((Car) contact.getFixtureA().getBody().getUserData()).crash();
                    }
                    if (contact.getFixtureB().getBody().getUserData() instanceof FinishSensor){

                        System.out.println("Next Level");
                        game.setScreen(new GameScreen(game,1));
                    }
                } else {
                    if(contact.getFixtureB().getBody().getUserData() instanceof Car){
                        if (contact.getFixtureA().getBody().getUserData() instanceof Road) {
                            ((Car) contact.getFixtureB().getBody().getUserData()).crash();
                        }
                        if (contact.getFixtureA().getBody().getUserData() instanceof FinishSensor){
                            System.out.println("Next Level");
                            game.setScreen(new GameScreen(game,1));
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

    }



    private boolean reverse = false;

    @Override
    public void act(float delta) {
        //System.out.println(Gdx.input.getAccelerometerX()+ "; " +Gdx.input.getAccelerometerY()+ "; " +Gdx.input.getAccelerometerZ());

        if (controlStage.turboOn){
            controlStage.getTurbo().setTexture(Assets.manager.get(Assets.TURBO_ON));
            turbo = true;
            turboOnFor += delta;
            if(turboOnFor > 2){
                turboOnFor = 0;
                controlStage.turboOn = false;
                turbo = false;
                controlStage.getTurbo().setTexture(Assets.manager.get(Assets.TURBO_OFF));
                car.divSpeed(2f);

            }
        }

        if (mapLoader.addNext() || car == null){
            mapCreatingStage.setPercent(mapLoader.getPercent());
            mapCreatingStage.act(delta);
            return;
        }
        world.step(delta,10,10);
        super.act(delta);
        controlStage.act(delta);
        /*OrthographicCamera c = (OrthographicCamera)getCamera();
        c.zoom = 0.2f;
        set
        */
        //car.getBody().getMassData().center.set(getWidth()/2,getHeight()/2);
        if(SettingsStage.cameraRoatation){
            setCameraMoveToXY(car.getX(), car.getY(), 0.12f + (0.5f * car.getSpeed()/car.maxSpeed), 3, car.getRotation());
        }else{
            setCameraMoveToXY(car.getX(), car.getY(), 0.12f + (0.5f * car.getSpeed()/car.maxSpeed),3);
        }

        if(input.isKeyPressed(Input.Keys.ESCAPE)){
            game.setScreenBackByStackPop();
        }

        if(input.isKeyPressed(Input.Keys.UP) || controlStage.isGasTouched){
            if (car.isStopped()){
                reverse = false;
            }
            if (reverse){
                car.brake(delta);
            }else {
                if (turbo){
                    car.accelerate(delta *2);
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
        if(input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.getAccelerometerY()+1.5 < rotationBase || controlStage.turnLeft){
            car.turnLeft((Gdx.input.getAccelerometerY()-rotationBase) * 0.15f * delta);
        }
        if(input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.getAccelerometerY()-1.5 > rotationBase || controlStage.turnRight){
            car.turnRight((Gdx.input.getAccelerometerY()-rotationBase) * 0.15f * delta);
        }
    }

    @Override
    public void draw() {
        if (mapLoader.addNext() || car == null){
            mapCreatingStage.draw();
            return;
        }
        updateFrustum();
        super.draw();
        controlStage.draw();
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
