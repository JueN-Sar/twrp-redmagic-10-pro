package com.google.android.material.internal;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextPaint;
import androidx.annotation.RestrictTo;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.resources.TextAppearanceFontCallback;
import java.lang.ref.WeakReference;

@RestrictTo
/* loaded from: classes.dex */
public class TextDrawableHelper {

    /* renamed from: c, reason: collision with root package name */
    private float f14781c;

    /* renamed from: d, reason: collision with root package name */
    private float f14782d;

    /* renamed from: g, reason: collision with root package name */
    private TextAppearance f14785g;

    /* renamed from: a, reason: collision with root package name */
    private final TextPaint f14779a = new TextPaint(1);

    /* renamed from: b, reason: collision with root package name */
    private final TextAppearanceFontCallback f14780b = new TextAppearanceFontCallback() { // from class: com.google.android.material.internal.TextDrawableHelper.1
        @Override // com.google.android.material.resources.TextAppearanceFontCallback
        public void a(int i2) {
            TextDrawableHelper.this.f14783e = true;
            TextDrawableDelegate textDrawableDelegate = (TextDrawableDelegate) TextDrawableHelper.this.f14784f.get();
            if (textDrawableDelegate != null) {
                textDrawableDelegate.a();
            }
        }

        @Override // com.google.android.material.resources.TextAppearanceFontCallback
        public void b(Typeface typeface, boolean z) {
            if (z) {
                return;
            }
            TextDrawableHelper.this.f14783e = true;
            TextDrawableDelegate textDrawableDelegate = (TextDrawableDelegate) TextDrawableHelper.this.f14784f.get();
            if (textDrawableDelegate != null) {
                textDrawableDelegate.a();
            }
        }
    };

    /* renamed from: e, reason: collision with root package name */
    private boolean f14783e = true;

    /* renamed from: f, reason: collision with root package name */
    private WeakReference f14784f = new WeakReference(null);

    public interface TextDrawableDelegate {
        void a();

        int[] getState();

        boolean onStateChange(int[] iArr);
    }

    public TextDrawableHelper(TextDrawableDelegate textDrawableDelegate) {
        j(textDrawableDelegate);
    }

    private float c(String str) {
        if (str == null) {
            return 0.0f;
        }
        return Math.abs(this.f14779a.getFontMetrics().ascent);
    }

    private float d(CharSequence charSequence) {
        if (charSequence == null) {
            return 0.0f;
        }
        return this.f14779a.measureText(charSequence, 0, charSequence.length());
    }

    private void i(String str) {
        this.f14781c = d(str);
        this.f14782d = c(str);
        this.f14783e = false;
    }

    public TextAppearance e() {
        return this.f14785g;
    }

    public float f(String str) {
        if (!this.f14783e) {
            return this.f14782d;
        }
        i(str);
        return this.f14782d;
    }

    public TextPaint g() {
        return this.f14779a;
    }

    public float h(String str) {
        if (!this.f14783e) {
            return this.f14781c;
        }
        i(str);
        return this.f14781c;
    }

    public void j(TextDrawableDelegate textDrawableDelegate) {
        this.f14784f = new WeakReference(textDrawableDelegate);
    }

    public void k(TextAppearance textAppearance, Context context) {
        if (this.f14785g != textAppearance) {
            this.f14785g = textAppearance;
            if (textAppearance != null) {
                textAppearance.n(context, this.f14779a, this.f14780b);
                TextDrawableDelegate textDrawableDelegate = (TextDrawableDelegate) this.f14784f.get();
                if (textDrawableDelegate != null) {
                    this.f14779a.drawableState = textDrawableDelegate.getState();
                }
                textAppearance.m(context, this.f14779a, this.f14780b);
                this.f14783e = true;
            }
            TextDrawableDelegate textDrawableDelegate2 = (TextDrawableDelegate) this.f14784f.get();
            if (textDrawableDelegate2 != null) {
                textDrawableDelegate2.a();
                textDrawableDelegate2.onStateChange(textDrawableDelegate2.getState());
            }
        }
    }

    public void l(boolean z) {
        this.f14783e = z;
    }

    public void m(boolean z) {
        this.f14783e = z;
    }

    public void n(Context context) {
        this.f14785g.m(context, this.f14779a, this.f14780b);
    }
}
