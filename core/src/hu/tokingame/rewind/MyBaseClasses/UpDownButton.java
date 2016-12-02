package hu.tokingame.rewind.MyBaseClasses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

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
        addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                pressed();
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                realesed();
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                super.exit(event, x, y, pointer, toActor);
                realesed();
            }
        });

    }

    public void pressed(){
        setTexture(this.down);
    }

    public void realesed(){
        setTexture(this.up);
    }

}
