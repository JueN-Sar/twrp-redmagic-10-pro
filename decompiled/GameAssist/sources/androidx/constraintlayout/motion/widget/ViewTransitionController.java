package androidx.constraintlayout.motion.widget;

import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.constraintlayout.motion.widget.ViewTransition;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.SharedValues;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes.dex */
public class ViewTransitionController {

    /* renamed from: a, reason: collision with root package name */
    private final MotionLayout f2391a;

    /* renamed from: c, reason: collision with root package name */
    private HashSet f2393c;

    /* renamed from: e, reason: collision with root package name */
    ArrayList f2395e;

    /* renamed from: b, reason: collision with root package name */
    private ArrayList f2392b = new ArrayList();

    /* renamed from: d, reason: collision with root package name */
    private String f2394d = "ViewTransitionController";

    /* renamed from: f, reason: collision with root package name */
    ArrayList f2396f = new ArrayList();

    public ViewTransitionController(MotionLayout motionLayout) {
        this.f2391a = motionLayout;
    }

    private void f(final ViewTransition viewTransition, final boolean z) {
        final int h2 = viewTransition.h();
        final int g2 = viewTransition.g();
        ConstraintLayout.getSharedValues().a(viewTransition.h(), new SharedValues.SharedValuesListener() { // from class: androidx.constraintlayout.motion.widget.ViewTransitionController.1
        });
    }

    private void j(ViewTransition viewTransition, View... viewArr) {
        int currentState = this.f2391a.getCurrentState();
        if (viewTransition.f2359e == 2) {
            viewTransition.c(this, this.f2391a, currentState, null, viewArr);
            return;
        }
        if (currentState != -1) {
            ConstraintSet q0 = this.f2391a.q0(currentState);
            if (q0 == null) {
                return;
            }
            viewTransition.c(this, this.f2391a, currentState, q0, viewArr);
            return;
        }
        Log.w(this.f2394d, "No support for ViewTransition within transition yet. Currently: " + this.f2391a.toString());
    }

    public void a(ViewTransition viewTransition) {
        this.f2392b.add(viewTransition);
        this.f2393c = null;
        if (viewTransition.i() == 4) {
            f(viewTransition, true);
        } else if (viewTransition.i() == 5) {
            f(viewTransition, false);
        }
    }

    void b(ViewTransition.Animate animate) {
        if (this.f2395e == null) {
            this.f2395e = new ArrayList();
        }
        this.f2395e.add(animate);
    }

    void c() {
        ArrayList arrayList = this.f2395e;
        if (arrayList == null) {
            return;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((ViewTransition.Animate) it.next()).a();
        }
        this.f2395e.removeAll(this.f2396f);
        this.f2396f.clear();
        if (this.f2395e.isEmpty()) {
            this.f2395e = null;
        }
    }

    boolean d(int i2, MotionController motionController) {
        Iterator it = this.f2392b.iterator();
        while (it.hasNext()) {
            ViewTransition viewTransition = (ViewTransition) it.next();
            if (viewTransition.e() == i2) {
                viewTransition.f2360f.a(motionController);
                return true;
            }
        }
        return false;
    }

    void e() {
        this.f2391a.invalidate();
    }

    void g(ViewTransition.Animate animate) {
        this.f2396f.add(animate);
    }

    void h(MotionEvent motionEvent) {
        int currentState = this.f2391a.getCurrentState();
        if (currentState == -1) {
            return;
        }
        if (this.f2393c == null) {
            this.f2393c = new HashSet();
            Iterator it = this.f2392b.iterator();
            while (it.hasNext()) {
                ViewTransition viewTransition = (ViewTransition) it.next();
                int childCount = this.f2391a.getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    View childAt = this.f2391a.getChildAt(i2);
                    if (viewTransition.k(childAt)) {
                        childAt.getId();
                        this.f2393c.add(childAt);
                    }
                }
            }
        }
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        Rect rect = new Rect();
        int action = motionEvent.getAction();
        ArrayList arrayList = this.f2395e;
        if (arrayList != null && !arrayList.isEmpty()) {
            Iterator it2 = this.f2395e.iterator();
            while (it2.hasNext()) {
                ((ViewTransition.Animate) it2.next()).d(action, x, y);
            }
        }
        if (action == 0 || action == 1) {
            ConstraintSet q0 = this.f2391a.q0(currentState);
            Iterator it3 = this.f2392b.iterator();
            while (it3.hasNext()) {
                ViewTransition viewTransition2 = (ViewTransition) it3.next();
                if (viewTransition2.m(action)) {
                    Iterator it4 = this.f2393c.iterator();
                    while (it4.hasNext()) {
                        View view = (View) it4.next();
                        if (viewTransition2.k(view)) {
                            view.getHitRect(rect);
                            if (rect.contains((int) x, (int) y)) {
                                viewTransition2.c(this, this.f2391a, currentState, q0, view);
                            }
                        }
                    }
                }
            }
        }
    }

    void i(int i2, View... viewArr) {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.f2392b.iterator();
        ViewTransition viewTransition = null;
        while (it.hasNext()) {
            ViewTransition viewTransition2 = (ViewTransition) it.next();
            if (viewTransition2.e() == i2) {
                for (View view : viewArr) {
                    if (viewTransition2.d(view)) {
                        arrayList.add(view);
                    }
                }
                if (!arrayList.isEmpty()) {
                    j(viewTransition2, (View[]) arrayList.toArray(new View[0]));
                    arrayList.clear();
                }
                viewTransition = viewTransition2;
            }
        }
        if (viewTransition == null) {
            Log.e(this.f2394d, " Could not find ViewTransition");
        }
    }
}
