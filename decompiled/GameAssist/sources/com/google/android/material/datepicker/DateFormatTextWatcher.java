package com.google.android.material.datepicker;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import com.google.android.material.R;
import com.google.android.material.internal.TextWatcherAdapter;
import com.google.android.material.textfield.TextInputLayout;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes.dex */
abstract class DateFormatTextWatcher extends TextWatcherAdapter {

    /* renamed from: c, reason: collision with root package name */
    private final TextInputLayout f14448c;

    /* renamed from: h, reason: collision with root package name */
    private final String f14449h;

    /* renamed from: i, reason: collision with root package name */
    private final DateFormat f14450i;

    /* renamed from: j, reason: collision with root package name */
    private final CalendarConstraints f14451j;

    /* renamed from: k, reason: collision with root package name */
    private final String f14452k;

    /* renamed from: l, reason: collision with root package name */
    private final Runnable f14453l;

    /* renamed from: m, reason: collision with root package name */
    private Runnable f14454m;

    /* renamed from: n, reason: collision with root package name */
    private int f14455n = 0;

    DateFormatTextWatcher(final String str, DateFormat dateFormat, TextInputLayout textInputLayout, CalendarConstraints calendarConstraints) {
        this.f14449h = str;
        this.f14450i = dateFormat;
        this.f14448c = textInputLayout;
        this.f14451j = calendarConstraints;
        this.f14452k = textInputLayout.getContext().getString(R.string.mtrl_picker_out_of_range);
        this.f14453l = new Runnable() { // from class: com.google.android.material.datepicker.a
            @Override // java.lang.Runnable
            public final void run() {
                DateFormatTextWatcher.this.e(str);
            }
        };
    }

    private Runnable c(final long j2) {
        return new Runnable() { // from class: com.google.android.material.datepicker.b
            @Override // java.lang.Runnable
            public final void run() {
                DateFormatTextWatcher.this.d(j2);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d(long j2) {
        this.f14448c.setError(String.format(this.f14452k, i(DateStrings.c(j2))));
        f();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void e(String str) {
        TextInputLayout textInputLayout = this.f14448c;
        DateFormat dateFormat = this.f14450i;
        Context context = textInputLayout.getContext();
        textInputLayout.setError(context.getString(R.string.mtrl_picker_invalid_format) + "\n" + String.format(context.getString(R.string.mtrl_picker_invalid_format_use), i(str)) + "\n" + String.format(context.getString(R.string.mtrl_picker_invalid_format_example), i(dateFormat.format(new Date(UtcDates.k().getTimeInMillis())))));
        f();
    }

    private String i(String str) {
        return str.replace(' ', (char) 160);
    }

    @Override // com.google.android.material.internal.TextWatcherAdapter, android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        if (!Locale.getDefault().getLanguage().equals(Locale.KOREAN.getLanguage()) && editable.length() != 0 && editable.length() < this.f14449h.length() && editable.length() >= this.f14455n) {
            char charAt = this.f14449h.charAt(editable.length());
            if (Character.isLetterOrDigit(charAt)) {
                return;
            }
            editable.append(charAt);
        }
    }

    @Override // com.google.android.material.internal.TextWatcherAdapter, android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        this.f14455n = charSequence.length();
    }

    void f() {
    }

    abstract void g(Long l2);

    public void h(View view, Runnable runnable) {
        view.post(runnable);
    }

    @Override // com.google.android.material.internal.TextWatcherAdapter, android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        this.f14448c.removeCallbacks(this.f14453l);
        this.f14448c.removeCallbacks(this.f14454m);
        this.f14448c.setError(null);
        g(null);
        if (TextUtils.isEmpty(charSequence) || charSequence.length() < this.f14449h.length()) {
            return;
        }
        try {
            Date parse = this.f14450i.parse(charSequence.toString());
            this.f14448c.setError(null);
            long time = parse.getTime();
            if (this.f14451j.k().j(time) && this.f14451j.u(time)) {
                g(Long.valueOf(parse.getTime()));
                return;
            }
            Runnable c2 = c(time);
            this.f14454m = c2;
            h(this.f14448c, c2);
        } catch (ParseException unused) {
            h(this.f14448c, this.f14453l);
        }
    }
}
