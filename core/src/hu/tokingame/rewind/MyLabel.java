package hu.tokingame.rewind;


        import com.badlogic.gdx.graphics.Color;
        import com.badlogic.gdx.graphics.Pixmap;
        import com.badlogic.gdx.scenes.scene2d.ui.Label;
        import com.badlogic.gdx.scenes.scene2d.ui.Skin;
        import com.badlogic.gdx.utils.Align;
/**
 * Created by davim on 2016. 10. 21..
 */

public class MyLabel extends Label {
    float elapsedtime =0;
    public static LabelStyle style;
    public static LabelStyle style2;
    public static LabelStyle style3;

    static {
        refresh();
    }

    public static void refresh() {
        style = new LabelStyle();
        style.font = Assets.manager.get(Assets.CALIBRIL_FONT);

        ;
        style.fontColor = Color.WHITE;


        style2 = new LabelStyle();
        style2.font = Assets.manager.get(Assets.DIGITAL_7_FONT);


        style2.fontColor = Color.WHITE;

        style3 = new LabelStyle();
        style3.font = Assets.manager.get(Assets.CALIBRIL_FONT);
        style3.fontColor = Color.BLACK;
    }

    public MyLabel(String text) {
        super(text, style);
        setAlignment(Align.center);
    }


    public MyLabel(CharSequence text, LabelStyle style) {
        super(text, style);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
