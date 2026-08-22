package com.google.android.material.color.utilities;

import androidx.annotation.RestrictTo;

@RestrictTo
/* loaded from: classes.dex */
public final class QuantizerWu implements Quantizer {

    /* renamed from: com.google.android.material.color.utilities.QuantizerWu$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f14349a;

        static {
            int[] iArr = new int[Direction.values().length];
            f14349a = iArr;
            try {
                iArr[Direction.RED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f14349a[Direction.GREEN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f14349a[Direction.BLUE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private static final class Box {
    }

    private static final class CreateBoxesResult {
    }

    private enum Direction {
        RED,
        GREEN,
        BLUE
    }

    private static final class MaximizeResult {
    }
}
