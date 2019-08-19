package com.itxiaox.pluginlib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 管理Activity生命周期,手动管理，非系统管理
 */
public class PluginActivity  extends Activity implements IPlugin {
    private int mFrom = FROM_INTERNAL;

    //插件的上下文
    Activity mProxyActivity;
    @Override
    public void attachActivity(Activity activity) {
        mProxyActivity = activity;
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        if(saveInstanceState!=null){
            mFrom = saveInstanceState.getInt("FROM");
        }
        if(mFrom == FROM_INTERNAL){
            super.onCreate(saveInstanceState);
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        if(mFrom == FROM_INTERNAL){
            super.setContentView(layoutResID);
        }else {
            mProxyActivity.setContentView(layoutResID);
        }
    }

    @Override
    public void onStart() {
        if(mFrom == FROM_INTERNAL){
            super.onStart();
        }
    }

    @Override
    public void onRestart() {
        if(mFrom == FROM_INTERNAL){
            super.onRestart();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(mFrom == FROM_INTERNAL){
            super.onActivityResult(requestCode,  resultCode,  data);
        }
    }

    @Override
    public void onResume() {
        if(mFrom == FROM_INTERNAL){
            super.onResume();
        }
    }

    @Override
    public void onPause() {
        if(mFrom == FROM_INTERNAL){
            super.onPause();
        }
    }

    @Override
    public void onStop() {
        if(mFrom == FROM_INTERNAL){
            super.onStop();
        }
    }

    @Override
    public void onDestroy() {
        if(mFrom == FROM_INTERNAL){
            super.onDestroy();
        }
    }
}
