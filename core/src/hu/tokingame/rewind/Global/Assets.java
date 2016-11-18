package hu.tokingame.rewind.Global;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;

/**
 * Created by M on 10/7/2016.
 */

public class Assets {

    public static AssetManager manager;

    static final FreetypeFontLoader.FreeTypeFontLoaderParameter fontParameter = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
    static {
        fontParameter.fontFileName = "./Fonts/calibril.ttf";
        fontParameter.fontParameters.size = 50;
        fontParameter.fontParameters.characters = hu.tokingame.rewind.Global.Globals.CHARS;
        fontParameter.fontParameters.color = Color.WHITE;
    }

    public static final AssetDescriptor<BitmapFont> CALIBRIL_FONT
            = new AssetDescriptor<BitmapFont>("./Fonts/calibril.ttf", BitmapFont.class, fontParameter);
    public static final AssetDescriptor<Texture> ROAD_VERTICAL = new AssetDescriptor<Texture>("./Map/vr.png", Texture.class);
    public static final AssetDescriptor<Texture> ROAD_HORIZONTAL = new AssetDescriptor<Texture>("./Map/hr.png", Texture.class);
    public static final AssetDescriptor<Texture> ROAD_BLOCK = new AssetDescriptor<Texture>("./Map/block.png", Texture.class);
    public static final AssetDescriptor<Texture> BUSH = new AssetDescriptor<Texture>("./Map/bush.png", Texture.class);
    public static final AssetDescriptor<Texture> FOUR_WAY = new AssetDescriptor<Texture>("./Map/4way.png", Texture.class);
    public static final AssetDescriptor<Texture> GRASS = new AssetDescriptor<Texture>("./Map/bush.png", Texture.class);
    public static final AssetDescriptor<Texture> ROCK_ONE = new AssetDescriptor<Texture>("./Map/rock1.png", Texture.class);
    public static final AssetDescriptor<Texture> ROCK_TWO = new AssetDescriptor<Texture>("./Map/rock2.png", Texture.class);
    public static final AssetDescriptor<Texture> ROCK_THREE = new AssetDescriptor<Texture>("./Map/rock3.png", Texture.class);
    public static final AssetDescriptor<Texture> T_D = new AssetDescriptor<Texture>("./Map/td.png", Texture.class);
    public static final AssetDescriptor<Texture> T_L = new AssetDescriptor<Texture>("./Map/tl.png", Texture.class);
    public static final AssetDescriptor<Texture> T_R = new AssetDescriptor<Texture>("./Map/tr.png", Texture.class);
    public static final AssetDescriptor<Texture> T_U = new AssetDescriptor<Texture>("./Map/tu.png", Texture.class);
    public static final AssetDescriptor<Texture> LEFT_TURN = new AssetDescriptor<Texture>("./Map/turnl.png", Texture.class);
    public static final AssetDescriptor<Texture> RIGHT_TURN = new AssetDescriptor<Texture>("./Map/turnr.png", Texture.class);
    public static final AssetDescriptor<Texture> LEFT_TURN_UP = new AssetDescriptor<Texture>("./Map/turnul.png", Texture.class);
    public static final AssetDescriptor<Texture> RIGHT_TURN_DOWN = new AssetDescriptor<Texture>("./Map/turnur.png", Texture.class);








    public static void prepare() {
        manager = new AssetManager();
        Texture.setAssetManager(manager);
    }

    public static void load(){
        FileHandleResolver resolver = new InternalFileHandleResolver();
        manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
        manager.setLoader(BitmapFont.class, ".otf", new FreetypeFontLoader(resolver));


        manager.load(CALIBRIL_FONT);
        manager.load(ROAD_VERTICAL);
        manager.load(ROAD_HORIZONTAL);
        manager.load(ROAD_BLOCK);
        manager.load(BUSH);
        manager.load(FOUR_WAY);
        manager.load(GRASS);
        manager.load(ROCK_ONE);
        manager.load(ROCK_TWO);
        manager.load(ROCK_THREE);
        manager.load(T_D);
        manager.load(T_L);
        manager.load(T_R);
        manager.load(T_U);
        manager.load(LEFT_TURN);
        manager.load(RIGHT_TURN);
        manager.load(LEFT_TURN_UP);
        manager.load(RIGHT_TURN_DOWN);

    }

    public static void afterLoaded(){

    }

    public static void unload(){
        manager.dispose();
    }

}
