package androidx.transition;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.RestrictTo;
import androidx.core.os.CancellationSignal;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransitionImpl;
import androidx.transition.Transition;
import java.util.ArrayList;
import java.util.List;

@RestrictTo
/* loaded from: classes.dex */
public class FragmentTransitionSupport extends FragmentTransitionImpl {
    private static boolean w(Transition transition) {
        return (FragmentTransitionImpl.i(transition.D()) && FragmentTransitionImpl.i(transition.E()) && FragmentTransitionImpl.i(transition.F())) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void x(Runnable runnable, Transition transition, Runnable runnable2) {
        if (runnable != null) {
            runnable.run();
        } else {
            transition.h();
            runnable2.run();
        }
    }

    @Override // androidx.fragment.app.FragmentTransitionImpl
    public void a(Object obj, View view) {
        if (obj != null) {
            ((Transition) obj).b(view);
        }
    }

    @Override // androidx.fragment.app.FragmentTransitionImpl
    public void b(Object obj, ArrayList arrayList) {
        Transition transition = (Transition) obj;
        if (transition == null) {
            return;
        }
        int i2 = 0;
        if (transition instanceof TransitionSet) {
            TransitionSet transitionSet = (TransitionSet) transition;
            int v0 = transitionSet.v0();
            while (i2 < v0) {
                b(transitionSet.u0(i2), arrayList);
                i2++;
            }
            return;
        }
        if (w(transition) || !FragmentTransitionImpl.i(transition.H())) {
            return;
        }
        int size = arrayList.size();
        while (i2 < size) {
            transition.b((View) arrayList.get(i2));
            i2++;
        }
    }

    @Override // androidx.fragment.app.FragmentTransitionImpl
    public void c(ViewGroup viewGroup, Object obj) {
        TransitionManager.a(viewGroup, (Transition) obj);
    }

    @Override // androidx.fragment.app.FragmentTransitionImpl
    public boolean e(Object obj) {
        return obj instanceof Transition;
    }

    @Override // androidx.fragment.app.FragmentTransitionImpl
    public Object f(Object obj) {
        if (obj != null) {
            return ((Transition) obj).clone();
        }
        return null;
    }

    @Override // androidx.fragment.app.FragmentTransitionImpl
    public Object j(Object obj, Object obj2, Object obj3) {
        Transition transition = (Transition) obj;
        Transition transition2 = (Transition) obj2;
        Transition transition3 = (Transition) obj3;
        if (transition != null && transition2 != null) {
            transition = new TransitionSet().s0(transition).s0(transition2).B0(1);
        } else if (transition == null) {
            transition = transition2 != null ? transition2 : null;
        }
        if (transition3 == null) {
            return transition;
        }
        TransitionSet transitionSet = new TransitionSet();
        if (transition != null) {
            transitionSet.s0(transition);
        }
        transitionSet.s0(transition3);
        return transitionSet;
    }

    @Override // androidx.fragment.app.FragmentTransitionImpl
    public Object k(Object obj, Object obj2, Object obj3) {
        TransitionSet transitionSet = new TransitionSet();
        if (obj != null) {
            transitionSet.s0((Transition) obj);
        }
        if (obj2 != null) {
            transitionSet.s0((Transition) obj2);
        }
        if (obj3 != null) {
            transitionSet.s0((Transition) obj3);
        }
        return transitionSet;
    }

    @Override // androidx.fragment.app.FragmentTransitionImpl
    public void m(Object obj, final View view, final ArrayList arrayList) {
        ((Transition) obj).a(new Transition.TransitionListener() { // from class: androidx.transition.FragmentTransitionSupport.2
            @Override // androidx.transition.Transition.TransitionListener
            public void b(Transition transition) {
            }

            @Override // androidx.transition.Transition.TransitionListener
            public void c(Transition transition) {
                transition.b0(this);
                transition.a(this);
            }

            @Override // androidx.transition.Transition.TransitionListener
            public void d(Transition transition) {
            }

            @Override // androidx.transition.Transition.TransitionListener
            public void f(Transition transition) {
                transition.b0(this);
                view.setVisibility(8);
                int size = arrayList.size();
                for (int i2 = 0; i2 < size; i2++) {
                    ((View) arrayList.get(i2)).setVisibility(0);
                }
            }

            @Override // androidx.transition.Transition.TransitionListener
            public void g(Transition transition) {
            }
        });
    }

    @Override // androidx.fragment.app.FragmentTransitionImpl
    public void n(Object obj, final Object obj2, final ArrayList arrayList, final Object obj3, final ArrayList arrayList2, final Object obj4, final ArrayList arrayList3) {
        ((Transition) obj).a(new TransitionListenerAdapter() { // from class: androidx.transition.FragmentTransitionSupport.3
            @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
            public void c(Transition transition) {
                Object obj5 = obj2;
                if (obj5 != null) {
                    FragmentTransitionSupport.this.y(obj5, arrayList, null);
                }
                Object obj6 = obj3;
                if (obj6 != null) {
                    FragmentTransitionSupport.this.y(obj6, arrayList2, null);
                }
                Object obj7 = obj4;
                if (obj7 != null) {
                    FragmentTransitionSupport.this.y(obj7, arrayList3, null);
                }
            }

            @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
            public void f(Transition transition) {
                transition.b0(this);
            }
        });
    }

    @Override // androidx.fragment.app.FragmentTransitionImpl
    public void o(Object obj, final Rect rect) {
        if (obj != null) {
            ((Transition) obj).i0(new Transition.EpicenterCallback() { // from class: androidx.transition.FragmentTransitionSupport.5
                @Override // androidx.transition.Transition.EpicenterCallback
                public Rect a(Transition transition) {
                    Rect rect2 = rect;
                    if (rect2 == null || rect2.isEmpty()) {
                        return null;
                    }
                    return rect;
                }
            });
        }
    }

    @Override // androidx.fragment.app.FragmentTransitionImpl
    public void p(Object obj, View view) {
        if (view != null) {
            final Rect rect = new Rect();
            h(view, rect);
            ((Transition) obj).i0(new Transition.EpicenterCallback() { // from class: androidx.transition.FragmentTransitionSupport.1
                @Override // androidx.transition.Transition.EpicenterCallback
                public Rect a(Transition transition) {
                    return rect;
                }
            });
        }
    }

    @Override // androidx.fragment.app.FragmentTransitionImpl
    public void q(Fragment fragment, Object obj, CancellationSignal cancellationSignal, Runnable runnable) {
        z(fragment, obj, cancellationSignal, null, runnable);
    }

    @Override // androidx.fragment.app.FragmentTransitionImpl
    public void s(Object obj, View view, ArrayList arrayList) {
        TransitionSet transitionSet = (TransitionSet) obj;
        List H = transitionSet.H();
        H.clear();
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            FragmentTransitionImpl.d(H, (View) arrayList.get(i2));
        }
        H.add(view);
        arrayList.add(view);
        b(transitionSet, arrayList);
    }

    @Override // androidx.fragment.app.FragmentTransitionImpl
    public void t(Object obj, ArrayList arrayList, ArrayList arrayList2) {
        TransitionSet transitionSet = (TransitionSet) obj;
        if (transitionSet != null) {
            transitionSet.H().clear();
            transitionSet.H().addAll(arrayList2);
            y(transitionSet, arrayList, arrayList2);
        }
    }

    @Override // androidx.fragment.app.FragmentTransitionImpl
    public Object u(Object obj) {
        if (obj == null) {
            return null;
        }
        TransitionSet transitionSet = new TransitionSet();
        transitionSet.s0((Transition) obj);
        return transitionSet;
    }

    public void y(Object obj, ArrayList arrayList, ArrayList arrayList2) {
        Transition transition = (Transition) obj;
        int i2 = 0;
        if (transition instanceof TransitionSet) {
            TransitionSet transitionSet = (TransitionSet) transition;
            int v0 = transitionSet.v0();
            while (i2 < v0) {
                y(transitionSet.u0(i2), arrayList, arrayList2);
                i2++;
            }
            return;
        }
        if (w(transition)) {
            return;
        }
        List H = transition.H();
        if (H.size() == arrayList.size() && H.containsAll(arrayList)) {
            int size = arrayList2 == null ? 0 : arrayList2.size();
            while (i2 < size) {
                transition.b((View) arrayList2.get(i2));
                i2++;
            }
            for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
                transition.c0((View) arrayList.get(size2));
            }
        }
    }

    public void z(Fragment fragment, Object obj, CancellationSignal cancellationSignal, final Runnable runnable, final Runnable runnable2) {
        final Transition transition = (Transition) obj;
        cancellationSignal.c(new CancellationSignal.OnCancelListener() { // from class: androidx.transition.a
            @Override // androidx.core.os.CancellationSignal.OnCancelListener
            public final void onCancel() {
                FragmentTransitionSupport.x(runnable, transition, runnable2);
            }
        });
        transition.a(new Transition.TransitionListener() { // from class: androidx.transition.FragmentTransitionSupport.4
            @Override // androidx.transition.Transition.TransitionListener
            public void b(Transition transition2) {
            }

            @Override // androidx.transition.Transition.TransitionListener
            public void c(Transition transition2) {
            }

            @Override // androidx.transition.Transition.TransitionListener
            public void d(Transition transition2) {
            }

            @Override // androidx.transition.Transition.TransitionListener
            public void f(Transition transition2) {
                runnable2.run();
            }

            @Override // androidx.transition.Transition.TransitionListener
            public void g(Transition transition2) {
            }
        });
    }
}
