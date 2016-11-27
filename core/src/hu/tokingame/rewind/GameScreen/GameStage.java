package hu.tokingame.rewind.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.tokingame.rewind.Bodies.Car;
import hu.tokingame.rewind.Global.Assets;
import hu.tokingame.rewind.Global.Globals;
import hu.tokingame.rewind.MapElements.*;
import hu.tokingame.rewind.MyBaseClasses.MyStage;
import hu.tokingame.rewind.MyBaseClasses.MyTextButton;
import hu.tokingame.rewind.MyBaseClasses.WorldBodyEditorLoader;
import hu.tokingame.rewind.MyGdxGame;

import static com.badlogic.gdx.Gdx.input;


/**
 * Created by davimatyi on 2016. 11. 20..
 */

public class GameStage extends MyStage{
    public static int level = 10;
    World world;
    WorldBodyEditorLoader loader;
    Car car;
    ControlStage controlStage;



    public GameStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }

    @Override
    public void init() {
        world = new World(new Vector2(0,0), false);
        loader = new WorldBodyEditorLoader(Gdx.files.internal("Jsons/physics.json"));
        MapLoader mapLoader = new MapLoader(level,this,world,loader).load();
        addActor(car = new Car(world, loader, 1,1));
        car.setPosition(4.5f,5.5f);
        controlStage = new ControlStage(getBatch(), game);
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(this);
        inputMultiplexer.addProcessor(controlStage);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }



    private boolean reverse = false;

    @Override
    public void act(float delta) {
        world.step(delta,10,10);
        super.act(delta);
        controlStage.act(delta);
        /*OrthographicCamera c = (OrthographicCamera)getCamera();
        c.zoom = 0.2f;
        set
        */
        //car.getBody().getMassData().center.set(getWidth()/2,getHeight()/2);
        setCameraMoveToXY(car.getX(), car.getY(), 0.12f + (0.5f * car.getSpeed()/car.maxSpeed), 3);
        if(input.isKeyPressed(Input.Keys.UP) || controlStage.isGasTouched){
            if (car.isStopped()){
                reverse = false;
            }
            if (reverse){
                car.brake(delta);
            }else {
                car.accelerate(delta);
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
        if(input.isKeyPressed(Input.Keys.LEFT)){
            car.turnLeft(delta);
        }
        if(input.isKeyPressed(Input.Keys.RIGHT)){
            car.turnRight(delta);
        }
    }

    @Override
    public void draw() {
        super.draw();
        controlStage.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
        controlStage.dispose();
    }

    @Override
    public void resize(int screenWidth, int screenHeight) {
        super.resize(screenWidth, screenHeight);
        controlStage.resize(screenWidth, screenHeight);
    }
}
