package cn.nubia.gameassist.tips;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;
import android.os.SystemProperties;
import android.provider.Settings;
import androidx.annotation.VisibleForTesting;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.SettingsListener;
import cn.nubia.gameassist.test.GameAssistTestActivity;
import cn.nubia.gameassist.tips.launch.TipsMessage;
import cn.nubia.gameassist.tips.learn.UserGuideController;
import cn.nubia.gameassist.tips.learn.UserGuideView;
import com.zte.distbus.basetransfer.DistBusKeys;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;
import com.zte.shared.wrapper.ContextWrapper;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class TipsUtils implements GameMonitor.Callback {
    public static final String FIRST_LAUNCH_GUIDE_STR = "game_assist_first_launch_guide";

    /* renamed from: o, reason: collision with root package name */
    private static volatile GameAssistLaunchTips f7520o;

    /* renamed from: p, reason: collision with root package name */
    private static volatile GameAssistGuide f7521p;

    /* renamed from: c, reason: collision with root package name */
    private HandlerThread f7525c;

    /* renamed from: h, reason: collision with root package name */
    private Handler f7526h;

    /* renamed from: i, reason: collision with root package name */
    private HandlerThread f7527i;

    /* renamed from: j, reason: collision with root package name */
    private Handler f7528j;

    /* renamed from: k, reason: collision with root package name */
    private Context f7529k;

    /* renamed from: l, reason: collision with root package name */
    public volatile boolean f7530l = false;

    /* renamed from: m, reason: collision with root package name */
    public static volatile TipsUtils f7518m = new TipsUtils();
    public static final int LAUNCH_GUIDE_VERSION = SystemProperties.getInt("debug.sys.LAUNCH_GUIDE_VERSION", 145);

    /* renamed from: n, reason: collision with root package name */
    public static final Map f7519n = new HashMap();

    /* renamed from: q, reason: collision with root package name */
    private static volatile String f7522q = "";

    /* renamed from: r, reason: collision with root package name */
    private static volatile Map f7523r = new HashMap();

    /* renamed from: s, reason: collision with root package name */
    private static volatile boolean f7524s = true;
    private static final boolean t = SystemProperties.getBoolean("persist.sys.ENABLE_SCORE_SOFT_SHOW_TIP", true);
    public static List u = Arrays.asList("cn.nubia.gamehighlights", "cn.nubia.gameassist");
    public static List v = Arrays.asList(new ComponentName("com.tencent.tmgp.sgame", "com.tencent.android.tpush.InnerTpnsActivity"));
    public static List w = Arrays.asList("com.antutu.ABenchMark", "com.antutu.benchmark.full", "com.ludashi.benchmark", "com.ludashi.benchmark2", "com.ludashi.vrbench", "com.ludashi.aibench");
    public static List x = Arrays.asList("cn.nubia.gamehighlights", "com.android.systemui", "cn.nubia.nbgame");
    public static boolean y = false;

    private void f(final Context context, final String str, final int i2, boolean z, boolean z2, boolean z3, String str2) {
        ContextWrapper.updateDisplay(context);
        boolean z4 = Settings.Global.getInt(context.getContentResolver(), FIRST_LAUNCH_GUIDE_STR, 1) != LAUNCH_GUIDE_VERSION;
        if (f7521p != null && (z || z4)) {
            f7521p.w(false);
            f7521p = null;
        }
        if (f7520o != null && z) {
            f7520o.m();
            f7520o = null;
        }
        if (z3 && f7524s && z4) {
            if (f7521p == null) {
                y = true;
                f7521p = GameAssistGuide.r(context, new Runnable() { // from class: cn.nubia.gameassist.tips.h
                    @Override // java.lang.Runnable
                    public final void run() {
                        TipsUtils.this.k(context, str, i2);
                    }
                });
            }
            r(context);
        } else if (z && z2) {
            y = true;
            GaLog.e("LaunchTips", "show launch tips, reason=" + str2);
            f7520o = GameAssistLaunchTips.k(context, h(), g(), str, null, null, new Runnable() { // from class: cn.nubia.gameassist.tips.i
                @Override // java.lang.Runnable
                public final void run() {
                    TipsUtils.this.l(context);
                }
            }, null);
            f7523r.put(str, Integer.valueOf(i2));
            r(context);
        } else {
            if (GaLog.f17034b) {
                GaLog.e("LaunchTips", "show launch tips, reason=" + str2);
            }
            y = false;
        }
        s(y);
    }

    public static void i() {
        GameAssistGuide gameAssistGuide = f7521p;
        if (gameAssistGuide != null) {
            gameAssistGuide.w(true);
        }
    }

    @VisibleForTesting
    public static boolean isFirstLaunchTips(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), FIRST_LAUNCH_GUIDE_STR, 1) < LAUNCH_GUIDE_VERSION;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void k(Context context, String str, int i2) {
        int i3;
        GameAssistGuide gameAssistGuide = f7521p;
        if (gameAssistGuide == null || !gameAssistGuide.v()) {
            f7524s = false;
            i3 = LAUNCH_GUIDE_VERSION;
        } else {
            i3 = LAUNCH_GUIDE_VERSION + 1;
            GaLog.e("LaunchTips", "play error :" + gameAssistGuide.getErrorMsg());
        }
        Settings.Global.putInt(context.getContentResolver(), FIRST_LAUNCH_GUIDE_STR, i3);
        Settings.Global.putInt(context.getContentResolver(), "flag_number_launch_guide", 1);
        f7520o = GameAssistLaunchTips.k(context, h(), g(), str, null, null, null, null);
        f7523r.put(str, Integer.valueOf(i2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void l(final Context context) {
        if (Settings.Global.getInt(context.getContentResolver(), "flag_number_launch_guide", 0) == 1) {
            UserGuideController.e(context).q("ActionLearn", new UserGuideView.UserGuideResource(this) { // from class: cn.nubia.gameassist.tips.TipsUtils.3
                @Override // cn.nubia.gameassist.tips.learn.UserGuideView.UserGuideResource
                public int[] a() {
                    String language = context.getResources().getConfiguration().locale.getLanguage();
                    return new int[]{R.drawable.user_guide_xgravity1, "zh".equals(language) ? R.drawable.user_guide_xgravity2_zh : "es".equals(language) ? R.drawable.user_guide_xgravity2_es : R.drawable.user_guide_xgravity2_en};
                }

                @Override // cn.nubia.gameassist.tips.learn.UserGuideView.UserGuideResource
                public int[] b() {
                    return new int[]{R.string.plugin_label_action_learn, R.string.action_learn_tip2_confirm};
                }

                @Override // cn.nubia.gameassist.tips.learn.UserGuideView.UserGuideResource
                public int[] c() {
                    return new int[]{R.string.action_learn_tip1, R.string.action_learn_tip2};
                }
            });
            Settings.Global.putInt(context.getContentResolver(), "flag_number_launch_guide", 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void m(Context context) {
        this.f7529k = context;
        SystemMgr.y(context).h(this);
        TipsMessage.NetworkAcceleration.b().c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void n(String str, int i2, StringBuffer stringBuffer, boolean z, boolean z2) {
        if (x.contains(str)) {
            return;
        }
        boolean contains = w.contains(str);
        String str2 = contains ? "nubia.score.soft" : str;
        if (!contains || t) {
            boolean z3 = !f7522q.equals(str2) && (((Integer) f7523r.getOrDefault(str, 9999)).intValue() != i2 || i2 == 9999);
            if ("com.tencent.mm".equals(str2)) {
                z3 = !f7522q.equals(str2);
            }
            boolean z4 = z3;
            stringBuffer.append(z4 ? "C" : "_");
            f(InflaterHelper.b(), str, i2, z4, z, z2, stringBuffer.toString());
            if (z2) {
                q();
            }
        }
        f7522q = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void o() {
        if (f7521p != null) {
            f7521p.w(false);
            f7521p = null;
        }
        if (f7520o != null) {
            f7520o.m();
            f7520o = null;
        }
    }

    public static void q() {
        if (NubiaTrackManager.p().s()) {
            NubiaTrackManager.p().B("game_assistant_animation_switch", "game_assistant_animation_switch", Settings.Global.getInt(ContextWrapper.getContext().getContentResolver(), "db_game_start_animation", 1) == 1 ? "on" : "off");
        }
    }

    public static void r(Context context) {
        Intent intent = new Intent();
        intent.setAction("cn.nubia.action.CHECK_HANDLE_CONNECT");
        intent.setComponent(new ComponentName("cn.nubia.externdevice", "cn.nubia.device.bluetooth.handle.CheckHandleConnectReceiver"));
        context.sendBroadcast(intent, "cn.nubia.bluetooth.check_handle");
    }

    private void s(boolean z) {
        if (this.f7530l != z) {
            this.f7530l = z;
            Settings.Global.putInt(this.f7529k.getContentResolver(), "nubia_game_launcher_first", z ? 1 : 0);
            GaLog.e("LaunchTips", "updateLauncherFirst first=" + z);
        }
    }

    public synchronized Handler g() {
        try {
            HandlerThread handlerThread = this.f7527i;
            if (handlerThread != null) {
                if (!handlerThread.isAlive()) {
                }
                if (this.f7527i != null && this.f7528j == null) {
                    Handler handler = new Handler(this.f7527i.getLooper());
                    this.f7528j = handler;
                    handler.post(new Runnable(this) { // from class: cn.nubia.gameassist.tips.TipsUtils.1
                        @Override // java.lang.Runnable
                        public void run() {
                            Process.setThreadPriority(-10);
                        }
                    });
                }
            }
            HandlerThread handlerThread2 = new HandlerThread("DecodeThread");
            this.f7527i = handlerThread2;
            handlerThread2.start();
            if (this.f7527i != null) {
                Handler handler2 = new Handler(this.f7527i.getLooper());
                this.f7528j = handler2;
                handler2.post(new Runnable(this) { // from class: cn.nubia.gameassist.tips.TipsUtils.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Process.setThreadPriority(-10);
                    }
                });
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.f7528j;
    }

    public synchronized Handler h() {
        try {
            HandlerThread handlerThread = this.f7525c;
            if (handlerThread != null) {
                if (!handlerThread.isAlive()) {
                }
                if (this.f7525c != null && this.f7526h == null) {
                    Handler handler = new Handler(this.f7525c.getLooper());
                    this.f7526h = handler;
                    handler.post(new Runnable(this) { // from class: cn.nubia.gameassist.tips.TipsUtils.2
                        @Override // java.lang.Runnable
                        public void run() {
                            Process.setThreadPriority(-10);
                        }
                    });
                }
            }
            HandlerThread handlerThread2 = new HandlerThread("TipThread");
            this.f7525c = handlerThread2;
            handlerThread2.start();
            if (this.f7525c != null) {
                Handler handler2 = new Handler(this.f7525c.getLooper());
                this.f7526h = handler2;
                handler2.post(new Runnable(this) { // from class: cn.nubia.gameassist.tips.TipsUtils.2
                    @Override // java.lang.Runnable
                    public void run() {
                        Process.setThreadPriority(-10);
                    }
                });
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.f7526h;
    }

    @VisibleForTesting
    public boolean isTestActivityResumed() {
        return (this.f7529k == null || SystemMgr.f16556q == null || SystemMgr.f16556q.mActivity == null || !SystemMgr.f16556q.mActivity.equals(new ComponentName(this.f7529k, (Class<?>) GameAssistTestActivity.class))) ? false : true;
    }

    public void j(final Context context) {
        h().post(new Runnable() { // from class: cn.nubia.gameassist.tips.g
            @Override // java.lang.Runnable
            public final void run() {
                TipsUtils.this.m(context);
            }
        });
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameSceneStateChanged */
    public void m0(boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(z ? "G" : "_");
        boolean contains = u.contains(SystemMgr.t);
        stringBuffer.append(contains ? "p" : "");
        boolean contains2 = v.contains(SystemMgr.f16556q.mActivity);
        stringBuffer.append(contains2 ? DistBusKeys.KEY_WIFI_FREQUENCY : "");
        boolean z2 = false;
        boolean z3 = (contains || contains2) ? false : true;
        stringBuffer.append(z ? "B" : "_");
        boolean equals = SystemMgr.D.equals(SystemMgr.w);
        stringBuffer.append(equals ? DistBusKeys.KEY_WIFI_ENABLE : "");
        boolean z4 = SettingsListener.f6185o;
        stringBuffer.append(z4 ? "f" : "");
        boolean z5 = equals || z4;
        stringBuffer.append(z5 ? "A" : "_");
        if (z && z3 && z5) {
            z2 = true;
        }
        boolean z6 = isTestActivityResumed() ? true : z2;
        if (SystemMgr.I) {
            h().post(new Runnable() { // from class: cn.nubia.gameassist.tips.e
                @Override // java.lang.Runnable
                public final void run() {
                    TipsUtils.o();
                }
            });
        } else {
            SystemMgr.D = "";
            p(SystemMgr.t(), SystemMgr.f16556q.mPid, z6, z, stringBuffer);
        }
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    public void onResumeFullscreenActivityPidChanged() {
        if (SystemMgr.H()) {
            int intValue = ((Integer) f7523r.getOrDefault(SystemMgr.t(), 0)).intValue();
            GaLog.e("LaunchTips", "onResumeFullscreenActivityPidChanged tipAppPid=" + intValue + "  mPid=" + SystemMgr.f16556q.mPid);
            if (intValue != SystemMgr.f16556q.mPid) {
                SystemMgr.D = SystemMgr.t();
                f7522q = "";
                m0(true);
            }
        }
    }

    public void p(final String str, final int i2, final boolean z, final boolean z2, final StringBuffer stringBuffer) {
        h().post(new Runnable() { // from class: cn.nubia.gameassist.tips.f
            @Override // java.lang.Runnable
            public final void run() {
                TipsUtils.this.n(str, i2, stringBuffer, z, z2);
            }
        });
    }
}
