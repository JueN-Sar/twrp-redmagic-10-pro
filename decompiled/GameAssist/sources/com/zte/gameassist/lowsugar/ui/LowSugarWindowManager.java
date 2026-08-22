package com.zte.gameassist.lowsugar.ui;

import android.content.Context;
import android.database.ContentObserver;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.WindowManager;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.lowsugar.LowSugarApplication;
import com.zte.gameassist.lowsugar.R;
import com.zte.gameassist.lowsugar.ui.LowSugarView;
import com.zte.gameassist.lowsugar.ui.LowSugarWindowManager;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.WindowManagerWrapper;

/* loaded from: classes2.dex */
public class LowSugarWindowManager {

    /* renamed from: l, reason: collision with root package name */
    private static volatile LowSugarWindowManager f16976l;

    /* renamed from: a, reason: collision with root package name */
    private Context f16977a;

    /* renamed from: b, reason: collision with root package name */
    private WindowManager f16978b;

    /* renamed from: c, reason: collision with root package name */
    private WindowManager.LayoutParams f16979c;

    /* renamed from: d, reason: collision with root package name */
    private int f16980d;

    /* renamed from: e, reason: collision with root package name */
    private int f16981e;

    /* renamed from: f, reason: collision with root package name */
    private LowSugarView f16982f;

    /* renamed from: g, reason: collision with root package name */
    private Handler f16983g;

    /* renamed from: h, reason: collision with root package name */
    private String f16984h;

    /* renamed from: i, reason: collision with root package name */
    private boolean f16985i = false;

    /* renamed from: j, reason: collision with root package name */
    private ContentObserver f16986j = new ContentObserver(null) { // from class: com.zte.gameassist.lowsugar.ui.LowSugarWindowManager.1
        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            super.onChange(z);
            if (Settings.System.getInt(LowSugarWindowManager.this.f16977a.getContentResolver(), "keyguard_is_showing", 0) == 1) {
                LowSugarWindowManager.this.i();
            }
        }
    };

    /* renamed from: k, reason: collision with root package name */
    private RotationMgr.Callback f16987k = new AnonymousClass2();

    /* renamed from: com.zte.gameassist.lowsugar.ui.LowSugarWindowManager$2, reason: invalid class name */
    class AnonymousClass2 implements RotationMgr.Callback {
        AnonymousClass2() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void b() {
            LowSugarWindowManager.this.i();
        }

        @Override // com.zte.gameassist.common.RotationMgr.Callback
        /* renamed from: onRotationChanged */
        public void y(int i2) {
            LowSugarWindowManager.this.f16983g.post(new Runnable() { // from class: com.zte.gameassist.lowsugar.ui.j
                @Override // java.lang.Runnable
                public final void run() {
                    LowSugarWindowManager.AnonymousClass2.this.b();
                }
            });
        }
    }

    private LowSugarWindowManager() {
        Context b2 = LowSugarApplication.c().b();
        this.f16977a = b2;
        this.f16978b = (WindowManager) b2.getSystemService("window");
        this.f16983g = new Handler(Looper.getMainLooper());
        e();
        n();
    }

    private void c() {
        if (this.f16979c == null) {
            return;
        }
        boolean j2 = RotationMgr.j();
        WindowManager.LayoutParams layoutParams = this.f16979c;
        int i2 = layoutParams.width;
        int i3 = layoutParams.height;
        if (j2 != (i2 > i3)) {
            layoutParams.width = i3;
            layoutParams.height = i2;
        }
    }

    public static LowSugarWindowManager d() {
        if (f16976l == null) {
            synchronized (LowSugarWindowManager.class) {
                try {
                    if (f16976l == null) {
                        f16976l = new LowSugarWindowManager();
                    }
                } finally {
                }
            }
        }
        return f16976l;
    }

    private void e() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(2008, 8718080, -3);
        this.f16979c = layoutParams;
        layoutParams.flags = (layoutParams.flags & (-131073)) | WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_COLOR_SPACE_AGNOSTIC;
        WindowManagerWrapper.LayoutParams.setFitInsetsTypes(layoutParams);
        WindowManager.LayoutParams layoutParams2 = this.f16979c;
        layoutParams2.gravity = 51;
        layoutParams2.setTitle("LowSugar");
        this.f16979c.packageName = this.f16977a.getPackageName();
    }

    private void f() {
        LowSugarView lowSugarView = (LowSugarView) InflaterHelper.f(R.layout.low_sugar_view, null);
        this.f16982f = lowSugarView;
        lowSugarView.u();
        this.f16982f.setRemoveViewListener(new LowSugarView.RemoveViewListener() { // from class: com.zte.gameassist.lowsugar.ui.i
        });
        this.f16982f.setSystemUiVisibility(6);
        this.f16984h = this.f16977a.getResources().getConfiguration().locale.getCountry();
    }

    private void h() {
        this.f16977a.getContentResolver().registerContentObserver(Settings.System.getUriFor("keyguard_is_showing"), true, this.f16986j);
    }

    private void l() {
        this.f16977a.getContentResolver().unregisterContentObserver(this.f16986j);
    }

    private void m() {
        if (this.f16982f == null) {
            return;
        }
        this.f16982f.M(RotationMgr.j());
        this.f16982f.I();
    }

    private void n() {
        int g2 = RotationMgr.g();
        int f2 = RotationMgr.f();
        boolean k2 = RotationMgr.k();
        this.f16981e = Math.max(g2, f2);
        int min = Math.min(g2, f2);
        this.f16980d = min;
        WindowManager.LayoutParams layoutParams = this.f16979c;
        layoutParams.height = k2 ? this.f16981e : min;
        if (!k2) {
            min = this.f16981e;
        }
        layoutParams.width = min;
        GaLog.e("LowSugarWindowManager", "updateLayoutParams() isPortrait = " + k2 + ", mLp = " + this.f16979c.height + "," + this.f16979c.width);
    }

    public boolean g() {
        return this.f16985i;
    }

    public void i() {
        if (this.f16982f == null) {
            return;
        }
        if (!this.f16983g.getLooper().isCurrentThread()) {
            this.f16983g.post(new Runnable() { // from class: com.zte.gameassist.lowsugar.ui.g
                @Override // java.lang.Runnable
                public final void run() {
                    LowSugarWindowManager.this.i();
                }
            });
            return;
        }
        GaLog.e("LowSugarWindowManager", "removeView mIsViewAdded: " + this.f16985i);
        if (this.f16985i) {
            this.f16982f.y();
            this.f16978b.removeView(this.f16982f);
            this.f16982f = null;
            this.f16985i = false;
            Settings.Global.putInt(this.f16977a.getContentResolver(), "nubia_game_window_show", 0);
            RotationMgr.e(this.f16977a).p(this.f16987k);
            l();
        }
    }

    public void j(Bitmap bitmap) {
        LowSugarView lowSugarView = this.f16982f;
        if (lowSugarView != null) {
            lowSugarView.setTestIconView(bitmap);
        }
    }

    public void k() {
        if (!this.f16983g.getLooper().isCurrentThread()) {
            this.f16983g.post(new Runnable() { // from class: com.zte.gameassist.lowsugar.ui.h
                @Override // java.lang.Runnable
                public final void run() {
                    LowSugarWindowManager.this.k();
                }
            });
            return;
        }
        String country = this.f16977a.getResources().getConfiguration().locale.getCountry();
        if (this.f16982f == null || (!TextUtils.isEmpty(country) && !country.equals(this.f16984h))) {
            GaLog.b("LowSugarWindowManager", "showView need initView");
            f();
        }
        n();
        c();
        m();
        GaLog.e("LowSugarWindowManager", "showView mIsViewAdded: " + this.f16985i);
        if (this.f16985i) {
            return;
        }
        this.f16983g.removeCallbacksAndMessages(null);
        this.f16978b.addView(this.f16982f, this.f16979c);
        this.f16982f.setId(R.id.low_sugar_root);
        this.f16985i = true;
        Settings.Global.putInt(this.f16977a.getContentResolver(), "nubia_game_window_show", 1);
        h();
    }
}
