package com.cipolat.fbcomments.UI.CustomView;

import android.content.Context;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.CookieManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import com.cipolat.fbcomments.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sebastian on 08/03/18.
 */

public class FbCommentsView extends FrameLayout {

    @BindView(R.id.commentSkeleton)
    CommentSkeletonView commentSkeleton;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.webview_frame)
    FrameLayout webviewFrame;
    @BindView(R.id.body)
    WebView mWebViewComments;

    private WebView mWebviewPop;

    private Context mCtx;
    private FbConfig confg;
    private boolean debugMode;

    public FbCommentsView(Context context) {
        super(context);
        this.mCtx = context;
        init();
    }

    public FbCommentsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mCtx = context;
        init();
    }

    public FbCommentsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mCtx = context;
        init();
    }

    public FbCommentsView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mCtx = context;
        init();
    }
    public void loadData(FbConfig config){
        this.confg=config;
        loadComments(config);
    }
    private void init() {
        inflate(getContext(), R.layout.fb_comments, this);
        ButterKnife.bind(this);
    }


    public boolean isDebugMode() {
        return debugMode;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }

    private void loadComments(FbConfig config) {
        setLoadingSkeleton(true);
        mWebViewComments.setWebViewClient(new UriWebViewClient());
        mWebViewComments.setWebChromeClient(new UriChromeClient());
        mWebViewComments.getSettings().setJavaScriptEnabled(true);
        mWebViewComments.getSettings().setAppCacheEnabled(true);
        mWebViewComments.getSettings().setDomStorageEnabled(true);
        mWebViewComments.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebViewComments.getSettings().setSupportMultipleWindows(true);
        mWebViewComments.getSettings().setSupportZoom(false);
        mWebViewComments.getSettings().setBuiltInZoomControls(false);
        if(debugMode)
            mWebViewComments.setWebContentsDebuggingEnabled(true);

        CookieManager.getInstance().setAcceptCookie(true);

        if (Build.VERSION.SDK_INT >= 21) {
            mWebViewComments.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            CookieManager.getInstance().setAcceptThirdPartyCookies(mWebViewComments, true);
        }
        mWebViewComments.loadDataWithBaseURL(config.getUrl(), FbUtils.buildHtml(config), "text/html", "UTF-8", null);
    }
    private void setLoading(boolean isLoading) {
        if (isLoading)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);

    }
    private void setLoadingSkeleton(boolean isLoading) {
        if (isLoading)
            commentSkeleton.setVisibility(View.VISIBLE);
        else
            commentSkeleton.setVisibility(View.GONE);
    }

    private class UriWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            String host = Uri.parse(url).getHost();
            if(host.equals("m.facebook.com")){
             //
            }

            return !host.equals("m.facebook.com");
        }

        @Override
        public void onPageFinished(WebView view, final String url) {
            super.onPageFinished(view, url);
            //Cuando se mostro el login de FB
            if (url.contains("/plugins/close_popup.php?reload")){
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                       /* setLoading(false);
                        isLoading=false;
                        invalidateOptionsMenu();*/
                        //Do something after 100ms
                        webviewFrame.removeView(mWebviewPop);
                        loadComments(confg);
                    }
                }, 600);
            }else{
                //HACK Demora debido a delay de carga de FB comments
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setLoadingSkeleton(false);
                    }
                }, 5000);
                mWebViewComments.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
           // errorDismiss();
        }
    }

    class UriChromeClient extends WebChromeClient {

        @Override
        public boolean onCreateWindow(WebView view, boolean isDialog,
                                      boolean isUserGesture, Message resultMsg) {
            mWebviewPop = new WebView(mCtx);
            mWebviewPop.setVerticalScrollBarEnabled(false);
            mWebviewPop.setHorizontalScrollBarEnabled(false);
            mWebviewPop.setWebViewClient(new UriWebViewClient());
            mWebviewPop.setWebChromeClient(this);
            mWebviewPop.getSettings().setJavaScriptEnabled(true);
            mWebviewPop.getSettings().setDomStorageEnabled(true);
            mWebviewPop.getSettings().setSupportZoom(false);
            mWebviewPop.getSettings().setBuiltInZoomControls(false);
            mWebviewPop.getSettings().setSupportMultipleWindows(true);
            mWebviewPop.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            webviewFrame.addView(mWebviewPop);
            WebView.WebViewTransport transport = (WebView.WebViewTransport) resultMsg.obj;
            transport.setWebView(mWebviewPop);
            resultMsg.sendToTarget();
            return true;
        }

        @Override
        public boolean onConsoleMessage(ConsoleMessage cm) {
            // errorDismiss();
            return true;
        }
        @Override
        public void onCloseWindow(WebView window) {
        }
    }


















    interface LoadingListener{
        void onError();
        void onLoadingReady();
    }
}
