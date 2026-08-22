package com.airbnb.lottie.model.content;

import androidx.core.graphics.BlendModeCompat;

/* loaded from: classes.dex */
public enum LBlendMode {
    NORMAL,
    MULTIPLY,
    SCREEN,
    OVERLAY,
    DARKEN,
    LIGHTEN,
    COLOR_DODGE,
    COLOR_BURN,
    HARD_LIGHT,
    SOFT_LIGHT,
    DIFFERENCE,
    EXCLUSION,
    HUE,
    SATURATION,
    COLOR,
    LUMINOSITY,
    ADD,
    HARD_MIX;

    /* renamed from: com.airbnb.lottie.model.content.LBlendMode$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f9672a;

        static {
            int[] iArr = new int[LBlendMode.values().length];
            f9672a = iArr;
            try {
                iArr[LBlendMode.NORMAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9672a[LBlendMode.SCREEN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9672a[LBlendMode.OVERLAY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f9672a[LBlendMode.DARKEN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f9672a[LBlendMode.LIGHTEN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f9672a[LBlendMode.ADD.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f9672a[LBlendMode.MULTIPLY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f9672a[LBlendMode.COLOR_DODGE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f9672a[LBlendMode.COLOR_BURN.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f9672a[LBlendMode.HARD_LIGHT.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f9672a[LBlendMode.SOFT_LIGHT.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f9672a[LBlendMode.DIFFERENCE.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f9672a[LBlendMode.EXCLUSION.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f9672a[LBlendMode.HUE.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f9672a[LBlendMode.SATURATION.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f9672a[LBlendMode.COLOR.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f9672a[LBlendMode.LUMINOSITY.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f9672a[LBlendMode.HARD_MIX.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
        }
    }

    public BlendModeCompat d() {
        int i2 = AnonymousClass1.f9672a[ordinal()];
        if (i2 == 2) {
            return BlendModeCompat.SCREEN;
        }
        if (i2 == 3) {
            return BlendModeCompat.OVERLAY;
        }
        if (i2 == 4) {
            return BlendModeCompat.DARKEN;
        }
        if (i2 == 5) {
            return BlendModeCompat.LIGHTEN;
        }
        if (i2 != 6) {
            return null;
        }
        return BlendModeCompat.PLUS;
    }
}
