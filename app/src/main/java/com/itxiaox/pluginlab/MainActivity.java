package com.itxiaox.pluginlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.itxiaox.pluginlib.FileUtils;
import com.itxiaox.pluginlib.PluginManager;
import com.itxiaox.pluginlib.ProxyActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PluginManager.getInstance().init(this);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadApk();
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goPluginActivity();
            }
        });
    }

    private void loadApk() {
        String apkPath = FileUtils.copyAssetAndWrite(MainActivity.this,"pluginapk.apk");
        //加载APK
        PluginManager.getInstance().loadApk(apkPath);
        Log.i(TAG, "loadApk: load apk success");
    }

    private void goPluginActivity() {
        //制定跳转到的类名
        Intent intent = new Intent(MainActivity.this, ProxyActivity.class);
         intent.putExtra("className","com.itxiaox.pluginapk.PluginTestActivity");
         startActivity(intent);
    }
}
