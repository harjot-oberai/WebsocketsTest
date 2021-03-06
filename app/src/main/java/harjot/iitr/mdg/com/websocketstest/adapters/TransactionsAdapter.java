package harjot.iitr.mdg.com.websocketstest.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import harjot.iitr.mdg.com.websocketstest.R;
import harjot.iitr.mdg.com.websocketstest.models.bpi.BPIResponse;
import harjot.iitr.mdg.com.websocketstest.models.transaction.TransactionData;
import harjot.iitr.mdg.com.websocketstest.models.transaction.TransactionWrapper;

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.TransactionsViewHolder> {

    public ArrayList<TransactionWrapper> transactions;

    private BPIResponse bpiResponse;

    public TransactionsAdapter(ArrayList<TransactionWrapper> transactions) {
        this.transactions = transactions;
    }

    public void setBpiResponse(BPIResponse bpiResponse) {
        this.bpiResponse = bpiResponse;
    }

    @NonNull
    @Override
    public TransactionsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.transactions_recycler_item, viewGroup, false);

        return new TransactionsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionsViewHolder transactionsViewHolder, int i) {

        TransactionData transactionData = transactions.get(i).getTransactionData();

        transactionsViewHolder.transactionHash.setText("Transaction Hash : " + transactionData.getHash());
        transactionsViewHolder.transactionAmount.setText("Transaction Amount : " + transactionData.getCumulativeValue() + "");
        if (bpiResponse != null) {
            transactionsViewHolder.usdAmount.setVisibility(View.VISIBLE);
            double bpi = Double.parseDouble(bpiResponse.getUsd().getLast());
            transactionsViewHolder.usdAmount.setText("USD Amount : " + (bpi * transactionData.getCumulativeValue() * 0.00000001) + "$");
        } else {
            transactionsViewHolder.usdAmount.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }


    class TransactionsViewHolder extends RecyclerView.ViewHolder {

        TextView transactionHash, transactionAmount, usdAmount;

        public TransactionsViewHolder(@NonNull View itemView) {
            super(itemView);
            transactionHash = itemView.findViewById(R.id.transaction_hash);
            transactionAmount = itemView.findViewById(R.id.transaction_amount);
            usdAmount = itemView.findViewById(R.id.usd_amount);
        }
    }

}
