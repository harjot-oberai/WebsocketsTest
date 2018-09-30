package harjot.iitr.mdg.com.websocketstest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import harjot.iitr.mdg.com.websocketstest.interfaces.BaseView;
import harjot.iitr.mdg.com.websocketstest.listeners.CustomWebSocketListener;
import harjot.iitr.mdg.com.websocketstest.models.ResponseModel;
import harjot.iitr.mdg.com.websocketstest.models.SubscriptionMessage;
import harjot.iitr.mdg.com.websocketstest.models.block.BlockWrapper;
import harjot.iitr.mdg.com.websocketstest.models.transaction.TransactionWrapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

public class MainActivity extends AppCompatActivity implements BaseView {

    private final String TAG = MainActivity.class.getSimpleName();

    private CustomWebSocketListener customWebSocketListener;

    private final String WEB_SOCKET_URL = "wss://ws.blockchain.info/inv";

    private OkHttpClient okHttpClient;

    private WebSocket webSocket;

    private Gson gson;

    private TextView connectionStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectionStatus = findViewById(R.id.connection_status_text);

        okHttpClient = new OkHttpClient();

        RuntimeTypeAdapterFactory<ResponseModel> typeAdapterFactory = RuntimeTypeAdapterFactory
                .of(ResponseModel.class, "op")
                .registerSubtype(TransactionWrapper.class, "utx")
                .registerSubtype(BlockWrapper.class, "block");

        gson = new GsonBuilder().registerTypeAdapterFactory(typeAdapterFactory).create();

    }

    @Override
    protected void onResume() {
        super.onResume();
        connectionStatus.setText("Connecting...");
        setupConnection();
    }

    private void setupConnection() {

        Request request = new Request.Builder().url(WEB_SOCKET_URL).build();
        CustomWebSocketListener customWebSocketListener = new CustomWebSocketListener(this);
        webSocket = okHttpClient.newWebSocket(request, customWebSocketListener);
        okHttpClient.dispatcher().executorService().shutdown();

        addSubscriptions();

    }

    private void addSubscriptions() {

        webSocket.send(gson.toJson(new SubscriptionMessage("unconfirmed_sub")));
        webSocket.send(gson.toJson(new SubscriptionMessage("blocks_sub")));

    }


    @Override
    public void onConnectionOpen() {
        connectionStatus.setText("Connected");
    }

    @Override
    public void onTextMessage(String text) {


        Log.d(TAG, text);

//        ResponseModel responseModel = gson.fromJson(text, ResponseModel.class);
//        Log.d(TAG, "OP : " + responseModel.getOp());

    }

    @Override
    public void onConnectionClosing() {
        connectionStatus.setText("Closing");
    }

    @Override
    public void onConnectionClosed() {
        connectionStatus.setText("Closed");
    }
}
