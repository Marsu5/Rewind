package hu.tokingame.rewind.MyBaseClasses;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import hu.tokingame.rewind.Globals.Assets;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class MyButton extends TextButton implements InitableInterface{

    private TextButtonStyle style;

    public MyButton(String text, TextButtonStyle style) {
        super(text, style);
        init();
    }

    public void setTexture(Texture texture) {
        style = new TextButtonStyle();
        style.up = new TextureRegionDrawable(new TextureRegion(texture));
        style.over = new TextureRegionDrawable(new TextureRegion(texture));
        style.down = new TextureRegionDrawable(new TextureRegion(texture));
        style.font = Assets.manager.get(Assets.CALIBRIL_FONT);
        this.setStyle(style);
    }

    public void init() {
    }
}
