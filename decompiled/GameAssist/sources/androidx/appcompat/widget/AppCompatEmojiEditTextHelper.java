package androidx.appcompat.widget;

import android.content.res.TypedArray;
import android.text.method.KeyListener;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import androidx.appcompat.R;
import androidx.emoji2.viewsintegration.EmojiEditTextHelper;

/* loaded from: classes.dex */
class AppCompatEmojiEditTextHelper {

    /* renamed from: a, reason: collision with root package name */
    private final EditText f761a;

    /* renamed from: b, reason: collision with root package name */
    private final EmojiEditTextHelper f762b;

    AppCompatEmojiEditTextHelper(EditText editText) {
        this.f761a = editText;
        this.f762b = new EmojiEditTextHelper(editText, false);
    }

    KeyListener a(KeyListener keyListener) {
        return b(keyListener) ? this.f762b.a(keyListener) : keyListener;
    }

    boolean b(KeyListener keyListener) {
        return !(keyListener instanceof NumberKeyListener);
    }

    void c(AttributeSet attributeSet, int i2) {
        TypedArray obtainStyledAttributes = this.f761a.getContext().obtainStyledAttributes(attributeSet, R.styleable.AppCompatTextView, i2, 0);
        try {
            boolean z = obtainStyledAttributes.hasValue(R.styleable.AppCompatTextView_emojiCompatEnabled) ? obtainStyledAttributes.getBoolean(R.styleable.AppCompatTextView_emojiCompatEnabled, true) : true;
            obtainStyledAttributes.recycle();
            e(z);
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    InputConnection d(InputConnection inputConnection, EditorInfo editorInfo) {
        return this.f762b.b(inputConnection, editorInfo);
    }

    void e(boolean z) {
        this.f762b.c(z);
    }
}
