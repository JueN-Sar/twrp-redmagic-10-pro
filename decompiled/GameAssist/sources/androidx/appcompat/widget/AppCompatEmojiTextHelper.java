package androidx.appcompat.widget;

import android.content.res.TypedArray;
import android.text.InputFilter;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.appcompat.R;
import androidx.emoji2.viewsintegration.EmojiTextViewHelper;

/* loaded from: classes.dex */
class AppCompatEmojiTextHelper {

    /* renamed from: a, reason: collision with root package name */
    private final TextView f763a;

    /* renamed from: b, reason: collision with root package name */
    private final EmojiTextViewHelper f764b;

    AppCompatEmojiTextHelper(TextView textView) {
        this.f763a = textView;
        this.f764b = new EmojiTextViewHelper(textView, false);
    }

    InputFilter[] a(InputFilter[] inputFilterArr) {
        return this.f764b.a(inputFilterArr);
    }

    public boolean b() {
        return this.f764b.b();
    }

    void c(AttributeSet attributeSet, int i2) {
        TypedArray obtainStyledAttributes = this.f763a.getContext().obtainStyledAttributes(attributeSet, R.styleable.AppCompatTextView, i2, 0);
        try {
            boolean z = obtainStyledAttributes.hasValue(R.styleable.AppCompatTextView_emojiCompatEnabled) ? obtainStyledAttributes.getBoolean(R.styleable.AppCompatTextView_emojiCompatEnabled, true) : true;
            obtainStyledAttributes.recycle();
            e(z);
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    void d(boolean z) {
        this.f764b.c(z);
    }

    void e(boolean z) {
        this.f764b.d(z);
    }

    public TransformationMethod f(TransformationMethod transformationMethod) {
        return this.f764b.e(transformationMethod);
    }
}
