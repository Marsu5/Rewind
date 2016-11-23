package hu.tokingame.rewind.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import hu.tokingame.rewind.MapElements.FourWay;
import hu.tokingame.rewind.MapElements.Grass;
import hu.tokingame.rewind.MapElements.RoadHorizontal;
import hu.tokingame.rewind.MapElements.RoadVertical;
import hu.tokingame.rewind.MapElements.TurnLD;
import hu.tokingame.rewind.MapElements.TurnRD;
import hu.tokingame.rewind.MapElements.TurnUL;
import hu.tokingame.rewind.MapElements.TurnUR;
import hu.tokingame.rewind.MapElements.*;
import hu.tokingame.rewind.MyBaseClasses.MyStage;
import hu.tokingame.rewind.MyBaseClasses.WorldBodyEditorLoader;
import hu.tokingame.rewind.MyGdxGame;

/**
 * Created by davimatyi on 2016. 11. 20..
 */

public class GameStage extends MyStage{
    public static int level = 1;
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

        loader = new WorldBodyEditorLoader(Gdx.files.internal("Jsons/gravity.json")); // ezzel gondja van ha rátöltjük telefonra
        loadMap(level);
    }
    private void loadMap(int what){
        switch(what){
            case 0 : map = "CityMap/TOOTOROL1.txt"; nyamm = new char[15][15]; break;
            case 1 : map = "CityMap/level1.txt"; nyamm = new char[24][24]; break;
            case 10 : map = "CityMap/bonus.txt"; nyamm = new char[25][25]; break;
        }
        Scanner be;
        try{
            be = new Scanner(Gdx.files.internal(map).reader());
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
            System.out.println(e);
            System.out.println("nincsen filé");
        }



        for (int i = nyamm.length -1 ;i >= 0; i--){
            for (int j = 0; j < nyamm[0].length; j++){
                char c = nyamm[i][j];
                System.out.print(c);
                addMapElement(c,j,i);
            }
            System.out.println("\n");
        }


    }


    private void addMapElement(char c, int j, int i){
        switch(c){
            case '1' : addActor(new RoadVertical(world, loader, j, nyamm[0].length - i)); break;
            case '2' : addActor(new RoadHorizontal(world, loader, j, nyamm[0].length -i)); break;
            case '5' : addActor(new TurnUL(world, loader, j, nyamm[0].length -i)); break;
            case '4' : addActor(new TurnUR(world, loader, j, nyamm[0].length -i)); break;
            case '6' : addActor(new TurnRD(world, loader, j, nyamm[0].length -i)); break;
            case '3' : addActor(new TurnLD(world, loader, j, nyamm[0].length -i)); break;
            case 'B' : addActor(new FourWay(world, loader, j, nyamm[0].length -i)); break;
            case '0' : addActor(new Grass(world, loader, j, nyamm[0].length -i)); break;
            case 'C' : addActor(new Decoration(world, loader, j, nyamm[0].length -i, (int)(Math.random()*5-1+1)+1)); break;
            case '9' : addActor(new Tup(world, loader, j, nyamm[0].length - i)); break;
            case '8' : addActor(new Tleft(world, loader, j, nyamm[0].length -i)); break;
            case 'A' : addActor(new Tright(world, loader , j, nyamm[0].length -i)); break;
            case '7' : addActor(new Tdown(world, loader, j, nyamm[0].length -i)); break;
            case 'E' : addActor(new DeadEndUp(world, loader, j, nyamm[0].length-i)); break;
            case 'F' : addActor(new DeadEndRight(world, loader, j, nyamm[0].length -i)); break;
            case 'G' : addActor(new DeadEndDown(world, loader, j, nyamm[0].length -i)); break;
            case 'H' : addActor(new DeadEndLeft(world, loader, j, nyamm[0].length -i)); break;

        }
    }

}
