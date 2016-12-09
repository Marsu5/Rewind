package hu.tokingame.rewind.MapElements;

import com.badlogic.gdx.physics.box2d.World;

import hu.tokingame.rewind.Global.Assets;
import hu.tokingame.rewind.MyBaseClasses.OneSpriteStaticActor;
import hu.tokingame.rewind.MyBaseClasses.WorldBodyEditorLoader;

/**
 * Created by davimatyi on 2016. 12. 09..
 */

public class Weed extends Road {
    public Weed(World world, WorldBodyEditorLoader loader, float X, float Y) {
        super(world, loader, "grass.png", new OneSpriteStaticActor(Assets.manager.get(Assets.WEED)), X, Y);
    }
}
