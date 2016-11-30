package hu.tokingame.rewind.Global;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.physics.box2d.World;
import com.sun.corba.se.spi.ior.iiop.MaxStreamFormatVersionComponent;

/**
 * Created by M on 10/14/2016.
 */


public class Globals {
    public static final String CHARS = "0123456789öüóqwertzuiopőúasdfghjkléáűíyxcvbnm'+!%/=()ÖÜÓQWERTZUIOPŐÚASDFGHJKLÉÁŰÍYXCVBNM?:_*<>#&@{}[],-.";
    public static final float WORLD_WIDTH = 1280;
    public static final float WORLD_HEIGHT = 720;
    public static boolean AccelerometerAvailable = Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer);
}
