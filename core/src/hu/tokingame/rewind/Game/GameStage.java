package hu.tokingame.rewind.Game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Vector;

import hu.tokingame.rewind.MyBaseClasses.MyStage;
import hu.tokingame.rewind.MyGdxGame;

/**
 * Created by davimatyi on 2016. 11. 20..
 */

public class GameStage extends MyStage{
    public static int level = 0;
    private String map;
    //private Vector eloNyamm = new Vector();
    private char[][] nyamm;

    public GameStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }

    @Override
    public void init() {
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
            while(be.hasNext()){
                int sor = 0;
                String vonat = be.nextLine();
                System.out.println(vonat.length());
                for(int i=0; i<vonat.length() - 2; i++){
                    nyamm[sor][i] = vonat.charAt(i);
                }
                sor++;
            }
        }catch(Exception e){
            System.out.println("nincsen filé");
        }
        for(int i = 0; i < nyamm.length; i++){
            for(int j = 0; j < nyamm[0].length; j++){
                System.out.println(nyamm[i][j]);
            }
        }


    }
}
