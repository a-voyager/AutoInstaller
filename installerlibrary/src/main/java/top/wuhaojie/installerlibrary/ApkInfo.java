package top.wuhaojie.installerlibrary;

import android.net.Uri;

/**
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2018/12/17 14:43
 * Version: 1.0
 */
public class ApkInfo {

    private Uri sourceUri;

    private String md5;

    private String desc;

    public Uri getSourceUri() {
        return sourceUri;
    }

    public void setSourceUri(Uri sourceUri) {
        this.sourceUri = sourceUri;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
