package androidx.constraintlayout.widget;

import androidx.constraintlayout.core.Metrics;

/* loaded from: classes.dex */
public class ConstraintLayoutStatistics {

    /* renamed from: b, reason: collision with root package name */
    private static int f2473b = 25;

    /* renamed from: c, reason: collision with root package name */
    private static final String f2474c = new String(new char[f2473b]).replace((char) 0, ' ');

    /* renamed from: a, reason: collision with root package name */
    private final Metrics f2475a;

    public ConstraintLayoutStatistics(ConstraintLayoutStatistics constraintLayoutStatistics) {
        Metrics metrics = new Metrics();
        this.f2475a = metrics;
        metrics.a(constraintLayoutStatistics.f2475a);
    }

    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ConstraintLayoutStatistics clone() {
        return new ConstraintLayoutStatistics(this);
    }
}
