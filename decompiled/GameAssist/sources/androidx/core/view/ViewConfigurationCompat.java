package androidx.core.view;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.view.InputDevice;
import android.view.ViewConfiguration;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.util.Supplier;
import com.google.android.gms.common.api.Api;
import java.util.Objects;

/* loaded from: classes.dex */
public final class ViewConfigurationCompat {

    @RequiresApi
    static class Api26Impl {
        @DoNotInline
        static float a(ViewConfiguration viewConfiguration) {
            return viewConfiguration.getScaledHorizontalScrollFactor();
        }

        @DoNotInline
        static float b(ViewConfiguration viewConfiguration) {
            return viewConfiguration.getScaledVerticalScrollFactor();
        }
    }

    @RequiresApi
    static class Api28Impl {
        @DoNotInline
        static int a(ViewConfiguration viewConfiguration) {
            return viewConfiguration.getScaledHoverSlop();
        }

        @DoNotInline
        static boolean b(ViewConfiguration viewConfiguration) {
            return viewConfiguration.shouldShowMenuShortcutsWhenKeyboardPresent();
        }
    }

    @RequiresApi
    static class Api34Impl {
        @DoNotInline
        static int a(@NonNull ViewConfiguration viewConfiguration, int i2, int i3, int i4) {
            return viewConfiguration.getScaledMaximumFlingVelocity(i2, i3, i4);
        }

        @DoNotInline
        static int b(@NonNull ViewConfiguration viewConfiguration, int i2, int i3, int i4) {
            return viewConfiguration.getScaledMinimumFlingVelocity(i2, i3, i4);
        }
    }

    private static int a(Resources resources, int i2, Supplier supplier, int i3) {
        int dimensionPixelSize;
        return i2 != -1 ? (i2 == 0 || (dimensionPixelSize = resources.getDimensionPixelSize(i2)) < 0) ? i3 : dimensionPixelSize : ((Integer) supplier.get()).intValue();
    }

    private static int b(Resources resources, String str, String str2) {
        return resources.getIdentifier(str, str2, "android");
    }

    private static int c(Resources resources, int i2, int i3) {
        if (i2 == 4194304 && i3 == 26) {
            return b(resources, "config_viewMaxRotaryEncoderFlingVelocity", "dimen");
        }
        return -1;
    }

    private static int d(Resources resources, int i2, int i3) {
        if (i2 == 4194304 && i3 == 26) {
            return b(resources, "config_viewMinRotaryEncoderFlingVelocity", "dimen");
        }
        return -1;
    }

    public static float e(ViewConfiguration viewConfiguration, Context context) {
        return Api26Impl.a(viewConfiguration);
    }

    public static int f(Context context, final ViewConfiguration viewConfiguration, int i2, int i3, int i4) {
        if (Build.VERSION.SDK_INT >= 34) {
            return Api34Impl.a(viewConfiguration, i2, i3, i4);
        }
        if (!i(i2, i3, i4)) {
            return Integer.MIN_VALUE;
        }
        Resources resources = context.getResources();
        int c2 = c(resources, i4, i3);
        Objects.requireNonNull(viewConfiguration);
        return a(resources, c2, new Supplier() { // from class: androidx.core.view.h
            @Override // androidx.core.util.Supplier
            public final Object get() {
                return Integer.valueOf(viewConfiguration.getScaledMaximumFlingVelocity());
            }
        }, Integer.MIN_VALUE);
    }

    public static int g(Context context, final ViewConfiguration viewConfiguration, int i2, int i3, int i4) {
        if (Build.VERSION.SDK_INT >= 34) {
            return Api34Impl.b(viewConfiguration, i2, i3, i4);
        }
        if (!i(i2, i3, i4)) {
            return Api.BaseClientBuilder.API_PRIORITY_OTHER;
        }
        Resources resources = context.getResources();
        int d2 = d(resources, i4, i3);
        Objects.requireNonNull(viewConfiguration);
        return a(resources, d2, new Supplier() { // from class: androidx.core.view.i
            @Override // androidx.core.util.Supplier
            public final Object get() {
                return Integer.valueOf(viewConfiguration.getScaledMinimumFlingVelocity());
            }
        }, Api.BaseClientBuilder.API_PRIORITY_OTHER);
    }

    public static float h(ViewConfiguration viewConfiguration, Context context) {
        return Api26Impl.b(viewConfiguration);
    }

    private static boolean i(int i2, int i3, int i4) {
        InputDevice device = InputDevice.getDevice(i2);
        return (device == null || device.getMotionRange(i3, i4) == null) ? false : true;
    }

    public static boolean j(ViewConfiguration viewConfiguration, Context context) {
        return Api28Impl.b(viewConfiguration);
    }
}
