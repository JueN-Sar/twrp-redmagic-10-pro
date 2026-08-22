package androidx.transition;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.core.content.res.TypedArrayUtils;
import androidx.transition.Transition;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class TransitionSet extends Transition {
    ArrayList W;
    private boolean X;
    int Y;
    boolean Z;
    private int a0;

    static class TransitionSetListener extends TransitionListenerAdapter {

        /* renamed from: c, reason: collision with root package name */
        TransitionSet f5565c;

        TransitionSetListener(TransitionSet transitionSet) {
            this.f5565c = transitionSet;
        }

        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
        public void c(Transition transition) {
            TransitionSet transitionSet = this.f5565c;
            if (transitionSet.Z) {
                return;
            }
            transitionSet.o0();
            this.f5565c.Z = true;
        }

        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
        public void f(Transition transition) {
            TransitionSet transitionSet = this.f5565c;
            int i2 = transitionSet.Y - 1;
            transitionSet.Y = i2;
            if (i2 == 0) {
                transitionSet.Z = false;
                transitionSet.r();
            }
            transition.b0(this);
        }
    }

    public TransitionSet() {
        this.W = new ArrayList();
        this.X = true;
        this.Z = false;
        this.a0 = 0;
    }

    private void D0() {
        TransitionSetListener transitionSetListener = new TransitionSetListener(this);
        Iterator it = this.W.iterator();
        while (it.hasNext()) {
            ((Transition) it.next()).a(transitionSetListener);
        }
        this.Y = this.W.size();
    }

    private void t0(Transition transition) {
        this.W.add(transition);
        transition.x = this;
    }

    private int w0(long j2) {
        for (int i2 = 1; i2 < this.W.size(); i2++) {
            if (((Transition) this.W.get(i2)).R > j2) {
                return i2 - 1;
            }
        }
        return this.W.size() - 1;
    }

    @Override // androidx.transition.Transition
    /* renamed from: A0, reason: merged with bridge method [inline-methods] */
    public TransitionSet j0(TimeInterpolator timeInterpolator) {
        this.a0 |= 1;
        ArrayList arrayList = this.W;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                ((Transition) this.W.get(i2)).j0(timeInterpolator);
            }
        }
        return (TransitionSet) super.j0(timeInterpolator);
    }

    public TransitionSet B0(int i2) {
        if (i2 == 0) {
            this.X = true;
        } else {
            if (i2 != 1) {
                throw new AndroidRuntimeException("Invalid parameter for TransitionSet ordering: " + i2);
            }
            this.X = false;
        }
        return this;
    }

    @Override // androidx.transition.Transition
    /* renamed from: C0, reason: merged with bridge method [inline-methods] */
    public TransitionSet n0(long j2) {
        return (TransitionSet) super.n0(j2);
    }

    @Override // androidx.transition.Transition
    boolean L() {
        for (int i2 = 0; i2 < this.W.size(); i2++) {
            if (((Transition) this.W.get(i2)).L()) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.transition.Transition
    public void Y(View view) {
        super.Y(view);
        int size = this.W.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((Transition) this.W.get(i2)).Y(view);
        }
    }

    @Override // androidx.transition.Transition
    void a0() {
        this.P = 0L;
        TransitionListenerAdapter transitionListenerAdapter = new TransitionListenerAdapter() { // from class: androidx.transition.TransitionSet.2
            @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
            public void g(Transition transition) {
                TransitionSet.this.W.remove(transition);
                if (TransitionSet.this.L()) {
                    return;
                }
                TransitionSet.this.W(Transition.TransitionNotification.f5550c, false);
                TransitionSet transitionSet = TransitionSet.this;
                transitionSet.H = true;
                transitionSet.W(Transition.TransitionNotification.f5549b, false);
            }
        };
        for (int i2 = 0; i2 < this.W.size(); i2++) {
            Transition transition = (Transition) this.W.get(i2);
            transition.a(transitionListenerAdapter);
            transition.a0();
            long I = transition.I();
            if (this.X) {
                this.P = Math.max(this.P, I);
            } else {
                long j2 = this.P;
                transition.R = j2;
                this.P = j2 + I;
            }
        }
    }

    @Override // androidx.transition.Transition
    public void d0(View view) {
        super.d0(view);
        int size = this.W.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((Transition) this.W.get(i2)).d0(view);
        }
    }

    @Override // androidx.transition.Transition
    protected void f0() {
        if (this.W.isEmpty()) {
            o0();
            r();
            return;
        }
        D0();
        if (this.X) {
            Iterator it = this.W.iterator();
            while (it.hasNext()) {
                ((Transition) it.next()).f0();
            }
            return;
        }
        for (int i2 = 1; i2 < this.W.size(); i2++) {
            Transition transition = (Transition) this.W.get(i2 - 1);
            final Transition transition2 = (Transition) this.W.get(i2);
            transition.a(new TransitionListenerAdapter() { // from class: androidx.transition.TransitionSet.1
                @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
                public void f(Transition transition3) {
                    transition2.f0();
                    transition3.b0(this);
                }
            });
        }
        Transition transition3 = (Transition) this.W.get(0);
        if (transition3 != null) {
            transition3.f0();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:44:? A[RETURN, SYNTHETIC] */
    @Override // androidx.transition.Transition
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void g0(long r19, long r21) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r3 = r21
            long r5 = r18.I()
            androidx.transition.TransitionSet r7 = r0.x
            r8 = 0
            if (r7 == 0) goto L21
            int r7 = (r1 > r8 ? 1 : (r1 == r8 ? 0 : -1))
            if (r7 >= 0) goto L18
            int r7 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
            if (r7 < 0) goto L20
        L18:
            int r7 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r7 <= 0) goto L21
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 <= 0) goto L21
        L20:
            return
        L21:
            int r7 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            r10 = 0
            if (r7 >= 0) goto L28
            r12 = 1
            goto L29
        L28:
            r12 = r10
        L29:
            int r13 = (r1 > r8 ? 1 : (r1 == r8 ? 0 : -1))
            if (r13 < 0) goto L31
            int r14 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
            if (r14 < 0) goto L39
        L31:
            int r14 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r14 > 0) goto L40
            int r14 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r14 <= 0) goto L40
        L39:
            r0.H = r10
            androidx.transition.Transition$TransitionNotification r14 = androidx.transition.Transition.TransitionNotification.f5548a
            r0.W(r14, r12)
        L40:
            boolean r14 = r0.X
            if (r14 == 0) goto L5d
        L44:
            java.util.ArrayList r7 = r0.W
            int r7 = r7.size()
            if (r10 >= r7) goto L5a
            java.util.ArrayList r7 = r0.W
            java.lang.Object r7 = r7.get(r10)
            androidx.transition.Transition r7 = (androidx.transition.Transition) r7
            r7.g0(r1, r3)
            int r10 = r10 + 1
            goto L44
        L5a:
            r16 = r12
            goto La5
        L5d:
            int r10 = r0.w0(r3)
            if (r7 < 0) goto L88
        L63:
            java.util.ArrayList r7 = r0.W
            int r7 = r7.size()
            if (r10 >= r7) goto L5a
            java.util.ArrayList r7 = r0.W
            java.lang.Object r7 = r7.get(r10)
            androidx.transition.Transition r7 = (androidx.transition.Transition) r7
            long r14 = r7.R
            r16 = r12
            long r11 = r1 - r14
            int r17 = (r11 > r8 ? 1 : (r11 == r8 ? 0 : -1))
            if (r17 >= 0) goto L7e
            goto La5
        L7e:
            long r14 = r3 - r14
            r7.g0(r11, r14)
            int r10 = r10 + 1
            r12 = r16
            goto L63
        L88:
            r16 = r12
        L8a:
            if (r10 < 0) goto La5
            java.util.ArrayList r7 = r0.W
            java.lang.Object r7 = r7.get(r10)
            androidx.transition.Transition r7 = (androidx.transition.Transition) r7
            long r11 = r7.R
            long r14 = r1 - r11
            long r11 = r3 - r11
            r7.g0(r14, r11)
            int r7 = (r14 > r8 ? 1 : (r14 == r8 ? 0 : -1))
            if (r7 < 0) goto La2
            goto La5
        La2:
            int r10 = r10 + (-1)
            goto L8a
        La5:
            androidx.transition.TransitionSet r7 = r0.x
            if (r7 == 0) goto Lc3
            int r1 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r1 <= 0) goto Lb1
            int r2 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r2 <= 0) goto Lb7
        Lb1:
            if (r13 >= 0) goto Lc3
            int r2 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
            if (r2 < 0) goto Lc3
        Lb7:
            if (r1 <= 0) goto Lbc
            r1 = 1
            r0.H = r1
        Lbc:
            androidx.transition.Transition$TransitionNotification r1 = androidx.transition.Transition.TransitionNotification.f5549b
            r11 = r16
            r0.W(r1, r11)
        Lc3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.transition.TransitionSet.g0(long, long):void");
    }

    @Override // androidx.transition.Transition
    protected void h() {
        super.h();
        int size = this.W.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((Transition) this.W.get(i2)).h();
        }
    }

    @Override // androidx.transition.Transition
    public void i(TransitionValues transitionValues) {
        if (O(transitionValues.f5571b)) {
            Iterator it = this.W.iterator();
            while (it.hasNext()) {
                Transition transition = (Transition) it.next();
                if (transition.O(transitionValues.f5571b)) {
                    transition.i(transitionValues);
                    transitionValues.f5572c.add(transition);
                }
            }
        }
    }

    @Override // androidx.transition.Transition
    public void i0(Transition.EpicenterCallback epicenterCallback) {
        super.i0(epicenterCallback);
        this.a0 |= 8;
        int size = this.W.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((Transition) this.W.get(i2)).i0(epicenterCallback);
        }
    }

    @Override // androidx.transition.Transition
    void k(TransitionValues transitionValues) {
        super.k(transitionValues);
        int size = this.W.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((Transition) this.W.get(i2)).k(transitionValues);
        }
    }

    @Override // androidx.transition.Transition
    public void l(TransitionValues transitionValues) {
        if (O(transitionValues.f5571b)) {
            Iterator it = this.W.iterator();
            while (it.hasNext()) {
                Transition transition = (Transition) it.next();
                if (transition.O(transitionValues.f5571b)) {
                    transition.l(transitionValues);
                    transitionValues.f5572c.add(transition);
                }
            }
        }
    }

    @Override // androidx.transition.Transition
    public void l0(PathMotion pathMotion) {
        super.l0(pathMotion);
        this.a0 |= 4;
        if (this.W != null) {
            for (int i2 = 0; i2 < this.W.size(); i2++) {
                ((Transition) this.W.get(i2)).l0(pathMotion);
            }
        }
    }

    @Override // androidx.transition.Transition
    public void m0(TransitionPropagation transitionPropagation) {
        super.m0(transitionPropagation);
        this.a0 |= 2;
        int size = this.W.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((Transition) this.W.get(i2)).m0(transitionPropagation);
        }
    }

    @Override // androidx.transition.Transition
    /* renamed from: o */
    public Transition clone() {
        TransitionSet transitionSet = (TransitionSet) super.clone();
        transitionSet.W = new ArrayList();
        int size = this.W.size();
        for (int i2 = 0; i2 < size; i2++) {
            transitionSet.t0(((Transition) this.W.get(i2)).clone());
        }
        return transitionSet;
    }

    @Override // androidx.transition.Transition
    String p0(String str) {
        String p0 = super.p0(str);
        for (int i2 = 0; i2 < this.W.size(); i2++) {
            StringBuilder sb = new StringBuilder();
            sb.append(p0);
            sb.append("\n");
            sb.append(((Transition) this.W.get(i2)).p0(str + "  "));
            p0 = sb.toString();
        }
        return p0;
    }

    @Override // androidx.transition.Transition
    void q(ViewGroup viewGroup, TransitionValuesMaps transitionValuesMaps, TransitionValuesMaps transitionValuesMaps2, ArrayList arrayList, ArrayList arrayList2) {
        long C = C();
        int size = this.W.size();
        for (int i2 = 0; i2 < size; i2++) {
            Transition transition = (Transition) this.W.get(i2);
            if (C > 0 && (this.X || i2 == 0)) {
                long C2 = transition.C();
                if (C2 > 0) {
                    transition.n0(C2 + C);
                } else {
                    transition.n0(C);
                }
            }
            transition.q(viewGroup, transitionValuesMaps, transitionValuesMaps2, arrayList, arrayList2);
        }
    }

    @Override // androidx.transition.Transition
    /* renamed from: q0, reason: merged with bridge method [inline-methods] */
    public TransitionSet a(Transition.TransitionListener transitionListener) {
        return (TransitionSet) super.a(transitionListener);
    }

    @Override // androidx.transition.Transition
    /* renamed from: r0, reason: merged with bridge method [inline-methods] */
    public TransitionSet b(View view) {
        for (int i2 = 0; i2 < this.W.size(); i2++) {
            ((Transition) this.W.get(i2)).b(view);
        }
        return (TransitionSet) super.b(view);
    }

    public TransitionSet s0(Transition transition) {
        t0(transition);
        long j2 = this.f5521i;
        if (j2 >= 0) {
            transition.h0(j2);
        }
        if ((this.a0 & 1) != 0) {
            transition.j0(v());
        }
        if ((this.a0 & 2) != 0) {
            transition.m0(z());
        }
        if ((this.a0 & 4) != 0) {
            transition.l0(y());
        }
        if ((this.a0 & 8) != 0) {
            transition.i0(u());
        }
        return this;
    }

    public Transition u0(int i2) {
        if (i2 < 0 || i2 >= this.W.size()) {
            return null;
        }
        return (Transition) this.W.get(i2);
    }

    public int v0() {
        return this.W.size();
    }

    @Override // androidx.transition.Transition
    /* renamed from: x0, reason: merged with bridge method [inline-methods] */
    public TransitionSet b0(Transition.TransitionListener transitionListener) {
        return (TransitionSet) super.b0(transitionListener);
    }

    @Override // androidx.transition.Transition
    /* renamed from: y0, reason: merged with bridge method [inline-methods] */
    public TransitionSet c0(View view) {
        for (int i2 = 0; i2 < this.W.size(); i2++) {
            ((Transition) this.W.get(i2)).c0(view);
        }
        return (TransitionSet) super.c0(view);
    }

    @Override // androidx.transition.Transition
    /* renamed from: z0, reason: merged with bridge method [inline-methods] */
    public TransitionSet h0(long j2) {
        ArrayList arrayList;
        super.h0(j2);
        if (this.f5521i >= 0 && (arrayList = this.W) != null) {
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                ((Transition) this.W.get(i2)).h0(j2);
            }
        }
        return this;
    }

    public TransitionSet(@NonNull Context context, @NonNull AttributeSet attributeSet) {
        super(context, attributeSet);
        this.W = new ArrayList();
        this.X = true;
        this.Z = false;
        this.a0 = 0;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, Styleable.f5516i);
        B0(TypedArrayUtils.k(obtainStyledAttributes, (XmlResourceParser) attributeSet, "transitionOrdering", 0, 0));
        obtainStyledAttributes.recycle();
    }
}
