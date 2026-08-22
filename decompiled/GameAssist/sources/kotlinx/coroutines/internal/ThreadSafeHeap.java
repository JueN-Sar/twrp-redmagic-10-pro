package kotlinx.coroutines.internal;

import java.lang.Comparable;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.InternalCoroutinesApi;
import kotlinx.coroutines.internal.ThreadSafeHeapNode;
import org.jetbrains.annotations.NotNull;

@InternalCoroutinesApi
@Metadata
/* loaded from: classes2.dex */
public class ThreadSafeHeap<T extends ThreadSafeHeapNode & Comparable<? super T>> {

    @NotNull
    private volatile /* synthetic */ int _size = 0;

    /* renamed from: a, reason: collision with root package name */
    private ThreadSafeHeapNode[] f19415a;

    private final ThreadSafeHeapNode[] f() {
        ThreadSafeHeapNode[] threadSafeHeapNodeArr = this.f19415a;
        if (threadSafeHeapNodeArr == null) {
            ThreadSafeHeapNode[] threadSafeHeapNodeArr2 = new ThreadSafeHeapNode[4];
            this.f19415a = threadSafeHeapNodeArr2;
            return threadSafeHeapNodeArr2;
        }
        if (c() < threadSafeHeapNodeArr.length) {
            return threadSafeHeapNodeArr;
        }
        Object[] copyOf = Arrays.copyOf(threadSafeHeapNodeArr, c() * 2);
        Intrinsics.d(copyOf, "copyOf(this, newSize)");
        ThreadSafeHeapNode[] threadSafeHeapNodeArr3 = (ThreadSafeHeapNode[]) copyOf;
        this.f19415a = threadSafeHeapNodeArr3;
        return threadSafeHeapNodeArr3;
    }

    private final void j(int i2) {
        this._size = i2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0028, code lost:
    
        if (((java.lang.Comparable) r3).compareTo(r4) < 0) goto L11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void k(int r6) {
        /*
            r5 = this;
        L0:
            int r0 = r6 * 2
            int r1 = r0 + 1
            int r2 = r5.c()
            if (r1 < r2) goto Lb
            return
        Lb:
            kotlinx.coroutines.internal.ThreadSafeHeapNode[] r2 = r5.f19415a
            kotlin.jvm.internal.Intrinsics.b(r2)
            int r0 = r0 + 2
            int r3 = r5.c()
            if (r0 >= r3) goto L2b
            r3 = r2[r0]
            kotlin.jvm.internal.Intrinsics.b(r3)
            java.lang.Comparable r3 = (java.lang.Comparable) r3
            r4 = r2[r1]
            kotlin.jvm.internal.Intrinsics.b(r4)
            int r3 = r3.compareTo(r4)
            if (r3 >= 0) goto L2b
            goto L2c
        L2b:
            r0 = r1
        L2c:
            r1 = r2[r6]
            kotlin.jvm.internal.Intrinsics.b(r1)
            java.lang.Comparable r1 = (java.lang.Comparable) r1
            r2 = r2[r0]
            kotlin.jvm.internal.Intrinsics.b(r2)
            int r1 = r1.compareTo(r2)
            if (r1 > 0) goto L3f
            return
        L3f:
            r5.m(r6, r0)
            r6 = r0
            goto L0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.ThreadSafeHeap.k(int):void");
    }

    private final void l(int i2) {
        while (i2 > 0) {
            ThreadSafeHeapNode[] threadSafeHeapNodeArr = this.f19415a;
            Intrinsics.b(threadSafeHeapNodeArr);
            int i3 = (i2 - 1) / 2;
            ThreadSafeHeapNode threadSafeHeapNode = threadSafeHeapNodeArr[i3];
            Intrinsics.b(threadSafeHeapNode);
            ThreadSafeHeapNode threadSafeHeapNode2 = threadSafeHeapNodeArr[i2];
            Intrinsics.b(threadSafeHeapNode2);
            if (((Comparable) threadSafeHeapNode).compareTo(threadSafeHeapNode2) <= 0) {
                return;
            }
            m(i2, i3);
            i2 = i3;
        }
    }

    private final void m(int i2, int i3) {
        ThreadSafeHeapNode[] threadSafeHeapNodeArr = this.f19415a;
        Intrinsics.b(threadSafeHeapNodeArr);
        ThreadSafeHeapNode threadSafeHeapNode = threadSafeHeapNodeArr[i3];
        Intrinsics.b(threadSafeHeapNode);
        ThreadSafeHeapNode threadSafeHeapNode2 = threadSafeHeapNodeArr[i2];
        Intrinsics.b(threadSafeHeapNode2);
        threadSafeHeapNodeArr[i2] = threadSafeHeapNode;
        threadSafeHeapNodeArr[i3] = threadSafeHeapNode2;
        threadSafeHeapNode.setIndex(i2);
        threadSafeHeapNode2.setIndex(i3);
    }

    public final void a(ThreadSafeHeapNode threadSafeHeapNode) {
        threadSafeHeapNode.c(this);
        ThreadSafeHeapNode[] f2 = f();
        int c2 = c();
        j(c2 + 1);
        f2[c2] = threadSafeHeapNode;
        threadSafeHeapNode.setIndex(c2);
        l(c2);
    }

    public final ThreadSafeHeapNode b() {
        ThreadSafeHeapNode[] threadSafeHeapNodeArr = this.f19415a;
        if (threadSafeHeapNodeArr != null) {
            return threadSafeHeapNodeArr[0];
        }
        return null;
    }

    public final int c() {
        return this._size;
    }

    public final boolean d() {
        return c() == 0;
    }

    public final ThreadSafeHeapNode e() {
        ThreadSafeHeapNode b2;
        synchronized (this) {
            b2 = b();
        }
        return b2;
    }

    public final boolean g(ThreadSafeHeapNode threadSafeHeapNode) {
        boolean z;
        synchronized (this) {
            if (threadSafeHeapNode.d() == null) {
                z = false;
            } else {
                h(threadSafeHeapNode.getIndex());
                z = true;
            }
        }
        return z;
    }

    public final ThreadSafeHeapNode h(int i2) {
        ThreadSafeHeapNode[] threadSafeHeapNodeArr = this.f19415a;
        Intrinsics.b(threadSafeHeapNodeArr);
        j(c() - 1);
        if (i2 < c()) {
            m(i2, c());
            int i3 = (i2 - 1) / 2;
            if (i2 > 0) {
                ThreadSafeHeapNode threadSafeHeapNode = threadSafeHeapNodeArr[i2];
                Intrinsics.b(threadSafeHeapNode);
                ThreadSafeHeapNode threadSafeHeapNode2 = threadSafeHeapNodeArr[i3];
                Intrinsics.b(threadSafeHeapNode2);
                if (((Comparable) threadSafeHeapNode).compareTo(threadSafeHeapNode2) < 0) {
                    m(i2, i3);
                    l(i3);
                }
            }
            k(i2);
        }
        ThreadSafeHeapNode threadSafeHeapNode3 = threadSafeHeapNodeArr[c()];
        Intrinsics.b(threadSafeHeapNode3);
        threadSafeHeapNode3.c(null);
        threadSafeHeapNode3.setIndex(-1);
        threadSafeHeapNodeArr[c()] = null;
        return threadSafeHeapNode3;
    }

    public final ThreadSafeHeapNode i() {
        ThreadSafeHeapNode h2;
        synchronized (this) {
            h2 = c() > 0 ? h(0) : null;
        }
        return h2;
    }
}
