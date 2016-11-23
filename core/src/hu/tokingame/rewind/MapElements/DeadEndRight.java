package hu.tokingame.rewind.MapElements;

import com.badlogic.gdx.physics.box2d.World;

import hu.tokingame.rewind.MyBaseClasses.OneSpriteStaticActor;
import hu.tokingame.rewind.MyBaseClasses.WorldBodyEditorLoader;

/**
 * Created by davim on 2016. 11. 23..
 */

public class DeadEndRight extends Road {
    public DeadEndRight(World world, WorldBodyEditorLoader loader, float X, float Y) {
        super(world, loader, "block.png", new OneSpriteStaticActor("Map/block.png"), X, Y);
    }
}
