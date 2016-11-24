package hu.tokingame.rewind.Bodies;

import com.badlogic.gdx.physics.box2d.World;

import hu.tokingame.rewind.MyBaseClasses.OneSpriteStaticActor;
import hu.tokingame.rewind.MyBaseClasses.WorldBodyEditorLoader;

/**
 * Created by M on 11/24/2016.
 */

public class Car extends Body {
    public Car(World world, WorldBodyEditorLoader loader, String bodyID, float X, float Y) {
        super(world, loader, bodyID, new OneSpriteStaticActor(randomTexture()), X, Y);
    }

    private String randomTexture(){
        int a = (int)Math.random() * (4-1+1)+4;
        switch (a){
            case 1 : return "GameTextures/bluecar.png";  break;
            case 2: return "GameTextures/greencar.png"; break;
            default: return"GameTextures/bluecar.png"; break;
        }
    }
}
