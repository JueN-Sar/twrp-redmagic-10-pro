package com.google.android.material.carousel;

import com.google.android.material.animation.AnimationUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
final class KeylineState {

    /* renamed from: a, reason: collision with root package name */
    private final float f14156a;

    /* renamed from: b, reason: collision with root package name */
    private final List f14157b;

    /* renamed from: c, reason: collision with root package name */
    private final int f14158c;

    /* renamed from: d, reason: collision with root package name */
    private final int f14159d;

    static final class Builder {

        /* renamed from: a, reason: collision with root package name */
        private final float f14160a;

        /* renamed from: b, reason: collision with root package name */
        private final float f14161b;

        /* renamed from: d, reason: collision with root package name */
        private Keyline f14163d;

        /* renamed from: e, reason: collision with root package name */
        private Keyline f14164e;

        /* renamed from: c, reason: collision with root package name */
        private final List f14162c = new ArrayList();

        /* renamed from: f, reason: collision with root package name */
        private int f14165f = -1;

        /* renamed from: g, reason: collision with root package name */
        private int f14166g = -1;

        /* renamed from: h, reason: collision with root package name */
        private float f14167h = 0.0f;

        /* renamed from: i, reason: collision with root package name */
        private int f14168i = -1;

        Builder(float f2, float f3) {
            this.f14160a = f2;
            this.f14161b = f3;
        }

        private static float j(float f2, float f3, int i2, int i3) {
            return (f2 - (i2 * f3)) + (i3 * f3);
        }

        Builder a(float f2, float f3, float f4) {
            return d(f2, f3, f4, false, true);
        }

        Builder b(float f2, float f3, float f4) {
            return c(f2, f3, f4, false);
        }

        Builder c(float f2, float f3, float f4, boolean z) {
            return d(f2, f3, f4, z, false);
        }

        Builder d(float f2, float f3, float f4, boolean z, boolean z2) {
            float f5;
            float f6 = f4 / 2.0f;
            float f7 = f2 - f6;
            float f8 = f6 + f2;
            float f9 = this.f14161b;
            if (f8 > f9) {
                f5 = Math.abs(f8 - Math.max(f8 - f4, f9));
            } else {
                f5 = 0.0f;
                if (f7 < 0.0f) {
                    f5 = Math.abs(f7 - Math.min(f7 + f4, 0.0f));
                }
            }
            return e(f2, f3, f4, z, z2, f5);
        }

        Builder e(float f2, float f3, float f4, boolean z, boolean z2, float f5) {
            return f(f2, f3, f4, z, z2, f5, 0.0f, 0.0f);
        }

        Builder f(float f2, float f3, float f4, boolean z, boolean z2, float f5, float f6, float f7) {
            if (f4 <= 0.0f) {
                return this;
            }
            if (z2) {
                if (z) {
                    throw new IllegalArgumentException("Anchor keylines cannot be focal.");
                }
                int i2 = this.f14168i;
                if (i2 != -1 && i2 != 0) {
                    throw new IllegalArgumentException("Anchor keylines must be either the first or last keyline.");
                }
                this.f14168i = this.f14162c.size();
            }
            Keyline keyline = new Keyline(Float.MIN_VALUE, f2, f3, f4, z2, f5, f6, f7);
            if (z) {
                if (this.f14163d == null) {
                    this.f14163d = keyline;
                    this.f14165f = this.f14162c.size();
                }
                if (this.f14166g != -1 && this.f14162c.size() - this.f14166g > 1) {
                    throw new IllegalArgumentException("Keylines marked as focal must be placed next to each other. There cannot be non-focal keylines between focal keylines.");
                }
                if (f4 != this.f14163d.f14172d) {
                    throw new IllegalArgumentException("Keylines that are marked as focal must all have the same masked item size.");
                }
                this.f14164e = keyline;
                this.f14166g = this.f14162c.size();
            } else {
                if (this.f14163d == null && keyline.f14172d < this.f14167h) {
                    throw new IllegalArgumentException("Keylines before the first focal keyline must be ordered by incrementing masked item size.");
                }
                if (this.f14164e != null && keyline.f14172d > this.f14167h) {
                    throw new IllegalArgumentException("Keylines after the last focal keyline must be ordered by decreasing masked item size.");
                }
            }
            this.f14167h = keyline.f14172d;
            this.f14162c.add(keyline);
            return this;
        }

        Builder g(float f2, float f3, float f4, int i2) {
            return h(f2, f3, f4, i2, false);
        }

        Builder h(float f2, float f3, float f4, int i2, boolean z) {
            if (i2 > 0 && f4 > 0.0f) {
                for (int i3 = 0; i3 < i2; i3++) {
                    c((i3 * f4) + f2, f3, f4, z);
                }
            }
            return this;
        }

        KeylineState i() {
            if (this.f14163d == null) {
                throw new IllegalStateException("There must be a keyline marked as focal.");
            }
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < this.f14162c.size(); i2++) {
                Keyline keyline = (Keyline) this.f14162c.get(i2);
                arrayList.add(new Keyline(j(this.f14163d.f14170b, this.f14160a, this.f14165f, i2), keyline.f14170b, keyline.f14171c, keyline.f14172d, keyline.f14173e, keyline.f14174f, keyline.f14175g, keyline.f14176h));
            }
            return new KeylineState(this.f14160a, arrayList, this.f14165f, this.f14166g);
        }
    }

    static final class Keyline {

        /* renamed from: a, reason: collision with root package name */
        final float f14169a;

        /* renamed from: b, reason: collision with root package name */
        final float f14170b;

        /* renamed from: c, reason: collision with root package name */
        final float f14171c;

        /* renamed from: d, reason: collision with root package name */
        final float f14172d;

        /* renamed from: e, reason: collision with root package name */
        final boolean f14173e;

        /* renamed from: f, reason: collision with root package name */
        final float f14174f;

        /* renamed from: g, reason: collision with root package name */
        final float f14175g;

        /* renamed from: h, reason: collision with root package name */
        final float f14176h;

        Keyline(float f2, float f3, float f4, float f5) {
            this(f2, f3, f4, f5, false, 0.0f, 0.0f, 0.0f);
        }

        static Keyline a(Keyline keyline, Keyline keyline2, float f2) {
            return new Keyline(AnimationUtils.a(keyline.f14169a, keyline2.f14169a, f2), AnimationUtils.a(keyline.f14170b, keyline2.f14170b, f2), AnimationUtils.a(keyline.f14171c, keyline2.f14171c, f2), AnimationUtils.a(keyline.f14172d, keyline2.f14172d, f2));
        }

        Keyline(float f2, float f3, float f4, float f5, boolean z, float f6, float f7, float f8) {
            this.f14169a = f2;
            this.f14170b = f3;
            this.f14171c = f4;
            this.f14172d = f5;
            this.f14173e = z;
            this.f14174f = f6;
            this.f14175g = f7;
            this.f14176h = f8;
        }
    }

    static KeylineState m(KeylineState keylineState, KeylineState keylineState2, float f2) {
        if (keylineState.f() != keylineState2.f()) {
            throw new IllegalArgumentException("Keylines being linearly interpolated must have the same item size.");
        }
        List g2 = keylineState.g();
        List g3 = keylineState2.g();
        if (g2.size() != g3.size()) {
            throw new IllegalArgumentException("Keylines being linearly interpolated must have the same number of keylines.");
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < keylineState.g().size(); i2++) {
            arrayList.add(Keyline.a((Keyline) g2.get(i2), (Keyline) g3.get(i2), f2));
        }
        return new KeylineState(keylineState.f(), arrayList, AnimationUtils.c(keylineState.b(), keylineState2.b(), f2), AnimationUtils.c(keylineState.i(), keylineState2.i(), f2));
    }

    static KeylineState n(KeylineState keylineState, float f2) {
        Builder builder = new Builder(keylineState.f(), f2);
        float f3 = (f2 - keylineState.j().f14170b) - (keylineState.j().f14172d / 2.0f);
        int size = keylineState.g().size() - 1;
        while (size >= 0) {
            Keyline keyline = (Keyline) keylineState.g().get(size);
            builder.d(f3 + (keyline.f14172d / 2.0f), keyline.f14171c, keyline.f14172d, size >= keylineState.b() && size <= keylineState.i(), keyline.f14173e);
            f3 += keyline.f14172d;
            size--;
        }
        return builder.i();
    }

    Keyline a() {
        return (Keyline) this.f14157b.get(this.f14158c);
    }

    int b() {
        return this.f14158c;
    }

    Keyline c() {
        return (Keyline) this.f14157b.get(0);
    }

    Keyline d() {
        for (int i2 = 0; i2 < this.f14157b.size(); i2++) {
            Keyline keyline = (Keyline) this.f14157b.get(i2);
            if (!keyline.f14173e) {
                return keyline;
            }
        }
        return null;
    }

    List e() {
        return this.f14157b.subList(this.f14158c, this.f14159d + 1);
    }

    float f() {
        return this.f14156a;
    }

    List g() {
        return this.f14157b;
    }

    Keyline h() {
        return (Keyline) this.f14157b.get(this.f14159d);
    }

    int i() {
        return this.f14159d;
    }

    Keyline j() {
        return (Keyline) this.f14157b.get(r1.size() - 1);
    }

    Keyline k() {
        for (int size = this.f14157b.size() - 1; size >= 0; size--) {
            Keyline keyline = (Keyline) this.f14157b.get(size);
            if (!keyline.f14173e) {
                return keyline;
            }
        }
        return null;
    }

    int l() {
        Iterator it = this.f14157b.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            if (((Keyline) it.next()).f14173e) {
                i2++;
            }
        }
        return this.f14157b.size() - i2;
    }

    private KeylineState(float f2, List list, int i2, int i3) {
        this.f14156a = f2;
        this.f14157b = Collections.unmodifiableList(list);
        this.f14158c = i2;
        this.f14159d = i3;
    }
}
