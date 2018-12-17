# Auto Installer Library
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-AutoInstaller-green.svg?style=true)](https://android-arsenal.com/details/1/3972)

[中文](https://github.com/a-voyager/AutoInstaller/blob/master/README_zh.md) | [English](https://github.com/a-voyager/AutoInstaller/blob/master/README.md)

A library makes your application's auto update **more elegant** !

> Don't forget give me a star :）

## Feature
 - Just **one line** of code to solve the silent download and **silent installation**

  ```java
 AutoInstaller.getDefault(this).install(APK_FILE_PATH);
  ```
 - two ways of silent installation:  `ROOT`  and  `AccessibilityService` , can automatically chose the valid way.

![image](https://github.com/a-voyager/AutoInstaller/raw/master/imgs/GIF.gif)

## Dependency
There are two ways:

 - clone this project, and use as dependency
 - just add following code to you build.gradle:

 ```groovy
    // Add it in your root build.gradle at the end of repositories
 	allprojects {
 		repositories {
 			...
 			maven { url "https://jitpack.io" }
 		}
 	}
 	// Add the dependency
	dependencies {
	        compile 'com.github.a-voyager:AutoInstaller:v1.0'
	}
 ```

## Usage
 - Get the instance
 get default instance or use  `AutoInstaller.builder`  to build a instance
 ```java
 AutoInstaller installer = AutoInstaller.getDefault(this);
 ```

 - Register listener
 ```java
 installer.setOnStateChangedListener(new AutoInstaller.OnStateChangedListener() {
     @Override
     public void onStart() {
         // callback when it starts installing
         mProgressDialog.show();
     }
     @Override
     public void onComplete() {
         // callback when is complete request installing
         mProgressDialog.dismiss();
     }
     @Override
     public void onNeed2OpenService() {
         // callback when `AccessibilityService` is needs and start the  `AccessibilityService` Activity
         // here you can notify user to open the service
         Toast.makeText(MainActivity.this, "Please open Accessibility Service", Toast.LENGTH_SHORT).show();
     }
 });
 ```

 - Installation
  `install()` is an asynchronous method, call this to request install an application with parameter  `file path`  or  `http url`
 ```java
 // file path
 installer.install(APK_FILE_PATH);
 // or http url
 installer.installFromUrl(APK_URL);
 ```

## More
Use builder to config installer, contains installation mode、cache directory...
```java
AutoInstaller installer = new AutoInstaller.Builder(this)
        .setMode(AutoInstaller.MODE.AUTO_ONLY)
        .setCacheDirectory(CACHE_FILE_PATH)
        .build();
```

## Updates
1. fixed on Android O
2. support override install


## Support me


![Alipay](http://7xqdz8.com1.z0.glb.clouddn.com/pay_alipay.jpg)

![Weixin](http://7xqdz8.com1.z0.glb.clouddn.com/pay_weixin.png)




