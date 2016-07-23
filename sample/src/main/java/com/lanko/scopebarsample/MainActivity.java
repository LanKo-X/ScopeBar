package com.lanko.scopebarsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lanko.scopebar.ScopeBar;

public class MainActivity extends AppCompatActivity {

    private ScopeBar scopeBar;
    private ViewPager mainPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPager = (ViewPager) findViewById(R.id.vp_main_pager);
        mainPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager(), 3));

        scopeBar = (ScopeBar) findViewById(R.id.sb_main_scope_bar);
        scopeBar.addScope("All").addScope("Post").addScope("User");
        scopeBar.setOnTabChangeListener(new ScopeBar.OnTabChangeListener() {
            @Override
            public void onTabSelected(int position) {
                mainPager.setCurrentItem(position, false);
            }
        });
    }
}
