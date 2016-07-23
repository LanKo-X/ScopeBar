package com.lanko.scopebarsample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * For pager
 * Created by Lex on 16/7/23.
 */
public class MainPagerAdapter extends FragmentStatePagerAdapter {

    private int pageSize = 0;

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public MainPagerAdapter(FragmentManager fm, int pageSize) {
        super(fm);
        this.pageSize = pageSize;
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.getInstance(position);
    }

    @Override
    public int getCount() {
        return pageSize;
    }
}
