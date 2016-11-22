package hu.tokingame.rewind.MapElements;

import com.badlogic.gdx.physics.box2d.World;

import hu.tokingame.rewind.MyBaseClasses.OneSpriteStaticActor;
import hu.tokingame.rewind.MyBaseClasses.WorldBodyEditorLoader;

/**
 * Created by davimatyi on 2016. 11. 22..
 */

public class Decoration extends Road {
    public Decoration(World world, WorldBodyEditorLoader loader, float X, float Y, int t) {
        super(world, loader, s(t), new OneSpriteStaticActor(ss(t)), X, Y);
    }
    public static String s(int t){
        switch(t){
            case 1: return "bush.png";
            case 2: return "rock1.png";
            case 3: return "rock2.png";
            case 4: return "rock3.png";
            case 5: return "bush.png";
            default: return "bush.png";
        }
    }
    public static String ss(int t){
        switch(t){
            case 1: return "Map/bush.png";
            case 2: return "Map/rock1.png";
            case 3: return "Map/rock2.png";
            case 4: return "Map/rock3.png";
            case 5: return "Map/bush.png";
            default: return "Map/bush.png";
        }
    }

}
