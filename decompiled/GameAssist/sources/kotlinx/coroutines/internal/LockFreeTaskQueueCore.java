package kotlinx.coroutines.internal;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

@Metadata
/* loaded from: classes2.dex */
public final class LockFreeTaskQueueCore<E> {

    @NotNull
    private volatile /* synthetic */ Object _next = null;

    @NotNull
    private volatile /* synthetic */ long _state = 0;

    /* renamed from: a, reason: collision with root package name */
    private final int f19388a;

    /* renamed from: b, reason: collision with root package name */
    private final boolean f19389b;

    /* renamed from: c, reason: collision with root package name */
    private final int f19390c;

    /* renamed from: d, reason: collision with root package name */
    private /* synthetic */ AtomicReferenceArray f19391d;

    /* renamed from: e, reason: collision with root package name */
    public static final Companion f19384e = new Companion(null);

    /* renamed from: h, reason: collision with root package name */
    public static final Symbol f19387h = new Symbol("REMOVE_FROZEN");

    /* renamed from: f, reason: collision with root package name */
    private static final /* synthetic */ AtomicReferenceFieldUpdater f19385f = AtomicReferenceFieldUpdater.newUpdater(LockFreeTaskQueueCore.class, Object.class, "_next");

    /* renamed from: g, reason: collision with root package name */
    private static final /* synthetic */ AtomicLongFieldUpdater f19386g = AtomicLongFieldUpdater.newUpdater(LockFreeTaskQueueCore.class, "_state");

    @Metadata
    public static final class Companion {
        private Companion() {
        }

        public final int a(long j2) {
            return (j2 & 2305843009213693952L) != 0 ? 2 : 1;
        }

        public final long b(long j2, int i2) {
            return d(j2, 1073741823L) | i2;
        }

        public final long c(long j2, int i2) {
            return d(j2, 1152921503533105152L) | (i2 << 30);
        }

        public final long d(long j2, long j3) {
            return j2 & (~j3);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata
    public static final class Placeholder {

        /* renamed from: a, reason: collision with root package name */
        public final int f19392a;

        public Placeholder(int i2) {
            this.f19392a = i2;
        }
    }

    public LockFreeTaskQueueCore(int i2, boolean z) {
        this.f19388a = i2;
        this.f19389b = z;
        int i3 = i2 - 1;
        this.f19390c = i3;
        this.f19391d = new AtomicReferenceArray(i2);
        if (i3 > 1073741823) {
            throw new IllegalStateException("Check failed.".toString());
        }
        if ((i2 & i3) != 0) {
            throw new IllegalStateException("Check failed.".toString());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final LockFreeTaskQueueCore b(long j2) {
        LockFreeTaskQueueCore lockFreeTaskQueueCore = new LockFreeTaskQueueCore(this.f19388a * 2, this.f19389b);
        int i2 = (int) (1073741823 & j2);
        int i3 = (int) ((1152921503533105152L & j2) >> 30);
        while (true) {
            int i4 = this.f19390c;
            if ((i2 & i4) == (i3 & i4)) {
                lockFreeTaskQueueCore._state = f19384e.d(j2, 1152921504606846976L);
                return lockFreeTaskQueueCore;
            }
            Object obj = this.f19391d.get(i4 & i2);
            if (obj == null) {
                obj = new Placeholder(i2);
            }
            lockFreeTaskQueueCore.f19391d.set(lockFreeTaskQueueCore.f19390c & i2, obj);
            i2++;
        }
    }

    private final LockFreeTaskQueueCore c(long j2) {
        while (true) {
            LockFreeTaskQueueCore lockFreeTaskQueueCore = (LockFreeTaskQueueCore) this._next;
            if (lockFreeTaskQueueCore != null) {
                return lockFreeTaskQueueCore;
            }
            f19385f.compareAndSet(this, null, b(j2));
        }
    }

    private final LockFreeTaskQueueCore e(int i2, Object obj) {
        Object obj2 = this.f19391d.get(this.f19390c & i2);
        if (!(obj2 instanceof Placeholder) || ((Placeholder) obj2).f19392a != i2) {
            return null;
        }
        this.f19391d.set(i2 & this.f19390c, obj);
        return this;
    }

    private final long h() {
        long j2;
        long j3;
        do {
            j2 = this._state;
            if ((j2 & 1152921504606846976L) != 0) {
                return j2;
            }
            j3 = j2 | 1152921504606846976L;
        } while (!f19386g.compareAndSet(this, j2, j3));
        return j3;
    }

    private final LockFreeTaskQueueCore k(int i2, int i3) {
        long j2;
        int i4;
        do {
            j2 = this._state;
            i4 = (int) (1073741823 & j2);
            if ((1152921504606846976L & j2) != 0) {
                return i();
            }
        } while (!f19386g.compareAndSet(this, j2, f19384e.b(j2, i3)));
        this.f19391d.set(this.f19390c & i4, null);
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x004a, code lost:
    
        return 1;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int a(java.lang.Object r12) {
        /*
            r11 = this;
        L0:
            long r2 = r11._state
            r0 = 3458764513820540928(0x3000000000000000, double:1.727233711018889E-77)
            long r0 = r0 & r2
            r6 = 0
            int r0 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r0 == 0) goto L12
            kotlinx.coroutines.internal.LockFreeTaskQueueCore$Companion r11 = kotlinx.coroutines.internal.LockFreeTaskQueueCore.f19384e
            int r11 = r11.a(r2)
            return r11
        L12:
            r0 = 1073741823(0x3fffffff, double:5.304989472E-315)
            long r0 = r0 & r2
            int r0 = (int) r0
            r4 = 1152921503533105152(0xfffffffc0000000, double:1.2882296003504729E-231)
            long r4 = r4 & r2
            r1 = 30
            long r4 = r4 >> r1
            int r8 = (int) r4
            int r9 = r11.f19390c
            int r1 = r8 + 2
            r1 = r1 & r9
            r4 = r0 & r9
            r5 = 1
            if (r1 != r4) goto L2c
            return r5
        L2c:
            boolean r1 = r11.f19389b
            r4 = 1073741823(0x3fffffff, float:1.9999999)
            if (r1 != 0) goto L4b
            java.util.concurrent.atomic.AtomicReferenceArray r1 = r11.f19391d
            r10 = r8 & r9
            java.lang.Object r1 = r1.get(r10)
            if (r1 == 0) goto L4b
            int r1 = r11.f19388a
            r2 = 1024(0x400, float:1.435E-42)
            if (r1 < r2) goto L4a
            int r8 = r8 - r0
            r0 = r8 & r4
            int r1 = r1 >> 1
            if (r0 <= r1) goto L0
        L4a:
            return r5
        L4b:
            int r0 = r8 + 1
            r0 = r0 & r4
            java.util.concurrent.atomic.AtomicLongFieldUpdater r1 = kotlinx.coroutines.internal.LockFreeTaskQueueCore.f19386g
            kotlinx.coroutines.internal.LockFreeTaskQueueCore$Companion r4 = kotlinx.coroutines.internal.LockFreeTaskQueueCore.f19384e
            long r4 = r4.c(r2, r0)
            r0 = r1
            r1 = r11
            boolean r0 = r0.compareAndSet(r1, r2, r4)
            if (r0 == 0) goto L0
            java.util.concurrent.atomic.AtomicReferenceArray r0 = r11.f19391d
            r1 = r8 & r9
            r0.set(r1, r12)
        L65:
            long r0 = r11._state
            r2 = 1152921504606846976(0x1000000000000000, double:1.2882297539194267E-231)
            long r0 = r0 & r2
            int r0 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r0 == 0) goto L78
            kotlinx.coroutines.internal.LockFreeTaskQueueCore r11 = r11.i()
            kotlinx.coroutines.internal.LockFreeTaskQueueCore r11 = r11.e(r8, r12)
            if (r11 != 0) goto L65
        L78:
            r11 = 0
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.LockFreeTaskQueueCore.a(java.lang.Object):int");
    }

    public final boolean d() {
        long j2;
        do {
            j2 = this._state;
            if ((j2 & 2305843009213693952L) != 0) {
                return true;
            }
            if ((1152921504606846976L & j2) != 0) {
                return false;
            }
        } while (!f19386g.compareAndSet(this, j2, j2 | 2305843009213693952L));
        return true;
    }

    public final int f() {
        long j2 = this._state;
        return 1073741823 & (((int) ((j2 & 1152921503533105152L) >> 30)) - ((int) (1073741823 & j2)));
    }

    public final boolean g() {
        long j2 = this._state;
        return ((int) (1073741823 & j2)) == ((int) ((j2 & 1152921503533105152L) >> 30));
    }

    public final LockFreeTaskQueueCore i() {
        return c(h());
    }

    public final Object j() {
        while (true) {
            long j2 = this._state;
            if ((1152921504606846976L & j2) != 0) {
                return f19387h;
            }
            int i2 = (int) (1073741823 & j2);
            int i3 = this.f19390c;
            if ((((int) ((1152921503533105152L & j2) >> 30)) & i3) == (i2 & i3)) {
                return null;
            }
            Object obj = this.f19391d.get(i3 & i2);
            if (obj == null) {
                if (this.f19389b) {
                    return null;
                }
            } else {
                if (obj instanceof Placeholder) {
                    return null;
                }
                int i4 = (i2 + 1) & 1073741823;
                if (f19386g.compareAndSet(this, j2, f19384e.b(j2, i4))) {
                    this.f19391d.set(this.f19390c & i2, null);
                    return obj;
                }
                if (this.f19389b) {
                    do {
                        this = this.k(i2, i4);
                    } while (this != null);
                    return obj;
                }
            }
        }
    }
}
