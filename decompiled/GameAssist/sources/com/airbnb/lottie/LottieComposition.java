package com.airbnb.lottie;

import android.graphics.Rect;
import androidx.collection.LongSparseArray;
import androidx.collection.SparseArrayCompat;
import com.airbnb.lottie.model.Marker;
import com.airbnb.lottie.model.layer.Layer;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.utils.Utils;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class LottieComposition {

    /* renamed from: c, reason: collision with root package name */
    private Map f9263c;

    /* renamed from: d, reason: collision with root package name */
    private Map f9264d;

    /* renamed from: e, reason: collision with root package name */
    private float f9265e;

    /* renamed from: f, reason: collision with root package name */
    private Map f9266f;

    /* renamed from: g, reason: collision with root package name */
    private List f9267g;

    /* renamed from: h, reason: collision with root package name */
    private SparseArrayCompat f9268h;

    /* renamed from: i, reason: collision with root package name */
    private LongSparseArray f9269i;

    /* renamed from: j, reason: collision with root package name */
    private List f9270j;

    /* renamed from: k, reason: collision with root package name */
    private Rect f9271k;

    /* renamed from: l, reason: collision with root package name */
    private float f9272l;

    /* renamed from: m, reason: collision with root package name */
    private float f9273m;

    /* renamed from: n, reason: collision with root package name */
    private float f9274n;

    /* renamed from: o, reason: collision with root package name */
    private boolean f9275o;

    /* renamed from: a, reason: collision with root package name */
    private final PerformanceTracker f9261a = new PerformanceTracker();

    /* renamed from: b, reason: collision with root package name */
    private final HashSet f9262b = new HashSet();

    /* renamed from: p, reason: collision with root package name */
    private int f9276p = 0;

    @Deprecated
    public static class Factory {

        private static final class ListenerAdapter implements LottieListener<LottieComposition>, Cancellable {

            /* renamed from: a, reason: collision with root package name */
            private final OnCompositionLoadedListener f9277a;

            /* renamed from: b, reason: collision with root package name */
            private boolean f9278b;

            @Override // com.airbnb.lottie.LottieListener
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onResult(LottieComposition lottieComposition) {
                if (this.f9278b) {
                    return;
                }
                this.f9277a.a(lottieComposition);
            }
        }
    }

    public void a(String str) {
        Logger.c(str);
        this.f9262b.add(str);
    }

    public Rect b() {
        return this.f9271k;
    }

    public SparseArrayCompat c() {
        return this.f9268h;
    }

    public float d() {
        return (long) ((e() / this.f9274n) * 1000.0f);
    }

    public float e() {
        return this.f9273m - this.f9272l;
    }

    public float f() {
        return this.f9273m;
    }

    public Map g() {
        return this.f9266f;
    }

    public float h(float f2) {
        return MiscUtils.i(this.f9272l, this.f9273m, f2);
    }

    public float i() {
        return this.f9274n;
    }

    public Map j() {
        float e2 = Utils.e();
        if (e2 != this.f9265e) {
            for (Map.Entry entry : this.f9264d.entrySet()) {
                this.f9264d.put((String) entry.getKey(), ((LottieImageAsset) entry.getValue()).a(this.f9265e / e2));
            }
        }
        this.f9265e = e2;
        return this.f9264d;
    }

    public List k() {
        return this.f9270j;
    }

    public Marker l(String str) {
        int size = this.f9267g.size();
        for (int i2 = 0; i2 < size; i2++) {
            Marker marker = (Marker) this.f9267g.get(i2);
            if (marker.a(str)) {
                return marker;
            }
        }
        return null;
    }

    public int m() {
        return this.f9276p;
    }

    public PerformanceTracker n() {
        return this.f9261a;
    }

    public List o(String str) {
        return (List) this.f9263c.get(str);
    }

    public float p() {
        return this.f9272l;
    }

    public boolean q() {
        return this.f9275o;
    }

    public void r(int i2) {
        this.f9276p += i2;
    }

    public void s(Rect rect, float f2, float f3, float f4, List list, LongSparseArray longSparseArray, Map map, Map map2, float f5, SparseArrayCompat sparseArrayCompat, Map map3, List list2) {
        this.f9271k = rect;
        this.f9272l = f2;
        this.f9273m = f3;
        this.f9274n = f4;
        this.f9270j = list;
        this.f9269i = longSparseArray;
        this.f9263c = map;
        this.f9264d = map2;
        this.f9265e = f5;
        this.f9268h = sparseArrayCompat;
        this.f9266f = map3;
        this.f9267g = list2;
    }

    public Layer t(long j2) {
        return (Layer) this.f9269i.f(j2);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("LottieComposition:\n");
        Iterator it = this.f9270j.iterator();
        while (it.hasNext()) {
            sb.append(((Layer) it.next()).z("\t"));
        }
        return sb.toString();
    }

    public void u(boolean z) {
        this.f9275o = z;
    }

    public void v(boolean z) {
        this.f9261a.b(z);
    }
}
