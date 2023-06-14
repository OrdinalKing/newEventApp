package com.ui.myEvent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentMyeventBinding;
import com.ui.placeholder.PlaceholderContent;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyMyEventRecyclerViewAdapter extends RecyclerView.Adapter<MyMyEventRecyclerViewAdapter.ViewHolder> {

    private final List<PlaceholderContent.Event> mValues;

    public MyMyEventRecyclerViewAdapter(List<PlaceholderContent.Event> items) {
        mValues = items;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_myevent, parent, false));
    }

    @Override
    @NonNull
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mIdView.setText(mValues.get(position).name);
        holder.mContentView.setText(mValues.get(position).details);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mIdView = itemView.findViewById(R.id.eventHeadline);
            mContentView = itemView.findViewById(R.id.eventDetails);
        }

    }
}