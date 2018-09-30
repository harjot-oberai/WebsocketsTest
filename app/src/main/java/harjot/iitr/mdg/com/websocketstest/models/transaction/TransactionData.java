package harjot.iitr.mdg.com.websocketstest.models.transaction;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TransactionData {

    @SerializedName("lock_time")
    @Expose
    private Integer lockTime;
    @SerializedName("ver")
    @Expose
    private Integer ver;
    @SerializedName("size")
    @Expose
    private Integer size;
    @SerializedName("inputs")
    @Expose
    private List<Input> inputs = null;
    @SerializedName("time")
    @Expose
    private Integer time;
    @SerializedName("tx_index")
    @Expose
    private Integer txIndex;
    @SerializedName("vin_sz")
    @Expose
    private Integer vinSz;
    @SerializedName("hash")
    @Expose
    private String hash;
    @SerializedName("vout_sz")
    @Expose
    private Integer voutSz;
    @SerializedName("relayed_by")
    @Expose
    private String relayedBy;
    @SerializedName("out")
    @Expose
    private List<Out> out = null;

    public Integer getLockTime() {
        return lockTime;
    }

    public void setLockTime(Integer lockTime) {
        this.lockTime = lockTime;
    }

    public Integer getVer() {
        return ver;
    }

    public void setVer(Integer ver) {
        this.ver = ver;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<Input> getInputs() {
        return inputs;
    }

    public void setInputs(List<Input> inputs) {
        this.inputs = inputs;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getTxIndex() {
        return txIndex;
    }

    public void setTxIndex(Integer txIndex) {
        this.txIndex = txIndex;
    }

    public Integer getVinSz() {
        return vinSz;
    }

    public void setVinSz(Integer vinSz) {
        this.vinSz = vinSz;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Integer getVoutSz() {
        return voutSz;
    }

    public void setVoutSz(Integer voutSz) {
        this.voutSz = voutSz;
    }

    public String getRelayedBy() {
        return relayedBy;
    }

    public void setRelayedBy(String relayedBy) {
        this.relayedBy = relayedBy;
    }

    public List<Out> getOut() {
        return out;
    }

    public void setOut(List<Out> out) {
        this.out = out;
    }

}
