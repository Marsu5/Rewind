package hu.tokingame.rewind.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.Rectangle;

import hu.tokingame.rewind.Bodies.Car;
import hu.tokingame.rewind.Global.Assets;
import hu.tokingame.rewind.MapElements.*;
import hu.tokingame.rewind.MyBaseClasses.MyStage;
import hu.tokingame.rewind.MyBaseClasses.WorldBodyEditorLoader;
import hu.tokingame.rewind.MyGdxGame;

import static com.badlogic.gdx.Gdx.input;


/**
 * Created by davimatyi on 2016. 11. 20..
 */

public class GameStage extends MyStage{
    public static int level = 2;
    World world;
    WorldBodyEditorLoader loader;
    Car car;
    ControlStage controlStage;
    MapCreatingStage mapCreatingStage;
    MapLoader mapLoader;
    Box2DDebugRenderer box2DDebugRenderer;
    float turboOnFor;
    boolean turbo = false;
    float rotationBase;


    public GameStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }

    @Override
    public void init() {
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
                car.setPosition(4.5f,5.5f);
            }
        }).start();






        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(this);
        inputMultiplexer.addProcessor(controlStage);
        Gdx.input.setInputProcessor(inputMultiplexer);

        rotationBase = Gdx.input.getAccelerometerY();
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
        setCameraMoveToXY(car.getX(), car.getY(), 0.12f + (0.5f * car.getSpeed()/car.maxSpeed), 3, car.getRotation());
        if(input.isKeyPressed(Input.Keys.UP) || controlStage.isGasTouched){
            if (car.isStopped()){
                reverse = false;
            }
            if (reverse){
                car.brake(delta);
            }else {
                if (turbo){
                    car.accelerate(delta *1.5f);
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
        if(input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.getAccelerometerY()+0.4 < rotationBase){
            car.turnLeft(delta);
        }
        if(input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.getAccelerometerY()-0.4 > rotationBase){
            car.turnRight(delta);
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
        super.dispose();
        controlStage.dispose();
        mapCreatingStage.dispose();
    }

    @Override
    public void resize(int screenWidth, int screenHeight) {
        super.resize(screenWidth, screenHeight);
        controlStage.resize(screenWidth, screenHeight);
    }
}
