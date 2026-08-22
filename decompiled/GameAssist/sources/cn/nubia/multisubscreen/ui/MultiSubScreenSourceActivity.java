package cn.nubia.multisubscreen.ui;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.window.OnBackInvokedCallback;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.utils.ToastUtil;
import cn.nubia.multisubscreen.callback.StatusCallback;
import cn.nubia.multisubscreen.mgr.DistributeBusMgr;
import cn.nubia.multisubscreen.utils.MultiSubScreenUtils;
import com.zte.distbus.basetransfer.Constants;
import com.zte.gameassist.common.ThreadManager;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.SharedPreferencesUtil;
import com.zte.shared.wrapper.WindowManagerWrapper;

/* loaded from: classes.dex */
public class MultiSubScreenSourceActivity extends MultiSubScreenBaseActivity implements View.OnClickListener {

    /* renamed from: i, reason: collision with root package name */
    private TextView f8131i;

    /* renamed from: j, reason: collision with root package name */
    private TextView f8132j;

    /* renamed from: k, reason: collision with root package name */
    private TextView f8133k;

    /* renamed from: l, reason: collision with root package name */
    private SharedPreferencesUtil f8134l;

    /* renamed from: p, reason: collision with root package name */
    private ImageView f8138p;

    /* renamed from: q, reason: collision with root package name */
    private ImageView f8139q;

    /* renamed from: r, reason: collision with root package name */
    private ImageView f8140r;

    /* renamed from: s, reason: collision with root package name */
    private ImageView f8141s;
    private View t;
    private ToggleButton u;
    private Handler w;

    /* renamed from: m, reason: collision with root package name */
    private boolean f8135m = false;

    /* renamed from: n, reason: collision with root package name */
    private boolean f8136n = true;

    /* renamed from: o, reason: collision with root package name */
    private boolean f8137o = false;
    private int v = 0;
    private OnBackInvokedCallback x = new OnBackInvokedCallback(this) { // from class: cn.nubia.multisubscreen.ui.MultiSubScreenSourceActivity.1
        @Override // android.window.OnBackInvokedCallback
        public void onBackInvoked() {
            GaLog.e("MultiSubScreen_MultiSubScreenSourceActivity", "mOnBackInvokedCallback onBackInvoked showDisconnectDialog doNothing!");
        }
    };
    private StatusCallback y = new StatusCallback() { // from class: cn.nubia.multisubscreen.ui.MultiSubScreenSourceActivity.2
        @Override // cn.nubia.multisubscreen.callback.StatusCallback
        public void b(String str, int i2) {
            if (MultiSubScreenSourceActivity.this.f8135m) {
                return;
            }
            MultiSubScreenSourceActivity.this.D();
        }
    };
    private ContentObserver z = new ContentObserver(new Handler(ThreadManager.c().f())) { // from class: cn.nubia.multisubscreen.ui.MultiSubScreenSourceActivity.3
        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            GaLog.a("MultiSubScreen_MultiSubScreenSourceActivity", "mDbKillGameLauncherObserver onChange uri = " + uri);
            if (Settings.Global.getInt(MultiSubScreenSourceActivity.this.getContentResolver(), "gcs_need_kill_game_launcher", 0) == 1) {
                MultiSubScreenSourceActivity.this.finish();
            }
        }
    };
    private ContentObserver A = new ContentObserver(null) { // from class: cn.nubia.multisubscreen.ui.MultiSubScreenSourceActivity.4
        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            GaLog.a("MultiSubScreen_MultiSubScreenSourceActivity", "mMultiSubScreenEnableObserver onChange uri = " + uri);
            MultiSubScreenSourceActivity.this.w.post(new Runnable() { // from class: cn.nubia.multisubscreen.ui.MultiSubScreenSourceActivity.4.1
                @Override // java.lang.Runnable
                public void run() {
                    MultiSubScreenSourceActivity.this.H(Settings.Global.getInt(MultiSubScreenSourceActivity.this.getContentResolver(), "multi_sub_screen_enable", 0) == 1);
                }
            });
        }
    };

    private void A() {
        findViewById(R.id.multi_sub_screen_international_guide_view).setVisibility(8);
        findViewById(R.id.multi_sub_screen_domestic_guide_view).setVisibility(0);
        this.f8133k.setVisibility(0);
    }

    private void B() {
        findViewById(R.id.multi_sub_screen_domestic_guide_view).setVisibility(8);
        findViewById(R.id.multi_sub_screen_international_guide_view).setVisibility(0);
        this.f8133k.setVisibility(8);
    }

    private void C() {
        this.f8131i.setVisibility(8);
        this.f8132j.setText(R.string.connect_sencond_screen);
        this.f8133k.setText(R.string.connect_sencond_screen);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void D() {
        if (MultiSubScreenUtils.f8174d == 2) {
            z();
        } else {
            C();
        }
    }

    private void E() {
        try {
            startActivity(new Intent("com.android.settings.action.NewMultiScreenActivity").setFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED));
        } catch (ActivityNotFoundException e2) {
            GaLog.b("MultiSubScreen_MultiSubScreenSourceActivity", "can not start multi screen activity！");
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void F(boolean z, boolean z2) {
        this.u.setChecked(z);
        if (z2) {
            w(this.f8141s, this.f8140r, this.f8138p, this.f8139q, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void G(boolean z) {
        Settings.Global.putInt(getContentResolver(), "multi_sub_screen_enable", z ? 1 : 0);
        Settings.System.putInt(getContentResolver(), Constants.SETTING_SWITCH_MODE, z ? 1 : 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void H(boolean z) {
        if (z) {
            this.f8132j.setEnabled(true);
            this.f8132j.setAlpha(1.0f);
        } else {
            this.f8132j.setEnabled(false);
            this.f8132j.setAlpha(0.3f);
        }
    }

    private CompoundButton.OnCheckedChangeListener m() {
        return new CompoundButton.OnCheckedChangeListener() { // from class: cn.nubia.multisubscreen.ui.MultiSubScreenSourceActivity.5
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                MultiSubScreenSourceActivity.this.F(z, true);
                MultiSubScreenSourceActivity.this.G(z);
            }
        };
    }

    private void o() {
        this.f8134l.b0(false);
        this.f8135m = false;
        if (this.f8136n) {
            v();
            D();
        } else {
            finish();
            if (this.f8137o || ZteFeature.IS_INTER_VERSION) {
                y();
            } else {
                E();
            }
        }
        getOnBackInvokedDispatcher().unregisterOnBackInvokedCallback(this.x);
    }

    private int p() {
        return Settings.System.getInt(getContentResolver(), Constants.SETTING_SWITCH_MODE, 0);
    }

    private void q() {
        this.f8137o = p() == 1;
        GaLog.a("MultiSubScreen_MultiSubScreenSourceActivity", "checkServiceManagerSwitch, mServiceManagerSwitch: " + this.f8137o);
        Intent intent = getIntent();
        SharedPreferencesUtil k2 = SharedPreferencesUtil.k(this);
        this.f8134l = k2;
        this.f8135m = k2.s();
        boolean booleanExtra = intent.getBooleanExtra("IS_OPEN_SOURCE_ALT", true);
        this.f8136n = booleanExtra;
        if (this.f8135m) {
            u();
            return;
        }
        if (booleanExtra) {
            v();
            return;
        }
        finish();
        if (this.f8137o || ZteFeature.IS_INTER_VERSION) {
            y();
        } else {
            E();
        }
    }

    private void r() {
        findViewById(R.id.multi_sub_screen_first_guide_finish).setOnClickListener(this);
        View findViewById = findViewById(R.id.multi_sub_screen_first_guide_title);
        if (!this.f8136n) {
            findViewById.setVisibility(4);
        } else {
            findViewById(R.id.multi_sub_screen_first_guide_back).setOnClickListener(this);
            findViewById.setVisibility(0);
        }
    }

    private void s() {
        MultiSubScreenUtils.C(this.y);
    }

    private void t() {
        TextView textView = (TextView) findViewById(R.id.multi_sub_screen_connect_device);
        this.f8132j = textView;
        textView.setOnClickListener(this);
        TextView textView2 = (TextView) findViewById(R.id.multi_sub_screen_domestic_connect_device);
        this.f8133k = textView2;
        textView2.setOnClickListener(this);
        findViewById(R.id.multi_sub_screen_source_back).setOnClickListener(this);
        this.f8131i = (TextView) findViewById(R.id.multi_sub_screen_connected_device_name);
        if (!ZteFeature.IS_INTER_VERSION) {
            A();
        } else {
            B();
            x();
        }
    }

    private void u() {
        setContentView(R.layout.multi_sub_screen_source_first_guide);
        r();
    }

    private void v() {
        setContentView(R.layout.multi_sub_screen_source_activity);
        t();
        s();
    }

    private void w(View view, View view2, View view3, View view4, boolean z) {
        AnimatorSet animatorSet = new AnimatorSet();
        float applyDimension = TypedValue.applyDimension(1, 30.0f, getResources().getDisplayMetrics());
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "translationX", 0.0f, applyDimension);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view2, "translationX", 0.0f, applyDimension);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(view, "translationX", applyDimension, 0.0f);
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(view2, "translationX", applyDimension, 0.0f);
        ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(view, "alpha", 0.0f, 1.0f);
        ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat(view2, "alpha", 0.0f, 1.0f);
        ObjectAnimator ofFloat7 = ObjectAnimator.ofFloat(view3, "alpha", 0.0f, 1.0f);
        ObjectAnimator ofFloat8 = ObjectAnimator.ofFloat(view4, "alpha", 0.0f, 1.0f);
        ObjectAnimator ofFloat9 = ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.0f);
        ObjectAnimator ofFloat10 = ObjectAnimator.ofFloat(view2, "alpha", 1.0f, 0.0f);
        ObjectAnimator ofFloat11 = ObjectAnimator.ofFloat(view3, "alpha", 1.0f, 0.0f);
        ObjectAnimator ofFloat12 = ObjectAnimator.ofFloat(view4, "alpha", 1.0f, 0.0f);
        int i2 = this.v;
        long j2 = i2 == 0 ? 0L : 250L;
        if (i2 == 0) {
            this.v = i2 + 1;
        }
        animatorSet.setDuration(j2);
        ObjectAnimator objectAnimator = z ? ofFloat : ofFloat3;
        if (!z) {
            ofFloat2 = ofFloat4;
        }
        if (z) {
            ofFloat5 = ofFloat9;
        }
        if (!z) {
            ofFloat6 = ofFloat10;
        }
        if (z) {
            ofFloat7 = ofFloat11;
        }
        if (!z) {
            ofFloat8 = ofFloat12;
        }
        animatorSet.playTogether(objectAnimator, ofFloat2, ofFloat5, ofFloat6, ofFloat7, ofFloat8);
        animatorSet.start();
    }

    private void x() {
        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.multi_sub_screen_switch);
        this.u = toggleButton;
        toggleButton.setClickable(true);
        this.t = findViewById(R.id.switch_button_layout);
        this.f8138p = (ImageView) findViewById(R.id.switch_button_track_black);
        this.f8139q = (ImageView) findViewById(R.id.switch_button_track_gray);
        this.f8140r = (ImageView) findViewById(R.id.switch_button_thumb_red);
        this.f8141s = (ImageView) findViewById(R.id.switch_button_thumb_black);
        this.t.setAlpha(1.0f);
        this.u.setOnCheckedChangeListener(m());
    }

    private void y() {
        startActivity(new Intent(this, (Class<?>) ChooseDeviceAlertAty.class));
    }

    private void z() {
        if (MultiSubScreenUtils.k() == null && TextUtils.isEmpty(DistributeBusMgr.getInstance().getSinkDeviceId())) {
            return;
        }
        TextView textView = this.f8131i;
        if (textView != null) {
            textView.setVisibility(0);
            this.f8131i.setText(getString(R.string.connected_device_name, new Object[]{MultiSubScreenUtils.k() == null ? MultiSubScreenUtils.i(DistributeBusMgr.getInstance().getSinkDeviceId()) : MultiSubScreenUtils.k().getName()}));
        }
        this.f8132j.setText(R.string.disconnect_sencond_screen);
        this.f8133k.setText(R.string.disconnect_sencond_screen);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.multi_sub_screen_connect_device && view.getId() != R.id.multi_sub_screen_domestic_connect_device) {
            if (view.getId() == R.id.multi_sub_screen_source_back) {
                finish();
                return;
            } else {
                if (view.getId() == R.id.multi_sub_screen_first_guide_back || view.getId() == R.id.multi_sub_screen_first_guide_finish) {
                    o();
                    return;
                }
                return;
            }
        }
        this.f8137o = p() == 1;
        int i2 = Settings.Global.getInt(getContentResolver(), "multi_sub_screen_enable", 0);
        if (!MultiSubScreenUtils.d()) {
            ToastUtil.a(getString(R.string.multi_subscreen_connect_failure_wifi_bt));
            return;
        }
        if (ZteFeature.IS_INTER_VERSION && i2 == 0) {
            ToastUtil.a(getString(R.string.open_multi_sub_screen_before_connect_toast));
        } else if (this.f8137o) {
            y();
        } else {
            E();
        }
    }

    @Override // cn.nubia.multisubscreen.ui.MultiSubScreenBaseActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        q();
        this.w = new Handler();
        getContentResolver().registerContentObserver(MultiSubScreenUtils.f8172b, false, this.z);
        getContentResolver().registerContentObserver(MultiSubScreenUtils.f8171a, false, this.A);
    }

    @Override // cn.nubia.multisubscreen.ui.MultiSubScreenBaseActivity, android.app.Activity
    protected void onDestroy() {
        MultiSubScreenUtils.N(this.y);
        if (this.z != null) {
            getContentResolver().unregisterContentObserver(this.z);
        }
        if (this.A != null) {
            getContentResolver().unregisterContentObserver(this.A);
        }
        super.onDestroy();
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        GaLog.a("MultiSubScreen_MultiSubScreenSourceActivity", "onNewIntent, intent: " + intent);
        setIntent(intent);
        q();
    }

    @Override // cn.nubia.multisubscreen.ui.MultiSubScreenBaseActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        getOnBackInvokedDispatcher().unregisterOnBackInvokedCallback(this.x);
    }

    @Override // cn.nubia.multisubscreen.ui.MultiSubScreenBaseActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.f8135m) {
            getOnBackInvokedDispatcher().registerOnBackInvokedCallback(0, this.x);
            return;
        }
        D();
        if (ZteFeature.IS_INTER_VERSION) {
            int i2 = Settings.Global.getInt(getContentResolver(), "multi_sub_screen_enable", 0);
            F(i2 == 1, false);
            H(i2 == 1);
        }
    }
}
