package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.collection.ArrayMap;
import androidx.core.app.SharedElementCallback;
import androidx.core.os.CancellationSignal;
import androidx.core.util.Preconditions;
import androidx.core.view.OneShotPreDrawListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewGroupCompat;
import androidx.fragment.app.FragmentAnim;
import androidx.fragment.app.SpecialEffectsController;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
class DefaultSpecialEffectsController extends SpecialEffectsController {

    /* renamed from: androidx.fragment.app.DefaultSpecialEffectsController$10, reason: invalid class name */
    static /* synthetic */ class AnonymousClass10 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f3925a;

        static {
            int[] iArr = new int[SpecialEffectsController.Operation.State.values().length];
            f3925a = iArr;
            try {
                iArr[SpecialEffectsController.Operation.State.GONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3925a[SpecialEffectsController.Operation.State.INVISIBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3925a[SpecialEffectsController.Operation.State.REMOVED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3925a[SpecialEffectsController.Operation.State.VISIBLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private static class AnimationInfo extends SpecialEffectsInfo {

        /* renamed from: c, reason: collision with root package name */
        private boolean f3960c;

        /* renamed from: d, reason: collision with root package name */
        private boolean f3961d;

        /* renamed from: e, reason: collision with root package name */
        private FragmentAnim.AnimationOrAnimator f3962e;

        AnimationInfo(SpecialEffectsController.Operation operation, CancellationSignal cancellationSignal, boolean z) {
            super(operation, cancellationSignal);
            this.f3961d = false;
            this.f3960c = z;
        }

        FragmentAnim.AnimationOrAnimator e(Context context) {
            if (this.f3961d) {
                return this.f3962e;
            }
            FragmentAnim.AnimationOrAnimator b2 = FragmentAnim.b(context, b().f(), b().e() == SpecialEffectsController.Operation.State.VISIBLE, this.f3960c);
            this.f3962e = b2;
            this.f3961d = true;
            return b2;
        }
    }

    private static class SpecialEffectsInfo {

        /* renamed from: a, reason: collision with root package name */
        private final SpecialEffectsController.Operation f3963a;

        /* renamed from: b, reason: collision with root package name */
        private final CancellationSignal f3964b;

        SpecialEffectsInfo(SpecialEffectsController.Operation operation, CancellationSignal cancellationSignal) {
            this.f3963a = operation;
            this.f3964b = cancellationSignal;
        }

        void a() {
            this.f3963a.d(this.f3964b);
        }

        SpecialEffectsController.Operation b() {
            return this.f3963a;
        }

        CancellationSignal c() {
            return this.f3964b;
        }

        boolean d() {
            SpecialEffectsController.Operation.State state;
            SpecialEffectsController.Operation.State e2 = SpecialEffectsController.Operation.State.e(this.f3963a.f().O);
            SpecialEffectsController.Operation.State e3 = this.f3963a.e();
            return e2 == e3 || !(e2 == (state = SpecialEffectsController.Operation.State.VISIBLE) || e3 == state);
        }
    }

    private static class TransitionInfo extends SpecialEffectsInfo {

        /* renamed from: c, reason: collision with root package name */
        private final Object f3965c;

        /* renamed from: d, reason: collision with root package name */
        private final boolean f3966d;

        /* renamed from: e, reason: collision with root package name */
        private final Object f3967e;

        TransitionInfo(SpecialEffectsController.Operation operation, CancellationSignal cancellationSignal, boolean z, boolean z2) {
            super(operation, cancellationSignal);
            if (operation.e() == SpecialEffectsController.Operation.State.VISIBLE) {
                this.f3965c = z ? operation.f().T() : operation.f().B();
                this.f3966d = z ? operation.f().v() : operation.f().u();
            } else {
                this.f3965c = z ? operation.f().W() : operation.f().E();
                this.f3966d = true;
            }
            if (!z2) {
                this.f3967e = null;
            } else if (z) {
                this.f3967e = operation.f().Y();
            } else {
                this.f3967e = operation.f().X();
            }
        }

        private FragmentTransitionImpl f(Object obj) {
            if (obj == null) {
                return null;
            }
            FragmentTransitionImpl fragmentTransitionImpl = FragmentTransition.f4178a;
            if (fragmentTransitionImpl != null && fragmentTransitionImpl.e(obj)) {
                return fragmentTransitionImpl;
            }
            FragmentTransitionImpl fragmentTransitionImpl2 = FragmentTransition.f4179b;
            if (fragmentTransitionImpl2 != null && fragmentTransitionImpl2.e(obj)) {
                return fragmentTransitionImpl2;
            }
            throw new IllegalArgumentException("Transition " + obj + " for fragment " + b().f() + " is not a valid framework Transition or AndroidX Transition");
        }

        FragmentTransitionImpl e() {
            FragmentTransitionImpl f2 = f(this.f3965c);
            FragmentTransitionImpl f3 = f(this.f3967e);
            if (f2 == null || f3 == null || f2 == f3) {
                return f2 != null ? f2 : f3;
            }
            throw new IllegalArgumentException("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + b().f() + " returned Transition " + this.f3965c + " which uses a different Transition  type than its shared element transition " + this.f3967e);
        }

        public Object g() {
            return this.f3967e;
        }

        Object h() {
            return this.f3965c;
        }

        public boolean i() {
            return this.f3967e != null;
        }

        boolean j() {
            return this.f3966d;
        }
    }

    DefaultSpecialEffectsController(ViewGroup viewGroup) {
        super(viewGroup);
    }

    private void w(List list, List list2, boolean z, Map map) {
        int i2;
        boolean z2;
        Context context;
        View view;
        int i3;
        final SpecialEffectsController.Operation operation;
        final ViewGroup m2 = m();
        Context context2 = m2.getContext();
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        boolean z3 = false;
        while (true) {
            i2 = 2;
            if (!it.hasNext()) {
                break;
            }
            final AnimationInfo animationInfo = (AnimationInfo) it.next();
            if (animationInfo.d()) {
                animationInfo.a();
            } else {
                FragmentAnim.AnimationOrAnimator e2 = animationInfo.e(context2);
                if (e2 == null) {
                    animationInfo.a();
                } else {
                    final Animator animator = e2.f4024b;
                    if (animator == null) {
                        arrayList.add(animationInfo);
                    } else {
                        final SpecialEffectsController.Operation b2 = animationInfo.b();
                        Fragment f2 = b2.f();
                        if (Boolean.TRUE.equals(map.get(b2))) {
                            if (FragmentManager.N0(2)) {
                                Log.v("FragmentManager", "Ignoring Animator set on " + f2 + " as this Fragment was involved in a Transition.");
                            }
                            animationInfo.a();
                        } else {
                            boolean z4 = b2.e() == SpecialEffectsController.Operation.State.GONE;
                            if (z4) {
                                list2.remove(b2);
                            }
                            final View view2 = f2.O;
                            m2.startViewTransition(view2);
                            final boolean z5 = z4;
                            animator.addListener(new AnimatorListenerAdapter() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.2
                                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                public void onAnimationEnd(Animator animator2) {
                                    m2.endViewTransition(view2);
                                    if (z5) {
                                        b2.e().c(view2);
                                    }
                                    animationInfo.a();
                                    if (FragmentManager.N0(2)) {
                                        Log.v("FragmentManager", "Animator from operation " + b2 + " has ended.");
                                    }
                                }
                            });
                            animator.setTarget(view2);
                            animator.start();
                            if (FragmentManager.N0(2)) {
                                StringBuilder sb = new StringBuilder();
                                sb.append("Animator from operation ");
                                operation = b2;
                                sb.append(operation);
                                sb.append(" has started.");
                                Log.v("FragmentManager", sb.toString());
                            } else {
                                operation = b2;
                            }
                            animationInfo.c().c(new CancellationSignal.OnCancelListener() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.3
                                @Override // androidx.core.os.CancellationSignal.OnCancelListener
                                public void onCancel() {
                                    animator.end();
                                    if (FragmentManager.N0(2)) {
                                        Log.v("FragmentManager", "Animator from operation " + operation + " has been canceled.");
                                    }
                                }
                            });
                            z3 = true;
                        }
                    }
                }
            }
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            final AnimationInfo animationInfo2 = (AnimationInfo) it2.next();
            final SpecialEffectsController.Operation b3 = animationInfo2.b();
            Fragment f3 = b3.f();
            if (z) {
                if (FragmentManager.N0(i2)) {
                    Log.v("FragmentManager", "Ignoring Animation set on " + f3 + " as Animations cannot run alongside Transitions.");
                }
                animationInfo2.a();
            } else if (z3) {
                if (FragmentManager.N0(i2)) {
                    Log.v("FragmentManager", "Ignoring Animation set on " + f3 + " as Animations cannot run alongside Animators.");
                }
                animationInfo2.a();
            } else {
                final View view3 = f3.O;
                Animation animation = (Animation) Preconditions.h(((FragmentAnim.AnimationOrAnimator) Preconditions.h(animationInfo2.e(context2))).f4023a);
                if (b3.e() != SpecialEffectsController.Operation.State.REMOVED) {
                    view3.startAnimation(animation);
                    animationInfo2.a();
                    z2 = z3;
                    context = context2;
                    i3 = i2;
                    view = view3;
                } else {
                    m2.startViewTransition(view3);
                    FragmentAnim.EndViewTransitionAnimation endViewTransitionAnimation = new FragmentAnim.EndViewTransitionAnimation(animation, m2, view3);
                    z2 = z3;
                    context = context2;
                    view = view3;
                    endViewTransitionAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.4
                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationEnd(Animation animation2) {
                            m2.post(new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.4.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    AnonymousClass4 anonymousClass4 = AnonymousClass4.this;
                                    m2.endViewTransition(view3);
                                    animationInfo2.a();
                                }
                            });
                            if (FragmentManager.N0(2)) {
                                Log.v("FragmentManager", "Animation from operation " + b3 + " has ended.");
                            }
                        }

                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationRepeat(Animation animation2) {
                        }

                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationStart(Animation animation2) {
                            if (FragmentManager.N0(2)) {
                                Log.v("FragmentManager", "Animation from operation " + b3 + " has reached onAnimationStart.");
                            }
                        }
                    });
                    view.startAnimation(endViewTransitionAnimation);
                    i3 = 2;
                    if (FragmentManager.N0(2)) {
                        Log.v("FragmentManager", "Animation from operation " + b3 + " has started.");
                    }
                }
                final View view4 = view;
                animationInfo2.c().c(new CancellationSignal.OnCancelListener() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.5
                    @Override // androidx.core.os.CancellationSignal.OnCancelListener
                    public void onCancel() {
                        view4.clearAnimation();
                        m2.endViewTransition(view4);
                        animationInfo2.a();
                        if (FragmentManager.N0(2)) {
                            Log.v("FragmentManager", "Animation from operation " + b3 + " has been cancelled.");
                        }
                    }
                });
                i2 = i3;
                z3 = z2;
                context2 = context;
            }
        }
    }

    private Map x(List list, List list2, final boolean z, final SpecialEffectsController.Operation operation, final SpecialEffectsController.Operation operation2) {
        String str;
        String str2;
        String str3;
        View view;
        Object obj;
        ArrayList arrayList;
        Object obj2;
        ArrayList arrayList2;
        SpecialEffectsController.Operation operation3;
        SpecialEffectsController.Operation operation4;
        View view2;
        ArrayMap arrayMap;
        SpecialEffectsController.Operation operation5;
        HashMap hashMap;
        ArrayList arrayList3;
        View view3;
        FragmentTransitionImpl fragmentTransitionImpl;
        ArrayList arrayList4;
        SpecialEffectsController.Operation operation6;
        final Rect rect;
        SharedElementCallback C;
        SharedElementCallback F;
        ArrayList arrayList5;
        int i2;
        final View view4;
        String b2;
        ArrayList arrayList6;
        boolean z2 = z;
        SpecialEffectsController.Operation operation7 = operation;
        SpecialEffectsController.Operation operation8 = operation2;
        HashMap hashMap2 = new HashMap();
        Iterator it = list.iterator();
        final FragmentTransitionImpl fragmentTransitionImpl2 = null;
        while (it.hasNext()) {
            TransitionInfo transitionInfo = (TransitionInfo) it.next();
            if (!transitionInfo.d()) {
                FragmentTransitionImpl e2 = transitionInfo.e();
                if (fragmentTransitionImpl2 == null) {
                    fragmentTransitionImpl2 = e2;
                } else if (e2 != null && fragmentTransitionImpl2 != e2) {
                    throw new IllegalArgumentException("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + transitionInfo.b().f() + " returned Transition " + transitionInfo.h() + " which uses a different Transition  type than other Fragments.");
                }
            }
        }
        if (fragmentTransitionImpl2 == null) {
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                TransitionInfo transitionInfo2 = (TransitionInfo) it2.next();
                hashMap2.put(transitionInfo2.b(), Boolean.FALSE);
                transitionInfo2.a();
            }
            return hashMap2;
        }
        View view5 = new View(m().getContext());
        Rect rect2 = new Rect();
        ArrayList arrayList7 = new ArrayList();
        ArrayList arrayList8 = new ArrayList();
        ArrayMap arrayMap2 = new ArrayMap();
        Iterator it3 = list.iterator();
        Object obj3 = null;
        View view6 = null;
        boolean z3 = false;
        while (true) {
            str = "FragmentManager";
            if (!it3.hasNext()) {
                break;
            }
            TransitionInfo transitionInfo3 = (TransitionInfo) it3.next();
            if (!transitionInfo3.i() || operation7 == null || operation8 == null) {
                arrayMap = arrayMap2;
                operation5 = operation7;
                hashMap = hashMap2;
                arrayList3 = arrayList7;
                view3 = view5;
                fragmentTransitionImpl = fragmentTransitionImpl2;
                arrayList4 = arrayList8;
                operation6 = operation8;
                rect = rect2;
                view6 = view6;
            } else {
                Object u = fragmentTransitionImpl2.u(fragmentTransitionImpl2.f(transitionInfo3.g()));
                ArrayList Z = operation2.f().Z();
                ArrayList Z2 = operation.f().Z();
                ArrayList a0 = operation.f().a0();
                View view7 = view6;
                HashMap hashMap3 = hashMap2;
                int i3 = 0;
                while (i3 < a0.size()) {
                    int indexOf = Z.indexOf(a0.get(i3));
                    ArrayList arrayList9 = a0;
                    if (indexOf != -1) {
                        Z.set(indexOf, (String) Z2.get(i3));
                    }
                    i3++;
                    a0 = arrayList9;
                }
                ArrayList a02 = operation2.f().a0();
                if (z2) {
                    C = operation.f().C();
                    F = operation2.f().F();
                } else {
                    C = operation.f().F();
                    F = operation2.f().C();
                }
                int size = Z.size();
                View view8 = view5;
                int i4 = 0;
                while (i4 < size) {
                    arrayMap2.put((String) Z.get(i4), (String) a02.get(i4));
                    i4++;
                    size = size;
                    rect2 = rect2;
                }
                Rect rect3 = rect2;
                if (FragmentManager.N0(2)) {
                    Log.v("FragmentManager", ">>> entering view names <<<");
                    for (Iterator it4 = a02.iterator(); it4.hasNext(); it4 = it4) {
                        Log.v("FragmentManager", "Name: " + ((String) it4.next()));
                    }
                    Log.v("FragmentManager", ">>> exiting view names <<<");
                    for (Iterator it5 = Z.iterator(); it5.hasNext(); it5 = it5) {
                        Log.v("FragmentManager", "Name: " + ((String) it5.next()));
                    }
                }
                ArrayMap arrayMap3 = new ArrayMap();
                u(arrayMap3, operation.f().O);
                arrayMap3.n(Z);
                if (C != null) {
                    if (FragmentManager.N0(2)) {
                        Log.v("FragmentManager", "Executing exit callback for operation " + operation7);
                    }
                    C.d(Z, arrayMap3);
                    int size2 = Z.size() - 1;
                    while (size2 >= 0) {
                        String str4 = (String) Z.get(size2);
                        View view9 = (View) arrayMap3.get(str4);
                        if (view9 == null) {
                            arrayMap2.remove(str4);
                            arrayList6 = Z;
                        } else {
                            arrayList6 = Z;
                            if (!str4.equals(ViewCompat.D(view9))) {
                                arrayMap2.put(ViewCompat.D(view9), (String) arrayMap2.remove(str4));
                            }
                        }
                        size2--;
                        Z = arrayList6;
                    }
                    arrayList5 = Z;
                } else {
                    arrayList5 = Z;
                    arrayMap2.n(arrayMap3.keySet());
                }
                final ArrayMap arrayMap4 = new ArrayMap();
                u(arrayMap4, operation2.f().O);
                arrayMap4.n(a02);
                arrayMap4.n(arrayMap2.values());
                if (F != null) {
                    if (FragmentManager.N0(2)) {
                        Log.v("FragmentManager", "Executing enter callback for operation " + operation8);
                    }
                    F.d(a02, arrayMap4);
                    for (int size3 = a02.size() - 1; size3 >= 0; size3--) {
                        String str5 = (String) a02.get(size3);
                        View view10 = (View) arrayMap4.get(str5);
                        if (view10 == null) {
                            String b3 = FragmentTransition.b(arrayMap2, str5);
                            if (b3 != null) {
                                arrayMap2.remove(b3);
                            }
                        } else if (!str5.equals(ViewCompat.D(view10)) && (b2 = FragmentTransition.b(arrayMap2, str5)) != null) {
                            arrayMap2.put(b2, ViewCompat.D(view10));
                        }
                    }
                } else {
                    FragmentTransition.d(arrayMap2, arrayMap4);
                }
                v(arrayMap3, arrayMap2.keySet());
                v(arrayMap4, arrayMap2.values());
                if (arrayMap2.isEmpty()) {
                    arrayList7.clear();
                    arrayList8.clear();
                    arrayMap = arrayMap2;
                    arrayList4 = arrayList8;
                    operation5 = operation7;
                    arrayList3 = arrayList7;
                    fragmentTransitionImpl = fragmentTransitionImpl2;
                    view6 = view7;
                    view3 = view8;
                    hashMap = hashMap3;
                    rect = rect3;
                    obj3 = null;
                    operation6 = operation8;
                } else {
                    FragmentTransition.a(operation2.f(), operation.f(), z2, arrayMap3, true);
                    arrayMap = arrayMap2;
                    ArrayList arrayList10 = arrayList8;
                    OneShotPreDrawListener.a(m(), new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.6
                        @Override // java.lang.Runnable
                        public void run() {
                            FragmentTransition.a(operation2.f(), operation.f(), z, arrayMap4, false);
                        }
                    });
                    arrayList7.addAll(arrayMap3.values());
                    if (arrayList5.isEmpty()) {
                        i2 = 0;
                        view6 = view7;
                    } else {
                        i2 = 0;
                        view6 = (View) arrayMap3.get((String) arrayList5.get(0));
                        fragmentTransitionImpl2.p(u, view6);
                    }
                    arrayList10.addAll(arrayMap4.values());
                    if (a02.isEmpty() || (view4 = (View) arrayMap4.get((String) a02.get(i2))) == null) {
                        rect = rect3;
                        view3 = view8;
                    } else {
                        rect = rect3;
                        OneShotPreDrawListener.a(m(), new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.7
                            @Override // java.lang.Runnable
                            public void run() {
                                fragmentTransitionImpl2.h(view4, rect);
                            }
                        });
                        view3 = view8;
                        z3 = true;
                    }
                    fragmentTransitionImpl2.s(u, view3, arrayList7);
                    arrayList3 = arrayList7;
                    fragmentTransitionImpl = fragmentTransitionImpl2;
                    fragmentTransitionImpl2.n(u, null, null, null, null, u, arrayList10);
                    Boolean bool = Boolean.TRUE;
                    operation5 = operation;
                    arrayList4 = arrayList10;
                    hashMap = hashMap3;
                    hashMap.put(operation5, bool);
                    operation6 = operation2;
                    hashMap.put(operation6, bool);
                    obj3 = u;
                }
            }
            view5 = view3;
            rect2 = rect;
            arrayList7 = arrayList3;
            arrayList8 = arrayList4;
            operation8 = operation6;
            z2 = z;
            hashMap2 = hashMap;
            fragmentTransitionImpl2 = fragmentTransitionImpl;
            operation7 = operation5;
            arrayMap2 = arrayMap;
        }
        View view11 = view6;
        ArrayMap arrayMap5 = arrayMap2;
        SpecialEffectsController.Operation operation9 = operation7;
        HashMap hashMap4 = hashMap2;
        ArrayList arrayList11 = arrayList7;
        View view12 = view5;
        FragmentTransitionImpl fragmentTransitionImpl3 = fragmentTransitionImpl2;
        ArrayList arrayList12 = arrayList8;
        SpecialEffectsController.Operation operation10 = operation8;
        Rect rect4 = rect2;
        ArrayList arrayList13 = new ArrayList();
        Iterator it6 = list.iterator();
        Object obj4 = null;
        Object obj5 = null;
        while (it6.hasNext()) {
            TransitionInfo transitionInfo4 = (TransitionInfo) it6.next();
            if (transitionInfo4.d()) {
                hashMap4.put(transitionInfo4.b(), Boolean.FALSE);
                transitionInfo4.a();
                it6 = it6;
            } else {
                Iterator it7 = it6;
                Object f2 = fragmentTransitionImpl3.f(transitionInfo4.h());
                SpecialEffectsController.Operation b4 = transitionInfo4.b();
                boolean z4 = obj3 != null && (b4 == operation9 || b4 == operation10);
                if (f2 == null) {
                    if (!z4) {
                        hashMap4.put(b4, Boolean.FALSE);
                        transitionInfo4.a();
                    }
                    view = view12;
                    str3 = str;
                    arrayList = arrayList11;
                    arrayList2 = arrayList12;
                    obj = obj4;
                    obj2 = obj5;
                    operation3 = operation10;
                    view2 = view11;
                } else {
                    str3 = str;
                    final ArrayList arrayList14 = new ArrayList();
                    Object obj6 = obj4;
                    t(arrayList14, b4.f().O);
                    if (z4) {
                        if (b4 == operation9) {
                            arrayList14.removeAll(arrayList11);
                        } else {
                            arrayList14.removeAll(arrayList12);
                        }
                    }
                    if (arrayList14.isEmpty()) {
                        fragmentTransitionImpl3.a(f2, view12);
                        view = view12;
                        arrayList = arrayList11;
                        arrayList2 = arrayList12;
                        obj2 = obj5;
                        operation4 = b4;
                        operation3 = operation10;
                        obj = obj6;
                    } else {
                        fragmentTransitionImpl3.b(f2, arrayList14);
                        view = view12;
                        obj = obj6;
                        arrayList = arrayList11;
                        obj2 = obj5;
                        arrayList2 = arrayList12;
                        operation3 = operation10;
                        fragmentTransitionImpl3.n(f2, f2, arrayList14, null, null, null, null);
                        if (b4.e() == SpecialEffectsController.Operation.State.GONE) {
                            operation4 = b4;
                            list2.remove(operation4);
                            ArrayList arrayList15 = new ArrayList(arrayList14);
                            arrayList15.remove(operation4.f().O);
                            fragmentTransitionImpl3.m(f2, operation4.f().O, arrayList15);
                            OneShotPreDrawListener.a(m(), new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.8
                                @Override // java.lang.Runnable
                                public void run() {
                                    FragmentTransition.e(arrayList14, 4);
                                }
                            });
                        } else {
                            operation4 = b4;
                        }
                    }
                    if (operation4.e() == SpecialEffectsController.Operation.State.VISIBLE) {
                        arrayList13.addAll(arrayList14);
                        if (z3) {
                            fragmentTransitionImpl3.o(f2, rect4);
                        }
                        view2 = view11;
                    } else {
                        view2 = view11;
                        fragmentTransitionImpl3.p(f2, view2);
                    }
                    hashMap4.put(operation4, Boolean.TRUE);
                    if (transitionInfo4.j()) {
                        obj2 = fragmentTransitionImpl3.k(obj2, f2, null);
                    } else {
                        obj = fragmentTransitionImpl3.k(obj, f2, null);
                    }
                }
                it6 = it7;
                obj4 = obj;
                view11 = view2;
                obj5 = obj2;
                operation10 = operation3;
                str = str3;
                view12 = view;
                arrayList11 = arrayList;
                arrayList12 = arrayList2;
            }
        }
        String str6 = str;
        ArrayList arrayList16 = arrayList11;
        ArrayList arrayList17 = arrayList12;
        SpecialEffectsController.Operation operation11 = operation10;
        Object j2 = fragmentTransitionImpl3.j(obj5, obj4, obj3);
        if (j2 == null) {
            return hashMap4;
        }
        Iterator it8 = list.iterator();
        while (it8.hasNext()) {
            final TransitionInfo transitionInfo5 = (TransitionInfo) it8.next();
            if (!transitionInfo5.d()) {
                Object h2 = transitionInfo5.h();
                final SpecialEffectsController.Operation b5 = transitionInfo5.b();
                boolean z5 = obj3 != null && (b5 == operation9 || b5 == operation11);
                if (h2 == null && !z5) {
                    str2 = str6;
                } else if (ViewCompat.N(m())) {
                    str2 = str6;
                    fragmentTransitionImpl3.q(transitionInfo5.b().f(), j2, transitionInfo5.c(), new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.9
                        @Override // java.lang.Runnable
                        public void run() {
                            transitionInfo5.a();
                            if (FragmentManager.N0(2)) {
                                Log.v("FragmentManager", "Transition for operation " + b5 + "has completed");
                            }
                        }
                    });
                } else {
                    if (FragmentManager.N0(2)) {
                        str2 = str6;
                        Log.v(str2, "SpecialEffectsController: Container " + m() + " has not been laid out. Completing operation " + b5);
                    } else {
                        str2 = str6;
                    }
                    transitionInfo5.a();
                }
                str6 = str2;
            }
        }
        String str7 = str6;
        if (!ViewCompat.N(m())) {
            return hashMap4;
        }
        FragmentTransition.e(arrayList13, 4);
        ArrayList l2 = fragmentTransitionImpl3.l(arrayList17);
        if (FragmentManager.N0(2)) {
            Log.v(str7, ">>>>> Beginning transition <<<<<");
            Log.v(str7, ">>>>> SharedElementFirstOutViews <<<<<");
            Iterator it9 = arrayList16.iterator();
            while (it9.hasNext()) {
                View view13 = (View) it9.next();
                Log.v(str7, "View: " + view13 + " Name: " + ViewCompat.D(view13));
            }
            Log.v(str7, ">>>>> SharedElementLastInViews <<<<<");
            Iterator it10 = arrayList17.iterator();
            while (it10.hasNext()) {
                View view14 = (View) it10.next();
                Log.v(str7, "View: " + view14 + " Name: " + ViewCompat.D(view14));
            }
        }
        fragmentTransitionImpl3.c(m(), j2);
        fragmentTransitionImpl3.r(m(), arrayList16, arrayList17, l2, arrayMap5);
        FragmentTransition.e(arrayList13, 0);
        fragmentTransitionImpl3.t(obj3, arrayList16, arrayList17);
        return hashMap4;
    }

    private void y(List list) {
        Fragment f2 = ((SpecialEffectsController.Operation) list.get(list.size() - 1)).f();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            SpecialEffectsController.Operation operation = (SpecialEffectsController.Operation) it.next();
            operation.f().R.f4004c = f2.R.f4004c;
            operation.f().R.f4005d = f2.R.f4005d;
            operation.f().R.f4006e = f2.R.f4006e;
            operation.f().R.f4007f = f2.R.f4007f;
        }
    }

    @Override // androidx.fragment.app.SpecialEffectsController
    void f(List list, boolean z) {
        Iterator it = list.iterator();
        SpecialEffectsController.Operation operation = null;
        SpecialEffectsController.Operation operation2 = null;
        while (it.hasNext()) {
            SpecialEffectsController.Operation operation3 = (SpecialEffectsController.Operation) it.next();
            SpecialEffectsController.Operation.State e2 = SpecialEffectsController.Operation.State.e(operation3.f().O);
            int i2 = AnonymousClass10.f3925a[operation3.e().ordinal()];
            if (i2 == 1 || i2 == 2 || i2 == 3) {
                if (e2 == SpecialEffectsController.Operation.State.VISIBLE && operation == null) {
                    operation = operation3;
                }
            } else if (i2 == 4 && e2 != SpecialEffectsController.Operation.State.VISIBLE) {
                operation2 = operation3;
            }
        }
        if (FragmentManager.N0(2)) {
            Log.v("FragmentManager", "Executing operations from " + operation + " to " + operation2);
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        final ArrayList arrayList3 = new ArrayList(list);
        y(list);
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            final SpecialEffectsController.Operation operation4 = (SpecialEffectsController.Operation) it2.next();
            CancellationSignal cancellationSignal = new CancellationSignal();
            operation4.j(cancellationSignal);
            arrayList.add(new AnimationInfo(operation4, cancellationSignal, z));
            CancellationSignal cancellationSignal2 = new CancellationSignal();
            operation4.j(cancellationSignal2);
            boolean z2 = false;
            if (z) {
                if (operation4 != operation) {
                    arrayList2.add(new TransitionInfo(operation4, cancellationSignal2, z, z2));
                    operation4.a(new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (arrayList3.contains(operation4)) {
                                arrayList3.remove(operation4);
                                DefaultSpecialEffectsController.this.s(operation4);
                            }
                        }
                    });
                }
                z2 = true;
                arrayList2.add(new TransitionInfo(operation4, cancellationSignal2, z, z2));
                operation4.a(new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (arrayList3.contains(operation4)) {
                            arrayList3.remove(operation4);
                            DefaultSpecialEffectsController.this.s(operation4);
                        }
                    }
                });
            } else {
                if (operation4 != operation2) {
                    arrayList2.add(new TransitionInfo(operation4, cancellationSignal2, z, z2));
                    operation4.a(new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (arrayList3.contains(operation4)) {
                                arrayList3.remove(operation4);
                                DefaultSpecialEffectsController.this.s(operation4);
                            }
                        }
                    });
                }
                z2 = true;
                arrayList2.add(new TransitionInfo(operation4, cancellationSignal2, z, z2));
                operation4.a(new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (arrayList3.contains(operation4)) {
                            arrayList3.remove(operation4);
                            DefaultSpecialEffectsController.this.s(operation4);
                        }
                    }
                });
            }
        }
        Map x = x(arrayList2, arrayList3, z, operation, operation2);
        w(arrayList, arrayList3, x.containsValue(Boolean.TRUE), x);
        Iterator it3 = arrayList3.iterator();
        while (it3.hasNext()) {
            s((SpecialEffectsController.Operation) it3.next());
        }
        arrayList3.clear();
        if (FragmentManager.N0(2)) {
            Log.v("FragmentManager", "Completed executing operations from " + operation + " to " + operation2);
        }
    }

    void s(SpecialEffectsController.Operation operation) {
        operation.e().c(operation.f().O);
    }

    void t(ArrayList arrayList, View view) {
        if (!(view instanceof ViewGroup)) {
            if (arrayList.contains(view)) {
                return;
            }
            arrayList.add(view);
            return;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        if (ViewGroupCompat.a(viewGroup)) {
            if (arrayList.contains(view)) {
                return;
            }
            arrayList.add(viewGroup);
            return;
        }
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if (childAt.getVisibility() == 0) {
                t(arrayList, childAt);
            }
        }
    }

    void u(Map map, View view) {
        String D = ViewCompat.D(view);
        if (D != null) {
            map.put(D, view);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = viewGroup.getChildAt(i2);
                if (childAt.getVisibility() == 0) {
                    u(map, childAt);
                }
            }
        }
    }

    void v(ArrayMap arrayMap, Collection collection) {
        Iterator it = arrayMap.entrySet().iterator();
        while (it.hasNext()) {
            if (!collection.contains(ViewCompat.D((View) ((Map.Entry) it.next()).getValue()))) {
                it.remove();
            }
        }
    }
}
