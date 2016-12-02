package hu.tokingame.rewind.MapElements;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import hu.tokingame.rewind.MyBaseClasses.ShapeType;
import hu.tokingame.rewind.MyBaseClasses.WorldActorGroup;

/**
 * Created by davim on 2016. 12. 02..
 */

public class FinishSensor extends WorldActorGroup {
    public FinishSensor(World world, float x, float y) {
        super(world, ShapeType.Circle, BodyDef.BodyType.StaticBody, 1, 1, 1, true);
        setPosition(x,y);
        setSize(0.5f,0.5f);
    }

    @Override
    public void init() {
        super.init();
        addToWorld();
    }
}
