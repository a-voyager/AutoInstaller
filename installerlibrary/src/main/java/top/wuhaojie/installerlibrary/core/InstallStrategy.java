package top.wuhaojie.installerlibrary.core;

import android.content.Context;

import top.wuhaojie.installerlibrary.ApkInfo;

/**
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2018/12/17 14:49
 * Version: 1.0
 */
public interface InstallStrategy {

    boolean available();

    void prepare(Context context, ApkInfo apkInfo);

    int install(Context context, ApkInfo apkInfo);

}
