package harjot.iitr.mdg.com.websocketstest.models.block;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BlockWrapper {

    @SerializedName("blockData")
    @Expose
    private BlockData blockData;

    public BlockData getBlockData() {
        return blockData;
    }

    public void setBlockData(BlockData blockData) {
        this.blockData = blockData;
    }
}
