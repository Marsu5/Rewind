package hu.tokingame.rewind.MapElements;

import com.badlogic.gdx.physics.box2d.World;

import hu.tokingame.rewind.MyBaseClasses.OneSpriteStaticActor;
import hu.tokingame.rewind.MyBaseClasses.WorldBodyEditorLoader;

/**
 * Created by davimatyi on 2016. 11. 22..
 */

public class Tup extends Road {
    public Tup(World world, WorldBodyEditorLoader loader, float X, float Y) {
        super(world, loader, "tu.png", new OneSpriteStaticActor("Map/tu.png"), X, Y);
    }
}
