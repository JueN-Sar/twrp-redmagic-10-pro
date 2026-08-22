package androidx.constraintlayout.core;

import androidx.constraintlayout.core.Pools;

/* loaded from: classes.dex */
public class Cache {

    /* renamed from: a, reason: collision with root package name */
    Pools.Pool f1469a = new Pools.SimplePool(256);

    /* renamed from: b, reason: collision with root package name */
    Pools.Pool f1470b = new Pools.SimplePool(256);

    /* renamed from: c, reason: collision with root package name */
    Pools.Pool f1471c = new Pools.SimplePool(256);

    /* renamed from: d, reason: collision with root package name */
    SolverVariable[] f1472d = new SolverVariable[32];
}
