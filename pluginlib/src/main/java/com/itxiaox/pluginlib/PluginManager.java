package com.itxiaox.pluginlib;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class PluginManager {

    private Context mContext;
    private PluginApk pluginApk;
    private static PluginManager instance = new PluginManager();
    private PluginManager(){}

    public  static  PluginManager getInstance(){
        return instance;
    }

    public void init(Context context){
        mContext = context.getApplicationContext();
    }



    //加载插件apk
    public void loadApk(String apkPath){
        PackageInfo packageInfo = mContext.getPackageManager().getPackageArchiveInfo(apkPath,
                PackageManager.GET_ACTIVITIES|PackageManager.GET_SERVICES);

        if(packageInfo==null)return;

        DexClassLoader classLoader = createDexClassLoader(apkPath);

        AssetManager am = createAssetManager(apkPath);

        Resources resources = createResource(am);

         pluginApk = new PluginApk(classLoader,resources,packageInfo);

    }

    public PluginApk getPluginApk(){
        return pluginApk;
    }

    //创建访问插件apk的DexClassLoader
    private DexClassLoader createDexClassLoader(String apkPath) {
        File file = mContext.getDir("dex",Context.MODE_PRIVATE);
        return new DexClassLoader(apkPath,file.getAbsolutePath(),null,mContext.getClassLoader());

    }

   //创建访问插件apk资源的AssetManager
    private AssetManager createAssetManager(String apkPath) {
        AssetManager am = null;
        try {
            am = AssetManager.class.newInstance();
            Method method = AssetManager.class.getDeclaredMethod("addAssetPath",String.class);
            method.invoke(am,apkPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return am;
    }

    //创建访问插件apk资源的Resource对象
    private Resources createResource(AssetManager am) {
        Resources resources = mContext.getResources();
        return new Resources(am,resources.getDisplayMetrics(),resources.getConfiguration());
    }

}
