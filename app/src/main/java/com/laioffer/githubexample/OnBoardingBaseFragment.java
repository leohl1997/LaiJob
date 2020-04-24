package com.laioffer.githubexample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.laioffer.githubexample.databinding.LoginFragmentBinding;
import com.laioffer.githubexample.ui.login.LoginFragment;
import com.laioffer.githubexample.ui.register.RegisterFragment;

import java.util.ArrayList;

public class OnBoardingBaseFragment extends Fragment {

    static class OnBoardingPagerAdapter extends FragmentPagerAdapter {
        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();

        OnBoardingPagerAdapter(@NonNull FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentArrayList.size();
        }

        void addFragment(Fragment fragment) {
            fragmentArrayList.add(fragment);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.onboarding_base_fragment, container, false);
        ViewPager viewPager = view.findViewById(R.id.viewPager);
        OnBoardingPagerAdapter pagerAdapter = new OnBoardingPagerAdapter(getChildFragmentManager());
        pagerAdapter.addFragment(new LoginFragment());
        pagerAdapter.addFragment(new RegisterFragment());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setPageTransformer(false, (page, position) -> {
//            page.setRotationY(position * -30);
            int pageWidth = page.getWidth();
            int pageHeight = page.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                page.setAlpha(0);

            } else if (position <= 1) { // [-1,1]

                ImageView iconLogin = page.findViewById(R.id.icon_login);
                ImageView icon = page.findViewById(R.id.icon);
                TextView subLogin = page.findViewById(R.id.tv_subtitle_login);
                TextView sub = page.findViewById(R.id.tv_subtitle);
                EditText userIdLogin = page.findViewById(R.id.et_user_id_login);
                EditText passwordLogin = page.findViewById(R.id.et_password_login);
                EditText userId = page.findViewById(R.id.et_user_id);
                EditText password = page.findViewById(R.id.et_password);
                Button loginBtn = page.findViewById(R.id.btn_login);
                Button registerBtn = page.findViewById(R.id.btn_register);
                TextView swipeRight = page.findViewById(R.id.swipeRight);

                if (userIdLogin != null && passwordLogin != null && iconLogin != null && subLogin != null) {
                    iconLogin.setTranslationX((float)(-(position) * pageWidth));
                    userIdLogin.setTranslationX((float)(-(position) * pageWidth));
                    passwordLogin.setTranslationX((float)(-(position) * pageWidth));
                    subLogin.setTranslationX((float)(-(position) * pageWidth));
                    loginBtn.setTranslationX((float)(-(position) * pageWidth));
                    loginBtn.setTranslationY((float)(-(position) * pageHeight * 0.1695));
                    swipeRight.setTranslationX((float) ((position) * 1.2 * pageWidth));
                }

                if (userId != null && password != null && icon != null && sub != null) {
                    icon.setTranslationX((float)(-(position) * pageWidth));
                    userId.setTranslationX((float)(-(position) * pageWidth));
                    password.setTranslationX((float)(-(position) * pageWidth));
                    sub.setTranslationX((float)(-(position) * pageWidth));
                    registerBtn.setTranslationX((float)(-(position) * pageWidth));
                    registerBtn.setTranslationY((float)(-(position) * pageHeight * 0.1695));
                }

                EditText firstName = page.findViewById(R.id.et_first_name);
                EditText lastName = page.findViewById(R.id.et_last_name);
                TextView swipeLeft = page.findViewById(R.id.swipeLeft);

                if (firstName != null && lastName != null) {
                    firstName.setTranslationX((position) * (pageWidth / 0.9f));
                    lastName.setTranslationX((position) * (pageWidth / 0.25f));
                    swipeLeft.setTranslationX((position) * (pageWidth / 2f));
                }

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                page.setAlpha(0);
            }

        });
        return view;
    }

}
