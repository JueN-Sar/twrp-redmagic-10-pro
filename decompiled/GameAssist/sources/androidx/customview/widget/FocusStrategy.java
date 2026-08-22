package androidx.customview.widget;

import android.graphics.Rect;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/* loaded from: classes.dex */
class FocusStrategy {

    public interface BoundsAdapter<T> {
        void a(Object obj, Rect rect);
    }

    public interface CollectionAdapter<T, V> {
        Object a(Object obj, int i2);

        int b(Object obj);
    }

    private static class SequentialComparator<T> implements Comparator<T> {

        /* renamed from: c, reason: collision with root package name */
        private final Rect f3598c = new Rect();

        /* renamed from: h, reason: collision with root package name */
        private final Rect f3599h = new Rect();

        /* renamed from: i, reason: collision with root package name */
        private final boolean f3600i;

        /* renamed from: j, reason: collision with root package name */
        private final BoundsAdapter f3601j;

        SequentialComparator(boolean z, BoundsAdapter boundsAdapter) {
            this.f3600i = z;
            this.f3601j = boundsAdapter;
        }

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            Rect rect = this.f3598c;
            Rect rect2 = this.f3599h;
            this.f3601j.a(obj, rect);
            this.f3601j.a(obj2, rect2);
            int i2 = rect.top;
            int i3 = rect2.top;
            if (i2 < i3) {
                return -1;
            }
            if (i2 > i3) {
                return 1;
            }
            int i4 = rect.left;
            int i5 = rect2.left;
            if (i4 < i5) {
                return this.f3600i ? 1 : -1;
            }
            if (i4 > i5) {
                return this.f3600i ? -1 : 1;
            }
            int i6 = rect.bottom;
            int i7 = rect2.bottom;
            if (i6 < i7) {
                return -1;
            }
            if (i6 > i7) {
                return 1;
            }
            int i8 = rect.right;
            int i9 = rect2.right;
            if (i8 < i9) {
                return this.f3600i ? 1 : -1;
            }
            if (i8 > i9) {
                return this.f3600i ? -1 : 1;
            }
            return 0;
        }
    }

    private static boolean a(int i2, Rect rect, Rect rect2, Rect rect3) {
        boolean b2 = b(i2, rect, rect2);
        if (b(i2, rect, rect3) || !b2) {
            return false;
        }
        return !j(i2, rect, rect3) || i2 == 17 || i2 == 66 || k(i2, rect, rect2) < m(i2, rect, rect3);
    }

    private static boolean b(int i2, Rect rect, Rect rect2) {
        if (i2 != 17) {
            if (i2 != 33) {
                if (i2 != 66) {
                    if (i2 != 130) {
                        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                    }
                }
            }
            return rect2.right >= rect.left && rect2.left <= rect.right;
        }
        return rect2.bottom >= rect.top && rect2.top <= rect.bottom;
    }

    public static Object c(Object obj, CollectionAdapter collectionAdapter, BoundsAdapter boundsAdapter, Object obj2, Rect rect, int i2) {
        Rect rect2 = new Rect(rect);
        if (i2 == 17) {
            rect2.offset(rect.width() + 1, 0);
        } else if (i2 == 33) {
            rect2.offset(0, rect.height() + 1);
        } else if (i2 == 66) {
            rect2.offset(-(rect.width() + 1), 0);
        } else {
            if (i2 != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            rect2.offset(0, -(rect.height() + 1));
        }
        int b2 = collectionAdapter.b(obj);
        Rect rect3 = new Rect();
        Object obj3 = null;
        for (int i3 = 0; i3 < b2; i3++) {
            Object a2 = collectionAdapter.a(obj, i3);
            if (a2 != obj2) {
                boundsAdapter.a(a2, rect3);
                if (h(i2, rect, rect3, rect2)) {
                    rect2.set(rect3);
                    obj3 = a2;
                }
            }
        }
        return obj3;
    }

    public static Object d(Object obj, CollectionAdapter collectionAdapter, BoundsAdapter boundsAdapter, Object obj2, int i2, boolean z, boolean z2) {
        int b2 = collectionAdapter.b(obj);
        ArrayList arrayList = new ArrayList(b2);
        for (int i3 = 0; i3 < b2; i3++) {
            arrayList.add(collectionAdapter.a(obj, i3));
        }
        Collections.sort(arrayList, new SequentialComparator(z, boundsAdapter));
        if (i2 == 1) {
            return f(obj2, arrayList, z2);
        }
        if (i2 == 2) {
            return e(obj2, arrayList, z2);
        }
        throw new IllegalArgumentException("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD}.");
    }

    private static Object e(Object obj, ArrayList arrayList, boolean z) {
        int size = arrayList.size();
        int lastIndexOf = (obj == null ? -1 : arrayList.lastIndexOf(obj)) + 1;
        if (lastIndexOf < size) {
            return arrayList.get(lastIndexOf);
        }
        if (!z || size <= 0) {
            return null;
        }
        return arrayList.get(0);
    }

    private static Object f(Object obj, ArrayList arrayList, boolean z) {
        int size = arrayList.size();
        int indexOf = (obj == null ? size : arrayList.indexOf(obj)) - 1;
        if (indexOf >= 0) {
            return arrayList.get(indexOf);
        }
        if (!z || size <= 0) {
            return null;
        }
        return arrayList.get(size - 1);
    }

    private static int g(int i2, int i3) {
        return (i2 * 13 * i2) + (i3 * i3);
    }

    private static boolean h(int i2, Rect rect, Rect rect2, Rect rect3) {
        if (!i(rect, rect2, i2)) {
            return false;
        }
        if (i(rect, rect3, i2) && !a(i2, rect, rect2, rect3)) {
            return !a(i2, rect, rect3, rect2) && g(k(i2, rect, rect2), o(i2, rect, rect2)) < g(k(i2, rect, rect3), o(i2, rect, rect3));
        }
        return true;
    }

    private static boolean i(Rect rect, Rect rect2, int i2) {
        if (i2 == 17) {
            int i3 = rect.right;
            int i4 = rect2.right;
            return (i3 > i4 || rect.left >= i4) && rect.left > rect2.left;
        }
        if (i2 == 33) {
            int i5 = rect.bottom;
            int i6 = rect2.bottom;
            return (i5 > i6 || rect.top >= i6) && rect.top > rect2.top;
        }
        if (i2 == 66) {
            int i7 = rect.left;
            int i8 = rect2.left;
            return (i7 < i8 || rect.right <= i8) && rect.right < rect2.right;
        }
        if (i2 != 130) {
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
        }
        int i9 = rect.top;
        int i10 = rect2.top;
        return (i9 < i10 || rect.bottom <= i10) && rect.bottom < rect2.bottom;
    }

    private static boolean j(int i2, Rect rect, Rect rect2) {
        if (i2 == 17) {
            return rect.left >= rect2.right;
        }
        if (i2 == 33) {
            return rect.top >= rect2.bottom;
        }
        if (i2 == 66) {
            return rect.right <= rect2.left;
        }
        if (i2 == 130) {
            return rect.bottom <= rect2.top;
        }
        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
    }

    private static int k(int i2, Rect rect, Rect rect2) {
        return Math.max(0, l(i2, rect, rect2));
    }

    private static int l(int i2, Rect rect, Rect rect2) {
        int i3;
        int i4;
        if (i2 == 17) {
            i3 = rect.left;
            i4 = rect2.right;
        } else if (i2 == 33) {
            i3 = rect.top;
            i4 = rect2.bottom;
        } else if (i2 == 66) {
            i3 = rect2.left;
            i4 = rect.right;
        } else {
            if (i2 != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            i3 = rect2.top;
            i4 = rect.bottom;
        }
        return i3 - i4;
    }

    private static int m(int i2, Rect rect, Rect rect2) {
        return Math.max(1, n(i2, rect, rect2));
    }

    private static int n(int i2, Rect rect, Rect rect2) {
        int i3;
        int i4;
        if (i2 == 17) {
            i3 = rect.left;
            i4 = rect2.left;
        } else if (i2 == 33) {
            i3 = rect.top;
            i4 = rect2.top;
        } else if (i2 == 66) {
            i3 = rect2.right;
            i4 = rect.right;
        } else {
            if (i2 != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            i3 = rect2.bottom;
            i4 = rect.bottom;
        }
        return i3 - i4;
    }

    private static int o(int i2, Rect rect, Rect rect2) {
        if (i2 != 17) {
            if (i2 != 33) {
                if (i2 != 66) {
                    if (i2 != 130) {
                        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                    }
                }
            }
            return Math.abs((rect.left + (rect.width() / 2)) - (rect2.left + (rect2.width() / 2)));
        }
        return Math.abs((rect.top + (rect.height() / 2)) - (rect2.top + (rect2.height() / 2)));
    }
}
