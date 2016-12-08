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

    public static int[] levels = {0, 1, 2, 3, 4, 5};
    public static boolean[] unlockedLevels = {true, false, false, false, false, false};
    public static int currenLevel = 0;

    public static float carPositionX = 1;
    public static float carPositionY = 1;
    public static float carRotation = 0;


    public static float finishPositionX = 2;
    public static float finishPositionY = 2;
    public static float finishRotation = 0;


    public static java.util.Vector<Float> barricadeX = new java.util.Vector<Float>();
    public static java.util.Vector<Float> barricadeY = new java.util.Vector<Float>();
    public static java.util.Vector<Float> barricadeRotation = new java.util.Vector<Float>();


    public static boolean music = true;
}
