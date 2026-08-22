package cn.nubia.gameassist.panel;

import android.content.Context;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.VisibleForTesting;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.SettingsListener;
import cn.nubia.gameassist.utils.Utils;
import com.zte.gameassist.common.FoldMgr;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.common.ThreadManager;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.ContextWrapper;
import com.zte.shared.wrapper.WindowManagerWrapper;
import com.zte.shared.wrapper.ZteFeatureWrapper;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public class IndicateWindowController implements RotationMgr.Callback, GameMonitor.Callback {
    private static volatile IndicateWindowController t;

    /* renamed from: c, reason: collision with root package name */
    private Context f6792c;

    /* renamed from: h, reason: collision with root package name */
    private WindowManager.LayoutParams f6793h;

    /* renamed from: i, reason: collision with root package name */
    private WindowManager.LayoutParams f6794i;

    /* renamed from: j, reason: collision with root package name */
    private WindowManager f6795j;

    /* renamed from: k, reason: collision with root package name */
    private FrameLayout f6796k;

    /* renamed from: l, reason: collision with root package name */
    private FrameLayout f6797l;

    /* renamed from: m, reason: collision with root package name */
    private boolean f6798m = false;

    /* renamed from: n, reason: collision with root package name */
    protected String f6799n;

    /* renamed from: o, reason: collision with root package name */
    private final Handler f6800o;

    /* renamed from: p, reason: collision with root package name */
    private final int f6801p;

    /* renamed from: q, reason: collision with root package name */
    private final int f6802q;

    /* renamed from: r, reason: collision with root package name */
    private Runnable f6803r;

    /* renamed from: s, reason: collision with root package name */
    private Runnable f6804s;

    private IndicateWindowController(Context context) {
        Handler handler = new Handler(ThreadManager.c().h());
        this.f6800o = handler;
        this.f6801p = ZteFeature.isSprdVendor() ? 100 : 110;
        this.f6802q = 8;
        this.f6803r = new Runnable() { // from class: cn.nubia.gameassist.panel.IndicateWindowController.1
            @Override // java.lang.Runnable
            public void run() {
                String message;
                IndicateWindowController.this.f6800o.removeCallbacks(IndicateWindowController.this.f6804s);
                boolean H = SystemMgr.H();
                GaLog.j("IndicateWindowController", "mShowRunnable isGameScene= " + H + " sGSGuide=" + SettingsListener.f6185o);
                if (SettingsListener.f6185o || !H) {
                    return;
                }
                int displayId = ContextWrapper.getDisplayId(IndicateWindowController.this.f6792c);
                if (!IndicateWindowController.this.f6798m) {
                    try {
                        IndicateWindowController.this.D();
                        ContextWrapper.updateDisplay(IndicateWindowController.this.f6792c);
                        IndicateWindowController.this.f6795j.addView(IndicateWindowController.this.f6796k, IndicateWindowController.this.f6793h);
                        IndicateWindowController.this.f6795j.addView(IndicateWindowController.this.f6797l, IndicateWindowController.this.f6794i);
                        IndicateWindowController.this.f6798m = true;
                        message = "";
                    } catch (Exception e2) {
                        message = e2.getMessage();
                    }
                    GaLog.e("IndicateWindowController", "showWindow: mWindowsAdd=" + IndicateWindowController.this.f6798m + " displayid=" + displayId + "." + message);
                }
                IndicateWindowController.this.f6800o.postDelayed(IndicateWindowController.this.f6804s, 15000L);
            }
        };
        this.f6804s = new Runnable() { // from class: cn.nubia.gameassist.panel.IndicateWindowController.2
            @Override // java.lang.Runnable
            public void run() {
                String message;
                GaLog.j("IndicateWindowController", "mHideRunnable: isGameScene= " + SystemMgr.H());
                IndicateWindowController.this.f6800o.removeCallbacks(IndicateWindowController.this.f6803r);
                if (IndicateWindowController.this.f6798m) {
                    try {
                        IndicateWindowController.this.f6795j.removeView(IndicateWindowController.this.f6796k);
                        IndicateWindowController.this.f6795j.removeView(IndicateWindowController.this.f6797l);
                        IndicateWindowController.this.f6798m = false;
                        message = "";
                    } catch (Exception e2) {
                        message = e2.getMessage();
                    }
                    GaLog.e("IndicateWindowController", "hideWindow." + message);
                }
            }
        };
        this.f6792c = context;
        this.f6795j = (WindowManager) context.getSystemService("window");
        handler.post(new Runnable() { // from class: cn.nubia.gameassist.panel.G
            @Override // java.lang.Runnable
            public final void run() {
                IndicateWindowController.this.t();
            }
        });
        FoldMgr.c().a(new FoldMgr.Callback() { // from class: cn.nubia.gameassist.panel.H
            @Override // com.zte.gameassist.common.FoldMgr.Callback
            public final void onDisplayInUseStateChanged(int i2) {
                IndicateWindowController.this.z(i2);
            }
        });
    }

    private void A(String str) {
        boolean H = SystemMgr.H();
        if (H && this.f6798m) {
            GaLog.e("IndicateWindowController", str + ": isGameScene= " + H + " mWindowsAdd=" + this.f6798m + " isPortrait=" + RotationMgr.k());
            D();
            this.f6795j.updateViewLayout(this.f6796k, this.f6793h);
            this.f6795j.updateViewLayout(this.f6797l, this.f6794i);
            this.f6800o.removeCallbacks(this.f6803r);
            this.f6800o.removeCallbacks(this.f6804s);
            if (FoldMgr.f()) {
                this.f6800o.post(this.f6804s);
            } else {
                this.f6800o.postDelayed(this.f6804s, 15000L);
            }
        }
    }

    private void B(FrameLayout frameLayout, int i2) {
        if (frameLayout != null) {
            ((ImageView) frameLayout.getChildAt(0)).setImageResource(i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void D() {
        int i2;
        int i3;
        boolean k2 = RotationMgr.k();
        boolean z = w() || ZteFeature.isSprdVendor();
        boolean isTabletProduct = ZteFeature.isTabletProduct();
        if (!z || isTabletProduct) {
            i2 = k2 ? R.drawable.indicate_straight_port : R.drawable.indicate_straight_land;
            i3 = k2 ? R.drawable.indicate_straight_port : R.drawable.indicate_straight_land;
        } else {
            i2 = k2 ? R.drawable.indicate_straight_sprd_port : R.drawable.indicate_straight_sprd_land;
            i3 = k2 ? R.drawable.indicate_straight_sprd_port : R.drawable.indicate_straight_sprd_land;
        }
        B(this.f6796k, i2);
        B(this.f6797l, i3);
    }

    private boolean q(Runnable runnable) {
        if (this.f6800o.getLooper().isCurrentThread()) {
            return false;
        }
        this.f6800o.post(runnable);
        return true;
    }

    public static IndicateWindowController s(Context context) {
        if (t == null) {
            synchronized (IndicateWindowController.class) {
                try {
                    if (t == null) {
                        t = new IndicateWindowController(context);
                    }
                } finally {
                }
            }
        }
        return t;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        SystemMgr.y(this.f6792c).h(this);
        RotationMgr.e(this.f6792c).c(this);
        u();
        v();
    }

    private void u() {
        FrameLayout frameLayout = new FrameLayout(this.f6792c);
        this.f6796k = frameLayout;
        frameLayout.setId(R.id.indicate_left);
        this.f6796k.setPadding(8, this.f6801p, 0, 0);
        this.f6796k.addView(new ImageView(this.f6792c));
        FrameLayout frameLayout2 = new FrameLayout(this.f6792c);
        this.f6797l = frameLayout2;
        frameLayout2.setId(R.id.indicate_right);
        this.f6797l.setPadding(0, this.f6801p, 8, 0);
        this.f6797l.addView(new ImageView(this.f6792c));
    }

    private void v() {
        if (this.f6793h == null) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            this.f6793h = layoutParams;
            layoutParams.width = -2;
            layoutParams.height = -2;
            layoutParams.gravity = 51;
            layoutParams.setTitle("IndicateLeft");
            WindowManagerWrapper.LayoutParams.addHidePrivateTrustedOverlayFlags(this.f6793h);
            WindowManager.LayoutParams layoutParams2 = this.f6793h;
            layoutParams2.format = -2;
            layoutParams2.type = 2038;
            layoutParams2.flags = 280;
            layoutParams2.layoutInDisplayCutoutMode = 3;
        }
        if (this.f6794i == null) {
            WindowManager.LayoutParams layoutParams3 = new WindowManager.LayoutParams();
            this.f6794i = layoutParams3;
            layoutParams3.width = -2;
            layoutParams3.height = -2;
            layoutParams3.gravity = 53;
            layoutParams3.setTitle("IndicateRight");
            WindowManagerWrapper.LayoutParams.addHidePrivateTrustedOverlayFlags(this.f6794i);
            WindowManager.LayoutParams layoutParams4 = this.f6794i;
            layoutParams4.format = -2;
            layoutParams4.layoutInDisplayCutoutMode = 3;
            layoutParams4.type = 2038;
            layoutParams4.flags = 280;
            WindowManagerWrapper.LayoutParams.setFitInsetsTypes(layoutParams4);
        }
    }

    public static boolean w() {
        return ZteFeatureWrapper.getBoolean(ZteFeature.ZTE_FEATURE_BEND_INDECATE, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void x() {
        A("onFoldChange");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z(int i2) {
        GaLog.a("IndicateWindowController", "onFoldChange state = " + i2);
        if (i2 == 0 || i2 == 1) {
            this.f6800o.post(new Runnable() { // from class: cn.nubia.gameassist.panel.I
                @Override // java.lang.Runnable
                public final void run() {
                    IndicateWindowController.this.x();
                }
            });
        }
    }

    public void C() {
        if (q(new Runnable() { // from class: cn.nubia.gameassist.panel.F
            @Override // java.lang.Runnable
            public final void run() {
                IndicateWindowController.this.C();
            }
        })) {
            return;
        }
        if (this.f6798m) {
            this.f6800o.removeCallbacks(this.f6804s);
            this.f6800o.postDelayed(this.f6804s, 15000L);
        } else {
            this.f6800o.removeCallbacks(this.f6804s);
            this.f6800o.removeCallbacks(this.f6803r);
            this.f6800o.postDelayed(this.f6803r, 50L);
            GaLog.e("IndicateWindowController", "showIndicateWindow,delay to showWindow");
        }
    }

    @VisibleForTesting
    public boolean isIndicateWindowShow() {
        return this.f6798m;
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStart */
    public void y() {
        if (q(new Runnable() { // from class: cn.nubia.gameassist.panel.D
            @Override // java.lang.Runnable
            public final void run() {
                IndicateWindowController.this.y();
            }
        })) {
            return;
        }
        this.f6800o.removeCallbacks(this.f6804s);
        this.f6800o.removeCallbacks(this.f6803r);
        this.f6804s.run();
        this.f6799n = Utils.j();
        GaLog.e("IndicateWindowController", "onGameStart: " + this.f6799n);
        this.f6800o.postDelayed(this.f6803r, this.f6799n.equals("com.tencent.tmgp.pubgmhd") ? 2000L : 1000L);
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStop */
    public void z() {
        if (q(new Runnable() { // from class: cn.nubia.gameassist.panel.E
            @Override // java.lang.Runnable
            public final void run() {
                IndicateWindowController.this.z();
            }
        })) {
            return;
        }
        this.f6800o.removeCallbacks(this.f6804s);
        this.f6800o.removeCallbacks(this.f6803r);
        this.f6804s.run();
    }

    @Override // com.zte.gameassist.common.RotationMgr.Callback
    /* renamed from: onRotationChanged, reason: merged with bridge method [inline-methods] */
    public void y(final int i2) {
        if (q(new Runnable() { // from class: cn.nubia.gameassist.panel.C
            @Override // java.lang.Runnable
            public final void run() {
                IndicateWindowController.this.y(i2);
            }
        })) {
            return;
        }
        A("onRotationChanged");
    }

    public void r(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.println("    IndicateWindowController:");
        printWriter.println("        mWindowsAdd=" + this.f6798m);
        printWriter.println("        sIsGameScene=" + SystemMgr.H());
        if (this.f6793h != null) {
            printWriter.println("        mLeftIndicateParams x=" + this.f6793h.x);
            printWriter.println("        mLeftIndicateParams y=" + this.f6793h.y);
        }
        if (this.f6794i != null) {
            printWriter.println("        mRightIndicateParams x=" + this.f6794i.x);
            printWriter.println("        mRightIndicateParams y=" + this.f6794i.y);
        }
    }
}
