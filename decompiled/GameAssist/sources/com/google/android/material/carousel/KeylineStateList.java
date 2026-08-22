package com.google.android.material.carousel;

import androidx.core.math.MathUtils;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.carousel.KeylineState;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
class KeylineStateList {

    /* renamed from: a, reason: collision with root package name */
    private final KeylineState f14177a;

    /* renamed from: b, reason: collision with root package name */
    private final List f14178b;

    /* renamed from: c, reason: collision with root package name */
    private final List f14179c;

    /* renamed from: d, reason: collision with root package name */
    private final float[] f14180d;

    /* renamed from: e, reason: collision with root package name */
    private final float[] f14181e;

    /* renamed from: f, reason: collision with root package name */
    private final float f14182f;

    /* renamed from: g, reason: collision with root package name */
    private final float f14183g;

    private KeylineStateList(KeylineState keylineState, List list, List list2) {
        this.f14177a = keylineState;
        this.f14178b = Collections.unmodifiableList(list);
        this.f14179c = Collections.unmodifiableList(list2);
        float f2 = ((KeylineState) list.get(list.size() - 1)).c().f14169a - keylineState.c().f14169a;
        this.f14182f = f2;
        float f3 = keylineState.j().f14169a - ((KeylineState) list2.get(list2.size() - 1)).j().f14169a;
        this.f14183g = f3;
        this.f14180d = m(f2, list, true);
        this.f14181e = m(f3, list2, false);
    }

    private KeylineState a(List list, float f2, float[] fArr) {
        float[] o2 = o(list, f2, fArr);
        return o2[0] >= 0.5f ? (KeylineState) list.get((int) o2[2]) : (KeylineState) list.get((int) o2[1]);
    }

    private static int b(KeylineState keylineState, float f2) {
        for (int i2 = keylineState.i(); i2 < keylineState.g().size(); i2++) {
            if (f2 == ((KeylineState.Keyline) keylineState.g().get(i2)).f14171c) {
                return i2;
            }
        }
        return keylineState.g().size() - 1;
    }

    private static int c(KeylineState keylineState) {
        for (int i2 = 0; i2 < keylineState.g().size(); i2++) {
            if (!((KeylineState.Keyline) keylineState.g().get(i2)).f14173e) {
                return i2;
            }
        }
        return -1;
    }

    private static int d(KeylineState keylineState, float f2) {
        for (int b2 = keylineState.b() - 1; b2 >= 0; b2--) {
            if (f2 == ((KeylineState.Keyline) keylineState.g().get(b2)).f14171c) {
                return b2;
            }
        }
        return 0;
    }

    private static int e(KeylineState keylineState) {
        for (int size = keylineState.g().size() - 1; size >= 0; size--) {
            if (!((KeylineState.Keyline) keylineState.g().get(size)).f14173e) {
                return size;
            }
        }
        return -1;
    }

    static KeylineStateList f(Carousel carousel, KeylineState keylineState, float f2, float f3, float f4) {
        return new KeylineStateList(keylineState, p(carousel, keylineState, f2, f3), n(carousel, keylineState, f2, f4));
    }

    private static float[] m(float f2, List list, boolean z) {
        int size = list.size();
        float[] fArr = new float[size];
        int i2 = 1;
        while (i2 < size) {
            int i3 = i2 - 1;
            KeylineState keylineState = (KeylineState) list.get(i3);
            KeylineState keylineState2 = (KeylineState) list.get(i2);
            fArr[i2] = i2 == size + (-1) ? 1.0f : fArr[i3] + ((z ? keylineState2.c().f14169a - keylineState.c().f14169a : keylineState.j().f14169a - keylineState2.j().f14169a) / f2);
            i2++;
        }
        return fArr;
    }

    private static List n(Carousel carousel, KeylineState keylineState, float f2, float f3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(keylineState);
        int e2 = e(keylineState);
        float a2 = carousel.g() ? carousel.a() : carousel.b();
        if (r(carousel, keylineState) || e2 == -1) {
            if (f3 > 0.0f) {
                arrayList.add(u(keylineState, f3, a2, false, f2));
            }
            return arrayList;
        }
        int i2 = e2 - keylineState.i();
        float f4 = keylineState.c().f14170b - (keylineState.c().f14172d / 2.0f);
        if (i2 <= 0 && keylineState.h().f14174f > 0.0f) {
            arrayList.add(v(keylineState, f4 - keylineState.h().f14174f, a2));
            return arrayList;
        }
        float f5 = 0.0f;
        int i3 = 0;
        while (i3 < i2) {
            KeylineState keylineState2 = (KeylineState) arrayList.get(arrayList.size() - 1);
            int i4 = e2 - i3;
            float f6 = f5 + ((KeylineState.Keyline) keylineState.g().get(i4)).f14174f;
            int i5 = i4 + 1;
            int i6 = i3;
            KeylineState t = t(keylineState2, e2, i5 < keylineState.g().size() ? d(keylineState2, ((KeylineState.Keyline) keylineState.g().get(i5)).f14171c) + 1 : 0, f4 - f6, keylineState.b() + i3 + 1, keylineState.i() + i3 + 1, a2);
            if (i6 == i2 - 1 && f3 > 0.0f) {
                t = u(t, f3, a2, false, f2);
            }
            arrayList.add(t);
            i3 = i6 + 1;
            f5 = f6;
        }
        return arrayList;
    }

    private static float[] o(List list, float f2, float[] fArr) {
        int size = list.size();
        float f3 = fArr[0];
        int i2 = 1;
        while (i2 < size) {
            float f4 = fArr[i2];
            if (f2 <= f4) {
                return new float[]{AnimationUtils.b(0.0f, 1.0f, f3, f4, f2), i2 - 1, i2};
            }
            i2++;
            f3 = f4;
        }
        return new float[]{0.0f, 0.0f, 0.0f};
    }

    private static List p(Carousel carousel, KeylineState keylineState, float f2, float f3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(keylineState);
        int c2 = c(keylineState);
        float a2 = carousel.g() ? carousel.a() : carousel.b();
        int i2 = 1;
        if (q(keylineState) || c2 == -1) {
            if (f3 > 0.0f) {
                arrayList.add(u(keylineState, f3, a2, true, f2));
            }
            return arrayList;
        }
        int b2 = keylineState.b() - c2;
        float f4 = keylineState.c().f14170b - (keylineState.c().f14172d / 2.0f);
        if (b2 <= 0 && keylineState.a().f14174f > 0.0f) {
            arrayList.add(v(keylineState, f4 + keylineState.a().f14174f, a2));
            return arrayList;
        }
        int i3 = 0;
        float f5 = 0.0f;
        while (i3 < b2) {
            KeylineState keylineState2 = (KeylineState) arrayList.get(arrayList.size() - i2);
            int i4 = c2 + i3;
            int size = keylineState.g().size() - i2;
            float f6 = f5 + ((KeylineState.Keyline) keylineState.g().get(i4)).f14174f;
            int i5 = i4 - i2;
            int b3 = i5 >= 0 ? b(keylineState2, ((KeylineState.Keyline) keylineState.g().get(i5)).f14171c) - i2 : size;
            int i6 = i3;
            KeylineState t = t(keylineState2, c2, b3, f4 + f6, (keylineState.b() - i3) - 1, (keylineState.i() - i3) - 1, a2);
            if (i6 == b2 - 1 && f3 > 0.0f) {
                t = u(t, f3, a2, true, f2);
            }
            arrayList.add(t);
            i3 = i6 + 1;
            f5 = f6;
            i2 = 1;
        }
        return arrayList;
    }

    private static boolean q(KeylineState keylineState) {
        return keylineState.a().f14170b - (keylineState.a().f14172d / 2.0f) >= 0.0f && keylineState.a() == keylineState.d();
    }

    private static boolean r(Carousel carousel, KeylineState keylineState) {
        int b2 = carousel.b();
        if (carousel.g()) {
            b2 = carousel.a();
        }
        return keylineState.h().f14170b + (keylineState.h().f14172d / 2.0f) <= ((float) b2) && keylineState.h() == keylineState.k();
    }

    private static KeylineState s(List list, float f2, float[] fArr) {
        float[] o2 = o(list, f2, fArr);
        return KeylineState.m((KeylineState) list.get((int) o2[1]), (KeylineState) list.get((int) o2[2]), o2[0]);
    }

    private static KeylineState t(KeylineState keylineState, int i2, int i3, float f2, int i4, int i5, float f3) {
        ArrayList arrayList = new ArrayList(keylineState.g());
        arrayList.add(i3, (KeylineState.Keyline) arrayList.remove(i2));
        KeylineState.Builder builder = new KeylineState.Builder(keylineState.f(), f3);
        int i6 = 0;
        while (i6 < arrayList.size()) {
            KeylineState.Keyline keyline = (KeylineState.Keyline) arrayList.get(i6);
            float f4 = keyline.f14172d;
            builder.e(f2 + (f4 / 2.0f), keyline.f14171c, f4, i6 >= i4 && i6 <= i5, keyline.f14173e, keyline.f14174f);
            f2 += keyline.f14172d;
            i6++;
        }
        return builder.i();
    }

    private static KeylineState u(KeylineState keylineState, float f2, float f3, boolean z, float f4) {
        ArrayList arrayList = new ArrayList(keylineState.g());
        KeylineState.Builder builder = new KeylineState.Builder(keylineState.f(), f3);
        float l2 = f2 / keylineState.l();
        float f5 = z ? f2 : 0.0f;
        int i2 = 0;
        while (i2 < arrayList.size()) {
            KeylineState.Keyline keyline = (KeylineState.Keyline) arrayList.get(i2);
            if (keyline.f14173e) {
                builder.e(keyline.f14170b, keyline.f14171c, keyline.f14172d, false, true, keyline.f14174f);
            } else {
                boolean z2 = i2 >= keylineState.b() && i2 <= keylineState.i();
                float f6 = keyline.f14172d - l2;
                float b2 = CarouselStrategy.b(f6, keylineState.f(), f4);
                float f7 = (f6 / 2.0f) + f5;
                float f8 = f7 - keyline.f14170b;
                builder.f(f7, b2, f6, z2, false, keyline.f14174f, z ? f8 : 0.0f, z ? 0.0f : f8);
                f5 += f6;
            }
            i2++;
        }
        return builder.i();
    }

    private static KeylineState v(KeylineState keylineState, float f2, float f3) {
        return t(keylineState, 0, 0, f2, keylineState.b(), keylineState.i(), f3);
    }

    KeylineState g() {
        return this.f14177a;
    }

    KeylineState h() {
        return (KeylineState) this.f14179c.get(r1.size() - 1);
    }

    Map i(int i2, int i3, int i4, boolean z) {
        float f2 = this.f14177a.f();
        HashMap hashMap = new HashMap();
        int i5 = 0;
        int i6 = 0;
        while (true) {
            if (i5 >= i2) {
                break;
            }
            int i7 = z ? (i2 - i5) - 1 : i5;
            if (i7 * f2 * (z ? -1 : 1) > i4 - this.f14183g || i5 >= i2 - this.f14179c.size()) {
                Integer valueOf = Integer.valueOf(i7);
                List list = this.f14179c;
                hashMap.put(valueOf, (KeylineState) list.get(MathUtils.b(i6, 0, list.size() - 1)));
                i6++;
            }
            i5++;
        }
        int i8 = 0;
        for (int i9 = i2 - 1; i9 >= 0; i9--) {
            int i10 = z ? (i2 - i9) - 1 : i9;
            if (i10 * f2 * (z ? -1 : 1) < i3 + this.f14182f || i9 < this.f14178b.size()) {
                Integer valueOf2 = Integer.valueOf(i10);
                List list2 = this.f14178b;
                hashMap.put(valueOf2, (KeylineState) list2.get(MathUtils.b(i8, 0, list2.size() - 1)));
                i8++;
            }
        }
        return hashMap;
    }

    public KeylineState j(float f2, float f3, float f4) {
        return k(f2, f3, f4, false);
    }

    KeylineState k(float f2, float f3, float f4, boolean z) {
        float b2;
        List list;
        float[] fArr;
        float f5 = this.f14182f + f3;
        float f6 = f4 - this.f14183g;
        float f7 = l().a().f14175g;
        float f8 = h().h().f14176h;
        if (this.f14182f == f7) {
            f5 += f7;
        }
        if (this.f14183g == f8) {
            f6 -= f8;
        }
        if (f2 < f5) {
            b2 = AnimationUtils.b(1.0f, 0.0f, f3, f5, f2);
            list = this.f14178b;
            fArr = this.f14180d;
        } else {
            if (f2 <= f6) {
                return this.f14177a;
            }
            b2 = AnimationUtils.b(0.0f, 1.0f, f6, f4, f2);
            list = this.f14179c;
            fArr = this.f14181e;
        }
        return z ? a(list, b2, fArr) : s(list, b2, fArr);
    }

    KeylineState l() {
        return (KeylineState) this.f14178b.get(r1.size() - 1);
    }
}
