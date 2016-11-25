package hu.tokingame.rewind.Bodies;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import hu.tokingame.rewind.MyBaseClasses.OneSpriteStaticActor;
import hu.tokingame.rewind.MyBaseClasses.WorldActorGroup;
import hu.tokingame.rewind.MyBaseClasses.WorldBodyEditorLoader;

/**
 * Created by M on 11/24/2016.
 */

public class Body extends WorldActorGroup{
    public Body(World world, WorldBodyEditorLoader loader, String bodyID, OneSpriteStaticActor oneSpriteStaticActor, float X, float Y) {
        super(world, loader, bodyID, BodyDef.BodyType.StaticBody, 1, 0.01f, 100, false);
        addActor(oneSpriteStaticActor);
        oneSpriteStaticActor.setSize(1,1);
        setPosition(X,Y);
        addToWorld();
    }
}
