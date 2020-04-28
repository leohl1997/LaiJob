package com.laioffer.githubexample.ui.search;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.laioffer.githubexample.R;
import com.laioffer.githubexample.base.BaseFragment;
import com.laioffer.githubexample.databinding.SearchFragmentBinding;
import com.laioffer.githubexample.ui.HomeList.HomeListFragment;
import com.laioffer.githubexample.ui.HomeMap.HomeMapFragment;
import com.laioffer.githubexample.ui.NavigationManager;
import com.laioffer.githubexample.util.Utils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class SearchFragment extends BaseFragment<SearchViewModel, SearchRepository> implements AdapterView.OnItemSelectedListener  {
    SearchEvent searchEvent = new SearchEvent(0,"Developer");

    private SearchViewModel mViewModel;
    EditText t1;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }
    private NavigationManager navigationManager;

    public void setFilterRule(int filterRule) {
        this.searchEvent.setFilterRule(filterRule);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        navigationManager = (NavigationManager) context;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Spinner spinner = view.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(), R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);


    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0 || position == 1){
            this.searchEvent.setFilterRule(position);
        }
        else if (position == 2){
            this.searchEvent.setFilterRule(3);
        }
        else {
            this.searchEvent.setFilterRule(7);
        }
        //String text = parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(), Integer.toString(filterRule), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View view = inflater.inflate(R.layout.search_fragment, container, false);
        t1 = (EditText) view.findViewById(R.id.search_view) ;

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Utils.constructToast(getContext(),searchEvent.getKeyWord()).show();
                searchEvent.setKeyWord(t1.getText().toString());
            }
        });

        Button button1 = view.findViewById(R.id.search_button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Utils.constructToast(getContext(),searchEvent.getKeyWord()).show();
                navigationManager.navigateTo(new HomeListFragment(searchEvent));
            }
        });

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //mViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    protected SearchViewModel getViewModel() {
        return new ViewModelProvider(requireActivity(), getFactory()).get(SearchViewModel.class);
    }

    @Override
    protected ViewModelProvider.Factory getFactory() {
        return new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new SearchViewModel((SearchRepository) getRepository());
            }
        };
    }



    @Override
    protected SearchRepository getRepository() {
        return new SearchRepository();
    }
}
