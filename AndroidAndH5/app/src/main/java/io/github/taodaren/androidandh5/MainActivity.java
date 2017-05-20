package io.github.taodaren.androidandh5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button btnJavaAndJs;
    private Button btnJsCallJava;
    private Button btnJsCallPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
    }

    private void findViews() {
        btnJavaAndJs = (Button) findViewById(R.id.btn_java_and_js);
        btnJsCallJava = (Button) findViewById(R.id.btn_js_call_java);
        btnJsCallPhone = (Button) findViewById(R.id.btn_js_call_phone);

        btnJavaAndJs.setOnClickListener(this);
        btnJsCallJava.setOnClickListener(this);
        btnJsCallPhone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnJavaAndJs) {
            Intent intent = new Intent(this, AndroidAndJsActivity.class);
            startActivity(intent);
        } else if (v == btnJsCallJava) {
            Intent intent = new Intent(this, JsCallJavaVideoActivity.class);
            startActivity(intent);
        } else if (v == btnJsCallPhone) {
            Intent intent = new Intent(this, JsCallJavaCallPhoneActivity.class);
            startActivity(intent);
        }
    }
}
