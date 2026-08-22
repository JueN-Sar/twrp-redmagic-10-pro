package cn.nubia.plugin.screenextraction.controller;

import android.content.Context;
import android.database.ContentObserver;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;
import cn.nubia.gameassist.R;
import cn.nubia.plugin.screenextraction.ScreenExtractionManager;
import cn.nubia.plugin.screenextraction.bean.ScreenExtractionData;
import cn.nubia.plugin.screenextraction.utils.DefaultUtils;
import com.zte.gameassist.utils.GaLog;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public class InterceptTouchWindowController extends BaseWindowController<View> {

    /* renamed from: p, reason: collision with root package name */
    private ScreenExtractionData f8608p;

    /* renamed from: q, reason: collision with root package name */
    private Toast f8609q;

    /* renamed from: r, reason: collision with root package name */
    private SettingsObserver f8610r;

    private class SettingsObserver extends ContentObserver {
        public SettingsObserver(Handler handler) {
            super(handler);
        }

        public void a() {
            InterceptTouchWindowController.this.f8593k.getContentResolver().registerContentObserver(Settings.Global.getUriFor("nubia_gameratio_app_bound"), false, this);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            super.onChange(z, uri);
            InterceptTouchWindowController interceptTouchWindowController = InterceptTouchWindowController.this;
            interceptTouchWindowController.N(interceptTouchWindowController.f8608p);
        }
    }

    public InterceptTouchWindowController(ScreenExtractionManager screenExtractionManager) {
        super(screenExtractionManager);
        SettingsObserver settingsObserver = new SettingsObserver(this.f8592j);
        this.f8610r = settingsObserver;
        settingsObserver.a();
    }

    private boolean I() {
        ScreenExtractionData screenExtractionData = this.f8608p;
        if (screenExtractionData == null) {
            return false;
        }
        if (screenExtractionData.d() == 2) {
            return true;
        }
        return this.f8608p.d() == 0 && !J().isEmpty();
    }

    private Rect J() {
        Rect K;
        if (this.f8608p != null) {
            Rect rect = new Rect(this.f8608p.c());
            if (this.f8608p.d() == 2) {
                return rect;
            }
            if (this.f8608p.d() == 0 && (K = K()) != null && !K.contains(rect)) {
                Rect rect2 = new Rect(rect);
                int i2 = rect.left;
                int i3 = K.left;
                if (i2 < i3 && rect.right > i3) {
                    rect2.right = i3;
                }
                int i4 = rect.top;
                int i5 = K.top;
                if (i4 < i5 && rect.bottom > i5) {
                    rect2.bottom = i5;
                }
                int i6 = rect.right;
                int i7 = K.right;
                if (i6 > i7 && i2 < i7) {
                    rect2.left = i7;
                }
                int i8 = rect.bottom;
                int i9 = K.bottom;
                if (i8 > i9 && i4 < i9) {
                    rect2.top = i9;
                }
                return rect2;
            }
        }
        return new Rect();
    }

    private Rect K() {
        if (this.f8608p.d() != 0) {
            return null;
        }
        try {
            String string = Settings.Global.getString(this.f8593k.getContentResolver(), "nubia_gameratio_app_bound");
            if (string == null) {
                return null;
            }
            String[] split = string.split(",");
            if (split.length >= 4) {
                return new Rect(Integer.valueOf(split[0]).intValue(), Integer.valueOf(split[1]).intValue(), Integer.valueOf(split[2]).intValue(), Integer.valueOf(split[3]).intValue());
            }
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void L(ScreenExtractionData screenExtractionData) {
        DefaultUtils.e(this.f8593k, screenExtractionData);
        ScreenExtractionManager.w().E();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void M(final ScreenExtractionData screenExtractionData) {
        this.f8592j.post(new Runnable() { // from class: cn.nubia.plugin.screenextraction.controller.e
            @Override // java.lang.Runnable
            public final void run() {
                InterceptTouchWindowController.this.L(screenExtractionData);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void O() {
        Toast toast = this.f8609q;
        if (toast != null) {
            toast.cancel();
            this.f8609q = null;
        }
        Context context = this.f8593k;
        Toast makeText = Toast.makeText(context, context.getText(R.string.plugin_screen_extraction_mode_nosupper_map), 0);
        this.f8609q = makeText;
        makeText.addCallback(new Toast.Callback() { // from class: cn.nubia.plugin.screenextraction.controller.InterceptTouchWindowController.2
            @Override // android.widget.Toast.Callback
            public void onToastHidden() {
                InterceptTouchWindowController.this.f8609q = null;
            }
        });
        this.f8609q.show();
    }

    public void N(ScreenExtractionData screenExtractionData) {
        this.f8608p = ScreenExtractionData.j(screenExtractionData);
        if (!I()) {
            if (v()) {
                b("onScreenExtractionChanged");
                return;
            }
            return;
        }
        if (v()) {
            z();
            C();
        } else {
            B("onScreenExtractionChanged");
        }
        GaLog.a("ScreenExtraction.InterceptTouch", "windowRect=" + J());
    }

    @Override // cn.nubia.plugin.screenextraction.controller.IWindowController
    public void a(FileDescriptor fileDescriptor, PrintWriter printWriter, String str) {
        printWriter.println(str + "ScreenExtraction.InterceptTouch");
        String str2 = str + "  ";
        printWriter.println(str2 + "isShowWindow=" + v());
        if (v()) {
            printWriter.println(str2 + "WindowPosition=" + p());
            printWriter.println(str2 + "WindowSize=" + q());
            printWriter.println(str2 + "FreeShow=" + Settings.Global.getString(this.f8593k.getContentResolver(), "nubia_gameratio_app_bound"));
        }
    }

    @Override // cn.nubia.plugin.screenextraction.controller.BaseWindowController
    View j() {
        return new View(this.f8593k) { // from class: cn.nubia.plugin.screenextraction.controller.InterceptTouchWindowController.1
            private final Point mDownPoint = new Point();
            private final Rect mDownArea = new Rect();
            private final boolean isTest = false;

            @Override // android.view.View
            protected void onDraw(Canvas canvas) {
                super.onDraw(canvas);
            }

            /* JADX WARN: Code restructure failed: missing block: B:11:0x0021, code lost:
            
                if (r6 != 3) goto L39;
             */
            /* JADX WARN: Code restructure failed: missing block: B:32:0x0083, code lost:
            
                if (r0 != false) goto L34;
             */
            @Override // android.view.View
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public boolean onTouchEvent(android.view.MotionEvent r6) {
                /*
                    r5 = this;
                    cn.nubia.plugin.screenextraction.controller.InterceptTouchWindowController r0 = cn.nubia.plugin.screenextraction.controller.InterceptTouchWindowController.this
                    cn.nubia.plugin.screenextraction.bean.ScreenExtractionData r0 = cn.nubia.plugin.screenextraction.controller.InterceptTouchWindowController.E(r0)
                    r1 = 0
                    if (r0 != 0) goto La
                    return r1
                La:
                    float r0 = r6.getRawX()
                    int r0 = (int) r0
                    float r2 = r6.getRawY()
                    int r2 = (int) r2
                    int r6 = r6.getActionMasked()
                    r3 = 1
                    if (r6 == 0) goto Lad
                    if (r6 == r3) goto L9b
                    r4 = 2
                    if (r6 == r4) goto L25
                    r0 = 3
                    if (r6 == r0) goto L9b
                    goto Lc1
                L25:
                    cn.nubia.plugin.screenextraction.controller.InterceptTouchWindowController r6 = cn.nubia.plugin.screenextraction.controller.InterceptTouchWindowController.this
                    cn.nubia.plugin.screenextraction.bean.ScreenExtractionData r6 = cn.nubia.plugin.screenextraction.controller.InterceptTouchWindowController.E(r6)
                    int r6 = r6.d()
                    if (r6 != r4) goto L9b
                    android.graphics.Point r6 = r5.mDownPoint
                    int r4 = r6.x
                    int r0 = r0 - r4
                    int r6 = r6.y
                    int r2 = r2 - r6
                    android.graphics.Rect r6 = new android.graphics.Rect
                    android.graphics.Rect r4 = r5.mDownArea
                    r6.<init>(r4)
                    if (r0 != 0) goto L47
                    if (r2 == 0) goto L45
                    goto L47
                L45:
                    r0 = r1
                    goto L4b
                L47:
                    r6.offset(r0, r2)
                    r0 = r3
                L4b:
                    int r2 = r6.left
                    if (r2 >= 0) goto L57
                    int r0 = java.lang.Math.abs(r2)
                    r6.offset(r0, r1)
                    r0 = r3
                L57:
                    int r2 = r6.top
                    if (r2 >= 0) goto L63
                    int r0 = java.lang.Math.abs(r2)
                    r6.offset(r1, r0)
                    r0 = r3
                L63:
                    int r2 = r6.right
                    int r4 = cn.nubia.gameassist.panel.GameAssistWindowManager.Q
                    if (r2 <= r4) goto L73
                    int r2 = r2 - r4
                    int r0 = java.lang.Math.abs(r2)
                    int r0 = -r0
                    r6.offset(r0, r1)
                    r0 = r3
                L73:
                    int r2 = r6.bottom
                    int r4 = cn.nubia.gameassist.panel.GameAssistWindowManager.P
                    if (r2 <= r4) goto L83
                    int r2 = r2 - r4
                    int r0 = java.lang.Math.abs(r2)
                    int r0 = -r0
                    r6.offset(r1, r0)
                    goto L85
                L83:
                    if (r0 == 0) goto L9b
                L85:
                    cn.nubia.plugin.screenextraction.controller.InterceptTouchWindowController r0 = cn.nubia.plugin.screenextraction.controller.InterceptTouchWindowController.this
                    cn.nubia.plugin.screenextraction.bean.ScreenExtractionData r0 = cn.nubia.plugin.screenextraction.controller.InterceptTouchWindowController.E(r0)
                    android.graphics.Rect r0 = r0.c()
                    r0.set(r6)
                    cn.nubia.plugin.screenextraction.controller.InterceptTouchWindowController r6 = cn.nubia.plugin.screenextraction.controller.InterceptTouchWindowController.this
                    cn.nubia.plugin.screenextraction.bean.ScreenExtractionData r0 = cn.nubia.plugin.screenextraction.controller.InterceptTouchWindowController.E(r6)
                    cn.nubia.plugin.screenextraction.controller.InterceptTouchWindowController.G(r6, r0)
                L9b:
                    cn.nubia.plugin.screenextraction.controller.InterceptTouchWindowController r6 = cn.nubia.plugin.screenextraction.controller.InterceptTouchWindowController.this
                    cn.nubia.plugin.screenextraction.bean.ScreenExtractionData r6 = cn.nubia.plugin.screenextraction.controller.InterceptTouchWindowController.E(r6)
                    int r6 = r6.d()
                    if (r6 != 0) goto Lc1
                    cn.nubia.plugin.screenextraction.controller.InterceptTouchWindowController r5 = cn.nubia.plugin.screenextraction.controller.InterceptTouchWindowController.this
                    cn.nubia.plugin.screenextraction.controller.InterceptTouchWindowController.H(r5)
                    goto Lc1
                Lad:
                    android.graphics.Point r6 = r5.mDownPoint
                    r6.set(r0, r2)
                    android.graphics.Rect r6 = r5.mDownArea
                    cn.nubia.plugin.screenextraction.controller.InterceptTouchWindowController r5 = cn.nubia.plugin.screenextraction.controller.InterceptTouchWindowController.this
                    cn.nubia.plugin.screenextraction.bean.ScreenExtractionData r5 = cn.nubia.plugin.screenextraction.controller.InterceptTouchWindowController.E(r5)
                    android.graphics.Rect r5 = r5.c()
                    r6.set(r5)
                Lc1:
                    return r3
                */
                throw new UnsupportedOperationException("Method not decompiled: cn.nubia.plugin.screenextraction.controller.InterceptTouchWindowController.AnonymousClass1.onTouchEvent(android.view.MotionEvent):boolean");
            }
        };
    }

    @Override // cn.nubia.plugin.screenextraction.controller.BaseWindowController
    protected Point p() {
        if (this.f8608p == null) {
            return super.q();
        }
        Rect J = J();
        return new Point(J.left, J.top);
    }

    @Override // cn.nubia.plugin.screenextraction.controller.BaseWindowController
    protected Point q() {
        if (this.f8608p == null) {
            return super.q();
        }
        Rect J = J();
        return new Point(J.width(), J.height());
    }

    @Override // cn.nubia.plugin.screenextraction.controller.BaseWindowController
    protected String r() {
        return "ScreenExtraction.InterceptTouch";
    }

    @Override // cn.nubia.plugin.screenextraction.controller.BaseWindowController
    protected int t() {
        return 2008;
    }
}
