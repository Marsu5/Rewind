package hu.tokingame.rewind.Bodies;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;

import hu.tokingame.rewind.Global.Assets;
import hu.tokingame.rewind.MyBaseClasses.OneSpriteStaticActor;
import hu.tokingame.rewind.MyBaseClasses.WorldBodyEditorLoader;

/**
 * Created by M on 11/24/2016.
 */

public class Car extends Body {

    OneSpriteStaticActor actor;

    public Car(World world, WorldBodyEditorLoader loader, float X, float Y) {
        super(world, loader, "bluecar.png",  new OneSpriteStaticActor(Assets.manager.get(randomTexture())), X, Y);
        actor = (OneSpriteStaticActor)getChildren().get(0);
        actor.setSize(0.25f,0.5f);
        setSize(getWidth()/4, getHeight()/4);
    }

    private static AssetDescriptor<Texture> randomTexture(){
        int a = (int)(Math.random() * (4-1+1)+1);
        switch (a){
            case 1 : return Assets.CAR_BLUE;
            case 2 : return Assets.CAR_GREEN;
            case 3 : return Assets.CAR_ORANGE;
            case 4 : return Assets.CAR_RED;
            default: return Assets.CAR_BLUE;
        }
    }
}
