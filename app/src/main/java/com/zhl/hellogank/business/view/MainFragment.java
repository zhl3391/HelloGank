package com.zhl.hellogank.business.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhl.hellogank.R;
import com.zhl.hellogank.business.model.ArticleModel;
import com.zhl.hellogank.business.model.bean.Article;
import com.zhl.hellogank.business.model.bean.HomeType;
import com.zhl.hellogank.business.presenter.ArticlePresenter;
import com.zhl.hellogank.common.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhouhl on 2016/11/8.
 * MainFragment
 */

public class MainFragment extends BaseFragment {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private HomeType[] mTabTitles;
    private List<Fragment> mFragmentList = new ArrayList<>();
    private FragmentManager mFragmentManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_main, container, false);
        ButterKnife.bind(this, rootView);

        mTabTitles = HomeType.values();

        initFragments();

        TabAdapter tabAdapter = new TabAdapter(mFragmentManager);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setAdapter(tabAdapter);
        mViewPager.setOffscreenPageLimit(mTabTitles.length);

        return rootView;
    }

    public void setFragmentManager(FragmentManager fm) {
        mFragmentManager = fm;
    }

    private void initFragments() {
        if (mFragmentManager.getFragments() != null) {
            for (Fragment fragment : mFragmentManager.getFragments()) {
                if (fragment instanceof ArticleFragment) {
                    mFragmentList.add(fragment);
                }
            }
        }
        if (mFragmentList.isEmpty()) {
            ArticleModel articleModel = new ArticleModel(mActivity);
            for (HomeType homeType : mTabTitles) {
                ArticleFragment articleFragment = new ArticleFragment();
                ArticlePresenter articlePresenter = new ArticlePresenter(articleFragment, articleModel);
                articlePresenter.setHomeType(homeType);
                mFragmentList.add(articleFragment);
            }
        } else {
            ArticleModel articleModel = new ArticleModel(mActivity);
            for (HomeType homeType : mTabTitles) {
                ArticleFragment articleFragment = (ArticleFragment) mFragmentList.get(homeType.ordinal());
                ArticlePresenter articlePresenter = new ArticlePresenter(articleFragment, articleModel);
                articlePresenter.setHomeType(homeType);
                mFragmentList.add(articleFragment);
            }
        }
    }

    private class TabAdapter extends FragmentPagerAdapter {

        public TabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            if (mTabTitles == null) {
                return 0;
            } else {
                return mTabTitles.length;
            }
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabTitles[position].name;
        }
    }
}
