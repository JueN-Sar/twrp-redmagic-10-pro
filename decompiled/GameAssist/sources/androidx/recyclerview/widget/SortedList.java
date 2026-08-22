package androidx.recyclerview.widget;

import java.util.Comparator;

/* loaded from: classes.dex */
public class SortedList<T> {

    public static class BatchedCallback<T2> extends Callback<T2> {

        /* renamed from: c, reason: collision with root package name */
        final Callback f5280c;

        /* renamed from: h, reason: collision with root package name */
        private final BatchingListUpdateCallback f5281h;

        @Override // androidx.recyclerview.widget.ListUpdateCallback
        public void a(int i2, int i3) {
            this.f5281h.a(i2, i3);
        }

        @Override // androidx.recyclerview.widget.ListUpdateCallback
        public void b(int i2, int i3) {
            this.f5281h.b(i2, i3);
        }

        @Override // androidx.recyclerview.widget.SortedList.Callback, androidx.recyclerview.widget.ListUpdateCallback
        public void c(int i2, int i3, Object obj) {
            this.f5281h.c(i2, i3, obj);
        }

        @Override // androidx.recyclerview.widget.SortedList.Callback, java.util.Comparator
        public int compare(Object obj, Object obj2) {
            return this.f5280c.compare(obj, obj2);
        }

        @Override // androidx.recyclerview.widget.ListUpdateCallback
        public void d(int i2, int i3) {
            this.f5281h.d(i2, i3);
        }

        @Override // androidx.recyclerview.widget.SortedList.Callback
        public void e(int i2, int i3) {
            this.f5281h.c(i2, i3, null);
        }
    }

    public static abstract class Callback<T2> implements Comparator<T2>, ListUpdateCallback {
        public void c(int i2, int i3, Object obj) {
            e(i2, i3);
        }

        @Override // java.util.Comparator
        public abstract int compare(Object obj, Object obj2);

        public abstract void e(int i2, int i3);
    }
}
