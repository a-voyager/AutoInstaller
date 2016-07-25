package top.wuhaojie.installerlibrary.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * Created by wuhaojie on 2016/7/25 22:18.
 */
public class Utils {


    public static final String TAG = "Utils";

    // 此方法工作有误
    @Deprecated
    public static boolean isRooted() {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("su");
            OutputStream outputStream = process.getOutputStream();
            InputStream inputStream = process.getInputStream();
            outputStream.write("id\n".getBytes());
            outputStream.flush();
            outputStream.write("exit\n".getBytes());
            outputStream.flush();
            process.waitFor();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String s = bufferedReader.readLine();
            if (s.contains("uid=0")) return true;
        } catch (IOException e) {
            Log.e(TAG, "没有root权限");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (process != null)
                process.destroy();
        }
        return false;
    }

    public static boolean checkRooted() {
        boolean result = false;
        try {
            result = new File("/system/bin/su").exists() || new File("/system/xbin/su").exists();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
