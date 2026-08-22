package com.google.android.material.motion;

import android.os.Build;
import android.view.View;
import android.window.BackEvent;
import android.window.OnBackAnimationCallback;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.activity.BackEventCompat;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import java.util.Objects;

@RestrictTo
/* loaded from: classes.dex */
public final class MaterialBackOrchestrator {

    /* renamed from: a, reason: collision with root package name */
    private final BackCallbackDelegate f14813a;

    /* renamed from: b, reason: collision with root package name */
    private final MaterialBackHandler f14814b;

    /* renamed from: c, reason: collision with root package name */
    private final View f14815c;

    @RequiresApi
    private static class Api33BackCallbackDelegate implements BackCallbackDelegate {

        /* renamed from: a, reason: collision with root package name */
        private OnBackInvokedCallback f14816a;

        private Api33BackCallbackDelegate() {
        }

        @Override // com.google.android.material.motion.MaterialBackOrchestrator.BackCallbackDelegate
        @DoNotInline
        public void a(@NonNull View view) {
            OnBackInvokedDispatcher findOnBackInvokedDispatcher = view.findOnBackInvokedDispatcher();
            if (findOnBackInvokedDispatcher == null) {
                return;
            }
            findOnBackInvokedDispatcher.unregisterOnBackInvokedCallback(this.f14816a);
            this.f14816a = null;
        }

        @Override // com.google.android.material.motion.MaterialBackOrchestrator.BackCallbackDelegate
        @DoNotInline
        public void b(@NonNull MaterialBackHandler materialBackHandler, @NonNull View view, boolean z) {
            OnBackInvokedDispatcher findOnBackInvokedDispatcher;
            if (this.f14816a == null && (findOnBackInvokedDispatcher = view.findOnBackInvokedDispatcher()) != null) {
                OnBackInvokedCallback c2 = c(materialBackHandler);
                this.f14816a = c2;
                findOnBackInvokedDispatcher.registerOnBackInvokedCallback(z ? 1000000 : 0, c2);
            }
        }

        OnBackInvokedCallback c(final MaterialBackHandler materialBackHandler) {
            Objects.requireNonNull(materialBackHandler);
            return new OnBackInvokedCallback() { // from class: com.google.android.material.motion.a
                @Override // android.window.OnBackInvokedCallback
                public final void onBackInvoked() {
                    MaterialBackHandler.this.e();
                }
            };
        }

        boolean d() {
            return this.f14816a != null;
        }
    }

    @RequiresApi
    private static class Api34BackCallbackDelegate extends Api33BackCallbackDelegate {
        private Api34BackCallbackDelegate() {
            super();
        }

        @Override // com.google.android.material.motion.MaterialBackOrchestrator.Api33BackCallbackDelegate
        OnBackInvokedCallback c(final MaterialBackHandler materialBackHandler) {
            return new OnBackAnimationCallback() { // from class: com.google.android.material.motion.MaterialBackOrchestrator.Api34BackCallbackDelegate.1
                @Override // android.window.OnBackAnimationCallback
                public void onBackCancelled() {
                    if (Api34BackCallbackDelegate.this.d()) {
                        materialBackHandler.a();
                    }
                }

                @Override // android.window.OnBackInvokedCallback
                public void onBackInvoked() {
                    materialBackHandler.e();
                }

                @Override // android.window.OnBackAnimationCallback
                public void onBackProgressed(BackEvent backEvent) {
                    if (Api34BackCallbackDelegate.this.d()) {
                        materialBackHandler.d(new BackEventCompat(backEvent));
                    }
                }

                @Override // android.window.OnBackAnimationCallback
                public void onBackStarted(BackEvent backEvent) {
                    if (Api34BackCallbackDelegate.this.d()) {
                        materialBackHandler.c(new BackEventCompat(backEvent));
                    }
                }
            };
        }
    }

    private interface BackCallbackDelegate {
        void a(View view);

        void b(MaterialBackHandler materialBackHandler, View view, boolean z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MaterialBackOrchestrator(View view) {
        this((MaterialBackHandler) view, view);
    }

    private static BackCallbackDelegate a() {
        return Build.VERSION.SDK_INT >= 34 ? new Api34BackCallbackDelegate() : new Api33BackCallbackDelegate();
    }

    private void d(boolean z) {
        BackCallbackDelegate backCallbackDelegate = this.f14813a;
        if (backCallbackDelegate != null) {
            backCallbackDelegate.b(this.f14814b, this.f14815c, z);
        }
    }

    public boolean b() {
        return this.f14813a != null;
    }

    public void c() {
        d(false);
    }

    public void e() {
        d(true);
    }

    public void f() {
        BackCallbackDelegate backCallbackDelegate = this.f14813a;
        if (backCallbackDelegate != null) {
            backCallbackDelegate.a(this.f14815c);
        }
    }

    public MaterialBackOrchestrator(MaterialBackHandler materialBackHandler, View view) {
        this.f14813a = a();
        this.f14814b = materialBackHandler;
        this.f14815c = view;
    }
}
