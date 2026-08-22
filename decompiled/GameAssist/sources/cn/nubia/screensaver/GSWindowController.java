package cn.nubia.screensaver;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.Display;
import android.view.DisplayInfo;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.FrameLayout;
import cn.nubia.screensaver.GSWindowController;
import cn.nubia.screensaver.common.IController;
import cn.nubia.screensaver.power.GSPowerController;
import cn.nubia.screensaver.util.DefaultUtil;
import cn.nubia.screensaver.view.CardParentView;
import cn.nubia.screensaver.view.GamePresentation;
import cn.nubia.screensaver.view.KeyguardPresentation;
import cn.nubia.screensaver.view.ScreensaverRootView;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.DisplayWrapper;
import com.zte.shared.wrapper.TraceWrapper;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* loaded from: classes.dex */
public class GSWindowController implements IController, GSPowerController.PowerCallback {

    /* renamed from: c, reason: collision with root package name */
    private final DisplayMetrics f8953c = new DisplayMetrics();

    /* renamed from: h, reason: collision with root package name */
    private final List f8954h;

    /* renamed from: i, reason: collision with root package name */
    private final GameScreensaverManager f8955i;

    /* renamed from: j, reason: collision with root package name */
    private final WindowManager f8956j;

    /* renamed from: k, reason: collision with root package name */
    private Handler f8957k;

    /* renamed from: l, reason: collision with root package name */
    private ScreensaverRootView f8958l;

    /* renamed from: m, reason: collision with root package name */
    private Point f8959m;

    /* renamed from: n, reason: collision with root package name */
    private final DisplayManager f8960n;

    /* renamed from: o, reason: collision with root package name */
    private GSPowerController f8961o;

    /* renamed from: p, reason: collision with root package name */
    private boolean f8962p;

    /* renamed from: q, reason: collision with root package name */
    private boolean f8963q;

    /* renamed from: r, reason: collision with root package name */
    private final SparseArray f8964r;

    /* renamed from: s, reason: collision with root package name */
    private final DisplayInfo f8965s;
    private final Runnable t;
    private final Runnable u;

    /* renamed from: cn.nubia.screensaver.GSWindowController$1, reason: invalid class name */
    class AnonymousClass1 implements Runnable {

        /* renamed from: cn.nubia.screensaver.GSWindowController$1$1, reason: invalid class name and collision with other inner class name */
        class ViewTreeObserverOnPreDrawListenerC00081 implements ViewTreeObserver.OnPreDrawListener {
            ViewTreeObserverOnPreDrawListenerC00081() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public /* synthetic */ void d() {
                GSWindowController.this.f8955i.o0("checkAddView");
            }

            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                if (GSWindowController.this.f8958l == null) {
                    return true;
                }
                GSWindowController.this.f8958l.getViewTreeObserver().removeOnPreDrawListener(this);
                GSWindowController.this.f8954h.forEach(new Consumer() { // from class: cn.nubia.screensaver.k
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((GSWindowController.Callback) obj).i();
                    }
                });
                GSWindowController.this.f8958l.o(new Runnable() { // from class: cn.nubia.screensaver.l
                    @Override // java.lang.Runnable
                    public final void run() {
                        GSWindowController.AnonymousClass1.ViewTreeObserverOnPreDrawListenerC00081.this.d();
                    }
                });
                return true;
            }
        }

        AnonymousClass1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (GameScreensaverManager.class) {
                try {
                    if (!GSWindowController.this.f8962p) {
                        GaLog.e("GameScreensaver.Window", "---showWindow---");
                        GSWindowController.this.f8954h.forEach(new Consumer() { // from class: cn.nubia.screensaver.h
                            @Override // java.util.function.Consumer
                            public final void accept(Object obj) {
                                ((GSWindowController.Callback) obj).c();
                            }
                        });
                        try {
                            if (GSWindowController.this.f8958l == null) {
                                GSWindowController.this.f8955i.B();
                                DefaultUtil.d(GSWindowController.this.z());
                                GSWindowController.this.f8958l = new ScreensaverRootView(GSWindowController.this.z(), GSWindowController.this.f8955i);
                                GSWindowController.this.f8958l.addView(GSWindowController.this.f8955i.D(), new FrameLayout.LayoutParams(GSWindowController.this.f8959m.x, GSWindowController.this.f8959m.y));
                            }
                            GSWindowController.this.f8956j.addView(GSWindowController.this.f8958l, GSWindowController.this.A());
                            GSWindowController.this.f8962p = true;
                            GSWindowController.this.f8955i.G().g(512);
                            if (GSWindowController.this.f8955i.T()) {
                                GSWindowController.this.f8958l.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserverOnPreDrawListenerC00081());
                            } else {
                                GSWindowController.this.f8954h.forEach(new Consumer() { // from class: cn.nubia.screensaver.i
                                    @Override // java.util.function.Consumer
                                    public final void accept(Object obj) {
                                        ((GSWindowController.Callback) obj).i();
                                    }
                                });
                            }
                        } catch (Exception e2) {
                            GaLog.b("GameScreensaver.Window", "---showWindow--- " + e2.getMessage());
                        }
                        GSWindowController.this.f8954h.forEach(new Consumer() { // from class: cn.nubia.screensaver.j
                            @Override // java.util.function.Consumer
                            public final void accept(Object obj) {
                                ((GSWindowController.Callback) obj).m();
                            }
                        });
                    } else if (GSWindowController.this.f8958l != null) {
                        GSWindowController.this.V("addWindow");
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    /* renamed from: cn.nubia.screensaver.GSWindowController$2, reason: invalid class name */
    class AnonymousClass2 implements Runnable {
        AnonymousClass2() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void d() {
            GSWindowController.this.F();
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (GameScreensaverManager.class) {
                try {
                    if (GSWindowController.this.f8962p && GSWindowController.this.f8958l != null) {
                        GSWindowController.this.f8954h.forEach(new Consumer() { // from class: cn.nubia.screensaver.m
                            @Override // java.util.function.Consumer
                            public final void accept(Object obj) {
                                ((GSWindowController.Callback) obj).n();
                            }
                        });
                        GSWindowController.this.f8958l.n(new Runnable() { // from class: cn.nubia.screensaver.n
                            @Override // java.lang.Runnable
                            public final void run() {
                                GSWindowController.AnonymousClass2.this.d();
                            }
                        });
                        GaLog.e("GameScreensaver.Window", "---hideWindow--- ");
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public interface Callback {
        default void c() {
        }

        default void d() {
        }

        default void i() {
        }

        default void k() {
        }

        default void m() {
        }

        default void n() {
        }
    }

    public GSWindowController(GameScreensaverManager gameScreensaverManager, Callback callback) {
        ArrayList arrayList = new ArrayList();
        this.f8954h = arrayList;
        this.f8959m = new Point();
        this.f8964r = new SparseArray();
        this.f8965s = new DisplayInfo();
        this.t = new AnonymousClass1();
        this.u = new AnonymousClass2();
        this.f8955i = gameScreensaverManager;
        this.f8957k = gameScreensaverManager.C();
        arrayList.add(callback);
        this.f8960n = (DisplayManager) z().getSystemService(DisplayManager.class);
        this.f8956j = (WindowManager) z().getSystemService(WindowManager.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public WindowManager.LayoutParams A() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-1, -1);
        layoutParams.type = 2027;
        layoutParams.flags = 92604160;
        layoutParams.gravity = 21;
        layoutParams.format = -3;
        layoutParams.alpha = 0.999f;
        layoutParams.windowAnimations = 0;
        if (ZteFeature.isTabletProduct()) {
            layoutParams.screenOrientation = 6;
        } else {
            layoutParams.screenOrientation = -1;
        }
        layoutParams.token = GameScreensaverManager.L().M();
        R(layoutParams, 30000L);
        WindowManagerWrapper.LayoutParams.setFitInsetsTypes(layoutParams);
        layoutParams.setTitle("GameScreensaver.Window");
        layoutParams.layoutInDisplayCutoutMode = 3;
        WindowManagerWrapper.LayoutParams.addHidePrivateTrustedOverlayFlags(layoutParams);
        return layoutParams;
    }

    private void D(int i2) {
        GamePresentation gamePresentation = (GamePresentation) this.f8964r.get(i2);
        if (gamePresentation != null) {
            GaLog.a("GameScreensaver.Window", "hide presentation " + i2);
            this.f8964r.remove(i2);
            gamePresentation.dismiss();
        }
        if (this.f8964r.size() == 0) {
            this.f8955i.o0("hidePresentation");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v5, types: [int] */
    /* JADX WARN: Type inference failed for: r1v8, types: [java.util.function.Consumer] */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r2v11, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r2v14, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v8, types: [cn.nubia.screensaver.common.ActionEvent] */
    /* JADX WARN: Type inference failed for: r3v1, types: [cn.nubia.screensaver.e, java.util.function.Consumer] */
    /* JADX WARN: Type inference failed for: r3v5, types: [cn.nubia.screensaver.e, java.util.function.Consumer] */
    /* JADX WARN: Type inference failed for: r8v0, types: [cn.nubia.screensaver.GSWindowController] */
    /* JADX WARN: Type inference failed for: r8v3, types: [cn.nubia.screensaver.GSWindowController] */
    /* JADX WARN: Type inference failed for: r8v5, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r8v8 */
    /* JADX WARN: Type inference failed for: r8v9 */
    public void F() {
        synchronized (GameScreensaverManager.class) {
            if (this.f8962p && this.f8958l != null) {
                ?? r1 = 1024;
                boolean z = false;
                ScreensaverRootView screensaverRootView = null;
                try {
                    try {
                        Q();
                        this.f8956j.removeView(this.f8958l);
                        this.f8958l = null;
                        this.f8962p = false;
                        ?? r2 = this.f8954h;
                        ?? r3 = new Consumer() { // from class: cn.nubia.screensaver.e
                            @Override // java.util.function.Consumer
                            public final void accept(Object obj) {
                                ((GSWindowController.Callback) obj).d();
                            }
                        };
                        r2.forEach(r3);
                        this.f8955i.G().g(1024);
                        this.f8955i.o0("checkRemoveView");
                        List list = this.f8954h;
                        r1 = new Consumer() { // from class: cn.nubia.screensaver.f
                            @Override // java.util.function.Consumer
                            public final void accept(Object obj) {
                                ((GSWindowController.Callback) obj).k();
                            }
                        };
                        z = "checkRemoveView";
                        screensaverRootView = r3;
                        this = list;
                    } catch (Throwable th) {
                        this.f8958l = screensaverRootView;
                        this.f8962p = z;
                        this.f8954h.forEach(new Consumer() { // from class: cn.nubia.screensaver.e
                            @Override // java.util.function.Consumer
                            public final void accept(Object obj) {
                                ((GSWindowController.Callback) obj).d();
                            }
                        });
                        this.f8955i.G().g(r1);
                        this.f8955i.o0("checkRemoveView");
                        this.f8954h.forEach(new Consumer() { // from class: cn.nubia.screensaver.f
                            @Override // java.util.function.Consumer
                            public final void accept(Object obj) {
                                ((GSWindowController.Callback) obj).k();
                            }
                        });
                        throw th;
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    GaLog.b("GameScreensaver.Window", "---hideWindow--- e: " + e2.getMessage());
                    this.f8958l = null;
                    this.f8962p = false;
                    ?? r22 = this.f8954h;
                    ?? r32 = new Consumer() { // from class: cn.nubia.screensaver.e
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            ((GSWindowController.Callback) obj).d();
                        }
                    };
                    r22.forEach(r32);
                    this.f8955i.G().g(1024);
                    this.f8955i.o0("checkRemoveView");
                    List list2 = this.f8954h;
                    r1 = new Consumer() { // from class: cn.nubia.screensaver.f
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            ((GSWindowController.Callback) obj).k();
                        }
                    };
                    z = "checkRemoveView";
                    screensaverRootView = r32;
                    this = list2;
                }
                this.forEach(r1);
                GaLog.e("GameScreensaver.Window", "---hideWindow--- ");
            }
        }
    }

    private boolean G(Display display) {
        if (this.f8955i.m0()) {
            return I(display);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void K(Callback callback) {
        if (this.f8954h.contains(callback)) {
            return;
        }
        this.f8954h.add(callback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void N(KeyguardPresentation keyguardPresentation, int i2, DialogInterface dialogInterface) {
        if (keyguardPresentation.equals(this.f8964r.get(i2))) {
            this.f8964r.remove(i2);
        }
    }

    private void Q() {
        for (int i2 = 0; i2 < this.f8958l.getChildCount(); i2++) {
            View childAt = this.f8958l.getChildAt(i2);
            if (childAt instanceof CardParentView) {
                ((CardParentView) childAt).w();
            }
        }
    }

    private void R(WindowManager.LayoutParams layoutParams, long j2) {
        try {
            WindowManager.LayoutParams.class.getMethod("setUserActivityTimeout", Long.TYPE).invoke(layoutParams, Long.valueOf(j2));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private boolean S(Display display) {
        if (!G(display)) {
            return false;
        }
        GaLog.e("GameScreensaver.Window", "Keyguard enabled on display: " + display);
        final int displayId = display.getDisplayId();
        if (((GamePresentation) this.f8964r.get(displayId)) == null) {
            this.f8955i.B();
            final KeyguardPresentation keyguardPresentation = new KeyguardPresentation(z(), display);
            keyguardPresentation.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: cn.nubia.screensaver.d
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    GSWindowController.this.N(keyguardPresentation, displayId, dialogInterface);
                }
            });
            try {
                keyguardPresentation.show();
            } catch (WindowManager.InvalidDisplayException e2) {
                GaLog.c("GameScreensaver.Window", "invalid display:", e2);
                keyguardPresentation = null;
            }
            if (keyguardPresentation != null) {
                GaLog.a("GameScreensaver.Window", "show presentation " + display);
                this.f8964r.append(displayId, keyguardPresentation);
                this.f8955i.o0("showPresentation");
                return true;
            }
        }
        return false;
    }

    public boolean B() {
        return this.f8963q;
    }

    public boolean C() {
        return this.f8964r.size() > 0;
    }

    public void E(String str) {
        if (H()) {
            return;
        }
        TraceWrapper.traceBegin(8L, "hideWindow=" + str);
        this.f8957k.removeCallbacks(this.u);
        this.f8957k.removeCallbacks(this.t);
        this.f8957k.post(this.u);
        TraceWrapper.traceEnd(8L);
    }

    public boolean H() {
        ScreensaverRootView screensaverRootView = this.f8958l;
        return screensaverRootView != null && screensaverRootView.h();
    }

    public boolean I(Display display) {
        if (display == null) {
            GaLog.e("GameScreensaver.Window", "Cannot show Keyguard on null display");
            return false;
        }
        int type = DisplayWrapper.getType(display);
        if (type != 2 && type != 5 && type != 3) {
            GaLog.e("GameScreensaver.Window", "Cannot show Keyguard on display type " + type);
            return false;
        }
        String name = display.getName();
        if ("app_3d_display".equals(name)) {
            GaLog.e("GameScreensaver.Window", "Cannot show Keyguard on display name " + name);
            return false;
        }
        display.getDisplayInfo(this.f8965s);
        if ((this.f8965s.flags & 512) == 0) {
            return true;
        }
        GaLog.e("GameScreensaver.Window", "Do not show KeyguardPresentation on an unlocked display");
        return false;
    }

    public boolean J() {
        return this.f8962p;
    }

    public void O(int i2) {
        S(this.f8960n.getDisplay(i2));
    }

    public void P(int i2) {
        D(i2);
    }

    public void T(String str) {
        if (H()) {
            return;
        }
        TraceWrapper.traceBegin(8L, "showWindow=" + str);
        this.f8957k.removeCallbacks(this.u);
        this.f8957k.removeCallbacks(this.t);
        this.f8957k.post(this.t);
        TraceWrapper.traceEnd(8L);
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected boolean U(boolean z) {
        if (z) {
            Display[] displays = this.f8960n.getDisplays();
            int length = displays.length;
            boolean z2 = false;
            while (r0 < length) {
                z2 |= S(displays[r0]);
                r0++;
            }
            return z2;
        }
        r0 = this.f8964r.size() > 0 ? 1 : 0;
        for (int size = this.f8964r.size() - 1; size >= 0; size--) {
            ((GamePresentation) this.f8964r.valueAt(size)).dismiss();
        }
        this.f8964r.clear();
        this.f8955i.o0("hidePresentation");
        return r0;
    }

    public void V(String str) {
        GaLog.e("GameScreensaver.Window", "---updateViewRoot--- reason=" + str);
        if (this.f8958l != null) {
            TraceWrapper.traceBegin(8L, "updateViewRoot=" + str);
            this.f8958l.requestLayout();
            this.f8958l.postInvalidate();
            TraceWrapper.traceEnd(8L);
        }
    }

    @Override // cn.nubia.screensaver.common.IController
    public void a(FileDescriptor fileDescriptor, PrintWriter printWriter, String str) {
        printWriter.println(str + "GameScreensaver.Window");
        String str2 = str + "  ";
        printWriter.println(str2 + "mWindowSize=" + this.f8959m);
        printWriter.println(str2 + "mIsAddToWindow=" + this.f8962p);
        ScreensaverRootView screensaverRootView = this.f8958l;
        if (screensaverRootView != null) {
            printWriter.println(str2 + "mWindowView=" + screensaverRootView);
        }
        printWriter.println(str2 + "presentations=" + this.f8964r.size());
        if (this.f8964r.size() > 0) {
            for (int i2 = 0; i2 < this.f8964r.size(); i2++) {
                GamePresentation gamePresentation = (GamePresentation) this.f8964r.valueAt(i2);
                if (gamePresentation != null) {
                    gamePresentation.e(fileDescriptor, printWriter, str2);
                }
            }
        }
    }

    @Override // cn.nubia.screensaver.power.GSPowerController.PowerCallback
    public void e(int i2, String str) {
        this.f8963q = false;
        F();
    }

    @Override // cn.nubia.screensaver.common.IController
    public void f() {
        Point point = new Point();
        Display display = this.f8960n.getDisplay(0);
        display.getRealMetrics(this.f8953c);
        display.getRealSize(point);
        Point point2 = this.f8959m;
        int i2 = point.x;
        int i3 = point.y;
        if (i2 <= i3) {
            i2 = i3;
        }
        point2.x = i2;
        int i4 = point.x;
        if (i4 < i3) {
            i3 = i4;
        }
        point2.y = i3;
        GSPowerController gSPowerController = (GSPowerController) this.f8955i.I(GSPowerController.class);
        this.f8961o = gSPowerController;
        gSPowerController.t(this);
    }

    @Override // cn.nubia.screensaver.common.IController
    public void o(int i2, boolean z) {
        U(z);
    }

    public void x(final Callback callback) {
        this.f8957k.post(new Runnable() { // from class: cn.nubia.screensaver.g
            @Override // java.lang.Runnable
            public final void run() {
                GSWindowController.this.K(callback);
            }
        });
    }

    public void y() {
        this.f8963q = true;
        E("gestureExit");
    }

    public Context z() {
        return this.f8955i.H();
    }
}
