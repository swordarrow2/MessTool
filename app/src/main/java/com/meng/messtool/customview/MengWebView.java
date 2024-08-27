package com.meng.messtool.customview;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.net.*;
import android.net.http.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.webkit.*;
import com.meng.messtool.*;

public class MengWebView extends WebView {

    /**
     * Created by 清梦 on 2018/6/12.
     */
    public static final String TAG = "MengWebView";   

    public MengWebView(Context context) {
        super(context);
    }

    public MengWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void init() {
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUserAgentString(getUA());
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setUseWideViewPort(true);
        settings.setPluginState(WebSettings.PluginState.ON);
        settings.setLoadWithOverviewMode(true);
        settings.setGeolocationEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setSupportZoom(true);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollbarOverlay(true);
        settings.setAllowFileAccess(true);
        settings.setDomStorageEnabled(true);
        setOnTouchListener(new OnTouchListener() {

                @Override
                public boolean onTouch(View p1, MotionEvent p2) {
//                    MainActivity.instence.menuBar.setVisibility(View.GONE);
//                    MainActivity.instence.topBar.setIsEdit(false);
                    return false;
                }
            });
        setDownloadListener(new DownloadListener() {

                @Override
                public void onDownloadStart(String p1, String p2, String p3, String p4, long p5) {
                    ApplicationHolder.getActivity().showToast(p1 + "  " + p2 + "   " + p3 + "  " + p4 + "  " + p5);
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse(p1));
                    getContext().startActivity(intent);
                }
            });
        setWebViewClient(new WebViewClient(){
                String lastUrl = "";

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    //    MainActivity.instence.topBar.setUrl(url);
                    if (url.startsWith("https") || url.startsWith("http")) {
                        view.loadUrl(url);
                    } else {
                        try {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                            getContext().startActivity(intent);
                        } catch (ActivityNotFoundException e) {
                            // TODO: handle exception
                        }
                    }
                    return true;
                }

                @Override
                public void onPageFinished(WebView webView, String url) {
                    super.onPageFinished(webView, url);
                    lastUrl = url;
                    //     MainActivity.instence.topBar.setUrl(MainActivity.instence.webView.getUrl());
                    CookieManager cookieManager = CookieManager.getInstance();
                    String CookieStr = cookieManager.getCookie(url);
                    if (CookieStr != null) {
                        //       MainActivity.instence.sharedPreference.putValue(Data.preferenceKey.cookieValue, CookieStr);
                    }
                    webView.loadUrl("javascript:navigator.__defineGetter__('userAgent', function(){ return 'Mozilla/5.0 (Symbian/3; Series60/5.2 NokiaN8-00/012.002; Profile/MIDP-2.1 Configuration/CLDC-1.1 ) AppleWebKit/533.4 (KHTML, like Gecko) NokiaBrowser/7.3.0 Mobile Safari/533.4 3gpp-gba'; });");
                    webView.loadUrl("javascript:alert(\"jsAlert\");");
                    webView.evaluateJavascript("javascript:alert(\"Android调用了JS的callJS方法\");", new ValueCallback<String>() {
                            @Override
                            public void onReceiveValue(String value) {
                                //    此处为 js 返回的结果
                            }
                        });
                }

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    // TODO: Implement this method
                    if (!lastUrl.equals(url)) {
//                        try {
//                            MainActivity.instence.topBar.setUrl(url);
//                        } catch (Exception e) {
//                            tool.showToast(view.getContext(), e.toString());
//                        }
                    }
                    super.onPageStarted(view, url, favicon);
                }

                @Override
                public void onFormResubmission(WebView view, Message dontResend, Message resend) {
                    super.onFormResubmission(view, dontResend, resend);
                }

                @Override
                public void onLoadResource(WebView view, String url) {
                    super.onLoadResource(view, url);
                }

                @Override
                public void onPageCommitVisible(WebView view, String url) {
                    super.onPageCommitVisible(view, url);
                }

                @Override
                public void onReceivedClientCertRequest(WebView view, ClientCertRequest request) {
                    super.onReceivedClientCertRequest(view, request);
                }

                @Override
                public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                    super.onReceivedError(view, request, error);
                }

                @Override
                public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
                    super.onReceivedHttpAuthRequest(view, handler, host, realm);
                }

                @Override
                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                    super.onReceivedError(view, errorCode, description, failingUrl);
                }

                @Override
                public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                    super.onReceivedHttpError(view, request, errorResponse);
                }

                @Override
                public void onReceivedLoginRequest(WebView view, String realm, String account, String args) {
                    super.onReceivedLoginRequest(view, realm, account, args);
                }

                @Override
                public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                    super.onReceivedSslError(view, handler, error);
                }

                @Override
                public void onScaleChanged(WebView view, float oldScale, float newScale) {
                    super.onScaleChanged(view, oldScale, newScale);
                }

                @Override
                public void onUnhandledKeyEvent(WebView view, KeyEvent event) {
                    super.onUnhandledKeyEvent(view, event);
                }

                @Override
                public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                    return super.shouldInterceptRequest(view, request);
                }
            });
        setWebChromeClient(new WebChromeClient() {
                @Override
                public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                    AlertDialog.Builder b = new AlertDialog.Builder(view.getContext());
                    b.setTitle("来自" + view.getTitle() + "的提示");
                    b.setMessage(message);
                    b.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                result.confirm();
                            }
                        });
                    b.setCancelable(false);
                    b.create().show();
                    return true;
                }

                @Override
                public void onProgressChanged(WebView view, int newProgress) {
                    //     MainActivity.instence.topBar.setProgress(newProgress);
                }

                @Override
                public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                    return super.onConsoleMessage(consoleMessage);
                }

                @Override
                public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
                    return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
                }

                @Override
                public boolean onJsBeforeUnload(WebView view, String url, String message, JsResult result) {
                    return super.onJsBeforeUnload(view, url, message, result);
                }

                @Override
                public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                    return super.onJsConfirm(view, url, message, result);
                }

                @Override
                public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                    return super.onJsPrompt(view, url, message, defaultValue, result);
                }

                @Override
                public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                    return super.onShowFileChooser(webView, filePathCallback, fileChooserParams);
                }

                @Override
                public void onCloseWindow(WebView window) {
                    super.onCloseWindow(window);
                }

                @Override
                public void onGeolocationPermissionsHidePrompt() {
                    super.onGeolocationPermissionsHidePrompt();
                }

                @Override
                public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                    super.onGeolocationPermissionsShowPrompt(origin, callback);
                }

                @Override
                public void onHideCustomView() {
                    super.onHideCustomView();
                }

                @Override
                public void onPermissionRequest(PermissionRequest request) {
                    super.onPermissionRequest(request);
                }

                @Override
                public void onPermissionRequestCanceled(PermissionRequest request) {
                    super.onPermissionRequestCanceled(request);
                }

                @Override
                public void onReceivedIcon(WebView view, Bitmap icon) {
                    super.onReceivedIcon(view, icon);
                }

                @Override
                public void onReceivedTitle(WebView view, String title) {
                    super.onReceivedTitle(view, title);
                }

                @Override
                public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed) {
                    super.onReceivedTouchIconUrl(view, url, precomposed);
                }

                @Override
                public void onRequestFocus(WebView view) {
                    super.onRequestFocus(view);
                }

                @Override
                public void onShowCustomView(View view, CustomViewCallback callback) {
                    super.onShowCustomView(view, callback);
                }
            }
        );
    }

    public boolean goToBack() {
        if (canGoBack()) {
            goBack();
            return true;
        } else {
            return false;
        }
    }

    public boolean goToForward() {
        if (canGoForward()) {
            goForward();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void loadUrl(String url) {
        //   MainActivity.instence.historyAndCollectionTool.addHistory(url);
        super.loadUrl(url);
    }

    @Override
    public void stopLoading() {
        super.stopLoading();
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
    }

    private String getUA() {
//        String data = MainActivity.instence.sharedPreference.getValue(Data.preferenceKey.userAgentList, "default_value");
//        if (data.equals("default_value")) {
        return getSettings().getUserAgentString();
//        }
//        if (data.equals("by_user")) {
//            return MainActivity.instence.sharedPreference.getValue(Data.preferenceKey.userAgent);
//        }
//        return data;
    }

}

