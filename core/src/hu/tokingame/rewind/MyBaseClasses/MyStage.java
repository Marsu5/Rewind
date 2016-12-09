package hu.tokingame.rewind.MyBaseClasses;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.tokingame.rewind.Global.Globals;
import hu.tokingame.rewind.MyGdxGame;



/**
 * Created by tuskeb on 2016. 09. 30..
 */
abstract public class MyStage extends Stage implements InitableInterface {
    public final MyGdxGame game;

    private float timer = 0;
    private boolean timerRunning = false;

    public MyStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch);
        this.game = game;
        setCameraResetToCenterOfScreen();
        init();
    }



    public void addBackEventStackListener()    {
        addListener(new InputListener() {

            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if(keycode== Input.Keys.BACK || keycode == Input.Keys.ESCAPE) {
                    game.setScreenBackByStackPop();
                }
                return true;
            }
        });
    }

    public Actor getLastAdded() {
        return getActors().get(getActors().size-1);
    }

    public void setCameraZoomXY(float x, float y, float zoom)
    {
        OrthographicCamera c = (OrthographicCamera)getCamera();
        c.zoom=zoom;
        c.position.set(x,y,0);
        c.update();
    }

    private float cameraTargetX = 0;
    private float cameraTargetY = 0;
    private float cameraTargetZoom = 0;
    private float cameraMoveSpeed = 0;

    public float getCameraRotation() {
        return cameraRotation;
    }

    public void setCameraRotation(float cameraRotation) {
        this.cameraRotation = cameraRotation;
    }

    private float cameraRotation = 0;
    private float rotateTo = 0;

    public void setCameraMoveToXY(float x, float y, float zoom, float speed, float rotate)
    {
        cameraTargetX = x;
        cameraTargetY = y;
        cameraTargetZoom = zoom;
        cameraMoveSpeed = speed;
        rotateTo = rotate;

    }

    public void setCameraMoveToXY(float x, float y, float zoom, float speed)
    {
        cameraTargetX = x;
        cameraTargetY = y;
        cameraTargetZoom = zoom;
        cameraMoveSpeed = speed;
    }

    public void setCameraResetToCenterOfScreen()
    {
        if (getViewport() instanceof ExtendViewport) {
            OrthographicCamera c = (OrthographicCamera) getCamera();
            ExtendViewport v = (ExtendViewport) getViewport();
            c.setToOrtho(false, getViewport().getWorldWidth(), getViewport().getWorldHeight());
            c.translate((v.getWorldWidth() - v.getMinWorldWidth() / 2) < 0 ? 0 : -((v.getWorldWidth() - v.getMinWorldWidth()) / 2),
                    ((v.getWorldHeight() - v.getMinWorldHeight()) / 2) < 0 ? 0 : -((v.getWorldHeight() - v.getMinWorldHeight()) / 2));
            c.update();
        }
    }
    public void setCameraResetToLeftBottomOfScreen(){
        OrthographicCamera c = (OrthographicCamera)getCamera();
        Viewport v = getViewport();
        setCameraZoomXY(v.getWorldWidth()/2, v.getWorldHeight()/2,1);
        c.update();

    }

    public void resize(int screenWidth, int screenHeight){
        getViewport().update(screenWidth, screenHeight, true);
        resized();
    }

    protected void resized(){
        setCameraResetToCenterOfScreen();
    };

    @Override
    public void act(float delta) {
        super.act(delta);
        if (timerRunning){
            timer += delta;
        }
        OrthographicCamera c = (OrthographicCamera)getCamera();
        if (cameraTargetX!=c.position.x || cameraTargetY!=c.position.y || cameraTargetZoom!=c.zoom){
            if (Math.abs(c.position.x-cameraTargetX)<cameraMoveSpeed*delta) {
                c.position.x = (c.position.x + cameraTargetX) / 2;
            } else {
                if (c.position.x<cameraTargetX){
                    c.position.x += cameraMoveSpeed*delta;
                }else{
                    c.position.x -= cameraMoveSpeed*delta;
                }
            }
            if (Math.abs(c.position.y-cameraTargetY)<cameraMoveSpeed*delta) {
                c.position.y = (c.position.y + cameraTargetY) / 2;
            } else {
                if (c.position.y<cameraTargetY){
                    c.position.y += cameraMoveSpeed*delta;
                }else{
                    c.position.y -= cameraMoveSpeed*delta;
                }
            }
            if (Math.abs(c.zoom-cameraTargetZoom)<cameraMoveSpeed*delta) {
                c.zoom = (c.zoom + cameraTargetZoom) / 2;
            } else {
                if (c.zoom<cameraTargetZoom){
                    c.zoom += cameraMoveSpeed*delta;
                }else{
                    c.zoom -= cameraMoveSpeed*delta;
                }
            }
            //System.out.println(cameraRotation + " ... " + rotateTo);
            if (Math.abs(cameraRotation - rotateTo)<0.01f){
                cameraRotation = rotateTo;
            }else{
                float sp = Math.abs(cameraRotation - rotateTo)/2f;
                if (sp>cameraMoveSpeed*delta*100){
                    sp = cameraMoveSpeed*delta*100;
                }
                if(cameraRotation > rotateTo) {
                    c.rotate(sp);
                    cameraRotation-=sp;
                }else{
                    c.rotate(-sp);
                    cameraRotation+=sp;
                }

            }
            /*
            if(cameraRotation > rotateTo){
                c.rotate(-((cameraRotation + rotateTo) / 2f)); cameraRotation-=(cameraRotation + rotateTo) / 2f;
            } else {
                if (cameraRotation < rotateTo){
                    c.rotate((cameraRotation + rotateTo) / 2f); cameraRotation+=(cameraRotation + rotateTo) / 2f;
                }
            }
*/
            c.update();

        }

    }

    public void updateFrustum(){
        Camera c = getCamera();
        for (Actor a: getActors()) {
            a.setVisible(isActorShowing(c,a));
        }
    }

    public void updateFrustum(float margin){
        OrthographicCamera c = (OrthographicCamera)getCamera();
        for (Actor a: getActors()) {
            a.setVisible(isActorShowing(c,a, margin));
        }
    }

    public void startTimer(){
        timerRunning = true;
    }

    public float getTime(){
        return timer;
    }

    public void stopTimer(){
        timerRunning = false;
    }

    public void resetTimer(){
        timer = 0;
    }

    public boolean isTimerRunning(){
        return timerRunning;
    }

    private static boolean isActorShowing(Camera c, Actor a){
        return c.frustum.pointInFrustum(a.getX(), a.getY(), 0) || c.frustum.pointInFrustum(a.getX() + a.getWidth(), a.getY() + a.getHeight(), 0) ||
                c.frustum.pointInFrustum(a.getX() + a.getWidth(), a.getY(), 0) || c.frustum.pointInFrustum(a.getX(), a.getY() + a.getHeight(), 0);
    }

    private static boolean isActorShowing(OrthographicCamera c, Actor a, float zoom){
        float z = c.zoom;
        c.zoom *= zoom;
        c.update();
        boolean b = isActorShowing(c,a);
        c.zoom = z;
        c.update();
        return b;
        /*return isActorShowing(c, a) || c.frustum.pointInFrustum(a.getX() - margin, a.getY() - margin, 0) || c.frustum.pointInFrustum(a.getX()  + a.getWidth() + margin, a.getY() + a.getHeight() + margin, 0) ||
                c.frustum.pointInFrustum(a.getX() + a.getWidth() + margin, a.getY() - margin, 0) || c.frustum.pointInFrustum(a.getX() - margin, a.getY() + a.getHeight() + margin, 0);
    */
    }
}
