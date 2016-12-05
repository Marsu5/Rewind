package hu.tokingame.rewind.Bodies;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import hu.tokingame.rewind.Global.Assets;
import hu.tokingame.rewind.MyBaseClasses.OneSpriteStaticActor;
import hu.tokingame.rewind.MyBaseClasses.WorldActorGroup;
import hu.tokingame.rewind.MyBaseClasses.WorldBodyEditorLoader;

/**
 * Created by davim on 2016. 12. 01..
 */

public class Barricade extends WorldActorGroup{
    OneSpriteStaticActor actor;

    public Barricade(World world, WorldBodyEditorLoader loader, float X, float Y) {
        super(world, loader, "p.png", BodyDef.BodyType.DynamicBody, 1000, 0.2f, 10, false);
        actor = new OneSpriteStaticActor(Assets.manager.get(Assets.PINGAS));
        actor.setSize(0.25f, 0.5f);
        setSize(getWidth() / 4, getHeight() / 4);
        addActor(actor);
        addToWorld();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
