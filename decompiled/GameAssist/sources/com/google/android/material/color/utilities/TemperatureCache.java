package com.google.android.material.color.utilities;

import androidx.annotation.RestrictTo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@RestrictTo
/* loaded from: classes.dex */
public final class TemperatureCache {

    /* renamed from: a, reason: collision with root package name */
    private final Hct f14376a;

    /* renamed from: b, reason: collision with root package name */
    private List f14377b;

    /* renamed from: c, reason: collision with root package name */
    private List f14378c;

    /* renamed from: d, reason: collision with root package name */
    private Map f14379d;

    public TemperatureCache(Hct hct) {
        this.f14376a = hct;
    }

    private Hct c() {
        return (Hct) e().get(0);
    }

    private List d() {
        List list = this.f14378c;
        if (list != null) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        for (double d2 = 0.0d; d2 <= 360.0d; d2 += 1.0d) {
            arrayList.add(Hct.a(d2, this.f14376a.c(), this.f14376a.e()));
        }
        List unmodifiableList = Collections.unmodifiableList(arrayList);
        this.f14378c = unmodifiableList;
        return unmodifiableList;
    }

    private List e() {
        List list = this.f14377b;
        if (list != null) {
            return list;
        }
        ArrayList arrayList = new ArrayList(d());
        arrayList.add(this.f14376a);
        Collections.sort(arrayList, Comparator.comparing(new Function() { // from class: com.google.android.material.color.utilities.c1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Double i2;
                i2 = TemperatureCache.this.i((Hct) obj);
                return i2;
            }
        }, new Comparator() { // from class: com.google.android.material.color.utilities.d1
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return ((Double) obj).compareTo((Double) obj2);
            }
        }));
        this.f14377b = arrayList;
        return arrayList;
    }

    private Map g() {
        Map map = this.f14379d;
        if (map != null) {
            return map;
        }
        ArrayList<Hct> arrayList = new ArrayList(d());
        arrayList.add(this.f14376a);
        HashMap hashMap = new HashMap();
        for (Hct hct : arrayList) {
            hashMap.put(hct, Double.valueOf(j(hct)));
        }
        this.f14379d = hashMap;
        return hashMap;
    }

    private Hct h() {
        return (Hct) e().get(e().size() - 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Double i(Hct hct) {
        return (Double) g().get(hct);
    }

    public static double j(Hct hct) {
        double[] h2 = ColorUtils.h(hct.h());
        return ((Math.pow(Math.hypot(h2[1], h2[2]), 1.07d) * 0.02d) * Math.cos(Math.toRadians(MathUtils.e(MathUtils.e(Math.toDegrees(Math.atan2(h2[2], h2[1]))) - 50.0d)))) - 0.5d;
    }

    public List b(int i2, int i3) {
        int round = (int) Math.round(this.f14376a.d());
        Hct hct = (Hct) d().get(round);
        double f2 = f(hct);
        ArrayList arrayList = new ArrayList();
        arrayList.add(hct);
        double d2 = 0.0d;
        double d3 = 0.0d;
        int i4 = 0;
        while (i4 < 360) {
            double f3 = f((Hct) d().get(MathUtils.f(round + i4)));
            d3 += Math.abs(f3 - f2);
            i4++;
            f2 = f3;
        }
        double d4 = d3 / i3;
        double f4 = f(hct);
        int i5 = 1;
        while (true) {
            if (arrayList.size() >= i3) {
                break;
            }
            Hct hct2 = (Hct) d().get(MathUtils.f(round + i5));
            double f5 = f(hct2);
            d2 += Math.abs(f5 - f4);
            boolean z = d2 >= ((double) arrayList.size()) * d4;
            int i6 = 1;
            while (z && arrayList.size() < i3) {
                arrayList.add(hct2);
                z = d2 >= ((double) (arrayList.size() + i6)) * d4;
                i6++;
            }
            i5++;
            if (i5 > 360) {
                while (arrayList.size() < i3) {
                    arrayList.add(hct2);
                }
            } else {
                f4 = f5;
            }
        }
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(this.f14376a);
        int floor = (int) Math.floor((i2 - 1.0d) / 2.0d);
        for (int i7 = 1; i7 < floor + 1; i7++) {
            int i8 = 0 - i7;
            while (i8 < 0) {
                i8 += arrayList.size();
            }
            if (i8 >= arrayList.size()) {
                i8 %= arrayList.size();
            }
            arrayList2.add(0, (Hct) arrayList.get(i8));
        }
        int i9 = i2 - floor;
        for (int i10 = 1; i10 < i9; i10++) {
            int i11 = i10;
            while (i11 < 0) {
                i11 += arrayList.size();
            }
            if (i11 >= arrayList.size()) {
                i11 %= arrayList.size();
            }
            arrayList2.add((Hct) arrayList.get(i11));
        }
        return arrayList2;
    }

    public double f(Hct hct) {
        double doubleValue = ((Double) g().get(h())).doubleValue() - ((Double) g().get(c())).doubleValue();
        double doubleValue2 = ((Double) g().get(hct)).doubleValue() - ((Double) g().get(c())).doubleValue();
        if (doubleValue == 0.0d) {
            return 0.5d;
        }
        return doubleValue2 / doubleValue;
    }
}
