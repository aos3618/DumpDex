package com.xposed;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by AoS on 2017/10/24.
 */

public class Dumper implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {

        Class clazz = loadPackageParam.classLoader.loadClass("com.android.dex.Dex");
        if (clazz != null) {
            XposedHelpers.findAndHookMethod(clazz, "create", ByteBuffer.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    Log.d("AoS ******", "beforeHookedMethod param is : " + loadPackageParam.processName + " / " + loadPackageParam.packageName);

                    if ("your.package.name".equals(loadPackageParam.processName)) {
                        dump((ByteBuffer) param.args[0], loadPackageParam.packageName);
                    }
                    super.beforeHookedMethod(param);
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    Log.d("AoS ******", "afterHookedMethod param is : " + param);
                }
            });
        }
    }

    private static int num = 1;

    private static void dump(ByteBuffer d, String processName) {
        d.order(ByteOrder.LITTLE_ENDIAN);
        d.duplicate();
        byte[] bytes = new byte[d.remaining()];
        d.get(bytes);

        File dir = new File(Environment.getExternalStorageDirectory().getPath() + "/dump");
        if (!dir.exists() || !dir.isDirectory()) {
            dir.mkdir();
        }
        String path = dir.getAbsolutePath() + "/dump_" + processName + num + ".dex";
        Log.e("AoS ******", "Begin dump dex :" + path + "  size :" + bytes.length);
        writeByteToFile(bytes, path);
        Log.e("AoS ******", "Finished dump dex :" + path + "  size :" + bytes.length);
        num++;

    }

    private static void writeByteToFile(byte[] data, String path) {
        try {
            FileOutputStream localFileOutputStream = new FileOutputStream(path);
            localFileOutputStream.write(data);
            localFileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
