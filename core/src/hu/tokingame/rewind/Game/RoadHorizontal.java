package hu.tokingame.rewind.Game;

import com.badlogic.gdx.physics.box2d.World;

import hu.tokingame.rewind.MyBaseClasses.OneSpriteStaticActor;
import hu.tokingame.rewind.MyBaseClasses.WorldBodyEditorLoader;

/**
 * Created by M on 11/18/2016.
 */

public class RoadHorizontal extends Road {
    public RoadHorizontal(World world, WorldBodyEditorLoader loader, String bodyID, OneSpriteStaticActor oneSpriteStaticActor, float X, float Y) {
        super(world, loader, "Horizontal", new OneSpriteStaticActor("greencar.png"), X, Y);
    }
}
