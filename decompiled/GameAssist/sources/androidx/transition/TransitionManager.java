package androidx.transition;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.annotation.VisibleForTesting;
import androidx.collection.ArrayMap;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class TransitionManager {

    /* renamed from: a, reason: collision with root package name */
    private static Transition f5555a = new AutoTransition();

    /* renamed from: b, reason: collision with root package name */
    private static ThreadLocal f5556b = new ThreadLocal();

    /* renamed from: c, reason: collision with root package name */
    static ArrayList f5557c = new ArrayList();

    private static class MultiListener implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {

        /* renamed from: c, reason: collision with root package name */
        Transition f5558c;

        /* renamed from: h, reason: collision with root package name */
        ViewGroup f5559h;

        MultiListener(Transition transition, ViewGroup viewGroup) {
            this.f5558c = transition;
            this.f5559h = viewGroup;
        }

        private void a() {
            this.f5559h.getViewTreeObserver().removeOnPreDrawListener(this);
            this.f5559h.removeOnAttachStateChangeListener(this);
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            a();
            if (!TransitionManager.f5557c.remove(this.f5559h)) {
                return true;
            }
            final ArrayMap<ViewGroup, ArrayList<Transition>> runningTransitions = TransitionManager.getRunningTransitions();
            ArrayList arrayList = (ArrayList) runningTransitions.get(this.f5559h);
            ArrayList arrayList2 = null;
            if (arrayList == null) {
                arrayList = new ArrayList();
                runningTransitions.put(this.f5559h, arrayList);
            } else if (arrayList.size() > 0) {
                arrayList2 = new ArrayList(arrayList);
            }
            arrayList.add(this.f5558c);
            this.f5558c.a(new TransitionListenerAdapter() { // from class: androidx.transition.TransitionManager.MultiListener.1
                @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
                public void f(Transition transition) {
                    ((ArrayList) runningTransitions.get(MultiListener.this.f5559h)).remove(transition);
                    transition.b0(this);
                }
            });
            this.f5558c.m(this.f5559h, false);
            if (arrayList2 != null) {
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    ((Transition) it.next()).d0(this.f5559h);
                }
            }
            this.f5558c.Z(this.f5559h);
            return true;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            a();
            TransitionManager.f5557c.remove(this.f5559h);
            ArrayList arrayList = (ArrayList) TransitionManager.getRunningTransitions().get(this.f5559h);
            if (arrayList != null && arrayList.size() > 0) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((Transition) it.next()).d0(this.f5559h);
                }
            }
            this.f5558c.n(true);
        }
    }

    public static void a(ViewGroup viewGroup, Transition transition) {
        if (f5557c.contains(viewGroup) || !viewGroup.isLaidOut()) {
            return;
        }
        f5557c.add(viewGroup);
        if (transition == null) {
            transition = f5555a;
        }
        Transition clone = transition.clone();
        c(viewGroup, clone);
        Scene.c(viewGroup, null);
        b(viewGroup, clone);
    }

    private static void b(ViewGroup viewGroup, Transition transition) {
        if (transition == null || viewGroup == null) {
            return;
        }
        MultiListener multiListener = new MultiListener(transition, viewGroup);
        viewGroup.addOnAttachStateChangeListener(multiListener);
        viewGroup.getViewTreeObserver().addOnPreDrawListener(multiListener);
    }

    private static void c(ViewGroup viewGroup, Transition transition) {
        ArrayList arrayList = (ArrayList) getRunningTransitions().get(viewGroup);
        if (arrayList != null && arrayList.size() > 0) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((Transition) it.next()).Y(viewGroup);
            }
        }
        if (transition != null) {
            transition.m(viewGroup, true);
        }
        Scene b2 = Scene.b(viewGroup);
        if (b2 != null) {
            b2.a();
        }
    }

    @VisibleForTesting
    static ArrayMap<ViewGroup, ArrayList<Transition>> getRunningTransitions() {
        ArrayMap<ViewGroup, ArrayList<Transition>> arrayMap;
        WeakReference weakReference = (WeakReference) f5556b.get();
        if (weakReference != null && (arrayMap = (ArrayMap) weakReference.get()) != null) {
            return arrayMap;
        }
        ArrayMap<ViewGroup, ArrayList<Transition>> arrayMap2 = new ArrayMap<>();
        f5556b.set(new WeakReference(arrayMap2));
        return arrayMap2;
    }
}
