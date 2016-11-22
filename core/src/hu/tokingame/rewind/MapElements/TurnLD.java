package hu.tokingame.rewind.MapElements;

import com.badlogic.gdx.physics.box2d.World;

import hu.tokingame.rewind.MyBaseClasses.OneSpriteStaticActor;
import hu.tokingame.rewind.MyBaseClasses.WorldBodyEditorLoader;

/**
 * Created by davimatyi on 2016. 11. 22..
 */

public class TurnLD extends Road {
    public TurnLD(World world, WorldBodyEditorLoader loader, float X, float Y) {
        super(world, loader, "turnl.png", new OneSpriteStaticActor("Map/turnl.png"), X, Y);
    }
}
