package com.ui.event;

import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ui.placeholder.PlaceholderContent;
import com.example.myapplication.databinding.FragmentEventBinding;

import java.io.InputStream;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyEventRecyclerViewAdapter extends RecyclerView.Adapter<MyEventRecyclerViewAdapter.ViewHolder> {

    private final List<PlaceholderContent.Event> mValues;

    public MyEventRecyclerViewAdapter(List<PlaceholderContent.Event> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentEventBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.eventHeadline.setText(mValues.get(position).name);
        holder.eventDetails.setText(mValues.get(position).details);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView eventHeadline;
        public final TextView eventDetails;
        public PlaceholderContent.Event mItem;

        public ViewHolder(FragmentEventBinding binding) {
            super(binding.getRoot());
            eventHeadline = binding.eventHeadline;
            eventDetails = binding.eventDetails;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + eventDetails.getText() + "'";
        }
    }
}