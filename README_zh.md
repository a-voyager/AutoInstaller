# 应用自动安装库
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-AutoInstaller-green.svg?style=true)](https://android-arsenal.com/details/1/3972)

[中文](https://github.com/a-voyager/AutoInstaller/blob/master/README_zh.md) | [English](https://github.com/a-voyager/AutoInstaller/blob/master/README.md)

一个可以让您应用的自动更新功能**更加优雅**的静默安装库 !

> 别忘记给个赞支持一下哦 :）

## 特性
 - 只需要**一行**代码搞定您应用的后台静默下载和**静默（自动）安装**

  ```java
 AutoInstaller.getDefault(this).install(APK_FILE_PATH);
  ```
 - 两种自动安装方式:  `ROOT静默安装`  和  `辅助功能自动模拟点击安装` ，并且能够自动选择可用方式

![image](https://github.com/a-voyager/AutoInstaller/raw/master/imgs/GIF.gif)

## 依赖
可以选择两种方式:

 - 克隆本项目，然后在你的IDE中依赖此项目即可
 - 只需要在build.gradle中添加一下代码即可(可能暂时无法使用):

 ```groovy
    // 在项目根目录 build.gradle 添加
 	allprojects {
 		repositories {
 			...
 			maven { url "https://jitpack.io" }
 		}
 	}
 	// 在App目录 build.gradle 添加依赖
	dependencies {
	        compile 'com.github.a-voyager:AutoInstaller:v1.0'
	}
 ```

## 用法
 - 获取实例
 可以直接获取默认实例，或者使用  `AutoInstaller.builder` 来构造一个实例
 ```java
 AutoInstaller installer = AutoInstaller.getDefault(this);
 ```

 - 注册事件监听
 ```java
 installer.setOnStateChangedListener(new AutoInstaller.OnStateChangedListener() {
     @Override
     public void onStart() {
         // 当后台安装线程开始时回调
         mProgressDialog.show();
     }
     @Override
     public void onComplete() {
         // 当请求安装完成时回调
         mProgressDialog.dismiss();
     }
     @Override
     public void onNeed2OpenService() {
         // 当需要用户手动打开 `辅助功能服务` 时回调
         // 可以在这里提示用户打开辅助功能
         Toast.makeText(MainActivity.this, "请打开辅助功能服务", Toast.LENGTH_SHORT).show();
     }
 });
 ```

 - 安装过程
  `install()` 是一个异步的方法, 携带 `文件路径`  或者  `安装包HTTP下载地址` 作为参数
 ```java
 // 文件路径
 installer.install(APK_FILE_PATH);
 // 或者HTTP下载地址
 installer.installFromUrl(APK_URL);
 ```

## 更多
使用Builder构造器可以配置更多参数, 比如安装模式、缓存目录...
```java
AutoInstaller installer = new AutoInstaller.Builder(this)
        .setMode(AutoInstaller.MODE.AUTO_ONLY)
        .setCacheDirectory(CACHE_FILE_PATH)
        .build();
```


## 赞助作者


![Alipay](http://7xqdz8.com1.z0.glb.clouddn.com/pay_alipay.jpg)

![Weixin](http://7xqdz8.com1.z0.glb.clouddn.com/pay_weixin.png)



## License
    The MIT License (MIT)

    Copyright (c) 2015 WuHaojie

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.


