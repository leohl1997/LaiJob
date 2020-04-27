package com.laioffer.githubexample.ui.map;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.laioffer.githubexample.R;
import com.laioffer.githubexample.remote.response.Job;
import com.laioffer.githubexample.ui.NavigationManager;
import com.laioffer.githubexample.ui.jobInfo.JobInfoFragment;
import com.laioffer.githubexample.util.Utils;
import com.squareup.picasso.Picasso;

public class CardFragment extends Fragment {
    private CardView cardView;
    private NavigationManager navigationManager;

    public static CardFragment getInstance(Job job) {
        CardFragment cardFragment = new CardFragment();
        Bundle args = new Bundle();
        args.putSerializable("job", job);
        cardFragment.setArguments(args);
        return cardFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_card, container, false);
        cardView = view.findViewById(R.id.map_card_view);
        cardView.setMaxCardElevation(cardView.getCardElevation() *
                CardAdapter.MAX_ELEVATION_FACTOR);

        Job job = (Job) getArguments().getSerializable("job");
        TextView title = view.findViewById(R.id.cv_title);
        title.setText(job.name);
        TextView companyName = view.findViewById(R.id.cv_company_name);
        companyName.setText(job.company);
        TextView location = view.findViewById(R.id.cv_location);
        location.setText(job.address);
        cardView.setOnClickListener( v -> {
            JobInfoFragment fragment = JobInfoFragment.newInstance(job);
            navigationManager.navigateTo(fragment);
        });
        ImageView imageView = view.findViewById(R.id.cvImg_info);
        if (!job.imageUrl.isEmpty()) {
            Picasso.get().setLoggingEnabled(true);
            Picasso.get().load(job.imageUrl).placeholder(R.drawable.ic_center)
                    .resize(70,70)
                    .into(imageView);

        }
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        navigationManager = (NavigationManager) context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public CardView getCardView() {
        return cardView;
    }

}
