package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.InflateException;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowId;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.collection.ArrayMap;
import androidx.collection.LongSparseArray;
import androidx.core.content.res.TypedArrayUtils;
import androidx.core.util.Consumer;
import androidx.core.view.ViewCompat;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.transition.Transition;
import com.zte.shared.wrapper.VirtualHandleWrapper;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/* loaded from: classes.dex */
public abstract class Transition implements Cloneable {
    private static final Animator[] S = new Animator[0];
    private static final int[] T = {2, 1, 3, 4};
    private static final PathMotion U = new PathMotion() { // from class: androidx.transition.Transition.1
        @Override // androidx.transition.PathMotion
        public Path a(float f2, float f3, float f4, float f5) {
            Path path = new Path();
            path.moveTo(f2, f3);
            path.lineTo(f4, f5);
            return path;
        }
    };
    private static ThreadLocal V = new ThreadLocal();
    private ArrayList A;
    private TransitionListener[] B;
    TransitionPropagation L;
    private EpicenterCallback M;
    private ArrayMap N;
    long P;
    SeekController Q;
    long R;
    private ArrayList z;

    /* renamed from: c, reason: collision with root package name */
    private String f5519c = getClass().getName();

    /* renamed from: h, reason: collision with root package name */
    private long f5520h = -1;

    /* renamed from: i, reason: collision with root package name */
    long f5521i = -1;

    /* renamed from: j, reason: collision with root package name */
    private TimeInterpolator f5522j = null;

    /* renamed from: k, reason: collision with root package name */
    ArrayList f5523k = new ArrayList();

    /* renamed from: l, reason: collision with root package name */
    ArrayList f5524l = new ArrayList();

    /* renamed from: m, reason: collision with root package name */
    private ArrayList f5525m = null;

    /* renamed from: n, reason: collision with root package name */
    private ArrayList f5526n = null;

    /* renamed from: o, reason: collision with root package name */
    private ArrayList f5527o = null;

    /* renamed from: p, reason: collision with root package name */
    private ArrayList f5528p = null;

    /* renamed from: q, reason: collision with root package name */
    private ArrayList f5529q = null;

    /* renamed from: r, reason: collision with root package name */
    private ArrayList f5530r = null;

    /* renamed from: s, reason: collision with root package name */
    private ArrayList f5531s = null;
    private ArrayList t = null;
    private ArrayList u = null;
    private TransitionValuesMaps v = new TransitionValuesMaps();
    private TransitionValuesMaps w = new TransitionValuesMaps();
    TransitionSet x = null;
    private int[] y = T;
    boolean C = false;
    ArrayList D = new ArrayList();
    private Animator[] E = S;
    int F = 0;
    private boolean G = false;
    boolean H = false;
    private Transition I = null;
    private ArrayList J = null;
    ArrayList K = new ArrayList();
    private PathMotion O = U;

    private static class AnimationInfo {

        /* renamed from: a, reason: collision with root package name */
        View f5535a;

        /* renamed from: b, reason: collision with root package name */
        String f5536b;

        /* renamed from: c, reason: collision with root package name */
        TransitionValues f5537c;

        /* renamed from: d, reason: collision with root package name */
        WindowId f5538d;

        /* renamed from: e, reason: collision with root package name */
        Transition f5539e;

        /* renamed from: f, reason: collision with root package name */
        Animator f5540f;

        AnimationInfo(View view, String str, Transition transition, WindowId windowId, TransitionValues transitionValues, Animator animator) {
            this.f5535a = view;
            this.f5536b = str;
            this.f5537c = transitionValues;
            this.f5538d = windowId;
            this.f5539e = transition;
            this.f5540f = animator;
        }
    }

    private static class ArrayListManager {
    }

    public static abstract class EpicenterCallback {
        public abstract Rect a(Transition transition);
    }

    @RequiresApi
    private static class Impl26 {
        @DoNotInline
        static long a(Animator animator) {
            return animator.getTotalDuration();
        }

        @DoNotInline
        static void b(Animator animator, long j2) {
            ((AnimatorSet) animator).setCurrentPlayTime(j2);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface MatchOrder {
    }

    @RequiresApi
    class SeekController extends TransitionListenerAdapter implements TransitionSeekController, DynamicAnimation.OnAnimationUpdateListener {

        /* renamed from: c, reason: collision with root package name */
        private long f5541c;

        /* renamed from: h, reason: collision with root package name */
        private ArrayList f5542h;

        /* renamed from: i, reason: collision with root package name */
        private ArrayList f5543i;

        /* renamed from: j, reason: collision with root package name */
        private boolean f5544j;

        /* renamed from: k, reason: collision with root package name */
        private boolean f5545k;

        /* renamed from: l, reason: collision with root package name */
        private Consumer[] f5546l;

        /* renamed from: m, reason: collision with root package name */
        final /* synthetic */ Transition f5547m;

        private void i() {
            ArrayList arrayList = this.f5543i;
            if (arrayList == null || arrayList.isEmpty()) {
                return;
            }
            int size = this.f5543i.size();
            if (this.f5546l == null) {
                this.f5546l = new Consumer[size];
            }
            Consumer[] consumerArr = (Consumer[]) this.f5543i.toArray(this.f5546l);
            this.f5546l = null;
            for (int i2 = 0; i2 < size; i2++) {
                consumerArr[i2].accept(this);
                consumerArr[i2] = null;
            }
            this.f5546l = consumerArr;
        }

        @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationUpdateListener
        public void a(DynamicAnimation dynamicAnimation, float f2, float f3) {
            long max = Math.max(-1L, Math.min(j() + 1, Math.round(f2)));
            this.f5547m.g0(max, this.f5541c);
            this.f5541c = max;
            i();
        }

        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
        public void g(Transition transition) {
            this.f5545k = true;
        }

        public long j() {
            return this.f5547m.I();
        }

        void k() {
            long j2 = j() == 0 ? 1L : 0L;
            this.f5547m.g0(j2, this.f5541c);
            this.f5541c = j2;
        }

        public void l() {
            this.f5544j = true;
            ArrayList arrayList = this.f5542h;
            if (arrayList != null) {
                this.f5542h = null;
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    ((Consumer) arrayList.get(i2)).accept(this);
                }
            }
            i();
        }
    }

    public interface TransitionListener {
        void b(Transition transition);

        void c(Transition transition);

        void d(Transition transition);

        default void e(Transition transition, boolean z) {
            f(transition);
        }

        void f(Transition transition);

        void g(Transition transition);

        default void h(Transition transition, boolean z) {
            c(transition);
        }
    }

    interface TransitionNotification {

        /* renamed from: a, reason: collision with root package name */
        public static final TransitionNotification f5548a = new TransitionNotification() { // from class: androidx.transition.b
            @Override // androidx.transition.Transition.TransitionNotification
            public final void e(Transition.TransitionListener transitionListener, Transition transition, boolean z) {
                transitionListener.h(transition, z);
            }
        };

        /* renamed from: b, reason: collision with root package name */
        public static final TransitionNotification f5549b = new TransitionNotification() { // from class: androidx.transition.c
            @Override // androidx.transition.Transition.TransitionNotification
            public final void e(Transition.TransitionListener transitionListener, Transition transition, boolean z) {
                transitionListener.e(transition, z);
            }
        };

        /* renamed from: c, reason: collision with root package name */
        public static final TransitionNotification f5550c = new TransitionNotification() { // from class: androidx.transition.d
            @Override // androidx.transition.Transition.TransitionNotification
            public final void e(Transition.TransitionListener transitionListener, Transition transition, boolean z) {
                transitionListener.g(transition);
            }
        };

        /* renamed from: d, reason: collision with root package name */
        public static final TransitionNotification f5551d = new TransitionNotification() { // from class: androidx.transition.e
            @Override // androidx.transition.Transition.TransitionNotification
            public final void e(Transition.TransitionListener transitionListener, Transition transition, boolean z) {
                transitionListener.d(transition);
            }
        };

        /* renamed from: e, reason: collision with root package name */
        public static final TransitionNotification f5552e = new TransitionNotification() { // from class: androidx.transition.f
            @Override // androidx.transition.Transition.TransitionNotification
            public final void e(Transition.TransitionListener transitionListener, Transition transition, boolean z) {
                transitionListener.b(transition);
            }
        };

        void e(TransitionListener transitionListener, Transition transition, boolean z);
    }

    public Transition() {
    }

    private static ArrayMap B() {
        ArrayMap arrayMap = (ArrayMap) V.get();
        if (arrayMap != null) {
            return arrayMap;
        }
        ArrayMap arrayMap2 = new ArrayMap();
        V.set(arrayMap2);
        return arrayMap2;
    }

    private static boolean N(int i2) {
        return i2 >= 1 && i2 <= 4;
    }

    private static boolean P(TransitionValues transitionValues, TransitionValues transitionValues2, String str) {
        Object obj = transitionValues.f5570a.get(str);
        Object obj2 = transitionValues2.f5570a.get(str);
        if (obj == null && obj2 == null) {
            return false;
        }
        if (obj == null || obj2 == null) {
            return true;
        }
        return !obj.equals(obj2);
    }

    private void Q(ArrayMap arrayMap, ArrayMap arrayMap2, SparseArray sparseArray, SparseArray sparseArray2) {
        View view;
        int size = sparseArray.size();
        for (int i2 = 0; i2 < size; i2++) {
            View view2 = (View) sparseArray.valueAt(i2);
            if (view2 != null && O(view2) && (view = (View) sparseArray2.get(sparseArray.keyAt(i2))) != null && O(view)) {
                TransitionValues transitionValues = (TransitionValues) arrayMap.get(view2);
                TransitionValues transitionValues2 = (TransitionValues) arrayMap2.get(view);
                if (transitionValues != null && transitionValues2 != null) {
                    this.z.add(transitionValues);
                    this.A.add(transitionValues2);
                    arrayMap.remove(view2);
                    arrayMap2.remove(view);
                }
            }
        }
    }

    private void R(ArrayMap arrayMap, ArrayMap arrayMap2) {
        TransitionValues transitionValues;
        for (int size = arrayMap.size() - 1; size >= 0; size--) {
            View view = (View) arrayMap.f(size);
            if (view != null && O(view) && (transitionValues = (TransitionValues) arrayMap2.remove(view)) != null && O(transitionValues.f5571b)) {
                this.z.add((TransitionValues) arrayMap.h(size));
                this.A.add(transitionValues);
            }
        }
    }

    private void S(ArrayMap arrayMap, ArrayMap arrayMap2, LongSparseArray longSparseArray, LongSparseArray longSparseArray2) {
        View view;
        int n2 = longSparseArray.n();
        for (int i2 = 0; i2 < n2; i2++) {
            View view2 = (View) longSparseArray.o(i2);
            if (view2 != null && O(view2) && (view = (View) longSparseArray2.f(longSparseArray.j(i2))) != null && O(view)) {
                TransitionValues transitionValues = (TransitionValues) arrayMap.get(view2);
                TransitionValues transitionValues2 = (TransitionValues) arrayMap2.get(view);
                if (transitionValues != null && transitionValues2 != null) {
                    this.z.add(transitionValues);
                    this.A.add(transitionValues2);
                    arrayMap.remove(view2);
                    arrayMap2.remove(view);
                }
            }
        }
    }

    private void T(ArrayMap arrayMap, ArrayMap arrayMap2, ArrayMap arrayMap3, ArrayMap arrayMap4) {
        View view;
        int size = arrayMap3.size();
        for (int i2 = 0; i2 < size; i2++) {
            View view2 = (View) arrayMap3.j(i2);
            if (view2 != null && O(view2) && (view = (View) arrayMap4.get(arrayMap3.f(i2))) != null && O(view)) {
                TransitionValues transitionValues = (TransitionValues) arrayMap.get(view2);
                TransitionValues transitionValues2 = (TransitionValues) arrayMap2.get(view);
                if (transitionValues != null && transitionValues2 != null) {
                    this.z.add(transitionValues);
                    this.A.add(transitionValues2);
                    arrayMap.remove(view2);
                    arrayMap2.remove(view);
                }
            }
        }
    }

    private void U(TransitionValuesMaps transitionValuesMaps, TransitionValuesMaps transitionValuesMaps2) {
        ArrayMap arrayMap = new ArrayMap(transitionValuesMaps.f5573a);
        ArrayMap arrayMap2 = new ArrayMap(transitionValuesMaps2.f5573a);
        int i2 = 0;
        while (true) {
            int[] iArr = this.y;
            if (i2 >= iArr.length) {
                c(arrayMap, arrayMap2);
                return;
            }
            int i3 = iArr[i2];
            if (i3 == 1) {
                R(arrayMap, arrayMap2);
            } else if (i3 == 2) {
                T(arrayMap, arrayMap2, transitionValuesMaps.f5576d, transitionValuesMaps2.f5576d);
            } else if (i3 == 3) {
                Q(arrayMap, arrayMap2, transitionValuesMaps.f5574b, transitionValuesMaps2.f5574b);
            } else if (i3 == 4) {
                S(arrayMap, arrayMap2, transitionValuesMaps.f5575c, transitionValuesMaps2.f5575c);
            }
            i2++;
        }
    }

    private void V(Transition transition, TransitionNotification transitionNotification, boolean z) {
        Transition transition2 = this.I;
        if (transition2 != null) {
            transition2.V(transition, transitionNotification, z);
        }
        ArrayList arrayList = this.J;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        int size = this.J.size();
        TransitionListener[] transitionListenerArr = this.B;
        if (transitionListenerArr == null) {
            transitionListenerArr = new TransitionListener[size];
        }
        this.B = null;
        TransitionListener[] transitionListenerArr2 = (TransitionListener[]) this.J.toArray(transitionListenerArr);
        for (int i2 = 0; i2 < size; i2++) {
            transitionNotification.e(transitionListenerArr2[i2], transition, z);
            transitionListenerArr2[i2] = null;
        }
        this.B = transitionListenerArr2;
    }

    private static int[] X(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, ",");
        int[] iArr = new int[stringTokenizer.countTokens()];
        int i2 = 0;
        while (stringTokenizer.hasMoreTokens()) {
            String trim = stringTokenizer.nextToken().trim();
            if (VirtualHandleWrapper.KEY_ID.equalsIgnoreCase(trim)) {
                iArr[i2] = 3;
            } else if ("instance".equalsIgnoreCase(trim)) {
                iArr[i2] = 1;
            } else if ("name".equalsIgnoreCase(trim)) {
                iArr[i2] = 2;
            } else if ("itemId".equalsIgnoreCase(trim)) {
                iArr[i2] = 4;
            } else {
                if (!trim.isEmpty()) {
                    throw new InflateException("Unknown match type in matchOrder: '" + trim + "'");
                }
                int[] iArr2 = new int[iArr.length - 1];
                System.arraycopy(iArr, 0, iArr2, 0, i2);
                i2--;
                iArr = iArr2;
            }
            i2++;
        }
        return iArr;
    }

    private void c(ArrayMap arrayMap, ArrayMap arrayMap2) {
        for (int i2 = 0; i2 < arrayMap.size(); i2++) {
            TransitionValues transitionValues = (TransitionValues) arrayMap.j(i2);
            if (O(transitionValues.f5571b)) {
                this.z.add(transitionValues);
                this.A.add(null);
            }
        }
        for (int i3 = 0; i3 < arrayMap2.size(); i3++) {
            TransitionValues transitionValues2 = (TransitionValues) arrayMap2.j(i3);
            if (O(transitionValues2.f5571b)) {
                this.A.add(transitionValues2);
                this.z.add(null);
            }
        }
    }

    private static void e(TransitionValuesMaps transitionValuesMaps, View view, TransitionValues transitionValues) {
        transitionValuesMaps.f5573a.put(view, transitionValues);
        int id = view.getId();
        if (id >= 0) {
            if (transitionValuesMaps.f5574b.indexOfKey(id) >= 0) {
                transitionValuesMaps.f5574b.put(id, null);
            } else {
                transitionValuesMaps.f5574b.put(id, view);
            }
        }
        String D = ViewCompat.D(view);
        if (D != null) {
            if (transitionValuesMaps.f5576d.containsKey(D)) {
                transitionValuesMaps.f5576d.put(D, null);
            } else {
                transitionValuesMaps.f5576d.put(D, view);
            }
        }
        if (view.getParent() instanceof ListView) {
            ListView listView = (ListView) view.getParent();
            if (listView.getAdapter().hasStableIds()) {
                long itemIdAtPosition = listView.getItemIdAtPosition(listView.getPositionForView(view));
                if (transitionValuesMaps.f5575c.h(itemIdAtPosition) < 0) {
                    view.setHasTransientState(true);
                    transitionValuesMaps.f5575c.k(itemIdAtPosition, view);
                    return;
                }
                View view2 = (View) transitionValuesMaps.f5575c.f(itemIdAtPosition);
                if (view2 != null) {
                    view2.setHasTransientState(false);
                    transitionValuesMaps.f5575c.k(itemIdAtPosition, null);
                }
            }
        }
    }

    private void e0(Animator animator, final ArrayMap arrayMap) {
        if (animator != null) {
            animator.addListener(new AnimatorListenerAdapter() { // from class: androidx.transition.Transition.2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator2) {
                    arrayMap.remove(animator2);
                    Transition.this.D.remove(animator2);
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator2) {
                    Transition.this.D.add(animator2);
                }
            });
            g(animator);
        }
    }

    private static boolean f(int[] iArr, int i2) {
        int i3 = iArr[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            if (iArr[i4] == i3) {
                return true;
            }
        }
        return false;
    }

    private void j(View view, boolean z) {
        if (view == null) {
            return;
        }
        int id = view.getId();
        ArrayList arrayList = this.f5527o;
        if (arrayList == null || !arrayList.contains(Integer.valueOf(id))) {
            ArrayList arrayList2 = this.f5528p;
            if (arrayList2 == null || !arrayList2.contains(view)) {
                ArrayList arrayList3 = this.f5529q;
                if (arrayList3 != null) {
                    int size = arrayList3.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        if (((Class) this.f5529q.get(i2)).isInstance(view)) {
                            return;
                        }
                    }
                }
                if (view.getParent() instanceof ViewGroup) {
                    TransitionValues transitionValues = new TransitionValues(view);
                    if (z) {
                        l(transitionValues);
                    } else {
                        i(transitionValues);
                    }
                    transitionValues.f5572c.add(this);
                    k(transitionValues);
                    if (z) {
                        e(this.v, view, transitionValues);
                    } else {
                        e(this.w, view, transitionValues);
                    }
                }
                if (view instanceof ViewGroup) {
                    ArrayList arrayList4 = this.f5531s;
                    if (arrayList4 == null || !arrayList4.contains(Integer.valueOf(id))) {
                        ArrayList arrayList5 = this.t;
                        if (arrayList5 == null || !arrayList5.contains(view)) {
                            ArrayList arrayList6 = this.u;
                            if (arrayList6 != null) {
                                int size2 = arrayList6.size();
                                for (int i3 = 0; i3 < size2; i3++) {
                                    if (((Class) this.u.get(i3)).isInstance(view)) {
                                        return;
                                    }
                                }
                            }
                            ViewGroup viewGroup = (ViewGroup) view;
                            for (int i4 = 0; i4 < viewGroup.getChildCount(); i4++) {
                                j(viewGroup.getChildAt(i4), z);
                            }
                        }
                    }
                }
            }
        }
    }

    public final Transition A() {
        TransitionSet transitionSet = this.x;
        return transitionSet != null ? transitionSet.A() : this;
    }

    public long C() {
        return this.f5520h;
    }

    public List D() {
        return this.f5523k;
    }

    public List E() {
        return this.f5525m;
    }

    public List F() {
        return this.f5526n;
    }

    public List H() {
        return this.f5524l;
    }

    final long I() {
        return this.P;
    }

    public String[] J() {
        return null;
    }

    public TransitionValues K(View view, boolean z) {
        TransitionSet transitionSet = this.x;
        if (transitionSet != null) {
            return transitionSet.K(view, z);
        }
        return (TransitionValues) (z ? this.v : this.w).f5573a.get(view);
    }

    boolean L() {
        return !this.D.isEmpty();
    }

    public boolean M(TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues == null || transitionValues2 == null) {
            return false;
        }
        String[] J = J();
        if (J == null) {
            Iterator it = transitionValues.f5570a.keySet().iterator();
            while (it.hasNext()) {
                if (P(transitionValues, transitionValues2, (String) it.next())) {
                }
            }
            return false;
        }
        for (String str : J) {
            if (!P(transitionValues, transitionValues2, str)) {
            }
        }
        return false;
        return true;
    }

    boolean O(View view) {
        ArrayList arrayList;
        ArrayList arrayList2;
        int id = view.getId();
        ArrayList arrayList3 = this.f5527o;
        if (arrayList3 != null && arrayList3.contains(Integer.valueOf(id))) {
            return false;
        }
        ArrayList arrayList4 = this.f5528p;
        if (arrayList4 != null && arrayList4.contains(view)) {
            return false;
        }
        ArrayList arrayList5 = this.f5529q;
        if (arrayList5 != null) {
            int size = arrayList5.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (((Class) this.f5529q.get(i2)).isInstance(view)) {
                    return false;
                }
            }
        }
        if (this.f5530r != null && ViewCompat.D(view) != null && this.f5530r.contains(ViewCompat.D(view))) {
            return false;
        }
        if ((this.f5523k.size() == 0 && this.f5524l.size() == 0 && (((arrayList = this.f5526n) == null || arrayList.isEmpty()) && ((arrayList2 = this.f5525m) == null || arrayList2.isEmpty()))) || this.f5523k.contains(Integer.valueOf(id)) || this.f5524l.contains(view)) {
            return true;
        }
        ArrayList arrayList6 = this.f5525m;
        if (arrayList6 != null && arrayList6.contains(ViewCompat.D(view))) {
            return true;
        }
        if (this.f5526n != null) {
            for (int i3 = 0; i3 < this.f5526n.size(); i3++) {
                if (((Class) this.f5526n.get(i3)).isInstance(view)) {
                    return true;
                }
            }
        }
        return false;
    }

    void W(TransitionNotification transitionNotification, boolean z) {
        V(this, transitionNotification, z);
    }

    public void Y(View view) {
        if (this.H) {
            return;
        }
        int size = this.D.size();
        Animator[] animatorArr = (Animator[]) this.D.toArray(this.E);
        this.E = S;
        for (int i2 = size - 1; i2 >= 0; i2--) {
            Animator animator = animatorArr[i2];
            animatorArr[i2] = null;
            animator.pause();
        }
        this.E = animatorArr;
        W(TransitionNotification.f5551d, false);
        this.G = true;
    }

    void Z(ViewGroup viewGroup) {
        AnimationInfo animationInfo;
        this.z = new ArrayList();
        this.A = new ArrayList();
        U(this.v, this.w);
        ArrayMap B = B();
        int size = B.size();
        WindowId windowId = viewGroup.getWindowId();
        for (int i2 = size - 1; i2 >= 0; i2--) {
            Animator animator = (Animator) B.f(i2);
            if (animator != null && (animationInfo = (AnimationInfo) B.get(animator)) != null && animationInfo.f5535a != null && windowId.equals(animationInfo.f5538d)) {
                TransitionValues transitionValues = animationInfo.f5537c;
                View view = animationInfo.f5535a;
                TransitionValues K = K(view, true);
                TransitionValues w = w(view, true);
                if (K == null && w == null) {
                    w = (TransitionValues) this.w.f5573a.get(view);
                }
                if ((K != null || w != null) && animationInfo.f5539e.M(transitionValues, w)) {
                    Transition transition = animationInfo.f5539e;
                    if (transition.A().Q != null) {
                        animator.cancel();
                        transition.D.remove(animator);
                        B.remove(animator);
                        if (transition.D.size() == 0) {
                            transition.W(TransitionNotification.f5550c, false);
                            if (!transition.H) {
                                transition.H = true;
                                transition.W(TransitionNotification.f5549b, false);
                            }
                        }
                    } else if (animator.isRunning() || animator.isStarted()) {
                        animator.cancel();
                    } else {
                        B.remove(animator);
                    }
                }
            }
        }
        q(viewGroup, this.v, this.w, this.z, this.A);
        if (this.Q == null) {
            f0();
        } else if (Build.VERSION.SDK_INT >= 34) {
            a0();
            this.Q.k();
            this.Q.l();
        }
    }

    public Transition a(TransitionListener transitionListener) {
        if (this.J == null) {
            this.J = new ArrayList();
        }
        this.J.add(transitionListener);
        return this;
    }

    void a0() {
        ArrayMap B = B();
        this.P = 0L;
        for (int i2 = 0; i2 < this.K.size(); i2++) {
            Animator animator = (Animator) this.K.get(i2);
            AnimationInfo animationInfo = (AnimationInfo) B.get(animator);
            if (animator != null && animationInfo != null) {
                if (s() >= 0) {
                    animationInfo.f5540f.setDuration(s());
                }
                if (C() >= 0) {
                    animationInfo.f5540f.setStartDelay(C() + animationInfo.f5540f.getStartDelay());
                }
                if (v() != null) {
                    animationInfo.f5540f.setInterpolator(v());
                }
                this.D.add(animator);
                this.P = Math.max(this.P, Impl26.a(animator));
            }
        }
        this.K.clear();
    }

    public Transition b(View view) {
        this.f5524l.add(view);
        return this;
    }

    public Transition b0(TransitionListener transitionListener) {
        Transition transition;
        ArrayList arrayList = this.J;
        if (arrayList == null) {
            return this;
        }
        if (!arrayList.remove(transitionListener) && (transition = this.I) != null) {
            transition.b0(transitionListener);
        }
        if (this.J.size() == 0) {
            this.J = null;
        }
        return this;
    }

    public Transition c0(View view) {
        this.f5524l.remove(view);
        return this;
    }

    public void d0(View view) {
        if (this.G) {
            if (!this.H) {
                int size = this.D.size();
                Animator[] animatorArr = (Animator[]) this.D.toArray(this.E);
                this.E = S;
                for (int i2 = size - 1; i2 >= 0; i2--) {
                    Animator animator = animatorArr[i2];
                    animatorArr[i2] = null;
                    animator.resume();
                }
                this.E = animatorArr;
                W(TransitionNotification.f5552e, false);
            }
            this.G = false;
        }
    }

    protected void f0() {
        o0();
        ArrayMap B = B();
        Iterator it = this.K.iterator();
        while (it.hasNext()) {
            Animator animator = (Animator) it.next();
            if (B.containsKey(animator)) {
                o0();
                e0(animator, B);
            }
        }
        this.K.clear();
        r();
    }

    protected void g(Animator animator) {
        if (animator == null) {
            r();
            return;
        }
        if (s() >= 0) {
            animator.setDuration(s());
        }
        if (C() >= 0) {
            animator.setStartDelay(C() + animator.getStartDelay());
        }
        if (v() != null) {
            animator.setInterpolator(v());
        }
        animator.addListener(new AnimatorListenerAdapter() { // from class: androidx.transition.Transition.3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator2) {
                Transition.this.r();
                animator2.removeListener(this);
            }
        });
        animator.start();
    }

    void g0(long j2, long j3) {
        long I = I();
        int i2 = 0;
        boolean z = j2 < j3;
        int i3 = (j3 > 0L ? 1 : (j3 == 0L ? 0 : -1));
        if ((i3 < 0 && j2 >= 0) || (j3 > I && j2 <= I)) {
            this.H = false;
            W(TransitionNotification.f5548a, z);
        }
        Animator[] animatorArr = (Animator[]) this.D.toArray(this.E);
        this.E = S;
        for (int size = this.D.size(); i2 < size; size = size) {
            Animator animator = animatorArr[i2];
            animatorArr[i2] = null;
            Impl26.b(animator, Math.min(Math.max(0L, j2), Impl26.a(animator)));
            i2++;
            i3 = i3;
        }
        int i4 = i3;
        this.E = animatorArr;
        if ((j2 <= I || j3 > I) && (j2 >= 0 || i4 < 0)) {
            return;
        }
        if (j2 > I) {
            this.H = true;
        }
        W(TransitionNotification.f5549b, z);
    }

    protected void h() {
        int size = this.D.size();
        Animator[] animatorArr = (Animator[]) this.D.toArray(this.E);
        this.E = S;
        for (int i2 = size - 1; i2 >= 0; i2--) {
            Animator animator = animatorArr[i2];
            animatorArr[i2] = null;
            animator.cancel();
        }
        this.E = animatorArr;
        W(TransitionNotification.f5550c, false);
    }

    public Transition h0(long j2) {
        this.f5521i = j2;
        return this;
    }

    public abstract void i(TransitionValues transitionValues);

    public void i0(EpicenterCallback epicenterCallback) {
        this.M = epicenterCallback;
    }

    public Transition j0(TimeInterpolator timeInterpolator) {
        this.f5522j = timeInterpolator;
        return this;
    }

    void k(TransitionValues transitionValues) {
        String[] b2;
        if (this.L == null || transitionValues.f5570a.isEmpty() || (b2 = this.L.b()) == null) {
            return;
        }
        for (String str : b2) {
            if (!transitionValues.f5570a.containsKey(str)) {
                this.L.a(transitionValues);
                return;
            }
        }
    }

    public void k0(int... iArr) {
        if (iArr == null || iArr.length == 0) {
            this.y = T;
            return;
        }
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (!N(iArr[i2])) {
                throw new IllegalArgumentException("matches contains invalid value");
            }
            if (f(iArr, i2)) {
                throw new IllegalArgumentException("matches contains a duplicate value");
            }
        }
        this.y = (int[]) iArr.clone();
    }

    public abstract void l(TransitionValues transitionValues);

    public void l0(PathMotion pathMotion) {
        if (pathMotion == null) {
            this.O = U;
        } else {
            this.O = pathMotion;
        }
    }

    void m(ViewGroup viewGroup, boolean z) {
        ArrayList arrayList;
        ArrayList arrayList2;
        ArrayMap arrayMap;
        n(z);
        if ((this.f5523k.size() > 0 || this.f5524l.size() > 0) && (((arrayList = this.f5525m) == null || arrayList.isEmpty()) && ((arrayList2 = this.f5526n) == null || arrayList2.isEmpty()))) {
            for (int i2 = 0; i2 < this.f5523k.size(); i2++) {
                View findViewById = viewGroup.findViewById(((Integer) this.f5523k.get(i2)).intValue());
                if (findViewById != null) {
                    TransitionValues transitionValues = new TransitionValues(findViewById);
                    if (z) {
                        l(transitionValues);
                    } else {
                        i(transitionValues);
                    }
                    transitionValues.f5572c.add(this);
                    k(transitionValues);
                    if (z) {
                        e(this.v, findViewById, transitionValues);
                    } else {
                        e(this.w, findViewById, transitionValues);
                    }
                }
            }
            for (int i3 = 0; i3 < this.f5524l.size(); i3++) {
                View view = (View) this.f5524l.get(i3);
                TransitionValues transitionValues2 = new TransitionValues(view);
                if (z) {
                    l(transitionValues2);
                } else {
                    i(transitionValues2);
                }
                transitionValues2.f5572c.add(this);
                k(transitionValues2);
                if (z) {
                    e(this.v, view, transitionValues2);
                } else {
                    e(this.w, view, transitionValues2);
                }
            }
        } else {
            j(viewGroup, z);
        }
        if (z || (arrayMap = this.N) == null) {
            return;
        }
        int size = arrayMap.size();
        ArrayList arrayList3 = new ArrayList(size);
        for (int i4 = 0; i4 < size; i4++) {
            arrayList3.add((View) this.v.f5576d.remove((String) this.N.f(i4)));
        }
        for (int i5 = 0; i5 < size; i5++) {
            View view2 = (View) arrayList3.get(i5);
            if (view2 != null) {
                this.v.f5576d.put((String) this.N.j(i5), view2);
            }
        }
    }

    public void m0(TransitionPropagation transitionPropagation) {
        this.L = transitionPropagation;
    }

    void n(boolean z) {
        if (z) {
            this.v.f5573a.clear();
            this.v.f5574b.clear();
            this.v.f5575c.b();
        } else {
            this.w.f5573a.clear();
            this.w.f5574b.clear();
            this.w.f5575c.b();
        }
    }

    public Transition n0(long j2) {
        this.f5520h = j2;
        return this;
    }

    @Override // 
    /* renamed from: o, reason: merged with bridge method [inline-methods] */
    public Transition clone() {
        try {
            Transition transition = (Transition) super.clone();
            transition.K = new ArrayList();
            transition.v = new TransitionValuesMaps();
            transition.w = new TransitionValuesMaps();
            transition.z = null;
            transition.A = null;
            transition.Q = null;
            transition.I = this;
            transition.J = null;
            return transition;
        } catch (CloneNotSupportedException e2) {
            throw new RuntimeException(e2);
        }
    }

    protected void o0() {
        if (this.F == 0) {
            W(TransitionNotification.f5548a, false);
            this.H = false;
        }
        this.F++;
    }

    public Animator p(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return null;
    }

    String p0(String str) {
        StringBuilder sb = new StringBuilder(str);
        sb.append(getClass().getSimpleName());
        sb.append("@");
        sb.append(Integer.toHexString(hashCode()));
        sb.append(": ");
        if (this.f5521i != -1) {
            sb.append("dur(");
            sb.append(this.f5521i);
            sb.append(") ");
        }
        if (this.f5520h != -1) {
            sb.append("dly(");
            sb.append(this.f5520h);
            sb.append(") ");
        }
        if (this.f5522j != null) {
            sb.append("interp(");
            sb.append(this.f5522j);
            sb.append(") ");
        }
        if (this.f5523k.size() > 0 || this.f5524l.size() > 0) {
            sb.append("tgts(");
            if (this.f5523k.size() > 0) {
                for (int i2 = 0; i2 < this.f5523k.size(); i2++) {
                    if (i2 > 0) {
                        sb.append(", ");
                    }
                    sb.append(this.f5523k.get(i2));
                }
            }
            if (this.f5524l.size() > 0) {
                for (int i3 = 0; i3 < this.f5524l.size(); i3++) {
                    if (i3 > 0) {
                        sb.append(", ");
                    }
                    sb.append(this.f5524l.get(i3));
                }
            }
            sb.append(")");
        }
        return sb.toString();
    }

    void q(ViewGroup viewGroup, TransitionValuesMaps transitionValuesMaps, TransitionValuesMaps transitionValuesMaps2, ArrayList arrayList, ArrayList arrayList2) {
        Animator p2;
        int i2;
        int i3;
        View view;
        Animator animator;
        TransitionValues transitionValues;
        ArrayMap B = B();
        SparseIntArray sparseIntArray = new SparseIntArray();
        int size = arrayList.size();
        boolean z = A().Q != null;
        long j2 = Long.MAX_VALUE;
        int i4 = 0;
        while (i4 < size) {
            TransitionValues transitionValues2 = (TransitionValues) arrayList.get(i4);
            TransitionValues transitionValues3 = (TransitionValues) arrayList2.get(i4);
            if (transitionValues2 != null && !transitionValues2.f5572c.contains(this)) {
                transitionValues2 = null;
            }
            if (transitionValues3 != null && !transitionValues3.f5572c.contains(this)) {
                transitionValues3 = null;
            }
            if (!(transitionValues2 == null && transitionValues3 == null) && ((transitionValues2 == null || transitionValues3 == null || M(transitionValues2, transitionValues3)) && (p2 = p(viewGroup, transitionValues2, transitionValues3)) != null)) {
                if (transitionValues3 != null) {
                    view = transitionValues3.f5571b;
                    String[] J = J();
                    Animator animator2 = p2;
                    if (J != null && J.length > 0) {
                        transitionValues = new TransitionValues(view);
                        i2 = size;
                        TransitionValues transitionValues4 = (TransitionValues) transitionValuesMaps2.f5573a.get(view);
                        if (transitionValues4 != null) {
                            int i5 = 0;
                            while (i5 < J.length) {
                                Map map = transitionValues.f5570a;
                                int i6 = i4;
                                String str = J[i5];
                                map.put(str, transitionValues4.f5570a.get(str));
                                i5++;
                                i4 = i6;
                                J = J;
                            }
                        }
                        i3 = i4;
                        int size2 = B.size();
                        int i7 = 0;
                        while (true) {
                            if (i7 >= size2) {
                                break;
                            }
                            AnimationInfo animationInfo = (AnimationInfo) B.get((Animator) B.f(i7));
                            if (animationInfo.f5537c != null && animationInfo.f5535a == view && animationInfo.f5536b.equals(x()) && animationInfo.f5537c.equals(transitionValues)) {
                                animator2 = null;
                                break;
                            }
                            i7++;
                        }
                    } else {
                        i2 = size;
                        i3 = i4;
                        transitionValues = null;
                    }
                    animator = animator2;
                } else {
                    i2 = size;
                    i3 = i4;
                    view = transitionValues2.f5571b;
                    animator = p2;
                    transitionValues = null;
                }
                if (animator != null) {
                    TransitionPropagation transitionPropagation = this.L;
                    if (transitionPropagation != null) {
                        long c2 = transitionPropagation.c(viewGroup, this, transitionValues2, transitionValues3);
                        sparseIntArray.put(this.K.size(), (int) c2);
                        j2 = Math.min(c2, j2);
                    }
                    long j3 = j2;
                    AnimationInfo animationInfo2 = new AnimationInfo(view, x(), this, viewGroup.getWindowId(), transitionValues, animator);
                    if (z) {
                        AnimatorSet animatorSet = new AnimatorSet();
                        animatorSet.play(animator);
                        animator = animatorSet;
                    }
                    B.put(animator, animationInfo2);
                    this.K.add(animator);
                    j2 = j3;
                }
            } else {
                i2 = size;
                i3 = i4;
            }
            i4 = i3 + 1;
            size = i2;
        }
        if (sparseIntArray.size() != 0) {
            for (int i8 = 0; i8 < sparseIntArray.size(); i8++) {
                AnimationInfo animationInfo3 = (AnimationInfo) B.get((Animator) this.K.get(sparseIntArray.keyAt(i8)));
                animationInfo3.f5540f.setStartDelay((sparseIntArray.valueAt(i8) - j2) + animationInfo3.f5540f.getStartDelay());
            }
        }
    }

    protected void r() {
        int i2 = this.F - 1;
        this.F = i2;
        if (i2 == 0) {
            W(TransitionNotification.f5549b, false);
            for (int i3 = 0; i3 < this.v.f5575c.n(); i3++) {
                View view = (View) this.v.f5575c.o(i3);
                if (view != null) {
                    view.setHasTransientState(false);
                }
            }
            for (int i4 = 0; i4 < this.w.f5575c.n(); i4++) {
                View view2 = (View) this.w.f5575c.o(i4);
                if (view2 != null) {
                    view2.setHasTransientState(false);
                }
            }
            this.H = true;
        }
    }

    public long s() {
        return this.f5521i;
    }

    public Rect t() {
        EpicenterCallback epicenterCallback = this.M;
        if (epicenterCallback == null) {
            return null;
        }
        return epicenterCallback.a(this);
    }

    public String toString() {
        return p0("");
    }

    public EpicenterCallback u() {
        return this.M;
    }

    public TimeInterpolator v() {
        return this.f5522j;
    }

    TransitionValues w(View view, boolean z) {
        TransitionSet transitionSet = this.x;
        if (transitionSet != null) {
            return transitionSet.w(view, z);
        }
        ArrayList arrayList = z ? this.z : this.A;
        if (arrayList == null) {
            return null;
        }
        int size = arrayList.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                i2 = -1;
                break;
            }
            TransitionValues transitionValues = (TransitionValues) arrayList.get(i2);
            if (transitionValues == null) {
                return null;
            }
            if (transitionValues.f5571b == view) {
                break;
            }
            i2++;
        }
        if (i2 >= 0) {
            return (TransitionValues) (z ? this.A : this.z).get(i2);
        }
        return null;
    }

    public String x() {
        return this.f5519c;
    }

    public PathMotion y() {
        return this.O;
    }

    public TransitionPropagation z() {
        return this.L;
    }

    public Transition(@NonNull Context context, @NonNull AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, Styleable.f5510c);
        XmlResourceParser xmlResourceParser = (XmlResourceParser) attributeSet;
        long k2 = TypedArrayUtils.k(obtainStyledAttributes, xmlResourceParser, "duration", 1, -1);
        if (k2 >= 0) {
            h0(k2);
        }
        long k3 = TypedArrayUtils.k(obtainStyledAttributes, xmlResourceParser, "startDelay", 2, -1);
        if (k3 > 0) {
            n0(k3);
        }
        int l2 = TypedArrayUtils.l(obtainStyledAttributes, xmlResourceParser, "interpolator", 0, 0);
        if (l2 > 0) {
            j0(AnimationUtils.loadInterpolator(context, l2));
        }
        String m2 = TypedArrayUtils.m(obtainStyledAttributes, xmlResourceParser, "matchOrder", 3);
        if (m2 != null) {
            k0(X(m2));
        }
        obtainStyledAttributes.recycle();
    }
}
