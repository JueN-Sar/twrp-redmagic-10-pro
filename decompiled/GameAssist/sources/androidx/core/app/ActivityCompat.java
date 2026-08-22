package androidx.core.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.SharedElementCallback;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.app.ActivityCompat;
import androidx.core.app.SharedElementCallback;
import androidx.core.content.ContextCompat;
import androidx.core.content.LocusIdCompat;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class ActivityCompat extends ContextCompat {

    /* renamed from: b, reason: collision with root package name */
    private static PermissionCompatDelegate f2616b;

    /* renamed from: androidx.core.app.ActivityCompat$1, reason: invalid class name */
    class AnonymousClass1 implements Runnable {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ String[] f2617c;

        /* renamed from: h, reason: collision with root package name */
        final /* synthetic */ Activity f2618h;

        /* renamed from: i, reason: collision with root package name */
        final /* synthetic */ int f2619i;

        @Override // java.lang.Runnable
        public void run() {
            int[] iArr = new int[this.f2617c.length];
            PackageManager packageManager = this.f2618h.getPackageManager();
            String packageName = this.f2618h.getPackageName();
            int length = this.f2617c.length;
            for (int i2 = 0; i2 < length; i2++) {
                iArr[i2] = packageManager.checkPermission(this.f2617c[i2], packageName);
            }
            ((OnRequestPermissionsResultCallback) this.f2618h).onRequestPermissionsResult(this.f2619i, this.f2617c, iArr);
        }
    }

    @RequiresApi
    static class Api21Impl {
        @DoNotInline
        static void a(Activity activity) {
            activity.finishAfterTransition();
        }

        @DoNotInline
        static void b(Activity activity) {
            activity.postponeEnterTransition();
        }

        @DoNotInline
        static void c(Activity activity, android.app.SharedElementCallback sharedElementCallback) {
            activity.setEnterSharedElementCallback(sharedElementCallback);
        }

        @DoNotInline
        static void d(Activity activity, android.app.SharedElementCallback sharedElementCallback) {
            activity.setExitSharedElementCallback(sharedElementCallback);
        }

        @DoNotInline
        static void e(Activity activity) {
            activity.startPostponedEnterTransition();
        }
    }

    @RequiresApi
    static class Api22Impl {
        @DoNotInline
        static Uri a(Activity activity) {
            return activity.getReferrer();
        }
    }

    @RequiresApi
    static class Api23Impl {
        /* JADX INFO: Access modifiers changed from: package-private */
        @DoNotInline
        public static void a(Object obj) {
            ((SharedElementCallback.OnSharedElementsReadyListener) obj).onSharedElementsReady();
        }

        @DoNotInline
        static void b(Activity activity, String[] strArr, int i2) {
            activity.requestPermissions(strArr, i2);
        }

        @DoNotInline
        static boolean c(Activity activity, String str) {
            return activity.shouldShowRequestPermissionRationale(str);
        }
    }

    @RequiresApi
    static class Api28Impl {
        @DoNotInline
        static <T> T a(Activity activity, int i2) {
            return (T) activity.requireViewById(i2);
        }
    }

    @RequiresApi
    static class Api30Impl {
        @DoNotInline
        static Display a(ContextWrapper contextWrapper) {
            return contextWrapper.getDisplay();
        }

        @DoNotInline
        static void b(@NonNull Activity activity, @Nullable LocusIdCompat locusIdCompat, @Nullable Bundle bundle) {
            activity.setLocusContext(locusIdCompat == null ? null : locusIdCompat.b(), bundle);
        }
    }

    @RequiresApi
    static class Api31Impl {
        @DoNotInline
        static boolean a(@NonNull Activity activity) {
            return activity.isLaunchedFromBubble();
        }

        @DoNotInline
        @SuppressLint({"BanUncheckedReflection"})
        static boolean b(Activity activity, String str) {
            try {
                return ((Boolean) PackageManager.class.getMethod("shouldShowRequestPermissionRationale", String.class).invoke(activity.getApplication().getPackageManager(), str)).booleanValue();
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
                return activity.shouldShowRequestPermissionRationale(str);
            }
        }
    }

    @RequiresApi
    static class Api32Impl {
        @DoNotInline
        static boolean a(Activity activity, String str) {
            return activity.shouldShowRequestPermissionRationale(str);
        }
    }

    public interface OnRequestPermissionsResultCallback {
        void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr);
    }

    public interface PermissionCompatDelegate {
        boolean a(Activity activity, String[] strArr, int i2);
    }

    @RestrictTo
    public interface RequestPermissionsRequestCodeValidator {
        void m(int i2);
    }

    @RequiresApi
    static class SharedElementCallback21Impl extends android.app.SharedElementCallback {

        /* renamed from: a, reason: collision with root package name */
        private final SharedElementCallback f2620a;

        @Override // android.app.SharedElementCallback
        public Parcelable onCaptureSharedElementSnapshot(View view, Matrix matrix, RectF rectF) {
            return this.f2620a.b(view, matrix, rectF);
        }

        @Override // android.app.SharedElementCallback
        public View onCreateSnapshotView(Context context, Parcelable parcelable) {
            return this.f2620a.c(context, parcelable);
        }

        @Override // android.app.SharedElementCallback
        public void onMapSharedElements(List list, Map map) {
            this.f2620a.d(list, map);
        }

        @Override // android.app.SharedElementCallback
        public void onRejectSharedElements(List list) {
            this.f2620a.e(list);
        }

        @Override // android.app.SharedElementCallback
        public void onSharedElementEnd(List list, List list2, List list3) {
            this.f2620a.f(list, list2, list3);
        }

        @Override // android.app.SharedElementCallback
        public void onSharedElementStart(List list, List list2, List list3) {
            this.f2620a.g(list, list2, list3);
        }

        @Override // android.app.SharedElementCallback
        public void onSharedElementsArrived(List list, List list2, final SharedElementCallback.OnSharedElementsReadyListener onSharedElementsReadyListener) {
            this.f2620a.h(list, list2, new SharedElementCallback.OnSharedElementsReadyListener() { // from class: androidx.core.app.a
                @Override // androidx.core.app.SharedElementCallback.OnSharedElementsReadyListener
                public final void onSharedElementsReady() {
                    ActivityCompat.Api23Impl.a(onSharedElementsReadyListener);
                }
            });
        }
    }

    public static void n(Activity activity) {
        activity.finishAffinity();
    }

    public static void o(Activity activity) {
        activity.recreate();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void p(Activity activity, String[] strArr, int i2) {
        PermissionCompatDelegate permissionCompatDelegate = f2616b;
        if (permissionCompatDelegate == null || !permissionCompatDelegate.a(activity, strArr, i2)) {
            HashSet hashSet = new HashSet();
            for (String str : strArr) {
                if (TextUtils.isEmpty(str)) {
                    throw new IllegalArgumentException("Permission request for permissions " + Arrays.toString(strArr) + " must not contain null or empty values");
                }
            }
            int size = hashSet.size();
            String[] strArr2 = size > 0 ? new String[strArr.length - size] : strArr;
            if (size > 0) {
                if (size == strArr.length) {
                    return;
                }
                int i3 = 0;
                for (int i4 = 0; i4 < strArr.length; i4++) {
                    if (!hashSet.contains(Integer.valueOf(i4))) {
                        strArr2[i3] = strArr[i4];
                        i3++;
                    }
                }
            }
            if (activity instanceof RequestPermissionsRequestCodeValidator) {
                ((RequestPermissionsRequestCodeValidator) activity).m(i2);
            }
            Api23Impl.b(activity, strArr, i2);
        }
    }

    public static void q(Activity activity, Intent intent, int i2, Bundle bundle) {
        activity.startActivityForResult(intent, i2, bundle);
    }

    public static void r(Activity activity, IntentSender intentSender, int i2, Intent intent, int i3, int i4, int i5, Bundle bundle) {
        activity.startIntentSenderForResult(intentSender, i2, intent, i3, i4, i5, bundle);
    }
}
