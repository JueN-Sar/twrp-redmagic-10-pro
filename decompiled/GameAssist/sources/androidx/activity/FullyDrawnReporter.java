package androidx.activity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public final class FullyDrawnReporter {

    /* renamed from: a, reason: collision with root package name */
    private final Executor f42a;

    /* renamed from: b, reason: collision with root package name */
    private final Function0 f43b;

    /* renamed from: c, reason: collision with root package name */
    private final Object f44c;

    /* renamed from: d, reason: collision with root package name */
    private int f45d;

    /* renamed from: e, reason: collision with root package name */
    private boolean f46e;

    /* renamed from: f, reason: collision with root package name */
    private boolean f47f;

    /* renamed from: g, reason: collision with root package name */
    private final List f48g;

    /* renamed from: h, reason: collision with root package name */
    private final Runnable f49h;

    public FullyDrawnReporter(Executor executor, Function0 reportFullyDrawn) {
        Intrinsics.e(executor, "executor");
        Intrinsics.e(reportFullyDrawn, "reportFullyDrawn");
        this.f42a = executor;
        this.f43b = reportFullyDrawn;
        this.f44c = new Object();
        this.f48g = new ArrayList();
        this.f49h = new Runnable() { // from class: androidx.activity.g
            @Override // java.lang.Runnable
            public final void run() {
                FullyDrawnReporter.g(FullyDrawnReporter.this);
            }
        };
    }

    private final void e() {
        if (this.f46e || this.f45d != 0) {
            return;
        }
        this.f46e = true;
        this.f42a.execute(this.f49h);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void g(FullyDrawnReporter this$0) {
        Intrinsics.e(this$0, "this$0");
        synchronized (this$0.f44c) {
            try {
                this$0.f46e = false;
                if (this$0.f45d == 0 && !this$0.f47f) {
                    this$0.f43b.a();
                    this$0.c();
                }
                Unit unit = Unit.f18288a;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void b() {
        synchronized (this.f44c) {
            try {
                if (!this.f47f) {
                    this.f45d++;
                }
                Unit unit = Unit.f18288a;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void c() {
        synchronized (this.f44c) {
            try {
                this.f47f = true;
                Iterator it = this.f48g.iterator();
                while (it.hasNext()) {
                    ((Function0) it.next()).a();
                }
                this.f48g.clear();
                Unit unit = Unit.f18288a;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final boolean d() {
        boolean z;
        synchronized (this.f44c) {
            z = this.f47f;
        }
        return z;
    }

    public final void f() {
        int i2;
        synchronized (this.f44c) {
            try {
                if (!this.f47f && (i2 = this.f45d) > 0) {
                    this.f45d = i2 - 1;
                    e();
                }
                Unit unit = Unit.f18288a;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
