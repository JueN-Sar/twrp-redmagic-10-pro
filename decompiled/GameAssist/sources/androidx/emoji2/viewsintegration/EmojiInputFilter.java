package androidx.emoji2.viewsintegration;

import android.text.InputFilter;
import android.text.Selection;
import android.text.Spannable;
import android.text.Spanned;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.emoji2.text.EmojiCompat;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

@RequiresApi
@RestrictTo
/* loaded from: classes.dex */
final class EmojiInputFilter implements InputFilter {

    /* renamed from: a, reason: collision with root package name */
    private final TextView f3854a;

    /* renamed from: b, reason: collision with root package name */
    private EmojiCompat.InitCallback f3855b;

    @RequiresApi
    private static class InitCallbackImpl extends EmojiCompat.InitCallback {

        /* renamed from: a, reason: collision with root package name */
        private final Reference f3856a;

        /* renamed from: b, reason: collision with root package name */
        private final Reference f3857b;

        InitCallbackImpl(TextView textView, EmojiInputFilter emojiInputFilter) {
            this.f3856a = new WeakReference(textView);
            this.f3857b = new WeakReference(emojiInputFilter);
        }

        private boolean c(TextView textView, InputFilter inputFilter) {
            InputFilter[] filters;
            if (inputFilter == null || textView == null || (filters = textView.getFilters()) == null) {
                return false;
            }
            for (InputFilter inputFilter2 : filters) {
                if (inputFilter2 == inputFilter) {
                    return true;
                }
            }
            return false;
        }

        @Override // androidx.emoji2.text.EmojiCompat.InitCallback
        public void b() {
            CharSequence text;
            CharSequence p2;
            super.b();
            TextView textView = (TextView) this.f3856a.get();
            if (c(textView, (InputFilter) this.f3857b.get()) && textView.isAttachedToWindow() && text != (p2 = EmojiCompat.c().p((text = textView.getText())))) {
                int selectionStart = Selection.getSelectionStart(p2);
                int selectionEnd = Selection.getSelectionEnd(p2);
                textView.setText(p2);
                if (p2 instanceof Spannable) {
                    EmojiInputFilter.b((Spannable) p2, selectionStart, selectionEnd);
                }
            }
        }
    }

    EmojiInputFilter(TextView textView) {
        this.f3854a = textView;
    }

    private EmojiCompat.InitCallback a() {
        if (this.f3855b == null) {
            this.f3855b = new InitCallbackImpl(this.f3854a, this);
        }
        return this.f3855b;
    }

    static void b(Spannable spannable, int i2, int i3) {
        if (i2 >= 0 && i3 >= 0) {
            Selection.setSelection(spannable, i2, i3);
        } else if (i2 >= 0) {
            Selection.setSelection(spannable, i2);
        } else if (i3 >= 0) {
            Selection.setSelection(spannable, i3);
        }
    }

    @Override // android.text.InputFilter
    public CharSequence filter(CharSequence charSequence, int i2, int i3, Spanned spanned, int i4, int i5) {
        if (this.f3854a.isInEditMode()) {
            return charSequence;
        }
        int e2 = EmojiCompat.c().e();
        if (e2 != 0) {
            if (e2 == 1) {
                if ((i5 == 0 && i4 == 0 && spanned.length() == 0 && charSequence == this.f3854a.getText()) || charSequence == null) {
                    return charSequence;
                }
                if (i2 != 0 || i3 != charSequence.length()) {
                    charSequence = charSequence.subSequence(i2, i3);
                }
                return EmojiCompat.c().q(charSequence, 0, charSequence.length());
            }
            if (e2 != 3) {
                return charSequence;
            }
        }
        EmojiCompat.c().t(a());
        return charSequence;
    }
}
