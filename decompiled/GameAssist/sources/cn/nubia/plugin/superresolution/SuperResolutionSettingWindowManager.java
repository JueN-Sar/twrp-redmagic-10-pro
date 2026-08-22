package cn.nubia.plugin.superresolution;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.plugin.PluginUtils;
import cn.nubia.gameassist.utils.Utils;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.common.ThreadManager;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.WindowManagerWrapper;

/* loaded from: classes.dex */
public class SuperResolutionSettingWindowManager implements GameMonitor.Callback {

    /* renamed from: s, reason: collision with root package name */
    private static volatile SuperResolutionSettingWindowManager f8680s;

    /* renamed from: h, reason: collision with root package name */
    private Context f8682h;

    /* renamed from: i, reason: collision with root package name */
    private WindowManager f8683i;

    /* renamed from: k, reason: collision with root package name */
    protected LinearLayout f8685k;

    /* renamed from: l, reason: collision with root package name */
    private Handler f8686l;

    /* renamed from: m, reason: collision with root package name */
    private RadioGroup f8687m;

    /* renamed from: n, reason: collision with root package name */
    private RadioGroup f8688n;

    /* renamed from: o, reason: collision with root package name */
    private String f8689o;

    /* renamed from: p, reason: collision with root package name */
    private String f8690p;

    /* renamed from: q, reason: collision with root package name */
    private String f8691q;

    /* renamed from: r, reason: collision with root package name */
    private String f8692r;

    /* renamed from: c, reason: collision with root package name */
    private int f8681c = 0;

    /* renamed from: j, reason: collision with root package name */
    private boolean f8684j = false;

    public SuperResolutionSettingWindowManager(Context context) {
        this.f8682h = context;
        this.f8683i = (WindowManager) context.getSystemService("window");
        SystemMgr.y(this.f8682h).h(this);
        this.f8686l = new Handler(ThreadManager.c().e());
    }

    private int g(float f2) {
        return Math.round(f2 * this.f8682h.getResources().getDisplayMetrics().density);
    }

    private String h(int i2) {
        return i2 == R.id.plugin_super_resolution_frame_rate_origin ? "frameRate_origin" : i2 == R.id.plugin_super_resolution_frame_rate_super ? "frameRate_super" : "frameRate_ultra";
    }

    private int i(String str) {
        str.hashCode();
        return !str.equals("frameRate_super") ? !str.equals("frameRate_origin") ? R.id.plugin_super_resolution_frame_rate_ultra : R.id.plugin_super_resolution_frame_rate_origin : R.id.plugin_super_resolution_frame_rate_super;
    }

    private String j(int i2) {
        return i2 == R.id.plugin_super_resolution_image_quality_origin ? "origin" : i2 == R.id.plugin_super_resolution_image_quality_high ? "high" : "super";
    }

    private int k(String str) {
        str.hashCode();
        return !str.equals("origin") ? !str.equals("high") ? R.id.plugin_super_resolution_image_quality_super : R.id.plugin_super_resolution_image_quality_high : R.id.plugin_super_resolution_image_quality_origin;
    }

    public static SuperResolutionSettingWindowManager l(Context context) {
        if (f8680s == null) {
            synchronized (SuperResolutionSettingWindowManager.class) {
                try {
                    if (f8680s == null) {
                        f8680s = new SuperResolutionSettingWindowManager(context);
                    }
                } finally {
                }
            }
        }
        return f8680s;
    }

    private WindowManager.LayoutParams m() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(2003, 75826952, -3);
        layoutParams.flags = (layoutParams.flags & (-131073)) | WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_COLOR_SPACE_AGNOSTIC;
        layoutParams.type = 2038;
        layoutParams.setTitle("SuperResolutionSettingWindow");
        layoutParams.width = InflaterHelper.d().getDimensionPixelSize(R.dimen.plugin_super_resolution_setting_width);
        layoutParams.height = InflaterHelper.d().getDimensionPixelSize(R.dimen.plugin_super_resolution_setting_width);
        if (RotationMgr.j()) {
            layoutParams.gravity = 21;
            layoutParams.x = g(16.0f);
        } else {
            layoutParams.gravity = 81;
            layoutParams.y = Utils.K(this.f8682h) ? g(36.0f) : g(16.0f);
        }
        return layoutParams;
    }

    private void n() {
        this.f8685k = (LinearLayout) InflaterHelper.f(R.layout.plugin_superresolution_setting_view, null);
        SuperResolutionTypeDataManager.c().g();
        this.f8690p = this.f8682h.getString(R.string.plugin_super_resolution_setting_bottom_tips);
        View findViewById = this.f8685k.findViewById(R.id.plugin_super_resolution_setting_close);
        this.f8687m = (RadioGroup) this.f8685k.findViewById(R.id.plugin_super_resolution_image_quality_radioGroup);
        this.f8688n = (RadioGroup) this.f8685k.findViewById(R.id.plugin_super_resolution_frame_rate_radioGroup);
        s();
        this.f8687m.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: cn.nubia.plugin.superresolution.b
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i2) {
                SuperResolutionSettingWindowManager.this.p(radioGroup, i2);
            }
        });
        this.f8688n.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: cn.nubia.plugin.superresolution.c
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i2) {
                SuperResolutionSettingWindowManager.this.q(radioGroup, i2);
            }
        });
        findViewById.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.plugin.superresolution.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SuperResolutionSettingWindowManager.this.r(view);
            }
        });
        TextView textView = (TextView) this.f8685k.findViewById(R.id.plugin_super_resolution_setting_bottom_tv);
        GaLog.a("SuperResolutionSettingWindowManager", "settingDesc : " + this.f8690p);
        textView.setText(this.f8690p);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void o(String str) {
        e(str);
        this.f8685k = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void p(RadioGroup radioGroup, int i2) {
        this.f8691q = j(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void q(RadioGroup radioGroup, int i2) {
        this.f8692r = h(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void r(View view) {
        e("click close -> close the view");
        SuperResolutionTypeDataManager.c().h(SystemMgr.v(), "imageQuality", this.f8691q);
        SuperResolutionTypeDataManager.c().h(SystemMgr.v(), "frameRate", this.f8692r);
        GaLog.a("SuperResolutionSettingWindowManager", "imageQuality = " + SuperResolutionTypeDataManager.c().d(SystemMgr.v(), "imageQuality"));
        GaLog.a("SuperResolutionSettingWindowManager", "frameRate = " + SuperResolutionTypeDataManager.c().d(SystemMgr.v(), "frameRate"));
        SuperResolutionViewController.q(this.f8682h).G(this.f8689o);
        if (SuperResolutionViewController.q(this.f8682h).x()) {
            SuperResolutionViewController.q(this.f8682h).L(this.f8689o, false);
        } else {
            SuperResolutionViewController.q(this.f8682h).L(this.f8689o, true);
        }
    }

    private void s() {
        t(PluginUtils.f(this.f8682h).e(SystemMgr.v()));
        this.f8691q = SuperResolutionTypeDataManager.c().d(SystemMgr.v(), "imageQuality");
        GaLog.a("SuperResolutionSettingWindowManager", "loadSelectedRadioButton imageQuality = " + this.f8691q);
        this.f8687m.check(k(this.f8691q));
        this.f8692r = SuperResolutionTypeDataManager.c().d(SystemMgr.v(), "frameRate");
        GaLog.a("SuperResolutionSettingWindowManager", "loadSelectedRadioButton frameRate = " + this.f8692r);
        this.f8688n.check(i(this.f8692r));
    }

    private void t(int i2) {
        int i3 = i2 / 10;
        GaLog.a("SuperResolutionSettingWindowManager", "parseSupportFunction interpolationSupport = " + i3);
        if (i3 == 0) {
            this.f8685k.findViewById(R.id.plugin_super_resolution_image_quality_layout).setVisibility(8);
            this.f8690p = u(this.f8690p);
        } else if (i3 == 1) {
            this.f8685k.findViewById(R.id.plugin_super_resolution_image_quality_super).setVisibility(8);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f8685k.findViewById(R.id.plugin_super_resolution_image_quality_high).getLayoutParams();
            layoutParams.weight = 2.0f;
            this.f8685k.findViewById(R.id.plugin_super_resolution_image_quality_high).setLayoutParams(layoutParams);
        }
        int i4 = i2 % 10;
        GaLog.a("SuperResolutionSettingWindowManager", "parseSupportFunction superResolutionSupport = " + i4);
        if (i4 == 0) {
            this.f8685k.findViewById(R.id.plugin_super_resolution_frame_rate_layout).setVisibility(8);
            this.f8690p = u(this.f8690p);
        } else {
            if (i4 != 1) {
                return;
            }
            this.f8685k.findViewById(R.id.plugin_super_resolution_frame_rate_ultra).setVisibility(8);
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.f8685k.findViewById(R.id.plugin_super_resolution_frame_rate_super).getLayoutParams();
            layoutParams2.weight = 2.0f;
            this.f8685k.findViewById(R.id.plugin_super_resolution_frame_rate_super).setLayoutParams(layoutParams2);
        }
    }

    public static String u(String str) {
        int indexOf;
        GaLog.a("SuperResolutionSettingWindowManager", "processText inputText = " + str);
        String[] split = str.split("\n");
        StringBuilder sb = new StringBuilder();
        int i2 = 1;
        for (String str2 : split) {
            if (!str2.startsWith("2.") && (indexOf = str2.indexOf(46)) != -1) {
                String trim = str2.substring(indexOf + 1).trim();
                sb.append(i2);
                sb.append(".");
                sb.append(trim);
                sb.append("\n");
                i2++;
            }
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    public void e(String str) {
        try {
            if (this.f8684j) {
                this.f8684j = false;
                this.f8683i.removeView(this.f8685k);
                GaLog.a("SuperResolutionSettingWindowManager", "close setting reason = " + str);
            }
        } catch (Exception e2) {
            GaLog.b("SuperResolutionSettingWindowManager", "close setting view Exception = " + e2);
            e("Exception");
        }
    }

    public void f(final String str) {
        if (this.f8685k != null) {
            this.f8686l.post(new Runnable() { // from class: cn.nubia.plugin.superresolution.a
                @Override // java.lang.Runnable
                public final void run() {
                    SuperResolutionSettingWindowManager.this.o(str);
                }
            });
        }
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStart */
    public void y() {
        super.y();
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStop */
    public void z() {
        GaLog.a("SuperResolutionSettingWindowManager", "onGameStop");
        f("no GameScene");
    }

    public void v(String str) {
        if (this.f8684j) {
            return;
        }
        this.f8684j = true;
        this.f8689o = str;
        WindowManager.LayoutParams m2 = m();
        n();
        if (this.f8685k != null) {
            s();
        }
        this.f8683i.addView(this.f8685k, m2);
        GaLog.a("SuperResolutionSettingWindowManager", "show setting");
    }
}
