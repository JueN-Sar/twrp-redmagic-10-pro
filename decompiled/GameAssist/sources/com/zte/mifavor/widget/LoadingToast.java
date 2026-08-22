package com.zte.mifavor.widget;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

/* loaded from: classes2.dex */
public class LoadingToast {

    /* renamed from: a, reason: collision with root package name */
    private final WindowManager.LayoutParams f17674a;

    /* renamed from: b, reason: collision with root package name */
    private int f17675b;

    /* renamed from: c, reason: collision with root package name */
    private int f17676c;

    /* renamed from: d, reason: collision with root package name */
    private int f17677d;

    /* renamed from: e, reason: collision with root package name */
    private float f17678e;

    /* renamed from: f, reason: collision with root package name */
    private float f17679f;

    /* renamed from: g, reason: collision with root package name */
    private View f17680g;

    /* renamed from: h, reason: collision with root package name */
    private View f17681h;

    /* renamed from: i, reason: collision with root package name */
    private WindowManager f17682i;

    /* renamed from: com.zte.mifavor.widget.LoadingToast$1, reason: invalid class name */
    class AnonymousClass1 implements Runnable {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ LoadingToast f17683c;

        @Override // java.lang.Runnable
        public void run() {
            this.f17683c.d();
        }
    }

    /* renamed from: com.zte.mifavor.widget.LoadingToast$2, reason: invalid class name */
    class AnonymousClass2 implements Runnable {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ LoadingToast f17684c;

        @Override // java.lang.Runnable
        public void run() {
            this.f17684c.c();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        View view = this.f17680g;
        if (view != null) {
            if (view.getParent() != null) {
                this.f17682i.removeViewImmediate(this.f17680g);
            }
            this.f17680g = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (this.f17680g == this.f17681h) {
            Log.w("LoadingToast", "handle Show mView=" + this.f17680g + ", mNextView=" + this.f17681h);
            return;
        }
        c();
        View view = this.f17681h;
        this.f17680g = view;
        int absoluteGravity = Gravity.getAbsoluteGravity(this.f17675b, view.getContext().getResources().getConfiguration().getLayoutDirection());
        WindowManager.LayoutParams layoutParams = this.f17674a;
        layoutParams.gravity = absoluteGravity;
        if ((absoluteGravity & 7) == 7) {
            layoutParams.horizontalWeight = 1.0f;
        }
        if ((absoluteGravity & 112) == 112) {
            layoutParams.verticalWeight = 1.0f;
        }
        layoutParams.x = this.f17676c;
        layoutParams.y = this.f17677d;
        layoutParams.verticalMargin = this.f17679f;
        layoutParams.horizontalMargin = this.f17678e;
        try {
            Log.d("LoadingToast", "handle Show x=" + this.f17674a.x + "y=" + this.f17674a.y + "width=" + this.f17674a.width + "height=" + this.f17674a.height + ", mView=" + this.f17680g + ", mParams=" + this.f17674a + ", mWM=" + this.f17682i);
            this.f17682i.addView(this.f17680g, this.f17674a);
        } catch (Exception e2) {
            Log.w("LoadingToast", "handle Show Exception :" + e2);
        }
    }
}
