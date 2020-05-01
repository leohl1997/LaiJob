package com.laioffer.githubexample.ui.jobInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.laioffer.githubexample.R;
import com.laioffer.githubexample.remote.response.Item;
import com.laioffer.githubexample.remote.response.Job;
import com.laioffer.githubexample.util.Config;
import com.laioffer.githubexample.util.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class JobInfoRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private static final int TYPE_JOB_INFO = 0;
    private static final int TYPE_WRITE_COMMENT = 1;
    private static final int TYPE_COMMENT = 2;

    private ArrayList<Item> itemArrayList;
    private TextView commentNumber = null;
    private TextView avgRating = null;
    private TextView saveText = null;
    private Button saveButton = null;
    private AdapterOperationListener adapterOperationListener;

    JobInfoRecyclerViewAdapter(Job job, AdapterOperationListener adapterOperationListener) {
        this.adapterOperationListener = adapterOperationListener;
        itemArrayList = new ArrayList<>();
        itemArrayList.add(job);
        itemArrayList.add(new Item());
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        if (viewType == TYPE_JOB_INFO) {
            View itemView = LayoutInflater.from(context).inflate(R.layout.job_info_card, parent, false);
            return new InfoViewHolder(itemView, adapterOperationListener);
        } else if (viewType == TYPE_COMMENT){
            View itemView = LayoutInflater.from(context).inflate(R.layout.comment_card, parent, false);
            return new CommentViewHolder(itemView);
        } else if (viewType == TYPE_WRITE_COMMENT) {
            View itemView = LayoutInflater.from(context).inflate(R.layout.write_comment_card, parent, false);
            return new WriteCommentViewHolder(itemView, adapterOperationListener);
        }
        throw new RuntimeException("No support view type");
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof InfoViewHolder) {
            Job currJob = (Job) itemArrayList.get(position);
            ((InfoViewHolder) holder).company.setText(currJob.company);
            if (!Utils.isNullOrEmpty(currJob.description)) {
                ((InfoViewHolder) holder).description.setText(currJob.description.replaceAll("<.*?>", ""));
            }
            if (!Utils.isNullOrEmpty(currJob.imageUrl)) {
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
            Glide.with(context).setDefaultRequestOptions(new RequestOptions().circleCrop())
                    .load(R.drawable.thumbnail)
                    .placeholder(R.drawable.thumbnail)
                    .into(((CommentViewHolder) holder).thumbnail);
        } else if (holder instanceof WriteCommentViewHolder) {
            ((WriteCommentViewHolder) holder).writeCommentUserId.setText(Config.userId);
            Glide.with(context).setDefaultRequestOptions(new RequestOptions().circleCrop())
                    .load(R.drawable.thumbnail)
                    .placeholder(R.drawable.thumbnail)
                    .into(((WriteCommentViewHolder) holder).imageView);
        }

    }

    void addAll(List<CommentEvent> comments) {
        while (itemArrayList.size() > 2) {
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
        } else if (position == 1) {
            return TYPE_WRITE_COMMENT;
        }
        return TYPE_COMMENT;
    }

    TextView getCommentNumber() {
        return commentNumber;
    }

    TextView getAvgRating() {
        return avgRating;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    TextView getSavedText() {
        return saveText;
    }

    // comment view holder
    static class CommentViewHolder extends RecyclerView.ViewHolder {

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

        InfoViewHolder(@NonNull View itemView, AdapterOperationListener listener) {
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
                    description.setMaxLines(20);
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
                adapterOperationListener.onBackClicked();
            });
            itemView.findViewById(R.id.comment).setOnClickListener(
                    v -> adapterOperationListener.onCommentClicked());
            itemView.findViewById(R.id.apply).setOnClickListener(
                    v -> adapterOperationListener.onApplyCLicked()
            );
            saveText = itemView.findViewById(R.id.save_text);
        }
    }

    class WriteCommentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private int rating = 0;
        private boolean visible = false;
        private TextView writeCommentUserId;
        private TextView showComment;
        private ConstraintLayout invisibleBody;
        private EditText writeCommentBody;
        private Button btnOneStar;
        private Button btnTwoStar;
        private Button btnThreeStar;
        private Button btnFourStar;
        private Button btnFiveStar;
        private Button sendComment;
        private AdapterOperationListener listener;
        private ImageView imageView;

        WriteCommentViewHolder(@NonNull View itemView, AdapterOperationListener listener) {
            super(itemView);
            this.listener = listener;
            imageView = itemView.findViewById(R.id.send_cmt_thumbnail);
            writeCommentUserId = itemView.findViewById(R.id.send_cmt_user_id);
            invisibleBody = itemView.findViewById(R.id.invisible_body);
            showComment = itemView.findViewById(R.id.textView_show_comment);
            sendComment = itemView.findViewById(R.id.btn_comment);
            sendComment.setOnClickListener(null);
            writeCommentBody = itemView.findViewById(R.id.et_comment_body);
            btnOneStar = itemView.findViewById(R.id.btn_one_star);
            btnOneStar.setOnClickListener(null);
            btnTwoStar = itemView.findViewById(R.id.btn_two_star);
            btnTwoStar.setOnClickListener(null);
            btnThreeStar = itemView.findViewById(R.id.btn_three_star);
            btnThreeStar.setOnClickListener(null);
            btnFourStar = itemView.findViewById(R.id.btn_four_star);
            btnFourStar.setOnClickListener(null);
            btnFiveStar = itemView.findViewById(R.id.btn_five_star);
            btnFiveStar.setOnClickListener(null);
            showComment.setOnClickListener(v -> setVisible());
            itemView.setOnClickListener(this);
        }

        private void unsetStars() {
            btnOneStar.setBackground(itemView.getResources().getDrawable(R.drawable.star_hollow));
            btnTwoStar.setBackground(itemView.getResources().getDrawable(R.drawable.star_hollow));
            btnThreeStar.setBackground(itemView.getResources().getDrawable(R.drawable.star_hollow));
            btnFourStar.setBackground(itemView.getResources().getDrawable(R.drawable.star_hollow));
            btnFiveStar.setBackground(itemView.getResources().getDrawable(R.drawable.star_hollow));
        }

        private void setVisible() {
            if (!visible) {
                visible = true;
                writeCommentBody.requestFocus();
                adapterOperationListener.showKeyboard();
                showComment.setVisibility(View.INVISIBLE);
                invisibleBody.setVisibility(View.VISIBLE);
                sendComment.setOnClickListener(view -> {
                    send();
                });
                btnOneStar.setOnClickListener(v1 -> {
                    unsetStars();
                    btnOneStar.setBackground(itemView.getResources().getDrawable(R.drawable.star_solid));
                    rating = 1;
                });
                btnTwoStar.setOnClickListener(v1 -> {
                    unsetStars();
                    btnOneStar.setBackground(itemView.getResources().getDrawable(R.drawable.star_solid));
                    btnTwoStar.setBackground(itemView.getResources().getDrawable(R.drawable.star_solid));
                    rating = 2;
                });
                btnThreeStar.setOnClickListener(v1 -> {
                    unsetStars();
                    btnOneStar.setBackground(itemView.getResources().getDrawable(R.drawable.star_solid));
                    btnTwoStar.setBackground(itemView.getResources().getDrawable(R.drawable.star_solid));
                    btnThreeStar.setBackground(itemView.getResources().getDrawable(R.drawable.star_solid));
                    rating = 3;
                });
                btnFourStar.setOnClickListener(v1 -> {
                    unsetStars();
                    btnOneStar.setBackground(itemView.getResources().getDrawable(R.drawable.star_solid));
                    btnTwoStar.setBackground(itemView.getResources().getDrawable(R.drawable.star_solid));
                    btnThreeStar.setBackground(itemView.getResources().getDrawable(R.drawable.star_solid));
                    btnFourStar.setBackground(itemView.getResources().getDrawable(R.drawable.star_solid));
                    rating = 4;
                });
                btnFiveStar.setOnClickListener(v1 -> {
                    unsetStars();
                    btnOneStar.setBackground(itemView.getResources().getDrawable(R.drawable.star_solid));
                    btnTwoStar.setBackground(itemView.getResources().getDrawable(R.drawable.star_solid));
                    btnThreeStar.setBackground(itemView.getResources().getDrawable(R.drawable.star_solid));
                    btnFourStar.setBackground(itemView.getResources().getDrawable(R.drawable.star_solid));
                    btnFiveStar.setBackground(itemView.getResources().getDrawable(R.drawable.star_solid));
                    rating = 5;
                });
            }
        }

        private void send() {
            if (rating == 0) {
                listener.sentInfo("Please give a rating first!");
                return;
            }
            listener.onSendClicked(rating, writeCommentBody.getText().toString());
            setInvisible();

        }

        private void setInvisible() {
            visible = false;
            showComment.setVisibility(View.VISIBLE);
            invisibleBody.setVisibility(View.GONE);
            sendComment.setOnClickListener(null);
            btnOneStar.setOnClickListener(null);
            btnTwoStar.setOnClickListener(null);
            btnThreeStar.setOnClickListener(null);
            btnFourStar.setOnClickListener(null);
            btnFiveStar.setOnClickListener(null);
        }

        @Override
        public void onClick(View v) {
            if (visible) {
                setInvisible();
            } else {
                setVisible();
            }
        }
    }

    public interface AdapterOperationListener {
        void onSaveClicked();
        void onBackClicked();
        void onCommentClicked();
        void onApplyCLicked();
        void onSendClicked(int rating, String commentBody);
        void showKeyboard();
        void sentInfo(String msg);
    }

    public interface RemoteListener {
        void onSaveEvent(MutableLiveData<String> responseLiveData);
        void onCommentEvent(LiveData<List<CommentEvent>> responseLiveData);
        void onSendEvent(LiveData<String> responseLiveData);
    }
}