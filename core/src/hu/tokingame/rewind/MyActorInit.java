package hu.tokingame.rewind;

/**
 * Created by davim on 2016. 10. 21..
 */

abstract public class MyActorInit extends MyActor {
    public MyActorInit() {
        super();
        init();
    }

    abstract void init();
}
