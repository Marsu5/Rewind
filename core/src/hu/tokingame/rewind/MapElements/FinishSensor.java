package hu.tokingame.rewind.MapElements;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import hu.tokingame.rewind.Global.Assets;
import hu.tokingame.rewind.MyBaseClasses.OneSpriteStaticActor;
import hu.tokingame.rewind.MyBaseClasses.ShapeType;
import hu.tokingame.rewind.MyBaseClasses.WorldActorGroup;

/**
 * Created by davim on 2016. 12. 02..
 */

public class FinishSensor extends WorldActorGroup {
    float x, y;
    public FinishSensor(World world, float x, float y) {
        super(world, ShapeType.Circle, BodyDef.BodyType.StaticBody, 1, 1, 1, true);
        setPosition(x,y);
        setSize(0.5f,0.5f);
        this.x = x; this.y = y;
    }

    @Override
    public void init() {
        super.init();
        addActor(new OneSpriteStaticActor(Assets.manager.get(Assets.FINISH)){
            @Override
            public void init() {
                super.init();
                setSize(0.5f, 0.5f);
                setPosition(x, y);
                setZIndex(Integer.MAX_VALUE);
            }
        });
        addToWorld();
    }
}
