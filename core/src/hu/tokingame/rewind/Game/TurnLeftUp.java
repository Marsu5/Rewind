package hu.tokingame.rewind.Game;

import com.badlogic.gdx.physics.box2d.World;

import hu.tokingame.rewind.MyBaseClasses.OneSpriteStaticActor;
import hu.tokingame.rewind.MyBaseClasses.WorldBodyEditorLoader;

/**
 * Created by M on 11/22/2016.
 */

public class TurnLeftUp extends Road {
    public TurnLeftUp(World world, WorldBodyEditorLoader loader, float X, float Y) {
        super(world, loader, "turnul.png", new OneSpriteStaticActor("Map/turnul.png"), X, Y);
    }
}
