package com.itxiaox.pluginlib;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProxyActivity extends Activity {

    private static final String TAG = "Plugin";
    private String mClassName;

    private PluginApk pluginApk;
    private IPlugin iPlugin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mClassName = getIntent().getStringExtra("className");

        pluginApk = PluginManager.getInstance().getPluginApk();

        launchPluginActivity();
    }

    private void launchPluginActivity() {
        if (pluginApk == null){
            Log.e(TAG, "load plugin apk fail: ");
            return;
        }
        try {
            Class<?> clazz = pluginApk.classLoader.loadClass(mClassName);
            //实例化Activity,注意，这里的activity是没有生命周期的，也没有上下文对象
            Object object = clazz.newInstance();
            if (object instanceof IPlugin){
                iPlugin = (IPlugin) object;
                iPlugin.attachActivity(this);
                Bundle bundle = new Bundle();
                bundle.putInt("FROM",IPlugin.FROM_EXTERNAL);
                iPlugin.onCreate(bundle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public Resources getResources() {
        return pluginApk!=null ?pluginApk.resources : super.getResources();
    }

    @Override
    public AssetManager getAssets() {
        return pluginApk!=null ?pluginApk.assetManager : super.getAssets();
    }

    @Override
    public ClassLoader getClassLoader() {
        return pluginApk!=null ?pluginApk.classLoader :super.getClassLoader();
    }
}
