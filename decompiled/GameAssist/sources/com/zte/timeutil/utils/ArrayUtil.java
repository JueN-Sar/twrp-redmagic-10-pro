package com.zte.timeutil.utils;

/* loaded from: classes2.dex */
public class ArrayUtil {
    public static int a(int[] iArr) {
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (iArr[i2] == -1) {
                return i2;
            }
        }
        return -1;
    }
}
