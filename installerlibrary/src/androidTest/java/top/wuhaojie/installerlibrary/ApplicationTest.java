package top.wuhaojie.installerlibrary;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import top.wuhaojie.installerlibrary.utils.Utils;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {

    public static final String TAG = "Test";

    public ApplicationTest() {
        super(Application.class);
    }

    public void testRoot() {
        boolean b = Utils.checkRooted();
        Log.d(TAG, b + "");
    }

}