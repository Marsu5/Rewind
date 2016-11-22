package hu.tokingame.rewind.Game;

import com.badlogic.gdx.physics.box2d.World;

import hu.tokingame.rewind.MyBaseClasses.OneSpriteStaticActor;
import hu.tokingame.rewind.MyBaseClasses.WorldBodyEditorLoader;

/**
 * Created by M on 11/18/2016.
 */

public class RoadHorizontal extends Road {
    public RoadHorizontal(World world, WorldBodyEditorLoader loader, float X, float Y) {
        super(world, loader, "hr.png", new OneSpriteStaticActor("Map/hr.png"), X, Y);
    }
}
