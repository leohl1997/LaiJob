package com.laioffer.githubexample.ui.search;

import android.text.Editable;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.laioffer.githubexample.BR;

public class SearchEvent extends BaseObservable {
    private int filterRule;
    private String keyWord;
    @Bindable
    public int getFilterRule() {
        return filterRule;

    }

    public void setFilterRule(int filterRule) {
        this.filterRule = filterRule;
        notifyPropertyChanged(BR.filterRule);
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
        notifyPropertyChanged(BR.keyWord);
    }
    @Bindable
    public String getKeyWord() {
        return keyWord;
    }

    public SearchEvent(int filterRule, String keyWord) {
        this.filterRule = filterRule;
        this.keyWord = keyWord;
    }


}
