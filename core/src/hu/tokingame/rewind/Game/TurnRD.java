package hu.tokingame.rewind.Game;

import com.badlogic.gdx.physics.box2d.World;

import hu.tokingame.rewind.MyBaseClasses.OneSpriteStaticActor;
import hu.tokingame.rewind.MyBaseClasses.WorldBodyEditorLoader;

/**
 * Created by davimatyi on 2016. 11. 22..
 */

public class TurnRD extends Road {
    public TurnRD(World world, WorldBodyEditorLoader loader, float X, float Y) {
        super(world, loader, "turnr.png", new OneSpriteStaticActor("Map/turnr.png"), X, Y);
    }
}
