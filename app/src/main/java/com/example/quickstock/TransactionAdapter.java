package com.example.quickstock;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.Locale;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {

    private Context context;
    private List<Transaction> transactionList;

    public TransactionAdapter(Context context, List<Transaction> transactionList) {
        this.context = context;
        this.transactionList = transactionList;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_item_transaction, parent, false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        Transaction transaction = transactionList.get(position);
        holder.tvProductName.setText(transaction.getProductName());
        holder.tvType.setText(transaction.getType().toUpperCase());
        holder.tvQuantity.setText("Qty: " + transaction.getQuantity());
        holder.tvUnitPrice.setText(String.format(Locale.US, "₱%.2f", transaction.getUnitPrice()));
        holder.tvTotal.setText(String.format(Locale.US, "₱%.2f", transaction.getTotalAmount()));
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    public void updateTransactions(List<Transaction> newList) {
        this.transactionList = newList;
        notifyDataSetChanged();
    }

    public static class TransactionViewHolder extends RecyclerView.ViewHolder {
        TextView tvProductName, tvType, tvQuantity, tvUnitPrice, tvTotal;

        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProductName = itemView.findViewById(R.id.tvTransactionProductName);
            tvType = itemView.findViewById(R.id.tvTransactionType);
            tvQuantity = itemView.findViewById(R.id.tvTransactionQuantity);
            tvUnitPrice = itemView.findViewById(R.id.tvTransactionUnitPrice);
            tvTotal = itemView.findViewById(R.id.tvTransactionTotal);
        }
    }
}
