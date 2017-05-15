package com.focus.vbox.view;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.focus.vbox.R;
import com.focus.vbox.base.BaseFragment;

/**
 * Created by focus on 2017/5/10.
 */

public class RecomendFragment extends BaseFragment {

    private WebView mRecomendWebView;

    public static RecomendFragment newInstance(Bundle args) {
        RecomendFragment fragment = new RecomendFragment();
        if (args == null) {
            args = new Bundle();
        }
        fragment.setArguments(args);
        return fragment;
    }

    public RecomendFragment() {
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_net_videos, container, false);
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView) {
        mRecomendWebView = (WebView) rootView.findViewById(R.id.wbview_recomend);
        initWebView();
    }

    private void initWebView() {
        mRecomendWebView.setAlwaysDrawnWithCacheEnabled(true);
        mRecomendWebView.setAnimationCacheEnabled(true);
        mRecomendWebView.setScrollbarFadingEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(mRecomendWebView, true);
        }

        WebSettings settings = mRecomendWebView.getSettings();
        settings.setJavaScriptEnabled(true);
//        mRecomendWebView.addJavascriptInterface(new JSInterfaceManager(this), "JSInterfaceManager");
        if (Build.VERSION.SDK_INT < 18) {
            settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        }
        settings.setDomStorageEnabled(true);
        String appCacheDir = this.getActivity().getApplicationContext().getDir("cache", Context.MODE_PRIVATE).getPath();
        settings.setAppCachePath(appCacheDir);
        settings.setAllowFileAccess(true);
        settings.setAppCacheEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        mRecomendWebView.loadUrl("http://live.bilibili.com/h5/");
//        mRecomendWebView.setWebViewClient(new CustomWebViewClient());
    }

}
