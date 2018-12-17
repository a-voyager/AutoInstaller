package top.wuhaojie.installerlibrary.core;

import android.content.Context;

import top.wuhaojie.installerlibrary.ApkInfo;

/**
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2018/12/17 15:03
 * Version: 1.0
 */
public class SystemInstallStrategy implements InstallStrategy {

    @Override
    public boolean available() {
        return false;
    }

    @Override
    public void prepare(Context context, ApkInfo apkInfo) {

    }

    @Override
    public int install(Context context, ApkInfo apkInfo) {
        return 0;
    }


}
