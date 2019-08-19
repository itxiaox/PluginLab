package com.itxiaox.pluginlib;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {


    /**
     * 复制文件
     * @param sourceFile 源文件
     * @param targetFile 目标文件
     * @throws IOException
     */
    public static void copyFile(File sourceFile,File targetFile) throws IOException {
      //新建文件输入流并对它进行缓冲
      FileInputStream fis = new FileInputStream(sourceFile);

      BufferedInputStream bis = new BufferedInputStream(fis);

      //新建文件输出流并对它进行缓冲

      FileOutputStream fos = new FileOutputStream(targetFile);

      BufferedOutputStream bos = new BufferedOutputStream(fos);
      //缓冲数组
      byte[] b = new byte[1024 * 5];
      int len;
      while ((len = bis.read(b))!=-1){
          bos.write(b,0,len);
      }
      //刷新此缓冲的输出流
      bos.flush();
      //关闭流
        bis.close();
        bos.close();
        fos.close();
        fis.close();
    }

    public static String copyAssetAndWrite(Context context,String fileName){
        try{
            File cacheDir = context.getCacheDir();
            if (!cacheDir.exists()){
                cacheDir.mkdirs();
            }
            File outFile = new File(cacheDir,fileName);
            if(outFile.exists()){
                outFile.delete();
            }
            if(outFile.createNewFile()){
                InputStream is = context.getAssets().open(fileName);

                FileOutputStream fos = new FileOutputStream(outFile);
                //缓冲数组
                byte[] b = new byte[is.available()];
                int len;
                while ((len = is.read(b))!= -1){
                    fos.write(b,0,len);
                }
                fos.flush();
                is.close();
                fos.close();
                return outFile.getAbsolutePath();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
