package hu.tokingame.rewind.MyBaseClasses;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by M on 12/1/2016.
 */

public class UpDownButton extends OneSpriteStaticActor implements InitableInterface {

    private Texture up, down;

    public UpDownButton(Texture up, Texture down) {
        super(up);
        this.up = up;
        this.down = down;
    }

    @Override
    public void init() {

    }

    public void pressed(){
        setTexture(this.down);
    }

    public void realesed(){
        setTexture(this.up);
    }

}
