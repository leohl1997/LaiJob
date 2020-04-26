package com.laioffer.githubexample.ui.jobInfo;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.laioffer.githubexample.R;
import com.laioffer.githubexample.base.BaseFragment;
import com.laioffer.githubexample.base.BaseRepository;
import com.laioffer.githubexample.base.BaseViewModel;
import com.laioffer.githubexample.ui.HomeList.ItemDataAdapter;
import com.laioffer.githubexample.ui.HomeList.Job;

import java.util.List;

public class JobInfoFragment extends BaseFragment {

    private JobInfoViewModel mViewModel;
    private Job job;


    public static JobInfoFragment newInstance() {
        JobInfoFragment fragment =  new JobInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("Job", Job);
        fragment.setArguments(bundle);
        fragment.job = Job;
        return fragment;
        ;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.job_info_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(JobInfoViewModel.class);

        setContentView(R.layout.job_info_fragment);

        TextView textElement1 = (TextView) View.findViewById(R.id.job_title);
        String newText1;
        newText1 = getResources().getString(R,string.jobTittle);
        textElement1.setText(newText1);


        TextView textElement2 = (TextView) View.findViewById(R.id.company);
        String newText2;
        newText2 = getResources().getString(R,string.company);
        textElement2.setText(newText2);


        TextView textElement3 = (TextView) View.findViewById(R.id.location);
        String newText3;
        newText3 = getResources().getString(R,string.laction);
        textElement3.setText(newText3);


        TextView textElement4 = (TextView) View.findViewById(R.id.post_time);
        String newText4;
        newText4 = getResources().getString(R,string.postTime);
        textElement4.setText(newText4);


        TextView textElement5 = (TextView) View.findViewById(R.id.job_description);
        String newText5;
        newText5 = getResources().getString(R,string.jobDescription);
        textElement5.setText(newText5);

        TextView textElement6= (TextView) View.findViewById(R.id.job_image);
        String newText6;
        newText6 = getResources().getString(R,string.job_image);
        textElement6.setText(newText6);

    }// TODO: Use the ViewModel
    }


    @Override
    //public void onCreateView(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);




    @Override
    protected BaseViewModel getViewModel() {
        return null;
    }

    @Override
    protected ViewModelProvider.Factory getFactory() {
        return null;
    }

    @Override
    protected BaseRepository getRepository() {
        return null;
    }


}
