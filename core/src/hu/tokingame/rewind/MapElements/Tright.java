package hu.tokingame.rewind.MapElements;

import com.badlogic.gdx.physics.box2d.World;

import hu.tokingame.rewind.MyBaseClasses.OneSpriteStaticActor;
import hu.tokingame.rewind.MyBaseClasses.WorldBodyEditorLoader;

/**
 * Created by davimatyi on 2016. 11. 22..
 */

public class Tright extends Road {
    public Tright(World world, WorldBodyEditorLoader loader, float X, float Y) {
        super(world, loader, "tr.png", new OneSpriteStaticActor("Map/tr.png"), X, Y);
    }
}
