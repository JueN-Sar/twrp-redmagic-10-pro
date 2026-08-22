package cn.nubia.chatassistant;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.zte.shared.wrapper.GameAssistControllerWrapper;

/* loaded from: classes.dex */
public class SystemMgr {
    private static String TAG = "SystemMgr";
    private static final int WINDOWING_MODE_FULLSCREEN = 1;
    private static final int WINDOWING_MODE_UNDEFINED = 0;
    private static volatile SystemMgr mInstance = null;
    public static String sResumedFullscreenActivity = "";
    public static String sResumedFullscreenName = "";
    public static String sResumedFullscreenPackage = "";
    private Context mContext;

    public SystemMgr(Context context) {
        this.mContext = context;
    }

    public static String getCurFullscreenActivity() {
        return sResumedFullscreenActivity;
    }

    public static String getCurFullscreenName() {
        return sResumedFullscreenName;
    }

    public static String getCurFullscreenPackage() {
        return sResumedFullscreenPackage;
    }

    public static SystemMgr getInstance(Context context) {
        if (mInstance == null) {
            synchronized (SystemMgr.class) {
                if (mInstance == null) {
                    mInstance = new SystemMgr(context);
                }
            }
        }
        return mInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onActivityResumed(ComponentName componentName, ActivityInfo activityInfo, int i, int i2, int i3, int i4, int i5, int i6, Bundle bundle) {
        if (i4 != 0) {
            Log.d(TAG, "displayId is :" + i4);
        } else {
            onTopActivityChange(componentName, activityInfo, i6);
        }
    }

    private void onTopActivityChange(ComponentName componentName, ActivityInfo activityInfo, int i) {
        String packageName = componentName.getPackageName();
        String className = componentName.getClassName();
        String obj = activityInfo.loadLabel(this.mContext.getPackageManager()).toString();
        if (TextUtils.isEmpty(packageName) || sResumedFullscreenPackage.equals(packageName)) {
            return;
        }
        if (i == 0 || i == 1) {
            sResumedFullscreenPackage = packageName;
            sResumedFullscreenActivity = className;
            sResumedFullscreenName = obj;
        }
        Log.d(TAG, "sResumedFullscreenPackage:" + sResumedFullscreenPackage + " sResumedFullscreenActivity:" + sResumedFullscreenActivity + " sResumedFullscreenName:" + sResumedFullscreenName + " windowMode:" + i);
    }

    public void init() {
        Log.w(TAG, "init...");
        GameAssistControllerWrapper.registerFullActivityResumedCallback(new GameAssistControllerWrapper.FullActivityResumedCallback() { // from class: cn.nubia.chatassistant.SystemMgr$$ExternalSyntheticLambda0
            @Override // com.zte.shared.wrapper.GameAssistControllerWrapper.FullActivityResumedCallback
            public final void onFullActivityResumed(ComponentName componentName, ActivityInfo activityInfo, int i, int i2, int i3, int i4, int i5, int i6, Bundle bundle) {
                SystemMgr.this.onActivityResumed(componentName, activityInfo, i, i2, i3, i4, i5, i6, bundle);
            }
        });
    }
}
