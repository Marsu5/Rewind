package hu.tokingame.rewind.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.IntFloatMap;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import hu.tokingame.rewind.Bodies.Car;
import hu.tokingame.rewind.MapElements.FourWay;
import hu.tokingame.rewind.MapElements.Grass;
import hu.tokingame.rewind.MapElements.RoadHorizontal;
import hu.tokingame.rewind.MapElements.RoadVertical;
import hu.tokingame.rewind.MapElements.TurnLD;
import hu.tokingame.rewind.MapElements.TurnRD;
import hu.tokingame.rewind.MapElements.TurnUL;
import hu.tokingame.rewind.MapElements.TurnUR;
import hu.tokingame.rewind.MapElements.*;
import hu.tokingame.rewind.MyBaseClasses.MyStage;
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
    }

    private boolean reverse = false;

    @Override
    public void act(float delta) {
        world.step(delta,20,20);
        super.act(delta);
        /*OrthographicCamera c = (OrthographicCamera)getCamera();
        c.zoom = 0.2f;
        set
        */
        //car.getBody().getMassData().center.set(getWidth()/2,getHeight()/2);
        setCameraMoveToXY(car.getX(), car.getY(), 0.12f + (0.5f * car.getSpeed()/car.maxSpeed), 3);
        if(input.isKeyPressed(Input.Keys.UP)){
            if (car.isStopped()){
                reverse = false;
            }
            if (reverse){
                car.brake(delta);
            }else {
                car.accelerate(delta);
            }
        }
        if(input.isKeyPressed(Input.Keys.DOWN)){
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

}
