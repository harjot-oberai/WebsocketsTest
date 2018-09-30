package harjot.iitr.mdg.com.websocketstest.models.bpi;

import com.google.gson.annotations.SerializedName;

public class CurrencyData {

    @SerializedName("last")
    private String last;
    @SerializedName("buy")
    private String buy;
    @SerializedName("sell")
    private String sell;

    public CurrencyData(String last, String buy, String sell) {
        this.last = last;
        this.buy = buy;
        this.sell = sell;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getSell() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }
}
