package io.github.taodaren.androidandh5;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class JsCallJavaVideoActivity extends Activity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js_call_java_vedio);
        findViews();
    }

    private void findViews() {
        webView = (WebView)findViewById( R.id.web_view );
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        //支持缩放按钮
        settings.setBuiltInZoomControls(true);

        webView.setWebViewClient(new WebViewClient());
        //设置支持js调用java
        webView.addJavascriptInterface(new AndroidAndJsInterface(), "android");
        webView.loadUrl("file:///android_asset/RealNetJSCallJavaActivity.htm");
    }

    /**
     * 该类被 Js 调用
     */
    class AndroidAndJsInterface {

        @JavascriptInterface
        public void playVideo(int id, String videoUrl, String title) {
            //调用所有播放器
            Intent intent = new Intent();
            intent.setDataAndType(Uri.parse(videoUrl), "video/*");
            startActivity(intent);

            Toast.makeText(JsCallJavaVideoActivity.this, "videoUrl==" + videoUrl, Toast.LENGTH_SHORT).show();
        }
    }

}
