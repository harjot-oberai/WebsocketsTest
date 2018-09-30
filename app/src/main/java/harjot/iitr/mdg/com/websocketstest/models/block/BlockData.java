package harjot.iitr.mdg.com.websocketstest.models.block;

public class BlockData {

    private String blockIndex;

    private String prevBlockIndex;

    private String reward;

    private String hash;

    private String mrklRoot;

    private String estimatedBTCSent;

    private String size;

    private String version;

    private String time;

    private String height;

    private String nonce;

    private String totalBTCSent;

    private String bits;

    private String[] txIndexes;

    private String nTx;

    public String getBlockIndex() {
        return blockIndex;
    }

    public void setBlockIndex(String blockIndex) {
        this.blockIndex = blockIndex;
    }

    public String getPrevBlockIndex() {
        return prevBlockIndex;
    }

    public void setPrevBlockIndex(String prevBlockIndex) {
        this.prevBlockIndex = prevBlockIndex;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getMrklRoot() {
        return mrklRoot;
    }

    public void setMrklRoot(String mrklRoot) {
        this.mrklRoot = mrklRoot;
    }

    public String getEstimatedBTCSent() {
        return estimatedBTCSent;
    }

    public void setEstimatedBTCSent(String estimatedBTCSent) {
        this.estimatedBTCSent = estimatedBTCSent;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getTotalBTCSent() {
        return totalBTCSent;
    }

    public void setTotalBTCSent(String totalBTCSent) {
        this.totalBTCSent = totalBTCSent;
    }

    public String getBits() {
        return bits;
    }

    public void setBits(String bits) {
        this.bits = bits;
    }

    public String[] getTxIndexes() {
        return txIndexes;
    }

    public void setTxIndexes(String[] txIndexes) {
        this.txIndexes = txIndexes;
    }

    public String getNTx() {
        return nTx;
    }

    public void setNTx(String nTx) {
        this.nTx = nTx;
    }

}
