package com.laioffer.githubexample.ui.search;

import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.laioffer.githubexample.R;
import com.laioffer.githubexample.base.BaseFragment;
import com.laioffer.githubexample.databinding.SearchFragmentBinding;
import com.laioffer.githubexample.ui.HomeList.HomeListFragment;

import com.laioffer.githubexample.ui.NavigationManager;
import com.laioffer.githubexample.util.Utils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class SearchFragment extends BaseFragment<SearchViewModel, SearchRepository> implements AdapterView.OnItemSelectedListener  {
    SearchEvent searchEvent = new SearchEvent(0,"developer");
    private SearchViewModel mViewModel;
    EditText t1;
    private ListView mListView;
    public static SearchFragment newInstance() {
        return new SearchFragment();
    }
    private NavigationManager navigationManager;
    private SearchFragmentBinding binding;
    public void setFilterRule(int filterRule) {
        this.searchEvent.setFilterRule(filterRule);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        navigationManager = (NavigationManager) context;
    }

    @SuppressLint("ResourceType")
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);

       binding = SearchFragmentBinding.inflate(inflater, container, false);

        ItemAdapter mAdapter = new ItemAdapter(Objects.requireNonNull(this.getActivity()),R.layout.list_item, Arrays.asList(data.data));
        binding.listview.setAdapter(mAdapter);
        binding.listview.setTextFilterEnabled(true);
        binding.searchView.setSubmitButtonEnabled(true);
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (searchEvent == null){
                    searchEvent = new SearchEvent(0,"developer");
                }
                // Utils.constructToast(getContext(),searchEvent.getKeyWord()).show();
                EditText mEdit = (SearchView.SearchAutoComplete)binding.searchView.findViewById(R.id.search_src_text);
                searchEvent.setKeyWord(mEdit.getText().toString());
                navigationManager.navigateTo(new HomeListFragment(searchEvent));
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!TextUtils.isEmpty(newText)) {
//              mListView.setFilterText(newText);
                    mAdapter.getFilter().filter(newText);
                } else {
                    binding.listview.clearTextFilter();
                }
                return true;
            }
        });
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = data.get(position);
                EditText mEdit = (SearchView.SearchAutoComplete)binding.searchView.findViewById(R.id.search_src_text);
                mEdit.setText(item);
                binding.searchView.setSubmitButtonEnabled(true);
            }
        });
        binding.button.setOnClickListener(v -> {
            navigationManager.navigateTo(new HomeListFragment(searchEvent));
        });

        return binding.getRoot();
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
