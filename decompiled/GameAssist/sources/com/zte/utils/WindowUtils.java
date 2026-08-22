package com.zte.utils;

import android.app.ActivityThread;
import android.os.Bundle;
import cn.nubia.gameassist.view.NubiaTextClock;
import com.zte.activityevent.ActivityEventsManager;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class WindowUtils {

    public static class VisibleActivity {

        /* renamed from: a, reason: collision with root package name */
        public final String f18200a;

        /* renamed from: b, reason: collision with root package name */
        public final String f18201b;

        /* renamed from: c, reason: collision with root package name */
        public final String f18202c;

        /* renamed from: d, reason: collision with root package name */
        public final String f18203d;

        /* renamed from: e, reason: collision with root package name */
        public final int f18204e;

        /* renamed from: f, reason: collision with root package name */
        public final int f18205f;

        /* renamed from: g, reason: collision with root package name */
        public final int f18206g;

        /* renamed from: h, reason: collision with root package name */
        public final int f18207h;

        public VisibleActivity(String str, String str2, String str3, String str4, int i2, int i3, int i4, int i5) {
            this.f18200a = str;
            this.f18201b = str2;
            this.f18202c = str3;
            this.f18203d = str4;
            this.f18204e = i2;
            this.f18205f = i3;
            this.f18206g = i4;
            this.f18207h = i5;
        }

        public String toString() {
            return "Task{mPackageName='" + this.f18200a + NubiaTextClock.QUOTE + ", mTopActivity='" + this.f18201b + NubiaTextClock.QUOTE + ", mUid='" + this.f18203d + NubiaTextClock.QUOTE + ", mWindowMode=" + this.f18204e + ", mActivityType=" + this.f18205f + ", mRootTaskId=" + this.f18206g + ", mDisplayId=" + this.f18207h + '}';
        }
    }

    public static List a() {
        ArrayList arrayList = new ArrayList();
        ActivityEventsManager activityEventsManager = ActivityEventsManager.getInstance(ActivityThread.currentApplication());
        if (activityEventsManager != null) {
            try {
                List visiblePackageDates = activityEventsManager.getVisiblePackageDates();
                if (visiblePackageDates != null && visiblePackageDates.size() > 0) {
                    for (int size = visiblePackageDates.size() - 1; size >= 0; size--) {
                        Bundle bundle = (Bundle) visiblePackageDates.get(size);
                        if (bundle != null && bundle.containsKey("packageName") && bundle.containsKey("windowMode")) {
                            arrayList.add(new VisibleActivity(bundle.getString("packageName"), bundle.getString("topActivity"), bundle.getString("windowTitle"), bundle.getString("reserved"), bundle.getInt("windowMode"), bundle.getInt("activityType"), bundle.getInt("rootTaskId"), bundle.getInt("displayId")));
                        } else {
                            arrayList.add(null);
                        }
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return arrayList;
    }
}
