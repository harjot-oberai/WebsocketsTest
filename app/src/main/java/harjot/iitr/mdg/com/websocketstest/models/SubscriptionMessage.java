package harjot.iitr.mdg.com.websocketstest.models;

public class SubscriptionMessage {

    private String op;

    public SubscriptionMessage(String op) {
        this.op = op;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }
}
