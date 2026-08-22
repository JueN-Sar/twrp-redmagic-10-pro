package com.airbnb.lottie;

import androidx.collection.ArraySet;
import androidx.core.util.Pair;
import com.airbnb.lottie.utils.MeanCalculator;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class PerformanceTracker {

    /* renamed from: a, reason: collision with root package name */
    private boolean f9332a = false;

    /* renamed from: b, reason: collision with root package name */
    private final Set f9333b = new ArraySet();

    /* renamed from: c, reason: collision with root package name */
    private final Map f9334c = new HashMap();

    /* renamed from: d, reason: collision with root package name */
    private final Comparator f9335d = new Comparator<Pair<String, Float>>() { // from class: com.airbnb.lottie.PerformanceTracker.1
        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(Pair pair, Pair pair2) {
            float floatValue = ((Float) pair.f3271b).floatValue();
            float floatValue2 = ((Float) pair2.f3271b).floatValue();
            if (floatValue2 > floatValue) {
                return 1;
            }
            return floatValue > floatValue2 ? -1 : 0;
        }
    };

    public interface FrameListener {
        void a(float f2);
    }

    public void a(String str, float f2) {
        if (this.f9332a) {
            MeanCalculator meanCalculator = (MeanCalculator) this.f9334c.get(str);
            if (meanCalculator == null) {
                meanCalculator = new MeanCalculator();
                this.f9334c.put(str, meanCalculator);
            }
            meanCalculator.a(f2);
            if (str.equals("__container")) {
                Iterator it = this.f9333b.iterator();
                while (it.hasNext()) {
                    ((FrameListener) it.next()).a(f2);
                }
            }
        }
    }

    void b(boolean z) {
        this.f9332a = z;
    }
}
