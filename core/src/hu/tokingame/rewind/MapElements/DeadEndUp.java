package hu.tokingame.rewind.MapElements;

import com.badlogic.gdx.physics.box2d.World;

import hu.tokingame.rewind.MyBaseClasses.OneSpriteStaticActor;
import hu.tokingame.rewind.MyBaseClasses.WorldBodyEditorLoader;

/**
 * Created by davim on 2016. 11. 23..
 */

public class DeadEndUp extends Road {
    public DeadEndUp(World world, WorldBodyEditorLoader loader, float X, float Y) {
        super(world, loader, "blockup.png", new OneSpriteStaticActor("Map/blockup.png"), X, Y);
    }
}
