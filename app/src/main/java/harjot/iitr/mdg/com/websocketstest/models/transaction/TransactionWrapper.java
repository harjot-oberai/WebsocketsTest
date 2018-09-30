package harjot.iitr.mdg.com.websocketstest.models.transaction;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import harjot.iitr.mdg.com.websocketstest.models.ResponseModel;

public class TransactionWrapper extends ResponseModel{

    @SerializedName("transactionData")
    @Expose
    private TransactionData transactionData;

    public TransactionData getTransactionData() {
        return transactionData;
    }

    public void setTransactionData(TransactionData transactionData) {
        this.transactionData = transactionData;
    }

}
