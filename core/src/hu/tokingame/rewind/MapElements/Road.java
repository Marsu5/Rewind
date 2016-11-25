package hu.tokingame.rewind.MapElements;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import hu.tokingame.rewind.MyBaseClasses.OneSpriteStaticActor;
import hu.tokingame.rewind.MyBaseClasses.WorldActorGroup;
import hu.tokingame.rewind.MyBaseClasses.WorldBodyEditorLoader;

/**
 * Created by tuskeb on 2016. 11. 18..
 */

public class Road extends WorldActorGroup {
    public Road(World world, WorldBodyEditorLoader loader, String bodyID, OneSpriteStaticActor oneSpriteStaticActor, float X, float Y) {
        super(world, loader, bodyID, BodyDef.BodyType.StaticBody, 1, 0.01f, 100, false);
        addActor(oneSpriteStaticActor);
        oneSpriteStaticActor.setSize(1,1);
        setPosition(X,Y);
        addToWorld();
    }
}
