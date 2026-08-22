package com.zte.mifavor.widget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.zte.extres.R;
import com.zte.mifavor.widget.TimePickerZTE;

/* loaded from: classes2.dex */
public class TimePickerDialogZTE extends AlertDialog implements DialogInterface.OnClickListener, TimePickerZTE.OnTimeChangedListener {

    /* renamed from: c, reason: collision with root package name */
    private final TimePickerZTE f17792c;

    /* renamed from: h, reason: collision with root package name */
    private final OnTimeSetListener f17793h;

    /* renamed from: i, reason: collision with root package name */
    private final boolean f17794i;

    /* renamed from: j, reason: collision with root package name */
    private TextView f17795j;

    /* renamed from: k, reason: collision with root package name */
    private boolean f17796k;

    /* renamed from: l, reason: collision with root package name */
    private String f17797l;

    /* renamed from: m, reason: collision with root package name */
    private BroadcastReceiver f17798m;

    /* renamed from: com.zte.mifavor.widget.TimePickerDialogZTE$1, reason: invalid class name */
    class AnonymousClass1 extends BroadcastReceiver {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ TimePickerDialogZTE f17799a;

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.CONFIGURATION_CHANGED")) {
                this.f17799a.g();
            }
        }
    }

    public interface OnTimeSetListener {
        void a(TimePickerZTE timePickerZTE, int i2, int i3);
    }

    private void i(TimePickerZTE timePickerZTE, int i2, int i3) {
        String f2;
        String valueOf;
        if (this.f17796k) {
            return;
        }
        if (this.f17794i) {
            f2 = "";
        } else {
            TimePickerZTE timePickerZTE2 = this.f17792c;
            f2 = i2 > 12 ? timePickerZTE2.f(1) : timePickerZTE2.f(0);
        }
        if (!this.f17794i && i2 > 12) {
            i2 -= 12;
        }
        String valueOf2 = String.valueOf(i2);
        if (valueOf2.length() == 1) {
            valueOf2 = "0" + valueOf2;
        }
        if (i3 < 10) {
            valueOf = "0" + String.valueOf(i3);
        } else {
            valueOf = String.valueOf(i3);
        }
        this.f17795j.setText(this.f17797l + f2 + " " + valueOf2 + ":" + valueOf);
    }

    @Override // com.zte.mifavor.widget.TimePickerZTE.OnTimeChangedListener
    public void b(TimePickerZTE timePickerZTE, int i2, int i3) {
        i(timePickerZTE, i2, i3);
    }

    public void g() {
        if (Utils.r(getContext())) {
            h(getContext().getResources().getDimensionPixelSize(R.dimen.mfvc_ic_txt_padding));
        } else {
            h(0);
        }
    }

    public void h(int i2) {
        LinearLayout timePickerContainer = this.f17792c.getTimePickerContainer();
        timePickerContainer.setPadding(timePickerContainer.getPaddingLeft(), i2, timePickerContainer.getPaddingRight(), i2);
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i2) {
        if (i2 == -2) {
            cancel();
            return;
        }
        if (i2 == -1 && this.f17793h != null) {
            this.f17792c.clearFocus();
            OnTimeSetListener onTimeSetListener = this.f17793h;
            TimePickerZTE timePickerZTE = this.f17792c;
            onTimeSetListener.a(timePickerZTE, timePickerZTE.getCurrentHour().intValue(), this.f17792c.getCurrentMinute().intValue());
        }
    }

    @Override // android.app.Dialog
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        int i2 = bundle.getInt("hour");
        int i3 = bundle.getInt("minute");
        this.f17792c.setIs24HourView(Boolean.valueOf(bundle.getBoolean("is24hour")));
        this.f17792c.setCurrentHour(Integer.valueOf(i2));
        this.f17792c.setCurrentMinute(Integer.valueOf(i3));
    }

    @Override // android.app.Dialog
    public Bundle onSaveInstanceState() {
        Bundle onSaveInstanceState = super.onSaveInstanceState();
        onSaveInstanceState.putInt("hour", this.f17792c.getCurrentHour().intValue());
        onSaveInstanceState.putInt("minute", this.f17792c.getCurrentMinute().intValue());
        onSaveInstanceState.putBoolean("is24hour", this.f17792c.i());
        return onSaveInstanceState;
    }

    @Override // android.app.Dialog
    public void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.CONFIGURATION_CHANGED");
        getContext().registerReceiver(this.f17798m, intentFilter);
    }

    @Override // android.app.Dialog
    public void onStop() {
        super.onStop();
        getContext().unregisterReceiver(this.f17798m);
    }

    @Override // com.zte.mifavor.widget.AlertDialog, android.app.Dialog
    public void show() {
        super.show();
        getButton(-1).setOnClickListener(new View.OnClickListener() { // from class: com.zte.mifavor.widget.TimePickerDialogZTE.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (TimePickerDialogZTE.this.f17792c.r()) {
                    if (TimePickerDialogZTE.this.f17793h != null) {
                        TimePickerDialogZTE.this.f17792c.clearFocus();
                        TimePickerDialogZTE.this.f17793h.a(TimePickerDialogZTE.this.f17792c, TimePickerDialogZTE.this.f17792c.getCurrentHour().intValue(), TimePickerDialogZTE.this.f17792c.getCurrentMinute().intValue());
                    }
                    TimePickerDialogZTE.this.dismiss();
                }
            }
        });
    }
}
