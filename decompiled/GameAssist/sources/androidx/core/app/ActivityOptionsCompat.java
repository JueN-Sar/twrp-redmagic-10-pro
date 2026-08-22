package androidx.core.app;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.PendingIntent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;

/* loaded from: classes.dex */
public class ActivityOptionsCompat {

    private static class ActivityOptionsCompatImpl extends ActivityOptionsCompat {

        /* renamed from: a, reason: collision with root package name */
        private final ActivityOptions f2621a;

        @Override // androidx.core.app.ActivityOptionsCompat
        public Bundle a() {
            return this.f2621a.toBundle();
        }
    }

    @RequiresApi
    static class Api21Impl {
        @DoNotInline
        static ActivityOptions a(Activity activity, View view, String str) {
            return ActivityOptions.makeSceneTransitionAnimation(activity, view, str);
        }

        @SafeVarargs
        @DoNotInline
        static ActivityOptions b(Activity activity, Pair<View, String>... pairArr) {
            return ActivityOptions.makeSceneTransitionAnimation(activity, pairArr);
        }

        @DoNotInline
        static ActivityOptions c() {
            return ActivityOptions.makeTaskLaunchBehind();
        }
    }

    @RequiresApi
    static class Api23Impl {
        @DoNotInline
        static ActivityOptions a() {
            return ActivityOptions.makeBasic();
        }

        @DoNotInline
        static ActivityOptions b(View view, int i2, int i3, int i4, int i5) {
            return ActivityOptions.makeClipRevealAnimation(view, i2, i3, i4, i5);
        }

        @DoNotInline
        static void c(ActivityOptions activityOptions, PendingIntent pendingIntent) {
            activityOptions.requestUsageTimeReport(pendingIntent);
        }
    }

    @RequiresApi
    static class Api24Impl {
        @DoNotInline
        static Rect a(ActivityOptions activityOptions) {
            return activityOptions.getLaunchBounds();
        }

        @DoNotInline
        static ActivityOptions b(ActivityOptions activityOptions, Rect rect) {
            return activityOptions.setLaunchBounds(rect);
        }
    }

    @RequiresApi
    static class Api34Impl {
        @DoNotInline
        static ActivityOptions a(ActivityOptions activityOptions, boolean z) {
            return activityOptions.setShareIdentityEnabled(z);
        }
    }

    public Bundle a() {
        return null;
    }
}
