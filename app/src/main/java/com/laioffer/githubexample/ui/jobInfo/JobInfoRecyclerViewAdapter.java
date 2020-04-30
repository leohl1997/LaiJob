package com.laioffer.githubexample.ui.jobInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.laioffer.githubexample.R;
import com.laioffer.githubexample.remote.response.Job;

import com.laioffer.githubexample.ui.comment.CommentEvent;
import com.laioffer.githubexample.ui.comment.Item;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Comment;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import tm.charlie.expandabletextview.ExpandableTextView;

public class JobInfoRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private static final int TYPE_JOB_INFO = 0;
    private static final int TYPE_COMMENT = 1;
    private ArrayList<Item> itemArrayList;
    private TextView commentNumber = null;
    private TextView avgRating = null;
    private TextView saveText = null;
    private Button saveButton = null;
    private SaveItemListener saveItemListener;

    public JobInfoRecyclerViewAdapter(Job job, SaveItemListener saveItemListener) {
        this.saveItemListener = saveItemListener;
        itemArrayList = new ArrayList<>();
        itemArrayList.add(job);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        if (viewType == TYPE_JOB_INFO) {
            View itemView = LayoutInflater.from(context).inflate(R.layout.job_info_card, parent, false);
            return new InfoViewHolder(itemView, saveItemListener);
        } else if (viewType == TYPE_COMMENT){
            View itemView = LayoutInflater.from(context).inflate(R.layout.comment_card, parent, false);
            return new CommentViewHolder(itemView);
        }
        throw new RuntimeException("No support view type");
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof InfoViewHolder) {
            Job currJob = (Job) itemArrayList.get(position);
            ((InfoViewHolder) holder).company.setText(currJob.company);
            ((InfoViewHolder) holder).description.setText(currJob.description.replaceAll("<.*?>", ""));
            if (!currJob.imageUrl.isEmpty()) {
                Picasso.get().setLoggingEnabled(true);
                Picasso.get().load(currJob.imageUrl).placeholder(R.drawable.thumbnail)
                        .resize(100,100)
                        .into(((InfoViewHolder) holder).image);
            }
            ((InfoViewHolder) holder).location.setText(currJob.address);
            ((InfoViewHolder) holder).title.setText(currJob.name);
            ((InfoViewHolder) holder).postTime.setText(currJob.time);
            if (currJob.favorite) {
                ((InfoViewHolder) holder).saveButton.setBackground(context.getResources()
                        .getDrawable(R.drawable.ic_saved_24dp));
                ((InfoViewHolder) holder).saveText.setText(R.string.saved);
            }
            this.commentNumber = ((InfoViewHolder) holder).commentNumber;
            this.avgRating = ((InfoViewHolder) holder).avgRating;
            this.saveText = ((InfoViewHolder) holder).saveText;
            this.saveButton = ((InfoViewHolder) holder).saveButton;
        } else if (holder instanceof CommentViewHolder) {
            CommentEvent currentComment = (CommentEvent) itemArrayList.get(position);
            ((CommentViewHolder) holder).userId.setText(currentComment.userId);
            ((CommentViewHolder) holder).comment.setText(currentComment.commentText);
            ((CommentViewHolder) holder).time.setText(currentComment.currentTime);
            for (int i = 0; i < currentComment.rating; i++) {
                ((CommentViewHolder) holder).stars.get(i).setImageResource(R.drawable.star_solid);
            }
        }

    }

    public void addAll(List<CommentEvent> comments) {
        while (itemArrayList.size() > 1) {
            itemArrayList.remove(itemArrayList.size() - 1);
        }
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

    public TextView getCommentNumber() {
        return commentNumber;
    }

    public TextView getAvgRating() {
        return avgRating;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public TextView getSavedText() {
        return saveText;
    }

    // comment view holder
    class CommentViewHolder extends RecyclerView.ViewHolder {

        private ImageView thumbnail;
        private TextView userId;
        private ArrayList<ImageView> stars;
        private TextView time;
        private TextView comment;

        CommentViewHolder(@NonNull View itemView) {
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
            comment = itemView.findViewById(R.id.comment_body);


        }
    }

    // info view holder
    class InfoViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView company;
        private TextView location;
        private ImageView image;
        private TextView description;
        private TextView postTime;
        private TextView commentNumber;
        private TextView avgRating;
        private TextView saveText;
        private Button saveButton;

        public InfoViewHolder(@NonNull View itemView, SaveItemListener listener) {
            super(itemView);
            title = itemView.findViewById(R.id.job_title);
            company = itemView.findViewById(R.id.company);
            location = itemView.findViewById(R.id.location);
            image = itemView.findViewById(R.id.job_image);
            description = itemView.findViewById(R.id.job_description);
            description.setOnClickListener(v -> {
                if (description.getMaxLines() < 1000) {
                    description.setMaxLines(1000);
                } else {
                    description.setMaxLines(10);
                }
            });
            postTime = itemView.findViewById(R.id.post_time);
            commentNumber = itemView.findViewById(R.id.comment_number);
            avgRating = itemView.findViewById(R.id.average_rating);
            saveButton = itemView.findViewById(R.id.save);
            saveButton.setOnClickListener(v -> {
                listener.onSaveClicked();
            });
            Button button = itemView.findViewById(R.id.btn_back_info);
            button.setOnClickListener(v -> {
                saveItemListener.onBackClicked();
            });
            itemView.findViewById(R.id.comment).setOnClickListener(
                    v -> saveItemListener.onCommentClicked());
            itemView.findViewById(R.id.apply).setOnClickListener(
                    v -> saveItemListener.onApplyCLicked()
            );
            saveText = itemView.findViewById(R.id.save_text);
        }
    }

    public interface SaveItemListener {
        void onSaveClicked();
        void onBackClicked();
        void onCommentClicked();
        void onApplyCLicked();
    }

    public interface RemoteListener {
        void onSaveEvent(MutableLiveData<String> responseLiveData);
        void onCommentEvent(LiveData<List<CommentEvent>> responseLiveData);
    }
}