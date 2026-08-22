package cn.nubia.systemwrapper;

import android.app.ActivityOptions;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.view.WindowManager;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.DisplayManagerWrapper;
import com.zte.shared.wrapper.WindowManagerWrapper;

/* loaded from: classes.dex */
public class ActivityManagerWrapper {

    /* renamed from: a, reason: collision with root package name */
    private static volatile ActivityManagerWrapper f9221a;

    public static ActivityManagerWrapper b() {
        if (f9221a == null) {
            synchronized (ActivityManagerWrapper.class) {
                try {
                    if (f9221a == null) {
                        f9221a = new ActivityManagerWrapper();
                    }
                } finally {
                }
            }
        }
        return f9221a;
    }

    public boolean a(String str) {
        try {
            return com.zte.shared.wrapper.ActivityManagerWrapper.checkTaskSupportWr(str);
        } catch (NoSuchMethodError e2) {
            GaLog.b("ActivityManagerWrapper", "checkTaskSupportWr, error:" + e2);
            return false;
        }
    }

    public UserHandle c(Context context, int i2) {
        return com.zte.shared.wrapper.ActivityManagerWrapper.getUserHandle(context, i2);
    }

    public WindowManager.LayoutParams d() {
        return WindowManagerWrapper.createOverlayLayoutParams();
    }

    public boolean e(Context context) {
        return DisplayManagerWrapper.isUnable(context);
    }

    public void f(Intent intent, Context context) {
        com.zte.shared.wrapper.ActivityManagerWrapper.startPipActivity(intent, context);
    }

    public void g(Intent intent, Context context, UserHandle userHandle) {
        com.zte.shared.wrapper.ActivityManagerWrapper.startPipActivityAsUser(intent, context, userHandle);
    }

    public void h(Intent intent, Context context) {
        GaLog.j("ActivityManagerWrapper", "startWindowFreeForm ");
        com.zte.shared.wrapper.ActivityManagerWrapper.startWindowFreeForm(intent, context, SystemMgr.w(), 0);
    }

    public void i(PendingIntent pendingIntent, Context context) {
        if (pendingIntent == null || context == null) {
            GaLog.b("ActivityManagerWrapper", "Intent or Context is null");
            return;
        }
        ActivityOptions makeBasic = ActivityOptions.makeBasic();
        makeBasic.setLaunchWindowingMode(5);
        makeBasic.setPendingIntentBackgroundActivityStartMode(1);
        if (context.getDisplayId() > 0) {
            GaLog.e("ActivityManagerWrapper", "startWindowFreeFormWithPendingIntent: DisplayId=" + context.getDisplayId());
            makeBasic.setLaunchDisplayId(context.getDisplayId());
        }
        Bundle bundle = makeBasic.toBundle();
        if (bundle != null) {
            bundle.putBoolean("WindowReply", true);
            bundle.putInt("Start_WindowReply_Mode", 0);
        }
        try {
            pendingIntent.send(null, 0, null, null, null, null, bundle);
        } catch (Exception e2) {
            GaLog.b("ActivityManagerWrapper", "Sending intent failed: " + e2.getMessage());
            e2.printStackTrace();
        }
    }
}
