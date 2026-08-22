package cn.nubia.gameassist.plugin.panel;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.BaseViewController;
import cn.nubia.gameassist.panel.GameAssistWindowManager;
import cn.nubia.gameassist.plugin.sort.PluginSortWindow;
import cn.nubia.gameassist.plugin.sort.SortListAdapter;
import cn.nubia.gameassist.plugin.sort.TileOrderChangedCallback;
import cn.nubia.gameassist.utils.JsonUtil;
import cn.nubia.gameassist.utils.Utils;
import com.zte.gameassist.common.FoldMgr;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;
import com.zte.gameassist.utils.SharedPreferencesUtil;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public class PluginSwitchController extends BaseViewController<ViewGroup> implements RadioGroup.OnCheckedChangeListener, View.OnClickListener, TileOrderChangedCallback {
    private PluginViewController A;

    /* renamed from: q, reason: collision with root package name */
    protected GameAssistWindowManager f7242q;

    /* renamed from: r, reason: collision with root package name */
    private RadioGroup f7243r;

    /* renamed from: s, reason: collision with root package name */
    private PluginRadioButton f7244s;
    private PluginRadioButton t;
    private View u;
    private ImageButton v;
    private ImageButton w;
    private final boolean x;
    public int y;
    protected JsonUtil z;

    public PluginSwitchController(GameAssistWindowManager gameAssistWindowManager) {
        super(gameAssistWindowManager);
        this.y = -1;
        this.f7242q = gameAssistWindowManager;
        this.x = ZteFeature.isSupportCardModeInPlugin();
        this.z = new JsonUtil(this.f6117c);
    }

    private void b0() {
        int a0 = a0();
        this.y = a0;
        this.w.setImageResource(a0 == 1 ? R.drawable.ic_plugin_sort_card : R.drawable.ic_plugin_sort_list);
        l0();
    }

    private void c0() {
        LinearLayout linearLayout = (LinearLayout) i(R.id.layout_switch_mode_plugin);
        if (linearLayout == null) {
            return;
        }
        if (Z()) {
            linearLayout.setVisibility(0);
        } else {
            linearLayout.setVisibility(8);
        }
    }

    private void d0() {
        final int a0 = a0();
        this.y = a0;
        if (a0 == 1) {
            this.t.setChecked(true);
        } else {
            this.f7244s.setChecked(true);
        }
        this.f6125o.postDelayed(new Runnable() { // from class: cn.nubia.gameassist.plugin.panel.e
            @Override // java.lang.Runnable
            public final void run() {
                PluginSwitchController.this.f0(a0);
            }
        }, 20L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f0(int i2) {
        this.A.d0(i2);
        this.A.f0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g0() {
        this.A.f0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h0() {
        this.f6118h.u0("tilehost");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void i0() {
        this.A.f0();
        GaLog.e("PluginSwitchController", "setRecycleViewVisibility");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void j0(String str) {
        this.f6118h.u0("tilehost");
        this.A.e0(str);
    }

    private void k0(int i2) {
        this.y = i2;
        this.A.d0(i2);
        this.f6125o.postDelayed(new Runnable() { // from class: cn.nubia.gameassist.plugin.panel.c
            @Override // java.lang.Runnable
            public final void run() {
                PluginSwitchController.this.g0();
            }
        }, 20L);
        SharedPreferencesUtil.k(this.f6117c).P("mode_plugin", this.z.a(SystemMgr.t(), "mode_plugin", Integer.valueOf(i2)));
        PluginRadioButton pluginRadioButton = this.f7244s;
        if (pluginRadioButton == null || this.t == null) {
            return;
        }
        pluginRadioButton.b(i2);
        this.t.b(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l0() {
        this.A.c0();
        this.f6125o.postDelayed(new Runnable() { // from class: cn.nubia.gameassist.plugin.panel.f
            @Override // java.lang.Runnable
            public final void run() {
                PluginSwitchController.this.i0();
            }
        }, 100L);
    }

    private void n0() {
        String str = a0() == 1 ? "explanation" : "switch";
        Bundle bundle = new Bundle();
        bundle.putString("action_type", "switch_mode");
        bundle.putString("action_value", str);
        bundle.putInt("report_interval", 1);
        NubiaTrackManager.p().x("cn.nubia.gamelauncher", "plugin_library_switch_mode", bundle);
        GaLog.a("PluginSwitchController", "sendEventOneDay: switchMode = " + str);
    }

    private void o0() {
        ListView listView = new ListView(this.f6117c);
        listView.setAdapter((ListAdapter) new SortListAdapter(this.f6117c));
        listView.setDivider(null);
        listView.setVerticalScrollBarEnabled(false);
        int dimensionPixelOffset = j().getResources().getDimensionPixelOffset(R.dimen.switch_plugin_pop_window_width);
        int dimensionPixelOffset2 = j().getResources().getDimensionPixelOffset(R.dimen.switch_plugin_pop_window_margin);
        final PopupWindow popupWindow = new PopupWindow((View) listView, dimensionPixelOffset, dimensionPixelOffset, true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(this.f6117c.getDrawable(R.drawable.game_assist_plugin_pop_up_bg));
        popupWindow.showAsDropDown(this.v, 0, dimensionPixelOffset2);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: cn.nubia.gameassist.plugin.panel.PluginSwitchController.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
                SharedPreferencesUtil.k(((BaseViewController) PluginSwitchController.this).f6117c).g0(Utils.j(), i2);
                popupWindow.dismiss();
                PluginSwitchController.this.l0();
            }
        });
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public int C() {
        return R.id.game_assist_left_panel;
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void P() {
        PluginRadioButton pluginRadioButton = this.f7244s;
        if (pluginRadioButton != null) {
            pluginRadioButton.setOnClickListener(null);
            this.f7244s = null;
        }
        PluginRadioButton pluginRadioButton2 = this.t;
        if (pluginRadioButton2 != null) {
            pluginRadioButton2.setOnClickListener(null);
            this.t = null;
        }
        RadioGroup radioGroup = this.f7243r;
        if (radioGroup != null) {
            radioGroup.setOnCheckedChangeListener(null);
            this.f7243r = null;
        }
        if (this.u != null) {
            this.u = null;
        }
        ImageButton imageButton = this.v;
        if (imageButton != null) {
            imageButton.setOnClickListener(null);
            this.v = null;
        }
        ImageButton imageButton2 = this.w;
        if (imageButton2 != null) {
            imageButton2.setOnClickListener(null);
            this.w = null;
        }
    }

    public boolean Z() {
        return this.x;
    }

    public int a0() {
        if (!this.x) {
            return 0;
        }
        JsonUtil jsonUtil = this.z;
        int i2 = 1;
        if (jsonUtil == null) {
            return 1;
        }
        Object b2 = jsonUtil.b(SystemMgr.t(), "mode_plugin");
        if (b2 != null) {
            i2 = Integer.parseInt(b2 + "");
        }
        GaLog.a("PluginSwitchController", "getPluginMode: pluginMode = " + i2);
        return i2;
    }

    @Override // cn.nubia.gameassist.plugin.sort.TileOrderChangedCallback
    public void b() {
        GaLog.a("PluginSwitchController", "onTileOrderChanged ");
        l0();
        this.f6125o.postDelayed(new Runnable() { // from class: cn.nubia.gameassist.plugin.panel.d
            @Override // java.lang.Runnable
            public final void run() {
                PluginSwitchController.this.h0();
            }
        }, 50L);
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    /* renamed from: e0, reason: merged with bridge method [inline-methods] */
    public void o(ViewGroup viewGroup) {
        ImageView imageView;
        this.f6120j = this.f6121k;
        this.A = (PluginViewController) this.f7242q.T(PluginViewController.class);
        GaLog.a("PluginSwitchController", "initModuleView: mPluginViewController = " + this.A);
        this.f7243r = (RadioGroup) i(R.id.switch_plugin_group);
        this.u = i(R.id.sort_plugin_group);
        this.f7244s = (PluginRadioButton) i(R.id.switch_plugin_list);
        this.t = (PluginRadioButton) i(R.id.switch_plugin_card);
        this.f7244s.setIsHorizontal(this.f6120j);
        this.t.setIsHorizontal(this.f6120j);
        this.v = (ImageButton) i(R.id.sort_plugin_btn);
        this.w = (ImageButton) i(R.id.switch_plugin_btn);
        this.v.setOnClickListener(this);
        this.w.setOnClickListener(this);
        if (ZteFeature.isSupportSort()) {
            this.u.setVisibility(0);
            this.f7243r.setVisibility(8);
            new PluginSortWindow.TileOrderChanged().a(this);
        } else {
            this.f7243r.setVisibility(0);
            this.u.setVisibility(8);
            this.f7243r.setOnCheckedChangeListener(this);
        }
        c0();
        if (ZteFeature.isSupportSort()) {
            b0();
        } else {
            d0();
        }
        if (!FoldMgr.f() || (imageView = (ImageView) i(R.id.game_assist_plugins_edit)) == null) {
            return;
        }
        imageView.setOnClickListener(this);
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void h(PrintWriter printWriter, String str) {
        super.h(printWriter, str);
        printWriter.println(str + "  mIsSupportCard = " + this.x);
        printWriter.println(str + "  pluginMode = " + a0());
    }

    public void m0(final String str) {
        this.f6125o.postDelayed(new Runnable() { // from class: cn.nubia.gameassist.plugin.panel.b
            @Override // java.lang.Runnable
            public final void run() {
                PluginSwitchController.this.j0(str);
            }
        }, 100L);
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public void onCheckedChanged(RadioGroup radioGroup, int i2) {
        if (i2 == R.id.switch_plugin_list) {
            GaLog.a("PluginSwitchController", "onClick: switch_plugin_list");
            k0(0);
        } else if (i2 == R.id.switch_plugin_card) {
            GaLog.a("PluginSwitchController", "onClick: switch_plugin_card");
            k0(1);
        }
        n0();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.sort_plugin_btn) {
            o0();
            return;
        }
        if (id == R.id.switch_plugin_btn) {
            k0(this.y == 0 ? 1 : 0);
            this.w.setImageResource(this.y == 1 ? R.drawable.ic_plugin_sort_card : R.drawable.ic_plugin_sort_list);
        } else if (id == R.id.game_assist_plugins_edit) {
            SharedPreferencesUtil.k(this.f6117c).g0(Utils.j(), 3);
            ((PluginSortWindow) InflaterHelper.e(R.layout.plugin_custome_panel_content)).l();
            this.f6118h.g0("tilehost");
        }
    }
}
