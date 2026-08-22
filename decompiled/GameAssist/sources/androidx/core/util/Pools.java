package androidx.core.util;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
/* loaded from: classes.dex */
public final class Pools {

    @Metadata
    public interface Pool<T> {
        Object acquire();

        boolean release(Object obj);
    }

    @Metadata
    @SourceDebugExtension
    public static class SimplePool<T> implements Pool<T> {

        /* renamed from: a, reason: collision with root package name */
        private final Object[] f3282a;

        /* renamed from: b, reason: collision with root package name */
        private int f3283b;

        public SimplePool(int i2) {
            if (i2 <= 0) {
                throw new IllegalArgumentException("The max pool size must be > 0".toString());
            }
            this.f3282a = new Object[i2];
        }

        private final boolean a(Object obj) {
            int i2 = this.f3283b;
            for (int i3 = 0; i3 < i2; i3++) {
                if (this.f3282a[i3] == obj) {
                    return true;
                }
            }
            return false;
        }

        @Override // androidx.core.util.Pools.Pool
        public Object acquire() {
            int i2 = this.f3283b;
            if (i2 <= 0) {
                return null;
            }
            int i3 = i2 - 1;
            Object obj = this.f3282a[i3];
            Intrinsics.c(obj, "null cannot be cast to non-null type T of androidx.core.util.Pools.SimplePool");
            this.f3282a[i3] = null;
            this.f3283b--;
            return obj;
        }

        @Override // androidx.core.util.Pools.Pool
        public boolean release(Object instance) {
            Intrinsics.e(instance, "instance");
            if (!(!a(instance))) {
                throw new IllegalStateException("Already in the pool!".toString());
            }
            int i2 = this.f3283b;
            Object[] objArr = this.f3282a;
            if (i2 >= objArr.length) {
                return false;
            }
            objArr[i2] = instance;
            this.f3283b = i2 + 1;
            return true;
        }
    }

    @Metadata
    @SourceDebugExtension
    public static class SynchronizedPool<T> extends SimplePool<T> {

        /* renamed from: c, reason: collision with root package name */
        private final Object f3284c;

        public SynchronizedPool(int i2) {
            super(i2);
            this.f3284c = new Object();
        }

        @Override // androidx.core.util.Pools.SimplePool, androidx.core.util.Pools.Pool
        public Object acquire() {
            Object acquire;
            synchronized (this.f3284c) {
                acquire = super.acquire();
            }
            return acquire;
        }

        @Override // androidx.core.util.Pools.SimplePool, androidx.core.util.Pools.Pool
        public boolean release(Object instance) {
            boolean release;
            Intrinsics.e(instance, "instance");
            synchronized (this.f3284c) {
                release = super.release(instance);
            }
            return release;
        }
    }
}
