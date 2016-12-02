package com.zhl.hellogank.business.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.zhl.hellogank.R;
import com.zhl.hellogank.business.IntentCreator;
import com.zhl.hellogank.common.base.BaseActivity;
import com.zhl.hellogank.common.utils.CommonUtils;
import com.zhl.hellogank.common.utils.ScreenUtils;

import aptintent.lib.AptIntent;
import aptintent.lib.ExtraField;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhouhl on 2016/11/8.
 * WebActivity
 */

public class WebActivity extends BaseActivity {

    @ExtraField(IntentCreator.EXTRA_URL)
    String mUrl;
    @ExtraField(IntentCreator.EXTRA_TITLE)
    String mTitle;
    @BindView(R.id.web_view)
    WebView mWebView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    private boolean mIsCollected;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        AptIntent.bind(this);

        mToolbar.setTitle(mTitle);
        setSupportActionBar(mToolbar);

        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_other_browser:
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        Uri uri = Uri.parse(mUrl);
                        intent.setData(uri);
                        startActivity(intent);
                        break;
                    case R.id.action_copy_url:
                        CommonUtils.copy(mActivity, mUrl);
                        Toast.makeText(mActivity, R.string.copy_success, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_share:
                        share();
                        break;
                    case R.id.action_collect:
                        mIsCollected = !mIsCollected;
                        item.setIcon(mIsCollected
                                ? R.drawable.ic_favorite_white_24dp
                                : R.drawable.ic_favorite_border_white_24dp);
                        break;
                }
                return true;
            }
        });

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDomStorageEnabled(true);

        mWebView.setWebChromeClient(new WebChromeClientDemo());
        mWebView.setWebViewClient(new WebViewClientDemo());
        mWebView.loadUrl(mUrl);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_web, menu);
        menu.findItem(R.id.action_collect).setIcon(mIsCollected
                ? R.drawable.ic_favorite_white_24dp
                : R.drawable.ic_favorite_border_white_24dp);
        return true;
    }

    private class WebChromeClientDemo extends WebChromeClient {
        public void onProgressChanged(WebView view, int progress) {
            super.onProgressChanged(view, progress);
            mProgressBar.setProgress(progress);
        }
    }

    private void share() {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, "「" + mTitle + "」: " + mUrl);
        shareIntent.setType("text/plain");
        startActivity(Intent.createChooser(shareIntent, "分享到"));
    }

    private class WebViewClientDemo extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                view.loadUrl(request.getUrl().toString());
            }
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
//            mToolbar.setTitle(view.getTitle());
            mProgressBar.setVisibility(View.GONE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mToolbar.setElevation(ScreenUtils.dp2px(mActivity, 3));
            }
        }
    }
}
