package harjot.iitr.mdg.com.websocketstest.models.bpi;

import com.google.gson.annotations.SerializedName;

public class BPIResponse {

    @SerializedName("USD")
    private CurrencyData usd;

    public BPIResponse(CurrencyData usd) {
        this.usd = usd;
    }

    public CurrencyData getUsd() {
        return usd;
    }

    public void setUsd(CurrencyData usd) {
        this.usd = usd;
    }
}
