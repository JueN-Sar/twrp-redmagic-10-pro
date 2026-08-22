package androidx.emoji2.text;

import android.text.Editable;
import android.text.SpanWatcher;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import androidx.annotation.RestrictTo;
import androidx.core.util.Preconditions;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestrictTo
/* loaded from: classes.dex */
public final class SpannableBuilder extends SpannableStringBuilder {

    /* renamed from: c, reason: collision with root package name */
    private final Class f3787c;

    /* renamed from: h, reason: collision with root package name */
    private final List f3788h;

    private static class WatcherWrapper implements TextWatcher, SpanWatcher {

        /* renamed from: c, reason: collision with root package name */
        final Object f3789c;

        /* renamed from: h, reason: collision with root package name */
        private final AtomicInteger f3790h = new AtomicInteger(0);

        WatcherWrapper(Object obj) {
            this.f3789c = obj;
        }

        private boolean b(Object obj) {
            return obj instanceof EmojiSpan;
        }

        final void a() {
            this.f3790h.incrementAndGet();
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            ((TextWatcher) this.f3789c).afterTextChanged(editable);
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            ((TextWatcher) this.f3789c).beforeTextChanged(charSequence, i2, i3, i4);
        }

        final void c() {
            this.f3790h.decrementAndGet();
        }

        @Override // android.text.SpanWatcher
        public void onSpanAdded(Spannable spannable, Object obj, int i2, int i3) {
            if (this.f3790h.get() <= 0 || !b(obj)) {
                ((SpanWatcher) this.f3789c).onSpanAdded(spannable, obj, i2, i3);
            }
        }

        @Override // android.text.SpanWatcher
        public void onSpanChanged(Spannable spannable, Object obj, int i2, int i3, int i4, int i5) {
            if (this.f3790h.get() <= 0 || !b(obj)) {
                ((SpanWatcher) this.f3789c).onSpanChanged(spannable, obj, i2, i3, i4, i5);
            }
        }

        @Override // android.text.SpanWatcher
        public void onSpanRemoved(Spannable spannable, Object obj, int i2, int i3) {
            if (this.f3790h.get() <= 0 || !b(obj)) {
                ((SpanWatcher) this.f3789c).onSpanRemoved(spannable, obj, i2, i3);
            }
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            ((TextWatcher) this.f3789c).onTextChanged(charSequence, i2, i3, i4);
        }
    }

    SpannableBuilder(Class cls, CharSequence charSequence) {
        super(charSequence);
        this.f3788h = new ArrayList();
        Preconditions.i(cls, "watcherClass cannot be null");
        this.f3787c = cls;
    }

    private void b() {
        for (int i2 = 0; i2 < this.f3788h.size(); i2++) {
            ((WatcherWrapper) this.f3788h.get(i2)).a();
        }
    }

    public static SpannableBuilder c(Class cls, CharSequence charSequence) {
        return new SpannableBuilder(cls, charSequence);
    }

    private void e() {
        for (int i2 = 0; i2 < this.f3788h.size(); i2++) {
            ((WatcherWrapper) this.f3788h.get(i2)).onTextChanged(this, 0, length(), length());
        }
    }

    private WatcherWrapper f(Object obj) {
        for (int i2 = 0; i2 < this.f3788h.size(); i2++) {
            WatcherWrapper watcherWrapper = (WatcherWrapper) this.f3788h.get(i2);
            if (watcherWrapper.f3789c == obj) {
                return watcherWrapper;
            }
        }
        return null;
    }

    private boolean g(Class cls) {
        return this.f3787c == cls;
    }

    private boolean h(Object obj) {
        return obj != null && g(obj.getClass());
    }

    private void i() {
        for (int i2 = 0; i2 < this.f3788h.size(); i2++) {
            ((WatcherWrapper) this.f3788h.get(i2)).c();
        }
    }

    public void a() {
        b();
    }

    public void d() {
        i();
        e();
    }

    @Override // android.text.SpannableStringBuilder, android.text.Spanned
    public int getSpanEnd(Object obj) {
        WatcherWrapper f2;
        if (h(obj) && (f2 = f(obj)) != null) {
            obj = f2;
        }
        return super.getSpanEnd(obj);
    }

    @Override // android.text.SpannableStringBuilder, android.text.Spanned
    public int getSpanFlags(Object obj) {
        WatcherWrapper f2;
        if (h(obj) && (f2 = f(obj)) != null) {
            obj = f2;
        }
        return super.getSpanFlags(obj);
    }

    @Override // android.text.SpannableStringBuilder, android.text.Spanned
    public int getSpanStart(Object obj) {
        WatcherWrapper f2;
        if (h(obj) && (f2 = f(obj)) != null) {
            obj = f2;
        }
        return super.getSpanStart(obj);
    }

    @Override // android.text.SpannableStringBuilder, android.text.Spanned
    public Object[] getSpans(int i2, int i3, Class cls) {
        if (!g(cls)) {
            return super.getSpans(i2, i3, cls);
        }
        WatcherWrapper[] watcherWrapperArr = (WatcherWrapper[]) super.getSpans(i2, i3, WatcherWrapper.class);
        Object[] objArr = (Object[]) Array.newInstance((Class<?>) cls, watcherWrapperArr.length);
        for (int i4 = 0; i4 < watcherWrapperArr.length; i4++) {
            objArr[i4] = watcherWrapperArr[i4].f3789c;
        }
        return objArr;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Spanned
    public int nextSpanTransition(int i2, int i3, Class cls) {
        if (cls == null || g(cls)) {
            cls = WatcherWrapper.class;
        }
        return super.nextSpanTransition(i2, i3, cls);
    }

    @Override // android.text.SpannableStringBuilder, android.text.Spannable
    public void removeSpan(Object obj) {
        WatcherWrapper watcherWrapper;
        if (h(obj)) {
            watcherWrapper = f(obj);
            if (watcherWrapper != null) {
                obj = watcherWrapper;
            }
        } else {
            watcherWrapper = null;
        }
        super.removeSpan(obj);
        if (watcherWrapper != null) {
            this.f3788h.remove(watcherWrapper);
        }
    }

    @Override // android.text.SpannableStringBuilder, android.text.Spannable
    public void setSpan(Object obj, int i2, int i3, int i4) {
        if (h(obj)) {
            WatcherWrapper watcherWrapper = new WatcherWrapper(obj);
            this.f3788h.add(watcherWrapper);
            obj = watcherWrapper;
        }
        super.setSpan(obj, i2, i3, i4);
    }

    @Override // android.text.SpannableStringBuilder, java.lang.CharSequence
    public CharSequence subSequence(int i2, int i3) {
        return new SpannableBuilder(this.f3787c, this, i2, i3);
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable
    public SpannableStringBuilder delete(int i2, int i3) {
        super.delete(i2, i3);
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable
    public SpannableStringBuilder insert(int i2, CharSequence charSequence) {
        super.insert(i2, charSequence);
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable
    public SpannableStringBuilder replace(int i2, int i3, CharSequence charSequence) {
        b();
        super.replace(i2, i3, charSequence);
        i();
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable
    public SpannableStringBuilder insert(int i2, CharSequence charSequence, int i3, int i4) {
        super.insert(i2, charSequence, i3, i4);
        return this;
    }

    SpannableBuilder(Class cls, CharSequence charSequence, int i2, int i3) {
        super(charSequence, i2, i3);
        this.f3788h = new ArrayList();
        Preconditions.i(cls, "watcherClass cannot be null");
        this.f3787c = cls;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable
    public SpannableStringBuilder replace(int i2, int i3, CharSequence charSequence, int i4, int i5) {
        b();
        super.replace(i2, i3, charSequence, i4, i5);
        i();
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable, java.lang.Appendable
    public SpannableStringBuilder append(CharSequence charSequence) {
        super.append(charSequence);
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable, java.lang.Appendable
    public SpannableStringBuilder append(char c2) {
        super.append(c2);
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable, java.lang.Appendable
    public SpannableStringBuilder append(CharSequence charSequence, int i2, int i3) {
        super.append(charSequence, i2, i3);
        return this;
    }

    @Override // android.text.SpannableStringBuilder
    public SpannableStringBuilder append(CharSequence charSequence, Object obj, int i2) {
        super.append(charSequence, obj, i2);
        return this;
    }
}
