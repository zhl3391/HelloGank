package com.zhl.hellogank.business.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.zhl.hellogank.R;
import com.zhl.hellogank.business.view.ExploreFragment;
import com.zhl.hellogank.business.view.HomeFragment;
import com.zhl.hellogank.business.view.MeFragment;
import com.zhl.hellogank.common.base.BaseActivity;
import com.zhl.hellogank.common.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.bottom_navigation)
    BottomNavigationView mBottomNavigation;

    private ExploreFragment mExploreFragment;
    private HomeFragment mHomeFragment;
    private MeFragment mMeFragment;

    private Fragment mCurrentFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        mExploreFragment = new ExploreFragment();
        mHomeFragment = new HomeFragment();
        mMeFragment = new MeFragment();

        mExploreFragment.setFragmentManager(getSupportFragmentManager());

        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mHomeFragment, R.id.layout_content);

        mCurrentFragment = mHomeFragment;

        mBottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        switchFragment(mHomeFragment);
                        break;
                    case R.id.navigation_explore:
                        switchFragment(mExploreFragment);
                        break;
                    case R.id.navigation_me:
                        switchFragment(mMeFragment);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
    }

    private void switchFragment(Fragment to) {
        if (to.isAdded()) {
            getSupportFragmentManager().beginTransaction()
                    .hide(mCurrentFragment)
                    .show(to).commitAllowingStateLoss();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .hide(mCurrentFragment)
                    .add(R.id.layout_content, to)
                    .commitAllowingStateLoss();
        }

        mCurrentFragment = to;
    }
}
