package kotlinx.coroutines.debug.internal;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.AbstractMutableMap;
import kotlin.collections.AbstractMutableSet;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableIterator;
import kotlin.jvm.internal.markers.KMutableMap;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.coroutines.debug.internal.ConcurrentWeakMap;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;

@Metadata
/* loaded from: classes2.dex */
public final class ConcurrentWeakMap<K, V> extends AbstractMutableMap<K, V> {

    /* renamed from: h, reason: collision with root package name */
    private static final /* synthetic */ AtomicIntegerFieldUpdater f19035h = AtomicIntegerFieldUpdater.newUpdater(ConcurrentWeakMap.class, "_size");

    @NotNull
    private volatile /* synthetic */ int _size;

    /* renamed from: c, reason: collision with root package name */
    private final ReferenceQueue f19036c;

    @NotNull
    volatile /* synthetic */ Object core;

    @Metadata
    private final class Core {

        /* renamed from: g, reason: collision with root package name */
        private static final /* synthetic */ AtomicIntegerFieldUpdater f19037g = AtomicIntegerFieldUpdater.newUpdater(Core.class, "load");

        /* renamed from: a, reason: collision with root package name */
        private final int f19038a;

        /* renamed from: b, reason: collision with root package name */
        private final int f19039b;

        /* renamed from: c, reason: collision with root package name */
        private final int f19040c;

        /* renamed from: d, reason: collision with root package name */
        /* synthetic */ AtomicReferenceArray f19041d;

        /* renamed from: e, reason: collision with root package name */
        /* synthetic */ AtomicReferenceArray f19042e;

        @NotNull
        private volatile /* synthetic */ int load = 0;

        @Metadata
        private final class KeyValueIterator<E> implements Iterator<E>, KMutableIterator {

            /* renamed from: c, reason: collision with root package name */
            private final Function2 f19044c;

            /* renamed from: h, reason: collision with root package name */
            private int f19045h = -1;

            /* renamed from: i, reason: collision with root package name */
            private Object f19046i;

            /* renamed from: j, reason: collision with root package name */
            private Object f19047j;

            public KeyValueIterator(Function2 function2) {
                this.f19044c = function2;
                b();
            }

            private final void b() {
                T t;
                while (true) {
                    int i2 = this.f19045h + 1;
                    this.f19045h = i2;
                    if (i2 >= Core.this.f19038a) {
                        return;
                    }
                    HashedWeakRef hashedWeakRef = (HashedWeakRef) Core.this.f19041d.get(this.f19045h);
                    if (hashedWeakRef != null && (t = hashedWeakRef.get()) != 0) {
                        this.f19046i = t;
                        Object obj = Core.this.f19042e.get(this.f19045h);
                        if (obj instanceof Marked) {
                            obj = ((Marked) obj).f19084a;
                        }
                        if (obj != null) {
                            this.f19047j = obj;
                            return;
                        }
                    }
                }
            }

            @Override // java.util.Iterator
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public Void remove() {
                ConcurrentWeakMapKt.e();
                throw new KotlinNothingValueException();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.f19045h < Core.this.f19038a;
            }

            @Override // java.util.Iterator
            public Object next() {
                if (this.f19045h >= Core.this.f19038a) {
                    throw new NoSuchElementException();
                }
                Function2 function2 = this.f19044c;
                Object obj = this.f19046i;
                if (obj == null) {
                    Intrinsics.s("key");
                    obj = Unit.f18288a;
                }
                Object obj2 = this.f19047j;
                if (obj2 == null) {
                    Intrinsics.s("value");
                    obj2 = Unit.f18288a;
                }
                Object y = function2.y(obj, obj2);
                b();
                return y;
            }
        }

        public Core(int i2) {
            this.f19038a = i2;
            this.f19039b = Integer.numberOfLeadingZeros(i2) + 1;
            this.f19040c = (i2 * 2) / 3;
            this.f19041d = new AtomicReferenceArray(i2);
            this.f19042e = new AtomicReferenceArray(i2);
        }

        private final int d(int i2) {
            return (i2 * (-1640531527)) >>> this.f19039b;
        }

        public static /* synthetic */ Object g(Core core, Object obj, Object obj2, HashedWeakRef hashedWeakRef, int i2, Object obj3) {
            if ((i2 & 4) != 0) {
                hashedWeakRef = null;
            }
            return core.f(obj, obj2, hashedWeakRef);
        }

        private final void i(int i2) {
            Object obj;
            do {
                obj = this.f19042e.get(i2);
                if (obj == null || (obj instanceof Marked)) {
                    return;
                }
            } while (!this.f19042e.compareAndSet(i2, obj, null));
            ConcurrentWeakMap.this.h();
        }

        public final void b(HashedWeakRef hashedWeakRef) {
            int d2 = d(hashedWeakRef.f19083a);
            while (true) {
                HashedWeakRef hashedWeakRef2 = (HashedWeakRef) this.f19041d.get(d2);
                if (hashedWeakRef2 == null) {
                    return;
                }
                if (hashedWeakRef2 == hashedWeakRef) {
                    i(d2);
                    return;
                } else {
                    if (d2 == 0) {
                        d2 = this.f19038a;
                    }
                    d2--;
                }
            }
        }

        public final Object c(Object obj) {
            int d2 = d(obj.hashCode());
            while (true) {
                HashedWeakRef hashedWeakRef = (HashedWeakRef) this.f19041d.get(d2);
                if (hashedWeakRef == null) {
                    return null;
                }
                T t = hashedWeakRef.get();
                if (Intrinsics.a(obj, t)) {
                    Object obj2 = this.f19042e.get(d2);
                    return obj2 instanceof Marked ? ((Marked) obj2).f19084a : obj2;
                }
                if (t == 0) {
                    i(d2);
                }
                if (d2 == 0) {
                    d2 = this.f19038a;
                }
                d2--;
            }
        }

        public final Iterator e(Function2 function2) {
            return new KeyValueIterator(function2);
        }

        /* JADX WARN: Code restructure failed: missing block: B:18:0x0057, code lost:
        
            r6 = r5.f19042e.get(r0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:19:0x005f, code lost:
        
            if ((r6 instanceof kotlinx.coroutines.debug.internal.Marked) == false) goto L30;
         */
        /* JADX WARN: Code restructure failed: missing block: B:21:0x006c, code lost:
        
            if (r5.f19042e.compareAndSet(r0, r6, r7) == false) goto L50;
         */
        /* JADX WARN: Code restructure failed: missing block: B:23:0x006e, code lost:
        
            return r6;
         */
        /* JADX WARN: Code restructure failed: missing block: B:27:0x0061, code lost:
        
            r5 = kotlinx.coroutines.debug.internal.ConcurrentWeakMapKt.f19053a;
         */
        /* JADX WARN: Code restructure failed: missing block: B:28:0x0065, code lost:
        
            return r5;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object f(java.lang.Object r6, java.lang.Object r7, kotlinx.coroutines.debug.internal.HashedWeakRef r8) {
            /*
                r5 = this;
                int r0 = r6.hashCode()
                int r0 = r5.d(r0)
                r1 = 0
            L9:
                java.util.concurrent.atomic.AtomicReferenceArray r2 = r5.f19041d
                java.lang.Object r2 = r2.get(r0)
                kotlinx.coroutines.debug.internal.HashedWeakRef r2 = (kotlinx.coroutines.debug.internal.HashedWeakRef) r2
                if (r2 != 0) goto L46
                r2 = 0
                if (r7 != 0) goto L17
                return r2
            L17:
                if (r1 != 0) goto L30
            L19:
                int r1 = r5.load
                int r3 = r5.f19040c
                if (r1 < r3) goto L24
                kotlinx.coroutines.internal.Symbol r5 = kotlinx.coroutines.debug.internal.ConcurrentWeakMapKt.a()
                return r5
            L24:
                int r3 = r1 + 1
                java.util.concurrent.atomic.AtomicIntegerFieldUpdater r4 = kotlinx.coroutines.debug.internal.ConcurrentWeakMap.Core.f19037g
                boolean r1 = r4.compareAndSet(r5, r1, r3)
                if (r1 != 0) goto L2f
                goto L19
            L2f:
                r1 = 1
            L30:
                if (r8 != 0) goto L3d
                kotlinx.coroutines.debug.internal.HashedWeakRef r8 = new kotlinx.coroutines.debug.internal.HashedWeakRef
                kotlinx.coroutines.debug.internal.ConcurrentWeakMap r3 = kotlinx.coroutines.debug.internal.ConcurrentWeakMap.this
                java.lang.ref.ReferenceQueue r3 = kotlinx.coroutines.debug.internal.ConcurrentWeakMap.f(r3)
                r8.<init>(r6, r3)
            L3d:
                java.util.concurrent.atomic.AtomicReferenceArray r3 = r5.f19041d
                boolean r2 = r3.compareAndSet(r0, r2, r8)
                if (r2 != 0) goto L57
                goto L9
            L46:
                java.lang.Object r2 = r2.get()
                boolean r3 = kotlin.jvm.internal.Intrinsics.a(r6, r2)
                if (r3 == 0) goto L6f
                if (r1 == 0) goto L57
                java.util.concurrent.atomic.AtomicIntegerFieldUpdater r6 = kotlinx.coroutines.debug.internal.ConcurrentWeakMap.Core.f19037g
                r6.decrementAndGet(r5)
            L57:
                java.util.concurrent.atomic.AtomicReferenceArray r6 = r5.f19042e
                java.lang.Object r6 = r6.get(r0)
                boolean r8 = r6 instanceof kotlinx.coroutines.debug.internal.Marked
                if (r8 == 0) goto L66
                kotlinx.coroutines.internal.Symbol r5 = kotlinx.coroutines.debug.internal.ConcurrentWeakMapKt.a()
                return r5
            L66:
                java.util.concurrent.atomic.AtomicReferenceArray r8 = r5.f19042e
                boolean r8 = r8.compareAndSet(r0, r6, r7)
                if (r8 == 0) goto L57
                return r6
            L6f:
                if (r2 != 0) goto L74
                r5.i(r0)
            L74:
                if (r0 != 0) goto L78
                int r0 = r5.f19038a
            L78:
                int r0 = r0 + (-1)
                goto L9
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.debug.internal.ConcurrentWeakMap.Core.f(java.lang.Object, java.lang.Object, kotlinx.coroutines.debug.internal.HashedWeakRef):java.lang.Object");
        }

        public final Core h() {
            int a2;
            Object obj;
            Symbol symbol;
            Marked d2;
            while (true) {
                a2 = RangesKt___RangesKt.a(ConcurrentWeakMap.this.size(), 4);
                Core core = new Core(Integer.highestOneBit(a2) * 4);
                int i2 = this.f19038a;
                for (int i3 = 0; i3 < i2; i3++) {
                    HashedWeakRef hashedWeakRef = (HashedWeakRef) this.f19041d.get(i3);
                    Object obj2 = hashedWeakRef != null ? hashedWeakRef.get() : null;
                    if (hashedWeakRef != null && obj2 == null) {
                        i(i3);
                    }
                    while (true) {
                        obj = this.f19042e.get(i3);
                        if (obj instanceof Marked) {
                            obj = ((Marked) obj).f19084a;
                            break;
                        }
                        AtomicReferenceArray atomicReferenceArray = this.f19042e;
                        d2 = ConcurrentWeakMapKt.d(obj);
                        if (atomicReferenceArray.compareAndSet(i3, obj, d2)) {
                            break;
                        }
                    }
                    if (obj2 != null && obj != null) {
                        Object f2 = core.f(obj2, obj, hashedWeakRef);
                        symbol = ConcurrentWeakMapKt.f19053a;
                        if (f2 != symbol) {
                        }
                    }
                }
                return core;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Metadata
    static final class Entry<K, V> implements Map.Entry<K, V>, KMutableMap.Entry {

        /* renamed from: c, reason: collision with root package name */
        private final Object f19049c;

        /* renamed from: h, reason: collision with root package name */
        private final Object f19050h;

        public Entry(Object obj, Object obj2) {
            this.f19049c = obj;
            this.f19050h = obj2;
        }

        @Override // java.util.Map.Entry
        public Object getKey() {
            return this.f19049c;
        }

        @Override // java.util.Map.Entry
        public Object getValue() {
            return this.f19050h;
        }

        @Override // java.util.Map.Entry
        public Object setValue(Object obj) {
            ConcurrentWeakMapKt.e();
            throw new KotlinNothingValueException();
        }
    }

    @Metadata
    private final class KeyValueSet<E> extends AbstractMutableSet<E> {

        /* renamed from: c, reason: collision with root package name */
        private final Function2 f19051c;

        public KeyValueSet(Function2 function2) {
            this.f19051c = function2;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean add(Object obj) {
            ConcurrentWeakMapKt.e();
            throw new KotlinNothingValueException();
        }

        @Override // kotlin.collections.AbstractMutableSet
        public int b() {
            return ConcurrentWeakMap.this.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator iterator() {
            return ((Core) ConcurrentWeakMap.this.core).e(this.f19051c);
        }
    }

    public /* synthetic */ ConcurrentWeakMap(boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? false : z);
    }

    private final void g(HashedWeakRef hashedWeakRef) {
        ((Core) this.core).b(hashedWeakRef);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void h() {
        f19035h.decrementAndGet(this);
    }

    private final synchronized Object i(Object obj, Object obj2) {
        Object g2;
        Symbol symbol;
        Core core = (Core) this.core;
        while (true) {
            g2 = Core.g(core, obj, obj2, null, 4, null);
            symbol = ConcurrentWeakMapKt.f19053a;
            if (g2 == symbol) {
                core = core.h();
                this.core = core;
            }
        }
        return g2;
    }

    @Override // kotlin.collections.AbstractMutableMap
    public Set a() {
        return new KeyValueSet(new Function2<K, V, Map.Entry<K, V>>() { // from class: kotlinx.coroutines.debug.internal.ConcurrentWeakMap$entries$1
            @Override // kotlin.jvm.functions.Function2
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public final Map.Entry y(Object obj, Object obj2) {
                return new ConcurrentWeakMap.Entry(obj, obj2);
            }
        });
    }

    @Override // kotlin.collections.AbstractMutableMap
    public Set b() {
        return new KeyValueSet(new Function2<K, V, K>() { // from class: kotlinx.coroutines.debug.internal.ConcurrentWeakMap$keys$1
            @Override // kotlin.jvm.functions.Function2
            public final Object y(Object obj, Object obj2) {
                return obj;
            }
        });
    }

    @Override // kotlin.collections.AbstractMutableMap
    public int c() {
        return this._size;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        Iterator it = keySet().iterator();
        while (it.hasNext()) {
            remove(it.next());
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Object get(Object obj) {
        if (obj == null) {
            return null;
        }
        return ((Core) this.core).c(obj);
    }

    public final void j() {
        if (this.f19036c == null) {
            throw new IllegalStateException("Must be created with weakRefQueue = true".toString());
        }
        while (true) {
            try {
                Reference remove = this.f19036c.remove();
                if (remove == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.debug.internal.HashedWeakRef<*>");
                }
                g((HashedWeakRef) remove);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Object put(Object obj, Object obj2) {
        Symbol symbol;
        Object g2 = Core.g((Core) this.core, obj, obj2, null, 4, null);
        symbol = ConcurrentWeakMapKt.f19053a;
        if (g2 == symbol) {
            g2 = i(obj, obj2);
        }
        if (g2 == null) {
            f19035h.incrementAndGet(this);
        }
        return g2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Object remove(Object obj) {
        Symbol symbol;
        if (obj == null) {
            return null;
        }
        Object g2 = Core.g((Core) this.core, obj, null, null, 4, null);
        symbol = ConcurrentWeakMapKt.f19053a;
        if (g2 == symbol) {
            g2 = i(obj, null);
        }
        if (g2 != null) {
            f19035h.decrementAndGet(this);
        }
        return g2;
    }

    public ConcurrentWeakMap(boolean z) {
        this._size = 0;
        this.core = new Core(16);
        this.f19036c = z ? new ReferenceQueue() : null;
    }
}
