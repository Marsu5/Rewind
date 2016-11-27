package hu.tokingame.rewind.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
    public boolean isGasTouched = false, isBrakeTouched = false;

    MyTextButton Gaspedal, BreakPedal;


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
        addActor(Gaspedal = new MyTextButton(""){

            @Override
            protected void init() {
                super.init();
                this.setSize(1, 1);
                this.setPosition(5, 6);
                setTextureUpDown(Assets.manager.get(Assets.GASPEDAL_UP), Assets.manager.get(Assets.GASPEDAL_DOWN));
                addListener(new InputListener(){
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        isGasTouched = true;
                        return super.touchDown(event, x, y, pointer, button);
                    }

                    @Override
                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                        isGasTouched = false;
                        super.touchUp(event, x, y, pointer, button);
                    }

                    @Override
                    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                        super.exit(event, x, y, pointer, toActor);
                        isGasTouched = false;
                    }
                });
            }
        });

        /*addActor(BreakPedal = new MyTextButton(""){
            @Override
            protected void init() {
                super.init();
                this.setSize(100, 100);
                this.setPosition(0, 0);
                addListener(new InputListener(){
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        isBrakeTouched = true;
                        return super.touchDown(event, x, y, pointer, button);
                    }

                    @Override
                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                        isBrakeTouched = false;
                        super.touchUp(event, x, y, pointer, button);
                    }
                });
            }
        });*/

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
        if(input.isKeyPressed(Input.Keys.UP) || isGasTouched){
            if (car.isStopped()){
                reverse = false;
            }
            if (reverse){
                car.brake(delta);
            }else {
                car.accelerate(delta);
            }
        }
        if(input.isKeyPressed(Input.Keys.DOWN) || isBrakeTouched){
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
