package top.wuhaojie.installerlibrary.core;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import top.wuhaojie.installerlibrary.ApkInfo;
import top.wuhaojie.installerlibrary.utils.Utils;

/**
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2018/12/17 14:51
 * Version: 1.0
 */
public class RootInstallStrategy implements InstallStrategy {

    private static final String TAG = RootInstallStrategy.class.getSimpleName();

    @Override
    public boolean available() {
        return Utils.checkRooted();
    }


    @Override
    public void prepare(Context context, ApkInfo apkInfo) {

    }

    @Override
    public int install(Context context, ApkInfo apkInfo) {
        Uri sourceUri = apkInfo.getSourceUri();
        String path = sourceUri.getPath();
        return installUseRoot(path) ? 200 : -1;
    }

    private boolean installUseRoot(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            throw new IllegalArgumentException("Please check apk file path!");
        }
        boolean result = false;
        Process process = null;
        OutputStream outputStream = null;
        BufferedReader errorStream = null;
        try {
            process = Runtime.getRuntime().exec("su");
            outputStream = process.getOutputStream();

            String command = "pm install -r " + filePath + "\n";
            outputStream.write(command.getBytes());
            outputStream.flush();
            outputStream.write("exit\n".getBytes());
            outputStream.flush();
            process.waitFor();
            errorStream = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            StringBuilder msg = new StringBuilder();
            String line;
            while ((line = errorStream.readLine()) != null) {
                msg.append(line);
            }
            Log.d(TAG, "install msg is " + msg);
            if (!msg.toString().contains("Failure")) {
                result = true;
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            result = false;
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (errorStream != null) {
                    errorStream.close();
                }
            } catch (IOException e) {
                outputStream = null;
                errorStream = null;
                process.destroy();
            }
        }
        return result;
    }

}
