package com.google.android.material.search;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import com.google.android.material.animation.AnimatableView;
import com.google.android.material.search.SearchBar;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/* loaded from: classes.dex */
class SearchBarAnimationHelper {

    /* renamed from: d, reason: collision with root package name */
    private Animator f14997d;

    /* renamed from: e, reason: collision with root package name */
    private Animator f14998e;

    /* renamed from: f, reason: collision with root package name */
    private boolean f14999f;

    /* renamed from: g, reason: collision with root package name */
    private boolean f15000g;

    /* renamed from: a, reason: collision with root package name */
    private final Set f14994a = new LinkedHashSet();

    /* renamed from: b, reason: collision with root package name */
    private final Set f14995b = new LinkedHashSet();

    /* renamed from: c, reason: collision with root package name */
    private final Set f14996c = new LinkedHashSet();

    /* renamed from: h, reason: collision with root package name */
    private boolean f15001h = true;

    /* renamed from: i, reason: collision with root package name */
    private Animator f15002i = null;

    /* renamed from: com.google.android.material.search.SearchBarAnimationHelper$1, reason: invalid class name */
    class AnonymousClass1 extends AnimatorListenerAdapter {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ SearchBarAnimationHelper f15003c;

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f15003c.e(new OnLoadAnimationInvocation() { // from class: com.google.android.material.search.b
                @Override // com.google.android.material.search.SearchBarAnimationHelper.OnLoadAnimationInvocation
                public final void a(SearchBar.OnLoadAnimationCallback onLoadAnimationCallback) {
                    onLoadAnimationCallback.a();
                }
            });
        }
    }

    /* renamed from: com.google.android.material.search.SearchBarAnimationHelper$2, reason: invalid class name */
    class AnonymousClass2 extends AnimatorListenerAdapter {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ View f15004c;

        /* renamed from: h, reason: collision with root package name */
        final /* synthetic */ Animator f15005h;

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f15004c.setVisibility(8);
            this.f15005h.start();
        }
    }

    /* renamed from: com.google.android.material.search.SearchBarAnimationHelper$3, reason: invalid class name */
    class AnonymousClass3 extends AnimatorListenerAdapter {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ SearchBarAnimationHelper f15006c;

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f15006c.f15002i = null;
        }
    }

    /* renamed from: com.google.android.material.search.SearchBarAnimationHelper$4, reason: invalid class name */
    class AnonymousClass4 extends AnimatorListenerAdapter {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ SearchBar f15007c;

        /* renamed from: h, reason: collision with root package name */
        final /* synthetic */ SearchBarAnimationHelper f15008h;

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f15008h.f14999f = false;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.f15007c.setVisibility(4);
        }
    }

    /* renamed from: com.google.android.material.search.SearchBarAnimationHelper$5, reason: invalid class name */
    class AnonymousClass5 extends AnimatorListenerAdapter {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ SearchBarAnimationHelper f15009c;

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f15009c.f15002i = null;
        }
    }

    /* renamed from: com.google.android.material.search.SearchBarAnimationHelper$6, reason: invalid class name */
    class AnonymousClass6 extends AnimatorListenerAdapter {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ SearchBar f15010c;

        /* renamed from: h, reason: collision with root package name */
        final /* synthetic */ SearchBarAnimationHelper f15011h;

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f15010c.setVisibility(0);
            this.f15011h.f15000g = false;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.f15010c.k0();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    interface OnLoadAnimationInvocation {
        void a(SearchBar.OnLoadAnimationCallback onLoadAnimationCallback);
    }

    SearchBarAnimationHelper() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(OnLoadAnimationInvocation onLoadAnimationInvocation) {
        Iterator it = this.f14994a.iterator();
        while (it.hasNext()) {
            onLoadAnimationInvocation.a((SearchBar.OnLoadAnimationCallback) it.next());
        }
    }

    void f(boolean z) {
        this.f15001h = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    void g(SearchBar searchBar) {
        Animator animator = this.f14997d;
        if (animator != null) {
            animator.end();
        }
        Animator animator2 = this.f14998e;
        if (animator2 != null) {
            animator2.end();
        }
        View centerView = searchBar.getCenterView();
        if (centerView instanceof AnimatableView) {
            ((AnimatableView) centerView).a();
        }
        if (centerView != 0) {
            centerView.setAlpha(0.0f);
        }
    }
}
