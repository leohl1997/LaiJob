package com.laioffer.githubexample.ui.HomeList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.laioffer.githubexample.R;
import com.laioffer.githubexample.remote.response.Job;
import com.laioffer.githubexample.ui.NavigationManager;
import com.squareup.picasso.Picasso;

/*
    Filter Rule here:
    if the filter rule is 0, then do nothing to the data get from the API
    if the filter rule is 1, then get the job posted within 24 hours
    if the filter rule is 3, then get the job posted within three days
    if the filter rule is 7, then get the job posted within one week

     */


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ItemDataAdapter extends RecyclerView.Adapter<ItemDataAdapter.ItemDataViewHolder> {
    NavigationManager navigationManager;
    private ArrayList<Job> items = new ArrayList<>();
    private Context context;


    public void setOnNoteListener(OnNoteListener onNoteListener) {
        this.onNoteListener = onNoteListener;
    }

    private OnNoteListener onNoteListener = null;

    @NonNull
    @Override
    public ItemDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.job_item, parent, false);
        return new ItemDataViewHolder(itemView, onNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemDataViewHolder holder, int position) {
        Job currentJob = items.get(position);
        holder.title.setText(currentJob.name);
        holder.title.setMaxWidth(500);
        holder.title.setSingleLine(false);

        holder.address.setText((currentJob.address));
        holder.address.setMaxWidth(500);
        holder.address.setSingleLine(false);

        holder.firm.setText((currentJob.company));
        holder.firm.setMaxWidth(500);
        holder.firm.setSingleLine(false);

        holder.postTime.setText((currentJob.time));
        holder.postTime.setMaxWidth(500);
        holder.postTime.setSingleLine(false);

        if (currentJob.imageUrl != null && !currentJob.imageUrl.isEmpty()) {
            Picasso.get().load(currentJob.imageUrl).placeholder(R.drawable.thumbnail)
                    .resize(100,100)
                    .into(holder.image);
        }
    }

    // do job filtration here
    public void setItems(ArrayList<Job> items, int filterRule) {
        ArrayList<Job> jobsLeft = new ArrayList<>();
        Date currentTime = (Date) Calendar.getInstance().getTime();

        if (items != null){
            for(Job job : items){
                if (!filteredJob(job.getPostTime(),currentTime,filterRule)){
                    jobsLeft.add(job);
                }
            }
        }
        this.items = jobsLeft;
    }

    public void setItems(ArrayList<Job> items) {

        this.items = items;
    }

    // check whether this job should be filtered or not
    // if return true, we should filter this job
    private boolean filteredJob(String postTime, Date currentTime, int maxDaysDiff){
        if (maxDaysDiff == 0){
            return false;
        }
        Date pt = new Date(postTime);
        int daysDiff = (int) (currentTime.getTime() - pt.getTime()) / (1000 * 60 * 60 * 24);
        return daysDiff >= maxDaysDiff;
    }

    public Job getItem(int pos) {
        return items.get(pos);
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }



    class ItemDataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView title;
        private TextView firm;
        private TextView address;
        private ImageView image;
        private TextView postTime;


        OnNoteListener onNoteListener;

        public ItemDataViewHolder(View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            title = itemView.findViewById(R.id.job_title);
            address = itemView.findViewById(R.id.location);
            image = itemView.findViewById(R.id.job_image);
            firm = itemView.findViewById(R.id.company);
            postTime = itemView.findViewById(R.id.post_time);

            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition(), ItemDataAdapter.this);
        }
    }

    public interface OnNoteListener {
        void onNoteClick(int position,  ItemDataAdapter adapter);
    }
}

