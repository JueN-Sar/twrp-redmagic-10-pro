package androidx.emoji2.viewsintegration;

import android.text.Editable;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.emoji2.text.EmojiCompat;

@RequiresApi
@RestrictTo
/* loaded from: classes.dex */
final class EmojiInputConnection extends InputConnectionWrapper {

    /* renamed from: a, reason: collision with root package name */
    private final TextView f3852a;

    /* renamed from: b, reason: collision with root package name */
    private final EmojiCompatDeleteHelper f3853b;

    public static class EmojiCompatDeleteHelper {
        public boolean a(InputConnection inputConnection, Editable editable, int i2, int i3, boolean z) {
            return EmojiCompat.f(inputConnection, editable, i2, i3, z);
        }

        public void b(EditorInfo editorInfo) {
            if (EmojiCompat.i()) {
                EmojiCompat.c().v(editorInfo);
            }
        }
    }

    EmojiInputConnection(TextView textView, InputConnection inputConnection, EditorInfo editorInfo) {
        this(textView, inputConnection, editorInfo, new EmojiCompatDeleteHelper());
    }

    private Editable a() {
        return this.f3852a.getEditableText();
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public boolean deleteSurroundingText(int i2, int i3) {
        return this.f3853b.a(this, a(), i2, i3, false) || super.deleteSurroundingText(i2, i3);
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public boolean deleteSurroundingTextInCodePoints(int i2, int i3) {
        return this.f3853b.a(this, a(), i2, i3, true) || super.deleteSurroundingTextInCodePoints(i2, i3);
    }

    EmojiInputConnection(TextView textView, InputConnection inputConnection, EditorInfo editorInfo, EmojiCompatDeleteHelper emojiCompatDeleteHelper) {
        super(inputConnection, false);
        this.f3852a = textView;
        this.f3853b = emojiCompatDeleteHelper;
        emojiCompatDeleteHelper.b(editorInfo);
    }
}
