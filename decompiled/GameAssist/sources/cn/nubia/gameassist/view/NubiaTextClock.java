package cn.nubia.gameassist.view;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.TypedArray;
import android.database.ContentObserver;
import android.icu.text.DateTimePatternGenerator;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.RemotableViewMethod;
import android.view.ViewDebug;
import android.widget.TextView;
import com.android.internal.R;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.TimeZone;

@SuppressLint({"AppCompatCustomView"})
/* loaded from: classes.dex */
public class NubiaTextClock extends TextView {

    @Deprecated
    public static final CharSequence DEFAULT_FORMAT_12_HOUR = "h:mm a";

    @Deprecated
    public static final CharSequence DEFAULT_FORMAT_24_HOUR = "H:mm";
    public static final char QUOTE = '\'';
    public static final char SECONDS = 's';

    @ViewDebug.ExportedProperty
    private CharSequence mFormat;
    private CharSequence mFormat12;
    private CharSequence mFormat24;
    private ContentObserver mFormatChangeObserver;

    @ViewDebug.ExportedProperty
    private boolean mHasSeconds;
    private final BroadcastReceiver mIntentReceiver;
    private boolean mRegistered;
    private boolean mShouldRunTicker;
    private final Runnable mTicker;
    private Calendar mTime;
    private String mTimeZone;

    public NubiaTextClock(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private static CharSequence h(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
        return charSequence == null ? charSequence2 == null ? charSequence3 : charSequence2 : charSequence;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        if (n()) {
            this.mFormat = h(this.mFormat24, this.mFormat12, k("Hm"));
        } else {
            this.mFormat = h(this.mFormat12, this.mFormat24, k("hm"));
        }
        boolean z = this.mHasSeconds;
        boolean l2 = l(this.mFormat, SECONDS);
        this.mHasSeconds = l2;
        if (!this.mShouldRunTicker || z == l2) {
            return;
        }
        this.mTicker.run();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j(String str) {
        if (str != null) {
            this.mTime = Calendar.getInstance(TimeZone.getTimeZone(str));
        } else {
            this.mTime = Calendar.getInstance();
        }
    }

    private String k(String str) {
        return DateTimePatternGenerator.getInstance(getContext().getResources().getConfiguration().locale).getBestPattern(str);
    }

    public static boolean l(CharSequence charSequence, char c2) {
        if (charSequence == null) {
            return false;
        }
        int length = charSequence.length();
        boolean z = false;
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = charSequence.charAt(i2);
            if (charAt == '\'') {
                z = !z;
            } else if (!z && charAt == c2) {
                return true;
            }
        }
        return false;
    }

    private void m() {
        if (this.mFormat12 == null) {
            this.mFormat12 = k("hm");
        }
        if (this.mFormat24 == null) {
            this.mFormat24 = k("Hm");
        }
        j(this.mTimeZone);
        i();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        this.mTime.setTimeInMillis(System.currentTimeMillis());
        setText(DateFormat.format(this.mFormat, this.mTime));
    }

    private void r() {
        if (this.mRegistered) {
            if (this.mFormatChangeObserver == null) {
                this.mFormatChangeObserver = new FormatChangeObserver(getHandler());
            }
            getContext().getContentResolver().registerContentObserver(Settings.System.getUriFor("time_12_24"), true, this.mFormatChangeObserver);
        }
    }

    private void s() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.TIME_SET");
        intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
        getContext().registerReceiver(this.mIntentReceiver, intentFilter, null, getHandler(), 2);
    }

    private void t() {
        if (this.mFormatChangeObserver != null) {
            getContext().getContentResolver().unregisterContentObserver(this.mFormatChangeObserver);
        }
    }

    private void u() {
        try {
            getContext().unregisterReceiver(this.mIntentReceiver);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public CharSequence getFormat() {
        return this.mFormat;
    }

    @ViewDebug.ExportedProperty
    public CharSequence getFormat12Hour() {
        return this.mFormat12;
    }

    @ViewDebug.ExportedProperty
    public CharSequence getFormat24Hour() {
        return this.mFormat24;
    }

    public String getTimeZone() {
        return this.mTimeZone;
    }

    public boolean n() {
        return DateFormat.is24HourFormat(getContext());
    }

    public void o() {
        if (this.mRegistered) {
            u();
            t();
            this.mRegistered = false;
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onVisibilityAggregated(boolean z) {
        super.onVisibilityAggregated(z);
        boolean z2 = this.mShouldRunTicker;
        if (!z2 && z) {
            this.mShouldRunTicker = true;
            this.mTicker.run();
        } else {
            if (!z2 || z) {
                return;
            }
            this.mShouldRunTicker = false;
            removeCallbacks(this.mTicker);
        }
    }

    public void p() {
        if (this.mRegistered) {
            return;
        }
        s();
        r();
        j(this.mTimeZone);
        this.mRegistered = true;
    }

    @RemotableViewMethod
    public void setFormat12Hour(CharSequence charSequence) {
        this.mFormat12 = charSequence;
        i();
        q();
    }

    @RemotableViewMethod
    public void setFormat24Hour(CharSequence charSequence) {
        this.mFormat24 = charSequence;
        i();
        q();
    }

    @RemotableViewMethod
    public void setTimeZone(String str) {
        this.mTimeZone = str;
        j(str);
        q();
    }

    private class FormatChangeObserver extends ContentObserver {
        public FormatChangeObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            NubiaTextClock.this.i();
            NubiaTextClock.this.q();
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            NubiaTextClock.this.i();
            NubiaTextClock.this.q();
        }
    }

    public NubiaTextClock(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public NubiaTextClock(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mIntentReceiver = new BroadcastReceiver() { // from class: cn.nubia.gameassist.view.NubiaTextClock.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if (NubiaTextClock.this.mTimeZone == null && "android.intent.action.TIMEZONE_CHANGED".equals(intent.getAction())) {
                    NubiaTextClock.this.j(intent.getStringExtra("time-zone"));
                } else if (!NubiaTextClock.this.mShouldRunTicker && ("android.intent.action.TIME_TICK".equals(intent.getAction()) || "android.intent.action.TIME_SET".equals(intent.getAction()))) {
                    return;
                }
                NubiaTextClock.this.q();
            }
        };
        this.mTicker = new Runnable() { // from class: cn.nubia.gameassist.view.NubiaTextClock.2
            @Override // java.lang.Runnable
            public void run() {
                NubiaTextClock.this.removeCallbacks(this);
                if (NubiaTextClock.this.mShouldRunTicker) {
                    NubiaTextClock.this.q();
                    Instant instant = NubiaTextClock.this.mTime.toInstant();
                    ZoneId zoneId = NubiaTextClock.this.mTime.getTimeZone().toZoneId();
                    long millis = Duration.between(instant, (NubiaTextClock.this.mHasSeconds ? instant.atZone(zoneId).plusSeconds(1L).withNano(0) : instant.atZone(zoneId).plusMinutes(1L).withSecond(0).withNano(0)).toInstant()).toMillis();
                    if (millis <= 0) {
                        millis = 1000;
                    }
                    NubiaTextClock.this.postDelayed(this, millis);
                }
            }
        };
        int[] iArr = R.styleable.TextClock;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i2, i3);
        saveAttributeDataForStyleable(context, iArr, attributeSet, obtainStyledAttributes, i2, i3);
        try {
            this.mFormat12 = obtainStyledAttributes.getText(0);
            this.mFormat24 = obtainStyledAttributes.getText(1);
            this.mTimeZone = obtainStyledAttributes.getString(2);
            obtainStyledAttributes.recycle();
            m();
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }
}
