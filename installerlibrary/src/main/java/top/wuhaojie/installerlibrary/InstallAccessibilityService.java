package top.wuhaojie.installerlibrary;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wuhaojie on 2016/7/25 23:15.
 */
public class InstallAccessibilityService extends android.accessibilityservice.AccessibilityService {

    private static final String TAG = InstallAccessibilityService.class.getSimpleName();

    private Map<Integer, Boolean> handledMap = new HashMap<>();

    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.d(TAG, "onAccessibilityEvent: " + event.toString());

        if (!String.valueOf(event.getPackageName()).contains("packageinstaller")) {
            //不写完整包名，是因为某些手机(如小米)安装器包名是自定义的
            return;
        }

        AccessibilityNodeInfo nodeInfo = event.getSource();
        if (nodeInfo == null) {
            Log.i(TAG, "eventNode: null, 重新获取eventNode...");
            performGlobalAction(GLOBAL_ACTION_RECENTS); // 打开最近页面
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    performGlobalAction(GLOBAL_ACTION_BACK); // 返回安装页面
                }
            }, 320);
            return;
        }

        int eventType = event.getEventType();
        if (eventType == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED ||
                eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            if (handledMap.get(event.getWindowId()) == null) {
                boolean handled = iterateNodesAndHandle(nodeInfo);
                if (handled) {
                    handledMap.put(event.getWindowId(), true);
                }
            }
        }
    }

    private boolean iterateNodesAndHandle(AccessibilityNodeInfo nodeInfo) {
        if (nodeInfo != null) {
            int childCount = nodeInfo.getChildCount();
            if ("android.widget.Button".equals(nodeInfo.getClassName())) {
                String nodeContent = nodeInfo.getText().toString();
                Log.d("TAG", "content is " + nodeContent);
                if (!TextUtils.isEmpty(nodeContent)
                        && ("安装".equals(nodeContent)
                        || "install".equals(nodeContent.toLowerCase())
                        || "done".equals(nodeContent.toLowerCase())
                        || "完成".equals(nodeContent)
                        || "确定".equals(nodeContent)
                )) {
                    nodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    return true;
                }
            } else if ("android.widget.ScrollView".equals(nodeInfo.getClassName())) {
                nodeInfo.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
            }
            for (int i = 0; i < childCount; i++) {
                AccessibilityNodeInfo childNodeInfo = nodeInfo.getChild(i);
                if (iterateNodesAndHandle(childNodeInfo)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void onInterrupt() {

    }
}
