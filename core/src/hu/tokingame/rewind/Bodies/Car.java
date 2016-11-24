package hu.tokingame.rewind.Bodies;

import com.badlogic.gdx.physics.box2d.World;

import hu.tokingame.rewind.MyBaseClasses.OneSpriteStaticActor;
import hu.tokingame.rewind.MyBaseClasses.WorldBodyEditorLoader;

/**
 * Created by M on 11/24/2016.
 */

public class Car extends Body {

    OneSpriteStaticActor actor;

    public Car(World world, WorldBodyEditorLoader loader, float X, float Y) {
        super(world, loader, "bluecar.png",  new OneSpriteStaticActor(randomTexture()), X, Y);
        actor = (OneSpriteStaticActor)getChildren().get(0);
        actor.setSize(0.25f,0.5f);
        setSize(getWidth()/4, getHeight()/4);
    }

    private static String randomTexture(){
        int a = (int)(Math.random() * (4-1+1)+1);
        switch (a){
            case 1 : return "GameTextures/bluecar.png";
            case 2 : return "GameTextures/greencar.png";
            case 3 : return "GameTextures/redcar.png";
            case 4 : return "GameTextures/orangecar.png";
            default: return"GameTextures/bluecar.png";
        }
    }
}
