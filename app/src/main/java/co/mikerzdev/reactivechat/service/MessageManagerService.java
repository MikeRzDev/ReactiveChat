package co.mikerzdev.reactivechat.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.koushikdutta.async.http.server.AsyncHttpServer;
import com.koushikdutta.async.http.server.AsyncHttpServerRequest;
import com.koushikdutta.async.http.server.AsyncHttpServerResponse;
import com.koushikdutta.async.http.server.HttpServerRequestCallback;

import co.mikerzdev.reactivechat.app.App;

public class MessageManagerService extends Service implements HttpServerRequestCallback {

    private static AsyncHttpServer server = new AsyncHttpServer();
    private static int CODE_CLIENT_IS_CHATTING = 405;


    public MessageManagerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        server.get("/logout",this);
        server.get("/start",this);
        server.post("/postMessage",this);
        server.listen(8100);
    }


    @Override
    public void onRequest(AsyncHttpServerRequest request, AsyncHttpServerResponse response) {
        processEndpoint(request.getPath(),response);
    }

    private void processEndpoint(String endpoint, AsyncHttpServerResponse response){
        Log.d("processEnd",endpoint);
        switch(endpoint) {
            case "/start" : {
                if (App.getAppState() == App.STATE_READY_FOR_CONNECTION)
                {
                    App.setAppState(App.STATE_CHAT_STARTED);
                    response.end();
                } else {
                    response.code(CODE_CLIENT_IS_CHATTING);
                    response.end();
                }
                break;
            }

            case "/postMessage" : {
                response.end();
                break;
            }

            case "/logout" : {
                response.end();
                break;
            }

        }
    }
}
