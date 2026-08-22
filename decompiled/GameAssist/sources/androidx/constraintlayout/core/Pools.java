package androidx.constraintlayout.core;

/* loaded from: classes.dex */
final class Pools {

    interface Pool<T> {
        void a(Object[] objArr, int i2);

        Object acquire();

        boolean release(Object obj);
    }

    static class SimplePool<T> implements Pool<T> {

        /* renamed from: a, reason: collision with root package name */
        private final Object[] f1511a;

        /* renamed from: b, reason: collision with root package name */
        private int f1512b;

        SimplePool(int i2) {
            if (i2 <= 0) {
                throw new IllegalArgumentException("The max pool size must be > 0");
            }
            this.f1511a = new Object[i2];
        }

        @Override // androidx.constraintlayout.core.Pools.Pool
        public void a(Object[] objArr, int i2) {
            if (i2 > objArr.length) {
                i2 = objArr.length;
            }
            for (int i3 = 0; i3 < i2; i3++) {
                Object obj = objArr[i3];
                int i4 = this.f1512b;
                Object[] objArr2 = this.f1511a;
                if (i4 < objArr2.length) {
                    objArr2[i4] = obj;
                    this.f1512b = i4 + 1;
                }
            }
        }

        @Override // androidx.constraintlayout.core.Pools.Pool
        public Object acquire() {
            int i2 = this.f1512b;
            if (i2 <= 0) {
                return null;
            }
            int i3 = i2 - 1;
            Object[] objArr = this.f1511a;
            Object obj = objArr[i3];
            objArr[i3] = null;
            this.f1512b = i2 - 1;
            return obj;
        }

        @Override // androidx.constraintlayout.core.Pools.Pool
        public boolean release(Object obj) {
            int i2 = this.f1512b;
            Object[] objArr = this.f1511a;
            if (i2 >= objArr.length) {
                return false;
            }
            objArr[i2] = obj;
            this.f1512b = i2 + 1;
            return true;
        }
    }
}
