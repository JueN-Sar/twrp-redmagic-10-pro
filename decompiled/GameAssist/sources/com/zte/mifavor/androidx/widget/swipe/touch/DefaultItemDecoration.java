package com.zte.mifavor.androidx.widget.swipe.touch;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/* loaded from: classes2.dex */
public class DefaultItemDecoration extends RecyclerView.ItemDecoration {

    /* renamed from: a, reason: collision with root package name */
    private final int f17242a;

    /* renamed from: b, reason: collision with root package name */
    private final int f17243b;

    /* renamed from: c, reason: collision with root package name */
    private final ColorDrawer f17244c;

    private void c(Canvas canvas, View view, int i2, int i3, int i4) {
        boolean h2 = h(0, i2, i3, i4);
        boolean j2 = j(0, i2, i3, i4);
        boolean g2 = g(0, i2, i3, i4);
        boolean i5 = i(0, i2, i3, i4);
        if (i3 == 1) {
            if (h2 && i5) {
                return;
            }
            if (g2) {
                this.f17244c.c(view, canvas);
                return;
            } else if (i5) {
                this.f17244c.b(view, canvas);
                return;
            } else {
                this.f17244c.b(view, canvas);
                this.f17244c.c(view, canvas);
                return;
            }
        }
        if (g2 && h2) {
            this.f17244c.c(view, canvas);
            this.f17244c.a(view, canvas);
            return;
        }
        if (g2 && j2) {
            this.f17244c.d(view, canvas);
            this.f17244c.c(view, canvas);
            return;
        }
        if (i5 && h2) {
            this.f17244c.b(view, canvas);
            this.f17244c.a(view, canvas);
            return;
        }
        if (i5 && j2) {
            this.f17244c.b(view, canvas);
            this.f17244c.d(view, canvas);
            return;
        }
        if (g2) {
            this.f17244c.d(view, canvas);
            this.f17244c.c(view, canvas);
            this.f17244c.a(view, canvas);
            return;
        }
        if (i5) {
            this.f17244c.b(view, canvas);
            this.f17244c.d(view, canvas);
            this.f17244c.a(view, canvas);
        } else if (h2) {
            this.f17244c.b(view, canvas);
            this.f17244c.c(view, canvas);
            this.f17244c.a(view, canvas);
        } else if (j2) {
            this.f17244c.b(view, canvas);
            this.f17244c.d(view, canvas);
            this.f17244c.c(view, canvas);
        } else {
            this.f17244c.b(view, canvas);
            this.f17244c.d(view, canvas);
            this.f17244c.c(view, canvas);
            this.f17244c.a(view, canvas);
        }
    }

    private void d(Canvas canvas, View view, int i2, int i3, int i4) {
        boolean h2 = h(1, i2, i3, i4);
        boolean j2 = j(1, i2, i3, i4);
        boolean g2 = g(1, i2, i3, i4);
        boolean i5 = i(1, i2, i3, i4);
        if (i3 == 1) {
            if (h2 && j2) {
                return;
            }
            if (h2) {
                this.f17244c.a(view, canvas);
                return;
            } else if (j2) {
                this.f17244c.d(view, canvas);
                return;
            } else {
                this.f17244c.d(view, canvas);
                this.f17244c.a(view, canvas);
                return;
            }
        }
        if (h2 && g2) {
            this.f17244c.c(view, canvas);
            this.f17244c.a(view, canvas);
            return;
        }
        if (h2 && i5) {
            this.f17244c.b(view, canvas);
            this.f17244c.a(view, canvas);
            return;
        }
        if (j2 && g2) {
            this.f17244c.d(view, canvas);
            this.f17244c.c(view, canvas);
            return;
        }
        if (j2 && i5) {
            this.f17244c.b(view, canvas);
            this.f17244c.d(view, canvas);
            return;
        }
        if (h2) {
            this.f17244c.b(view, canvas);
            this.f17244c.c(view, canvas);
            this.f17244c.a(view, canvas);
            return;
        }
        if (j2) {
            this.f17244c.b(view, canvas);
            this.f17244c.d(view, canvas);
            this.f17244c.c(view, canvas);
        } else if (g2) {
            this.f17244c.d(view, canvas);
            this.f17244c.c(view, canvas);
            this.f17244c.a(view, canvas);
        } else if (i5) {
            this.f17244c.b(view, canvas);
            this.f17244c.d(view, canvas);
            this.f17244c.a(view, canvas);
        } else {
            this.f17244c.b(view, canvas);
            this.f17244c.d(view, canvas);
            this.f17244c.c(view, canvas);
            this.f17244c.a(view, canvas);
        }
    }

    private int e(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) layoutManager).y2();
        }
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            return ((StaggeredGridLayoutManager) layoutManager).z2();
        }
        return 1;
    }

    private int f(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof GridLayoutManager) {
            return ((GridLayoutManager) layoutManager).h3();
        }
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            return ((StaggeredGridLayoutManager) layoutManager).B2();
        }
        return 1;
    }

    private boolean g(int i2, int i3, int i4, int i5) {
        return i2 == 1 ? i4 == 1 || i3 % i4 == 0 : i3 < i4;
    }

    private boolean h(int i2, int i3, int i4, int i5) {
        return i2 == 1 ? i3 < i4 : i4 == 1 || i3 % i4 == 0;
    }

    private boolean i(int i2, int i3, int i4, int i5) {
        if (i2 == 1) {
            return i4 == 1 || (i3 + 1) % i4 == 0;
        }
        if (i4 == 1) {
            return i3 + 1 == i5;
        }
        int i6 = i5 % i4;
        int i7 = ((i5 - i6) / i4) + (i6 > 0 ? 1 : 0);
        int i8 = i3 + 1;
        int i9 = i8 % i4;
        return i9 == 0 ? i7 == i8 / i4 : i7 == ((i8 - i9) / i4) + 1;
    }

    private boolean j(int i2, int i3, int i4, int i5) {
        if (i2 != 1) {
            return i4 == 1 || (i3 + 1) % i4 == 0;
        }
        if (i4 == 1) {
            return i3 + 1 == i5;
        }
        int i6 = i5 % i4;
        int i7 = ((i5 - i6) / i4) + (i6 > 0 ? 1 : 0);
        int i8 = i3 + 1;
        int i9 = i8 % i4;
        return i9 == 0 ? i7 == i8 / i4 : i7 == ((i8 - i9) / i4) + 1;
    }

    private void k(Rect rect, int i2, int i3, int i4) {
        boolean h2 = h(0, i2, i3, i4);
        boolean j2 = j(0, i2, i3, i4);
        boolean g2 = g(0, i2, i3, i4);
        boolean i5 = i(0, i2, i3, i4);
        if (i3 == 1) {
            if (g2 && i5) {
                rect.set(0, 0, 0, 0);
                return;
            }
            if (g2) {
                rect.set(0, 0, this.f17242a, 0);
                return;
            } else if (i5) {
                rect.set(this.f17242a, 0, 0, 0);
                return;
            } else {
                int i6 = this.f17242a;
                rect.set(i6, 0, i6, 0);
                return;
            }
        }
        if (g2 && h2) {
            rect.set(0, 0, this.f17242a, this.f17243b);
            return;
        }
        if (g2 && j2) {
            rect.set(0, this.f17243b, this.f17242a, 0);
            return;
        }
        if (i5 && h2) {
            rect.set(this.f17242a, 0, 0, this.f17243b);
            return;
        }
        if (i5 && j2) {
            rect.set(this.f17242a, this.f17243b, 0, 0);
            return;
        }
        if (g2) {
            int i7 = this.f17243b;
            rect.set(0, i7, this.f17242a, i7);
            return;
        }
        if (i5) {
            int i8 = this.f17242a;
            int i9 = this.f17243b;
            rect.set(i8, i9, 0, i9);
        } else if (h2) {
            int i10 = this.f17242a;
            rect.set(i10, 0, i10, this.f17243b);
        } else if (j2) {
            int i11 = this.f17242a;
            rect.set(i11, this.f17243b, i11, 0);
        } else {
            int i12 = this.f17242a;
            int i13 = this.f17243b;
            rect.set(i12, i13, i12, i13);
        }
    }

    private void l(Rect rect, int i2, int i3, int i4) {
        boolean h2 = h(1, i2, i3, i4);
        boolean j2 = j(1, i2, i3, i4);
        boolean g2 = g(1, i2, i3, i4);
        boolean i5 = i(1, i2, i3, i4);
        if (i3 == 1) {
            if (h2 && j2) {
                rect.set(0, 0, 0, 0);
                return;
            }
            if (h2) {
                rect.set(0, 0, 0, this.f17243b);
                return;
            } else if (j2) {
                rect.set(0, this.f17243b, 0, 0);
                return;
            } else {
                int i6 = this.f17243b;
                rect.set(0, i6, 0, i6);
                return;
            }
        }
        if (h2 && g2) {
            rect.set(0, 0, this.f17242a, this.f17243b);
            return;
        }
        if (h2 && i5) {
            rect.set(this.f17242a, 0, 0, this.f17243b);
            return;
        }
        if (j2 && g2) {
            rect.set(0, this.f17243b, this.f17242a, 0);
            return;
        }
        if (j2 && i5) {
            rect.set(this.f17242a, this.f17243b, 0, 0);
            return;
        }
        if (h2) {
            int i7 = this.f17242a;
            rect.set(i7, 0, i7, this.f17243b);
            return;
        }
        if (j2) {
            int i8 = this.f17242a;
            rect.set(i8, this.f17243b, i8, 0);
            return;
        }
        if (g2) {
            int i9 = this.f17243b;
            rect.set(0, i9, this.f17242a, i9);
        } else if (i5) {
            int i10 = this.f17242a;
            int i11 = this.f17243b;
            rect.set(i10, i11, 0, i11);
        } else {
            int i12 = this.f17242a;
            int i13 = this.f17243b;
            rect.set(i12, i13, i12, i13);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (!(layoutManager instanceof LinearLayoutManager)) {
            if (layoutManager instanceof StaggeredGridLayoutManager) {
                int i2 = this.f17242a;
                int i3 = this.f17243b;
                rect.set(i2, i3, i2, i3);
                return;
            }
            return;
        }
        int e2 = e(layoutManager);
        int g0 = recyclerView.g0(view);
        int f2 = f(layoutManager);
        int f3 = layoutManager.f();
        if (e2 == 1) {
            l(rect, g0, f2, f3);
        } else {
            k(rect, g0, f2, f3);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        int e2 = e(layoutManager);
        int f2 = f(layoutManager);
        int P = layoutManager.P();
        if (layoutManager instanceof LinearLayoutManager) {
            canvas.save();
            for (int i2 = 0; i2 < P; i2++) {
                View O = layoutManager.O(i2);
                int g0 = recyclerView.g0(O);
                if (e2 == 1) {
                    d(canvas, O, g0, f2, P);
                } else {
                    c(canvas, O, g0, f2, P);
                }
            }
            canvas.restore();
            return;
        }
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            canvas.save();
            for (int i3 = 0; i3 < P; i3++) {
                View O2 = layoutManager.O(i3);
                this.f17244c.b(O2, canvas);
                this.f17244c.d(O2, canvas);
                this.f17244c.c(O2, canvas);
                this.f17244c.a(O2, canvas);
            }
            canvas.restore();
        }
    }
}
