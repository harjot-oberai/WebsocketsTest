package harjot.iitr.mdg.com.websocketstest.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import harjot.iitr.mdg.com.websocketstest.R;
import harjot.iitr.mdg.com.websocketstest.models.block.BlockData;
import harjot.iitr.mdg.com.websocketstest.models.block.BlockWrapper;

public class BlocksAdapter extends RecyclerView.Adapter<BlocksAdapter.BlocksViewHolder> {

    private ArrayList<BlockWrapper> blocks;

    public BlocksAdapter(ArrayList<BlockWrapper> blocks) {
        this.blocks = blocks;
    }

    public ArrayList<BlockWrapper> getBlocks() {
        return blocks;
    }

    public void setBlocks(ArrayList<BlockWrapper> blocks) {
        this.blocks = blocks;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BlocksViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.blocks_recycler_item, viewGroup, false);

        return new BlocksViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BlocksViewHolder blocksViewHolder, int i) {

//        if (i > blocks.size() - 1) {
//            return;
//        }

        BlockData blockData = blocks.get(i).getBlockData();

        blocksViewHolder.blockHash.setText("Block Hash : " + blockData.getHash());
        blocksViewHolder.blockHeight.setText("Block Height : " + blockData.getHeight());
        blocksViewHolder.totalBTCSent.setText("Total BTC : " + blockData.getTotalBTCSent());
        blocksViewHolder.reward.setText("Reward : " + blockData.getReward());

    }

    @Override
    public int getItemCount() {
        return blocks.size();
    }

    class BlocksViewHolder extends RecyclerView.ViewHolder {

        TextView blockHash, blockHeight, totalBTCSent, reward;

        public BlocksViewHolder(@NonNull View itemView) {
            super(itemView);
            blockHash = itemView.findViewById(R.id.block_hash);
            blockHeight = itemView.findViewById(R.id.block_height);
            totalBTCSent = itemView.findViewById(R.id.total_btc_sent);
            reward = itemView.findViewById(R.id.reward);
        }
    }

}
