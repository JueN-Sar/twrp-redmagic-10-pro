package cn.nubia.gameassist.onemorething;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.BaseViewController;
import cn.nubia.gameassist.panel.GameAssistWindowManager;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public class OneMoreThingViewController extends BaseViewController implements View.OnClickListener {

    /* renamed from: q, reason: collision with root package name */
    private TextView f6737q;

    /* renamed from: r, reason: collision with root package name */
    private ImageButton f6738r;

    /* renamed from: s, reason: collision with root package name */
    private boolean f6739s;
    private final int t;
    private final int u;
    private OMTInfo v;
    private final OneMoreThingManager w;
    private final Runnable x;
    private final Runnable y;
    public static final boolean z = !ZteFeature.IS_INTER_VERSION;
    private static final Uri A = Uri.parse("content://com.zte.onemorething.contentProvider");

    public OneMoreThingViewController(GameAssistWindowManager gameAssistWindowManager) {
        super(gameAssistWindowManager);
        this.f6739s = true;
        this.t = 8000;
        this.u = 1000;
        this.x = new Runnable() { // from class: cn.nubia.gameassist.onemorething.OneMoreThingViewController.1
            @Override // java.lang.Runnable
            public void run() {
                if (OneMoreThingViewController.this.f6737q == null) {
                    GaLog.a("OneMoreThingViewController", "mTextView is null");
                    return;
                }
                GaLog.a("OneMoreThingViewController", "in time task and need update");
                OneMoreThingViewController.this.c0();
                OneMoreThingViewController.this.a0();
                ((BaseViewController) OneMoreThingViewController.this).f6125o.postDelayed(this, 8000L);
            }
        };
        this.y = new Runnable() { // from class: cn.nubia.gameassist.onemorething.OneMoreThingViewController.2
            @Override // java.lang.Runnable
            public void run() {
                if (OneMoreThingViewController.this.f6737q != null) {
                    OneMoreThingViewController.this.f6737q.setSelected(true);
                    OneMoreThingViewController.this.f6738r.setSelected(true);
                }
            }
        };
        this.w = OneMoreThingManager.g();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a0() {
        this.f6737q.setSelected(false);
        this.f6738r.setSelected(false);
        this.f6125o.postDelayed(this.y, 1000L);
    }

    private void b0(int i2) {
        float f2 = this.f6117c.getResources().getDisplayMetrics().density;
        this.f6737q.setCompoundDrawablesRelativeWithIntrinsicBounds((Drawable) null, (Drawable) null, new BitmapDrawable(this.f6117c.getResources(), BitmapFactory.decodeResource(this.f6117c.getResources(), i2)), (Drawable) null);
        this.f6737q.setCompoundDrawablePadding((int) (f2 * 3.0f));
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public int C() {
        return R.id.game_assist_middle_omt;
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void P() {
        GaLog.k("OneMoreThingViewController", "recycleView");
        this.f6739s = true;
        e0();
        this.f6737q.setClickable(false);
        this.f6737q = null;
        this.f6738r = null;
    }

    public void W() {
        if (this.f6738r == null || this.v == null) {
            return;
        }
        GaLog.a("OneMoreThingViewController", "clickThumb:");
        if (this.v.b()) {
            this.f6738r.setImageResource(R.drawable.thumb_off);
            this.v.hasVote = 0;
        } else {
            this.f6738r.setImageResource(R.drawable.thumb_up);
            this.v.hasVote = 1;
        }
        OneMoreThingManager oneMoreThingManager = this.w;
        OMTInfo oMTInfo = this.v;
        oneMoreThingManager.l(oMTInfo.id, oMTInfo.hasVote);
    }

    public void X(boolean z2) {
        GaLog.a("OneMoreThingViewController", "exchangeTips  textView = " + this.f6737q);
        if (z2) {
            d0(0);
            return;
        }
        e0();
        if (this.f6737q != null) {
            c0();
            a0();
        }
        d0(8000);
    }

    public boolean Y() {
        return Settings.Global.getInt(this.f6117c.getContentResolver(), "cancel_OMTDialog", 0) == 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0035  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.os.Bundle Z(java.lang.String r3, android.os.Bundle r4) {
        /*
            r2 = this;
            r0 = 0
            android.content.Context r2 = r2.f6117c     // Catch: java.lang.Throwable -> L22 java.lang.Exception -> L24
            android.content.ContentResolver r2 = r2.getContentResolver()     // Catch: java.lang.Throwable -> L22 java.lang.Exception -> L24
            android.net.Uri r1 = cn.nubia.gameassist.onemorething.OneMoreThingViewController.A     // Catch: java.lang.Throwable -> L22 java.lang.Exception -> L24
            android.content.ContentProviderClient r2 = r2.acquireUnstableContentProviderClient(r1)     // Catch: java.lang.Throwable -> L22 java.lang.Exception -> L24
            if (r2 != 0) goto L15
            if (r2 == 0) goto L14
            r2.close()
        L14:
            return r0
        L15:
            android.os.Bundle r3 = r2.call(r3, r0, r4)     // Catch: java.lang.Throwable -> L1d java.lang.Exception -> L20
            r2.close()
            return r3
        L1d:
            r3 = move-exception
            r0 = r2
            goto L33
        L20:
            r3 = move-exception
            goto L26
        L22:
            r3 = move-exception
            goto L33
        L24:
            r3 = move-exception
            r2 = r0
        L26:
            java.lang.String r4 = "OneMoreThingViewController"
            java.lang.String r1 = "linkOMTProvider: e = "
            com.zte.gameassist.utils.GaLog.c(r4, r1, r3)     // Catch: java.lang.Throwable -> L1d
            if (r2 == 0) goto L32
            r2.close()
        L32:
            return r0
        L33:
            if (r0 == 0) goto L38
            r0.close()
        L38:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gameassist.onemorething.OneMoreThingViewController.Z(java.lang.String, android.os.Bundle):android.os.Bundle");
    }

    public void c0() {
        TextView textView;
        OMTInfo h2 = this.w.h();
        this.v = h2;
        if (h2 == null) {
            return;
        }
        String a2 = h2.a(this.f6117c);
        if (TextUtils.isEmpty(a2) || (textView = this.f6737q) == null || this.f6738r == null) {
            return;
        }
        textView.setText(a2);
        this.f6738r.setImageResource(this.v.b() ? R.drawable.thumb_up : R.drawable.thumb_off);
        GaLog.a("OneMoreThingViewController", "mShowInfo:" + this.v);
        int i2 = this.v.hotLevel;
        int i3 = 0;
        if (i2 == 0) {
            this.f6737q.setCompoundDrawablesRelativeWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
            this.f6737q.setCompoundDrawablePadding(0);
        } else if (i2 == 1) {
            i3 = R.drawable.game_assist_omt_hot;
        } else if (i2 == 2) {
            i3 = R.drawable.game_assist_omt_hot2;
        } else if (i2 == 3) {
            i3 = R.drawable.game_assist_omt_hot3;
        }
        b0(i3);
    }

    public void d0(int i2) {
        GaLog.a("OneMoreThingViewController", "startTask");
        this.f6125o.postDelayed(this.x, i2);
    }

    public void e0() {
        GaLog.a("OneMoreThingViewController", "stopTask");
        this.f6125o.removeCallbacks(this.x);
        this.f6125o.removeCallbacks(this.y);
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void h(PrintWriter printWriter, String str) {
        super.h(printWriter, str);
        printWriter.println(str + "  mTextView = " + this.f6737q);
        printWriter.println(str + "  autoChange = " + this.f6739s);
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void o(View view) {
        GaLog.k("OneMoreThingViewController", "initModuleView");
        TextView textView = (TextView) i(R.id.game_assist_middle_one_more_thing);
        this.f6737q = textView;
        textView.setOnClickListener(this);
        this.f6737q.setSingleLine(true);
        this.f6737q.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        this.f6737q.setClickable(true);
        ImageButton imageButton = (ImageButton) i(R.id.game_assist_middle_thumb);
        this.f6738r = imageButton;
        if (z) {
            imageButton.setOnClickListener(this);
        } else {
            imageButton.setVisibility(4);
        }
        X(this.f6739s);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.game_assist_middle_one_more_thing) {
            GaLog.a("OneMoreThingViewController", "onClick game_assist_middle_one_more_thing isCancelOMTDialog = " + Y());
            this.f6739s = false;
            if (Y()) {
                Z("showSecondOMTDialog", null);
                GameAssistWindowManager.O(this.f6117c).g0("showSecondOMTDialog");
            }
            X(this.f6739s);
            return;
        }
        if (view.getId() == R.id.game_assist_middle_thumb) {
            GaLog.a("OneMoreThingViewController", "onClick game_assist_middle_thumb isCancelOMTDialog = " + Y());
            if (!Y()) {
                W();
            } else {
                Z("showSecondOMTDialog", null);
                GameAssistWindowManager.O(this.f6117c).g0("showSecondOMTDialog");
            }
        }
    }
}
