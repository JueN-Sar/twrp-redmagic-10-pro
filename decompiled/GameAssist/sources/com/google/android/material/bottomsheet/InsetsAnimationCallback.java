package com.google.android.material.bottomsheet;

import android.view.View;
import androidx.core.view.WindowInsetsAnimationCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.animation.AnimationUtils;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
class InsetsAnimationCallback extends WindowInsetsAnimationCompat.Callback {

    /* renamed from: c, reason: collision with root package name */
    private final View f14070c;

    /* renamed from: d, reason: collision with root package name */
    private int f14071d;

    /* renamed from: e, reason: collision with root package name */
    private int f14072e;

    /* renamed from: f, reason: collision with root package name */
    private final int[] f14073f;

    public InsetsAnimationCallback(View view) {
        super(0);
        this.f14073f = new int[2];
        this.f14070c = view;
    }

    @Override // androidx.core.view.WindowInsetsAnimationCompat.Callback
    public void b(WindowInsetsAnimationCompat windowInsetsAnimationCompat) {
        this.f14070c.setTranslationY(0.0f);
    }

    @Override // androidx.core.view.WindowInsetsAnimationCompat.Callback
    public void c(WindowInsetsAnimationCompat windowInsetsAnimationCompat) {
        this.f14070c.getLocationOnScreen(this.f14073f);
        this.f14071d = this.f14073f[1];
    }

    @Override // androidx.core.view.WindowInsetsAnimationCompat.Callback
    public WindowInsetsCompat d(WindowInsetsCompat windowInsetsCompat, List list) {
        Iterator it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            if ((((WindowInsetsAnimationCompat) it.next()).c() & WindowInsetsCompat.Type.a()) != 0) {
                this.f14070c.setTranslationY(AnimationUtils.c(this.f14072e, 0, r0.b()));
                break;
            }
        }
        return windowInsetsCompat;
    }

    @Override // androidx.core.view.WindowInsetsAnimationCompat.Callback
    public WindowInsetsAnimationCompat.BoundsCompat e(WindowInsetsAnimationCompat windowInsetsAnimationCompat, WindowInsetsAnimationCompat.BoundsCompat boundsCompat) {
        this.f14070c.getLocationOnScreen(this.f14073f);
        int i2 = this.f14071d - this.f14073f[1];
        this.f14072e = i2;
        this.f14070c.setTranslationY(i2);
        return boundsCompat;
    }
}
