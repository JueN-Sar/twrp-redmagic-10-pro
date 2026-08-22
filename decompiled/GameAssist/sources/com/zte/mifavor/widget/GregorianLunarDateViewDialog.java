package com.zte.mifavor.widget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import com.zte.extres.R;
import com.zte.mifavor.widget.GregorianLunarDateView;
import java.util.Calendar;

/* loaded from: classes2.dex */
public class GregorianLunarDateViewDialog extends AlertDialog implements DialogInterface.OnClickListener, GregorianLunarDateView.OnDateChangedListener {

    /* renamed from: c, reason: collision with root package name */
    private final GregorianLunarDateView f17643c;

    /* renamed from: h, reason: collision with root package name */
    private final OnDateSetListener f17644h;

    /* renamed from: i, reason: collision with root package name */
    private final Calendar f17645i;

    /* renamed from: j, reason: collision with root package name */
    private BroadcastReceiver f17646j;

    /* renamed from: com.zte.mifavor.widget.GregorianLunarDateViewDialog$1, reason: invalid class name */
    class AnonymousClass1 extends BroadcastReceiver {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ GregorianLunarDateViewDialog f17647a;

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.CONFIGURATION_CHANGED")) {
                this.f17647a.e();
            }
        }
    }

    public interface OnDateSetListener {
        void a(GregorianLunarDateView gregorianLunarDateView, int i2, int i3, int i4);
    }

    private void g() {
        if (this.f17644h != null) {
            this.f17643c.clearFocus();
            NumberPickerZTE numberPickerZTE = (NumberPickerZTE) this.f17643c.getNumberPickerYear();
            if (numberPickerZTE == null || numberPickerZTE.getYearValue() != 0 || this.f17643c.getIsGregorian()) {
                OnDateSetListener onDateSetListener = this.f17644h;
                GregorianLunarDateView gregorianLunarDateView = this.f17643c;
                onDateSetListener.a(gregorianLunarDateView, gregorianLunarDateView.getYear(), this.f17643c.getMonth(), this.f17643c.getDayOfMonth());
            } else {
                this.f17644h.a(this.f17643c, numberPickerZTE.getValue(), ((NumberPickerZTE) this.f17643c.getNumberPickerMonth()).getValue(), ((NumberPickerZTE) this.f17643c.getNumberPickerDay()).getValue());
            }
        }
    }

    private void h(int i2, int i3, int i4) {
        this.f17645i.set(1, i2);
        this.f17645i.set(2, i3);
        this.f17645i.set(5, i4);
    }

    @Override // com.zte.mifavor.widget.GregorianLunarDateView.OnDateChangedListener
    public void a(GregorianLunarDateView gregorianLunarDateView, int i2, int i3, int i4) {
        if (Utils.w()) {
            return;
        }
        Log.d("GregorianLunarDateViewDialog", "dd onDateChanged   year:" + i2 + "  month:" + i3 + "   day:" + i4);
        this.f17645i.set(1, i2);
        this.f17645i.set(2, i3);
        this.f17645i.set(5, i4);
        this.f17643c.r(i2, i3, i4, this);
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
        this.f17643c.F(i2, i3);
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i2) {
        if (i2 != -1) {
            return;
        }
        g();
    }

    @Override // android.app.Dialog
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        int i2 = bundle.getInt("year");
        int i3 = bundle.getInt("month");
        int i4 = bundle.getInt("day");
        boolean z = bundle.getBoolean("isgregorian");
        this.f17645i.set(1, i2);
        this.f17645i.set(2, i3);
        this.f17645i.set(5, i4);
        this.f17643c.s(this.f17645i, z);
        this.f17643c.r(i2, i3, i4, this);
    }

    @Override // android.app.Dialog
    public Bundle onSaveInstanceState() {
        Bundle onSaveInstanceState = super.onSaveInstanceState();
        onSaveInstanceState.putInt("year", this.f17643c.getYear());
        onSaveInstanceState.putInt("month", this.f17643c.getMonth());
        onSaveInstanceState.putInt("day", this.f17643c.getDayOfMonth());
        onSaveInstanceState.putBoolean("isgregorian", this.f17643c.getIsGregorian());
        return onSaveInstanceState;
    }

    @Override // android.app.Dialog
    public void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.CONFIGURATION_CHANGED");
        getContext().registerReceiver(this.f17646j, intentFilter);
    }

    @Override // android.app.Dialog
    public void onStop() {
        super.onStop();
        getContext().unregisterReceiver(this.f17646j);
    }
}
