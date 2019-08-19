package com.itxiaox.pluginlib;

import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;

import dalvik.system.DexClassLoader;

/**
 * 插件APK信息的实体对象
 */
public class PluginApk {
    public DexClassLoader classLoader;
    public Resources resources;
    public PackageInfo packageInfo;

    public AssetManager assetManager;

    public PluginApk(DexClassLoader classLoader, Resources resources, PackageInfo packageInfo) {
        this.classLoader = classLoader;
        this.resources = resources;
        this.packageInfo = packageInfo;
        assetManager = resources.getAssets();
    }



}
