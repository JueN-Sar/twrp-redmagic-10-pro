package com.zte.mifavor.widget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.zte.extres.R;
import com.zte.mifavor.widget.DatePickerZTE;
import java.util.Calendar;

/* loaded from: classes2.dex */
public class DatePickerDialogZTE extends AlertDialog implements DialogInterface.OnClickListener, DatePickerZTE.OnDateChangedListener {

    /* renamed from: c, reason: collision with root package name */
    private final DatePickerZTE f17599c;

    /* renamed from: h, reason: collision with root package name */
    private final Calendar f17600h;

    /* renamed from: i, reason: collision with root package name */
    private OnDateSetListener f17601i;

    /* renamed from: j, reason: collision with root package name */
    private TextView f17602j;

    /* renamed from: k, reason: collision with root package name */
    private LinearLayout f17603k;

    /* renamed from: l, reason: collision with root package name */
    private boolean f17604l;

    /* renamed from: m, reason: collision with root package name */
    private String f17605m;

    /* renamed from: n, reason: collision with root package name */
    private BroadcastReceiver f17606n;

    /* renamed from: com.zte.mifavor.widget.DatePickerDialogZTE$1, reason: invalid class name */
    class AnonymousClass1 extends BroadcastReceiver {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ DatePickerDialogZTE f17607a;

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.CONFIGURATION_CHANGED")) {
                this.f17607a.e();
            }
        }
    }

    public interface OnDateSetListener {
        void a(DatePickerZTE datePickerZTE, int i2, int i3, int i4);
    }

    private void g() {
        if (this.f17601i != null) {
            this.f17599c.clearFocus();
            OnDateSetListener onDateSetListener = this.f17601i;
            DatePickerZTE datePickerZTE = this.f17599c;
            onDateSetListener.a(datePickerZTE, datePickerZTE.getYear(), this.f17599c.getMonth(), this.f17599c.getDayOfMonth());
        }
    }

    private void h(int i2, int i3, int i4) {
        if (this.f17604l) {
            return;
        }
        this.f17600h.set(1, i2);
        this.f17600h.set(2, i3);
        this.f17600h.set(5, i4);
        String formatDateTime = DateUtils.formatDateTime(getContext(), this.f17600h.getTimeInMillis(), 98324);
        String formatDateTime2 = DateUtils.formatDateTime(getContext(), this.f17600h.getTimeInMillis(), 2);
        this.f17602j.setText(this.f17605m + formatDateTime + " " + formatDateTime2);
    }

    @Override // com.zte.mifavor.widget.DatePickerZTE.OnDateChangedListener
    public void c(DatePickerZTE datePickerZTE, int i2, int i3, int i4) {
        this.f17599c.k(i2, i3, i4, this);
        h(i2, i3, i4);
    }

    public void e() {
        if (Utils.r(getContext())) {
            f(getContext().getResources().getDimensionPixelSize(R.dimen.mfvc_ic_txt_padding), getContext().getResources().getDimensionPixelSize(R.dimen.mfvc_xlarge_padding));
        } else {
            f(0, 0);
        }
    }

    public void f(int i2, int i3) {
        LinearLayout linearLayout = this.f17603k;
        linearLayout.setPadding(linearLayout.getPaddingLeft(), this.f17603k.getPaddingTop(), this.f17603k.getPaddingRight(), i2);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f17602j.getLayoutParams();
        layoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin, layoutParams.rightMargin, i3);
        this.f17602j.setLayoutParams(layoutParams);
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i2) {
        if (i2 == -2) {
            cancel();
        } else {
            if (i2 != -1) {
                return;
            }
            g();
        }
    }

    @Override // android.app.Dialog
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        this.f17599c.k(bundle.getInt("year"), bundle.getInt("month"), bundle.getInt("day"), this);
    }

    @Override // android.app.Dialog
    public Bundle onSaveInstanceState() {
        Bundle onSaveInstanceState = super.onSaveInstanceState();
        onSaveInstanceState.putInt("year", this.f17599c.getYear());
        onSaveInstanceState.putInt("month", this.f17599c.getMonth());
        onSaveInstanceState.putInt("day", this.f17599c.getDayOfMonth());
        return onSaveInstanceState;
    }

    @Override // android.app.Dialog
    public void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.CONFIGURATION_CHANGED");
        getContext().registerReceiver(this.f17606n, intentFilter);
    }

    @Override // android.app.Dialog
    protected void onStop() {
        super.onStop();
        getContext().unregisterReceiver(this.f17606n);
    }
}
