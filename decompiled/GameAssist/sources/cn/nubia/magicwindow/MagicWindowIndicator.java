package cn.nubia.magicwindow;

import android.app.ActivityManager;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import cn.nubia.gameassist.R;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class MagicWindowIndicator {

    /* renamed from: a, reason: collision with root package name */
    private boolean f7850a;

    /* renamed from: b, reason: collision with root package name */
    private float f7851b;

    /* renamed from: c, reason: collision with root package name */
    private Context f7852c;

    /* renamed from: d, reason: collision with root package name */
    private MagicWindowPosition f7853d;

    /* renamed from: e, reason: collision with root package name */
    private boolean f7854e;

    /* renamed from: f, reason: collision with root package name */
    private boolean f7855f;

    /* renamed from: g, reason: collision with root package name */
    private boolean f7856g;

    /* renamed from: h, reason: collision with root package name */
    private boolean f7857h;

    /* renamed from: i, reason: collision with root package name */
    private ImageView f7858i;

    /* renamed from: j, reason: collision with root package name */
    private ImageView f7859j;

    /* renamed from: k, reason: collision with root package name */
    private ImageView f7860k;

    /* renamed from: l, reason: collision with root package name */
    private ImageView f7861l;

    /* renamed from: m, reason: collision with root package name */
    private WindowManager f7862m;

    /* renamed from: n, reason: collision with root package name */
    private ActivityManager f7863n;

    /* renamed from: o, reason: collision with root package name */
    private Method f7864o;

    public MagicWindowIndicator(Context context) {
        this.f7852c = context;
        this.f7862m = (WindowManager) context.getSystemService("window");
        this.f7863n = (ActivityManager) this.f7852c.getSystemService("activity");
    }

    private void e() {
        if (this.f7857h) {
            return;
        }
        this.f7857h = true;
        ImageView imageView = new ImageView(this.f7852c);
        this.f7861l = imageView;
        imageView.setImageResource(R.drawable.magic_window_arrow_down);
        WindowManager.LayoutParams l2 = l("MagicWindowIndicatorBottom");
        l2.gravity = 81;
        l2.y = this.f7852c.getResources().getDimensionPixelSize(R.dimen.magic_window_indicator_margin);
        this.f7861l.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.magicwindow.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MagicWindowIndicator.this.m(view);
            }
        });
        this.f7862m.addView(this.f7861l, l2);
    }

    private void f(int i2) {
        if (this.f7854e) {
            return;
        }
        this.f7854e = true;
        ImageView imageView = new ImageView(this.f7852c);
        this.f7858i = imageView;
        imageView.setImageResource(R.drawable.magic_window_arrow_left);
        WindowManager.LayoutParams l2 = l("MagicWindowIndicatorLeft");
        l2.gravity = 19;
        l2.x = this.f7852c.getResources().getDimensionPixelSize(R.dimen.magic_window_indicator_margin) + i2;
        this.f7858i.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.magicwindow.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MagicWindowIndicator.this.n(view);
            }
        });
        this.f7862m.addView(this.f7858i, l2);
    }

    private void g() {
        if (this.f7855f) {
            return;
        }
        this.f7855f = true;
        ImageView imageView = new ImageView(this.f7852c);
        this.f7859j = imageView;
        imageView.setImageResource(R.drawable.magic_window_arrow_right);
        WindowManager.LayoutParams l2 = l("MagicWindowIndicatorRight");
        l2.gravity = 21;
        l2.x = this.f7852c.getResources().getDimensionPixelSize(R.dimen.magic_window_indicator_margin);
        this.f7859j.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.magicwindow.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MagicWindowIndicator.this.o(view);
            }
        });
        this.f7862m.addView(this.f7859j, l2);
    }

    private void h(int i2) {
        if (this.f7856g) {
            return;
        }
        this.f7856g = true;
        ImageView imageView = new ImageView(this.f7852c);
        this.f7860k = imageView;
        imageView.setImageResource(R.drawable.magic_window_arrow_up);
        WindowManager.LayoutParams l2 = l("MagicWindowIndicatorTop");
        l2.gravity = 49;
        l2.y = this.f7852c.getResources().getDimensionPixelSize(R.dimen.magic_window_indicator_margin) + i2;
        this.f7860k.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.magicwindow.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MagicWindowIndicator.this.p(view);
            }
        });
        this.f7862m.addView(this.f7860k, l2);
    }

    private boolean i(float f2, float f3) {
        return Math.abs(f2 - f3) < 0.01f;
    }

    private WindowManager.LayoutParams l(String str) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(2038);
        layoutParams.flags = 67109672;
        layoutParams.layoutInDisplayCutoutMode = 3;
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.format = -2;
        layoutParams.setTitle(str);
        WindowManagerWrapper.LayoutParams.addHidePrivateTrustedOverlayFlags(layoutParams);
        return layoutParams;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void m(View view) {
        GaLog.e("MagicWindowMgr", "Indicator bottom clicked, pos " + this.f7851b);
        if (i(this.f7851b, 0.0f)) {
            v(0.5f);
        } else if (i(this.f7851b, 0.5f)) {
            v(1.0f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void n(View view) {
        GaLog.e("MagicWindowMgr", "Indicator left clicked, pos " + this.f7851b);
        if (i(this.f7851b, 1.0f)) {
            v(0.5f);
        } else if (i(this.f7851b, 0.5f)) {
            v(0.0f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void o(View view) {
        GaLog.e("MagicWindowMgr", "Indicator right clicked, pos " + this.f7851b);
        if (i(this.f7851b, 0.0f)) {
            v(0.5f);
        } else if (i(this.f7851b, 0.5f)) {
            v(1.0f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void p(View view) {
        GaLog.e("MagicWindowMgr", "Indicator top clicked, pos " + this.f7851b);
        if (i(this.f7851b, 1.0f)) {
            v(0.5f);
        } else if (i(this.f7851b, 0.5f)) {
            v(0.0f);
        }
    }

    private void q() {
        s();
        t();
        u();
        r();
    }

    private void r() {
        if (this.f7857h) {
            this.f7857h = false;
            this.f7862m.removeView(this.f7861l);
        }
    }

    private void s() {
        if (this.f7854e) {
            this.f7854e = false;
            this.f7862m.removeView(this.f7858i);
        }
    }

    private void t() {
        if (this.f7855f) {
            this.f7855f = false;
            this.f7862m.removeView(this.f7859j);
        }
    }

    private void u() {
        if (this.f7856g) {
            this.f7856g = false;
            this.f7862m.removeView(this.f7860k);
        }
    }

    private void v(float f2) {
        if (this.f7864o == null) {
            try {
                Method method = this.f7863n.getClass().getMethod("setMagicWindowPosition", String.class, Float.TYPE);
                this.f7864o = method;
                method.setAccessible(true);
            } catch (NoSuchMethodException unused) {
                GaLog.e("MagicWindowMgr", "set magic window position doesn't exist");
                return;
            }
        }
        try {
            this.f7864o.invoke(this.f7863n, this.f7853d.b(), Float.valueOf(f2));
        } catch (IllegalAccessException e2) {
            GaLog.f("MagicWindowMgr", "set magic window position error", e2);
        } catch (InvocationTargetException e3) {
            GaLog.f("MagicWindowMgr", "set magic window position error", e3);
        }
    }

    private void x() {
        this.f7851b = this.f7853d.c();
        if ("port".equals(this.f7853d.a())) {
            if (i(this.f7851b, 0.0f)) {
                s();
                g();
                return;
            } else if (i(this.f7851b, 0.5f)) {
                f(0);
                g();
                return;
            } else {
                f(0);
                t();
                return;
            }
        }
        if (i(this.f7851b, 0.0f)) {
            u();
            e();
        } else if (i(this.f7851b, 0.5f)) {
            h(0);
            e();
        } else {
            h(0);
            r();
        }
    }

    public void j() {
        if (this.f7850a) {
            this.f7850a = false;
            q();
        }
    }

    public void k(PrintWriter printWriter) {
        StringBuilder sb = new StringBuilder();
        if (this.f7856g) {
            sb.append("top ");
        }
        if (this.f7857h) {
            sb.append("bottom ");
        }
        if (this.f7854e) {
            sb.append("left ");
        }
        if (this.f7855f) {
            sb.append("right ");
        }
        if (sb.length() > 0) {
            sb.append("indicator added");
            printWriter.println(sb);
        }
    }

    public void w(MagicWindowPosition magicWindowPosition) {
        if (this.f7850a) {
            j();
        }
        GaLog.e("MagicWindowMgr", "Indicator show");
        this.f7850a = true;
        this.f7853d = magicWindowPosition;
        x();
    }
}
