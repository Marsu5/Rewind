package hu.tokingame.rewind.Global;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * Created by M on 12/9/2016.
 */

public class WriteUnlocked {
    public WriteUnlocked() {
    }

    static public void WriteUnlocked(){
        FileHandle file = Gdx.files.local("CityMap/unlocked.txt");
        String s = "";
        for (int i = 0; i < Globals.unlockedLevels.length; i++) {
            s+= Globals.unlockedLevels[i]+" ";
        }
        file.writeString(s, false);
    }

    static public void ReadUnlocked(){
        FileHandle file = Gdx.files.local("CityMap/unlocked.txt");
        String[] s = file.readString().split(" ");
        for (int i = 0; i < s.length-1; i++) {
            try {
                Globals.unlockedLevels[i] = Boolean.parseBoolean(s[i]);
            }catch (Exception e){}
        }
    }
}
