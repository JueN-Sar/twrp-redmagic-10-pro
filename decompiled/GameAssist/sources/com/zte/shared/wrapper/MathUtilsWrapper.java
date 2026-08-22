package com.zte.shared.wrapper;

import com.google.android.gms.common.api.Api;

/* loaded from: classes2.dex */
public class MathUtilsWrapper {
    private static final float DEG_TO_RAD = 0.017453292f;
    private static final float RAD_TO_DEG = 57.295784f;

    public static float abs(float f2) {
        return f2 > 0.0f ? f2 : -f2;
    }

    public static float acos(float f2) {
        return (float) Math.acos(f2);
    }

    public static int addOrThrow(int i2, int i3) {
        if (i3 == 0) {
            return i2;
        }
        if (i3 > 0 && i2 <= Api.BaseClientBuilder.API_PRIORITY_OTHER - i3) {
            return i2 + i3;
        }
        if (i3 < 0 && i2 >= Integer.MIN_VALUE - i3) {
            return i2 + i3;
        }
        throw new IllegalArgumentException("Addition overflow: " + i2 + " + " + i3);
    }

    public static float asin(float f2) {
        return (float) Math.asin(f2);
    }

    public static float atan(float f2) {
        return (float) Math.atan(f2);
    }

    public static float atan2(float f2, float f3) {
        return (float) Math.atan2(f2, f3);
    }

    public static float constrain(float f2, float f3, float f4) {
        return f2 < f3 ? f3 : f2 > f4 ? f4 : f2;
    }

    public static float constrainedMap(float f2, float f3, float f4, float f5, float f6) {
        return lerp(f2, f3, lerpInvSat(f4, f5, f6));
    }

    public static float cross(float f2, float f3, float f4, float f5) {
        return (f2 * f5) - (f3 * f4);
    }

    public static float degrees(float f2) {
        return f2 * RAD_TO_DEG;
    }

    public static float dist(float f2, float f3, float f4, float f5) {
        return (float) Math.hypot(f4 - f2, f5 - f3);
    }

    public static float dot(float f2, float f3, float f4, float f5) {
        return (f2 * f4) + (f3 * f5);
    }

    public static float exp(float f2) {
        return (float) Math.exp(f2);
    }

    public static float lerp(float f2, float f3, float f4) {
        return f2 + ((f3 - f2) * f4);
    }

    public static float lerpDeg(float f2, float f3, float f4) {
        return (((((f3 - f2) + 180.0f) % 360.0f) - 180.0f) * f4) + f2;
    }

    public static float lerpInv(float f2, float f3, float f4) {
        if (f2 != f3) {
            return (f4 - f2) / (f3 - f2);
        }
        return 0.0f;
    }

    public static float lerpInvSat(float f2, float f3, float f4) {
        return saturate(lerpInv(f2, f3, f4));
    }

    public static float log(float f2) {
        return (float) Math.log(f2);
    }

    public static float mag(float f2, float f3) {
        return (float) Math.hypot(f2, f3);
    }

    public static float map(float f2, float f3, float f4, float f5, float f6) {
        return f4 + ((f5 - f4) * ((f6 - f2) / (f3 - f2)));
    }

    public static float max(float f2, float f3) {
        return f2 > f3 ? f2 : f3;
    }

    public static float min(float f2, float f3) {
        return f2 < f3 ? f2 : f3;
    }

    public static float norm(float f2, float f3, float f4) {
        return (f4 - f2) / (f3 - f2);
    }

    public static float pow(float f2, float f3) {
        return (float) Math.pow(f2, f3);
    }

    public static float radians(float f2) {
        return f2 * DEG_TO_RAD;
    }

    public static float saturate(float f2) {
        return constrain(f2, 0.0f, 1.0f);
    }

    public static float smoothStep(float f2, float f3, float f4) {
        return constrain((f4 - f2) / (f3 - f2), 0.0f, 1.0f);
    }

    public static float sq(float f2) {
        return f2 * f2;
    }

    public static float sqrt(float f2) {
        return (float) Math.sqrt(f2);
    }

    public static float tan(float f2) {
        return (float) Math.tan(f2);
    }

    public static int constrain(int i2, int i3, int i4) {
        return i2 < i3 ? i3 : i2 > i4 ? i4 : i2;
    }

    public static float dist(float f2, float f3, float f4, float f5, float f6, float f7) {
        float f8 = f5 - f2;
        float f9 = f6 - f3;
        float f10 = f7 - f4;
        return (float) Math.sqrt((f8 * f8) + (f9 * f9) + (f10 * f10));
    }

    public static float lerp(int i2, int i3, float f2) {
        return lerp(i2, i3, f2);
    }

    public static float mag(float f2, float f3, float f4) {
        return (float) Math.sqrt((f2 * f2) + (f3 * f3) + (f4 * f4));
    }

    public static float max(float f2, float f3, float f4) {
        if (f2 > f3) {
            if (f2 > f4) {
                return f2;
            }
        } else if (f3 > f4) {
            return f3;
        }
        return f4;
    }

    public static float min(float f2, float f3, float f4) {
        if (f2 < f3) {
            if (f2 < f4) {
                return f2;
            }
        } else if (f3 < f4) {
            return f3;
        }
        return f4;
    }

    public static long constrain(long j2, long j3, long j4) {
        return j2 < j3 ? j3 : j2 > j4 ? j4 : j2;
    }

    public static float max(int i2, int i3) {
        return i2 > i3 ? i2 : i3;
    }

    public static float min(int i2, int i3) {
        return i2 < i3 ? i2 : i3;
    }

    public static float max(int i2, int i3, int i4) {
        if (i2 > i3) {
            if (i2 <= i4) {
                i2 = i4;
            }
            return i2;
        }
        if (i3 <= i4) {
            i3 = i4;
        }
        return i3;
    }

    public static float min(int i2, int i3, int i4) {
        if (i2 < i3) {
            if (i2 >= i4) {
                i2 = i4;
            }
            return i2;
        }
        if (i3 >= i4) {
            i3 = i4;
        }
        return i3;
    }
}
