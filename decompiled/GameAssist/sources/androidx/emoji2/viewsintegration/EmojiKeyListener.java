package androidx.emoji2.viewsintegration;

import android.text.Editable;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.View;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.emoji2.text.EmojiCompat;

@RequiresApi
@RestrictTo
/* loaded from: classes.dex */
final class EmojiKeyListener implements KeyListener {

    /* renamed from: a, reason: collision with root package name */
    private final KeyListener f3858a;

    /* renamed from: b, reason: collision with root package name */
    private final EmojiCompatHandleKeyDownHelper f3859b;

    public static class EmojiCompatHandleKeyDownHelper {
        public boolean a(Editable editable, int i2, KeyEvent keyEvent) {
            return EmojiCompat.g(editable, i2, keyEvent);
        }
    }

    EmojiKeyListener(KeyListener keyListener) {
        this(keyListener, new EmojiCompatHandleKeyDownHelper());
    }

    @Override // android.text.method.KeyListener
    public void clearMetaKeyState(View view, Editable editable, int i2) {
        this.f3858a.clearMetaKeyState(view, editable, i2);
    }

    @Override // android.text.method.KeyListener
    public int getInputType() {
        return this.f3858a.getInputType();
    }

    @Override // android.text.method.KeyListener
    public boolean onKeyDown(View view, Editable editable, int i2, KeyEvent keyEvent) {
        return this.f3859b.a(editable, i2, keyEvent) || this.f3858a.onKeyDown(view, editable, i2, keyEvent);
    }

    @Override // android.text.method.KeyListener
    public boolean onKeyOther(View view, Editable editable, KeyEvent keyEvent) {
        return this.f3858a.onKeyOther(view, editable, keyEvent);
    }

    @Override // android.text.method.KeyListener
    public boolean onKeyUp(View view, Editable editable, int i2, KeyEvent keyEvent) {
        return this.f3858a.onKeyUp(view, editable, i2, keyEvent);
    }

    EmojiKeyListener(KeyListener keyListener, EmojiCompatHandleKeyDownHelper emojiCompatHandleKeyDownHelper) {
        this.f3858a = keyListener;
        this.f3859b = emojiCompatHandleKeyDownHelper;
    }
}
