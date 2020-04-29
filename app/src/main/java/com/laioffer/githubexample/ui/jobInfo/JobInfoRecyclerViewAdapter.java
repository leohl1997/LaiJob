package com.laioffer.githubexample.ui.jobInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.laioffer.githubexample.R;
import com.laioffer.githubexample.remote.response.Job;

import com.laioffer.githubexample.ui.comment.CommentEvent;
import com.laioffer.githubexample.ui.comment.Item;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
    private Button saveButton = null;
    private SaveItemListener saveItemListener;

    private Context mContext;
    private JSONObject itemDetail;
    private String itemTitle, price, subTitle, brand, shipping, itemId;
    private JSONArray specifics;
    private JSONArray similarItems;
    private JSONObject ori;
    private RequestQueue requestQueue;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


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
                        .getDrawable(R.drawable.btn_custom_selected));
                ((InfoViewHolder) holder).saveButton.setText(R.string.saved);
            }
            this.commentNumber = ((InfoViewHolder) holder).commentNumber;
            this.avgRating = ((InfoViewHolder) holder).avgRating;
            this.saveButton = ((InfoViewHolder) holder).saveButton;
//            ((InfoViewHolder) holder).recyclerView =





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
        private Button saveButton;
        private RecyclerView recyclerView;

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
            recyclerView = itemView.findViewById(R.id.recommendation_card);


        }
    }

    public interface SaveItemListener {
        void onSaveClicked();
        void onBackClicked();
        void onCommentClicked();
        void onApplyCLicked();
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        recyclerView = (RecyclerView) layout.findViewById(R.id.similarSearchResultList);
        layoutManager = new GridLayoutManager(layout.getContext(),1);
        recyclerView.setLayoutManager(layoutManager);

        String url = "http:///jobSearch/recommendation?user_id=" + itemId;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject items = response.getJSONObject("getSimilarItemsResponse").getJSONObject("itemRecommendations");
                            ori = items;
                            JSONArray similarItemsArray = items.getJSONArray("item");

                            similarItems = similarItemsArray;
                            mAdapter = new RecommendationAdapter(similarItemsArray);
                            recyclerView.setAdapter(mAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(layout.getContext(), "Nothing found!", Toast.LENGTH_SHORT);
                    }
                });
        //add request to queue
        requestQueue.add(jsonObjectRequest);


        }


        collection.addView(layout);
        return layout;

    }





}
