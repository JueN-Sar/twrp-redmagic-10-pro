package cn.nubia.plugin.timer;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import cn.nubia.gameassist.GameAssistApplication;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.utils.Utils;
import cn.nubia.plugin.timer.TimerSettingPickerView;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;
import com.zte.shared.wrapper.WindowManagerWrapper;

/* loaded from: classes.dex */
public class TimerSettingWindow {

    /* renamed from: a, reason: collision with root package name */
    private WindowManager.LayoutParams f8760a;

    /* renamed from: b, reason: collision with root package name */
    private View f8761b;

    /* renamed from: h, reason: collision with root package name */
    private Context f8767h;

    /* renamed from: i, reason: collision with root package name */
    private WindowManager f8768i;

    /* renamed from: m, reason: collision with root package name */
    private TimerSettingPickerView f8772m;

    /* renamed from: p, reason: collision with root package name */
    private int f8775p;

    /* renamed from: q, reason: collision with root package name */
    private int f8776q;

    /* renamed from: c, reason: collision with root package name */
    private int f8762c = 300;

    /* renamed from: d, reason: collision with root package name */
    private int f8763d = 300;

    /* renamed from: e, reason: collision with root package name */
    private int f8764e = 0;

    /* renamed from: f, reason: collision with root package name */
    private int f8765f = 0;

    /* renamed from: g, reason: collision with root package name */
    private int f8766g = 0;

    /* renamed from: j, reason: collision with root package name */
    private String f8769j = "";

    /* renamed from: k, reason: collision with root package name */
    private boolean f8770k = false;

    /* renamed from: l, reason: collision with root package name */
    private final Handler f8771l = new Handler(Looper.getMainLooper());

    /* renamed from: n, reason: collision with root package name */
    private int f8773n = 0;

    /* renamed from: o, reason: collision with root package name */
    private boolean f8774o = false;

    public TimerSettingWindow(Context context) {
        this.f8767h = context;
        l();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public TimerItemData h() {
        return TimerMgr.r().s(this.f8773n);
    }

    private int i() {
        return r() ? this.f8766g : this.f8765f;
    }

    private int j(int i2) {
        int i3;
        int dimensionPixelSize;
        if (this.f8774o && r()) {
            return i2;
        }
        if (this.f8774o && !r()) {
            return i2;
        }
        if (this.f8774o || !r()) {
            i3 = i2 - this.f8765f;
            dimensionPixelSize = this.f8767h.getResources().getDimensionPixelSize(R.dimen.plugin_timer_item_height);
        } else {
            i3 = i2 - this.f8766g;
            dimensionPixelSize = this.f8767h.getResources().getDimensionPixelSize(R.dimen.plugin_timer_item_height);
        }
        return i3 + dimensionPixelSize;
    }

    private int k() {
        return (this.f8774o && r()) ? R.layout.plugin_timer_setting_down_short : (!this.f8774o || r()) ? (this.f8774o || !r()) ? R.layout.plugin_timer_setting_up_long : R.layout.plugin_timer_setting_up_short : R.layout.plugin_timer_setting_down_long;
    }

    private void m() {
        if (this.f8760a == null) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(2038, 75826952, -3);
            this.f8760a = layoutParams;
            layoutParams.width = this.f8764e;
            layoutParams.flags = (layoutParams.flags & (-131073)) | WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_COLOR_SPACE_AGNOSTIC;
            WindowManagerWrapper.LayoutParams.setFitInsetsTypes(layoutParams);
            WindowManager.LayoutParams layoutParams2 = this.f8760a;
            layoutParams2.gravity = 51;
            layoutParams2.setTitle("PluginTimerSetting");
            WindowManagerWrapper.LayoutParams.addHidePrivateTrustedOverlayFlags(this.f8760a);
        }
        this.f8760a.height = i();
        WindowManager.LayoutParams layoutParams3 = this.f8760a;
        layoutParams3.x = this.f8762c;
        layoutParams3.y = this.f8763d;
    }

    private void n() {
        TimerSettingPickerView timerSettingPickerView = (TimerSettingPickerView) this.f8761b.findViewById(R.id.plugin_timer_setting_picker);
        this.f8772m = timerSettingPickerView;
        timerSettingPickerView.setDisplayedValues(null);
        this.f8772m.setMinValue(1);
        this.f8772m.setMaxValue(90);
        this.f8772m.setValue(h().f8731c);
        this.f8772m.setFormatter(TimerSettingPickerView.getTwoDigitFormatter());
        this.f8772m.setOnValueChangedListener(new TimerSettingPickerView.OnValueChangeListener() { // from class: cn.nubia.plugin.timer.TimerSettingWindow.2
            @Override // cn.nubia.plugin.timer.TimerSettingPickerView.OnValueChangeListener
            public void a(TimerSettingPickerView timerSettingPickerView2, int i2, int i3) {
                TimerSettingWindow.this.f8775p = i3;
                int i4 = TimerSettingWindow.this.f8775p - 1;
                if (i4 == 0) {
                    TimerSettingWindow.this.h().f8731c = 90;
                } else {
                    TimerSettingWindow.this.h().f8731c = i4;
                }
                TimerMgr.r().z(TimerSettingWindow.this.f8773n);
                TimerSettingWindow.this.s();
                GaLog.e("TimerSettingWindow", "mSelectTime=" + TimerSettingWindow.this.f8775p);
            }
        });
    }

    private void o() {
        if (this.f8761b != null) {
            return;
        }
        View f2 = InflaterHelper.f(k(), null);
        this.f8761b = f2;
        f2.setOnTouchListener(new View.OnTouchListener(this) { // from class: cn.nubia.plugin.timer.TimerSettingWindow.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() != 4) {
                    return false;
                }
                GaLog.a("TimerSettingWindow", "Outside touch detected, hiding the window");
                TimerMgr.r().p();
                return false;
            }
        });
        q();
    }

    private void p() {
        this.f8761b.findViewById(R.id.plugin_timer_setting_switch).setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.plugin.timer.TimerSettingWindow.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                TimerSettingWindow.this.u();
            }
        });
    }

    private void q() {
        p();
        if (r()) {
            return;
        }
        n();
    }

    private boolean r() {
        return h().f8730b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        String str = h().f8730b ? "timing" : "countdown";
        int i2 = h().f8730b ? 0 : h().f8731c;
        Bundle bundle = new Bundle();
        bundle.putString("app_name", SystemMgr.t());
        bundle.putString("stype", str);
        bundle.putString("countdown_time", i2 + "");
        NubiaTrackManager.p().x("cn.nubia.gamelauncher", "game_stopwatch_set", bundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        TimerMgr.r().n(this.f8773n);
        g();
        this.f8770k = true;
        this.f8763d = j(this.f8776q);
        o();
        m();
        this.f8768i.addView(this.f8761b, this.f8760a);
        s();
    }

    public void g() {
        if (this.f8770k) {
            this.f8770k = false;
            this.f8768i.removeView(this.f8761b);
            this.f8761b = null;
            GaLog.e("TimerSettingWindow", "closePluginWindow: " + this.f8769j);
        }
    }

    public void l() {
        GameAssistApplication j2 = GameAssistApplication.j();
        this.f8767h = j2;
        this.f8768i = (WindowManager) j2.getSystemService(WindowManager.class);
        this.f8769j = Utils.j();
        this.f8764e = this.f8767h.getResources().getDimensionPixelSize(R.dimen.plugin_timer_setting_width);
        this.f8765f = this.f8767h.getResources().getDimensionPixelSize(R.dimen.plugin_timer_setting_long);
        this.f8766g = this.f8767h.getResources().getDimensionPixelSize(R.dimen.plugin_timer_setting_short);
    }

    public void t(int i2, int i3, int i4, boolean z) {
        this.f8773n = i2;
        this.f8774o = z;
        this.f8776q = i4;
        if (this.f8770k) {
            return;
        }
        this.f8770k = true;
        this.f8762c = i3 - (this.f8764e / 2);
        this.f8763d = j(i4);
        o();
        m();
        this.f8768i.addView(this.f8761b, this.f8760a);
        GaLog.e("TimerSettingWindow", "showPluginSettingWindow: x=" + this.f8762c + " y=" + this.f8763d);
    }
}
