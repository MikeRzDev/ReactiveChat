package co.mikerzdev.reactivechat.app;

/**
 * Created by user on 11/12/2016.
 */

public class App {
    public final static int STATE_READY_FOR_CONNECTION = 100;
    public final static int STATE_CHAT_STARTED = 200;
    private static int appState = STATE_READY_FOR_CONNECTION;



    public static int getAppState(){
        return appState;
    }

    public static void setAppState(int appState){
        App.appState = appState;
    }
}
