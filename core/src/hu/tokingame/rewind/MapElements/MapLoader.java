package hu.tokingame.rewind.MapElements;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.FloatAction;
import com.badlogic.gdx.utils.Queue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import hu.tokingame.rewind.Bodies.Barricade;
import hu.tokingame.rewind.GameScreen.GameStage;
import hu.tokingame.rewind.Global.Assets;
import hu.tokingame.rewind.Global.Globals;
import hu.tokingame.rewind.MyBaseClasses.MyStage;
import hu.tokingame.rewind.MyBaseClasses.OneSpriteStaticActor;
import hu.tokingame.rewind.MyBaseClasses.WorldBodyEditorLoader;

/**
 * Created by M on 11/23/2016.
 */


public class MapLoader{

    private class MapElement{
        public int x;
        public int y;
        public char t;

        public MapElement(char t, int x, int y) {
            this.t = t;
            this.x = x;
            this.y = y;
        }
    }

    private Queue<MapElement> mapElements = new Queue<MapElement>();
    private int maxElements = 1;

    private final int level;
    private String map;
    private char[][] nyamm;
    private MyStage stage;
    private World world;
    private WorldBodyEditorLoader loader;
    private boolean running = true;

    private int width=0, height=0;

    public MapLoader(int level, MyStage stage, World world, WorldBodyEditorLoader loader) {
        this.stage = stage;
        this.level = level;
        this.world = world;
        this.loader = loader;
    }


    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public MapLoader load(){
        loadMap();
        return this;
    }

    private void loadMap(){
        Globals.barricadeRotation.clear();
        Globals.barricadeX.clear();
        Globals.barricadeY.clear();
        switch(level){
            case 0 : map = "CityMap/TOOTOROL1.txt"; break;
            case 1 : map = "CityMap/level1.txt"; break;
            case 2 : map = "CityMap/level2.txt"; break;
            case 3 : map = "CityMap/level3.txt"; break;
            case 4 : map = "CityMap/level4.txt"; break;
            case 5 : map = "CityMap/level5.txt"; break;
            case 10 : map = "CityMap/bonus.txt"; break;
        }
        Scanner be = new Scanner(System.in);
        try{
            be = new Scanner(Gdx.files.internal(map).reader());
            int sor = 0;
            while(be.hasNextLine()){
                String vonat = be.nextLine();
                if(vonat.charAt(0)==('#')) {
                    vonat = vonat.substring(1);
                    String[] carpos = vonat.split(" ");
                    Globals.carPositionX = Float.parseFloat(carpos[0]);
                    Globals.carPositionY = Float.parseFloat(carpos[1]);
                    Globals.carRotation = Float.parseFloat(carpos[2]);

                }
                else if(vonat.charAt(0) == '@'){
                    vonat = vonat.substring(1);
                    String[] sbarikad = vonat.split(" ");
                    final float[] barikad = new float[3];
                    barikad[0] = Float.parseFloat(sbarikad[0]);
                    barikad[1] = Float.parseFloat(sbarikad[1]);
                    barikad[2] = Float.parseFloat(sbarikad[2]);
                    stage.addActor(new Barricade(world, loader, 1, 1){
                        @Override
                        public void init() {
                            super.init();
                            this.setPosition(barikad[0], barikad[1]);
                            this.setRotation(barikad[2]);
                        }
                    });
                }
                else if(vonat.charAt(0)==('&')) {
                    vonat = vonat.substring(1);
                    String[] finpos = vonat.split(" ");
                    Globals.finishPositionX = Float.parseFloat(finpos[0]);
                    Globals.finishPositionY = Float.parseFloat(finpos[1]);
                    Globals.finishRotation = Float.parseFloat(finpos[2]);
                    stage.addActor(new FinishSensor(world, Globals.finishPositionX, Globals.finishPositionY){
                        @Override
                        public void init() {
                            super.init();
                            this.setPosition(Globals.finishPositionX, Globals.finishPositionY);
                            this.setRotation(Globals.finishRotation);
                            this.setSize(5f, 5f);
                        }
                    });
                }
                else{
                for(int i = 0; i < vonat.length(); i++){
                    mapElements.addLast(new MapElement(vonat.charAt(i),i,vonat.length()-sor));
                }
                    if (vonat.length()>width) width = vonat.length();
                sor++;
                lineAdded();
            }

            }
            height = sor;


        }catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
        be.close();
        maxElements = mapElements.size;
        running = false;
    }

    public boolean addNext(){
        if (mapElements.size==0){
            return false;
        }
        MapElement m = mapElements.removeFirst();
        if(m != null){
            addMapElement(m.t,m.x,m.y);
        }
        return true;
    }

    public boolean hasNext(){
        return mapElements.size == 0;
    }

    public int getPercent(){
        if (maxElements==0) return 100;
        return 100 - mapElements.size * 100 / maxElements;
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
            case 'C' : if(level != 10)stage.addActor(new Decoration(world, loader, j, y, (int)(Math.random()*(5-1+1)+1)));
                else stage.addActor(new Weed(world,loader, j, y)); break;
        }
    }

    protected void lineAdded(){
    }

}
