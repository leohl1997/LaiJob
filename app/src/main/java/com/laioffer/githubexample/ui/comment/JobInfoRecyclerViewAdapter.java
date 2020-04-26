package com.laioffer.githubexample.ui.comment;

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
import com.ms.square.android.expandabletextview.ExpandableTextView;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.List;

public class JobInfoRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private static final int TYPE_JOB_INFO = 0;
    private static final int TYPE_COMMENT = 1;
    private ArrayList<Item> itemArrayList;

    public JobInfoRecyclerViewAdapter(Job job) {
        itemArrayList = new ArrayList<>();
        itemArrayList.add(job);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        if (viewType == TYPE_JOB_INFO) {
            // job info view
        } else if (viewType == TYPE_COMMENT){
            View itemView = LayoutInflater.from(context).inflate(R.layout.comment_card, parent, false);
            return new CommentViewHolder(itemView);
        }
        throw new RuntimeException("No support view type");
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof InfoViewHolder) {
            // job info
        } else if (holder instanceof CommentViewHolder) {
            CommentEvent currentComment = (CommentEvent) itemArrayList.get(position);
            ((CommentViewHolder) holder).userId.setText(currentComment.userId);
            ((CommentViewHolder) holder).comment.setText(currentComment.commentText);
            ((CommentViewHolder) holder).time.setText(currentComment.currentTime.toString());
            for (int i = 0; i < currentComment.rating; i++) {
                ((CommentViewHolder) holder).stars.get(i).setImageResource(R.drawable.star_solid);
            }
        }

    }

    public void addAll(List<CommentEvent> comments) {
        itemArrayList.addAll(comments);
    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_JOB_INFO;
        }
        return TYPE_COMMENT;
    }

    // comment view holder
    class CommentViewHolder extends RecyclerView.ViewHolder {

        public ImageView thumbnail;
        public TextView userId;
        public ArrayList<ImageView> stars;
        public TextView time;
        public ExpandableTextView comment;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.cmt_thumbnail);
            userId = itemView.findViewById(R.id.cmt_user_name);
            stars = new ArrayList<>();
            stars.add(itemView.findViewById(R.id.cmt_star_one));
            stars.add(itemView.findViewById(R.id.cmt_star_two));
            stars.add(itemView.findViewById(R.id.cmt_star_three));
            stars.add(itemView.findViewById(R.id.cmt_star_four));
            stars.add(itemView.findViewById(R.id.cmt_star_five));
            time = itemView.findViewById(R.id.cmt_time);
            comment = itemView.findViewById(R.id.expandable_text);
        }
    }

    // info view holder
    class InfoViewHolder extends RecyclerView.ViewHolder {
        public InfoViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
