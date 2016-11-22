package hu.tokingame.rewind.GameScreen;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Vector;

import hu.tokingame.rewind.Game.RoadHorizontal;
import hu.tokingame.rewind.Game.RoadVertical;
import hu.tokingame.rewind.MyBaseClasses.MyStage;
import hu.tokingame.rewind.MyBaseClasses.WorldBodyEditorLoader;
import hu.tokingame.rewind.MyGdxGame;

/**
 * Created by davimatyi on 2016. 11. 20..
 */

public class GameStage extends MyStage{
    public static int level = 0;
    private String map;
    private char[][] nyamm;
    World world;
    WorldBodyEditorLoader loader;





    public GameStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }

    @Override
    public void init() {
        world = new World(new Vector2(0,0), false);
        loader = new WorldBodyEditorLoader(new FileHandle("csizika.json")); //beírni neki valami helyes paramétert
        loadMap(level);
    }
    private void loadMap(int what){
        switch(what){
            case 0 : map = "./CityMap/TOOTOROL1.txt"; nyamm = new char[10][10]; break;
            case 1 : map = "./CityMap/level1.txt"; nyamm = new char[20][20]; break;
            case 2 : map = "./CityMap/level2.txt"; nyamm = new char[25][25]; break;
        }
        Scanner be;
        try{
            be = new Scanner(new FileReader(map));
            int sor = 0;
            while(be.hasNext()){

                String vonat = be.nextLine();
                System.out.println(vonat.length());
                for(int i=0; i<vonat.length(); i++){
                    nyamm[sor][i] = vonat.charAt(i);
                }
                sor++;
            }
        }catch(Exception e){
            System.out.println("nincsen filé");
        }
        for(int i = 0; i < nyamm.length; i++){
            for(int j = 0; j < nyamm[0].length; j++){
                System.out.print(nyamm[i][j]);
            }
            System.out.println();
        }


        for (int i = nyamm.length - 1;i > 0; i--){
            for (int j = 0; j < nyamm.length; j++){
                char c = nyamm[i][j];
                switch(c){
                    case '1' : addActor(new RoadVertical(world, loader, i, j)); break; //befejezni
                    case '2' : addActor(new RoadHorizontal(world, loader, i, j)); break; // ezt is
                }
            }
        }

    }
}
