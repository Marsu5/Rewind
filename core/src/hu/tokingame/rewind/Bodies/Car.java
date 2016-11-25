package hu.tokingame.rewind.Bodies;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import hu.tokingame.rewind.Global.Assets;
import hu.tokingame.rewind.MyBaseClasses.OneSpriteStaticActor;
import hu.tokingame.rewind.MyBaseClasses.WorldActorGroup;
import hu.tokingame.rewind.MyBaseClasses.WorldBodyEditorLoader;

/**
 * Created by M on 11/24/2016.
 */

public class Car extends WorldActorGroup {

    OneSpriteStaticActor actor;

    public Car(World world, WorldBodyEditorLoader loader, float X, float Y) {
        super(world, loader, "bluecar.png", BodyDef.BodyType.DynamicBody, 1000, 0.2f, 10, false);
        actor = new OneSpriteStaticActor(Assets.manager.get(randomTexture()));
        actor.setSize(0.25f, 0.5f);
        setSize(getWidth() / 4, getHeight() / 4);
        addActor(actor);
        addToWorld();
    }

    private static AssetDescriptor<Texture> randomTexture() {
        int a = (int) (Math.random() * (4 - 1 + 1) + 1);
        switch (a) {
            case 1:
                return Assets.CAR_BLUE;
            case 2:
                return Assets.CAR_GREEN;
            case 3:
                return Assets.CAR_ORANGE;
            case 4:
                return Assets.CAR_RED;
            default:
                return Assets.CAR_BLUE;
        }
    }

    public float maxSpeed = 20;
    public float maxTurnVelocity = 1f;
    public float turnVelocity = 2f;
    public float accelerateVelocity = 8;
    public float frictionMultiplier = 0.95f;
    public float breakMultilier = 0.8f;
    public float turnMultiplier = 0.9f;

    public float turnTime = 0.2f;
    public float accelerateTime = 0.2f;

    private float accelerateTimer = 0;
    private float turnTimer = 0;

    public float getSpeed() {
        return getBody().getLinearVelocity().len();
    }

    public void accelerate(float delta) {
        accelerateTimer = accelerateTime;
        if (getSpeed() < maxSpeed) {
            getBody().applyForceToCenter(new Vector2(0, delta * accelerateVelocity).rotateRad(getBody().getAngle()), true);
        }
    }

    public void brake(float delta) {
        getBody().setLinearVelocity(getBody().getLinearVelocity().scl(breakMultilier));
    }

    public void reverse(float delta) {
        accelerateTimer = accelerateTime;
        getBody().applyForceToCenter(new Vector2(0, -delta * accelerateVelocity).rotateRad(getBody().getAngle()), true);
    }

    private float getTurnVelocity(){
        return Math.min(getBody().getLinearVelocity().len() * turnVelocity, maxTurnVelocity);
    }

    public void turnLeft(float delta) {
        turnTimer = turnTime;
        if (isReversing()){
            getBody().setAngularVelocity(-getTurnVelocity());
        }else {
            getBody().setAngularVelocity(getTurnVelocity());
        }
    }

    public void turnRight(float delta) {
        turnTimer = turnTime;
        if (isReversing()){
            getBody().setAngularVelocity(getTurnVelocity());
        }else{
            getBody().setAngularVelocity(-getTurnVelocity());
        }
    }

    public boolean isStopped() {
        return getSpeed() == 0;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (isReversing()) {
            getBody().setLinearVelocity(getBody().getLinearVelocity().rotateRad(getBody().getAngularVelocity() * delta));
        } else{
            getBody().setLinearVelocity(getBody().getLinearVelocity().rotateRad(getBody().getAngularVelocity() * delta));
        }
        if (turnTimer > 0) {
            turnTimer -= delta;
        } else {
            getBody().setAngularVelocity(getBody().getAngularVelocity() * (1 - ((1 - turnMultiplier) * delta * 60)));
        }
        if (accelerateTime!=accelerateTimer){
            if (getSpeed() < 0.05f) {
                getBody().setLinearVelocity(0, 0);
            }
        }
        if (accelerateTimer > 0) {
            accelerateTimer -= delta;
        } else {
            getBody().setLinearVelocity(getBody().getLinearVelocity().scl(1 - ((1 - frictionMultiplier) * delta * 60)));
        }

    }

    public boolean isReversing(){
        Vector2 v = getBody().getLinearVelocity().cpy();
        v.rotateRad(-getBody().getAngle());
        return v.y<0;
    }
}
