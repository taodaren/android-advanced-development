package io.github.taodaren.androidandh5;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class JsCallJavaCallPhoneActivity extends Activity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js_call_java_call_phone);
        findViews();
    }

    private void findViews() {
        webView = (WebView) findViewById(R.id.web_view);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        //支持缩放按钮
        settings.setBuiltInZoomControls(true);

        webView.setWebViewClient(new WebViewClient());
        //设置支持js调用java
        webView.addJavascriptInterface(new AndroidAndJsInterface(), "Android");
        webView.loadUrl("file:///android_asset/JsCallJavaCallPhone.html");
    }

    /**
     * 该类被 Js 调用
     */
    class AndroidAndJsInterface {
        /**
         * 该方法将被js调用,用于加载数据
         */
        @JavascriptInterface
        public void showcontacts() {
            //下面的代码建议在子线程中调用
            String json = "[{\"name\":\"大禹\", \"phone\":\"18600012345\"}]";
            //调用JS中的方法
            webView.loadUrl("javascript:show('" + json + "')");
        }

        /**
         * 拨打电话
         */
        @JavascriptInterface
        public void call(String phone) {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
            if (ActivityCompat.checkSelfPermission(JsCallJavaCallPhoneActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            startActivity(intent);
        }
    }

}
