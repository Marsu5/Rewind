package hu.tokingame.rewind.MyBaseClasses;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Created by M on 10/21/2016.
 */

    import com.badlogic.gdx.graphics.Pixmap;
    import com.badlogic.gdx.graphics.Texture;
    import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import hu.tokingame.rewind.Global.Assets;

/**
     * Created by tuskeb on 2016. 09. 30..
     */
    public class MyTextButton extends TextButton{
        private TextButton button;
        static TextButtonStyle textButtonStyle;
        private TextButtonStyle style;
        private BitmapFont font;



        static {
            refresh();
        }

        public static void refresh()
        {
            textButtonStyle = new TextButtonStyle();
            textButtonStyle.font = Assets.manager.get(Assets.VERMIN_FONT_BIG);

            Pixmap p = new Pixmap(1,1, Pixmap.Format.Alpha);
            p.setColor(1);
            p.fill();
            textButtonStyle.up = new TextureRegionDrawable(new TextureRegion(new Texture(p)));

            //p.setColor(0.3f,0.5f,0.8f, 0);
            //p.fill();
            textButtonStyle.over = new TextureRegionDrawable(new TextureRegion(new Texture(p)));

            //p.setColor(0f,1f,0.1f, 0);
            //p.fill();
            textButtonStyle.down = new TextureRegionDrawable(new TextureRegion(new Texture(p)));

        }


        public MyTextButton(String text) {
            super(text, textButtonStyle);
            init();
        }


        public void setTexture(Texture texture) {
            style.up = new TextureRegionDrawable(new TextureRegion(texture));
            style.over = new TextureRegionDrawable(new TextureRegion(texture));
            style.down = new TextureRegionDrawable(new TextureRegion(texture));
            style.font = Assets.manager.get(Assets.VERMIN_FONT_BIG);
            this.setStyle(style);
        }
        public void setTextureUpDown(Texture up, Texture down){
            style.up = new TextureRegionDrawable(new TextureRegion(up));
            style.down = new TextureRegionDrawable(new TextureRegion(down));
            style.over = null;
            this.setStyle(style);
        }

        public void setFont(BitmapFont font){
            TextButtonStyle s = style;
            style = new TextButtonStyle();
            style.up = s.up;
            style.down = s.down;
            style.over = s.over;
            style.font = font;
            this.setStyle(style);
        }

        protected void init() {
            style = new TextButtonStyle();
            style.font = Assets.manager.get(Assets.VERMIN_FONT_BIG);
            Pixmap p = new Pixmap(1,1, Pixmap.Format.Alpha);
            p.setColor(1);
            p.fill();
            style.up = new TextureRegionDrawable(new TextureRegion(new Texture(p)));
            style.over = new TextureRegionDrawable(new TextureRegion(new Texture(p)));
            style.down = new TextureRegionDrawable(new TextureRegion(new Texture(p)));
            this.setStyle(style);
        }
    }
