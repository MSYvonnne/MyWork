package com.finalwork.mywork;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//分类界面，在此加入3 viewpager
public class ClassifyFragment extends Fragment {

    public ClassifyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_classify, container, false);
    }

    //在分类页面中加入viewpager
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        ViewPager viewPager = getActivity().findViewById(R.id.viewPager);
        MyPageAdapter pageAdapter = new MyPageAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(pageAdapter);
        TabLayout tabLayout = getActivity().findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
}

