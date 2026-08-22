package cn.nubia.hostassist;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import cn.nubia.componentcenter.router.Router;
import cn.nubia.componentcenter.service.ProjectionComService;
import cn.nubia.gameassist.GameAssistApplication;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.dessert.policy.performancemonitor.PerformanceMonitorController;
import cn.nubia.hostassist.HostAssistPanel;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;
import com.zte.gameassist.utils.SharedPreferencesUtil;

/* loaded from: classes.dex */
public class HostAssistPanel extends LinearLayout implements View.OnTouchListener, View.OnHoverListener {
    private static final String ACTION_KEY_MOUSE = "cn.nubia.keymapcenter.intent.action.KEY_MOUSE";
    private static final String ACTION_SCREEN_OFF_TOUPING = "cn.nubia.intent.action.screen_off_touping";
    private static final int ANIMATION_DURATION_TIME = 300;
    private static final String GO_BACK = "goBack";
    private static final String GO_HOME = "goHome";
    private static final String GO_KEYBORD = "goKeybord";
    private static final String GO_KEYGUARD = "goKeyguard";
    private static final String GO_MOUSE = "goMouse";
    private static final String GO_PERFORMANCE = "goPerformance";
    private static final String HOST_MODE_DISPLAYID = "gamebox_mirror_displayid";
    private static final String SCREEN_OFF_TOUPIN = "nubia_screen_off_tp";
    private static final String TAG = "HostAssistPanel";
    private Context mContext;
    private HostAssistMgr mHostAssistMgr;
    private ImageView mHostTipBack;
    private ImageView mHostTipFreeform;
    private InvalidateImageView mHostTipHome;
    private ImageView mHostTipKeybord;
    private ImageView mHostTipKeyguard;
    private ImageView mHostTipMouse;
    private CircleImageView mHostTipPerformance;
    private int mIsHostMode;
    private boolean mPerformMonitorEnable;
    private ImageView mPerformMonitorView;
    private boolean mScreenOffEnable;
    private ContentObserver mScreenOffEnableObserver;
    private final Handler mUiHandler;

    /* renamed from: cn.nubia.hostassist.HostAssistPanel$3, reason: invalid class name */
    class AnonymousClass3 extends ContentObserver {
        AnonymousClass3(Handler handler) {
            super(handler);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void b() {
            boolean z = Settings.Global.getInt(HostAssistPanel.this.mContext.getContentResolver(), HostAssistPanel.SCREEN_OFF_TOUPIN, 0) > 0;
            if (z != HostAssistPanel.this.mScreenOffEnable) {
                GaLog.e(HostAssistPanel.TAG, "onChange mScreenOffEnable= " + z);
                HostAssistPanel.this.setScreenOffEnable(z);
            }
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            super.onChange(z);
            HostAssistPanel.this.mUiHandler.post(new Runnable() { // from class: cn.nubia.hostassist.a
                @Override // java.lang.Runnable
                public final void run() {
                    HostAssistPanel.AnonymousClass3.this.b();
                }
            });
        }
    }

    public HostAssistPanel(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = null;
        this.mScreenOffEnable = false;
        this.mPerformMonitorEnable = false;
        this.mIsHostMode = 0;
        Handler handler = new Handler(Looper.getMainLooper());
        this.mUiHandler = handler;
        this.mScreenOffEnableObserver = new AnonymousClass3(handler);
        this.mContext = context;
        this.mHostAssistMgr = HostAssistMgr.n();
    }

    private void e() {
        GaLog.a(TAG, GO_BACK);
        this.mHostAssistMgr.D(1);
        o(GO_BACK);
    }

    private void f() {
        GaLog.a(TAG, "goFreeform");
        if (HostAssistMgr.F) {
            HostAssistMgr.n().j("clickicon");
        } else {
            HostAssistMgr.n().m();
            HostAssistMgr.n().F();
        }
    }

    private void g() {
        boolean y = HostAssistMgr.y();
        GaLog.a(TAG, "goHome: isExpandMode= " + y);
        if (y) {
            return;
        }
        this.mHostAssistMgr.t();
        o(GO_HOME);
    }

    private void h() {
        GaLog.a(TAG, "goKeybordSetting");
        this.mHostAssistMgr.k();
        String o2 = HostAssistMgr.n().o();
        if ("com.limelight".equals(o2) || "com.xiaoji.egggame.redmagic".equals(o2)) {
            Context context = this.mContext;
            Toast.makeText(context, context.getString(R.string.keymouse_no_support_this_mode), 1).show();
            return;
        }
        Intent intent = new Intent(ACTION_KEY_MOUSE);
        intent.setPackage("cn.nubia.keymapcenter");
        intent.putExtra("reason", "toggle_key_mouse_settings");
        this.mContext.startService(intent);
        o(GO_KEYBORD);
    }

    private void j() {
        boolean z = HostAssistMgr.z();
        GaLog.a(TAG, "goMouseSetting: isGameSpace= " + z);
        if (!z) {
            this.mHostAssistMgr.k();
        }
        ProjectionComService projectionComService = (ProjectionComService) Router.getInstance().getService(ProjectionComService.class.getSimpleName());
        if (projectionComService != null) {
            projectionComService.a();
        }
    }

    private void k() {
        boolean B = this.mHostAssistMgr.B();
        GaLog.a(TAG, "goPerformance: isShowPerformancePane= " + B);
        if (B) {
            this.mHostAssistMgr.l(true);
            n(false);
            SharedPreferencesUtil.k(this.mContext).T(false);
        } else {
            this.mHostAssistMgr.I(true);
            n(true);
            SharedPreferencesUtil.k(this.mContext).T(true);
        }
        HostAssistMgr.n().j(GO_PERFORMANCE);
        o(GO_PERFORMANCE);
    }

    private void l() {
        this.mHostTipBack = (ImageView) findViewById(R.id.host_tip_back);
        this.mHostTipHome = (InvalidateImageView) findViewById(R.id.host_tip_home);
        this.mHostTipFreeform = (ImageView) findViewById(R.id.host_tip_freeform);
        this.mHostTipKeyguard = (ImageView) findViewById(R.id.host_tip_keyguard);
        this.mHostTipMouse = (ImageView) findViewById(R.id.host_tip_mouse);
        this.mHostTipKeybord = (ImageView) findViewById(R.id.host_tip_keybord);
        this.mPerformMonitorView = (ImageView) findViewById(R.id.host_tip_performmonitor);
        this.mHostTipPerformance = (CircleImageView) findViewById(R.id.host_tip_performance);
        this.mHostTipBack.setOnHoverListener(this);
        this.mHostTipHome.setOnHoverListener(this);
        this.mHostTipFreeform.setOnHoverListener(this);
        this.mHostTipKeyguard.setOnHoverListener(this);
        this.mHostTipMouse.setOnHoverListener(this);
        this.mHostTipKeybord.setOnHoverListener(this);
        this.mPerformMonitorView.setOnHoverListener(this);
        this.mHostTipPerformance.setOnHoverListener(this);
        this.mHostTipBack.setOnTouchListener(this);
        this.mHostTipHome.setOnTouchListener(this);
        this.mHostTipFreeform.setOnTouchListener(this);
        this.mHostTipKeyguard.setOnTouchListener(this);
        this.mHostTipMouse.setOnTouchListener(this);
        this.mHostTipKeybord.setOnTouchListener(this);
        this.mPerformMonitorView.setOnTouchListener(this);
        this.mHostTipPerformance.setOnTouchListener(this);
        GameAssistApplication.j().getContentResolver().registerContentObserver(Settings.Global.getUriFor(SCREEN_OFF_TOUPIN), false, this.mScreenOffEnableObserver);
        this.mScreenOffEnable = Settings.Global.getInt(this.mContext.getContentResolver(), SCREEN_OFF_TOUPIN, 0) > 0;
        boolean z = Settings.Global.getInt(this.mContext.getContentResolver(), "host_performance_monitor", 0) > 0;
        this.mPerformMonitorEnable = z;
        this.mPerformMonitorView.setSelected(z);
        if (HostAssistUtils.j()) {
            this.mHostTipHome.m3DModeAlwaysInvalidate = true;
        }
    }

    private void o(String str) {
        String str2;
        str.hashCode();
        switch (str) {
            case "goKeyguard":
                str2 = "host_mode_console_reset_screen_click";
                break;
            case "goBack":
                str2 = "host_mode_console_back_click";
                break;
            case "goHome":
                str2 = "host_mode_console_home_click";
                break;
            case "goKeybord":
                str2 = "host_mode_console_key_click";
                break;
            case "goMouse":
                str2 = "host_mode_console_mouse_click";
                break;
            case "goPerformance":
                str2 = "host_mode_console_performance_click";
                break;
            default:
                str2 = "";
                break;
        }
        NubiaTrackManager.p().w("com.android.settings", str2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void p() {
        int i2 = Settings.Global.getInt(this.mContext.getContentResolver(), "host_performance_monitor", 0) > 0 ? 1 : 0;
        Settings.Global.putInt(this.mContext.getContentResolver(), "host_performance_monitor", i2 ^ 1);
        PerformanceMonitorController.getInstance(this.mContext).onHostChange(i2 ^ 1, this.mHostAssistMgr.o());
        boolean z = i2 ^ 1;
        this.mPerformMonitorEnable = z;
        this.mPerformMonitorView.setSelected(z);
    }

    public void i() {
        this.mScreenOffEnable = Settings.Global.getInt(this.mContext.getContentResolver(), SCREEN_OFF_TOUPIN, 0) > 0;
        GaLog.a(TAG, "goKeyguard: mScreenOffEnable= " + this.mScreenOffEnable);
        try {
            Class.forName("com.redmagic.os.RedMagicAppManager$Trigger").getMethod("openScreenOffTP", Boolean.TYPE).invoke(null, Boolean.valueOf(true ^ this.mScreenOffEnable));
        } catch (Exception e2) {
            e2.printStackTrace();
            GaLog.b(TAG, "goKeyguard: error");
        }
        o(GO_KEYGUARD);
    }

    public void m(View view) {
        int id = view.getId();
        if (id == R.id.host_tip_back) {
            e();
            return;
        }
        if (id == R.id.host_tip_home) {
            g();
            return;
        }
        if (id == R.id.host_tip_freeform) {
            f();
            return;
        }
        if (id == R.id.host_tip_keyguard) {
            i();
            return;
        }
        if (id == R.id.host_tip_mouse) {
            j();
            return;
        }
        if (id == R.id.host_tip_keybord) {
            h();
            return;
        }
        if (id == R.id.host_tip_performmonitor) {
            p();
        } else if (id == R.id.host_tip_performance && view == this.mHostTipPerformance) {
            k();
        }
    }

    public void n(boolean z) {
        GaLog.e(TAG, "playPerformanceAnimation222 : " + z);
        if (z) {
            ValueAnimator duration = ValueAnimator.ofFloat(-90.0f, 90.0f).setDuration(300L);
            duration.setInterpolator(new LinearInterpolator());
            duration.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.nubia.hostassist.HostAssistPanel.1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    if (HostAssistPanel.this.mHostTipPerformance == null || HostAssistPanel.this.mHostTipPerformance.getVisibility() != 0) {
                        return;
                    }
                    HostAssistPanel.this.mHostTipPerformance.setRotation(((Float) valueAnimator.getAnimatedValue()).floatValue());
                }
            });
            duration.start();
            return;
        }
        ValueAnimator duration2 = ValueAnimator.ofFloat(90.0f, -90.0f).setDuration(300L);
        duration2.setInterpolator(new LinearInterpolator());
        duration2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.nubia.hostassist.HostAssistPanel.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (HostAssistPanel.this.mHostTipPerformance == null || HostAssistPanel.this.mHostTipPerformance.getVisibility() != 0) {
                    return;
                }
                HostAssistPanel.this.mHostTipPerformance.setRotation(((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
        });
        duration2.start();
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        l();
    }

    @Override // android.view.View.OnHoverListener
    public boolean onHover(View view, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            if (!(view instanceof ImageView)) {
                return false;
            }
            HostDensityHelper.d(this.mContext);
            ((ImageView) view).setColorFilter(this.mContext.getResources().getColor(R.color.game_host_hover_background_color), PorterDuff.Mode.LIGHTEN);
            return false;
        }
        if (actionMasked != 10 || !(view instanceof ImageView)) {
            return false;
        }
        HostDensityHelper.d(this.mContext);
        ((ImageView) view).clearColorFilter();
        return false;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 1 || action == 3) {
            m(view);
        }
        return true;
    }

    public void setScreenOffEnable(boolean z) {
        this.mScreenOffEnable = z;
        this.mHostTipKeyguard.setSelected(z);
    }
}
