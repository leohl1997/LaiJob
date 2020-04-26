package com.laioffer.githubexample.ui;

import androidx.fragment.app.Fragment;

public interface NavigationManager {

    void navigateTo(Fragment fragment);
    void navigateToWithAnimation(Fragment fragment);
    void navigateWithFragmentDestroy(Fragment target, Fragment current);
}
