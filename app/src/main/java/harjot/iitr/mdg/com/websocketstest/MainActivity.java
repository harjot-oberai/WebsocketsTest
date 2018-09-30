package harjot.iitr.mdg.com.websocketstest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;

import harjot.iitr.mdg.com.websocketstest.interfaces.BaseView;
import harjot.iitr.mdg.com.websocketstest.listeners.CustomWebSocketListener;
import harjot.iitr.mdg.com.websocketstest.models.SubscriptionMessage;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

public class MainActivity extends AppCompatActivity implements BaseView{

    private CustomWebSocketListener customWebSocketListener;

    private final String WEB_SOCKET_URL = "wss://ws.blockchain.info/inv";

    private OkHttpClient okHttpClient;

    private WebSocket webSocket;

    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        okHttpClient = new OkHttpClient();

        

        gson = new Gson();

    }

    @Override
    protected void onResume() {
        super.onResume();
        setupConnection();
    }

    private void setupConnection(){

        Request request = new Request.Builder().url(WEB_SOCKET_URL).build();
        CustomWebSocketListener customWebSocketListener = new CustomWebSocketListener(this);
        webSocket = okHttpClient.newWebSocket(request, customWebSocketListener);
        okHttpClient.dispatcher().executorService().shutdown();

        addSubscriptions();

    }

    private void addSubscriptions(){

        webSocket.send(gson.toJson(new SubscriptionMessage("unconfirmed_sub")));
        webSocket.send(gson.toJson(new SubscriptionMessage("blocks_sub")));

    }


    @Override
    public void onConnectionOpen() {

    }

    @Override
    public void onTextMessage(String text) {

    }

    @Override
    public void onConnectionClosing() {

    }

    @Override
    public void onConnectionClosed() {

    }
}
