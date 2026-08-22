package cn.nubia.hostassist;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import cn.nubia.gameassist.GameAssistApplication;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.utils.AppsHelper;
import cn.nubia.gameassist.utils.CommonUtil;
import cn.nubia.gameassist.utils.Utils;
import cn.nubia.gameassist.utils.WindowManagerUtil;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class HostFreeformWindow {

    /* renamed from: n, reason: collision with root package name */
    private static List f7811n = Arrays.asList("com.ume.browser", "cn.nubia.browser", "com.tencent.mobileqq", "com.tencent.mm", "com.tencent.qqmusic", "tv.danmaku.bili", "com.netease.cloudmusic");

    /* renamed from: o, reason: collision with root package name */
    private static List f7812o = Arrays.asList("com.android.chrome", "com.ume.browser", "cn.nubia.browser");

    /* renamed from: a, reason: collision with root package name */
    private WindowManager.LayoutParams f7813a;

    /* renamed from: b, reason: collision with root package name */
    protected View f7814b;

    /* renamed from: d, reason: collision with root package name */
    private Context f7816d;

    /* renamed from: f, reason: collision with root package name */
    private WindowManagerUtil f7818f;

    /* renamed from: c, reason: collision with root package name */
    private int f7815c = 0;

    /* renamed from: e, reason: collision with root package name */
    private Handler f7817e = new Handler(Looper.getMainLooper());

    /* renamed from: g, reason: collision with root package name */
    private boolean f7819g = false;

    /* renamed from: h, reason: collision with root package name */
    private int f7820h = 0;

    /* renamed from: i, reason: collision with root package name */
    private boolean f7821i = true;

    /* renamed from: j, reason: collision with root package name */
    private Rect f7822j = new Rect();

    /* renamed from: k, reason: collision with root package name */
    private List f7823k = Arrays.asList(Integer.valueOf(R.id.host_freeform_panel_item_0), Integer.valueOf(R.id.host_freeform_panel_item_1), Integer.valueOf(R.id.host_freeform_panel_item_2), Integer.valueOf(R.id.host_freeform_panel_item_3), Integer.valueOf(R.id.host_freeform_panel_item_4), Integer.valueOf(R.id.host_freeform_panel_item_5), Integer.valueOf(R.id.host_freeform_panel_item_6), Integer.valueOf(R.id.host_freeform_panel_item_7), Integer.valueOf(R.id.host_freeform_panel_item_8));

    /* renamed from: l, reason: collision with root package name */
    private List f7824l = Arrays.asList(Integer.valueOf(R.id.host_freeform_panel_item_port_0), Integer.valueOf(R.id.host_freeform_panel_item_port_1), Integer.valueOf(R.id.host_freeform_panel_item_port_2), Integer.valueOf(R.id.host_freeform_panel_item_port_3), Integer.valueOf(R.id.host_freeform_panel_item_port_4), Integer.valueOf(R.id.host_freeform_panel_item_port_5), Integer.valueOf(R.id.host_freeform_panel_item_port_6), Integer.valueOf(R.id.host_freeform_panel_item_port_7), Integer.valueOf(R.id.host_freeform_panel_item_port_8));

    /* renamed from: m, reason: collision with root package name */
    private ArrayList f7825m = new ArrayList();

    public HostFreeformWindow(Context context) {
        this.f7816d = context;
        c();
    }

    private void d() {
        List<String> list = CommonUtil.b() ? f7812o : f7811n;
        this.f7825m.clear();
        String o2 = HostAssistMgr.n().o();
        int i2 = 0;
        for (String str : list) {
            if (!str.equals(o2) && Utils.y(this.f7816d, str)) {
                HostFreeformItemData hostFreeformItemData = new HostFreeformItemData();
                hostFreeformItemData.f7808a = str;
                hostFreeformItemData.f7809b = AppsHelper.b(str);
                int i3 = i2 + 1;
                hostFreeformItemData.f7810c = ((Integer) (this.f7821i ? this.f7823k : this.f7824l).get(i2)).intValue();
                this.f7825m.add(hostFreeformItemData);
                i2 = i3;
            }
        }
        GaLog.e("HostAssistMgr", "initAppList num=" + this.f7825m.size());
    }

    private void e() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(2027, 75826952, -3);
        this.f7813a = layoutParams;
        layoutParams.flags = (layoutParams.flags & (-131073)) | WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_COLOR_SPACE_AGNOSTIC;
        WindowManagerWrapper.LayoutParams.setFitInsetsTypes(layoutParams);
        WindowManager.LayoutParams layoutParams2 = this.f7813a;
        layoutParams2.gravity = 51;
        layoutParams2.setTitle("HostFreeformWindow");
        WindowManagerWrapper.LayoutParams.addHidePrivateTrustedOverlayFlags(this.f7813a);
        this.f7813a.width = HostAssistUtils.b() * (this.f7821i ? 1 : 2);
        int a2 = HostAssistUtils.a(this.f7825m.size()) * (this.f7821i ? 1 : 2);
        WindowManager.LayoutParams layoutParams3 = this.f7813a;
        layoutParams3.height = a2;
        Rect rect = this.f7822j;
        layoutParams3.x = rect.left;
        layoutParams3.y = (rect.top - a2) + 14;
    }

    private void f() {
        if (this.f7814b != null) {
            return;
        }
        this.f7814b = LayoutInflater.from(this.f7816d).inflate(this.f7821i ? R.layout.host_freeform_panel : R.layout.host_freeform_panel_port, (ViewGroup) null);
        Iterator it = this.f7825m.iterator();
        while (it.hasNext()) {
            HostFreeformItemData hostFreeformItemData = (HostFreeformItemData) it.next();
            g(hostFreeformItemData.f7808a, hostFreeformItemData);
        }
    }

    private void g(final String str, HostFreeformItemData hostFreeformItemData) {
        RelativeLayout relativeLayout = (RelativeLayout) this.f7814b.findViewById(hostFreeformItemData.f7810c);
        relativeLayout.setVisibility(0);
        ((TextView) relativeLayout.findViewById(R.id.host_freeform_item_txt)).setText(hostFreeformItemData.f7809b);
        ImageView imageView = (ImageView) relativeLayout.findViewById(R.id.host_freeform_item_img);
        imageView.setImageDrawable(ContextCompat.e(GameAssistApplication.j(), R.drawable.pip_icon_placeholder));
        Drawable f2 = AppsHelper.f(this.f7816d, str);
        int dimensionPixelSize = this.f7816d.getResources().getDimensionPixelSize(R.dimen.pip_dialog_icon_width);
        if (f2 != null) {
            f2.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
            imageView.setImageDrawable(f2);
        }
        relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.hostassist.HostFreeformWindow.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HostFreeformWindow.this.b("click app");
                if (HostAssistUtils.i(HostFreeformWindow.this.f7816d)) {
                    Toast.makeText(HostFreeformWindow.this.f7816d, "already has a small window, please close other", 1).show();
                    return;
                }
                GaLog.e("HostAssistMgr", "click packageName=" + str);
                HostAssistMgr.n().N(str);
            }
        });
    }

    public void b(String str) {
        if (this.f7819g) {
            this.f7819g = false;
            this.f7818f.b(this.f7814b);
            HostAssistMgr.F = false;
            GaLog.e("HostAssistMgr", "close reason=" + str);
        }
    }

    public void c() {
        this.f7818f = new WindowManagerUtil((WindowManager) this.f7816d.getSystemService(WindowManager.class));
    }

    public void h(boolean z, Rect rect) {
        this.f7821i = z;
        this.f7822j = rect;
        if (!this.f7819g) {
            this.f7819g = true;
            HostAssistMgr.F = true;
            d();
            e();
            f();
            this.f7818f.a(this.f7814b, this.f7813a);
        }
        GaLog.e("HostAssistMgr", "show isWindowAdd=" + this.f7819g);
    }
}
