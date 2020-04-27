package com.laioffer.githubexample.base;

import androidx.lifecycle.ViewModel;

public abstract class BaseViewModel<R extends BaseRepository> extends ViewModel {
    protected final R repository;

    protected BaseViewModel(R baseRepository) {
        repository = baseRepository;
    }

}
