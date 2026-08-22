package cn.nubia.screensaver.card;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.GameDurationManager;
import cn.nubia.screensaver.util.DefaultUtil;
import cn.nubia.screensaver.util.ShortCutUtil;
import com.zte.gameassist.common.GameCheck;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class CurrentPlayCard extends BaseCard implements GameDurationManager.CallBack {
    private String A;
    private Drawable B;
    private long C;
    private String D;
    private TextView v;
    private TextView w;
    private TextView x;
    private TextView y;
    private ImageView z;

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void B(boolean z) {
        DefaultUtil.d(this.f8994h);
        s(z);
        t();
    }

    public void A(final boolean z) {
        ViewGroup viewGroup = this.f8998l;
        if (viewGroup != null) {
            viewGroup.post(new Runnable() { // from class: cn.nubia.screensaver.card.a
                @Override // java.lang.Runnable
                public final void run() {
                    CurrentPlayCard.this.B(z);
                }
            });
        }
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected void a(View view) {
        this.v = (TextView) view.findViewById(R.id.tv_app_name);
        this.z = (ImageView) view.findViewById(R.id.iv_app_icon);
        this.w = (TextView) view.findViewById(R.id.tv_app_run_hour);
        this.x = (TextView) view.findViewById(R.id.tv_app_run_hour_tag);
        this.y = (TextView) view.findViewById(R.id.tv_app_run_minute);
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected String b() {
        return this.f8994h.getString(R.string.current_play_card_title);
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected int[] d() {
        return new int[]{R.layout.card_current_play};
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    public void f() {
        GameDurationManager.n().o(this);
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x007d -> B:17:0x0080). Please report as a decompilation issue!!! */
    @Override // cn.nubia.gameassist.common.GameDurationManager.CallBack
    public void onBundlePrepare(Bundle bundle) {
        if (bundle == null) {
            A(true);
            return;
        }
        String string = bundle.getString("pkg");
        this.C = bundle.getLong("time");
        GaLog.a(this.f8993c, "p:" + string + ",t:" + this.C);
        String str = this.D;
        if (str == null || !str.equals(string)) {
            try {
                if (SystemMgr.M(string)) {
                    GameCheck.WechatMiniAppInfo wechatMiniAppInfo = (GameCheck.WechatMiniAppInfo) GameCheck.e(string);
                    if (wechatMiniAppInfo != null) {
                        this.A = wechatMiniAppInfo.g();
                        this.B = ShortCutUtil.b().c(wechatMiniAppInfo);
                    }
                } else {
                    PackageManager packageManager = this.f8994h.getPackageManager();
                    ApplicationInfo applicationInfo = packageManager.getApplicationInfo(string, 128);
                    this.B = applicationInfo.loadIcon(packageManager);
                    this.A = applicationInfo.loadLabel(packageManager).toString();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        this.D = string;
        A(false);
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected void p(ViewGroup viewGroup, boolean z, boolean z2) {
        super.p(viewGroup, z, z2);
        a(viewGroup);
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected void w(ViewGroup viewGroup, boolean z) {
        super.w(viewGroup, z);
        if (!TextUtils.isEmpty(this.A)) {
            this.v.setText(this.A);
        }
        Drawable drawable = this.B;
        if (drawable != null) {
            this.z.setImageDrawable(drawable);
        }
        int i2 = (int) (this.C / 1000);
        int i3 = i2 / 3600;
        int i4 = (i2 % 3600) / 60;
        if (i3 == 0 && i4 == 0) {
            i4 = 1;
        }
        if (i3 == 0) {
            this.w.setVisibility(8);
            this.x.setVisibility(8);
        } else {
            this.w.setVisibility(0);
            this.x.setVisibility(0);
            this.w.setText(String.valueOf(i3));
        }
        this.y.setText(String.valueOf(i4));
    }
}
