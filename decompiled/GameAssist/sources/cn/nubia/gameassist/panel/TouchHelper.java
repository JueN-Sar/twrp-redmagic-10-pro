package cn.nubia.gameassist.panel;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.PowerManager;
import android.provider.Settings;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.widget.Toast;
import cn.nubia.gameassist.GameAssistApplication;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.SettingsListener;
import cn.nubia.gameassist.tips.TipsUtils;
import cn.nubia.gameassist.utils.ToastUtil;
import cn.nubia.plugin.gameshader.ShaderMgr;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.input.InterfaceEventListener;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.io.PrintWriter;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class TouchHelper implements InterfaceEventListener {
    private static int A = 44;
    private static int B = 111;

    /* renamed from: c, reason: collision with root package name */
    private Context f6828c;

    /* renamed from: h, reason: collision with root package name */
    private int f6829h;

    /* renamed from: i, reason: collision with root package name */
    private int f6830i;

    /* renamed from: j, reason: collision with root package name */
    private int f6831j;

    /* renamed from: k, reason: collision with root package name */
    private OnTouchHelperCallback f6832k;

    /* renamed from: l, reason: collision with root package name */
    private VelocityTracker f6833l;

    /* renamed from: m, reason: collision with root package name */
    private float f6834m;

    /* renamed from: n, reason: collision with root package name */
    private float f6835n;
    private final int t;
    private PowerManager u;

    /* renamed from: o, reason: collision with root package name */
    private boolean f6836o = false;

    /* renamed from: p, reason: collision with root package name */
    private Rect f6837p = new Rect();

    /* renamed from: q, reason: collision with root package name */
    private Rect f6838q = new Rect();

    /* renamed from: r, reason: collision with root package name */
    private boolean f6839r = false;

    /* renamed from: s, reason: collision with root package name */
    private boolean f6840s = false;
    private boolean v = false;
    private boolean w = true;
    private int x = -1;
    private ArrayList y = new ArrayList();
    private ResetOperateFlagRunnable z = new ResetOperateFlagRunnable();

    public interface OnTouchHelperCallback {
        void a();

        void d();

        void pilferPointers();
    }

    private class ResetOperateFlagRunnable implements Runnable {

        /* renamed from: c, reason: collision with root package name */
        public Toast f6841c;

        @Override // java.lang.Runnable
        public void run() {
            TouchHelper.this.x = -1;
            this.f6841c.cancel();
        }

        private ResetOperateFlagRunnable() {
            this.f6841c = null;
        }
    }

    public TouchHelper(Context context) {
        this.f6828c = context;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.f6829h = (viewConfiguration.getScaledTouchSlop() * 3) / 2;
        this.f6830i = viewConfiguration.getScaledMaximumFlingVelocity() / 6;
        this.f6831j = 108;
        this.t = Math.min(250, ViewConfiguration.getLongPressTimeout());
        this.u = (PowerManager) this.f6828c.getSystemService("power");
        A = c(context, 18.0f);
    }

    private void b(MotionEvent motionEvent, String str) {
        GaLog.e("TouchHelper", "cancelGesture, reason=" + str);
        this.f6839r = false;
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        obtain.setAction(3);
        j(obtain);
        obtain.recycle();
    }

    private int c(Context context, float f2) {
        return (int) ((f2 * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    private int g(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionMasked() == 0 ? 0 : motionEvent.getActionIndex();
        if (actionIndex == -1) {
            return 0;
        }
        return actionIndex;
    }

    private void j(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.f6833l = VelocityTracker.obtain();
            return;
        }
        if (actionMasked != 1) {
            if (actionMasked == 2) {
                float abs = Math.abs(motionEvent.getRawX() - this.f6834m);
                Math.abs(motionEvent.getRawY() - this.f6835n);
                this.f6833l.addMovement(motionEvent);
                if (abs <= this.f6831j || this.f6832k == null || this.f6836o) {
                    return;
                }
                u();
                this.f6836o = true;
                return;
            }
            if (actionMasked != 3) {
                return;
            }
        }
        this.f6833l.computeCurrentVelocity(1000);
        if (Math.abs(motionEvent.getRawY() - this.f6835n) <= this.f6831j) {
            Math.abs(this.f6833l.getXVelocity());
        }
        this.f6836o = false;
        this.f6833l.recycle();
    }

    private boolean k() {
        return !this.y.isEmpty();
    }

    private boolean m() {
        if (!this.u.isInteractive()) {
            GaLog.k("TouchHelper", "keyguard or screenoff");
            return false;
        }
        if (s()) {
            GaLog.k("TouchHelper", "forbidden: window added");
            this.f6832k.a();
            return false;
        }
        if (SettingsListener.f6181k) {
            GaLog.k("TouchHelper", "forbidden: expanding vision switch On");
            return false;
        }
        if (SettingsListener.f6179i) {
            GaLog.k("TouchHelper", "forbidden: keyguard show");
            return false;
        }
        if (SettingsListener.f6184n) {
            GaLog.k("TouchHelper", "forbidden: dock mode");
            return false;
        }
        if (!o()) {
            GaLog.k("TouchHelper", "forbidden: not game scene");
            return false;
        }
        if (SystemMgr.y(this.f6828c).f16568k || "cn.nubia.gameassist".equals(SystemMgr.t())) {
            GaLog.k("TouchHelper", "forbidden: black window exist");
            ToastUtil.a(this.f6828c.getString(R.string.game_assist_tips_pls_quit_setting));
            return false;
        }
        if (ShaderMgr.t().x()) {
            GaLog.k("TouchHelper", "forbidden: gameassist settings");
            ToastUtil.a(this.f6828c.getString(R.string.game_assist_tips_pls_quit_setting));
            return false;
        }
        if (!SettingsListener.f6185o || !TipsUtils.isFirstLaunchTips(this.f6828c)) {
            return true;
        }
        GaLog.k("TouchHelper", "forbidden: game assist guide");
        TipsUtils.i();
        GameAssistWindowManager.O(this.f6828c).u0("hideGuide");
        return false;
    }

    private boolean n() {
        if (!SettingsListener.f6182l || this.x > 0 || !SystemMgr.H()) {
            GaLog.e("TouchHelper", "isAntiMisoperateIntercept " + SystemMgr.H());
            return false;
        }
        GaLog.a("TouchHelper", "isAntiMisoperateIntercept(), Intercept the first event");
        this.x *= -1;
        IndicateWindowController.s(this.f6828c).C();
        Toast makeText = Toast.makeText(this.f6828c, R.string.nubia_game_anti_misoperate, 0);
        if (SettingsListener.f6186p) {
            GaLog.a("TouchHelper", "isAntiMisoperateIntercept(), ban toast");
        } else {
            makeText.show();
        }
        ResetOperateFlagRunnable resetOperateFlagRunnable = this.z;
        resetOperateFlagRunnable.f6841c = makeText;
        GameAssistApplication.u(resetOperateFlagRunnable);
        GameAssistApplication.t(this.z, 3000L);
        return true;
    }

    private boolean p(MotionEvent motionEvent) {
        int g2 = g(motionEvent);
        float x = motionEvent.getX(g2);
        float y = motionEvent.getY(g2);
        this.w = false;
        int i2 = (int) x;
        int i3 = (int) y;
        if (!this.f6838q.contains(i2, i3)) {
            return this.f6837p.contains(i2, i3);
        }
        this.w = true;
        return true;
    }

    private boolean q() {
        return ((KeyguardManager) this.f6828c.getSystemService("keyguard")).inKeyguardRestrictedInputMode();
    }

    private boolean s() {
        return this.v;
    }

    private void t(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.y.clear();
            this.f6834m = motionEvent.getRawX();
            this.f6835n = motionEvent.getRawY();
            l();
            this.f6839r = p(motionEvent) && m();
            if (motionEvent.getAxisValue(12, motionEvent.getActionIndex()) > 0.0f || motionEvent.getAxisValue(13, motionEvent.getActionIndex()) > 0.0f) {
                this.y.add(Integer.valueOf(motionEvent.getPointerId(motionEvent.getActionIndex())));
                GaLog.e("TouchHelper", "onMotionEvent inDualTouchMode down");
            }
            if (this.f6839r) {
                j(motionEvent);
                this.f6840s = false;
                return;
            } else {
                GaLog.e("TouchHelper", "onMotionEvent notInTouchRect downY=" + this.f6835n);
                return;
            }
        }
        if (k()) {
            int g2 = g(motionEvent);
            if (actionMasked == 5) {
                this.f6839r = p(motionEvent) && m();
                GaLog.e("TouchHelper", "onMotionEvent inDualTouchMode mAllowGesture=" + this.f6839r);
                this.f6834m = motionEvent.getX(g2);
                this.f6835n = motionEvent.getY(g2);
                return;
            }
            if (actionMasked == 2 && this.f6839r) {
                float abs = Math.abs(motionEvent.getX(g2) - this.f6834m);
                if (abs <= Math.abs(motionEvent.getY(g2) - this.f6835n) || abs <= this.f6829h) {
                    return;
                }
                this.f6839r = false;
                ToastUtil.a(this.f6828c.getString(R.string.game_assist_please_release_touch_key));
                return;
            }
            return;
        }
        if (this.f6839r) {
            if (!this.f6840s) {
                if (actionMasked == 5) {
                    b(motionEvent, "multi_touch");
                    return;
                }
                if (actionMasked == 2) {
                    if (motionEvent.getEventTime() - motionEvent.getDownTime() > this.t) {
                        b(motionEvent, "long_time");
                        return;
                    }
                    float abs2 = Math.abs(motionEvent.getX() - this.f6834m);
                    float abs3 = Math.abs(motionEvent.getY() - this.f6835n);
                    if (abs3 * 0.57d > abs2 && abs3 > this.f6829h) {
                        b(motionEvent, "vertical_direction");
                        return;
                    } else if (abs2 > abs3 && abs2 > this.f6829h) {
                        this.f6840s = true;
                        GaLog.e("TouchHelper", "onMotionEvent pilferPointers");
                        this.f6832k.pilferPointers();
                    }
                }
            }
            j(motionEvent);
            if (actionMasked == 1 || actionMasked == 3) {
                this.f6839r = false;
            }
        }
    }

    private void u() {
        if (n()) {
            return;
        }
        if (Settings.canDrawOverlays(this.f6828c)) {
            this.f6832k.d();
            return;
        }
        GaLog.b("TouchHelper", "onSlideIn(), not canDrawOverlays");
        Intent intent = new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION");
        intent.setData(Uri.fromParts("package", this.f6828c.getPackageName(), null));
        intent.setFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED);
        this.f6828c.startActivity(intent);
    }

    public void d(PrintWriter printWriter) {
        printWriter.println("TouchHelper:");
        printWriter.println("  mRightRect: " + this.f6837p);
        printWriter.println("  mLeftRect: " + this.f6838q);
        printWriter.println("  mAllowGesture: " + this.f6839r);
        printWriter.println("  Keyguard lock : " + q());
        printWriter.println("  mIsSlideIn: " + this.f6836o);
        printWriter.println("  mTouchSlop : " + this.f6829h);
        printWriter.println("  mDeviceWidth : " + i());
        printWriter.println("  mDeviceHeight : " + h());
        printWriter.println("  TOUCH_WIDTH : " + A);
    }

    @Override // com.zte.gameassist.input.InterfaceEventListener
    public void f(MotionEvent motionEvent) {
        t(motionEvent);
    }

    public int h() {
        return RotationMgr.j() ? RotationMgr.g() : RotationMgr.f();
    }

    public int i() {
        return RotationMgr.j() ? RotationMgr.f() : RotationMgr.g();
    }

    public void l() {
        this.f6838q.set(0, B, A, h() / 3);
        this.f6837p.set(i() - A, B, i(), h() / 3);
    }

    public boolean o() {
        return SystemMgr.H();
    }

    public boolean r() {
        return this.f6834m <= ((float) A);
    }

    public void v(OnTouchHelperCallback onTouchHelperCallback) {
        this.f6832k = onTouchHelperCallback;
    }

    public void w(boolean z) {
        this.v = z;
    }
}
