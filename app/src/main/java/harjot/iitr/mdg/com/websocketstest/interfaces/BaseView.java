package harjot.iitr.mdg.com.websocketstest.interfaces;

public interface BaseView {

    void onConnectionOpen();

    void onTextMessage(String text);

    void onConnectionClosing();

    void onConnectionClosed();

}
