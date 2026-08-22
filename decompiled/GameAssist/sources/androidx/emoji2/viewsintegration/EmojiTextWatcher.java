package androidx.emoji2.viewsintegration;

import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.emoji2.text.EmojiCompat;
import com.google.android.gms.common.api.Api;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

@RequiresApi
@RestrictTo
/* loaded from: classes.dex */
final class EmojiTextWatcher implements TextWatcher {

    /* renamed from: c, reason: collision with root package name */
    private final EditText f3865c;

    /* renamed from: h, reason: collision with root package name */
    private final boolean f3866h;

    /* renamed from: i, reason: collision with root package name */
    private EmojiCompat.InitCallback f3867i;

    /* renamed from: j, reason: collision with root package name */
    private int f3868j = Api.BaseClientBuilder.API_PRIORITY_OTHER;

    /* renamed from: k, reason: collision with root package name */
    private int f3869k = 0;

    /* renamed from: l, reason: collision with root package name */
    private boolean f3870l = true;

    @RequiresApi
    private static class InitCallbackImpl extends EmojiCompat.InitCallback {

        /* renamed from: a, reason: collision with root package name */
        private final Reference f3871a;

        InitCallbackImpl(EditText editText) {
            this.f3871a = new WeakReference(editText);
        }

        @Override // androidx.emoji2.text.EmojiCompat.InitCallback
        public void b() {
            super.b();
            EmojiTextWatcher.b((EditText) this.f3871a.get(), 1);
        }
    }

    EmojiTextWatcher(EditText editText, boolean z) {
        this.f3865c = editText;
        this.f3866h = z;
    }

    private EmojiCompat.InitCallback a() {
        if (this.f3867i == null) {
            this.f3867i = new InitCallbackImpl(this.f3865c);
        }
        return this.f3867i;
    }

    static void b(EditText editText, int i2) {
        if (i2 == 1 && editText != null && editText.isAttachedToWindow()) {
            Editable editableText = editText.getEditableText();
            int selectionStart = Selection.getSelectionStart(editableText);
            int selectionEnd = Selection.getSelectionEnd(editableText);
            EmojiCompat.c().p(editableText);
            EmojiInputFilter.b(editableText, selectionStart, selectionEnd);
        }
    }

    private boolean d() {
        return (this.f3870l && (this.f3866h || EmojiCompat.i())) ? false : true;
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
    }

    public void c(boolean z) {
        if (this.f3870l != z) {
            if (this.f3867i != null) {
                EmojiCompat.c().u(this.f3867i);
            }
            this.f3870l = z;
            if (z) {
                b(this.f3865c, EmojiCompat.c().e());
            }
        }
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        if (this.f3865c.isInEditMode() || d() || i3 > i4 || !(charSequence instanceof Spannable)) {
            return;
        }
        int e2 = EmojiCompat.c().e();
        if (e2 != 0) {
            if (e2 == 1) {
                EmojiCompat.c().s((Spannable) charSequence, i2, i2 + i4, this.f3868j, this.f3869k);
                return;
            } else if (e2 != 3) {
                return;
            }
        }
        EmojiCompat.c().t(a());
    }
}
