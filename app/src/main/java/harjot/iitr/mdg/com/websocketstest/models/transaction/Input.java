package harjot.iitr.mdg.com.websocketstest.models.transaction;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Input {

    @SerializedName("sequence")
    @Expose
    private Integer sequence;
    @SerializedName("prev_out")
    @Expose
    private PrevOut prevOut;
    @SerializedName("script")
    @Expose
    private String script;

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public PrevOut getPrevOut() {
        return prevOut;
    }

    public void setPrevOut(PrevOut prevOut) {
        this.prevOut = prevOut;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

}
