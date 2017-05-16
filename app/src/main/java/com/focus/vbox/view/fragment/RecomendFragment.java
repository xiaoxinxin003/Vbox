package com.focus.vbox.view.fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

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
        mRecomendWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
        mRecomendWebView.loadUrl("http://m.bilibili.com/subchannel.html#tid=5");
        mRecomendWebView.setWebChromeClient(new WebChromeClient());
    }

}
