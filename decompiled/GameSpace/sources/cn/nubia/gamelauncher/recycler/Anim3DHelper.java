package cn.nubia.gamelauncher.recycler;

import android.view.animation.PathInterpolator;

/* loaded from: classes.dex */
public class Anim3DHelper {
    public static float CAMERA_LOCATION_TO_PIXEL = 72.0f;
    public static float CAMERA_LOCATION_Z = -8.0f;
    public static final int CARD_ROUND_RECT_RADIUS = 34;
    public static final int MAX_DEGREES = 90;
    public static final int MAX_SCROLL_COUNT = 18;
    public static final float OFFSET_SCALE_RATE = 0.89f;
    public static final int RECENT_CARD_ROUND_RECT_RADIUS = 20;
    public static final float RECYCLE_FLING_SPEED_RATIO = 0.7f;
    public static final int ROTATE_DEGREES_OFFSET_ONE = 29;
    public static final float SCALE_MUTATION_RATE = 2.5f;
    public static final int START_ANIM_DURATION = 1350;
    private static final int TOTAL_ROTATE_DEGREES = 360;
    public static float mDensity = 3.0f;
    public static PathInterpolator DEFAULT_ANIM_PATH_INTERPOLATOR = new PathInterpolator(0.3f, 0.0f, 0.3f, 1.0f);
    public static PathInterpolator PATH_INTERPOLATOR_CARD_ENTER = new PathInterpolator(0.3f, 0.1f, 0.3f, 1.0f);
    public static PathInterpolator PATH_INTERPOLATOR_CARD_REBOUND = new PathInterpolator(0.3f, 0.13f, 0.58f, 1.0f);

    public static double getAngleWithScreenByOffset(float f) {
        float degreesByOffset = getDegreesByOffset(f);
        if (degreesByOffset > 90.0f) {
            degreesByOffset = 90.0f - (degreesByOffset % 90.0f);
        }
        return (degreesByOffset * 3.141592653589793d) / 180.0d;
    }

    public static float getCameraLocationZByPixel() {
        return Math.abs(CAMERA_LOCATION_Z * CAMERA_LOCATION_TO_PIXEL * mDensity);
    }

    public static float getDegreesByOffset(float f) {
        return ((f * 29.0f) + 360.0f) % 360.0f;
    }

    public static float getDensity() {
        return mDensity;
    }

    public static double getProjectionWidthByOffset(int i, float f) {
        float scaleRateByOffset = getScaleRateByOffset(f);
        double angleWithScreenByOffset = getAngleWithScreenByOffset(f);
        return ((getCameraLocationZByPixel() * r7) * Math.cos(angleWithScreenByOffset)) / (getCameraLocationZByPixel() + (((int) (i * scaleRateByOffset)) * Math.sin(angleWithScreenByOffset)));
    }

    public static float getScaleRateByOffset(float f) {
        return (float) Math.pow(0.8899999856948853d, Math.abs(transformOffsetWithMutation(f, 2.5f)));
    }

    public static float getValueOfInterpolator(PathInterpolator pathInterpolator, long j, long j2) {
        if (0 == j2) {
            return 0.0f;
        }
        if (j > j2) {
            return 1.0f;
        }
        if (pathInterpolator == null) {
            pathInterpolator = DEFAULT_ANIM_PATH_INTERPOLATOR;
        }
        return pathInterpolator.getInterpolation(j / j2);
    }

    public static void setDensity(float f) {
        mDensity = f;
    }

    public static float transformOffsetWithMutation(float f, float f2) {
        return Math.abs(f) > 1.0f ? Math.signum(f) * (Math.abs(f) + ((Math.abs(f) - 1.0f) * f2)) : f;
    }
}
