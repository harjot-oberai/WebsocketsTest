package harjot.iitr.mdg.com.websocketstest;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;

import harjot.iitr.mdg.com.websocketstest.adapters.BlocksAdapter;
import harjot.iitr.mdg.com.websocketstest.adapters.TransactionsAdapter;
import harjot.iitr.mdg.com.websocketstest.interfaces.BaseView;
import harjot.iitr.mdg.com.websocketstest.listeners.CustomWebSocketListener;
import harjot.iitr.mdg.com.websocketstest.models.ResponseModel;
import harjot.iitr.mdg.com.websocketstest.models.SubscriptionMessage;
import harjot.iitr.mdg.com.websocketstest.models.block.BlockWrapper;
import harjot.iitr.mdg.com.websocketstest.models.bpi.BPIResponse;
import harjot.iitr.mdg.com.websocketstest.models.transaction.TransactionWrapper;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;

public class MainActivity extends AppCompatActivity implements BaseView {

    private final String TAG = MainActivity.class.getSimpleName();

    private CustomWebSocketListener customWebSocketListener;

    private final String WEB_SOCKET_URL = "wss://ws.blockchain.info/inv";

    private OkHttpClient okHttpClient;
    private OkHttpClient okHttpBpiClient;

    private WebSocket webSocket;

    private Gson gson;

    private TextView connectionStatus;

    private RecyclerView blocksRecycler;
    private BlocksAdapter blocksAdapter;

    private RecyclerView transactionsRecycler;
    private TransactionsAdapter transactionsAdapter;

    private ArrayList<BlockWrapper> blocks = new ArrayList<>();
    private ArrayList<TransactionWrapper> transactions = new ArrayList<>();

    private BPIResponse bpiResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectionStatus = findViewById(R.id.connection_status_text);

        okHttpClient = new OkHttpClient();
        okHttpBpiClient = new OkHttpClient();

        RuntimeTypeAdapterFactory<ResponseModel> typeAdapterFactory = RuntimeTypeAdapterFactory
                .of(ResponseModel.class, "op")
                .registerSubtype(TransactionWrapper.class, "utx")
                .registerSubtype(BlockWrapper.class, "block");

        gson = new GsonBuilder().registerTypeAdapterFactory(typeAdapterFactory).create();

        blocksRecycler = findViewById(R.id.blocks_recycler);
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true); // newest block on top
        blocksRecycler.setLayoutManager(llm);
        blocksRecycler.setItemAnimator(new DefaultItemAnimator());

        blocksAdapter = new BlocksAdapter(blocks);
        blocksRecycler.setAdapter(blocksAdapter);

        transactionsRecycler = findViewById(R.id.transactions_recycler);
        LinearLayoutManager llm2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true); // newest transaction on top
        transactionsRecycler.setLayoutManager(llm2);
        transactionsRecycler.setItemAnimator(new DefaultItemAnimator());

        transactionsAdapter = new TransactionsAdapter(transactions);
        transactionsRecycler.setAdapter(transactionsAdapter);

        setupSwitcher();

    }

    public void setupSwitcher() {
        final Button transactionsButton = findViewById(R.id.transactions_button);
        final Button blocksButton = findViewById(R.id.blocks_button);
        transactionsButton.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));
        blocksButton.setBackgroundColor(Color.parseColor("#FFFFFF"));
        transactionsButton.setTextColor(Color.parseColor("#FFFFFF"));
        blocksButton.setTextColor(Color.parseColor("#000000"));
        transactionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transactionsButton.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));
                blocksButton.setBackgroundColor(Color.parseColor("#FFFFFF"));
                transactionsButton.setTextColor(Color.parseColor("#FFFFFF"));
                blocksButton.setTextColor(Color.parseColor("#000000"));
                blocksRecycler.setVisibility(View.GONE);
                transactionsRecycler.setVisibility(View.VISIBLE);
            }
        });
        blocksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blocksButton.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));
                transactionsButton.setBackgroundColor(Color.parseColor("#FFFFFF"));
                blocksButton.setTextColor(Color.parseColor("#FFFFFF"));
                transactionsButton.setTextColor(Color.parseColor("#000000"));
                blocksRecycler.setVisibility(View.VISIBLE);
                transactionsRecycler.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        connectionStatus.setText("Connecting...");
        setupConnection();
        getBPI();
    }

    private void getBPI() {
        final Request request = new Request.Builder()
                .url("https://blockchain.info/ticker")
                .build();

        okHttpBpiClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                } else {
                    bpiResponse = gson.fromJson(response.body().string(), BPIResponse.class);
                    transactionsAdapter.setBpiResponse(bpiResponse);
                    Log.d(TAG, bpiResponse.getUsd().getLast());
                }
            }
        });
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

        final ResponseModel responseModel = gson.fromJson(text, ResponseModel.class);
        Log.d(TAG, "OP : " + responseModel.getOp());

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if (responseModel instanceof BlockWrapper) {
                    blocks.add((BlockWrapper) responseModel);
                    blocksAdapter.notifyItemInserted(blocks.size() - 1);
                    blocksRecycler.scrollToPosition(blocksAdapter.getItemCount() - 1);
                } else if (responseModel instanceof TransactionWrapper) {
                    if (Math.abs(((TransactionWrapper) responseModel).getTransactionData().getCumulativeValue() * 0.00000001) <= 0.002) {
                        return;
                    }
                    transactions.add((TransactionWrapper) responseModel);
                    transactionsAdapter.notifyItemInserted(transactions.size() - 1);
                    transactionsRecycler.scrollToPosition(transactionsAdapter.getItemCount() - 1);
                }

            }
        });
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
