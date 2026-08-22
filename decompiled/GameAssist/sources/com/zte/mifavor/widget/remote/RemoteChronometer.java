package com.zte.mifavor.widget.remote;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Chronometer;
import java.util.Locale;

/* loaded from: classes2.dex */
public class RemoteChronometer extends Chronometer {
    private boolean showSecondsOnly;
    private long targetDurationMillis;

    public RemoteChronometer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.targetDurationMillis = Long.MAX_VALUE;
        this.showSecondsOnly = false;
        init();
    }

    private String b(long j2) {
        long j3 = j2 / 1000;
        if (this.showSecondsOnly) {
            return String.format(Locale.getDefault(), "%d", Long.valueOf(j3));
        }
        return String.format(Locale.getDefault(), "%02d:%02d:%02d", Long.valueOf(j3 / 3600), Long.valueOf((j3 % 3600) / 60), Long.valueOf(j3 % 60));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(Chronometer chronometer) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        Log.d("RemoteChronometer", "now = " + elapsedRealtime);
        boolean isCountDown = isCountDown();
        long base = getBase();
        long j2 = isCountDown ? base - elapsedRealtime : elapsedRealtime - base;
        if (isCountDown()) {
            if (j2 <= 0) {
                setText(this.showSecondsOnly ? "0" : "00:00:00");
                stop();
                return;
            }
        } else if (j2 >= this.targetDurationMillis) {
            setText(b(j2));
            stop();
            return;
        }
        setText(b(j2));
    }

    private void init() {
        setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() { // from class: com.zte.mifavor.widget.remote.a
            @Override // android.widget.Chronometer.OnChronometerTickListener
            public final void onChronometerTick(Chronometer chronometer) {
                RemoteChronometer.this.c(chronometer);
            }
        });
    }

    public void setShowSecondsOnly(boolean z) {
        this.showSecondsOnly = z;
    }

    public void setTargetDuration(long j2) {
        this.targetDurationMillis = j2;
    }
}
