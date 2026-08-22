package androidx.emoji2.viewsintegration;

import android.graphics.Rect;
import android.text.method.TransformationMethod;
import android.view.View;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.emoji2.text.EmojiCompat;

@RequiresApi
@RestrictTo
/* loaded from: classes.dex */
class EmojiTransformationMethod implements TransformationMethod {

    /* renamed from: c, reason: collision with root package name */
    private final TransformationMethod f3872c;

    EmojiTransformationMethod(TransformationMethod transformationMethod) {
        this.f3872c = transformationMethod;
    }

    public TransformationMethod a() {
        return this.f3872c;
    }

    @Override // android.text.method.TransformationMethod
    public CharSequence getTransformation(CharSequence charSequence, View view) {
        if (view.isInEditMode()) {
            return charSequence;
        }
        TransformationMethod transformationMethod = this.f3872c;
        if (transformationMethod != null) {
            charSequence = transformationMethod.getTransformation(charSequence, view);
        }
        return (charSequence == null || EmojiCompat.c().e() != 1) ? charSequence : EmojiCompat.c().p(charSequence);
    }

    @Override // android.text.method.TransformationMethod
    public void onFocusChanged(View view, CharSequence charSequence, boolean z, int i2, Rect rect) {
        TransformationMethod transformationMethod = this.f3872c;
        if (transformationMethod != null) {
            transformationMethod.onFocusChanged(view, charSequence, z, i2, rect);
        }
    }
}
