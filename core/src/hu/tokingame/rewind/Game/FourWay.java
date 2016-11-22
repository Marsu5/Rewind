package hu.tokingame.rewind.Game;

import com.badlogic.gdx.physics.box2d.World;

import hu.tokingame.rewind.MyBaseClasses.OneSpriteStaticActor;
import hu.tokingame.rewind.MyBaseClasses.WorldBodyEditorLoader;

/**
 * Created by M on 11/22/2016.
 */

public class FourWay extends Road {
    public FourWay(World world, WorldBodyEditorLoader loader, float X, float Y) {
        super(world, loader, "4way.png", new OneSpriteStaticActor("Map/4way.png"), X, Y);
    }
}
