package cn.nubia.gameassist.meditationmode.glimmernotice;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.service.notification.StatusBarNotification;
import android.view.WindowManager;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.utils.GaLog;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public class GlimmerNoticeManager {

    /* renamed from: h, reason: collision with root package name */
    private static volatile GlimmerNoticeManager f6713h;

    /* renamed from: a, reason: collision with root package name */
    private Context f6714a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f6715b;

    /* renamed from: c, reason: collision with root package name */
    private WindowManager f6716c;

    /* renamed from: d, reason: collision with root package name */
    private WindowManager.LayoutParams f6717d = f();

    /* renamed from: e, reason: collision with root package name */
    private GlimmerAnimationView f6718e;

    /* renamed from: f, reason: collision with root package name */
    private WorkHandler f6719f;

    /* renamed from: g, reason: collision with root package name */
    private HandlerThread f6720g;

    public interface OnAnimationEndCallbackListener {
        void a();
    }

    private static class WorkHandler extends Handler {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference f6723a;

        public WorkHandler(GlimmerNoticeManager glimmerNoticeManager, Looper looper) {
            super(looper);
            this.f6723a = new WeakReference(glimmerNoticeManager);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            GlimmerNoticeManager glimmerNoticeManager = (GlimmerNoticeManager) this.f6723a.get();
            if (glimmerNoticeManager == null) {
                return;
            }
            int i2 = message.what;
            if (i2 == 1001) {
                glimmerNoticeManager.i();
            } else {
                if (i2 != 1002) {
                    return;
                }
                glimmerNoticeManager.j();
            }
        }
    }

    private GlimmerNoticeManager(Context context) {
        this.f6714a = context;
        this.f6716c = (WindowManager) this.f6714a.getSystemService("window");
        HandlerThread handlerThread = new HandlerThread("TipThread");
        this.f6720g = handlerThread;
        handlerThread.start();
        this.f6719f = new WorkHandler(this, this.f6720g.getLooper());
        RotationMgr.e(this.f6714a).c(new RotationMgr.Callback() { // from class: cn.nubia.gameassist.meditationmode.glimmernotice.GlimmerNoticeManager.1
            @Override // com.zte.gameassist.common.RotationMgr.Callback
            public void onRotationChanged(int i2) {
                GlimmerNoticeManager.this.q();
            }
        });
    }

    private void d() {
        if (this.f6715b) {
            return;
        }
        this.f6715b = true;
        GlimmerAnimationView glimmerAnimationView = new GlimmerAnimationView(this.f6714a);
        this.f6718e = glimmerAnimationView;
        this.f6716c.addView(glimmerAnimationView, this.f6717d);
        this.f6718e.setOnWindowRemoveCallback(new OnAnimationEndCallbackListener() { // from class: cn.nubia.gameassist.meditationmode.glimmernotice.GlimmerNoticeManager.2
            @Override // cn.nubia.gameassist.meditationmode.glimmernotice.GlimmerNoticeManager.OnAnimationEndCallbackListener
            public void a() {
                GlimmerNoticeManager.this.n();
            }
        });
    }

    public static GlimmerNoticeManager e(Context context) {
        if (f6713h == null) {
            synchronized (GlimmerNoticeManager.class) {
                try {
                    if (f6713h == null) {
                        f6713h = new GlimmerNoticeManager(context);
                    }
                } finally {
                }
            }
        }
        return f6713h;
    }

    private WindowManager.LayoutParams f() {
        Rect h2 = h();
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-2, -2);
        layoutParams.flags = 66328;
        layoutParams.setTitle("GlimmerNoticeWindow");
        layoutParams.format = -3;
        layoutParams.type = 2038;
        layoutParams.gravity = 49;
        layoutParams.width = h2.width();
        layoutParams.height = h2.height();
        return layoutParams;
    }

    private int g(Context context) {
        return ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getWidth();
    }

    private Rect h() {
        int g2 = g(this.f6714a) / 2;
        return new Rect(g2 - 345, 0, g2 + 345, 345);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        GlimmerAnimationView glimmerAnimationView = this.f6718e;
        if (glimmerAnimationView == null || !glimmerAnimationView.d()) {
            d();
            this.f6718e.e();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        GlimmerAnimationView glimmerAnimationView = this.f6718e;
        if (glimmerAnimationView != null) {
            glimmerAnimationView.f();
            n();
        }
    }

    private boolean k() {
        return 2 == Settings.Global.getInt(this.f6714a.getContentResolver(), "gsc_meditation_level", 0);
    }

    private boolean l(StatusBarNotification statusBarNotification, String str) {
        return statusBarNotification != null && str.equals(statusBarNotification.getPackageName());
    }

    private boolean m(StatusBarNotification statusBarNotification) {
        return l(statusBarNotification, "com.android.incallui") || l(statusBarNotification, "com.google.android.dialer");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        if (this.f6715b) {
            GaLog.a("GlimmerNotice", "remove glimmer notice window!");
            this.f6715b = false;
            this.f6716c.removeView(this.f6718e);
            this.f6718e = null;
        }
    }

    public boolean o(StatusBarNotification statusBarNotification) {
        return k() && !m(statusBarNotification);
    }

    public void p() {
        this.f6719f.removeMessages(1001);
        this.f6719f.sendEmptyMessage(1001);
    }

    public void q() {
        this.f6719f.removeMessages(1002);
        this.f6719f.sendEmptyMessage(1002);
    }
}
