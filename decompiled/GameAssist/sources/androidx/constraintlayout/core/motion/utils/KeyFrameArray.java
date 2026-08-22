package androidx.constraintlayout.core.motion.utils;

import androidx.constraintlayout.core.motion.CustomAttribute;
import androidx.constraintlayout.core.motion.CustomVariable;

/* loaded from: classes.dex */
public class KeyFrameArray {

    public static class CustomArray {

        /* renamed from: a, reason: collision with root package name */
        int[] f1801a;

        /* renamed from: b, reason: collision with root package name */
        CustomAttribute[] f1802b;

        /* renamed from: c, reason: collision with root package name */
        int f1803c;

        public int a(int i2) {
            return this.f1801a[i2];
        }

        public int b() {
            return this.f1803c;
        }

        public CustomAttribute c(int i2) {
            return this.f1802b[this.f1801a[i2]];
        }
    }

    public static class CustomVar {

        /* renamed from: a, reason: collision with root package name */
        int[] f1804a;

        /* renamed from: b, reason: collision with root package name */
        CustomVariable[] f1805b;

        /* renamed from: c, reason: collision with root package name */
        int f1806c;

        public int a(int i2) {
            return this.f1804a[i2];
        }

        public int b() {
            return this.f1806c;
        }

        public CustomVariable c(int i2) {
            return this.f1805b[this.f1804a[i2]];
        }
    }

    static class FloatArray {
    }
}
