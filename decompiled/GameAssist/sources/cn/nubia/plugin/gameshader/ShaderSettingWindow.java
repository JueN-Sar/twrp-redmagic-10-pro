package cn.nubia.plugin.gameshader;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.nubia.gameassist.GameAssistApplication;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.utils.Utils;
import cn.nubia.plugin.gameshader.ShaderSettingListView;
import com.zte.gameassist.common.FoldMgr;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class ShaderSettingWindow {

    /* renamed from: a, reason: collision with root package name */
    private WindowManager.LayoutParams f8500a;

    /* renamed from: b, reason: collision with root package name */
    protected RelativeLayout f8501b;

    /* renamed from: d, reason: collision with root package name */
    private Context f8503d;

    /* renamed from: f, reason: collision with root package name */
    private WindowManager f8505f;

    /* renamed from: i, reason: collision with root package name */
    private String[] f8508i;

    /* renamed from: j, reason: collision with root package name */
    private ShaderSettingListView f8509j;

    /* renamed from: l, reason: collision with root package name */
    private ListAdapter f8511l;

    /* renamed from: m, reason: collision with root package name */
    private TextView f8512m;

    /* renamed from: c, reason: collision with root package name */
    private int f8502c = 0;

    /* renamed from: e, reason: collision with root package name */
    private Handler f8504e = new Handler(Looper.getMainLooper());

    /* renamed from: g, reason: collision with root package name */
    private boolean f8506g = false;

    /* renamed from: h, reason: collision with root package name */
    private ArrayList f8507h = new ArrayList();

    /* renamed from: k, reason: collision with root package name */
    private int f8510k = 0;

    public ShaderSettingWindow(Context context) {
        this.f8503d = context;
        e();
    }

    private int d(int i2) {
        return (int) ((i2 * this.f8503d.getResources().getDisplayMetrics().density) + 0.5d);
    }

    private void f() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(2003, 75826952, -3);
        this.f8500a = layoutParams;
        layoutParams.flags = (layoutParams.flags & (-131073)) | WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_COLOR_SPACE_AGNOSTIC;
        WindowManagerWrapper.LayoutParams.setFitInsetsTypes(layoutParams);
        this.f8500a.setTitle("PluginGameShaderSetting");
        WindowManagerWrapper.LayoutParams.addHidePrivateTrustedOverlayFlags(this.f8500a);
        this.f8500a.width = this.f8502c;
        if (ZteFeature.isTabletProduct()) {
            this.f8500a.width = this.f8503d.getResources().getDimensionPixelSize(R.dimen.plugin_gameshader_setting_width);
            this.f8500a.height = this.f8503d.getResources().getDimensionPixelSize(R.dimen.plugin_gameshader_setting_height);
        } else {
            FoldMgr.c();
            if (FoldMgr.f() && FoldMgr.c().e()) {
                this.f8500a.width = this.f8503d.getResources().getDimensionPixelSize(R.dimen.plugin_gameshader_setting_width);
                this.f8500a.height = this.f8503d.getResources().getDimensionPixelSize(R.dimen.plugin_gameshader_setting_height);
            } else {
                WindowManager.LayoutParams layoutParams2 = this.f8500a;
                layoutParams2.width = this.f8502c;
                layoutParams2.height = RotationMgr.g() - d(8);
            }
        }
        if (RotationMgr.j()) {
            WindowManager.LayoutParams layoutParams3 = this.f8500a;
            layoutParams3.gravity = 21;
            layoutParams3.x = d(16);
            return;
        }
        this.f8500a.gravity = 81;
        if (Utils.K(this.f8503d)) {
            this.f8500a.y = d(40);
        } else {
            this.f8500a.y = d(16);
        }
    }

    private void g() {
        if (this.f8501b != null) {
            return;
        }
        RelativeLayout relativeLayout = (RelativeLayout) InflaterHelper.f(R.layout.plugin_shader_setting_view, null);
        this.f8501b = relativeLayout;
        this.f8512m = (TextView) relativeLayout.findViewById(R.id.plugin_shader_setting_title_txt);
        this.f8501b.findViewById(R.id.plugin_shader_setting_close).setOnClickListener(new View.OnClickListener(this) { // from class: cn.nubia.plugin.gameshader.ShaderSettingWindow.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ShaderMgr.t().r();
            }
        });
        ShaderSettingListView shaderSettingListView = (ShaderSettingListView) this.f8501b.findViewById(R.id.plugin_shader_setting_listview);
        this.f8509j = shaderSettingListView;
        shaderSettingListView.a();
        this.f8511l = this.f8509j.getAdapter();
    }

    private void h() {
        String[] stringArray = GameAssistApplication.j().getResources().getStringArray(R.array.gameshader_list);
        this.f8508i = stringArray;
        int length = stringArray.length;
        this.f8507h.clear();
        for (int i2 = 0; i2 < this.f8508i.length; i2++) {
            ShaderSettingItemData shaderSettingItemData = new ShaderSettingItemData();
            String str = this.f8508i[i2];
            shaderSettingItemData.f8489b = str;
            shaderSettingItemData.f8488a = ShaderUtils.b(str);
            shaderSettingItemData.f8490c = ShaderUtils.e(shaderSettingItemData.f8489b);
            shaderSettingItemData.f8491d = ShaderUtils.d(shaderSettingItemData.f8489b);
            shaderSettingItemData.f8492e = ShaderUtils.a(shaderSettingItemData.f8489b);
            if (ShaderMgr.t().s().f8463d == shaderSettingItemData.f8488a) {
                this.f8510k = i2;
            }
            this.f8507h.add(shaderSettingItemData);
        }
    }

    private void i() {
        h();
        this.f8509j.setSelectedPosition(this.f8510k);
        ((ShaderSettingListView.ShaderListAdapter) this.f8511l).a(this.f8507h);
        this.f8504e.postDelayed(new Runnable() { // from class: cn.nubia.plugin.gameshader.ShaderSettingWindow.2
            @Override // java.lang.Runnable
            public void run() {
                ((ShaderSettingListView.ShaderListAdapter) ShaderSettingWindow.this.f8511l).a(ShaderSettingWindow.this.f8507h);
            }
        }, 20L);
        this.f8512m.setText(R.string.gameshader_title_full);
    }

    public void c() {
        if (this.f8506g) {
            this.f8506g = false;
            this.f8505f.removeView(this.f8501b);
            GaLog.e("GameShaderMgr", "close setting");
        }
    }

    public void e() {
        GameAssistApplication j2 = GameAssistApplication.j();
        this.f8503d = j2;
        this.f8505f = (WindowManager) j2.getSystemService(WindowManager.class);
        this.f8502c = this.f8503d.getResources().getDimensionPixelSize(R.dimen.plugin_gameshader_setting_width);
    }

    public void j() {
        if (this.f8506g) {
            return;
        }
        this.f8506g = true;
        f();
        g();
        i();
        this.f8505f.addView(this.f8501b, this.f8500a);
        GaLog.e("GameShaderMgr", "show setting");
    }
}
