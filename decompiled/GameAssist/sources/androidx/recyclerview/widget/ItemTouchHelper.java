package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.Interpolator;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.R;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class ItemTouchHelper extends RecyclerView.ItemDecoration implements RecyclerView.OnChildAttachStateChangeListener {
    private ItemTouchHelperGestureListener A;
    private Rect C;
    private long D;

    /* renamed from: d, reason: collision with root package name */
    float f5029d;

    /* renamed from: e, reason: collision with root package name */
    float f5030e;

    /* renamed from: f, reason: collision with root package name */
    private float f5031f;

    /* renamed from: g, reason: collision with root package name */
    private float f5032g;

    /* renamed from: h, reason: collision with root package name */
    float f5033h;

    /* renamed from: i, reason: collision with root package name */
    float f5034i;

    /* renamed from: j, reason: collision with root package name */
    private float f5035j;

    /* renamed from: k, reason: collision with root package name */
    private float f5036k;

    /* renamed from: m, reason: collision with root package name */
    Callback f5038m;

    /* renamed from: o, reason: collision with root package name */
    int f5040o;

    /* renamed from: q, reason: collision with root package name */
    private int f5042q;

    /* renamed from: r, reason: collision with root package name */
    RecyclerView f5043r;
    VelocityTracker t;
    private List u;
    private List v;
    GestureDetectorCompat z;

    /* renamed from: a, reason: collision with root package name */
    final List f5026a = new ArrayList();

    /* renamed from: b, reason: collision with root package name */
    private final float[] f5027b = new float[2];

    /* renamed from: c, reason: collision with root package name */
    RecyclerView.ViewHolder f5028c = null;

    /* renamed from: l, reason: collision with root package name */
    int f5037l = -1;

    /* renamed from: n, reason: collision with root package name */
    private int f5039n = 0;

    /* renamed from: p, reason: collision with root package name */
    List f5041p = new ArrayList();

    /* renamed from: s, reason: collision with root package name */
    final Runnable f5044s = new Runnable() { // from class: androidx.recyclerview.widget.ItemTouchHelper.1
        @Override // java.lang.Runnable
        public void run() {
            ItemTouchHelper itemTouchHelper = ItemTouchHelper.this;
            if (itemTouchHelper.f5028c == null || !itemTouchHelper.t()) {
                return;
            }
            ItemTouchHelper itemTouchHelper2 = ItemTouchHelper.this;
            RecyclerView.ViewHolder viewHolder = itemTouchHelper2.f5028c;
            if (viewHolder != null) {
                itemTouchHelper2.o(viewHolder);
            }
            ItemTouchHelper itemTouchHelper3 = ItemTouchHelper.this;
            itemTouchHelper3.f5043r.removeCallbacks(itemTouchHelper3.f5044s);
            ViewCompat.a0(ItemTouchHelper.this.f5043r, this);
        }
    };
    private RecyclerView.ChildDrawingOrderCallback w = null;
    View x = null;
    int y = -1;
    private final RecyclerView.OnItemTouchListener B = new RecyclerView.OnItemTouchListener() { // from class: androidx.recyclerview.widget.ItemTouchHelper.2
        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            int findPointerIndex;
            RecoverAnimation h2;
            ItemTouchHelper.this.z.a(motionEvent);
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                ItemTouchHelper.this.f5037l = motionEvent.getPointerId(0);
                ItemTouchHelper.this.f5029d = motionEvent.getX();
                ItemTouchHelper.this.f5030e = motionEvent.getY();
                ItemTouchHelper.this.p();
                ItemTouchHelper itemTouchHelper = ItemTouchHelper.this;
                if (itemTouchHelper.f5028c == null && (h2 = itemTouchHelper.h(motionEvent)) != null) {
                    ItemTouchHelper itemTouchHelper2 = ItemTouchHelper.this;
                    itemTouchHelper2.f5029d -= h2.f5065p;
                    itemTouchHelper2.f5030e -= h2.f5066q;
                    itemTouchHelper2.g(h2.f5060k, true);
                    if (ItemTouchHelper.this.f5026a.remove(h2.f5060k.f5252a)) {
                        ItemTouchHelper itemTouchHelper3 = ItemTouchHelper.this;
                        itemTouchHelper3.f5038m.c(itemTouchHelper3.f5043r, h2.f5060k);
                    }
                    ItemTouchHelper.this.u(h2.f5060k, h2.f5061l);
                    ItemTouchHelper itemTouchHelper4 = ItemTouchHelper.this;
                    itemTouchHelper4.z(motionEvent, itemTouchHelper4.f5040o, 0);
                }
            } else if (actionMasked == 3 || actionMasked == 1) {
                ItemTouchHelper itemTouchHelper5 = ItemTouchHelper.this;
                itemTouchHelper5.f5037l = -1;
                itemTouchHelper5.u(null, 0);
            } else {
                int i2 = ItemTouchHelper.this.f5037l;
                if (i2 != -1 && (findPointerIndex = motionEvent.findPointerIndex(i2)) >= 0) {
                    ItemTouchHelper.this.e(actionMasked, motionEvent, findPointerIndex);
                }
            }
            VelocityTracker velocityTracker = ItemTouchHelper.this.t;
            if (velocityTracker != null) {
                velocityTracker.addMovement(motionEvent);
            }
            return ItemTouchHelper.this.f5028c != null;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onRequestDisallowInterceptTouchEvent(boolean z) {
            if (z) {
                ItemTouchHelper.this.u(null, 0);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            ItemTouchHelper.this.z.a(motionEvent);
            VelocityTracker velocityTracker = ItemTouchHelper.this.t;
            if (velocityTracker != null) {
                velocityTracker.addMovement(motionEvent);
            }
            if (ItemTouchHelper.this.f5037l == -1) {
                return;
            }
            int actionMasked = motionEvent.getActionMasked();
            int findPointerIndex = motionEvent.findPointerIndex(ItemTouchHelper.this.f5037l);
            if (findPointerIndex >= 0) {
                ItemTouchHelper.this.e(actionMasked, motionEvent, findPointerIndex);
            }
            ItemTouchHelper itemTouchHelper = ItemTouchHelper.this;
            RecyclerView.ViewHolder viewHolder = itemTouchHelper.f5028c;
            if (viewHolder == null) {
                return;
            }
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    if (findPointerIndex >= 0) {
                        itemTouchHelper.z(motionEvent, itemTouchHelper.f5040o, findPointerIndex);
                        ItemTouchHelper.this.o(viewHolder);
                        ItemTouchHelper itemTouchHelper2 = ItemTouchHelper.this;
                        itemTouchHelper2.f5043r.removeCallbacks(itemTouchHelper2.f5044s);
                        ItemTouchHelper.this.f5044s.run();
                        ItemTouchHelper.this.f5043r.invalidate();
                        return;
                    }
                    return;
                }
                if (actionMasked != 3) {
                    if (actionMasked != 6) {
                        return;
                    }
                    int actionIndex = motionEvent.getActionIndex();
                    int pointerId = motionEvent.getPointerId(actionIndex);
                    ItemTouchHelper itemTouchHelper3 = ItemTouchHelper.this;
                    if (pointerId == itemTouchHelper3.f5037l) {
                        itemTouchHelper3.f5037l = motionEvent.getPointerId(actionIndex == 0 ? 1 : 0);
                        ItemTouchHelper itemTouchHelper4 = ItemTouchHelper.this;
                        itemTouchHelper4.z(motionEvent, itemTouchHelper4.f5040o, actionIndex);
                        return;
                    }
                    return;
                }
                VelocityTracker velocityTracker2 = itemTouchHelper.t;
                if (velocityTracker2 != null) {
                    velocityTracker2.clear();
                }
            }
            ItemTouchHelper.this.u(null, 0);
            ItemTouchHelper.this.f5037l = -1;
        }
    };

    /* renamed from: androidx.recyclerview.widget.ItemTouchHelper$5, reason: invalid class name */
    class AnonymousClass5 implements RecyclerView.ChildDrawingOrderCallback {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ ItemTouchHelper f5050a;

        @Override // androidx.recyclerview.widget.RecyclerView.ChildDrawingOrderCallback
        public int a(int i2, int i3) {
            ItemTouchHelper itemTouchHelper = this.f5050a;
            View view = itemTouchHelper.x;
            if (view == null) {
                return i3;
            }
            int i4 = itemTouchHelper.y;
            if (i4 == -1) {
                i4 = itemTouchHelper.f5043r.indexOfChild(view);
                this.f5050a.y = i4;
            }
            return i3 == i2 + (-1) ? i4 : i3 < i4 ? i3 : i3 + 1;
        }
    }

    public static abstract class Callback {

        /* renamed from: b, reason: collision with root package name */
        private static final Interpolator f5051b = new Interpolator() { // from class: androidx.recyclerview.widget.ItemTouchHelper.Callback.1
            @Override // android.animation.TimeInterpolator
            public float getInterpolation(float f2) {
                return f2 * f2 * f2 * f2 * f2;
            }
        };

        /* renamed from: c, reason: collision with root package name */
        private static final Interpolator f5052c = new Interpolator() { // from class: androidx.recyclerview.widget.ItemTouchHelper.Callback.2
            @Override // android.animation.TimeInterpolator
            public float getInterpolation(float f2) {
                float f3 = f2 - 1.0f;
                return (f3 * f3 * f3 * f3 * f3) + 1.0f;
            }
        };

        /* renamed from: a, reason: collision with root package name */
        private int f5053a = -1;

        public static int e(int i2, int i3) {
            int i4;
            int i5 = i2 & 789516;
            if (i5 == 0) {
                return i2;
            }
            int i6 = i2 & (~i5);
            if (i3 == 0) {
                i4 = i5 << 2;
            } else {
                int i7 = i5 << 1;
                i6 |= (-789517) & i7;
                i4 = (i7 & 789516) << 2;
            }
            return i6 | i4;
        }

        private int i(RecyclerView recyclerView) {
            if (this.f5053a == -1) {
                this.f5053a = recyclerView.getResources().getDimensionPixelSize(R.dimen.item_touch_helper_max_drag_scroll_per_frame);
            }
            return this.f5053a;
        }

        public static int s(int i2, int i3) {
            return i3 << (i2 * 8);
        }

        public static int t(int i2, int i3) {
            return s(2, i2) | s(1, i3) | s(0, i3 | i2);
        }

        public void A(RecyclerView.ViewHolder viewHolder, int i2) {
            if (viewHolder != null) {
                ItemTouchUIUtilImpl.f5072a.b(viewHolder.f5252a);
            }
        }

        public abstract void B(RecyclerView.ViewHolder viewHolder, int i2);

        public boolean a(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
            return true;
        }

        public RecyclerView.ViewHolder b(RecyclerView.ViewHolder viewHolder, List list, int i2, int i3) {
            int bottom;
            int abs;
            int top;
            int abs2;
            int left;
            int abs3;
            int right;
            int abs4;
            int width = viewHolder.f5252a.getWidth() + i2;
            int height = viewHolder.f5252a.getHeight() + i3;
            int left2 = i2 - viewHolder.f5252a.getLeft();
            int top2 = i3 - viewHolder.f5252a.getTop();
            int size = list.size();
            RecyclerView.ViewHolder viewHolder2 = null;
            int i4 = -1;
            for (int i5 = 0; i5 < size; i5++) {
                RecyclerView.ViewHolder viewHolder3 = (RecyclerView.ViewHolder) list.get(i5);
                if (left2 > 0 && (right = viewHolder3.f5252a.getRight() - width) < 0 && viewHolder3.f5252a.getRight() > viewHolder.f5252a.getRight() && (abs4 = Math.abs(right)) > i4) {
                    viewHolder2 = viewHolder3;
                    i4 = abs4;
                }
                if (left2 < 0 && (left = viewHolder3.f5252a.getLeft() - i2) > 0 && viewHolder3.f5252a.getLeft() < viewHolder.f5252a.getLeft() && (abs3 = Math.abs(left)) > i4) {
                    viewHolder2 = viewHolder3;
                    i4 = abs3;
                }
                if (top2 < 0 && (top = viewHolder3.f5252a.getTop() - i3) > 0 && viewHolder3.f5252a.getTop() < viewHolder.f5252a.getTop() && (abs2 = Math.abs(top)) > i4) {
                    viewHolder2 = viewHolder3;
                    i4 = abs2;
                }
                if (top2 > 0 && (bottom = viewHolder3.f5252a.getBottom() - height) < 0 && viewHolder3.f5252a.getBottom() > viewHolder.f5252a.getBottom() && (abs = Math.abs(bottom)) > i4) {
                    viewHolder2 = viewHolder3;
                    i4 = abs;
                }
            }
            return viewHolder2;
        }

        public void c(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            ItemTouchUIUtilImpl.f5072a.a(viewHolder.f5252a);
        }

        public int d(int i2, int i3) {
            int i4;
            int i5 = i2 & 3158064;
            if (i5 == 0) {
                return i2;
            }
            int i6 = i2 & (~i5);
            if (i3 == 0) {
                i4 = i5 >> 2;
            } else {
                int i7 = i5 >> 1;
                i6 |= (-3158065) & i7;
                i4 = (3158064 & i7) >> 2;
            }
            return i4 | i6;
        }

        final int f(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return d(k(recyclerView, viewHolder), ViewCompat.v(recyclerView));
        }

        public long g(RecyclerView recyclerView, int i2, float f2, float f3) {
            RecyclerView.ItemAnimator itemAnimator = recyclerView.getItemAnimator();
            return itemAnimator == null ? i2 == 8 ? 200L : 250L : i2 == 8 ? itemAnimator.n() : itemAnimator.o();
        }

        public int h() {
            return 0;
        }

        public float j(RecyclerView.ViewHolder viewHolder) {
            return 0.5f;
        }

        public abstract int k(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder);

        public float l(float f2) {
            return f2;
        }

        public float m(RecyclerView.ViewHolder viewHolder) {
            return 0.5f;
        }

        public float n(float f2) {
            return f2;
        }

        boolean o(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return (f(recyclerView, viewHolder) & 16711680) != 0;
        }

        public int p(RecyclerView recyclerView, int i2, int i3, int i4, long j2) {
            int signum = (int) (((int) (((int) Math.signum(i3)) * i(recyclerView) * f5052c.getInterpolation(Math.min(1.0f, (Math.abs(i3) * 1.0f) / i2)))) * f5051b.getInterpolation(j2 <= 2000 ? j2 / 2000.0f : 1.0f));
            return signum == 0 ? i3 > 0 ? 1 : -1 : signum;
        }

        public boolean q() {
            return true;
        }

        public boolean r() {
            return true;
        }

        public void u(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float f2, float f3, int i2, boolean z) {
            ItemTouchUIUtilImpl.f5072a.c(canvas, recyclerView, viewHolder.f5252a, f2, f3, i2, z);
        }

        public void v(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float f2, float f3, int i2, boolean z) {
            ItemTouchUIUtilImpl.f5072a.d(canvas, recyclerView, viewHolder.f5252a, f2, f3, i2, z);
        }

        void w(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, List list, int i2, float f2, float f3) {
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                RecoverAnimation recoverAnimation = (RecoverAnimation) list.get(i3);
                recoverAnimation.e();
                int save = canvas.save();
                u(canvas, recyclerView, recoverAnimation.f5060k, recoverAnimation.f5065p, recoverAnimation.f5066q, recoverAnimation.f5061l, false);
                canvas.restoreToCount(save);
            }
            if (viewHolder != null) {
                int save2 = canvas.save();
                u(canvas, recyclerView, viewHolder, f2, f3, i2, true);
                canvas.restoreToCount(save2);
            }
        }

        void x(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, List list, int i2, float f2, float f3) {
            int size = list.size();
            boolean z = false;
            for (int i3 = 0; i3 < size; i3++) {
                RecoverAnimation recoverAnimation = (RecoverAnimation) list.get(i3);
                int save = canvas.save();
                v(canvas, recyclerView, recoverAnimation.f5060k, recoverAnimation.f5065p, recoverAnimation.f5066q, recoverAnimation.f5061l, false);
                canvas.restoreToCount(save);
            }
            if (viewHolder != null) {
                int save2 = canvas.save();
                v(canvas, recyclerView, viewHolder, f2, f3, i2, true);
                canvas.restoreToCount(save2);
            }
            for (int i4 = size - 1; i4 >= 0; i4--) {
                RecoverAnimation recoverAnimation2 = (RecoverAnimation) list.get(i4);
                boolean z2 = recoverAnimation2.f5068s;
                if (z2 && !recoverAnimation2.f5064o) {
                    list.remove(i4);
                } else if (!z2) {
                    z = true;
                }
            }
            if (z) {
                recyclerView.invalidate();
            }
        }

        public abstract boolean y(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2);

        /* JADX WARN: Multi-variable type inference failed */
        public void z(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int i2, RecyclerView.ViewHolder viewHolder2, int i3, int i4, int i5) {
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof ViewDropHandler) {
                ((ViewDropHandler) layoutManager).d(viewHolder.f5252a, viewHolder2.f5252a, i4, i5);
                return;
            }
            if (layoutManager.q()) {
                if (layoutManager.W(viewHolder2.f5252a) <= recyclerView.getPaddingLeft()) {
                    recyclerView.l1(i3);
                }
                if (layoutManager.Z(viewHolder2.f5252a) >= recyclerView.getWidth() - recyclerView.getPaddingRight()) {
                    recyclerView.l1(i3);
                }
            }
            if (layoutManager.r()) {
                if (layoutManager.a0(viewHolder2.f5252a) <= recyclerView.getPaddingTop()) {
                    recyclerView.l1(i3);
                }
                if (layoutManager.U(viewHolder2.f5252a) >= recyclerView.getHeight() - recyclerView.getPaddingBottom()) {
                    recyclerView.l1(i3);
                }
            }
        }
    }

    private class ItemTouchHelperGestureListener extends GestureDetector.SimpleOnGestureListener {

        /* renamed from: a, reason: collision with root package name */
        private boolean f5054a = true;

        ItemTouchHelperGestureListener() {
        }

        void a() {
            this.f5054a = false;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
            View i2;
            RecyclerView.ViewHolder h0;
            if (!this.f5054a || (i2 = ItemTouchHelper.this.i(motionEvent)) == null || (h0 = ItemTouchHelper.this.f5043r.h0(i2)) == null) {
                return;
            }
            ItemTouchHelper itemTouchHelper = ItemTouchHelper.this;
            if (itemTouchHelper.f5038m.o(itemTouchHelper.f5043r, h0)) {
                int pointerId = motionEvent.getPointerId(0);
                int i3 = ItemTouchHelper.this.f5037l;
                if (pointerId == i3) {
                    int findPointerIndex = motionEvent.findPointerIndex(i3);
                    float x = motionEvent.getX(findPointerIndex);
                    float y = motionEvent.getY(findPointerIndex);
                    ItemTouchHelper itemTouchHelper2 = ItemTouchHelper.this;
                    itemTouchHelper2.f5029d = x;
                    itemTouchHelper2.f5030e = y;
                    itemTouchHelper2.f5034i = 0.0f;
                    itemTouchHelper2.f5033h = 0.0f;
                    if (itemTouchHelper2.f5038m.r()) {
                        ItemTouchHelper.this.u(h0, 2);
                    }
                }
            }
        }
    }

    private static class RecoverAnimation implements Animator.AnimatorListener {

        /* renamed from: c, reason: collision with root package name */
        final float f5056c;

        /* renamed from: h, reason: collision with root package name */
        final float f5057h;

        /* renamed from: i, reason: collision with root package name */
        final float f5058i;

        /* renamed from: j, reason: collision with root package name */
        final float f5059j;

        /* renamed from: k, reason: collision with root package name */
        final RecyclerView.ViewHolder f5060k;

        /* renamed from: l, reason: collision with root package name */
        final int f5061l;

        /* renamed from: m, reason: collision with root package name */
        private final ValueAnimator f5062m;

        /* renamed from: n, reason: collision with root package name */
        final int f5063n;

        /* renamed from: o, reason: collision with root package name */
        boolean f5064o;

        /* renamed from: p, reason: collision with root package name */
        float f5065p;

        /* renamed from: q, reason: collision with root package name */
        float f5066q;

        /* renamed from: r, reason: collision with root package name */
        boolean f5067r = false;

        /* renamed from: s, reason: collision with root package name */
        boolean f5068s = false;
        private float t;

        RecoverAnimation(RecyclerView.ViewHolder viewHolder, int i2, int i3, float f2, float f3, float f4, float f5) {
            this.f5061l = i3;
            this.f5063n = i2;
            this.f5060k = viewHolder;
            this.f5056c = f2;
            this.f5057h = f3;
            this.f5058i = f4;
            this.f5059j = f5;
            ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            this.f5062m = ofFloat;
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: androidx.recyclerview.widget.ItemTouchHelper.RecoverAnimation.1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    RecoverAnimation.this.c(valueAnimator.getAnimatedFraction());
                }
            });
            ofFloat.setTarget(viewHolder.f5252a);
            ofFloat.addListener(this);
            c(0.0f);
        }

        public void a() {
            this.f5062m.cancel();
        }

        public void b(long j2) {
            this.f5062m.setDuration(j2);
        }

        public void c(float f2) {
            this.t = f2;
        }

        public void d() {
            this.f5060k.H(false);
            this.f5062m.start();
        }

        public void e() {
            float f2 = this.f5056c;
            float f3 = this.f5058i;
            if (f2 == f3) {
                this.f5065p = this.f5060k.f5252a.getTranslationX();
            } else {
                this.f5065p = f2 + (this.t * (f3 - f2));
            }
            float f4 = this.f5057h;
            float f5 = this.f5059j;
            if (f4 == f5) {
                this.f5066q = this.f5060k.f5252a.getTranslationY();
            } else {
                this.f5066q = f4 + (this.t * (f5 - f4));
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            c(1.0f);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (!this.f5068s) {
                this.f5060k.H(true);
            }
            this.f5068s = true;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }
    }

    public static abstract class SimpleCallback extends Callback {

        /* renamed from: d, reason: collision with root package name */
        private int f5070d;

        /* renamed from: e, reason: collision with root package name */
        private int f5071e;

        public int C(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return this.f5071e;
        }

        public int D(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return this.f5070d;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public int k(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return Callback.t(C(recyclerView, viewHolder), D(recyclerView, viewHolder));
        }
    }

    public interface ViewDropHandler {
        void d(View view, View view2, int i2, int i3);
    }

    public ItemTouchHelper(Callback callback) {
        this.f5038m = callback;
    }

    private void c() {
    }

    private int d(RecyclerView.ViewHolder viewHolder, int i2) {
        if ((i2 & 12) == 0) {
            return 0;
        }
        int i3 = this.f5033h > 0.0f ? 8 : 4;
        VelocityTracker velocityTracker = this.t;
        if (velocityTracker != null && this.f5037l > -1) {
            velocityTracker.computeCurrentVelocity(1000, this.f5038m.n(this.f5032g));
            float xVelocity = this.t.getXVelocity(this.f5037l);
            float yVelocity = this.t.getYVelocity(this.f5037l);
            int i4 = xVelocity > 0.0f ? 8 : 4;
            float abs = Math.abs(xVelocity);
            if ((i4 & i2) != 0 && i3 == i4 && abs >= this.f5038m.l(this.f5031f) && abs > Math.abs(yVelocity)) {
                return i4;
            }
        }
        float width = this.f5043r.getWidth() * this.f5038m.m(viewHolder);
        if ((i2 & i3) == 0 || Math.abs(this.f5033h) <= width) {
            return 0;
        }
        return i3;
    }

    private void destroyCallbacks() {
        this.f5043r.Z0(this);
        this.f5043r.b1(this.B);
        this.f5043r.a1(this);
        for (int size = this.f5041p.size() - 1; size >= 0; size--) {
            this.f5038m.c(this.f5043r, ((RecoverAnimation) this.f5041p.get(0)).f5060k);
        }
        this.f5041p.clear();
        this.x = null;
        this.y = -1;
        r();
        x();
    }

    private int f(RecyclerView.ViewHolder viewHolder, int i2) {
        if ((i2 & 3) == 0) {
            return 0;
        }
        int i3 = this.f5034i > 0.0f ? 2 : 1;
        VelocityTracker velocityTracker = this.t;
        if (velocityTracker != null && this.f5037l > -1) {
            velocityTracker.computeCurrentVelocity(1000, this.f5038m.n(this.f5032g));
            float xVelocity = this.t.getXVelocity(this.f5037l);
            float yVelocity = this.t.getYVelocity(this.f5037l);
            int i4 = yVelocity > 0.0f ? 2 : 1;
            float abs = Math.abs(yVelocity);
            if ((i4 & i2) != 0 && i4 == i3 && abs >= this.f5038m.l(this.f5031f) && abs > Math.abs(xVelocity)) {
                return i4;
            }
        }
        float height = this.f5043r.getHeight() * this.f5038m.m(viewHolder);
        if ((i2 & i3) == 0 || Math.abs(this.f5034i) <= height) {
            return 0;
        }
        return i3;
    }

    private List j(RecyclerView.ViewHolder viewHolder) {
        RecyclerView.ViewHolder viewHolder2 = viewHolder;
        List list = this.u;
        if (list == null) {
            this.u = new ArrayList();
            this.v = new ArrayList();
        } else {
            list.clear();
            this.v.clear();
        }
        int h2 = this.f5038m.h();
        int round = Math.round(this.f5035j + this.f5033h) - h2;
        int round2 = Math.round(this.f5036k + this.f5034i) - h2;
        int i2 = h2 * 2;
        int width = viewHolder2.f5252a.getWidth() + round + i2;
        int height = viewHolder2.f5252a.getHeight() + round2 + i2;
        int i3 = (round + width) / 2;
        int i4 = (round2 + height) / 2;
        RecyclerView.LayoutManager layoutManager = this.f5043r.getLayoutManager();
        int P = layoutManager.P();
        int i5 = 0;
        while (i5 < P) {
            View O = layoutManager.O(i5);
            if (O != viewHolder2.f5252a && O.getBottom() >= round2 && O.getTop() <= height && O.getRight() >= round && O.getLeft() <= width) {
                RecyclerView.ViewHolder h0 = this.f5043r.h0(O);
                if (this.f5038m.a(this.f5043r, this.f5028c, h0)) {
                    int abs = Math.abs(i3 - ((O.getLeft() + O.getRight()) / 2));
                    int abs2 = Math.abs(i4 - ((O.getTop() + O.getBottom()) / 2));
                    int i6 = (abs * abs) + (abs2 * abs2);
                    int size = this.u.size();
                    int i7 = 0;
                    for (int i8 = 0; i8 < size && i6 > ((Integer) this.v.get(i8)).intValue(); i8++) {
                        i7++;
                    }
                    this.u.add(i7, h0);
                    this.v.add(i7, Integer.valueOf(i6));
                }
            }
            i5++;
            viewHolder2 = viewHolder;
        }
        return this.u;
    }

    private RecyclerView.ViewHolder k(MotionEvent motionEvent) {
        View i2;
        RecyclerView.LayoutManager layoutManager = this.f5043r.getLayoutManager();
        int i3 = this.f5037l;
        if (i3 == -1) {
            return null;
        }
        int findPointerIndex = motionEvent.findPointerIndex(i3);
        float x = motionEvent.getX(findPointerIndex) - this.f5029d;
        float y = motionEvent.getY(findPointerIndex) - this.f5030e;
        float abs = Math.abs(x);
        float abs2 = Math.abs(y);
        int i4 = this.f5042q;
        if (abs < i4 && abs2 < i4) {
            return null;
        }
        if (abs > abs2 && layoutManager.q()) {
            return null;
        }
        if ((abs2 <= abs || !layoutManager.r()) && (i2 = i(motionEvent)) != null) {
            return this.f5043r.h0(i2);
        }
        return null;
    }

    private void l(float[] fArr) {
        if ((this.f5040o & 12) != 0) {
            fArr[0] = (this.f5035j + this.f5033h) - this.f5028c.f5252a.getLeft();
        } else {
            fArr[0] = this.f5028c.f5252a.getTranslationX();
        }
        if ((this.f5040o & 3) != 0) {
            fArr[1] = (this.f5036k + this.f5034i) - this.f5028c.f5252a.getTop();
        } else {
            fArr[1] = this.f5028c.f5252a.getTranslationY();
        }
    }

    private static boolean n(View view, float f2, float f3, float f4, float f5) {
        return f2 >= f4 && f2 <= f4 + ((float) view.getWidth()) && f3 >= f5 && f3 <= f5 + ((float) view.getHeight());
    }

    private void r() {
        VelocityTracker velocityTracker = this.t;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.t = null;
        }
    }

    private void setupCallbacks() {
        this.f5042q = ViewConfiguration.get(this.f5043r.getContext()).getScaledTouchSlop();
        this.f5043r.h(this);
        this.f5043r.k(this.B);
        this.f5043r.j(this);
        w();
    }

    private void w() {
        this.A = new ItemTouchHelperGestureListener();
        this.z = new GestureDetectorCompat(this.f5043r.getContext(), this.A);
    }

    private void x() {
        ItemTouchHelperGestureListener itemTouchHelperGestureListener = this.A;
        if (itemTouchHelperGestureListener != null) {
            itemTouchHelperGestureListener.a();
            this.A = null;
        }
        if (this.z != null) {
            this.z = null;
        }
    }

    private int y(RecyclerView.ViewHolder viewHolder) {
        if (this.f5039n == 2) {
            return 0;
        }
        int k2 = this.f5038m.k(this.f5043r, viewHolder);
        int d2 = (this.f5038m.d(k2, ViewCompat.v(this.f5043r)) & 65280) >> 8;
        if (d2 == 0) {
            return 0;
        }
        int i2 = (k2 & 65280) >> 8;
        if (Math.abs(this.f5033h) > Math.abs(this.f5034i)) {
            int d3 = d(viewHolder, d2);
            if (d3 > 0) {
                return (i2 & d3) == 0 ? Callback.e(d3, ViewCompat.v(this.f5043r)) : d3;
            }
            int f2 = f(viewHolder, d2);
            if (f2 > 0) {
                return f2;
            }
        } else {
            int f3 = f(viewHolder, d2);
            if (f3 > 0) {
                return f3;
            }
            int d4 = d(viewHolder, d2);
            if (d4 > 0) {
                return (i2 & d4) == 0 ? Callback.e(d4, ViewCompat.v(this.f5043r)) : d4;
            }
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
    public void a(View view) {
        s(view);
        RecyclerView.ViewHolder h0 = this.f5043r.h0(view);
        if (h0 == null) {
            return;
        }
        RecyclerView.ViewHolder viewHolder = this.f5028c;
        if (viewHolder != null && h0 == viewHolder) {
            u(null, 0);
            return;
        }
        g(h0, false);
        if (this.f5026a.remove(h0.f5252a)) {
            this.f5038m.c(this.f5043r, h0);
        }
    }

    public void attachToRecyclerView(RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.f5043r;
        if (recyclerView2 == recyclerView) {
            return;
        }
        if (recyclerView2 != null) {
            destroyCallbacks();
        }
        this.f5043r = recyclerView;
        if (recyclerView != null) {
            Resources resources = recyclerView.getResources();
            this.f5031f = resources.getDimension(R.dimen.item_touch_helper_swipe_escape_velocity);
            this.f5032g = resources.getDimension(R.dimen.item_touch_helper_swipe_escape_max_velocity);
            setupCallbacks();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
    public void b(View view) {
    }

    void e(int i2, MotionEvent motionEvent, int i3) {
        RecyclerView.ViewHolder k2;
        int f2;
        if (this.f5028c != null || i2 != 2 || this.f5039n == 2 || !this.f5038m.q() || this.f5043r.getScrollState() == 1 || (k2 = k(motionEvent)) == null || (f2 = (this.f5038m.f(this.f5043r, k2) & 65280) >> 8) == 0) {
            return;
        }
        float x = motionEvent.getX(i3);
        float y = motionEvent.getY(i3);
        float f3 = x - this.f5029d;
        float f4 = y - this.f5030e;
        float abs = Math.abs(f3);
        float abs2 = Math.abs(f4);
        int i4 = this.f5042q;
        if (abs >= i4 || abs2 >= i4) {
            if (abs > abs2) {
                if (f3 < 0.0f && (f2 & 4) == 0) {
                    return;
                }
                if (f3 > 0.0f && (f2 & 8) == 0) {
                    return;
                }
            } else {
                if (f4 < 0.0f && (f2 & 1) == 0) {
                    return;
                }
                if (f4 > 0.0f && (f2 & 2) == 0) {
                    return;
                }
            }
            this.f5034i = 0.0f;
            this.f5033h = 0.0f;
            this.f5037l = motionEvent.getPointerId(0);
            u(k2, 1);
        }
    }

    void g(RecyclerView.ViewHolder viewHolder, boolean z) {
        for (int size = this.f5041p.size() - 1; size >= 0; size--) {
            RecoverAnimation recoverAnimation = (RecoverAnimation) this.f5041p.get(size);
            if (recoverAnimation.f5060k == viewHolder) {
                recoverAnimation.f5067r |= z;
                if (!recoverAnimation.f5068s) {
                    recoverAnimation.a();
                }
                this.f5041p.remove(size);
                return;
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        rect.setEmpty();
    }

    RecoverAnimation h(MotionEvent motionEvent) {
        if (this.f5041p.isEmpty()) {
            return null;
        }
        View i2 = i(motionEvent);
        for (int size = this.f5041p.size() - 1; size >= 0; size--) {
            RecoverAnimation recoverAnimation = (RecoverAnimation) this.f5041p.get(size);
            if (recoverAnimation.f5060k.f5252a == i2) {
                return recoverAnimation;
            }
        }
        return null;
    }

    View i(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        RecyclerView.ViewHolder viewHolder = this.f5028c;
        if (viewHolder != null) {
            View view = viewHolder.f5252a;
            if (n(view, x, y, this.f5035j + this.f5033h, this.f5036k + this.f5034i)) {
                return view;
            }
        }
        for (int size = this.f5041p.size() - 1; size >= 0; size--) {
            RecoverAnimation recoverAnimation = (RecoverAnimation) this.f5041p.get(size);
            View view2 = recoverAnimation.f5060k.f5252a;
            if (n(view2, x, y, recoverAnimation.f5065p, recoverAnimation.f5066q)) {
                return view2;
            }
        }
        return this.f5043r.S(x, y);
    }

    boolean m() {
        int size = this.f5041p.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (!((RecoverAnimation) this.f5041p.get(i2)).f5068s) {
                return true;
            }
        }
        return false;
    }

    void o(RecyclerView.ViewHolder viewHolder) {
        if (!this.f5043r.isLayoutRequested() && this.f5039n == 2) {
            float j2 = this.f5038m.j(viewHolder);
            int i2 = (int) (this.f5035j + this.f5033h);
            int i3 = (int) (this.f5036k + this.f5034i);
            if (Math.abs(i3 - viewHolder.f5252a.getTop()) >= viewHolder.f5252a.getHeight() * j2 || Math.abs(i2 - viewHolder.f5252a.getLeft()) >= viewHolder.f5252a.getWidth() * j2) {
                List j3 = j(viewHolder);
                if (j3.size() == 0) {
                    return;
                }
                RecyclerView.ViewHolder b2 = this.f5038m.b(viewHolder, j3, i2, i3);
                if (b2 == null) {
                    this.u.clear();
                    this.v.clear();
                    return;
                }
                int k2 = b2.k();
                int k3 = viewHolder.k();
                if (this.f5038m.y(this.f5043r, viewHolder, b2)) {
                    this.f5038m.z(this.f5043r, viewHolder, k3, b2, k2, i2, i3);
                }
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        float f2;
        float f3;
        this.y = -1;
        if (this.f5028c != null) {
            l(this.f5027b);
            float[] fArr = this.f5027b;
            float f4 = fArr[0];
            f3 = fArr[1];
            f2 = f4;
        } else {
            f2 = 0.0f;
            f3 = 0.0f;
        }
        this.f5038m.w(canvas, recyclerView, this.f5028c, this.f5041p, this.f5039n, f2, f3);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        float f2;
        float f3;
        if (this.f5028c != null) {
            l(this.f5027b);
            float[] fArr = this.f5027b;
            float f4 = fArr[0];
            f3 = fArr[1];
            f2 = f4;
        } else {
            f2 = 0.0f;
            f3 = 0.0f;
        }
        this.f5038m.x(canvas, recyclerView, this.f5028c, this.f5041p, this.f5039n, f2, f3);
    }

    void p() {
        VelocityTracker velocityTracker = this.t;
        if (velocityTracker != null) {
            velocityTracker.recycle();
        }
        this.t = VelocityTracker.obtain();
    }

    void q(final RecoverAnimation recoverAnimation, final int i2) {
        this.f5043r.post(new Runnable() { // from class: androidx.recyclerview.widget.ItemTouchHelper.4
            @Override // java.lang.Runnable
            public void run() {
                RecyclerView recyclerView = ItemTouchHelper.this.f5043r;
                if (recyclerView == null || !recyclerView.isAttachedToWindow()) {
                    return;
                }
                RecoverAnimation recoverAnimation2 = recoverAnimation;
                if (recoverAnimation2.f5067r || recoverAnimation2.f5060k.k() == -1) {
                    return;
                }
                RecyclerView.ItemAnimator itemAnimator = ItemTouchHelper.this.f5043r.getItemAnimator();
                if ((itemAnimator == null || !itemAnimator.q(null)) && !ItemTouchHelper.this.m()) {
                    ItemTouchHelper.this.f5038m.B(recoverAnimation.f5060k, i2);
                } else {
                    ItemTouchHelper.this.f5043r.post(this);
                }
            }
        });
    }

    void s(View view) {
        if (view == this.x) {
            this.x = null;
            if (this.w != null) {
                this.f5043r.setChildDrawingOrderCallback(null);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x00c1, code lost:
    
        if (r1 > 0) goto L37;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0100 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00fd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    boolean t() {
        /*
            Method dump skipped, instructions count: 277
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.ItemTouchHelper.t():boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0136  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void u(androidx.recyclerview.widget.RecyclerView.ViewHolder r24, int r25) {
        /*
            Method dump skipped, instructions count: 334
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.ItemTouchHelper.u(androidx.recyclerview.widget.RecyclerView$ViewHolder, int):void");
    }

    public void v(RecyclerView.ViewHolder viewHolder) {
        if (!this.f5038m.o(this.f5043r, viewHolder)) {
            Log.e("ItemTouchHelper", "Start drag has been called but dragging is not enabled");
            return;
        }
        if (viewHolder.f5252a.getParent() != this.f5043r) {
            Log.e("ItemTouchHelper", "Start drag has been called with a view holder which is not a child of the RecyclerView which is controlled by this ItemTouchHelper.");
            return;
        }
        p();
        this.f5034i = 0.0f;
        this.f5033h = 0.0f;
        u(viewHolder, 2);
    }

    void z(MotionEvent motionEvent, int i2, int i3) {
        float x = motionEvent.getX(i3);
        float y = motionEvent.getY(i3);
        float f2 = x - this.f5029d;
        this.f5033h = f2;
        this.f5034i = y - this.f5030e;
        if ((i2 & 4) == 0) {
            this.f5033h = Math.max(0.0f, f2);
        }
        if ((i2 & 8) == 0) {
            this.f5033h = Math.min(0.0f, this.f5033h);
        }
        if ((i2 & 1) == 0) {
            this.f5034i = Math.max(0.0f, this.f5034i);
        }
        if ((i2 & 2) == 0) {
            this.f5034i = Math.min(0.0f, this.f5034i);
        }
    }
}
