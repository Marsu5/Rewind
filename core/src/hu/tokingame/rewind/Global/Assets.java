package hu.tokingame.rewind.Global;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;

import java.awt.Font;

/**
 * Created by M on 10/7/2016.
 */

public class Assets {

    public static AssetManager manager;

    static final FreetypeFontLoader.FreeTypeFontLoaderParameter fontParameter = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
    static {
        fontParameter.fontFileName = "Fonts/vermin_vibes_1989.ttf";
        fontParameter.fontParameters.size = 50;
        fontParameter.fontParameters.characters = hu.tokingame.rewind.Global.Globals.CHARS;
        fontParameter.fontParameters.color = Color.WHITE;
    }
    static final FreetypeFontLoader.FreeTypeFontLoaderParameter fontParameter2 = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
    static {
        fontParameter2.fontFileName = "Fonts/vermin_vibes_1989.ttf";
        fontParameter2.fontParameters.size = 100;
        fontParameter2.fontParameters.characters = hu.tokingame.rewind.Global.Globals.CHARS;
        fontParameter2.fontParameters.color = Color.WHITE;
    }

    // Fonts
    public static final AssetDescriptor<BitmapFont> VERMIN_FONT = new AssetDescriptor<BitmapFont>("Fonts/vermin_vibes_1989.ttf", BitmapFont.class, fontParameter);
    public static final AssetDescriptor<BitmapFont> VERMIN_FONT_BIG = new AssetDescriptor<BitmapFont>("Fonts/vermin_vibes_1989.ttf", BitmapFont.class, fontParameter2);

    //<editor-fold desc="Car Textures>
    public static final AssetDescriptor<Texture> CAR_BLUE = new AssetDescriptor<Texture>("GameTextures/bluecar.png", Texture.class);
    public static final AssetDescriptor<Texture> CAR_GREEN = new AssetDescriptor<Texture>("GameTextures/greencar.png", Texture.class);
    public static final AssetDescriptor<Texture> CAR_RED = new AssetDescriptor<Texture>("GameTextures/redcar.png", Texture.class);
    public static final AssetDescriptor<Texture> CAR_ORANGE = new AssetDescriptor<Texture>("GameTextures/orangecar.png", Texture.class);
    //</editor-fold>

    //<editor-fold desc="Map Elements">
    public static final AssetDescriptor<Texture> ROAD_VERTICAL = new AssetDescriptor<Texture>("Map/vr.png", Texture.class);
    public static final AssetDescriptor<Texture> ROAD_HORIZONTAL = new AssetDescriptor<Texture>("Map/hr.png", Texture.class);
    public static final AssetDescriptor<Texture> ROAD_BLOCK = new AssetDescriptor<Texture>("Map/block.png", Texture.class);
    public static final AssetDescriptor<Texture> BUSH = new AssetDescriptor<Texture>("Map/bush.png", Texture.class);
    public static final AssetDescriptor<Texture> FOUR_WAY = new AssetDescriptor<Texture>("Map/4way.png", Texture.class);
    public static final AssetDescriptor<Texture> GRASS = new AssetDescriptor<Texture>("Map/bush.png", Texture.class);
    public static final AssetDescriptor<Texture> ROCK_ONE = new AssetDescriptor<Texture>("Map/rock1.png", Texture.class);
    public static final AssetDescriptor<Texture> ROCK_TWO = new AssetDescriptor<Texture>("Map/rock2.png", Texture.class);
    public static final AssetDescriptor<Texture> ROCK_THREE = new AssetDescriptor<Texture>("Map/rock3.png", Texture.class);
    public static final AssetDescriptor<Texture> T_D = new AssetDescriptor<Texture>("Map/td.png", Texture.class);
    public static final AssetDescriptor<Texture> T_L = new AssetDescriptor<Texture>("Map/tl.png", Texture.class);
    public static final AssetDescriptor<Texture> T_R = new AssetDescriptor<Texture>("Map/tr.png", Texture.class);
    public static final AssetDescriptor<Texture> T_U = new AssetDescriptor<Texture>("Map/tu.png", Texture.class);
    public static final AssetDescriptor<Texture> LEFT_TURN = new AssetDescriptor<Texture>("Map/turnl.png", Texture.class);
    public static final AssetDescriptor<Texture> RIGHT_TURN = new AssetDescriptor<Texture>("Map/turnr.png", Texture.class);
    public static final AssetDescriptor<Texture> LEFT_TURN_UP = new AssetDescriptor<Texture>("Map/turnul.png", Texture.class);
    public static final AssetDescriptor<Texture> RIGHT_TURN_DOWN = new AssetDescriptor<Texture>("Map/turnur.png", Texture.class);
    public static final AssetDescriptor<Texture> GASPEDAL_UP = new AssetDescriptor<Texture>("GameTextures/gazpedal_up.png", Texture.class);
    public static final AssetDescriptor<Texture> GASPEDAL_DOWN = new AssetDescriptor<Texture>("GameTextures/gazpedal_down.png", Texture.class);
    public static final AssetDescriptor<Texture> BRAKEPEDAL_UP = new AssetDescriptor<Texture>("GameTextures/fekpedal_up.png", Texture.class);
    public static final AssetDescriptor<Texture> BRAKEPEDAL_DOWN = new AssetDescriptor<Texture>("GameTextures/fekpedal_down.png", Texture.class);
    public static final AssetDescriptor<Texture> TURBO_ON = new AssetDescriptor<Texture>("GameTextures/turbo.png", Texture.class);
    public static final AssetDescriptor<Texture> TURBO_OFF = new AssetDescriptor<Texture>("GameTextures/noturbo.png", Texture.class);
    public static final AssetDescriptor<Texture> STEERLEFT_UP = new AssetDescriptor<Texture>("GameTextures/steer_left.png", Texture.class);
    public static final AssetDescriptor<Texture> STEERLEFT_DOWN = new AssetDescriptor<Texture>("GameTextures/steer_left_down.png", Texture.class);
    public static final AssetDescriptor<Texture> STEERRIGHT_UP = new AssetDescriptor<Texture>("GameTextures/steer_right.png", Texture.class);
    public static final AssetDescriptor<Texture> STEERRIGHT_DOWN = new AssetDescriptor<Texture>("GameTextures/steer_right_down.png", Texture.class);
    public static final AssetDescriptor<Texture> PINGAS = new AssetDescriptor<Texture>("GameTextures/p.png", Texture.class);
    //</editor-fold>

    //<editor-fold desc="Music">
    public static final AssetDescriptor<Music> MUSIC_MENU = new AssetDescriptor<Music>("Audio/9h00.mp3", Music.class);
    public static final AssetDescriptor<Music> MUSIC_1 = new AssetDescriptor<Music>("Audio/friendly zombie.mp3", Music.class);
    public static final AssetDescriptor<Music> MUSIC_2 = new AssetDescriptor<Music>("Audio/chase.mp3", Music.class);
    public static final AssetDescriptor<Music> MUSIC_3 = new AssetDescriptor<Music>("Audio/converter.mp3", Music.class);
    public static final AssetDescriptor<Music> MUSIC_4 = new AssetDescriptor<Music>("Audio/MFOS.mp3", Music.class);
    public static final AssetDescriptor<Music> MUSIC_5 = new AssetDescriptor<Music>("Audio/3083.mp3", Music.class);
    public static final AssetDescriptor<Music> MUSIC_TUTORIAL = new AssetDescriptor<Music>("Audio/getaway.mp3", Music.class);
    public static final AssetDescriptor<Music> SZIPU = new AssetDescriptor<Music>("Audio/420.mp3", Music.class);
    //</editor-fold>




    public static void prepare() {
        manager = new AssetManager();
        Texture.setAssetManager(manager);
    }

    public static void load(){
        FileHandleResolver resolver = new InternalFileHandleResolver();
        manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
        manager.setLoader(BitmapFont.class, ".otf", new FreetypeFontLoader(resolver));

        //<editor-fold desc="Loading">
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
        manager.load(GASPEDAL_DOWN);
        manager.load(GASPEDAL_UP);
        manager.load(BRAKEPEDAL_DOWN);
        manager.load(BRAKEPEDAL_UP);
        manager.load(TURBO_OFF);
        manager.load(TURBO_ON);
        manager.load(STEERLEFT_DOWN);
        manager.load(STEERLEFT_UP);
        manager.load(STEERRIGHT_DOWN);
        manager.load(STEERRIGHT_UP);
        manager.load(PINGAS);

        //car textures
        manager.load(CAR_BLUE);
        manager.load(CAR_GREEN);
        manager.load(CAR_RED);
        manager.load(CAR_ORANGE);

        manager.load(VERMIN_FONT);
        manager.load(VERMIN_FONT_BIG);

        manager.load(MUSIC_MENU);
        manager.load(MUSIC_1);
        manager.load(MUSIC_2);
        manager.load(MUSIC_3);
        manager.load(MUSIC_4);
        manager.load(MUSIC_5);
        manager.load(MUSIC_TUTORIAL);
        manager.load(SZIPU);

        //</editor-fold>

    }

    public static void afterLoaded(){
        manager.get(MUSIC_MENU).setLooping(true);
        manager.get(MUSIC_TUTORIAL).setLooping(true);
        manager.get(MUSIC_1).setLooping(true);
        manager.get(MUSIC_2).setLooping(true);
        manager.get(MUSIC_3).setLooping(true);
        manager.get(MUSIC_4).setLooping(true);
        manager.get(MUSIC_5).setLooping(true);
        manager.get(SZIPU).setLooping(true);
        manager.get(MUSIC_MENU).play();
    }

    public static void unload(){
        manager.dispose();
    }

}
