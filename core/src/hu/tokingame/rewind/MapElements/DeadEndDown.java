package hu.tokingame.rewind.MapElements;

import com.badlogic.gdx.physics.box2d.World;

import hu.tokingame.rewind.MyBaseClasses.OneSpriteStaticActor;
import hu.tokingame.rewind.MyBaseClasses.WorldBodyEditorLoader;

/**
 * Created by davim on 2016. 11. 23..
 */

public class DeadEndDown extends Road {
    public DeadEndDown(World world, WorldBodyEditorLoader loader, float X, float Y) {
        super(world, loader, "blockdown.png", new OneSpriteStaticActor("Map/blockdown.png"), X, Y);
    }
}
