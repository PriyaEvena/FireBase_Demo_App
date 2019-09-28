package com.example.firebasedemoapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class QuoteViewAdapter extends FirestoreRecyclerAdapter<Quote, QuoteViewAdapter.MyViewHolder> {



    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See
     * {@link FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public QuoteViewAdapter(FirestoreRecyclerOptions<Quote> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(MyViewHolder myViewHolder, int i, Quote quote) {
        myViewHolder.authorText.setText(quote.getAuthor());
        myViewHolder.quoteText.setText(quote.getQuote());

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.quote_layout,parent,false);
        return new MyViewHolder(v);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView quoteText,authorText;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            quoteText = itemView.findViewById(R.id.quoteText);
            authorText = itemView.findViewById(R.id.authorText);
        }
    }

}
