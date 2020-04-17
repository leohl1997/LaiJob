package com.laioffer.githubexample.base;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.laioffer.githubexample.remote.ApiService;
import com.laioffer.githubexample.remote.ApiUtils;

public abstract class BaseRepository {
    protected final ApiService apiService = ApiUtils.getAPIService();

}