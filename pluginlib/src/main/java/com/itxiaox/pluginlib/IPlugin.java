package com.itxiaox.pluginlib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public interface IPlugin {

    int FROM_INTERNAL = 0;//从内部调用， 系统调用
    int FROM_EXTERNAL = 1;//从外部调用， 主apk调用

    void attachActivity(Activity activity);

    void onCreate(Bundle saveInstanceState);

    void onStart();

    void onRestart();

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();
}
