package com.itxiaox.pluginapk;

import android.os.Bundle;
import android.view.View;

import com.itxiaox.pluginlib.PluginActivity;

public class PluginTestActivity extends PluginActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plugin_test);
    }

    public void back(View view) {
        PluginTestActivity.this.finish();
    }
}
