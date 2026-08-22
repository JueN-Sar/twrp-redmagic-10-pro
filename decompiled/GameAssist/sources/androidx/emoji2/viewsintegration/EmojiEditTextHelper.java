package androidx.emoji2.viewsintegration;

import android.text.method.KeyListener;
import android.text.method.NumberKeyListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import androidx.annotation.RequiresApi;
import androidx.core.util.Preconditions;
import com.google.android.gms.common.api.Api;

/* loaded from: classes.dex */
public final class EmojiEditTextHelper {

    /* renamed from: a, reason: collision with root package name */
    private final HelperInternal f3844a;

    /* renamed from: b, reason: collision with root package name */
    private int f3845b = Api.BaseClientBuilder.API_PRIORITY_OTHER;

    /* renamed from: c, reason: collision with root package name */
    private int f3846c = 0;

    static class HelperInternal {
        HelperInternal() {
        }

        KeyListener a(KeyListener keyListener) {
            return keyListener;
        }

        InputConnection b(InputConnection inputConnection, EditorInfo editorInfo) {
            return inputConnection;
        }

        void c(boolean z) {
        }
    }

    @RequiresApi
    private static class HelperInternal19 extends HelperInternal {

        /* renamed from: a, reason: collision with root package name */
        private final EditText f3847a;

        /* renamed from: b, reason: collision with root package name */
        private final EmojiTextWatcher f3848b;

        HelperInternal19(EditText editText, boolean z) {
            this.f3847a = editText;
            EmojiTextWatcher emojiTextWatcher = new EmojiTextWatcher(editText, z);
            this.f3848b = emojiTextWatcher;
            editText.addTextChangedListener(emojiTextWatcher);
            editText.setEditableFactory(EmojiEditableFactory.getInstance());
        }

        @Override // androidx.emoji2.viewsintegration.EmojiEditTextHelper.HelperInternal
        KeyListener a(KeyListener keyListener) {
            if (keyListener instanceof EmojiKeyListener) {
                return keyListener;
            }
            if (keyListener == null) {
                return null;
            }
            return keyListener instanceof NumberKeyListener ? keyListener : new EmojiKeyListener(keyListener);
        }

        @Override // androidx.emoji2.viewsintegration.EmojiEditTextHelper.HelperInternal
        InputConnection b(InputConnection inputConnection, EditorInfo editorInfo) {
            return inputConnection instanceof EmojiInputConnection ? inputConnection : new EmojiInputConnection(this.f3847a, inputConnection, editorInfo);
        }

        @Override // androidx.emoji2.viewsintegration.EmojiEditTextHelper.HelperInternal
        void c(boolean z) {
            this.f3848b.c(z);
        }
    }

    public EmojiEditTextHelper(EditText editText, boolean z) {
        Preconditions.i(editText, "editText cannot be null");
        this.f3844a = new HelperInternal19(editText, z);
    }

    public KeyListener a(KeyListener keyListener) {
        return this.f3844a.a(keyListener);
    }

    public InputConnection b(InputConnection inputConnection, EditorInfo editorInfo) {
        if (inputConnection == null) {
            return null;
        }
        return this.f3844a.b(inputConnection, editorInfo);
    }

    public void c(boolean z) {
        this.f3844a.c(z);
    }
}
