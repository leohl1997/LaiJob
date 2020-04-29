package com.laioffer.githubexample.ui.jobInfo;


import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

public class RecommendationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private JSONArray mDataset

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private TextView companyName, location, title;
        private ImageView icCenter;
        public String itemId, itemUrl;
        private CardView cardView;

        public ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.similarTitle);
            companyName = (TextView) v.findViewById(R.id.similarShippingMsg);
            icCenter = (ImageView) v.findViewById(R.id.similarImage);
            location = (TextView) v.findViewById(R.id.similarPrice);
            cardView = (CardView) v.findViewById(R.id.map_card_view);

        }
    }


    public RecommendationAdapter(JSONArray input) {
        mDataset = input;
    }


    // Create new views (invoked by the layout manager)
    @Override
    public RecommendationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.map_card_view, parent, false);
        RecommendationAdapter.ViewHolder vh = new RecommendationAdapter.ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final RecommendationAdapter.ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        try {
            holder.title.setText(mDataset.getJSONObject(position).getString("title"));
        } catch (JSONException e) {
            holder.title.setText("N/A");
        }
        try {
            holder.itemUrl = (mDataset.getJSONObject(position).getString("viewItemURL"));
        } catch (JSONException e) {
            holder.itemUrl = "http://www.ebay.com";
        }
        try {
            holder.companyName.setText(mDataset.getJSONObject(position).getString("title"));
        } catch (JSONException e) {
            holder.companyName.setText("N/A");
        }
        try {
            holder.location.setText(mDataset.getJSONObject(position).getString("title"));
        } catch (JSONException e) {
            holder.location.setText("N/A");
        }
        try {
            String imageUri = mDataset.getJSONObject(position).getString("imageURL");
            Picasso.with(holder.itemView.getContext()).load(imageUri).into(holder.icCenter);
        } catch (JSONException e) {
            System.out.println("x");
        }

        holder.cardView.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent pageIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(holder.itemUrl));
                v.getContext().startActivity(pageIntent);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)

}
