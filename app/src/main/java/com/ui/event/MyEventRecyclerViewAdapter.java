package com.ui.event;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.LoginActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;
import com.ui.placeholder.EventData;
import com.ui.placeholder.PlaceholderContent;
import com.example.myapplication.databinding.FragmentEventBinding;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {@link RecyclerView.Adapter} that can display a {@link}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyEventRecyclerViewAdapter extends RecyclerView.Adapter<MyEventRecyclerViewAdapter.ViewHolder> {

    private final List<EventData> mValues;

    public MyEventRecyclerViewAdapter(List<EventData> items) {
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
        Picasso.get().load(mValues.get(position).imageUrl).into(holder.eventImageSrc);
        if (LoginActivity.isGuestMode == true){
            holder.shareImage.setVisibility(View.GONE);
            holder.shareButton.setVisibility(View.GONE);
        }
        else {
            if (holder.mItem.getShare() == true){
                holder.shareImage.setVisibility(View.VISIBLE);
                holder.shareButton.setVisibility(View.GONE);
            } else {
                holder.shareButton.setVisibility(View.VISIBLE);
                holder.shareImage.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final TextView eventHeadline;
        public final TextView eventDetails;
        public final ImageView eventImageSrc;
        public final Button shareButton;
        public final ImageView shareImage;
        public EventData mItem;

        public ViewHolder(FragmentEventBinding binding) {
            super(binding.getRoot());
            eventHeadline = binding.eventHeadline;
            eventDetails = binding.eventDetails;
            eventImageSrc = binding.eventImageSrc;
            shareButton = binding.shareButton;
            shareImage = binding.shareImage;

            shareButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v == shareButton) {
                int position = getAdapterPosition();
                // Do something with this position, like remove the item from the list
                Log.d("tag-recyclerview", "--------------------Here I am ---------------------");
                String userId = mItem.getUserId();
                String docId = mItem.getDocId();
                Boolean share = mItem.getShare();
                if (share == false)
                {
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    db.collection("myevents").document(userId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            ArrayList<String> eventlist;
                            if (documentSnapshot.exists()) {
                                eventlist = (ArrayList<String>) documentSnapshot.get("eventlist");
                                eventlist.add(docId);
                                db.collection("myevents").document(userId).update("eventlist", eventlist).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Log.d("firebase","DocumentSnapshot successfully updated!");
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w("firebase", "Error updating document", e);
                                    }
                                });
                            }
                            else{
                                eventlist = new ArrayList<>();
                                eventlist.add(docId);
                                Map<String, Object> eventData = new HashMap<>();
                                eventData.put("eventlist", eventlist);
                                db.collection("myevents").document(userId).set(eventData).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Log.d("firebase","DocumentSnapshot successfully written!");
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w("firebase", "Error writing document", e);
                                    }
                                });
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("firebase","Error getting document", e);
                        }
                    });
                    mItem.setShare(true);
                }
            }
        }
        @Override
        public String toString() {
            return super.toString() + " '" + eventDetails.getText() + "'";
        }
    }
}