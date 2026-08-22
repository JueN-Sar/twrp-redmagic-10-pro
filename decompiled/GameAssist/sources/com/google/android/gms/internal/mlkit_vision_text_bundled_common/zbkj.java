package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import com.zte.shared.wrapper.VirtualHandleWrapper;

/* loaded from: classes.dex */
public final class zbkj {
    public static int a(int i2, int i3, String str) {
        String a2;
        if (i2 >= 0 && i2 < i3) {
            return i2;
        }
        if (i2 < 0) {
            a2 = zbkp.a("%s (%s) must not be negative", VirtualHandleWrapper.KEY_INDEX, Integer.valueOf(i2));
        } else {
            if (i3 < 0) {
                throw new IllegalArgumentException("negative size: " + i3);
            }
            a2 = zbkp.a("%s (%s) must be less than size (%s)", VirtualHandleWrapper.KEY_INDEX, Integer.valueOf(i2), Integer.valueOf(i3));
        }
        throw new IndexOutOfBoundsException(a2);
    }

    public static int b(int i2, int i3, String str) {
        if (i2 < 0 || i2 > i3) {
            throw new IndexOutOfBoundsException(e(i2, i3, VirtualHandleWrapper.KEY_INDEX));
        }
        return i2;
    }

    public static void c(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    public static void d(int i2, int i3, int i4) {
        if (i2 < 0 || i3 < i2 || i3 > i4) {
            throw new IndexOutOfBoundsException((i2 < 0 || i2 > i4) ? e(i2, i4, "start index") : (i3 < 0 || i3 > i4) ? e(i3, i4, "end index") : zbkp.a("end index (%s) must not be less than start index (%s)", Integer.valueOf(i3), Integer.valueOf(i2)));
        }
    }

    private static String e(int i2, int i3, String str) {
        if (i2 < 0) {
            return zbkp.a("%s (%s) must not be negative", str, Integer.valueOf(i2));
        }
        if (i3 >= 0) {
            return zbkp.a("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(i2), Integer.valueOf(i3));
        }
        throw new IllegalArgumentException("negative size: " + i3);
    }
}
