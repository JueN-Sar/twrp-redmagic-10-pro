package androidx.core.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

/* loaded from: classes.dex */
public final class LayoutInflaterCompat {

    static class Factory2Wrapper implements LayoutInflater.Factory2 {

        /* renamed from: c, reason: collision with root package name */
        final LayoutInflaterFactory f3339c;

        @Override // android.view.LayoutInflater.Factory
        public View onCreateView(String str, Context context, AttributeSet attributeSet) {
            return this.f3339c.onCreateView(null, str, context, attributeSet);
        }

        public String toString() {
            return getClass().getName() + "{" + this.f3339c + "}";
        }

        @Override // android.view.LayoutInflater.Factory2
        public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
            return this.f3339c.onCreateView(view, str, context, attributeSet);
        }
    }

    public static void a(LayoutInflater layoutInflater, LayoutInflater.Factory2 factory2) {
        layoutInflater.setFactory2(factory2);
    }
}
