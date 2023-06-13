package com.ui.myEvent;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

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
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentMyeventBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
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
        public PlaceholderContent.Event mItem;

        public ViewHolder(FragmentMyeventBinding binding) {
            super(binding.getRoot());
            mIdView = binding.eventHeadline;
            mContentView = binding.eventDetails;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}