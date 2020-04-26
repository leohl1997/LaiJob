package com.laioffer.githubexample.ui.HomeList;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.laioffer.githubexample.R;
import com.laioffer.githubexample.remote.response.Job;
import com.laioffer.githubexample.ui.NavigationManager;
import com.laioffer.githubexample.ui.comment.JobInfoRecyclerViewAdapter;
import com.laioffer.githubexample.ui.search.SearchFragment;
import com.laioffer.githubexample.ui.userInfo.UserInfoFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
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

        holder.postTime.setText((currentJob.company));
        holder.postTime.setMaxWidth(500);
        holder.postTime.setSingleLine(false);

        if (currentJob.imageUrl != null && !currentJob.imageUrl.isEmpty()) {
            Picasso.get().load(currentJob.imageUrl).placeholder(R.drawable.thumbnail)
                    .resize(100,100)
                    .into(holder.image);
        }
    }

    public void setItems(ArrayList<Job> items) {
        this.items = items;
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

