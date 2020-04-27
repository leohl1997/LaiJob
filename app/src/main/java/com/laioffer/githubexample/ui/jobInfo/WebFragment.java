package com.laioffer.githubexample.ui.jobInfo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.laioffer.githubexample.R;
import com.laioffer.githubexample.databinding.JobInfoFragmentBinding;
import com.laioffer.githubexample.remote.response.Job;
import com.laioffer.githubexample.ui.NavigationManager;
import com.laioffer.githubexample.util.Utils;

public class WebFragment extends Fragment {
    NavigationManager navigationManager;

    public static WebFragment getInstance(Job job) {
        WebFragment webFragment = new WebFragment();
        Bundle args = new Bundle();
        args.putSerializable("job", job);
        webFragment.setArguments(args);
        return webFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.web_fragment, container, false);
        Job job = (Job) getArguments().getSerializable("job");
        if (job == null || Utils.isNullOrEmpty(job.url)) {
            Utils.constructToast(getContext(), "No web url specified!");
            return view;
        }
        WebView webView = (WebView) view.findViewById(R.id.web_view);
        webView.loadUrl(job.url);
        view.findViewById(R.id.btn_back_web).setOnClickListener(v -> navigationManager.goBack());
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        navigationManager = (NavigationManager) context;
    }
}
