package hu.tokingame.rewind.MapElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import hu.tokingame.rewind.MyBaseClasses.MyStage;
import hu.tokingame.rewind.MyBaseClasses.WorldBodyEditorLoader;

/**
 * Created by M on 11/23/2016.
 */


public class MapLoader {
    private int level;
    private String map;
    private char[][] nyamm;
    private MyStage stage;
    private World world;
    private WorldBodyEditorLoader loader;

    public MapLoader(int level, MyStage stage, World world, WorldBodyEditorLoader loader) {
        this.stage = stage;
        this.level = level;
        this.world = world;
        this.loader = loader;
        //System.out.println("konstruktor");
    }

    public MapLoader load(){
        loadMap();
        //System.out.println("load");
        return this;
    }

    private void loadMap(){
        switch(level){
            case 0 : map = "CityMap/TOOTOROL1.txt"; break;
            case 1 : map = "CityMap/level1.txt"; break;
            case 10 : map = "CityMap/bonus.txt"; break;
        }
        System.out.println(map);
        Scanner be;
        try{
            be = new Scanner(Gdx.files.internal(map).reader());
            int sor = 0;
            //System.out.println("van file");
            while(be.hasNextLine()){
                String vonat = be.nextLine();
                //System.out.println(vonat.length());
                System.out.println("while");
                for(int i = 0; i < vonat.length(); i++){
                    //nyamm[sor][i] = vonat.charAt(i);
                    //System.out.println("for");
                    addMapElement(vonat.charAt(i),i,vonat.length()-sor);
                }
                sor++;
            }
        }catch(Exception e){
            System.out.println(e);
            System.out.println("nincsen filé");
        }


        /*
        for (int i = nyamm.length -1 ;i >= 0; i--){
            for (int j = 0; j < nyamm[0].length; j++){
                char c = nyamm[i][j];
                System.out.print(c);
                addMapElement(c,j,i);
            }
            System.out.println("\n");
        }
        */

    }


    private void addMapElement(char c, int j, int y){
        switch(c){
            case '1' : stage.addActor(new RoadVertical(world, loader, j, y)); break;
            case '2' : stage.addActor(new RoadHorizontal(world, loader, j, y)); break;
            case '5' : stage.addActor(new TurnUL(world, loader, j, y)); break;
            case '4' : stage.addActor(new TurnUR(world, loader, j, y)); break;
            case '6' : stage.addActor(new TurnRD(world, loader, j, y)); break;
            case '3' : stage.addActor(new TurnLD(world, loader, j, y)); break;
            case 'B' : stage.addActor(new FourWay(world, loader, j, y)); break;
            case '0' : stage.addActor(new Grass(world, loader, j, y)); break;
            case '9' : stage.addActor(new Tup(world, loader, j, y)); break;
            case '8' : stage.addActor(new Tleft(world, loader, j, y)); break;
            case 'A' : stage.addActor(new Tright(world, loader , j, y)); break;
            case '7' : stage.addActor(new Tdown(world, loader, j, y)); break;
            case 'E' : stage.addActor(new DeadEndUp(world, loader, j, y)); break;
            case 'F' : stage.addActor(new DeadEndRight(world, loader, j, y)); break;
            case 'G' : stage.addActor(new DeadEndDown(world, loader, j, y)); break;
            case 'H' : stage.addActor(new DeadEndLeft(world, loader, j, y)); break;
            case 'C' : stage.addActor(new Decoration(world, loader, j, y, (int)(Math.random()*(5-1+1)+1))); break;
        }
    }
}
