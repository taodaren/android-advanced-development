package io.github.taodaren.androidandh5;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AndroidAndJsActivity extends Activity implements View.OnClickListener {
    private WebView webView;
    private EditText etNumber;
    private EditText etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_and_js);
        findViews();
        initWebView();
    }

    private void findViews() {
        etNumber = (EditText) findViewById(R.id.et_number);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnLogin = (Button) findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(this);
    }

    private void initWebView() {
        webView = new WebView(this);
        WebSettings settings = webView.getSettings();

        //设置支持javaScript脚本语言
        settings.setJavaScriptEnabled(true);
        //支持双击-前提是页面要支持才显示
        settings.setUseWideViewPort(true);
        //支持缩放按钮-前提是页面要支持才显示
        settings.setBuiltInZoomControls(true);
        //设置客户端-不跳转到默认浏览器
        webView.setWebViewClient(new WebViewClient());

        //设置支持js调用java
        webView.addJavascriptInterface(new AndroidAndJsInterface(), "Android");

        //加载网络资源
//        webView.loadUrl("https://taodaren.github.io/");
        //加载本地资源
        webView.loadUrl("file:///android_asset/JavaAndJavaScriptCall.html");

        //显示页面
//        setContentView(webView);
    }

    @Override
    public void onClick(View v) {
        if (v == btnLogin) {
            login();
        }
    }

    private void login() {
        String number = etNumber.getText().toString().trim();// trim()方法用于忽略空格
        String password = etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(number) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "账号或密码为空", Toast.LENGTH_SHORT).show();
        } else {
            //登录
            callJs(number);
        }
    }

    private void callJs(String number) {
        //java调用Js
        webView.loadUrl("javascript:javaCallJs(" + "'" + number + "'" + ")");
        //显示页面
        setContentView(webView);
    }

    class AndroidAndJsInterface {
        @JavascriptInterface
        public void showToast() {
            Toast.makeText(AndroidAndJsActivity.this, "我被js调用了", Toast.LENGTH_SHORT).show();
        }
    }
}
