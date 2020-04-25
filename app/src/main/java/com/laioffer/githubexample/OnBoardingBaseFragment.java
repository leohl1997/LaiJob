package com.laioffer.githubexample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.laioffer.githubexample.databinding.OnboardingBaseFragmentBinding;
import com.laioffer.githubexample.ui.login.LoginFragment;
import com.laioffer.githubexample.ui.register.RegisterFragment;

import java.util.ArrayList;

public class OnBoardingBaseFragment extends Fragment {

    private OnboardingBaseFragmentBinding binding;

    class OnBoardingPagerAdapter extends FragmentPagerAdapter {
        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();

        public OnBoardingPagerAdapter(@NonNull FragmentManager fm) {
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
        return view;
    }

}
