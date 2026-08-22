package androidx.constraintlayout.core.state;

import androidx.constraintlayout.core.motion.utils.TypedValues;

/* loaded from: classes.dex */
public class Transition implements TypedValues {

    static class KeyPosition {
    }

    static class OnSwipe {

        /* renamed from: a, reason: collision with root package name */
        public static final String[] f1912a = {"top", "left", "right", "bottom", "middle", "start", "end"};

        /* renamed from: b, reason: collision with root package name */
        private static final float[][] f1913b = {new float[]{0.5f, 0.0f}, new float[]{0.0f, 0.5f}, new float[]{1.0f, 0.5f}, new float[]{0.5f, 1.0f}, new float[]{0.5f, 0.5f}, new float[]{0.0f, 0.5f}, new float[]{1.0f, 0.5f}};

        /* renamed from: c, reason: collision with root package name */
        public static final String[] f1914c = {"up", "down", "left", "right", "start", "end", "clockwise", "anticlockwise"};

        /* renamed from: d, reason: collision with root package name */
        public static final String[] f1915d = {"velocity", "spring"};

        /* renamed from: e, reason: collision with root package name */
        public static final String[] f1916e = {"autocomplete", "toStart", "toEnd", "stop", "decelerate", "decelerateComplete", "neverCompleteStart", "neverCompleteEnd"};

        /* renamed from: f, reason: collision with root package name */
        public static final String[] f1917f = {"overshoot", "bounceStart", "bounceEnd", "bounceBoth"};

        /* renamed from: g, reason: collision with root package name */
        private static final float[][] f1918g = {new float[]{0.0f, -1.0f}, new float[]{0.0f, 1.0f}, new float[]{-1.0f, 0.0f}, new float[]{1.0f, 0.0f}, new float[]{-1.0f, 0.0f}, new float[]{1.0f, 0.0f}};
    }

    public static class WidgetState {
    }
}
