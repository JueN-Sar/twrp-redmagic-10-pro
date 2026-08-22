package androidx.recyclerview.widget;

import androidx.annotation.VisibleForTesting;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class DiffUtil {

    /* renamed from: a, reason: collision with root package name */
    private static final Comparator f4976a = new Comparator<Snake>() { // from class: androidx.recyclerview.widget.DiffUtil.1
        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(Snake snake, Snake snake2) {
            int i2 = snake.f4991a - snake2.f4991a;
            return i2 == 0 ? snake.f4992b - snake2.f4992b : i2;
        }
    };

    public static abstract class Callback {
        public abstract boolean a(int i2, int i3);

        public abstract boolean b(int i2, int i3);

        public Object c(int i2, int i3) {
            return null;
        }

        public abstract int d();

        public abstract int e();
    }

    public static class DiffResult {

        /* renamed from: a, reason: collision with root package name */
        private final List f4977a;

        /* renamed from: b, reason: collision with root package name */
        private final int[] f4978b;

        /* renamed from: c, reason: collision with root package name */
        private final int[] f4979c;

        /* renamed from: d, reason: collision with root package name */
        private final Callback f4980d;

        /* renamed from: e, reason: collision with root package name */
        private final int f4981e;

        /* renamed from: f, reason: collision with root package name */
        private final int f4982f;

        /* renamed from: g, reason: collision with root package name */
        private final boolean f4983g;

        DiffResult(Callback callback, List list, int[] iArr, int[] iArr2, boolean z) {
            this.f4977a = list;
            this.f4978b = iArr;
            this.f4979c = iArr2;
            Arrays.fill(iArr, 0);
            Arrays.fill(iArr2, 0);
            this.f4980d = callback;
            this.f4981e = callback.e();
            this.f4982f = callback.d();
            this.f4983g = z;
            a();
            h();
        }

        private void a() {
            Snake snake = this.f4977a.isEmpty() ? null : (Snake) this.f4977a.get(0);
            if (snake != null && snake.f4991a == 0 && snake.f4992b == 0) {
                return;
            }
            Snake snake2 = new Snake();
            snake2.f4991a = 0;
            snake2.f4992b = 0;
            snake2.f4994d = false;
            snake2.f4993c = 0;
            snake2.f4995e = false;
            this.f4977a.add(0, snake2);
        }

        private void b(List list, ListUpdateCallback listUpdateCallback, int i2, int i3, int i4) {
            if (!this.f4983g) {
                listUpdateCallback.a(i2, i3);
                return;
            }
            for (int i5 = i3 - 1; i5 >= 0; i5--) {
                int i6 = i4 + i5;
                int i7 = this.f4979c[i6];
                int i8 = i7 & 31;
                if (i8 == 0) {
                    listUpdateCallback.a(i2, 1);
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        ((PostponedUpdate) it.next()).f4985b++;
                    }
                } else if (i8 == 4 || i8 == 8) {
                    int i9 = i7 >> 5;
                    listUpdateCallback.d(j(list, i9, true).f4985b, i2);
                    if (i8 == 4) {
                        listUpdateCallback.c(i2, 1, this.f4980d.c(i9, i6));
                    }
                } else {
                    if (i8 != 16) {
                        throw new IllegalStateException("unknown flag for pos " + i6 + " " + Long.toBinaryString(i8));
                    }
                    list.add(new PostponedUpdate(i6, i2, false));
                }
            }
        }

        private void c(List list, ListUpdateCallback listUpdateCallback, int i2, int i3, int i4) {
            if (!this.f4983g) {
                listUpdateCallback.b(i2, i3);
                return;
            }
            for (int i5 = i3 - 1; i5 >= 0; i5--) {
                int i6 = i4 + i5;
                int i7 = this.f4978b[i6];
                int i8 = i7 & 31;
                if (i8 == 0) {
                    listUpdateCallback.b(i2 + i5, 1);
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        ((PostponedUpdate) it.next()).f4985b--;
                    }
                } else if (i8 == 4 || i8 == 8) {
                    int i9 = i7 >> 5;
                    PostponedUpdate j2 = j(list, i9, false);
                    listUpdateCallback.d(i2 + i5, j2.f4985b - 1);
                    if (i8 == 4) {
                        listUpdateCallback.c(j2.f4985b - 1, 1, this.f4980d.c(i6, i9));
                    }
                } else {
                    if (i8 != 16) {
                        throw new IllegalStateException("unknown flag for pos " + i6 + " " + Long.toBinaryString(i8));
                    }
                    list.add(new PostponedUpdate(i6, i2 + i5, true));
                }
            }
        }

        private void f(int i2, int i3, int i4) {
            if (this.f4978b[i2 - 1] != 0) {
                return;
            }
            g(i2, i3, i4, false);
        }

        private boolean g(int i2, int i3, int i4, boolean z) {
            int i5;
            int i6;
            int i7;
            if (z) {
                i3--;
                i6 = i2;
                i5 = i3;
            } else {
                i5 = i2 - 1;
                i6 = i5;
            }
            while (i4 >= 0) {
                Snake snake = (Snake) this.f4977a.get(i4);
                int i8 = snake.f4991a;
                int i9 = snake.f4993c;
                int i10 = i8 + i9;
                int i11 = snake.f4992b + i9;
                if (z) {
                    for (int i12 = i6 - 1; i12 >= i10; i12--) {
                        if (this.f4980d.b(i12, i5)) {
                            i7 = this.f4980d.a(i12, i5) ? 8 : 4;
                            this.f4979c[i5] = (i12 << 5) | 16;
                            this.f4978b[i12] = (i5 << 5) | i7;
                            return true;
                        }
                    }
                } else {
                    for (int i13 = i3 - 1; i13 >= i11; i13--) {
                        if (this.f4980d.b(i5, i13)) {
                            i7 = this.f4980d.a(i5, i13) ? 8 : 4;
                            int i14 = i2 - 1;
                            this.f4978b[i14] = (i13 << 5) | 16;
                            this.f4979c[i13] = (i14 << 5) | i7;
                            return true;
                        }
                    }
                }
                i6 = snake.f4991a;
                i3 = snake.f4992b;
                i4--;
            }
            return false;
        }

        private void h() {
            int i2 = this.f4981e;
            int i3 = this.f4982f;
            for (int size = this.f4977a.size() - 1; size >= 0; size--) {
                Snake snake = (Snake) this.f4977a.get(size);
                int i4 = snake.f4991a;
                int i5 = snake.f4993c;
                int i6 = i4 + i5;
                int i7 = snake.f4992b + i5;
                if (this.f4983g) {
                    while (i2 > i6) {
                        f(i2, i3, size);
                        i2--;
                    }
                    while (i3 > i7) {
                        i(i2, i3, size);
                        i3--;
                    }
                }
                for (int i8 = 0; i8 < snake.f4993c; i8++) {
                    int i9 = snake.f4991a + i8;
                    int i10 = snake.f4992b + i8;
                    int i11 = this.f4980d.a(i9, i10) ? 1 : 2;
                    this.f4978b[i9] = (i10 << 5) | i11;
                    this.f4979c[i10] = (i9 << 5) | i11;
                }
                i2 = snake.f4991a;
                i3 = snake.f4992b;
            }
        }

        private void i(int i2, int i3, int i4) {
            if (this.f4979c[i3 - 1] != 0) {
                return;
            }
            g(i2, i3, i4, true);
        }

        private static PostponedUpdate j(List list, int i2, boolean z) {
            int size = list.size() - 1;
            while (size >= 0) {
                PostponedUpdate postponedUpdate = (PostponedUpdate) list.get(size);
                if (postponedUpdate.f4984a == i2 && postponedUpdate.f4986c == z) {
                    list.remove(size);
                    while (size < list.size()) {
                        ((PostponedUpdate) list.get(size)).f4985b += z ? 1 : -1;
                        size++;
                    }
                    return postponedUpdate;
                }
                size--;
            }
            return null;
        }

        public void d(ListUpdateCallback listUpdateCallback) {
            BatchingListUpdateCallback batchingListUpdateCallback = listUpdateCallback instanceof BatchingListUpdateCallback ? (BatchingListUpdateCallback) listUpdateCallback : new BatchingListUpdateCallback(listUpdateCallback);
            ArrayList arrayList = new ArrayList();
            int i2 = this.f4981e;
            int i3 = this.f4982f;
            for (int size = this.f4977a.size() - 1; size >= 0; size--) {
                Snake snake = (Snake) this.f4977a.get(size);
                int i4 = snake.f4993c;
                int i5 = snake.f4991a + i4;
                int i6 = snake.f4992b + i4;
                if (i5 < i2) {
                    c(arrayList, batchingListUpdateCallback, i5, i2 - i5, i5);
                }
                if (i6 < i3) {
                    b(arrayList, batchingListUpdateCallback, i5, i3 - i6, i6);
                }
                for (int i7 = i4 - 1; i7 >= 0; i7--) {
                    int[] iArr = this.f4978b;
                    int i8 = snake.f4991a;
                    if ((iArr[i8 + i7] & 31) == 2) {
                        batchingListUpdateCallback.c(i8 + i7, 1, this.f4980d.c(i8 + i7, snake.f4992b + i7));
                    }
                }
                i2 = snake.f4991a;
                i3 = snake.f4992b;
            }
            batchingListUpdateCallback.e();
        }

        public void e(RecyclerView.Adapter adapter) {
            d(new AdapterListUpdateCallback(adapter));
        }

        @VisibleForTesting
        List<Snake> getSnakes() {
            return this.f4977a;
        }
    }

    public static abstract class ItemCallback<T> {
        public abstract boolean a(Object obj, Object obj2);

        public abstract boolean b(Object obj, Object obj2);

        public Object c(Object obj, Object obj2) {
            return null;
        }
    }

    private static class PostponedUpdate {

        /* renamed from: a, reason: collision with root package name */
        int f4984a;

        /* renamed from: b, reason: collision with root package name */
        int f4985b;

        /* renamed from: c, reason: collision with root package name */
        boolean f4986c;

        public PostponedUpdate(int i2, int i3, boolean z) {
            this.f4984a = i2;
            this.f4985b = i3;
            this.f4986c = z;
        }
    }

    static class Range {

        /* renamed from: a, reason: collision with root package name */
        int f4987a;

        /* renamed from: b, reason: collision with root package name */
        int f4988b;

        /* renamed from: c, reason: collision with root package name */
        int f4989c;

        /* renamed from: d, reason: collision with root package name */
        int f4990d;

        public Range() {
        }

        public Range(int i2, int i3, int i4, int i5) {
            this.f4987a = i2;
            this.f4988b = i3;
            this.f4989c = i4;
            this.f4990d = i5;
        }
    }

    static class Snake {

        /* renamed from: a, reason: collision with root package name */
        int f4991a;

        /* renamed from: b, reason: collision with root package name */
        int f4992b;

        /* renamed from: c, reason: collision with root package name */
        int f4993c;

        /* renamed from: d, reason: collision with root package name */
        boolean f4994d;

        /* renamed from: e, reason: collision with root package name */
        boolean f4995e;

        Snake() {
        }
    }

    public static DiffResult a(Callback callback) {
        return b(callback, true);
    }

    public static DiffResult b(Callback callback, boolean z) {
        int e2 = callback.e();
        int d2 = callback.d();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new Range(0, e2, 0, d2));
        int abs = e2 + d2 + Math.abs(e2 - d2);
        int i2 = abs * 2;
        int[] iArr = new int[i2];
        int[] iArr2 = new int[i2];
        ArrayList arrayList3 = new ArrayList();
        while (!arrayList2.isEmpty()) {
            Range range = (Range) arrayList2.remove(arrayList2.size() - 1);
            Snake c2 = c(callback, range.f4987a, range.f4988b, range.f4989c, range.f4990d, iArr, iArr2, abs);
            if (c2 != null) {
                if (c2.f4993c > 0) {
                    arrayList.add(c2);
                }
                c2.f4991a += range.f4987a;
                c2.f4992b += range.f4989c;
                Range range2 = arrayList3.isEmpty() ? new Range() : (Range) arrayList3.remove(arrayList3.size() - 1);
                range2.f4987a = range.f4987a;
                range2.f4989c = range.f4989c;
                if (c2.f4995e) {
                    range2.f4988b = c2.f4991a;
                    range2.f4990d = c2.f4992b;
                } else if (c2.f4994d) {
                    range2.f4988b = c2.f4991a - 1;
                    range2.f4990d = c2.f4992b;
                } else {
                    range2.f4988b = c2.f4991a;
                    range2.f4990d = c2.f4992b - 1;
                }
                arrayList2.add(range2);
                if (!c2.f4995e) {
                    int i3 = c2.f4991a;
                    int i4 = c2.f4993c;
                    range.f4987a = i3 + i4;
                    range.f4989c = c2.f4992b + i4;
                } else if (c2.f4994d) {
                    int i5 = c2.f4991a;
                    int i6 = c2.f4993c;
                    range.f4987a = i5 + i6 + 1;
                    range.f4989c = c2.f4992b + i6;
                } else {
                    int i7 = c2.f4991a;
                    int i8 = c2.f4993c;
                    range.f4987a = i7 + i8;
                    range.f4989c = c2.f4992b + i8 + 1;
                }
                arrayList2.add(range);
            } else {
                arrayList3.add(range);
            }
        }
        Collections.sort(arrayList, f4976a);
        return new DiffResult(callback, arrayList, iArr, iArr2, z);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0042, code lost:
    
        if (r24[r13 - 1] < r24[r13 + r5]) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00b5, code lost:
    
        if (r25[r12 - 1] < r25[r12 + 1]) goto L50;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00de A[LOOP:4: B:54:0x00ca->B:58:0x00de, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00e9 A[EDGE_INSN: B:59:0x00e9->B:60:0x00e9 BREAK  A[LOOP:4: B:54:0x00ca->B:58:0x00de], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static androidx.recyclerview.widget.DiffUtil.Snake c(androidx.recyclerview.widget.DiffUtil.Callback r19, int r20, int r21, int r22, int r23, int[] r24, int[] r25, int r26) {
        /*
            Method dump skipped, instructions count: 299
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.DiffUtil.c(androidx.recyclerview.widget.DiffUtil$Callback, int, int, int, int, int[], int[], int):androidx.recyclerview.widget.DiffUtil$Snake");
    }
}
