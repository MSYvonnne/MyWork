package com.finalwork.mywork;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyPageAdapter extends FragmentPagerAdapter {

    private String[] title = new String[]{"电影信息","内容简介"};
    public MyPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i){
        if(i==0){
            return new FirstFragment();
        }else {
            return new SecondFragment();
        }//滑动控制
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position]; //标题
    }

    @Override
    public int getCount() {
        return 3;
    }
}
