package androidx.emoji2.viewsintegration;

import android.text.Editable;
import androidx.emoji2.text.SpannableBuilder;

/* loaded from: classes.dex */
final class EmojiEditableFactory extends Editable.Factory {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f3849a = new Object();

    /* renamed from: b, reason: collision with root package name */
    private static volatile Editable.Factory f3850b;

    /* renamed from: c, reason: collision with root package name */
    private static Class f3851c;

    private EmojiEditableFactory() {
        try {
            f3851c = Class.forName("android.text.DynamicLayout$ChangeWatcher", false, EmojiEditableFactory.class.getClassLoader());
        } catch (Throwable unused) {
        }
    }

    public static Editable.Factory getInstance() {
        if (f3850b == null) {
            synchronized (f3849a) {
                try {
                    if (f3850b == null) {
                        f3850b = new EmojiEditableFactory();
                    }
                } finally {
                }
            }
        }
        return f3850b;
    }

    @Override // android.text.Editable.Factory
    public Editable newEditable(CharSequence charSequence) {
        Class cls = f3851c;
        return cls != null ? SpannableBuilder.c(cls, charSequence) : super.newEditable(charSequence);
    }
}
